package info.aea.drawer;

import info.aea.snippets.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Fragment_CompileOwnCode extends Fragment {
	
	public Fragment_CompileOwnCode(){}
	
	public Spinner langspinner;
    String[] langs = { "Java", "C++", "C", "C# .net", "Python" };
    
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_own_code, container, false);
         
        langspinner = (Spinner) rootView.findViewById(R.id.langspinner);
		ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, langs);
		spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
		langspinner.setAdapter(spinnerArrayAdapter2);
		langspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	        // your code here
	        	 String language = langspinner.getSelectedItem().toString();
	    		System.out.println("show date selected--------------"+ language);
	            }

	    @Override
	    public void onNothingSelected(AdapterView<?> parentView) {
	        // your code here
	    }

	});
		
        return rootView;
    }
}
