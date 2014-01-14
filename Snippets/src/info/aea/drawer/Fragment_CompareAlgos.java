package info.aea.drawer;

import info.aea.database.Algorithms_FragmentAdapter;
import info.aea.database.Algorithms_Table;
import info.aea.database.SnippetsDB_Helper;
import info.aea.snippets.R;

import java.util.List;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_CompareAlgos extends Fragment {
	
	String algoid[];
	String algotitle[];
	
	ListView listview, listview2;

	SQLiteDatabase db;
	SnippetsDB_Helper logindb;
	
	@Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {       
		
        View view = inflater.inflate(R.layout.fragment_comparealgos, container,false);
         
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
			
			
			System.out.println("code id= =============+++++++++++++++++++"+ algoid[i]);
			System.out.println("code title==============+++++++++++++++++++"+ algotitle[i]);
			
		}

        listview.setAdapter(new Algorithms_FragmentAdapter(getActivity(), algoid, algotitle));
        listview2.setAdapter(new Algorithms_FragmentAdapter(getActivity(), algoid, algotitle));
              
        listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
			// on click code
		//		TextView textView = (TextView) getActivity().findViewById(R.id.title1);
			//	String algo=( algotitle[position] );
				//textView.setText(algo);
				
			}
		});
        
        listview2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
			// on click code
				TextView textView = (TextView) getActivity().findViewById(R.id.title2);
				String algo=( algotitle[position] );
				textView.setText(algo);
				
			}
		});
        
        return view;    
    
    }	
}