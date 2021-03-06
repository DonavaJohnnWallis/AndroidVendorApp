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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    Button next;
    Button previous;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);






        //if no-one is logged in show vendor log in screen
        RegisterDevice();

        if(!(Local.isSet(getApplicationContext(), "VendorLoggedIn") || Local.isSet(getApplicationContext(), "StaffLoggedIn")))
        {
            if(Local.isSet(getApplicationContext(), "EventSet"))



            {

            }
            else {
                Intent intent = new Intent(MainActivity.this, VendorLogin1.class);
                finish();

                startActivity(intent);
            }

        }




        String appID = Installation.applicationId(getApplicationContext());
        TextView lblInstallationID = (TextView)findViewById(R.id.lblInstallationID);
        lblInstallationID.setText(appID);
        RegisterDevice();
        TextView labelStaffCode = (TextView)findViewById(R.id.labelStaffCode);


        String staffCode = Local.Get(getApplicationContext(), "StaffCode");
        if(staffCode.equals(""))

        { labelStaffCode.setText("Staff Not Logged In"); }

        else

        { labelStaffCode.setText("Staff Code:" + staffCode); }



        EnableDisableButtons();

        TextView lblEventName = (TextView)findViewById(R.id.lblEventName);
        lblEventName.setText("Event:" + Local.Get(getApplicationContext(), "EventName"));




        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);

        next.setOnClickListener(this);
        previous.setOnClickListener(this);


        /*
        String message = "";

        try {
            message = Local.read(getApplicationContext(), "LastScreenMessage");

            final AlertDialog ad = new AlertDialog.Builder(this).create();

            ad.setTitle("Alert");
            ad.setMessage(message);
            ad.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }

    @Override
    public void onClick(View v) {
        if ( v == next) {
            viewFlipper.showNext();

        }


        else if (v == previous) {
            viewFlipper.showPrevious();
        }
    }

    public void EnableDisableButtons()
    {

        Button buttonVendorLogin = (Button)this.findViewById(R.id.buttonVendorLogin);

        Boolean on = EnableButton("VendorLogin");
        buttonVendorLogin.setEnabled(on);

        Button buttonSetEvent = (Button)this.findViewById(R.id.buttonManageEvents);
        buttonSetEvent.setEnabled(EnableButton("SetEvent"));

        Button buttonAddStaff = (Button)this.findViewById(R.id.buttonAddStaff);
        buttonAddStaff.setEnabled(EnableButton("AddStaff"));

        Button buttonStartShift = (Button)this.findViewById(R.id.buttonStartShift);
        buttonStartShift.setEnabled(EnableButton("StartShift"));

        Button buttonReprintSlip = (Button)this.findViewById(R.id.buttonReprintSlip);
        buttonReprintSlip.setEnabled(EnableButton("ReprintSlip"));

        Button buttonRefund = (Button)this.findViewById(R.id.buttonRefund);
        buttonRefund.setEnabled(true);//EnableButton("Refund"));

        Button buttonTransact = (Button)this.findViewById(R.id.buttonTransact);
        buttonTransact.setEnabled(EnableButton("Tender"));

        Button buttonStaffLogin = (Button)this.findViewById(R.id.buttonStaffLogin);
        buttonStaffLogin.setEnabled(EnableButton("StaffLogin"));



        Button buttonEndShift = (Button)this.findViewById(R.id.buttonEndShift);
        buttonEndShift.setEnabled(EnableButton("CloseShift"));


    }

    public boolean GetButtonSetting ( String SettingName) {
        try {
            if (Local.read(getApplicationContext(), SettingName) == "") {
                return false;
            }
            else
            {
                return true;

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stafflogoff, menu);
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
            case R.id.action_staffLogoff:
                //Log off staff
                Local.Set(getApplicationContext(), "StaffCode", "0");
                Local.Set(getApplicationContext(), "StaffID", "0");
                Local.Set(getApplicationContext(), "StaffLoggedIn","0");

                Toast.makeText(this, "Staff logged off.", Toast.LENGTH_SHORT)

                                .show();

                EnableDisableButtons();

                break;
            case R.id.action_adminLogin:
                //Toast.makeText(this, "Login selected", Toast.LENGTH_SHORT)

                //.show();
                Intent intent1 = new Intent(MainActivity.this, Reset101.class );
                startActivity(intent1);
                break;
            case R.id.action_tutmenu:
                //Toast.makeText(this, "Login selected", Toast.LENGTH_SHORT)

                //.show();
                Intent intent = new Intent(MainActivity.this, Pop.class );
                startActivity(intent);
                break;
        }



        return true;

        //return super.onOptionsItemSelected(item);
    }



    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(MainActivity.this, "To close app select your phones native menu button.", Toast.LENGTH_SHORT).show();
    }


    public void RegisterDevice()
    {
        String appID = Installation.applicationId(getApplicationContext());

        final AlertDialog ad=new AlertDialog.Builder(this).create();

        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();


            String DeviceCode = appID;

            RegisterDevice0Parameters params = new RegisterDevice0Parameters(cs, DeviceCode);

            new CallSoapRegisterDevice0().execute(params);

        } catch (Exception ex) {
            //code causing app to hang on s4 galaxy
            //    ad.setTitle("Register Device Error!"); 
            // ad.setMessage(ex.toString());

        }

        //ad.show();




    }
    //Region Button Click Events
    //button click event
    public void SetEvent(View view) {
        Intent intent = new Intent(MainActivity.this, SetEvent2.class );
        finish();
        startActivity(intent);
    }

    public void AddStaff(View view) {
        Intent intent = new Intent(MainActivity.this, AddStaff.class );
        finish();
        startActivity(intent);
    }


    public void CloseShift(View view) {
        Intent intent = new Intent(MainActivity.this, CloseShift9.class );
        finish();
        startActivity(intent);
    }

    public void ReprintSlip(View view) {
        Intent intent = new Intent(MainActivity.this, ReprintSlip8.class );
        finish();
        startActivity(intent);
    }

    public void StaffLogin(View view) {
        Intent intent = new Intent(MainActivity.this, StaffLogin4.class );
        finish();
        startActivity(intent);
    }

    public void TenderAmount(View view) {
        Intent intent = new Intent(MainActivity.this, TenderAmount5.class );
        finish();
        startActivity(intent);
    }

    public void Refund(View view) {
        Intent intent = new Intent(MainActivity.this, TenderAmountRefund5.class );
        finish();
        startActivity(intent);
    }

    public void  VendorLogin(View view) {
        Intent intent = new Intent(MainActivity.this, VendorLogin1.class );
        finish();
        startActivity(intent);
    }



    public void  VendorLogoff(View view) {

        Local.Set(getApplicationContext(), "VendorLoggedIn", "0");
        Intent intent = new Intent(MainActivity.this, MainActivity.class );
        finish();
        startActivity(intent);
    }





    public void openCashier(View view) {
        Intent intent = new Intent(MainActivity.this, VendorLogin1.class );
        finish();
        startActivity(intent);
    }

    public void accessControl(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity21.class );
        finish();
        startActivity(intent);
    }

    public void  Reset(View view) {

        ResetLocalSettings();

        Intent intent = new Intent(MainActivity.this, MainActivity.class );
        finish();
        startActivity(intent);
    }

    public void ResetLocalSettings(){
        Local.Set(getApplicationContext(), "VendorLoggedIn", "0" );

        Local.Set(getApplicationContext(), "StaffAdded", "0" );
        Local.Set(getApplicationContext(), "ShiftStarted", "0" );
        Local.Set(getApplicationContext(), "StaffLoggedIn", "0" );
        Local.Set(getApplicationContext(), "SupervisorLoggedIn", "0" );

        Local.Set(getApplicationContext(), "EventID", "0");
        Local.Set(getApplicationContext(), "EventName", "");
        Local.Set(getApplicationContext(), "EventSet", "0");
        Local.Set(getApplicationContext(), "VendorID", "0");
        Local.Set(getApplicationContext(), "ShiftNo", "0");
        Local.Set(getApplicationContext(), "LoggedInStaff", "0");
        Local.Set(getApplicationContext(), "StaffCode", "0");
        Local.Set(getApplicationContext(), "StaffPin", "0");

    }


    public boolean EnableButton(String buttonName)
    {
        boolean ret= false;


        switch(buttonName)
        {
            case "VendorLogin" : if(!Local.isSet(getApplicationContext(), "VendorLoggedIn")){
                ret = true;}
                break;

            case "SetEvent" : if(Local.isSet(getApplicationContext(), "VendorLoggedIn") && !Local.isSet(getApplicationContext(), "EventSet"))
                ret = true;
                break;

            case "AddStaff" : if(Local.isSet(getApplicationContext(), "VendorLoggedIn") && Local.isSet(getApplicationContext(), "EventSet"))
                ret = true;
                break;

            case "StartShift" : if(Local.isSet(getApplicationContext(), "VendorLoggedIn") && Local.isSet(getApplicationContext(), "EventSet") && Local.isSet(getApplicationContext(), "StaffAdded") && !Local.isSet(getApplicationContext(), "ShiftStarted"))
                ret = true;
                break;

            case "CloseShift" : if(Local.isSet(getApplicationContext(), "VendorLoggedIn") && Local.isSet(getApplicationContext(), "EventSet") && Local.isSet(getApplicationContext(), "StaffAdded") && Local.isSet(getApplicationContext(), "ShiftStarted"))
                ret = true;
                break;

            case "Tender" : if(Local.isSet(getApplicationContext(), "EventSet") && Local.isSet(getApplicationContext(), "StaffAdded") && Local.isSet(getApplicationContext(), "StaffLoggedIn") && Local.isSet(getApplicationContext(), "ShiftStarted"))
                ret = true;
                break;

            case "ReprintSlip" : if(Local.isSet(getApplicationContext(), "EventSet") && Local.isSet(getApplicationContext(), "StaffAdded") && Local.isSet(getApplicationContext(), "StaffLoggedIn") && Local.isSet(getApplicationContext(), "ShiftStarted") && Local.isSet(getApplicationContext(), "VendorLoggedIn"))
                ret = true;
                break;

            case "Refund" : if(Local.isSet(getApplicationContext(), "EventSet") && Local.isSet(getApplicationContext(), "StaffAdded") && Local.isSet(getApplicationContext(), "StaffLoggedIn") && Local.isSet(getApplicationContext(), "ShiftStarted") && Local.isSet(getApplicationContext(), "VendorLoggedIn"))
                ret = true;
                break;

            case "SuperVisorLogin" :
                ret=true;
                break; // Supervisor Logged Off as soon as refund or reprint slip is done

            case "StaffLogin": if(Local.isSet(getApplicationContext(), "EventSet") && Local.isSet(getApplicationContext(), "StaffAdded") && Local.isSet(getApplicationContext(), "ShiftStarted") && (!Local.isSet(getApplicationContext(), "StaffLoggedIn")))
                ret = true;
                break;//When is staff logged out?

            default: break;



        }







        return ret;



    }
    //Webmethod functions start
    public class CallSoapRegisterDevice0 extends AsyncTask<RegisterDevice0Parameters, Void, String> {

        private Exception exception;

        //After the webmethod is called
        @Override
        protected String doInBackground(RegisterDevice0Parameters... params) {
            return params[0].sca.RegisterDevice0(params[0].DeviceCode);
        }

        //After the webmethod has run
        protected void onPostExecute(String result) {

            if(result != "")
            {


                String deviceID = result.toString();
                try {

                    Local.write(getApplicationContext(), "DeviceID", deviceID);
                }
                catch (IOException e) {

                    e.printStackTrace();
                }


            }
            else {
                //Do nothing

            }

        }





    }


    private static class RegisterDevice0Parameters {
        MySOAPCallActivity sca;
        String DeviceCode;



        RegisterDevice0Parameters(MySOAPCallActivity sca, String DeviceCode) {
            this.sca = sca;
            this.DeviceCode = DeviceCode;


        }
    }

    //Start shift functions

    //Button Click
    public void StartShift(View view) {

        String appID = Installation.applicationId(getApplicationContext());

        final AlertDialog ad=new AlertDialog.Builder(this).create();



        MySOAPCallActivity cs = new MySOAPCallActivity();
        if(Local.isSet(getApplicationContext(), "VendorLoggedIn")) {
            try {

                final GlobalClass globalVariable = (GlobalClass) getApplicationContext();


                EditText editVendorCode = (EditText) findViewById(R.id.editVendorCode);
                EditText editVendorPin = (EditText) findViewById(R.id.editVendorPin);

                //get values from controls and stored values
                Integer CurrentVendorID = Integer.parseInt(Local.Get(getApplicationContext(), "VendorID"));
                Integer CurrentDevicePin = Integer.parseInt(Local.Get(getApplicationContext(), "VendorPin"));
                String CurrentStaffCode = Local.Get(getApplicationContext(), "VendorCode");
                Integer EventID = Integer.parseInt(Local.Get(getApplicationContext(), "EventID"));
                String DeviceCode = appID;

                StartShift3Parameters params = new StartShift3Parameters(cs, CurrentVendorID, CurrentDevicePin, CurrentStaffCode, EventID, DeviceCode);

                new CallSoapStartShift3().execute(params);

            } catch (Exception ex) {
              //  ad.setTitle("Start Shift Error!");
          //      ad.setMessage(ex.toString());
            }
         //   ad.show();

        }

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

            EnableDisableButtons();

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






//Webmethod functions end
}
