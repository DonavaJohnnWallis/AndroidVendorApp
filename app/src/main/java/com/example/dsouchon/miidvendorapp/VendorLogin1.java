package com.example.dsouchon.miidvendorapp;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


public class VendorLogin1 extends AppCompatActivity {

    EditText textmsg;
    static final int READ_BLOCK_SIZE = 100;



    public void browser1(View view){

        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.miid.co.zw/EventOrganisers/Register"));
        startActivity(browserIntent);
    }



    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(VendorLogin1.this, "To close app select your phones native menu button. To reset app click Admin options in Main Menu.", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendorlogin1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        //this llows the tutorial to only come up once when app has started
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity

            startActivity(new Intent(VendorLogin1.this, Pop.class));



        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();








        Button tutbtn =(Button) findViewById(R.id.tutorialbtn);
        tutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendorLogin1.this,Pop.class));
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
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
                //Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                //        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_adminLogin:
                //Toast.makeText(this, "Login selected", Toast.LENGTH_SHORT)

                //.show();
                Intent intent1 = new Intent(VendorLogin1.this, Reset101.class );
                startActivity(intent1);
                break;

            case R.id.action_register:
                //Toast.makeText(this, "Login selected", Toast.LENGTH_SHORT)

                //.show();
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.miid.co.zw/EventOrganisers/Register"));
                startActivity(browserIntent);
                break;

            case R.id.action_tutorial:
                //Toast.makeText(this, "Login selected", Toast.LENGTH_SHORT)

                //.show();
                Intent intent = new Intent(VendorLogin1.this, Pop.class );
                startActivity(intent);
                break;
        }

        return true;

        //return super.onOptionsItemSelected(item);
    }



    public void VendorLogin(View view) {

        final AlertDialog ad=new AlertDialog.Builder(this).create();

        EditText editVendorCode = (EditText)findViewById(R.id.editVendorCode);
        EditText editVendorPin = (EditText)findViewById(R.id.editVendorPin);

        //check if vendor logging in is the same for the current shift
        String strVendorCode = editVendorCode.getText().toString();
        String strVendorPin = editVendorPin.getText().toString();

        if(strVendorPin.length()<= 0) {

            Toast.makeText(VendorLogin1.this, "Enter vendor PIN", Toast.LENGTH_SHORT).show();
            return;

        }
        if(strVendorCode.equals( Local.Get(getApplicationContext(), "VendorCode")) || Local.Get(getApplicationContext(), "VendorLoggedIn").equals("0") ) {


            MySOAPCallActivity cs = new MySOAPCallActivity();


            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            VendorLogin1Parameters params = new VendorLogin1Parameters(cs, Integer.parseInt(editVendorPin.getText().toString()), editVendorCode.getText().toString());

            //Store Vendor Login Credentials
            Local.Set(getApplicationContext(), "VendorCode", editVendorCode.getText().toString());
            Local.Set(getApplicationContext(), "VendorPin", editVendorPin.getText().toString());


            new CallSoapVendorLogin1().execute(params);

        }
        else {
            Toast.makeText(VendorLogin1.this, "Current Vendor must first close current shift. Click Admin in main menu to close shift.", Toast.LENGTH_SHORT).show();


        }

        if(strVendorCode.length()<= 0) {

            Toast.makeText(VendorLogin1.this, "Enter vendor code", Toast.LENGTH_SHORT).show();

        }




    }

    public void adminmenu(View view) {
        Intent intent1 = new Intent(VendorLogin1.this, Reset101.class );
        startActivity(intent1);


    }


    //Webmethod functions start
    public class CallSoapVendorLogin1 extends AsyncTask<VendorLogin1Parameters, Void, String> {

        private Exception exception;

        //After the webmethod is called
        @Override
        protected String doInBackground(VendorLogin1Parameters... params) {
            return params[0].sca.VendorLogin1(params[0].VendorPin, params[0].VendorCode);
        }

        //After the webmethod has run
        protected void onPostExecute(String result) {

            if(result.toString().contains("|")) {

                String[] value_split = result.split("\\|");
                String[] vendor = value_split[0].split("\\;");
                String vendorID = vendor[0];

                Local.Set(getApplicationContext(), "VendorID", vendorID);
                Local.Set(getApplicationContext(), "VendorLoggedIn", "Yes");

                Integer pos = result.indexOf("|");
                String eventsOnly = result.substring(pos);

                Local.Set(getApplicationContext(), "EventList", eventsOnly);

                Local.Set(getApplicationContext(), "CanSetEvent", "1");

                //Back to main menu
                Intent intent = new Intent(VendorLogin1.this, MainActivity.class );
                finish();
                startActivity(intent);
            }
            else {
                //Do nothing
                TextView vendorLoginResult = (TextView)findViewById(R.id.vendorLoginResult);
                //Store Vendor Login Credentials
                Local.Set(getApplicationContext(), "VendorCode", "0");
                Local.Set(getApplicationContext(), "VendorPin", "0");

                vendorLoginResult.setText(result);
            }

        }





    }


    private static class VendorLogin1Parameters {
        MySOAPCallActivity sca;
        String VendorCode;
        Integer VendorPin;


        VendorLogin1Parameters(MySOAPCallActivity sca, Integer VendorPin, String VendorCode) {
            this.sca = sca;
            this.VendorCode = VendorCode;
            this.VendorPin = VendorPin;

        }
    }

//Webmethod functions end

    private void WriteVendor() {
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("c:\\mytextfile.txt",MODE_PRIVATE); // MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Read text from file
    public void ReadBtn(View v) {
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            Toast.makeText(getBaseContext(), s,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
