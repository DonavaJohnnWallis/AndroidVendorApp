package com.example.dsouchon.miidvendorapp;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;


/**
 * Created by DANIEL on 11/9/2015.
 */
public class ScanTagPin6 extends AppCompatActivity {

    //DEBUG COMMENTED OUT
    NfcAdapter nfcAdapter;

    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(ScanTagPin6.this, "Select Home in main menu to go back or Clear to restart sale.", Toast.LENGTH_SHORT).show();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.scantagpin6);
            final TextView textViewAmountToPay = (TextView) findViewById(R.id.textViewAmountToPay);
            textViewAmountToPay.setText(Local.read(this.getApplicationContext(), "TenderAmount"));
            final AlertDialog ad = new AlertDialog.Builder(this).create();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


            ProgressBar mprogressbar;
            mprogressbar = (ProgressBar) findViewById(R.id.progressbar);
            mprogressbar.setVisibility(View.GONE);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.clearpaymentbtn){
            startActivity(new Intent(this,TenderAmount5.class));
        }

        if (id == R.id.action_vendorLogin){
            startActivity(new Intent(this,MainActivity.class));
        }


        return super.onOptionsItemSelected(item);


        //return super.onOptionsItemSelected(item);
    }




    public void append0(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            if(Integer.parseInt(editTagPin.getText().toString())>0) {
                editTagPin.setText(editTagPin.getText() + "0");
            }

        } catch (Exception ex) {
        }

    }

    public void append1(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText(editTagPin.getText() + "1");

        } catch (Exception ex) {
        }

    }

    public void append2(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText(editTagPin.getText() + "2");

        } catch (Exception ex) {
        }

    }
    public void append3(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText(editTagPin.getText() + "3");

        } catch (Exception ex) {
        }

    }
    public void append4(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText(editTagPin.getText() + "4");

        } catch (Exception ex) {
        }

    }
    public void append5(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText(editTagPin.getText() + "5");

        } catch (Exception ex) {
        }

    }
    public void append6(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText(editTagPin.getText() + "6");

        } catch (Exception ex) {
        }

    }
    public void append7(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText(editTagPin.getText() + "7");

        } catch (Exception ex) {
        }

    }
    public void append8(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText(editTagPin.getText() + "8");

        } catch (Exception ex) {
        }

    }

    public void append9(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText(editTagPin.getText() + "9");

        } catch (Exception ex) {
        }

    }


    public void append00(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            if(Integer.parseInt(editTagPin.getText().toString())>0) {
                editTagPin.setText(editTagPin.getText() + "00");
            }

        } catch (Exception ex) {
        }

    }

    public void clearNumber(View view) {

        final EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
        try {
            editTagPin.setText("");

        } catch (Exception ex) {
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

            }
            else{
                globalVariable.setIsUserLoggedIn(false);
            }
        }



    }





    //Webmethod functions start
  /*  public class CallSoapScanTagForPayment6 extends AsyncTask<ScanTagForPayment6Parameters, Void, String> {

        private Exception exception;

        //After the webmethod is called
        @Override
        protected String doInBackground(ScanTagForPayment6Parameters... params) {
            return params[0].sca.ScanTagForPayment6(params[0].TagNumber);
        }

        //After the webmethod has run
        protected void onPostExecute(String result) {

            if(result.toString().contains("|")) {
                Intent intent = new Intent(ScanTagPin6.this, PaymentResult7NoPin.class );
                Bundle bundle = new Bundle();
                bundle.putString("PaymentResult", result);


                finish();
                startActivity(intent);

            }
            else {
                //Do nothing

            }

        }





    }


    private static class ScanTagForPayment6Parameters {
        MySOAPCallActivity sca;
        String TagNumber;





        ScanTagForPayment6Parameters(MySOAPCallActivity sca, String TagNumber) {
            this.sca = sca;
            this.TagNumber = TagNumber;


        }
    }
    */

