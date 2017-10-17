package com.example.dsouchon.miidvendorapp;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by DANIEL on 11/9/2015.
 */
public class TenderAmount5 extends AppCompatActivity {
    private Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tenderamount5);
        final AlertDialog ad=new AlertDialog.Builder(this).create();
        TextView labelCurrentEvent = (TextView)findViewById(R.id.labelCurrentEvent);
        TextView labelCurrentShift = (TextView)findViewById(R.id.labelCurrentShift);
        TextView labelStaffCode = (TextView)findViewById(R.id.labelStaffCode);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        try {
            labelCurrentEvent.setText("Event:" + Local.read(getApplicationContext(), "EventName"));
            labelCurrentShift.setText("Shift No:" + Local.read(getApplicationContext(), "ShiftNo"));
            labelStaffCode.setText("Staff Code:" + Local.read(getApplicationContext(), "StaffCode"));
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(TenderAmount5.this, "Select Home in Main Menu to go back.", Toast.LENGTH_SHORT).show();
    }


    public void append0(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            if(Integer.parseInt(txtAmount.getText().toString())>0) {
                txtAmount.setText(txtAmount.getText() + "0");
            }

        } catch (Exception ex) {
        }

    }
    public void append1(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText(txtAmount.getText() + "1");

        } catch (Exception ex) {
        }

    }
    public void append2(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText(txtAmount.getText() + "2");

        } catch (Exception ex) {
        }

    }
    public void append3(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText(txtAmount.getText() + "3");

        } catch (Exception ex) {
        }

    }
    public void append4(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText(txtAmount.getText() + "4");

        } catch (Exception ex) {
        }

    }
    public void append5(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText(txtAmount.getText() + "5");

        } catch (Exception ex) {
        }

    }
    public void append6(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText(txtAmount.getText() + "6");

        } catch (Exception ex) {
        }

    }
    public void append7(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText(txtAmount.getText() + "7");

        } catch (Exception ex) {
        }

    }
    public void append8(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText(txtAmount.getText() + "8");

        } catch (Exception ex) {
        }

    }

    public void append9(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText(txtAmount.getText() + "9");

        } catch (Exception ex) {
        }

    }


    public void append00(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            if(Integer.parseInt(txtAmount.getText().toString())>0) {
                txtAmount.setText(txtAmount.getText() + "00");
            }

        } catch (Exception ex) {
        }

    }

    public void clearNumber(View view) {

        final TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            txtAmount.setText("");

        } catch (Exception ex) {
        }

    }

    public void TenderAmount(View view) {

        // Check if no view has focus:
        View view1 = this.getCurrentFocus();
        if (view1 != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
        }
        //TO DO: open tag tender screen
        TextView txtAmount = (TextView)findViewById(R.id.textView);
        try {
            Local.write(getApplicationContext(), "TenderAmount", txtAmount.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(TenderAmount5.this, ScanTagPin6.class );
        finish();
        startActivity(intent);
        //Send Amount

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
                //Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                //        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_vendorLogin:
                //Toast.makeText(this, "Login selected", Toast.LENGTH_SHORT)

                        //.show();
                Intent intent1 = new Intent(TenderAmount5.this, MainActivity.class );
                startActivity(intent1);
                break;

            default:
                break;
        }

        return true;

        //return super.onOptionsItemSelected(item);
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

