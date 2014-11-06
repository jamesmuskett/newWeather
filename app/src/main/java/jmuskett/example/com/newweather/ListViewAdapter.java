package jmuskett.example.com.newweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by jmuskett on 04/11/2014.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    City[] data;
    private static LayoutInflater inflater = null;

    public ListViewAdapter(Context context, City[] data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.itemlist_row, null);
        TextView text = (TextView) vi.findViewById(R.id.itemListTextView);
        text.setText(data[position].getName());
        return vi;
    }
}
