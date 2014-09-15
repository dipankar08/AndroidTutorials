/***************************************************************************************************
 * Work Item 1 : ListView-II: Customized ListView With Arry Adapter
 * KeyPoint:
 *  1. Simple Activity.
 *  2. Bind Activity to Layout activity_hello_world.xml
 *  3. Layout only content a text.
 *  4. Observer menufest file and how it is constracted.
 *
 * ************************************************************************************************/

package com.dipankar.androidtutorials;

/* adapter*/

import android.content.Context;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/* 1. Define Adapter*/
class Custom_view extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public Custom_view(Context context, String[] values) {
        super(context, R.layout.list_activity, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_activity, parent, false);
        TextView tv = (TextView) rowView.findViewById(R.id.label);
        ImageView iv = (ImageView) rowView.findViewById(R.id.logo);

        String item_value = values[position];
        if (item_value.equals("samsung")) {
            iv.setImageResource(R.drawable.ic_launcher);
            tv.setText(item_value);
        } else if (item_value.equals("lg")) {
            iv.setImageResource(R.drawable.ic_launcher);
            tv.setText(item_value);
        } else if (item_value.equals("htc")) {
            iv.setImageResource(R.drawable.ic_launcher);
            tv.setText(item_value);
        } else if (item_value.equals("moto")) {
            iv.setImageResource(R.drawable.ic_launcher);
            tv.setText(item_value);
        } else if (item_value.equals("nexus")) {
            iv.setImageResource(R.drawable.ic_launcher);
            tv.setText(item_value);
        }
        return rowView;
    }
}

/* activity */
public class LIST_CustomizedListView extends ListActivity {

    String[] mob = new String[] { "samsung", "lg", "htc", "moto", "nexus" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Custom_view cv = new Custom_view(this, mob);
        setListAdapter(cv);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // getting the value of clicked item
        String clicked_item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, clicked_item, Toast.LENGTH_SHORT).show();
    }

}
