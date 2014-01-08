package info.ideone.adapters;

import info.ideone.activity.Ideone;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class AdapterLanguages extends ArrayAdapter<String> {
    
	public AdapterLanguages(Context context, int ideone_lang) {
		super(context, android.R.layout.simple_spinner_item);
		
		Ideone ideone = new Ideone(context);
		
        try{
	        for( String lang : ideone.getLangs() ){
	        	this.add(lang);
	        }
        } catch(Exception e){
        	Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }
	}
}