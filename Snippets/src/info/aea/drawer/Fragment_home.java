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
        
        TextView lang1 = (TextView) rootView.findViewById(R.id.java);
		lang1.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
            	Fragment frag = new webview_java();  
                android.app.FragmentManager fragmentManager = getFragmentManager();  
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  
                fragmentTransaction.add(R.id.frame_container, frag); 
                fragmentTransaction.commit();
            	}
	    });
		
		
		TextView lang2 = (TextView) rootView.findViewById(R.id.c);
		lang2.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
            	Fragment frag = new webview_c();  
                android.app.FragmentManager fragmentManager = getFragmentManager();  
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  
                fragmentTransaction.add(R.id.frame_container, frag); 
                fragmentTransaction.commit();
            	}
	    });
		
		
		TextView lang3 = (TextView) rootView.findViewById(R.id.cpp);
		lang3.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
            	Fragment frag = new webview_cpp();  
                android.app.FragmentManager fragmentManager = getFragmentManager();  
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  
                fragmentTransaction.add(R.id.frame_container, frag); 
                fragmentTransaction.commit();
            	}
	    });
		
		
		TextView lang4 = (TextView) rootView.findViewById(R.id.python);
		lang4.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
            	Fragment frag = new webview_python();  
                android.app.FragmentManager fragmentManager = getFragmentManager();  
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  
                fragmentTransaction.add(R.id.frame_container, frag); 
                fragmentTransaction.commit();
            	}
	    });

		
		TextView lang5 = (TextView) rootView.findViewById(R.id.csharp);
		lang5.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
            	Fragment frag = new webview_csharp();  
                android.app.FragmentManager fragmentManager = getFragmentManager();  
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  
                fragmentTransaction.add(R.id.frame_container, frag); 
                fragmentTransaction.commit();
            	}
	    });
        
        
		TextView lang6 = (TextView) rootView.findViewById(R.id.help);
		lang6.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
            	Fragment frag = new webview_help();  
                android.app.FragmentManager fragmentManager = getFragmentManager();  
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  
                fragmentTransaction.add(R.id.frame_container, frag); 
                fragmentTransaction.commit();
            	}
	    });
		
		
		TextView lang7 = (TextView) rootView.findViewById(R.id.website);
		lang7.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	             //your write code
            	Fragment frag = new webview_website();  
                android.app.FragmentManager fragmentManager = getFragmentManager();  
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();  
                fragmentTransaction.add(R.id.frame_container, frag); 
                fragmentTransaction.commit();
            	}
	    });
		
        return rootView;
    }
}
