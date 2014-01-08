package info.ideone.activity;

import info.ideone.exceptions.AuthException;
import info.ideone.exceptions.DataException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;


import android.content.Context;

public class Ideone{

	private static final String URL = "http://ideone.com/api/1/service";
	protected HashMap<Integer, String> langs = new HashMap<Integer, String>();
	protected ArrayList<String> langsList = new ArrayList<String>();
	protected Context context;
	
	String user = "devey";
	String pass = "26266aman19";

	public Ideone(Context context){
		
		this.context = context;
		
		langs.put(1, "C++");
		langs.put(2, "Pascal (gpc)");
		langs.put(3, "Perl");
		langs.put(4, "Python");
		langs.put(5, "Fortran");
		langs.put(6, "Whitespace");
		langs.put(7, "Ada");
		langs.put(8, "Ocaml");
		langs.put(9, "Intercal");
		langs.put(10, "Java");
		langs.put(11, "C");
		langs.put(12, "Brainf**k");
		langs.put(13, "Assembler");
		langs.put(14, "CLIPS");
		langs.put(15, "Prolog (swi)");
		langs.put(16, "Icon");
		langs.put(17, "Ruby");
		langs.put(19, "Pike");
		langs.put(21, "Haskell");
		langs.put(22, "Pascal (fpc)");
		langs.put(23, "Smalltalk");
		langs.put(25, "Nice");
		langs.put(26, "Lua");
		langs.put(27, "C#");
		langs.put(28, "Bash");
		langs.put(29, "PHP");
		langs.put(30, "Nemerle");
		langs.put(32, "Common Lisp (clisp)");
		langs.put(33, "Scheme (guile)");
		langs.put(34, "C99 strict");
		langs.put(35, "JavaScript (rhino)");
		langs.put(36, "Erlang");
		langs.put(38, "Tcl");
		langs.put(39, "Scala");
		langs.put(40, "SQL");
		langs.put(43, "Objective-C");
		langs.put(44, "C++0x");
		langs.put(45, "Assembler");
		langs.put(54, "Perl 6");
		langs.put(101, "Visual Basic .NET");
		langs.put(102, "D (dmd)");
		langs.put(104, "AWK (gawk)");
		langs.put(105, "AWK (mawk)");
		langs.put(106, "COBOL 85");
		langs.put(107, "Forth");
		langs.put(108, "Prolog (gnu)");
		langs.put(110, "bc");
		langs.put(111, "Clojure");
		langs.put(112, "JavaScript (spidermonkey)");
		langs.put(114, "Go");
		langs.put(115, "Unlambda");
		langs.put(116, "Python 3");
		langs.put(117, "R");
		langs.put(118, "COBOL");
		langs.put(119, "Oz");
		langs.put(121, "Groovy");
		langs.put(122, "Nimrod");
		langs.put(123, "Factor");
		langs.put(124, "F#");
		langs.put(125, "Falcon");
		for( Integer key : langs.keySet() ){
			this.langsList.add(langs.get(key));
		}
		Collections.sort(this.langsList);
		
	}
	
	protected HttpTransportSE createTransport(){
		HttpTransportSE transport = new HttpTransportSE(URL);
		transport.debug = false;
		return transport;
	}
	
	protected SoapObject createSoapObject(String method){
    	return new SoapObject(URL, method);
	}
	
	 /**
     * Creates a new submission.
     * 
     * @param sourceCode    source code
     * @param language		language identifier
     * @param input			intput
     * @param run			set to true to execute the program
     * @param priv			set to false not to list the submission in the 'recent pastes' page on ideone.com
     * @return				identifier of the submission
	 * @throws Exception 
     */
    public String createSubmission(String sourceCode, Integer language, String input, Boolean run, Boolean priv) throws Exception {

	    updateUserPass();
	    String ret = null;
		Hashtable data = new Hashtable();
		
		// transport
	    HttpTransportSE transport = createTransport();
	    
	    // soap object
	    SoapObject request = createSoapObject("createSubmission");
	    request.addProperty("user", user);
	    request.addProperty("pass", pass);
	    request.addProperty("sourceCode", sourceCode);
	    request.addProperty("language", language);
	    request.addProperty("input", input);
	    request.addProperty("run", run);
	    request.addProperty("private", priv);
	    
	    // serializacja
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
	    
        // request
	    transport.call("createSubmission", envelope);
	    SoapObject so = (SoapObject) envelope.getResponse();

	    int count = so.getPropertyCount();
	    for(int i = 0; i < count; ++i) {
			SoapObject so2 = (SoapObject)so.getProperty(i);
			String key = (String) so2.getProperty(0);
			Object val = so2.getProperty(1);
			data.put(key, val);
	    }

	    String error = (String) data.get("error");
	    if(error.equals("AUTH_ERROR")){
	    	throw new AuthException();
	    } else if(!error.equals("OK")) {
	    	throw new DataException(data.get("error").toString());
	    }

	    ret = (String)data.get("link");
		return ret;
    }

