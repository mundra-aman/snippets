package info.aea.drawer;

import info.aea.snippets.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_Settings extends Fragment {
	
	public Fragment_Settings(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_c, container, false);
         
        return rootView;
    }
}