//Webmethod functions end



    public void goPanelShow(View view) {
        //Hide top panel
        //LinearLayout ll2top = (LinearLayout)findViewById(R.id.ll2top);
        //ll2top.setVisibility(View.GONE);

       // LinearLayout ll1top = (LinearLayout)findViewById(R.id.ll2top);
       // ll1top.setVisibility(View.GONE);


        //Show bottom panel
        LinearLayout ll3 = (LinearLayout)findViewById(R.id.ll3);
        LinearLayout ll4 = (LinearLayout)findViewById(R.id.ll4);
        LinearLayout ll5 = (LinearLayout)findViewById(R.id.ll5);
        LinearLayout ll6 = (LinearLayout)findViewById(R.id.ll6);
        ll3.setVisibility(View.VISIBLE);
        ll4.setVisibility(View.VISIBLE);
        ll5.setVisibility(View.VISIBLE);
        ll6.setVisibility(View.VISIBLE);
    }

    public void goPanelShowNFC() {
        //Hide top panel
        //LinearLayout ll2top = (LinearLayout)findViewById(R.id.ll2top);
        //ll2top.setVisibility(View.INVISIBLE);

        //LinearLayout ll1top = (LinearLayout)findViewById(R.id.ll2top);
        //ll1top.setVisibility(View.INVISIBLE);


        //Show bottom panel
        LinearLayout ll3 = (LinearLayout)findViewById(R.id.ll3);
        LinearLayout ll4 = (LinearLayout)findViewById(R.id.ll4);
        LinearLayout ll5 = (LinearLayout)findViewById(R.id.ll5);
        LinearLayout ll6 = (LinearLayout)findViewById(R.id.ll6);
        ll3.setVisibility(View.VISIBLE);
        ll4.setVisibility(View.VISIBLE);
        ll5.setVisibility(View.VISIBLE);
        ll6.setVisibility(View.VISIBLE);
    }

    public void EnterPin(View view) {

        String appID = Installation.applicationId(getApplicationContext());

        final AlertDialog ad=new AlertDialog.Builder(this).create();



        MySOAPCallActivity cs = new MySOAPCallActivity();
        try {

            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

            //EditText editTagPin = (EditText)findViewById(R.id.editTagPin);
            EditText editTagNumber = (EditText)findViewById(R.id.editTagNumber);
            //String sPin = editTagPin.getText().toString();
            // if (sPin.matches("")) {
                // Toast.makeText(this, "You did not enter a PIN", Toast.LENGTH_SHORT).show();
                // return;


            // }




            String TenderAmount = Local.read(this.getApplicationContext(), "TenderAmount");

            //get values from controls
            Integer PinNumber = 0;//Integer.parseInt(editTagPin.getText().toString());
            String TagNumber = editTagNumber.getText().toString();
            if(editTagNumber.getText().toString().equals("")) {
                TagNumber = Local.Get(getApplicationContext(), "TagNo");
            }

            Integer Amount= Integer.parseInt(TenderAmount);
            Integer EventID = Integer.parseInt(Local.read(this.getApplicationContext(), "EventID"));
            Integer VendorID  = Integer.parseInt(Local.read(this.getApplicationContext(), "VendorID"));
            String DeviceCode = appID;
            Integer StaffID= Integer.parseInt(Local.read(this.getApplicationContext(), "StaffID"));
            Integer ShiftNo= Integer.parseInt(Local.read(this.getApplicationContext(), "ShiftNo"));

            PaymentResult7NoPinParameters params = new PaymentResult7NoPinParameters (cs, PinNumber, TagNumber, Amount, EventID, VendorID, DeviceCode, StaffID, ShiftNo);

            new CallSoapPaymentResult7NoPin().execute(params);

        } catch (Exception ex) {
            ad.setTitle("Set Event Error!");
            ad.setMessage(ex.toString());


        }
        ad.show();



    }



    //Webmethod functions start
    public class CallSoapPaymentResult7NoPin extends AsyncTask<PaymentResult7NoPinParameters, Void, String> {

        private Exception exception;

        //After the webmethod is called
        @Override
        protected String doInBackground(PaymentResult7NoPinParameters... params) {
            return params[0].sca.PaymentResult7NoPin(params[0].PinNumber, params[0].TagNumber, params[0].Amount, params[0].EventID, params[0].VendorID, params[0].DeviceCode, params[0].StaffID, params[0].ShiftNo);
        }

        //After the webmethod has run
        protected void onPostExecute(String result) {

            if(result.toString().toLowerCase().contains("success")) {
                //Success!
                try {
                    Local.write(getApplicationContext(), "PaymentResult", result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(ScanTagPin6.this, PaymentResult7.class );
                finish();
                startActivity(intent);

            }
            else {
                //Do nothing
                try {
                    Local.write(getApplicationContext(), "PaymentResult", result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(ScanTagPin6.this, PaymentResult7.class );
                finish();
                startActivity(intent);

            }

        }

    }


    private static class PaymentResult7NoPinParameters {
        MySOAPCallActivity sca;
        Integer PinNumber;
        String TagNumber;
        Integer Amount;
        Integer EventID;
        Integer VendorID;
        String DeviceCode;
        Integer StaffID;
        Integer ShiftNo;

        PaymentResult7NoPinParameters(MySOAPCallActivity sca,   Integer PinNumber, String TagNumber,
                                 Integer Amount,
                                 Integer EventID,
                                 Integer VendorID,
                                 String DeviceCode,
                                 Integer StaffID,
                                 Integer ShiftNo) {
            this.sca = sca;
            this.PinNumber = PinNumber;
            this.TagNumber = TagNumber;
            this.Amount = Amount;
            this.EventID = EventID;
            this.VendorID = VendorID;
            this.DeviceCode = DeviceCode;
            this.StaffID = StaffID;
            this.ShiftNo = ShiftNo;


        }
    }


//NFC Stuff Start - COMMENTING OUT FOR DEBUGGING

    @Override
    protected void onNewIntent( Intent intent) {

        super.onNewIntent(intent);
        final  AlertDialog ad=new AlertDialog.Builder(this).create();
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {



            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            String tagNo = "";

            if(parcelables != null && parcelables.length > 0){
                readTextFromMessage((NdefMessage)parcelables [0]);




                //MySOAPCallActivity cs = new MySOAPCallActivity();
                try{


                    tagNo =  readTextFromMessage((NdefMessage) parcelables[0]);
                    EditText editTagNumber = (EditText)findViewById(R.id.editTagNumber);
                    editTagNumber.setText(tagNo);
                    Local.Set(getApplicationContext(), "TagNo", tagNo);
                    goPanelShowNFC();

                }
                catch(Exception ex) {
                 //code causing hang on galaxy s4
                   // ad.setTitle("Error!"); 
                   // ad.setMessage(ex.toString());
                }
                //ad.show();




            }else{
                Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_SHORT).show();
            }




        }
    }

    private String readTextFromMessage(NdefMessage ndefMessage) {

        NdefRecord[] ndefRecords = ndefMessage.getRecords();

        if (ndefRecords != null && ndefRecords.length > 0) {

            NdefRecord ndefRecord = ndefRecords[0];

            String tagContent = getTextFromNdefRecord(ndefRecord);

            return tagContent;
            //txtTagContent.setText(tagContent);

        } else
        {
            Toast.makeText(this, "No NDEF records found!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    public String getTextFromNdefRecord(NdefRecord ndefRecord){
        String tagContent = null;
        try{
            byte[] payload = ndefRecord.getPayload();
            String textEncoding = ((payload[0] & 128 ) == 0) ? "UTF-8":"UTF-16";
            int languageSize = payload[0] & 0063;
            tagContent = new String(payload, languageSize + 1,payload.length - languageSize -1, textEncoding);

        } catch (UnsupportedEncodingException e) {
            Log.e("getTextFromNdefRecord", e.getMessage(), e);
        }
        return tagContent;

    }




    @Override
    protected void onResume(){

        super.onResume();

        enableForegroundDispatchSystem();

    }
    @Override
    protected void onPause(){

        super.onPause();
        disableForegroundDispatchSystem();

    }

    private void enableForegroundDispatchSystem(){
        if(nfcAdapter==null)
        {
            nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        }

        Intent intent = new Intent(this, ScanTagPin6.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilters = new IntentFilter[] {};
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);


    }

    private void disableForegroundDispatchSystem(){
        nfcAdapter.disableForegroundDispatch(this);
    }

    private void formatTag(Tag tag, NdefMessage ndefMessage)
    {
        try{
            NdefFormatable ndefFormatable = NdefFormatable.get(tag);

            if(ndefFormatable == null)
            {

                Toast.makeText(this, "Tag is not ndef formattable!", Toast.LENGTH_SHORT).show();


            }
            ndefFormatable.connect();
            ndefFormatable.format(ndefMessage);
            ndefFormatable.close();

            Toast.makeText(this, "Tag written!", Toast.LENGTH_SHORT).show();

        }
        catch(Exception e){
            Log.e("formatTag", e.getMessage());
        }

    }

    private void writeNdefMessage(Tag tag, NdefMessage ndefMessage)
    {
        try{


            if(tag == null)
            {

                Toast.makeText(this, "Tag object cannot be null!", Toast.LENGTH_SHORT).show();

                return;
            }
            Ndef ndef = Ndef.get(tag);
            if (ndef == null)
            {
//format tag with the ndef format and write the message
                formatTag(tag, ndefMessage);


            }
            else
            {
                ndef.connect();
                if(!ndef.isWritable())
                {
                    Toast.makeText(this, "Tag is not writable!", Toast.LENGTH_SHORT).show();
                    ndef.close();
                    return;


                }
                ndef.writeNdefMessage(ndefMessage);
                ndef.close();
                Toast.makeText(this, "Tag written!", Toast.LENGTH_SHORT).show();

            }
        }
        catch(Exception e){
            Log.e("writeNdefMessage", e.getMessage());
        }

    }


    private NdefRecord createTextRecord(String content)
    {
        try{
            byte[] language;
            language = Locale.getDefault().getLanguage().getBytes("QTF-8");
            final byte[] text = content.getBytes("QTF-8");
            final int languageSize = language.length;
            final int textLength = text.length;
            final ByteArrayOutputStream payload = new ByteArrayOutputStream(1 + languageSize + textLength);

            payload.write((byte)(languageSize & 0x1F));
            payload.write(language, 0, languageSize);
            payload.write(text, 0 , textLength);

            return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload.toByteArray());





        }
        catch(Exception e){

            Log.e("createTextRecord", e.getMessage());

        }
        return  null;
    }

    private NdefMessage createNdefMessage(String content)
    {
        NdefRecord ndefRecord = createTextRecord(content);

        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ ndefRecord});

        return ndefMessage;
    }

//NFC Stuff End

}






