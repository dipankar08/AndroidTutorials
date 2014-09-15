/***************************************************************************************************
 * Work Item 3 : OverView of Event Handaling here.
 * KeyPoint:
 *  1. handing event for a button
 *  2. Handaling Evnet for a TextBox.
 * 1. onClick
 * 2. onLongClick
 * 3.onFocusChange
 * 4. onKey
 * 5. onTouch
 * 6. onMenuItemClick
 *
 * ************************************************************************************************/
package com.dipankar.androidtutorials;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class EventOverView extends Activity {
    // 1. Define everything in Global...

    EditText txt;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_overview);

        /* Get the reference of each Element in the layout  */
        txt = (EditText) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.button);

        //OnClick..
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ makeToast("OnClick..."); }
        });

        //setOnLongClickListener.
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                makeToast("OnClick...");
                return true;
            }
        });

        //onFocusChange
        txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean gainFocus) {
                //onFocus
                if (gainFocus) { makeToast("On Focus...");
                }
                //onBlur
                else {
                    makeToast("On onBlur...");
                }
            }
        });
        //onKey() => play with keyUp/Down/ or Ky value
        txt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                makeToast("key Number :"+ keyCode);
                if ((event.getAction() == KeyEvent.ACTION_DOWN)) {
                    makeToast("On KeyDown ...");
                }
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    makeToast("You have pressed Baxck ...");
                }
                if ((event.getAction() == KeyEvent.ACTION_UP)) {
                    makeToast("On KeyUp ...");
                }
                return true;
            }
        });

        btn.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                {


                    if(event.getAction() == MotionEvent.ACTION_DOWN) {
                        makeToast("On Touch DOWN..."+ event.getX()+ event.getY());
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        makeToast("On Touch UP..."+ event.getX()+ event.getY());
                    }
                    return true;
                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void makeToast(String str){
        Log.d("debug" , str);
        Toast msg = Toast.makeText(getBaseContext(),str, Toast.LENGTH_LONG);
        msg.show();
    }

}
