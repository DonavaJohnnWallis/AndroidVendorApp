package com.example.dsouchon.miidvendorapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * Created by dsouchon on 11/27/2015.
 */
public class PaymentResultRefund7 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        try {
            String result = "";
            try {
                setContentView(R.layout.paymentresultrefund7);
                result = Local.read(this.getApplicationContext(), "PaymentResult");
                final TextView lblPaymentResult = (TextView) findViewById(R.id.lblResult);
                lblPaymentResult.setText(result.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }


    }

    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(PaymentResultRefund7.this, "There's no going back now! Use ... in menu bar or available buttons", Toast.LENGTH_SHORT).show();
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

                //        .show();
                Intent intent1 = new Intent(PaymentResultRefund7.this, MainActivity.class );
                startActivity(intent1);
                break;

            default:
                break;
        }

        return true;

        //return super.onOptionsItemSelected(item);
    }





    public void print(View view) {

                final TextView txtAmount = (TextView)findViewById(R.id.lblResult);

                final TextView printerIP = (TextView)findViewById(R.id.txtPrinterIP);

                new PrintSlipRefund().execute(txtAmount.getText().toString(), printerIP.getText().toString());

        }

        public void ReprintSlip(View view) {


                Intent intent = new Intent(PaymentResultRefund7.this, ReprintSlip8.class );
            finish();
            startActivity(intent);


        }

    public void MainMenu(View view) {


        Intent intent = new Intent(PaymentResultRefund7.this, MainActivity.class );
        finish();
        startActivity(intent);


    }

        private class PrintSlipRefund extends AsyncTask<String, Void, String> {

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
                                oStream.println("MiiD POS Refund");
                                oStream.println("\n\n\n");
                                oStream.println(params[0].toString());
                                oStream.println("\n\n\n");
                                Date d = new Date();
                                oStream.println("Date:" + d.toString());
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



                        return "Refund Printed Successfully";
                }

                @Override
                protected void onPostExecute(String result) {

                        final TextView txtAmount = (TextView)findViewById(R.id.lblResult);
                        txtAmount.setText(result);
                        // might want to change "executed" for the returned string passed
                        // into onPostExecute() but that is upto you
                }

                @Override
                protected void onPreExecute() {}

                @Override
                protected void onProgressUpdate(Void... values) {}
        }




//Webmethod functions end






}
