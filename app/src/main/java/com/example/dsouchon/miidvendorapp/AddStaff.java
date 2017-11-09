package com.example.dsouchon.miidvendorapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AddStaff extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addstaff);
        TextView labelCurrentEvent = (TextView)this.findViewById(R.id.labelCurrentEvent);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        spinner = (Spinner)this.findViewById(R.id.spinnerStaff);
        GetStaff();
        try {
            String eventName = Local.read(getApplicationContext(), "EventName");
            labelCurrentEvent.setText(eventName);
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

                  //      .show();
                Intent intent1 = new Intent(AddStaff.this, MainActivity.class );
                startActivity(intent1);
                break;

            default:
                break;
        }

        return true;

        //return super.onOptionsItemSelected(item);
    }



    //Button Click
    public void AddStaff(View view) {

        String appID = Installation.applicationId(getApplicationContext());

        final AlertDialog ad=new AlertDialog.Builder(this).create();


        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            //get values from controls
            Integer VendorID = 0;
            String StaffCode = "";
            Integer StaffPin = 0;
            Integer EventID = 0;
            Integer DeviceID = 0;


            VendorID = Integer.parseInt(Local.read(getApplicationContext(), "VendorID"));
            EditText editStaffPin = (EditText)findViewById(R.id.editStaffPin);
            StaffPin =  Integer.parseInt(editStaffPin.getText().toString());
            EditText editStaffCode = (EditText)findViewById(R.id.editStaffCode);
            StaffCode = editStaffCode.getText().toString();
            EventID = Integer.parseInt(Local.read(getApplicationContext(), "EventID"));
            DeviceID = Integer.parseInt(Local.read(getApplicationContext(), "DeviceID"));

            AddStaff3aParameters params = new AddStaff3aParameters(cs, VendorID,  StaffCode, StaffPin, EventID, DeviceID, false);

            new CallSoapAddStaff3a().execute(params);

        } catch (Exception ex) {
          //  ad.setTitle("Add Staff Error!");
         //   ad.setMessage(ex.toString());
        }
      //  ad.show();



    }

    public void OverwritePin(View view) {

        String appID = Installation.applicationId(getApplicationContext());

        final AlertDialog ad=new AlertDialog.Builder(this).create();


        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            //get values from controls
            Integer VendorID = 0;
            String StaffCode = "";
            Integer StaffPin = 0;
            Integer EventID = 0;
            Integer DeviceID = 0;


            VendorID = Integer.parseInt(Local.read(getApplicationContext(), "VendorID"));
            EditText editStaffPin = (EditText)findViewById(R.id.editStaffPin);
            StaffPin =  Integer.parseInt(editStaffPin.getText().toString());
            EditText editStaffCode = (EditText)findViewById(R.id.editStaffCode);
            StaffCode = editStaffCode.getText().toString();
            EventID = Integer.parseInt(Local.read(getApplicationContext(), "EventID"));
            DeviceID = Integer.parseInt(Local.read(getApplicationContext(), "DeviceID"));

            AddStaff3aParameters params = new AddStaff3aParameters(cs, VendorID,  StaffCode, StaffPin, EventID, DeviceID, true);

            new CallSoapAddStaff3a().execute(params);

        } catch (Exception ex) {
            ad.setTitle("Add Staff Error!");
            ad.setMessage(ex.toString());
        }
        ad.show();



    }



//On screen load
    public void GetStaff() {


        final AlertDialog ad=new AlertDialog.Builder(this).create();


        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            //get values from controls
            Integer VendorID = 0;

            Integer EventID = 0;


            VendorID = Integer.parseInt(Local.read(getApplicationContext(), "VendorID"));

            EventID = Integer.parseInt(Local.read(getApplicationContext(), "EventID"));

            GetStaff3cParameters params = new GetStaff3cParameters(cs, VendorID,  EventID);

            new CallSoapGetStaff3c().execute(params);

        } catch (Exception ex) {
        //code causing app to hange on Galaxy s4
            //ad.setTitle("Get Staff Error!");     ad.setMessage(ex.toString());
        }
         //ad.show();



    }

//Button Click
    public void StartShift(View view) {

        Intent intent = new Intent(AddStaff.this, StartShift3.class );
        finish();
        startActivity(intent);


    }







//Webmethod functions end

