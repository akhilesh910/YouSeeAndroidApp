package in.yousee.yousee;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OpportunityListAdapter extends ArrayAdapter<String>
{

	private final Context context;
	private final String[] values;
	// private final ArrayList<Integer> imageIdList;

	HashMap<String, Integer> mIdMap;

	public OpportunityListAdapter(Context context, String[] values)
	{
		super(context, R.layout.opportunity_list_item, values);
		this.context = context;
		this.values = values;
		// this.imageIdList = imageIdList;
		// Log.i("tag","item "+imageIdList);

		mIdMap = new HashMap<String, Integer>();

		for (int i = 0; i < values.length; ++i)
		{
			mIdMap.put(values[i], i);
		}

	}

	@Override
	public long getItemId(int position)
	{
		String item = getItem(position);
		return mIdMap.get(item);
		// return super.getItemId(position);
	}

	@Override
	public int getCount()
	{

		// TODO Auto-generated method stub
		Log.i("tag", "item Length=" + values.length);
		return values.length;
	}

	static int i = 0;

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// Log.i("tag","getview");

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.opportunity_list_item, parent, false);
		rowView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{

				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN)
				{
					v.setBackgroundColor(Color.parseColor("#DCDCDC"));

				} else if (event.getAction() == android.view.MotionEvent.ACTION_CANCEL)
				{
					v.setBackgroundColor(Color.parseColor("#FFFFFF"));
					Log.d("tag", "Touch up");
				} else if (event.getAction() == android.view.MotionEvent.ACTION_UP)
				{
					v.setBackgroundColor(Color.parseColor("#FFFFFF"));
					Log.d("tag", "Touch up");
				}
				return true; 
			}
		});

		TextView textView = (TextView) rowView.findViewById(R.id.opportunityTitle);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.opportunityCatagoryIcon);
		textView.setText(values[position]);
		switch (position % 3)
			{
			case 0:
				imageView.setBackgroundResource(R.drawable.ic_environment);

				break;
			case 1:
				imageView.setBackgroundResource(R.drawable.ic_health);

				break;
			case 2:
				imageView.setBackgroundResource(R.drawable.ic_education);

				break;
			default:
				break;
			}
		// Log.i("tag", "item " + (position) + "   " +
		// imageIdList.get(position));
		// imageView.setBackgroundResource(imageIdList.get(position));
		// Log.i("tag","endgetview");
		return rowView;
	}

	@Override
	public String getItem(int position)
	{
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

}
