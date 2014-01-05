package info.aea.activities;

import info.aea.snippets.R;
import info.aea.drawer.Fragment_Accounts;
import info.aea.drawer.Fragment_Java;
import info.aea.launch.LaunchActivity_NavDrawer;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class activity_fblogin extends Activity {

	String username;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fblogin);

		// start Facebook Login
		Session.openActiveSession(this, true, new Session.StatusCallback() {

			// callback when session changes state
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				if (session.isOpened()) {

					// make request to the /me API
					Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {


						// callback after Graph API response with user object
						@Override
						public void onCompleted(GraphUser user, Response response) {
							System.out.println("Value in user="+user);
							TextView welcome = null;
							if (user != null) {

								welcome = (TextView) findViewById(R.id.welcome);
								welcome.setText("Hello " + user.getName() + " !  \nYou are now connected to facebook \n" );
							}  // end of if
						




							Button button = (Button) findViewById(R.id.button1);
							button.setOnClickListener(new View.OnClickListener() {  	    	
								@Override
								public void onClick(View arg0) {
									System.out.println("go back to fb login...");
									Intent intent = new Intent(getApplicationContext(), LaunchActivity_NavDrawer.class);
									startActivity(intent);

								}
							});

						}
					});
				}
			}
		});
	}



	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}




}