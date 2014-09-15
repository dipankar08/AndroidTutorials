package com.dipankar.androidtutorials;

/**
 * Sample on btn Clcick.
 * ref : https://www.udemy.com/blog/android-alertdialog-examples/
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DialogExample extends Activity {
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.btn_click);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.MyButton);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                Log.d("L:og", "Do Something Here...");
           //Dialog 1
            AlertDialog.Builder builder = new AlertDialog.Builder(DialogExample.this);
           //builder.setMessage(" This is Msg");
            builder.setTitle("this is title..");

//>>>>>>>>>>>> Single Select <<<<<<<<<<<<<<<<<<<<<<<<
                /*
            final CharSequence[] items = {"Red", "Green", "Blue"};
            builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int item) {
                Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
            }});
*/
// >>>>>>>>>>>>  Multi selct <<<<<<<<<<<<<<<<<<<<<<<<<<
            // Note : I don't know why but setMessage and setMultiChoiceItems cannot work together.
            final CharSequence[] items1={".NET","J2EE","PHP"};
            final boolean[] itemsChecked = new boolean[items1.length];
            builder.setMultiChoiceItems(items1, new boolean[]{false,false,false}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        itemsChecked[which]=isChecked;
                    }
                });
// >>>>>>>>>>>>  Ok Btn <<<<<<<<<<<<<<<<<<<<<<<<<<
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedTech="Selected Tech - ";
                        for (int i = 0; i < items1.length; i++) {
                            if (itemsChecked[i]) {

                                selectedTech=selectedTech+items1[i]+" ";
                                itemsChecked[i]=false;
                            }
                        }
                        TextView tv=(TextView)findViewById(R.id.text);
                        tv.setText(selectedTech);
                    }
                });
// >>>>>>>>>>>>  Cancel Btn <<<<<<<<<<<<<<<<<<<<<<<<<<
                builder.setNeutralButton("Cancel",new DialogInterface.OnClickListener(){
                            public void onClick (DialogInterface dialog,int which){
                                Toast.makeText(getApplicationContext(), "You clicked on Cancel",
                                        Toast.LENGTH_SHORT).show();
                            }
                });
// >>>>>>>>>>>>  No  Btn <<<<<<<<<<<<<<<<<<<<<<<<<<
                builder.setNegativeButton("NO",new DialogInterface.OnClickListener(){
                    public void onClick (DialogInterface dialog,int which){
                        Toast.makeText(getApplicationContext(), "You clicked on No",
                                Toast.LENGTH_SHORT).show();
                    }
                });

            AlertDialog dialog = builder.create();
            dialog.show();

         }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}