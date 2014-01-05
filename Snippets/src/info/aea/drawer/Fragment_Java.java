package info.aea.drawer;

import info.aea.activities.activity_compiler;
import info.aea.database.SnippetsDB_Helper;
import info.aea.database.SourceCode_FragmentAdapter;
import info.aea.database.SourceCode_Table;
import info.aea.snippets.R;

import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment_Java extends Fragment {
	
	String codeid[];
	String codelang[];
	String codetitle[];
	String codesource[];
	String codeoutput[];

	ListView listview;

	SQLiteDatabase db;
	SnippetsDB_Helper logindb;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {       
		
        View view = inflater.inflate(R.layout.fragment_java, container,false);
        
        System.out.println("loading listview.........");
     
        listview = (ListView) view.findViewById(R.id.listView1);
        
        logindb = new SnippetsDB_Helper(getActivity()); 
        
       
        String lang="java";
		List<SourceCode_Table> ls = logindb.getall(lang);
		
		codeid    = new String[ls.size()];
		codelang    = new String[ls.size()];
		codetitle    = new String[ls.size()];
		codesource    = new String[ls.size()];
		codeoutput = new String[ls.size()];

		int i;
		for( i = 0; i<ls.size();i++)	{
			codeid[i]= ls.get(i).getCodeID();
			codelang[i]= ls.get(i).getCodeLang();
			codetitle[i]= ls.get(i).getCodeTitle();
			codesource[i]= ls.get(i).getCodeSource();
			codeoutput[i]= ls.get(i).getCodeOutput();
			
			Log.v("code id","-------"+ls.get(i).getCodeID());
			Log.v("code lang","-------"+ls.get(i).getCodeLang());
			Log.v("code title","-------"+ls.get(i).getCodeTitle());
			Log.v("code source","-------"+ls.get(i).getCodeSource());
			Log.v("code output","-------"+ls.get(i).getCodeOutput());
			
			System.out.println("code id= =============+++++++++++++++++++"+ codeid[i]);
			System.out.println("code lang==============+++++++++++++++++++"+ codelang[i]);
			System.out.println("code title==============+++++++++++++++++++"+ codetitle[i]);
			System.out.println("code source==============+++++++++++++++++++"+ codesource[i]);
			System.out.println("code output==============+++++++++++++++++++"+ codeoutput[i]);
			
		}

        listview.setAdapter(new SourceCode_FragmentAdapter(getActivity(), codeid, codelang, codetitle, codesource, codeoutput));  
              
        listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, final int position,
					long arg3) {
				//Create bundle to reference values in next class
                final Bundle bundle = new Bundle();
				bundle.putString("codeid", codeid[position]);
                bundle.putString("codelang", codelang[position]);
                
				//final String item = (String) arg0.getItemAtPosition(position);
				AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());

				// LayoutInflater inflater= getActivity().getLayoutInflater();
			        //this is what I did to add the layout to the alert dialog
			 	 adb.setTitle("Code Commands");
    			 adb.setMessage(codetitle[position]);
    			 adb.setIcon(R.drawable.cmd);
    			 
    			 //final EditText input = new EditText(getActivity());
    			 //input.setText("Send a message: ");
				 //adb.setView(input);

				adb.setNegativeButton("Execute", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog,int id) {
              
					Intent i=new Intent(getActivity(), activity_compiler.class);
					i.putExtras(bundle);
					startActivity(i);
							}
						});
				
				// Setting Negative "Save" Button
                adb.setNeutralButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    // User pressed No button. Write Logic Here
                    System.out.println("value of id=========" + codeid[position]);
                    String id = codeid[position];
                    System.out.println("String value......."+id);
                    String bit = "1";
                    logindb.update_save2(id, bit);
                    System.out.println("update command.......");
                    Toast.makeText(getActivity(), "Code saved in my codes", Toast.LENGTH_SHORT).show();
                    }
                });
 
                // Setting Netural "Cancel" Button
                adb.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    // User pressed Cancel button. Write Logic Here
                    Toast.makeText(getActivity(), "You clicked on Cancel",
                                        Toast.LENGTH_SHORT).show();
                    }
                });
				
				
						adb.show();


	}
		});


        return view;  
	
        
    
    }
	
	
	
}