//Webmethod functions start
    public class CallSoapAddStaff3a extends AsyncTask<AddStaff3aParameters, Void, String> {

        private Exception exception;

        //After the webmethod is called
        @Override
        protected String doInBackground(AddStaff3aParameters... params) {
            Local.Set(getApplicationContext(), "LatestStaffCode", params[0].StaffCode);
            Local.Set(getApplicationContext(), "LatestStaffPin", params[0].StaffPin.toString());
            return params[0].sca.AddStaff3a(params[0].VendorID, params[0].StaffCode, params[0].StaffPin, params[0].EventID, params[0].DeviceID, params[0].OverwritePin);
        }

        //After the webmethod has run
        protected void onPostExecute(String result) {

            if(!result.toLowerCase().toString().contains("failed") && !result.toLowerCase().toString().contains("already")) {

                ////////////////////////////////////////////EditText editStaffLoaded = (EditText)findViewById(R.id.editStaffLoaded);
                //editStaffLoaded.setText(result);

                String staffListArrayStr = result;
                //get events from file
                Local.Set(getApplicationContext(), "StaffList", result);
                Local.Set(getApplicationContext(), "StaffAdded", "1");
                Local.Set(getApplicationContext(), "VendorLoggedIn", "0");

                if(staffListArrayStr.contains("|")){

                    String[] RowData = staffListArrayStr.toString().split("\\|");
                    List<EventClass> spinnerArray = new ArrayList<EventClass>();
                    for(int x=0;x<RowData.length;x++)
                    {
                        if(RowData[x].toString().contains(";")) {
                            String[] StaffRecord = RowData[x].toString().split(";");
                             spinnerArray.add(new EventClass(Integer.parseInt(StaffRecord[0].toString()), StaffRecord[1].toString()));
                        }
                    }

                    // Step 2: Create and fill an ArrayAdapter with a bunch of "State" objects
                    ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(spinner.getContext() , android.R.layout.simple_spinner_item, spinnerArray);

                    // Step 3: Tell the spinner about our adapter

                    spinner.setAdapter(spinnerArrayAdapter);

                    String StaffCode = Local.Get(getApplicationContext(), "LatestStaffCode");
                    String StaffPin = Local.Get(getApplicationContext(), "LatestStaffPin");
                    Toast.makeText(AddStaff.this, "Staff Added: " + StaffCode+ " Pin: " + StaffPin, Toast.LENGTH_SHORT).show();
                }

            }
            else {
                //Do nothing
                Toast.makeText(AddStaff.this, result, Toast.LENGTH_SHORT).show();
            }
            if(result.toLowerCase().toString().contains("already"))
            {
                Button overwrite = (Button)findViewById(R.id.buttonOverwritePin);
                overwrite.setVisibility(View.VISIBLE);

            }

        }





    }


    private static class AddStaff3aParameters {
        MySOAPCallActivity sca;

        Integer VendorID;
        String StaffCode;

        Integer StaffPin;
        Integer EventID;
        Integer DeviceID;
        Boolean OverwritePin;



        AddStaff3aParameters(MySOAPCallActivity sca,   Integer VendorID, String StaffCode, Integer StaffPin, Integer EventID, Integer DeviceID, Boolean OverwritePin) {
            this.sca = sca;
            this.VendorID = VendorID;
            this.StaffCode = StaffCode;
            this.StaffPin = StaffPin;
            this.EventID = EventID;
            this.DeviceID = DeviceID;
            this.OverwritePin = OverwritePin;

        }
    }

//Webmethod functions end


    //Webmethod functions start
    public class CallSoapGetStaff3c extends AsyncTask<GetStaff3cParameters, Void, String> {

        private Exception exception;

        //After the webmethod is called
        @Override
        protected String doInBackground(GetStaff3cParameters... params) {
            return params[0].sca.GetStaff3c(params[0].VendorID, params[0].EventID);
        }

        //After the webmethod has run
        protected void onPostExecute(String result) {

            if(!result.toString().contains("Failed")) {

                ////////////////////////////////////////////EditText editStaffLoaded = (EditText)findViewById(R.id.editStaffLoaded);
                //editStaffLoaded.setText(result);

                String staffListArrayStr = result;
                //get events from file
                Local.Set(getApplicationContext(), "StaffList", result);
                Local.Set(getApplicationContext(), "StaffAdded", "1");



                if(staffListArrayStr.contains("|")){

                    String[] RowData = staffListArrayStr.toString().split("\\|");
                    List<EventClass> spinnerArray = new ArrayList<EventClass>();
                    for(int x=0;x<RowData.length;x++)
                    {
                        if(RowData[x].toString().contains(";")) {
                            String[] StaffRecord = RowData[x].toString().split(";");
                            spinnerArray.add(new EventClass(Integer.parseInt(StaffRecord[0].toString()), StaffRecord[1].toString()));
                        }
                    }

                    // Step 2: Create and fill an ArrayAdapter with a bunch of "State" objects
                    ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(spinner.getContext() , R.layout.spinnerwhite, spinnerArray);


                    // Step 3: Tell the spinner about our adapter

                    spinnerArrayAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
                    spinner.setAdapter(spinnerArrayAdapter);
                }

            }
            else {
                //Do nothing
                TextView vendorLoginResult = (TextView)findViewById(R.id.vendorLoginResult);

                vendorLoginResult.setText(result);
            }

        }





    }


    private static class GetStaff3cParameters {
        MySOAPCallActivity sca;

        Integer VendorID;
        Integer EventID;



        GetStaff3cParameters(MySOAPCallActivity sca,   Integer VendorID, Integer EventID) {
            this.sca = sca;
            this.VendorID = VendorID;
            this.EventID = EventID;

        }
    }

//Webmethod functions end




}
