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

    private City[] data;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, City[] data) {
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.itemlist_row, null);
        TextView text = (TextView) vi.findViewById(R.id.itemListTextView);
        text.setText(data[position].getName());
        return vi;
    }
}
