package info.aea.drawer;

import info.aea.activities.activity_compileMyCode;
import info.aea.activities.activity_compiler;
import info.aea.database.SnippetsDB_Helper;
import info.aea.snippets.R;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Fragment_CompileOwnCode extends Fragment {
	
	SQLiteDatabase db;
	SnippetsDB_Helper logindb;
	
	public Fragment_CompileOwnCode(){}
	
	public Spinner langspinner;
    String[] langs = { "Java", "C++", "C", "C# .net", "Python" };
    String AlgoLang, AlgoSource, Algoid, AlgoTitle;
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_own_code, container, false);
        
        logindb=new SnippetsDB_Helper(getActivity());
		
         
        langspinner = (Spinner) rootView.findViewById(R.id.langspinner);
		ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, langs);
		spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		langspinner.setAdapter(spinnerArrayAdapter2);
		
		final EditText codesource = (EditText) rootView.findViewById(R.id.edittextSource);
		final String source = codesource.getText().toString();
		AlgoSource = codesource.getText().toString();
		System.out.println("source"+AlgoSource);
		
		final EditText title = (EditText) rootView.findViewById(R.id.title);
		AlgoTitle = title.getText().toString();
		System.out.println("source"+AlgoSource);
		
		langspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	        // your code here
	        	AlgoLang = langspinner.getSelectedItem().toString();
	    		System.out.println("show lang selected--------------"+ AlgoLang);
	            }

	        
	    @Override
	    public void onNothingSelected(AdapterView<?> parentView) {
	        // your code here
	    }

	});
		
		
		
		System.out.println(AlgoSource + AlgoTitle + AlgoLang + Algoid);
		//Create bundle to reference values in next class
        final Bundle bundle = new Bundle();
		bundle.putString("Algoid", Algoid);
        bundle.putString("Algolang", AlgoLang);
        bundle.putString("AlgoSource", AlgoSource);
        bundle.putString("AlgoTitle", AlgoTitle);
		
        System.out.println("insert details" + AlgoSource + AlgoTitle + AlgoLang + Algoid);
        
		Button execute = (Button) rootView.findViewById(R.id.execute);
		execute.setOnClickListener(new OnClickListener() {

			
	        @Override
	        public void onClick(View v) {
	             //your write code
	        	
	        	final Bundle bundle = new Bundle();
	    		bundle.putString("Algoid", Algoid);
	            bundle.putString("Algolang", AlgoLang);
	            bundle.putString("AlgoSource", AlgoSource);
	            bundle.putString("AlgoTitle", AlgoTitle);
	        	
	        	Intent i=new Intent(getActivity(), activity_compileMyCode.class);
				i.putExtras(bundle);
				startActivity(i);
            	
	        }
	    });
		
		Button save = (Button) rootView.findViewById(R.id.save);
		save.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
	        	AlgoSource = codesource.getText().toString();
	    		System.out.println("source"+AlgoSource);
	    		
	    		
	    		AlgoTitle = title.getText().toString();
	    		System.out.println("source"+AlgoSource);
	    		
	        	System.out.println("insert details" + AlgoSource + AlgoTitle + AlgoLang + Algoid);
	        	String bit = "1";
	        	Algoid = "tt";
                logindb.insertcode(Algoid, AlgoLang, AlgoSource, AlgoTitle, bit);
                System.out.println("update command.......");
                Toast.makeText(getActivity(), "Code saved in my codes", Toast.LENGTH_SHORT).show();
                
            	
	        }
	    });
		
		Button share = (Button) rootView.findViewById(R.id.share);
		share.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
	        	Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Heading");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, source);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
      
            	
	        }
	    });

		
        return rootView;
    }
}
