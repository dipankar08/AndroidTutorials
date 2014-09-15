package com.dipankar.androidtutorials;

/**
 * Sample on btn Clcick.
 * ref : https://www.udemy.com/blog/android-alertdialog-examples/
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AnimatedDialogExample extends Activity {
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
            AlertDialog.Builder builder = new AlertDialog.Builder(AnimatedDialogExample.this);
           //builder.setMessage(" This is Msg");
            builder.setTitle("this is title..");
            AlertDialog dialog = builder.create();
            Dialog dialog1 = new Dialog(AnimatedDialogExample.this, R.style.DialogSlideAnim);
            //    Dialog dialog1 = new Dialog(AnimatedDialogExample.this, R.style.PauseDialog);
            dialog1.setTitle(" Hello I am Dipankar");
            dialog1.show();

         }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}