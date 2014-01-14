package info.aea.drawer;

import info.aea.activities.activity_compare;
import info.aea.database.Algorithms_FragmentAdapter;
import info.aea.database.Algorithms_Table;
import info.aea.database.SnippetsDB_Helper;
import info.aea.snippets.R;

import java.util.List;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_CompareAlgos extends Fragment {
	
	ProgressDialog progressDialog;
	
	TextView textOutput;
	TextView textResult;
	
	String algoid[];
	String algotitle[];
	
	String id1, id2;
	
	ListView listview, listview2;

	SQLiteDatabase db;
	SnippetsDB_Helper logindb;
	
	@Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {       
		
        final View view = inflater.inflate(R.layout.fragment_comparealgos, container,false);
         
        listview = (ListView) view.findViewById(R.id.algolist1);
        listview2 = (ListView) view.findViewById(R.id.algolist2);
        
        logindb = new SnippetsDB_Helper(getActivity()); 
               
        List<Algorithms_Table> al = logindb.getallAlgos();
		
		algoid    = new String[al.size()];
		algotitle    = new String[al.size()];
		
		int i;
		for( i = 0; i<al.size();i++)	{
			algoid[i]= al.get(i).getAlgoID();
			algotitle[i]= al.get(i).getAlgoTitle();
			
		}

        listview.setAdapter(new Algorithms_FragmentAdapter(getActivity(), algoid, algotitle));
        listview2.setAdapter(new Algorithms_FragmentAdapter(getActivity(), algoid, algotitle));
              
        listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
			// on click code
				TextView textView = (TextView) getActivity().findViewById(R.id.title1);
				String algo=( algotitle[position] );
				Toast.makeText(getActivity(), "Algorithm 1 selected", Toast.LENGTH_SHORT).show();
     			textView.setText(algo);
     			id1 = algoid[position];
				System.out.println("id1======" + id1);
			}
		});
        
        
        listview2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
			// on click code
				TextView textView = (TextView) view.findViewById(R.id.title2);
				String algo=( algotitle[position] );
				Toast.makeText(getActivity(), "Algorithm 2 selected", Toast.LENGTH_SHORT).show();
     			textView.setText(algo);
     			id2 = algoid[position];
     			System.out.println("id2======" + id2);
				
			}
		});   
        
      
        
        Button button = (Button) view.findViewById(R.id.compare);    
		button.setOnClickListener(new View.OnClickListener() {
 			
			@Override
			public void onClick(View arg0) {
				//Create bundle to reference values in next class
		        final Bundle bundle = new Bundle();
				bundle.putString("algoid1", id1);
		        bundle.putString("algoid2", id2);
		      
				Intent i = new Intent(getActivity(), activity_compare.class);
				i.putExtras(bundle);
				startActivity(i);
 
			  
			}
 
		});

        
		return view; 
        
        }
    }