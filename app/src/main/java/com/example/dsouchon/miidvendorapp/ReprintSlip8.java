package com.example.dsouchon.miidvendorapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by dsouchon on 11/26/2015.
 */
public class ReprintSlip8 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.reprintslip8);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(ReprintSlip8.this, "Select Home in Main Menu to go back.", Toast.LENGTH_SHORT).show();
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

                Intent intent1 = new Intent(ReprintSlip8.this, MainActivity.class );
                startActivity(intent1);
                break;

            default:
                break;
        }

        return true;

        //return super.onOptionsItemSelected(item);
    }




    public void ReprintSlip(View view) {



            final TextView txtAmount = (TextView)findViewById(R.id.textView5);

            try {
                txtAmount.setText(Local.read(getApplicationContext(), "PaymentResult"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            new PrintSlip().execute(txtAmount.getText().toString());


    }

    public void MainMenu(View view) {


        Intent intent = new Intent(ReprintSlip8.this, MainActivity.class );
        finish();
        startActivity(intent);


    }

    private  boolean checkSuperVisorCred(String VendorCode, String VendorPin)
    {

        String vendorCode = null;
        try {
            vendorCode = Local.read(getApplicationContext(), "VendorCode");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String vendorPin = null;
        try {
            vendorPin = Local.read(getApplicationContext(), "VendorPin");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(vendorCode.toString().equals(VendorCode.toString()) && vendorPin.toString().equals(VendorPin.toString()))
        {
            return true;

        }
        else {
            return false;
        }
    }

    private class PrintSlip extends AsyncTask<String, Void, String> {

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
                Socket sock = new Socket("192.168.103.253", 9100);
                PrintWriter oStream = new PrintWriter(sock.getOutputStream());
                oStream.println("");
                oStream.println(params[0].toString());
                oStream.println("\n\n\n");
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



            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView txt = (TextView) findViewById(R.id.textView5);
            txt.setText("Slip Printed."); // txt.setText(result);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
