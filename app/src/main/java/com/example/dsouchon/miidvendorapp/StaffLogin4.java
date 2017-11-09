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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class StaffLogin4 extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(StaffLogin4.this, "Select Home in Main Menu to go back.", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stafflogin4);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        TextView labelCurrentEvent = (TextView)findViewById(R.id.labelCurrentEvent);
       TextView labelCurrentShift = (TextView)findViewById(R.id.labelCurrentShift);

        try {
            labelCurrentEvent.setText("Event:" + Local.read(getApplicationContext(), "EventName"));
            labelCurrentShift.setText("Shift No:" + Local.read(getApplicationContext(), "ShiftNo"));
        } catch (IOException e) {
            e.printStackTrace();
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
                //.show();
                Intent intent1 = new Intent(StaffLogin4.this, MainActivity.class );
                startActivity(intent1);
                break;

            default:
                break;
        }

        return true;

        //return super.onOptionsItemSelected(item);
    }





    public void StaffLogin(View view) {

        String appID = Installation.applicationId(getApplicationContext());

        final AlertDialog ad=new AlertDialog.Builder(this).create();



        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            //get values from controls
            String DeviceCode = appID;
            Integer VendorID = Integer.parseInt(Local.read(getApplicationContext(), "VendorID"));
            Integer EventID  = Integer.parseInt(Local.read(getApplicationContext(), "EventID"));
            EditText editStaffCode = (EditText)findViewById(R.id.editStaffCode);
            String StaffCode= editStaffCode.getText().toString();
            EditText editStaffPin = (EditText)findViewById(R.id.editStaffPin);
            Integer StaffPin = Integer.parseInt(editStaffPin.getText().toString());
            Integer ShiftNo = Integer.parseInt(Local.read(getApplicationContext(), "ShiftNo"));

            StaffLogin4Parameters params = new StaffLogin4Parameters(cs, DeviceCode, VendorID, EventID, StaffCode, StaffPin, ShiftNo);

            new CallSoapStaffLogin4().execute(params);

        } catch (Exception ex) {
           // ad.setTitle("Staff Login Error!");
         //   ad.setMessage(ex.toString());
        }
        // ad.show();


    }



    //Webmethod functions start
    public class CallSoapStaffLogin4 extends AsyncTask<StaffLogin4Parameters, Void, String> {

        private Exception exception;

        //After the webmethod is called
        @Override
        protected String doInBackground(StaffLogin4Parameters... params) {
            return params[0].sca.StaffLogin4(params[0].DeviceCode, params[0].VendorID, params[0].EventID,  params[0].StaffCode, params[0].StaffPin, params[0].ShiftNo);
        }

        //After the webmethod has run
        protected void onPostExecute(String result) {

            if(!result.toString().toLowerCase().contains("fail")) {

                String[] value_split = result.split("\\:");


                try {
                    Local.write(getApplicationContext(), "StaffID", value_split[0].toString());
                    Local.write(getApplicationContext(), "StaffCode", value_split[1].toString());
                    Local.Set(getApplicationContext(), "StaffLoggedIn",value_split[0].toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(StaffLogin4.this, MainActivity.class );
                finish();
                startActivity(intent);

            }
            else {
                //Do nothing
                //TextView vendorLoginResult = (TextView)findViewById(R.id.vendorLoginResult);
                Toast.makeText(StaffLogin4.this, result, Toast.LENGTH_SHORT)

                        .show();
                //vendorLoginResult.setText(result);
            }

        }





    }


    private static class StaffLogin4Parameters {
        MySOAPCallActivity sca;
        String DeviceCode;
        Integer VendorID;
        Integer EventID;
        String StaffCode;
        Integer StaffPin;
        Integer ShiftNo;




        StaffLogin4Parameters(MySOAPCallActivity sca, String DeviceCode, Integer VendorID, Integer EventID, String StaffCode, Integer StaffPin, Integer ShiftNo) {
            this.sca = sca;
            this.DeviceCode = DeviceCode;
            this.VendorID = VendorID;
            this.EventID = EventID;
            this.StaffCode = StaffCode;
            this.StaffPin = StaffPin;
            this.ShiftNo = ShiftNo;

        }
    }

//Webmethod functions end







}
