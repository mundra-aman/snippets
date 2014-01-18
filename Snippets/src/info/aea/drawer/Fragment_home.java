package info.aea.drawer;

import info.aea.snippets.R;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment_home extends Fragment {
	
	public Fragment_home(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
		
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        TextView hackerspace = (TextView) rootView.findViewById(R.id.hacker_space);
		hackerspace.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
            	Fragment frag = new java_webview();  
                android.app.FragmentManager fragmentManager = getFragmentManager();  
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  
                fragmentTransaction.add(R.id.frame_container, frag); 
                fragmentTransaction.commit();
            	}
	    });

        
        
        return rootView;
    }
}
