package com.example.dsouchon.miidvendorapp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.content.Intent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        TextView labelCurrentShift = (TextView)findViewById(R.id.labelCurrentShift);

        try {

            labelCurrentShift.setText("Shift No:" + Local.read(getApplicationContext(), "ShiftNo"));

        } catch (IOException e) {
            e.printStackTrace();
        }



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

        { labelStaffCode.setText("Current Staff ID:" + staffCode); }



        EnableDisableButtons();

        TextView lblEventName = (TextView)findViewById(R.id.lblEventName);
        lblEventName.setText("Event:" + Local.Get(getApplicationContext(), "EventName"));


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


        /* used to filip through views in view flipper */

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);

        next.setOnClickListener(this);
        previous.setOnClickListener(this);



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
        //disables child button
        Button buttonSetEventbtn = (Button)this.findViewById(R.id.GoToSetEvenBtn);
        buttonSetEventbtn.setEnabled(EnableButton("SetEvent"));


        Button buttonAddStaff = (Button)this.findViewById(R.id.buttonAddStaff);
        buttonAddStaff.setEnabled(EnableButton("AddStaff"));
        //disables child button
        Button buttonaddstaffbtn1 = (Button)this.findViewById(R.id.GoToAddStaffBtn);
        buttonaddstaffbtn1.setEnabled(EnableButton("AddStaff"));


        Button buttonStartShift = (Button)this.findViewById(R.id.buttonStartShift);
        buttonStartShift.setEnabled(EnableButton("StartShift"));
        //disables child button
        Button buttonStartShift2 = (Button)this.findViewById(R.id.GoToStartShiftBtn);
        buttonStartShift2.setEnabled(EnableButton("StartShift"));


        Button buttonTransact = (Button)this.findViewById(R.id.buttonTransact);
        buttonTransact.setEnabled(EnableButton("Tender"));
        //disables child button
        Button buttonTransact2 = (Button)this.findViewById(R.id.GoToStartSaleBtn);
        buttonTransact2.setEnabled(EnableButton("Tender"));


        Button buttonStaffLogin = (Button)this.findViewById(R.id.buttonStaffLogin);
        buttonStaffLogin.setEnabled(EnableButton("StaffLogin"));
        //disables child button
        Button buttonStaffLogin2 = (Button)this.findViewById(R.id.GoToStaffLoginBtn);
        buttonStaffLogin2.setEnabled(EnableButton("StaffLogin"));

        Button buttonStafflogoff = (Button)this.findViewById(R.id.stafflogoffbtn);
        buttonStafflogoff.setEnabled(EnableButton("StaffLoginOff"));
        //disables child button
        Button buttonStafflogoff2 = (Button)this.findViewById(R.id.stafflogoff);
        buttonStafflogoff2.setEnabled(EnableButton("StaffLoginOff"));



        Button buttonEndShift = (Button)this.findViewById(R.id.buttonEndShift);
        buttonEndShift.setEnabled(EnableButton("CloseShift"));
        //disables child button
        Button buttonEndShiftbtn = (Button)this.findViewById(R.id.GoToCloseShiftBtn);
        buttonEndShiftbtn.setEnabled(EnableButton("CloseShift"));


        Button buttonReprintSlip = (Button)this.findViewById(R.id.buttonReprintSlip);
        buttonReprintSlip.setEnabled(EnableButton("ReprintSlip"));


        Button buttonRefund = (Button)this.findViewById(R.id.buttonRefund);
        buttonRefund.setEnabled(true);//EnableButton("Refund"));



    }

    //links child button to parent flipper layout and contains animations
    public void GoToCloseShift(View view) {
        viewFlipper.setDisplayedChild(1);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.endshiftlayout)));

        //pop animation for layout
        LinearLayout Layoutpop1 = (LinearLayout)this.findViewById(R.id.endshiftlayout);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop1.startAnimation(expandIn);
    }

    public void GoToStaffLogin(View view) {
        viewFlipper.setDisplayedChild(2);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.staffloginlayout)));

        //pop animation for layout
        LinearLayout Layoutpop2 = (LinearLayout)this.findViewById(R.id.staffloginlayout);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop2.startAnimation(expandIn);
    }

    public void GoToStartSale(View view) {
        viewFlipper.setDisplayedChild(3);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.tenderlayout)));

        //pop animation for layout
        LinearLayout Layoutpop3 = (LinearLayout)this.findViewById(R.id.tenderlayout);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop3.startAnimation(expandIn);
    }

    public void GoToToAddStaff(View view) {
        viewFlipper.setDisplayedChild(5);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.staffaddlayout)));

        //pop animation for layout
        LinearLayout Layoutpop4 = (LinearLayout)this.findViewById(R.id.staffaddlayout);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop4.startAnimation(expandIn);
    }

    public void GoToStartShift(View view) {
        viewFlipper.setDisplayedChild(5);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.startshiftlayout)));

        //pop animation for layout
        LinearLayout Layoutpop5 = (LinearLayout)this.findViewById(R.id.startshiftlayout);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop5.startAnimation(expandIn);
    }

    public void GoToSetEvent(View view) {
        viewFlipper.setDisplayedChild(6);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.seteventlayout)));

        //pop animation for layout
        LinearLayout Layoutpop6 = (LinearLayout)this.findViewById(R.id.seteventlayout);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop6.startAnimation(expandIn);
    }



    public void stafflogouTNew(View view) {
        viewFlipper.setDisplayedChild(7);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.staffloginofflayout)));

        //pop animation for layout
        LinearLayout Layoutpop7 = (LinearLayout)this.findViewById(R.id.staffloginofflayout);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop7.startAnimation(expandIn);
    }


    public void Tagdetails(View view) {
        viewFlipper.setDisplayedChild(8);
        viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.TagDetailslayout)));

        //pop animation for layout
        LinearLayout Layoutpop8 = (LinearLayout)this.findViewById(R.id.TagDetailslayout);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.expand_in);
        Layoutpop8.startAnimation(expandIn);
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
                Toast.makeText(this, "Settings selected", Toast.LENGTH_LONG)
                        .show();
                break;
            // action with ID action_settings was selected
            // case R.id.action_staffLogoff:
            // Log off staff
            // Local.Set(getApplicationContext(), "StaffCode", "0");
            // Local.Set(getApplicationContext(), "StaffID", "0");
            // Local.Set(getApplicationContext(), "StaffLoggedIn","0");
            // Toast.makeText(this, "Staff logged off.", Toast.LENGTH_SHORT)
            //  .show();
            // EnableDisableButtons();
            //  break;

            case R.id.action_adminLogin:
                //Toast.makeText(this, "Login selected", Toast.LENGTH_SHORT)

                //.show();
                Intent intent1 = new Intent(MainActivity.this, Reset101.class );
                startActivity(intent1);
                break;

        }



        return true;

        //return super.onOptionsItemSelected(item);
    }



    public void stafflougoff(View view) {

        //Log off staff
        Local.Set(getApplicationContext(), "StaffCode", "0");
        Local.Set(getApplicationContext(), "StaffID", "0");
        Local.Set(getApplicationContext(), "StaffLoggedIn","0");

        Toast.makeText(this, "Staff logged off.", Toast.LENGTH_LONG)

                .show();

        EnableDisableButtons();

        TextView myTextView = (TextView) findViewById(R.id.labelStaffCode);
        myTextView.setText(null);

    }


    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(MainActivity.this, "To close app select your phones native menu button.", Toast.LENGTH_LONG).show();
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

            case "StaffLoginOff" : if(Local.isSet(getApplicationContext(), "VendorLoggedIn") && Local.isSet(getApplicationContext(), "EventSet") && Local.isSet(getApplicationContext(), "StaffAdded") && Local.isSet(getApplicationContext(), "StaffLoggedIn"))
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

    public void GotoTagDetail (View view) {

        Intent intent = new Intent(MainActivity.this, UserInfo.class);
        finish();

        startActivity(intent);
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
