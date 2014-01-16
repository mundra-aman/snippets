package info.aea.activities;


import info.aea.compiler.activities.Ideone;
import info.aea.compiler.activities.RunThread;
import info.aea.compiler.adapters.AdapterLanguages;
import info.aea.database.SnippetsDB_Helper;
import info.aea.snippets.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class activity_compare extends Activity {
	
ProgressDialog progressDialog;
	
	TextView textOutput, textOutput2;
	TextView textResult, textResult2;
	TextView texttime, texttime2;
	TextView textmemory, textmemory2;
	
	SQLiteDatabase db;
	SnippetsDB_Helper logindb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compare);
		
		System.out.println("starting activity");
		String codeid1, codeid2; // get from fragment bundle
		//Extract the bundle from the intent to use variables
	    Bundle bundle = getIntent().getExtras();
	    //Extract each value from the bundle for usage
	    codeid1 = bundle.getString("algoid1");
	    codeid2 = bundle.getString("algoid2");
	 	System.out.println("code id1-------" + codeid1 + " codeid2--------" + codeid2);
	    
		
		logindb=new SnippetsDB_Helper(this);	
		
		String str = logindb.getrow_algo(codeid1);
		EditText editText = (EditText)findViewById(R.id.edittextSource);
		editText.setText(str);
		System.out.println(str);
		
		String str2 = logindb.getrow_algo(codeid2);
		EditText editText2 = (EditText)findViewById(R.id.edittextSource2);
		editText2.setText(str2);
		System.out.println(str2);

				 
	   			 
	
		// progress dialog
        progressDialog = ProgressDialog.show(this, "", "Loading. Please wait...", false);
		progressDialog.hide();
        
		final Spinner spinnerLanguage = (Spinner)this.findViewById(R.id.spinnerLanguage);
		final EditText edittextSource = (EditText)this.findViewById(R.id.edittextSource);
		final EditText edittextSource2 = (EditText)this.findViewById(R.id.edittextSource2);
		final EditText edittextInput = (EditText)this.findViewById(R.id.edittextInput);
		final EditText edittextInput2 = (EditText)this.findViewById(R.id.edittextInput2);
		final Activity activity = this;
		textOutput = (TextView)this.findViewById(R.id.textOutput);
		textOutput2 = (TextView)this.findViewById(R.id.textOutput2);
		textResult = (TextView)this.findViewById(R.id.textResult);
		textResult2 = (TextView)this.findViewById(R.id.textResult2);
		texttime = (TextView)this.findViewById(R.id.gettime);
		texttime2 = (TextView)this.findViewById(R.id.gettime2);
		textmemory = (TextView)this.findViewById(R.id.getmem);
		textmemory2 = (TextView)this.findViewById(R.id.getmem2);
		
        // languages
        spinnerLanguage.setAdapter(new AdapterLanguages(this, 1));
        
        // execute button
        Button btnExecute = (Button)this.findViewById(R.id.btnExecute);
        btnExecute.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Ideone ideone = new Ideone(activity);
				
				try{
	        	   RunThread runThread = new RunThread(activity, handler);
	        	   String _s = edittextSource.getText().toString();
	        	   runThread.setSource(_s);
	        	   runThread.setInput(edittextInput.getText().toString());
	        	   runThread.setLang(ideone.getLanguageIdByName(spinnerLanguage.getSelectedItem().toString()));
	        	   runThread.start();
	        	   

	           	
        	   } catch(Exception e){
        		   AlertDialog.Builder errb = new AlertDialog.Builder(activity);
        		   errb.setMessage("Error: " + e.toString());
        		   AlertDialog ad = errb.create();
        		   ad.show();
        	   }
				
				
				try{
					RunThread runThread = new RunThread(activity, handler2);
		        	   String _s = edittextSource2.getText().toString();
		        	   runThread.setSource(_s);
		        	   runThread.setInput(edittextInput2.getText().toString());
		        	   runThread.setLang(ideone.getLanguageIdByName(spinnerLanguage.getSelectedItem().toString()));
		        	   runThread.start();
		        	   
				}
				catch(Exception e){
					AlertDialog.Builder errb = new AlertDialog.Builder(activity);
	        		   errb.setMessage("Error: " + e.toString());
	        		   AlertDialog ad = errb.create();
	        		   ad.show();
				}
				
			}
		});
    }
	
	
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
        	try{
        		        		
        		//Toast.makeText(getApplicationContext(), "Compiling Algorithm 1", Toast.LENGTH_SHORT).show();
        		
        		String command = msg.getData().getString("command");
        		String text = msg.getData().getString("text");
        		
	            if( command.equals("open") ){
	            	progressDialog.show();
	            	textResult.setText("");
	            	
	            } else if( command.equals("close") ){
	            	progressDialog.hide();
	            	
	            } else if( command.equals("echo") ){
	            	progressDialog.setMessage(text);
	            	
	            } else if( command.equals("echo2") ){
	            	textOutput.setText(text);
	            	//textOutput2.setText(text);
	            
	            } else if( command.equals("result") ){
	            	textResult.setText(text);
	            	//textResult2.setText(text);
		        	   	
	            		System.out.println("time-space tradeoff 2");
	            		System.out.println("compile time=======" + Ideone.time);
			           	texttime.setText(String.valueOf(Ideone.time + "sec"));
			          // 	texttime2.setText(String.valueOf(Ideone.time));
			           	System.out.println("compile memory========" + Ideone.memory);
			           	textmemory.setText(String.valueOf(Ideone.memory + " bytes"));
			           	//textmemory2.setText(String.valueOf(Ideone.memory));
			        
	            	//Toast.makeText(getApplicationContext(), "Authorization Error", Toast.LENGTH_LONG).show();	
	            } else if( command.equals("error")){
	            	textOutput.setText(text);
	            	progressDialog.hide();
	            }
        	} catch( Exception e ){
        		//int a = 0;
        	}
        	
        	
    
        }
    
    };
    
    
    final Handler handler2 = new Handler() {
        public void handleMessage(Message msg) {
        	try{
        		
        		//Toast.makeText(getApplicationContext(), "Compiling Algorithm 2", Toast.LENGTH_SHORT).show();
        		
        		String command = msg.getData().getString("command");
        		String text = msg.getData().getString("text");
        		
	            if( command.equals("open") ){
	            	progressDialog.show();
	            	textResult2.setText("");
	            	
	            } else if( command.equals("close") ){
	            	progressDialog.hide();
	            	
	            } else if( command.equals("echo") ){
	            	progressDialog.setMessage(text);
	            	
	            } else if( command.equals("echo2") ){
	            	//textOutput.setText(text);
	            	textOutput2.setText(text);
	            
	            } else if( command.equals("result") ){
	            	//textResult.setText(text);
	            	textResult2.setText(text);
		        	   	
	            		System.out.println("time-space tradeoff 2");
	            		System.out.println("compile time=======" + Ideone.time);
			          // 	texttime.setText(String.valueOf(Ideone.time));
			           	texttime2.setText(String.valueOf(Ideone.time + "sec"));
			           	System.out.println("compile memory========" + Ideone.memory);
			          // 	textmemory.setText(String.valueOf(Ideone.memory));
			           	textmemory2.setText(String.valueOf(Ideone.memory + "bytes"));
			        
	            	//Toast.makeText(getApplicationContext(), "Authorization Error", Toast.LENGTH_LONG).show();	
	            } else if( command.equals("error")){
	            	textOutput.setText(text);
	            	progressDialog.hide();
	            }
        	} catch( Exception e ){
        		//int a = 0;
        	}
        	
        	
    
        }
    
    };
    
    
}