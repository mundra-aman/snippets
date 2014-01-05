package info.aea.database;

public class SourceCode_Table {
	private String  CodeID;
	private String CodeLang;
	private String CodeTitle;
	private String CodeSource;
    private String CodeOutput;
	

	// constructor
    public SourceCode_Table(String codeID, String codeLang, String CodeTitle, String CodeSource, String CodeOutput){
        this.CodeID= codeID;
        this.CodeLang= codeLang;
    	this.CodeTitle = CodeTitle;
        this.CodeSource = CodeSource ;
        this.CodeOutput = CodeOutput;
             }

    public SourceCode_Table(){

    }


    public void setCodeID(String id){
        this.CodeID = id;
    }
    
    public void setCodeLang(String lang){
        this.CodeLang = lang;
    }

    public void setCodeTitle(String title){
        this.CodeTitle = title;
    }

    public void setCodeSource(String source){
        this.CodeSource = source;
    }

    public void setCodeOutput(String output){
        this.CodeOutput = output;
    }
  
    
    public String getCodeID(){
        return this.CodeID;
    }
    
    public String getCodeLang(){
        return this.CodeLang;
    }

    public String getCodeTitle(){
        return this.CodeTitle;
    }

    public String getCodeSource(){
        return this.CodeSource;
    }

    public String getCodeOutput(){
        return this.CodeOutput;
    }

 }
