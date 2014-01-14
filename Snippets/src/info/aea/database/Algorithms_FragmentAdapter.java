package info.aea.database;

import info.aea.snippets.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Algorithms_FragmentAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] AlgoID;
	private final String[] AlgoTitle;
	

		public Algorithms_FragmentAdapter(Context context, String[] algoid, String[] algotitle) {
		super(context, R.layout.list_items, algoid);
	
		this.context = context;
		this.AlgoID = algoid;
		this.AlgoTitle = algotitle;
		
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.list_items, parent, false);
		
		TextView textView = (TextView) rowView.findViewById(R.id.tvPat);
		
		String test=( AlgoTitle[position] );
		textView.setText(test);
		
		System.out.println("list values ------->> " + AlgoID[position] + AlgoTitle[position] );
		return rowView;
	}

}