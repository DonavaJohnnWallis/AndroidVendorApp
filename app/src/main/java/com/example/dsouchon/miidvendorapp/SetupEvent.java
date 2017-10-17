package com.example.dsouchon.miidvendorapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SetupEvent extends Activity {

    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_event);
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        final EditText userName = (EditText) findViewById(R.id.editUserName);
        final EditText password = (EditText) findViewById(R.id.editPassword);


        final Button btnLogin = (Button)findViewById(R.id.buttonLogin);
        final Button buttonOKEvent = (Button)findViewById(R.id.buttonOKEvent);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySOAPCallActivity cs = new MySOAPCallActivity();
                try {
                    String user = userName.getText().toString();
                    String pwd = password.getText().toString();
                    LoginParams params = new LoginParams(cs, user, pwd);

                    new CallSoapLogin().execute(params);

                    /*final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                    if(globalVariable.getIsUserLoggedIn()) {
                        btnLogin.setVisibility(View.GONE);
                        buttonOKEvent.setVisibility(View.VISIBLE);
                        userName.setVisibility(View.GONE);
                        password.setVisibility(View.GONE);
                        new CallSoapGetCurrentEvents().execute(params);
                    }
                    else
                    {
                        btnLogin.setVisibility(View.VISIBLE);
                        buttonOKEvent.setVisibility(View.GONE);
                    }*/
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
                } catch (Exception ex) {
                    ad.setTitle("Error!");
                    ad.setMessage(ex.toString());
                }
                ad.show();

            }
        });



        Spinner spinner = (Spinner)findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                MySOAPCallActivity cs = new MySOAPCallActivity();

                String eventName = ((Spinner) findViewById(R.id.spinner2)).getSelectedItem().toString();

                // Calling Application class (see application tag in AndroidManifest.xml)
                final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                globalVariable.setEventName(eventName);

                EventImageParams params = new EventImageParams(cs, eventName);

                new CallSoapGetEventImage().execute(params);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setup_event, menu);
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

                Intent intent1 = new Intent(SetupEvent.this, MainActivity.class );
                startActivity(intent1);
                break;

            default:
                break;
        }

        return true;
    }

    public class CallSoapGetEventImage extends AsyncTask<EventImageParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(EventImageParams... params) {
            return params[0].foo.GetEventImage(params[0].eventName);
        }

        protected void onPostExecute(String result) {


            ImageView imageViewEventImage = (ImageView)findViewById(R.id.imageViewEventImage);
            byte[] decodedString = Base64.decode(result, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageViewEventImage.setImageBitmap(decodedByte);

            // Calling Application class (see application tag in AndroidManifest.xml)
            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
            globalVariable.setEventImage(result);
        }





    }

    private static class EventImageParams {
        MySOAPCallActivity foo;
        String eventName;



        EventImageParams (MySOAPCallActivity foo,  String eventName) {
            this.foo = foo;

            this.eventName = eventName;

        }
    }
    private static class LoginParams {
        MySOAPCallActivity foo;
        String username;
        String password;


        LoginParams(MySOAPCallActivity foo, String username, String password) {
            this.foo = foo;
            this.username = username;
            this.password = password;

        }
    }
    public class CallSoapGetCurrentEvents extends AsyncTask<LoginParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(LoginParams... params) {
            return params[0].foo.GetCurrentEvents(params[0].username, params[0].password);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed

            //TextView currentEventsResult =(TextView)findViewById(R.id.labelGetCurrentEventsResult);
            //currentEventsResult.setText(result);


            String[] RowData = result.toString().split("\\|");
            List<String> spinnerArray = new ArrayList<String>();
            for(int x=0;x<RowData.length;x++)
            {
                spinnerArray.add(RowData[x].toString());
            }

            spinner2 = (Spinner) findViewById(R.id.spinner2);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SetupEvent.this, R.layout.spinnerstyle, spinnerArray);

            adapter.setDropDownViewResource(R.layout.spinnerstyledrop);
            spinner2.setAdapter(adapter);



        }





    }


    public class CallSoapLogin extends AsyncTask<LoginParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(LoginParams... params) {
            return params[0].foo.Login(params[0].username, params[0].password);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed
            final EditText userName = (EditText) findViewById(R.id.editUserName);
            final EditText password = (EditText) findViewById(R.id.editPassword);

            MySOAPCallActivity cs = new MySOAPCallActivity();
            final Button btnLogin = (Button)findViewById(R.id.buttonLogin);
            final Button buttonOKEvent = (Button)findViewById(R.id.buttonOKEvent);
            TextView loginResult =(TextView)findViewById(R.id.labelLoginResult);
            loginResult.setText(result);
            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
            if(result.toLowerCase().contains("success")){
                String user = userName.getText().toString();
                String pwd = password.getText().toString();
                LoginParams params = new LoginParams(cs, user, pwd);
                globalVariable.setIsUserLoggedIn(true);
                btnLogin.setVisibility(View.GONE);
                buttonOKEvent.setVisibility(View.VISIBLE);
                userName.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                new CallSoapGetCurrentEvents().execute(params);
            }
            else{
                globalVariable.setIsUserLoggedIn(false);
            }
        }



    }
}
