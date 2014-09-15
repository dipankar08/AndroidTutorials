/***************************************************************************************************
 * Work Item 1 : Simple List View of items
 * KeyPoint:
 *  - Android ListView Lets you display Views in Vertical Scrollable List
    - In this example you will see list of movies displayed in listview
 *
 * ************************************************************************************************/
package com.dipankar.androidtutorials;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class List_Simplelist extends ListActivity {

    // 1 . Define a List
    String[] planets = new String[] { "Sun", "Mercury", "Venus", "Earth",
            "Mars", "Jupiter", "Saturn", "Uranus", "Neptune" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2. Define a Arry Adapter in the List..
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planets);
        //3 And Set it
        setListAdapter(aa);

    }
    // 4. Also add a Click Event.
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(List_Simplelist.this, "you clicked  " + planets[position],
                Toast.LENGTH_SHORT).show();
    }
}