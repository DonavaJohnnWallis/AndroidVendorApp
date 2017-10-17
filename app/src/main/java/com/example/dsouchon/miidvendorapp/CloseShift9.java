package com.example.dsouchon.miidvendorapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dsouchon on 11/26/2015.
 */
public class CloseShift9  extends Activity {

    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(CloseShift9.this, "Select Home in Main Menu to go back.", Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closeshift9);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }






    //Button Click
    public void CloseShift(View view) {

        String appID = Installation.applicationId(getApplicationContext());

        final AlertDialog ad=new AlertDialog.Builder(this).create();



        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            Integer VendorID = Integer.parseInt(Local.read(getApplicationContext(), "VendorID"));
            Integer EventID= Integer.parseInt(Local.read(getApplicationContext(), "EventID"));
            String DeviceCode=appID;
            Integer ShiftNo = Integer.parseInt(Local.read(getApplicationContext(), "ShiftNo"));
            Integer StaffID=Integer.parseInt(Local.read(getApplicationContext(), "StaffID"));
            String VendorCode= Local.Get(getApplicationContext(),"VendorCode");
            Integer VendorPin = Integer.parseInt(Local.Get(getApplicationContext(), "VendorPin"));

            CloseShift9Parameters params = new CloseShift9Parameters(cs, VendorPin, VendorCode, VendorID, EventID, DeviceCode,ShiftNo, StaffID);

            new CallSoapCloseShift9().execute(params);

        } catch (Exception ex) {
            //code causing app to hang on s4
            //    ad.setTitle("Close Shift Error!"); 
            // ad.setMessage(ex.toString());
        }

        //ad.show();




    }



    //Webmethod functions start
    public class CallSoapCloseShift9 extends AsyncTask<CloseShift9Parameters, Void, String> {

        private Exception exception;

        //After the webmethod is called
        @Override
        protected String doInBackground(CloseShift9Parameters... params) {
            return params[0].sca.CloseShift9(params[0].VendorPin, params[0].VendorCode, params[0].VendorID, params[0].EventID, params[0].DeviceCode, params[0].ShiftNo, params[0].StaffID);
        }

        //After the webmethod has run
        protected void onPostExecute(String result) {

            final AlertDialog ad=new AlertDialog.Builder(getApplicationContext()).create();
            try {

                if(result.toLowerCase().contains("|")) {


                    Local.write(getApplicationContext(), "ShiftNo", "0");
                    ad.setTitle("Close Shift result");
                    ad.setMessage(result);

                    Local.write(getApplicationContext(), "LastScreenMessage", result);
                    final TextView lblResult = (TextView)findViewById(R.id.lblResult);
                    lblResult.setText("Shift Closed Successfully. See printer for EOD report.");
                    final TextView printerIP = (TextView)findViewById(R.id.txtPrinterIP);

                    //new PrintEODReport().execute(result.toString(), printerIP.getText().toString());

                    Local.Set(getApplicationContext(), "VendorLoggedIn", "0");
                    Local.Set(getApplicationContext(), "EventSet", "0");
                    Local.Set(getApplicationContext(), "EventList", "0");
                    Local.Set(getApplicationContext(), "EventID", "0");
                    Local.Set(getApplicationContext(), "StaffLoggedIn", "0");
                    Local.Set(getApplicationContext(), "StaffID", "0");
                    Local.Set(getApplicationContext(), "VendorID", "0");
                    Local.Set(getApplicationContext(), "ShiftStarted", "0");

                }
                else
                {
                    final TextView lblResult = (TextView)findViewById(R.id.lblResult);
                    lblResult.setText(result);

                }
            } catch (IOException e) {
                e.printStackTrace();
                ad.setTitle("Close Shift result");
                ad.setMessage(result + ": " + e.getMessage());
            }





        }





    }


    private static class CloseShift9Parameters {
        MySOAPCallActivity sca;

        Integer VendorPin;
        String VendorCode;
        Integer VendorID;
        Integer EventID;
        String DeviceCode;
        Integer ShiftNo;
        Integer StaffID;


        CloseShift9Parameters(MySOAPCallActivity sca,   Integer VendorPin, String VendorCode, Integer VendorID, Integer EventID, String DeviceCode, Integer ShiftNo, Integer StaffID) {
            this.sca = sca;
            this.VendorPin = VendorPin;
            this.VendorCode = VendorCode;
            this.VendorID = VendorID;
            this.EventID = EventID;
            this.DeviceCode = DeviceCode;
            this.ShiftNo = ShiftNo;
            this.StaffID = StaffID;

        }
    }

//Webmethod functions end

    public void MainMenu(View view) {


        Intent intent = new Intent(CloseShift9.this, MainActivity.class );
        finish();
        startActivity(intent);


    }


    private class PrintEODReport extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }

            try
            {
                //"192.168.103.253"
                Socket sock = new Socket(params[1].toString(), 9100);
                PrintWriter oStream = new PrintWriter(sock.getOutputStream());
                oStream.println("MiiD POS EOD Report");
                oStream.println("\n\n");

                String[] reportRow = params[0].toString().split(";");

                for(int x=0;x<reportRow.length;x++)
                {

                    String reportLine = reportRow[x];
                    reportLine = reportLine.replace("|", "   ");
                    oStream.println(reportLine);
                    oStream.println();

                }

                oStream.println("\n\n");
                Date d = new Date();
                oStream.println("Date: " + d.toString());
                oStream.close();
                sock.close();
            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }



            return "EOD Report Printed Successfully";
        }

        @Override
        protected void onPostExecute(String result) {

            final TextView txtAmount = (TextView)findViewById(R.id.lblResult);
            txtAmount.setText(result);

            ResetLocalSettings();
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}

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


    }




}