    private void updateUserPass() throws AuthException {
    	if(user.equals("") || pass.equals("")){
    		throw new AuthException();
    	}
    	// SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		// user = prefs.getString("ideone_username", "");
		// pass = prefs.getString("ideone_password", "");
	}

	/**
     * Returns status and result of the submission.
     *
     * @param link	identifier of the submission
     * @return		information about status and result of the submission
	 * @throws AuthException 
	 * @throws DataException 
     */
    public IdeoneSubmissionStatus getSubmissionStatus(String link) throws AuthException, DataException {

    	updateUserPass();
		IdeoneSubmissionStatus ret = null;
		Hashtable data = new Hashtable();
		
		HttpTransportSE transport = createTransport();
	    SoapObject request = createSoapObject("getSubmissionStatus");
	    request.addProperty("user", user);
	    request.addProperty("pass", pass);
	    request.addProperty("link", link);
	    
	    // serializacja
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
	    
        // request
        try{
        	transport.call("getSubmissionStatus", envelope);
        	SoapObject so = (SoapObject) envelope.getResponse();
    	
		    int count = so.getPropertyCount();
		    for(int i = 0; i < count; ++i) {
				SoapObject so2 = (SoapObject) so.getProperty(i);
				String key = (String) so2.getProperty(0);
				Object val = so2.getProperty(1);
				data.put(key, val);
		    }
		    
		    // 
		    String error = (String) data.get("error");
		    if(error.equals("AUTH_ERROR")){
		    	throw new AuthException();
		    } else if(!error.equals("OK")) {
		    	throw new DataException(data.get("error").toString());
		    }
		    
        } catch (IOException ex) {
    	    throw new DataException("transport");
    	} catch (XmlPullParserException ex){
    		throw new DataException(ex.toString());
    	}
        
	    ret = new IdeoneSubmissionStatus();
	    ret.result = (Integer)data.get("result");
	    ret.status = (Integer)data.get("status");

	    return ret;
    }


    /**
     * Returns information about the submission.
     *
     * @param link	    	identifier of the submission
     * @param withSource    set to true if the source code should be returned
     * @param withInput	    set to true if the input should be returned
     * @param withOutput    set to true if the output produced by the program should be returned
     * @param withStderr    set to true if the std error should be returned
     * @param withCmpinfo   set to true if the compilation info should be returned
     * @return				final information about the submission
     * @throws AuthException 
     */
    public IdeoneSubmissionDetails getSubmissionDetails(String link, Boolean withSource, Boolean withInput,
	    Boolean withOutput, Boolean withStderr, Boolean withCmpinfo) throws AuthException {

    	updateUserPass();
    	IdeoneSubmissionDetails ret = null;
    	Hashtable data = new Hashtable();

		try {
			HttpTransportSE transport = createTransport();
			SoapObject request = createSoapObject("getSubmissionDetails");
		    request.addProperty("user", user);
		    request.addProperty("pass", pass);
		    request.addProperty("link", link);
		    request.addProperty("withSource", withSource);
		    request.addProperty("withInput", withInput);
		    request.addProperty("withOutput", withOutput);
		    request.addProperty("withStderr", withStderr);
		    request.addProperty("withCmpinfo", withCmpinfo);
		    
		    // serializacja
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.setOutputSoapObject(request);
		    
	        // request
		    transport.call("getSubmissionDetails", envelope);
		    SoapObject so = (SoapObject) envelope.getResponse();
	
	
		    int count = so.getPropertyCount();
		    for(int i = 0; i < count; ++i) {
				SoapObject so2 = (SoapObject) so.getProperty(i);
				String key = (String) so2.getProperty(0);
				Object val = so2.getProperty(1);
				data.put(key, val);
		    }
	
		    String error = (String) data.get("error");
		    if(error.equals("AUTH_ERROR")){
		    	throw new AuthException();
		    } else if(!error.equals("OK")) {
		    	throw new DataException(data.get("error").toString());
		    }
		    
		    ret = new IdeoneSubmissionDetails();
		    ret.langId = (Integer) data.get("result");
		    ret.langName = (String) data.get("langName");
		    ret.langVersion = (String) data.get("langVersion");
		    ret.date = (String) data.get("date");
		    //ret.time = (Float) data.get("time");  // no support for Float on ME kSoap
		    ret.time = Float.valueOf( ((SoapPrimitive) data.get("time")).toString() );
		    ret.result = (Integer) data.get("result");
		    ret.status = (Integer) data.get("status");
		    ret.memory = (Integer) data.get("memory");
		    ret.signal = (Integer) data.get("signal");
		    ret.isPublic = (Boolean) data.get("public");
		    
		    if(withSource.booleanValue()) {
		    	ret.source = (String) data.get("source");
		    }
		    if(withInput.booleanValue()) {
		    	ret.input = (String) data.get("input");
		    }
		    if(withOutput.booleanValue()) {
		    	ret.output = (String) data.get("output");
		    }
		    if(withStderr.booleanValue()) {
		    	ret.stderr = (String) data.get("stderr");
		    }
		    if(withCmpinfo.booleanValue()) {
		    	ret.cmpinfo = (String) data.get("cmpinfo");
		    }
		    
		} catch (IOException ex) {
		    System.out.println("IO Error");
		} catch (NumberFormatException ex) {
		    System.out.println("Number Format Error");
		} catch (Exception ex) {
		    System.out.println("Error");
		}
	
		return ret;
    }


