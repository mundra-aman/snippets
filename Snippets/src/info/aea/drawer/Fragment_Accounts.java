package info.aea.drawer;


import info.aea.activities.activity_fblogin;
import info.aea.snippets.R;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment_Accounts extends Fragment  {



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_accounts,
				container, false);


System.out.println("starting activity.....");

		Button button = (Button) view.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("starting activity...");
				Intent intent = new Intent(getActivity(), activity_fblogin.class);
				getActivity().startActivity(intent);
				
			}
						
			
			
		});


		return view;
	}}