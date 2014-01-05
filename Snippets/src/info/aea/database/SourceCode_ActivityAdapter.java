package info.aea.database;

import info.aea.snippets.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SourceCode_ActivityAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] CodeID;
	private final String[] CodeLang;
	private final String[] CodeTitle;
	private final String[] CodeSource;
	private final String[] CodeOutput;


		public SourceCode_ActivityAdapter(Context context, String[] codeid, String[] codelang, String[] codetitle, String[] codesource, String[] codeoutput) {
		super(context, R.layout.list_items, codeid);
	
		this.context = context;
		this.CodeID = codeid;
		this.CodeLang = codelang;
		this.CodeTitle = codetitle;
		this.CodeSource = codesource;
		this.CodeOutput = codeoutput;
		
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_items, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.tvPat);
		/*
		String test=(" code ID: "+ CodeID[position]+ "\n code Language: "+ CodeLang[position]+ "\n Code Title: "+
					CodeTitle[position]+ "\n code Source: "+ CodeSource[position]+ "\n code output: "+ CodeOutput[position]);
		textView.setText(test);
		*/
		
		
		String test=(" code Source: "+ CodeSource[position]);
	textView.setText(test);
	
		System.out.println("list values ------->> " + CodeID[position] + CodeLang[position] + CodeOutput[position] + CodeSource[position] + CodeTitle[position] );
		return rowView;
	}

}