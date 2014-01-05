package info.ideone.adapters;

import info.ideone.activity.Ideone;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;


import android.content.Context;
import android.text.format.DateFormat;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
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