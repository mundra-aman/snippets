package info.aea.drawer;

import info.aea.rss.AndroidRSSReaderApplicationActivity;
import info.aea.snippets.R;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_SnipFeeds extends Fragment {
	
	public Fragment_SnipFeeds(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_snipfeeds, container, false);
         
        // execute button
        Button btn = (Button) rootView.findViewById(R.id.buttontest);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				Intent i = new Intent(getActivity(), AndroidRSSReaderApplicationActivity.class);
				startActivity(i);
				
				/*
				// Create new fragment and transaction
			    Fragment newFragment = new Fragment_Java(); 
			    // consider using Java coding conventions (upper char class names!!!)
			    FragmentTransaction transaction = getFragmentManager().beginTransaction();

			    // Replace whatever is in the fragment_container view with this fragment,
			    // and add the transaction to the back stack
			    transaction.replace(R.id.frame_container, newFragment);
			    transaction.addToBackStack(null);

			    // Commit the transaction
			    transaction.commit(); 
				*/
			}
		});
       
	
         
        return rootView;
    }
}
