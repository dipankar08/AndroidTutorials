/***************************************************************************************************
 * Work Item 1 : Experimenting all Control Input
 * KeyPoint:
 *  1. Adding a Control in layout
 *  2. Bind the control in Activity
 *  3. Geeting and setting data from activity to Layout and vice versa.
 *  - The below Input Control is tested..
 *
 *
 * ************************************************************************************************/
package com.dipankar.androidtutorials;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class ActivityInputControl extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // 1. Define everything in Global...
    TextView txtView;
    EditText eText;
    EditText pText;
    private Button btn;
    ImageButton imgButton;
    AutoCompleteTextView autocompletetextview;
    ToggleButton toggleBtn1;

    RadioGroup radioGroup;
    private RadioButton radioBtn1;
    private RadioButton radioBtn2;

    Spinner osversions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_control);

        /* Get the reference of each Element in the layout         */
        txtView = (TextView) findViewById(R.id.text_id);txtView.setOnClickListener(this);
        eText = (EditText) findViewById(R.id.edittext); eText.setOnClickListener(this);
        pText = (EditText) findViewById(R.id.editpassword); pText.setOnClickListener(this);
        btn = (Button) findViewById(R.id.button); btn.setOnClickListener(this);
        imgButton = (ImageButton) findViewById(R.id.imageButton1); imgButton.setOnClickListener(this);

        String[] arr = { "MS SQL SERVER", "MySQL", "Oracle" };
        autocompletetextview = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1); autocompletetextview.setOnClickListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.select_dialog_item, arr);
        autocompletetextview.setThreshold(1);
        autocompletetextview.setAdapter(adapter);


        toggleBtn1 = (ToggleButton) findViewById(R.id.toggleButton1); toggleBtn1.setOnClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1); radioGroup.setOnClickListener(this);
        radioBtn1 = (RadioButton) findViewById(R.id.radioGroupButton0); radioBtn1.setOnClickListener(this);
        radioBtn2 = (RadioButton) findViewById(R.id.radioGroupButton1); radioBtn2.setOnClickListener(this);

        osversions = (Spinner) findViewById(R.id.osversions);
        String[] state= {"Andra Pradesh","Arunachal Pradesh","Assam","Bihar","Haryana","Himachal Pradesh", "Jammu and Kashmir", "Jharkhand","Karnataka", "Kerala","Tamil Nadu"};
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, state);
        osversions.setAdapter(adapter_state);
        osversions.setOnItemSelectedListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hello_world, menu);
        return true;
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

    // depending
    @Override
    public void onClick(View view) {
        Log.d("debug" , "hello");
        switch(view.getId()){
            case R.id.text_id:
                makeToast(txtView.getText().toString());
                break;
            case R.id.edittext:
                makeToast(eText.getText().toString());
                break;
            case R.id.editpassword:
                makeToast(pText.getText().toString());
                break;
            case R.id.button:
                makeToast(btn.getText().toString());
                break;
            case R.id.imageButton1:
                makeToast(imgButton.getTouchDelegate().toString());
                break;
            case R.id.autoCompleteTextView1:
                makeToast(autocompletetextview.getText().toString());
                break;
            case R.id.toggleButton1:
                makeToast(toggleBtn1.getText().toString());
                break;
            case R.id.radioGroup1:
                makeToast(String.valueOf(radioGroup.getCheckedRadioButtonId() == R.id.radioGroupButton0 ));
                break;

        }

    }
    void makeToast(String str){
        Log.d("debug" , "hello");
        Toast msg = Toast.makeText(getBaseContext(),str, Toast.LENGTH_LONG);
        msg.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id){

        osversions.setSelection(position);
        makeToast((String) osversions.getSelectedItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
