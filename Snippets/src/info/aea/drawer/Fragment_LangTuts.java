package info.aea.drawer;

import info.aea.activities.TabsPagerAdapter;
import info.aea.snippets.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_LangTuts extends Fragment implements ActionBar.TabListener {
	
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Top Rated", "Games", "Movies", "new" };
	
	public Fragment_LangTuts(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_langtuts, container, false);
         
     // Initilization
     		viewPager = (ViewPager) rootView.findViewById(R.id.pager);
     		actionBar = getActivity().getActionBar();
     //		mAdapter = new TabsPagerAdapter(getFragmentManager());

     		viewPager.setAdapter(mAdapter);
     		actionBar.setHomeButtonEnabled(false);
     		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

     		// Adding Tabs
     		for (String tab_name : tabs) {
     			actionBar.addTab(actionBar.newTab().setText(tab_name)
     					.setTabListener(this));
     		}

     		/**
     		 * on swiping the viewpager make respective tab selected
     		 * */
     		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

     			@Override
     			public void onPageSelected(int position) {
     				// on changing the page
     				// make respected tab selected
     				actionBar.setSelectedNavigationItem(position);
     			}

     			@Override
     			public void onPageScrolled(int arg0, float arg1, int arg2) {
     			}

     			@Override
     			public void onPageScrollStateChanged(int arg0) {
     			}
     		});       
        
        return rootView;
    }
	
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	
}
