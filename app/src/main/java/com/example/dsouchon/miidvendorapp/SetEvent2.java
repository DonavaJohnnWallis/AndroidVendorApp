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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SetEvent2 extends AppCompatActivity {

    private Spinner spinner;


    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(SetEvent2.this, "Select Home in Main Menu to go back.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setevent2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);






        // Now add the following lines to the onCreate method in our activity java class, in this case it is MainScreen.java.

                // Step 1: Locate our spinner control and save it to the class for convenience
                //         You could get it every time, I'm just being lazy...   :-)
        spinner = (Spinner)this.findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                              {
                                                  // Get the currently selected State object from the spinner
                                                  EventClass st = (EventClass)spinner.getSelectedItem();

                                                  String settingValue = st.id.toString();
                                                  String settingName = st.name.toString();

                                                  Local.Set(getApplicationContext(), "EventID", settingValue);
                                                  Local.Set(getApplicationContext(), "EventName", settingName);
                                                  Local.Set(getApplicationContext(), "EventSet", "1");
                                                  Local.Set(getApplicationContext(), "VendorLoggedIn", "0");

                                              }

                                              public void onNothingSelected(AdapterView<?> parent )
                                              {
                                              }

                                              public void toastState( String prefix, EventClass st )
                                              {
                                                  if ( st != null )
                                                  {
                                                      String desc = "Event: " + prefix + "\nstate: " + st.name;
                                                      desc += "\nabbreviation: " + st.abbrev + "\nid: " + String.valueOf( st.id );
                                                      Toast.makeText(getApplicationContext(), desc, Toast.LENGTH_SHORT).show();
                                                  }
                                              }

                                          }
        );
        String eventListArrayStr = "";
        //get events from file
        try {
            eventListArrayStr =  Local.read(getApplicationContext(), "EventList");
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(eventListArrayStr.contains("|")){

            String[] RowData = eventListArrayStr.toString().split("\\|");
            List<EventClass> spinnerArray = new ArrayList<EventClass>();
            for(int x=0;x<RowData.length;x++)
            {
                if(RowData[x].toString().contains(";")) {
                    String[] EventRecord = RowData[x].toString().split(";");
                    spinnerArray.add(new EventClass(Integer.parseInt(EventRecord[0].toString()), EventRecord[1].toString()));
                }
            }

        // Step 2: Create and fill an ArrayAdapter with a bunch of "State" objects
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                R.layout.spinnerstyle, spinnerArray);

        // Step 3: Tell the spinner about our adapter
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinnerstyledrop);
            spinner.setAdapter(spinnerArrayAdapter);
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

                Intent intent1 = new Intent(SetEvent2.this, MainActivity.class );
                startActivity(intent1);
                break;

            default:
                break;
        }

        return true;

        //return super.onOptionsItemSelected(item);
    }




    public void SetEvent(View view) {

        String appID = Installation.applicationId(getApplicationContext());

        final AlertDialog ad=new AlertDialog.Builder(this).create();

        EditText editVendorCode = (EditText)findViewById(R.id.editVendorCode);
        EditText editVendorPin = (EditText)findViewById(R.id.editVendorPin);

        MySOAPCallActivity cs = new MySOAPCallActivity();


            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();



            //get values from controls
            Integer eventID = 0;
            Integer vendorID = 0;
            String deviceCode = appID;

            vendorID = Integer.parseInt(Local.Get(getApplicationContext(), "VendorID"));
            eventID = Integer.parseInt(Local.Get(getApplicationContext(), "EventID"));


            SetEvent2Parameters params = new SetEvent2Parameters(cs, eventID, vendorID, deviceCode);

            new CallSoapSetEvents2().execute(params);





    }


    //Webmethod functions start
    public class CallSoapSetEvents2 extends AsyncTask<SetEvent2Parameters, Void, String> {

        private Exception exception;

        //After the webmethod is called
        @Override
        protected String doInBackground(SetEvent2Parameters... params) {
            return params[0].sca.SetEvent2(params[0].EventID, params[0].VendorID, params[0].DeviceCode);
        }

        //After the webmethod has run
        protected void onPostExecute(String result) {

            if(result.toString().contains("Successfully")) {


                Local.Set(getApplicationContext(), "EventSet", result);

                MySOAPCallActivity cs = new MySOAPCallActivity();
                Integer VendorID = Integer.parseInt(Local.Get(getApplicationContext(),"VendorID"));
                Integer EventID= Integer.parseInt(Local.Get(getApplicationContext(),"EventID"));
                GetStaff3cParameters params = new GetStaff3cParameters(cs, VendorID,  EventID);

                new CallSoapGetStaff3c().execute(params);



                Intent intent = new Intent(SetEvent2.this, AddStaff.class );
                finish();
                startActivity(intent);

            }
            else {
                //Do nothing
                TextView vendorLoginResult = (TextView)findViewById(R.id.vendorLoginResult);

                vendorLoginResult.setText(result);
            }

        }





    }


    private static class SetEvent2Parameters {
        MySOAPCallActivity sca;
        String DeviceCode;
        Integer VendorID;
        Integer EventID;

        SetEvent2Parameters(MySOAPCallActivity sca, Integer EventID, Integer VendorID, String DeviceCode) {
            this.sca = sca;
            this.EventID = EventID;
            this.VendorID = VendorID;
            this.DeviceCode = DeviceCode;

        }
    }


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


                String staffListArrayStr = result;

                if(staffListArrayStr.contains("|")){

                    Local.Set(getApplicationContext(), "StaffList", result);
                    Local.Set(getApplicationContext(), "StaffAdded", "1");

                }

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
