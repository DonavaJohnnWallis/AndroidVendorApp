package com.example.dsouchon.miidvendorapp;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class StartShift3 extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startshift3);
        TextView labelCurrentEvent = (TextView)this.findViewById(R.id.labelCurrentEvent);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        String eventName = Local.Get(getApplicationContext(), "EventName");
       labelCurrentEvent.setText(eventName);


    }


    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(StartShift3.this, "Select Home in Main Menu to go back.", Toast.LENGTH_SHORT).show();
    }






    //Button Click
    public void StartShift(View view) {

        String appID = Installation.applicationId(getApplicationContext());

        final AlertDialog ad=new AlertDialog.Builder(this).create();



        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            EditText editVendorCode = (EditText)findViewById(R.id.editVendorCode);
            EditText editVendorPin = (EditText)findViewById(R.id.editVendorPin);

            //get values from controls
            Integer CurrentVendorID = Integer.parseInt(Local.Get(getApplicationContext(), "VendorID"));
            Integer CurrentDevicePin = Integer.parseInt(editVendorPin.getText().toString());
            String CurrentStaffCode = editVendorCode.getText().toString();
            Integer EventID = Integer.parseInt(Local.Get(getApplicationContext(), "EventID"));
            String DeviceCode = appID;

            StartShift3Parameters params = new StartShift3Parameters(cs, CurrentVendorID, CurrentDevicePin, CurrentStaffCode, EventID, DeviceCode);

            new CallSoapStartShift3().execute(params);

        } catch (Exception ex) {
            ad.setTitle("Start Shift Error!");
            ad.setMessage(ex.toString());
        }
        ad.show();



    }

    //Button Click
    public void MainMenu(View view) {

        Intent intent = new Intent(StartShift3.this, MainActivity.class );
        finish();
        startActivity(intent);


    }

//Webmethod functions start
    public class CallSoapStartShift3 extends AsyncTask<StartShift3Parameters, Void, String> {

        private Exception exception;


        @Override
        protected String doInBackground(StartShift3Parameters... params) {
            return params[0].sca.StartShift3b(params[0].CurrentVendorID, params[0].CurrentDevicePin, params[0].CurrentStaffCode, params[0].EventID, params[0].DeviceCode);
        }

        protected void onPostExecute(String result) {

                Local.Set(getApplicationContext(), "ShiftNo", result);
                Local.Set(getApplicationContext(), "ShiftStarted", result);

            Local.Set(getApplicationContext(), "VendorLoggedIn", "0");

                Intent intent = new Intent(StartShift3.this, MainActivity.class );
            finish();
            startActivity(intent);



        }





    }


    private static class StartShift3Parameters {
        MySOAPCallActivity sca;

        Integer CurrentVendorID;
        Integer CurrentDevicePin;
        String CurrentStaffCode;
        Integer EventID;
        String DeviceCode;


        StartShift3Parameters(MySOAPCallActivity sca,   Integer CurrentVendorID,Integer CurrentDevicePin,String CurrentStaffCode,Integer EventID, String DeviceCode) {
            this.sca = sca;
            this.CurrentVendorID = CurrentVendorID;
            this.CurrentDevicePin = CurrentDevicePin;
            this.CurrentStaffCode = CurrentStaffCode;
            this.EventID = EventID;
            this.DeviceCode = DeviceCode;

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_vendorLogin:
                //Toast.makeText(this, "Login selected", Toast.LENGTH_SHORT)

                //      .show();
                Intent intent1 = new Intent(StartShift3.this, MainActivity.class );
                startActivity(intent1);
                break;

            default:
                break;
        }

        return true;

        //return super.onOptionsItemSelected(item);
    }



}
