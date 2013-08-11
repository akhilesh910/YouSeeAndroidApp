package in.yousee.yousee;

import in.yousee.yousee.model.ProxyOpportunityItem;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class OpportunityListBuilder implements OnPostResponseRecievedListener
{
	private String TAG_FIRSTTIME = "firstTime";
	private String TAG_UPDATE = "update";

	
	protected HttpPost postRequest;

	private MainActivity activity;
	private String fileName = "volunteering_opportunities.php";

	public OpportunityListBuilder(ArrayList<FilterGroupInfo> filterGroupList, MainActivity activity)
	{
		this.activity = (MainActivity) activity;
		createRequest();
		addValuesToPost(filterGroupList);
	}

	public OpportunityListBuilder(Activity activity)
	{
		this.activity = (MainActivity) activity;
		createRequest();
		addValuesToPostFirstTime();
	}

	protected void createRequest()
	{
		postRequest = new HttpPost(NetworkConnectionHandler.DOMAIN + fileName);
	}

	protected void addValuesToPostFirstTime()
	{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair(TAG_FIRSTTIME, "true"));

		try
		{
			postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

	}

	protected void addValuesToPost(ArrayList<FilterGroupInfo> filterGroupList)
	{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		nameValuePairs.add(new BasicNameValuePair(TAG_UPDATE, "true"));
		Log.i("tag", "+hfksjdhfldhfjkghdfjkgdkjfhgjkdhfjgkhdfjkghjkdfhgkjdshfg");
		Iterator<FilterGroupInfo> it = filterGroupList.iterator();
		while (it.hasNext())
		{
			FilterGroupInfo group = it.next();
			
			// nameValuePairs.add(new
			// BasicNameValuePair(group.getName(), "true"));
			Iterator<FilterChildInfo> childIterator = group.getProductList().iterator();
			while (childIterator.hasNext())
			{
				FilterChildInfo child = childIterator.next();
				if (child.isChecked())
				{
					Log.i("tag", " "+group.getName()+": " + child.getName());
					nameValuePairs.add(new BasicNameValuePair(group.getName(), child.getName()));
				}
			}

		}

		try
		{
			postRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

	}

	public void execute()
	{
		NetworkConnectionHandler networkHandler = new NetworkConnectionHandler(activity.getApplicationContext());
		networkHandler.sendRequestInMultiThreadedMode(postRequest, this);
	}

	@Override
	public void onPostResponseRecieved(String result)
	{

		JSONObject json;
		ArrayList<ProxyOpportunityItem> proxyItemList = new ArrayList<ProxyOpportunityItem>();
		try
		{
			Log.i("tag", "JSONlist length ");
			json = new JSONObject(result);
			int resultCount = json.getInt("resultCount");
			String totalCount = json.getString("totalCount");

			JSONArray list = json.getJSONArray("list");
			Log.i("tag", "JSONlist length " + list.toString());
			for (int i = 0; i < list.length(); i++)
			{
				Log.i("tag", "" + i);
				proxyItemList.add(new ProxyOpportunityItem(list.getJSONObject(i)));
			}

		} catch (JSONException e)

		{
			Log.i("tag", "exception caught");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("tag", "item list length = " + proxyItemList.size());
		activity.createOpportunityListView(proxyItemList);

	}

}