    /**
     * Returns list of supported languages.
     *
     * @return	a map of pairs "language id" => "language name and version"
     * @throws AuthException 
     */
    public Hashtable getLanguages() throws AuthException {

	    updateUserPass();
		Hashtable ret = new Hashtable();
		Hashtable data = new Hashtable();
	
		try {
		    HttpTransportSE transport = createTransport();
		    transport.debug = true;
		    SoapObject request = createSoapObject("getLanguages");
		    request.addProperty("user", user);
		    request.addProperty("pass", pass);
		    
		    // serializacja
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.setOutputSoapObject(request);
		    
	        // request
		    transport.call("getLanguages", envelope);
		    SoapObject so = (SoapObject) envelope.getResponse();
	
		    int count = so.getPropertyCount();
		    for(int i = 0; i < count; ++i) {
				SoapObject so2 = (SoapObject) so.getProperty(i);
				String key = (String) so2.getProperty(0);
				Object val = so2.getProperty(1);
				data.put(key, val);
		    }
	
		    String error = (String) data.get("error");
		    if(!error.equals("OK")) {
				System.out.println("Error occurred: " + error);
				return null;
		    }
	
		    so = (SoapObject)data.get("languages");
		    count = so.getPropertyCount();
		    for(int i = 0; i < count; ++i) {
				SoapObject so2 = (SoapObject) so.getProperty(i);
				Integer key = (Integer) so2.getProperty(0);
				String val = (String) so2.getProperty(1);
				ret.put(key, val);
		    }
	
		} catch (IOException ex) {
		    System.out.println("IO Error" + "\n" + ex.getMessage());
		} catch (NumberFormatException ex) {
		    System.out.println("Number Format Error");
		} catch (Exception ex) {
		    System.out.println("Error");
		}
	
		return ret;
    }
    
    
    public static String translateStatus(int status) {
		if(status < 0) {
		    return "waiting for compilation";
		}
		else if(status == 0) {
		    return "done";
		}
		else if(status == 1) {
		    return "compilation";
		}
		else if(status == 3) {
		    return "running";
		}
		return "unknown status";
    }

    public static String translateState(int state){
    	if( state < 0 ){ return "waiting for compilation"; }
        if( state == 0 ){ return "done"; }
        if( state == 1 ){ return "compilation"; }
        //if( state == 3 ){ 
        return "running";
    }
    
    public static String translateResult(int result) {
		switch(result) {
		    case 0: return "not running";
		    case 11: return "compilation error";
		    case 12: return "runtime error";
		    case 13: return "time limit exceeded";
		    case 15: return "success";
		    case 17: return "memory limit exceeded";
		    case 19: return "illegal system call";
		    case 20: return "internal error";
		}
		return "unknown result";
    }
    
    public ArrayList<String> getLangs(){
    	return this.langsList;
    }
    
    public Integer getLanguageIdByName(String name){
    	for( Integer key : langs.keySet() ){
    		if( langs.get(key).equals(name) ){
    			return key;
    		}
		}
    	return 1;
    }
}
