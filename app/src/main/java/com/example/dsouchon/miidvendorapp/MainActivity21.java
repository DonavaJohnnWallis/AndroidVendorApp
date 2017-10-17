package com.example.dsouchon.miidvendorapp;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.PendingIntent;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.pm.ActivityInfo;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        /*import android.nfc.NdefMessage;
        import android.nfc.NdefRecord;
        import android.nfc.NfcAdapter;
        import android.nfc.Tag;
        import android.nfc.tech.Ndef;
        import android.nfc.tech.NdefFormatable;
        */
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.Parcelable;
        import android.util.Base64;
        import android.util.Log;
        import android.view.Menu;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.ByteArrayOutputStream;
        import java.io.UnsupportedEncodingException;
        import java.util.Locale;


public class MainActivity21 extends Activity {

    //NfcAdapter nfcAdapter;
    EditText txtTagContent;

    /** Called when the activity is first created. */

    @Override
    public void onBackPressed() {
        // your code.
        Toast.makeText(MainActivity21.this, "Select Home in Main Menu to go back.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        final  AlertDialog ad=new AlertDialog.Builder(this).create();
        txtTagContent = (EditText)findViewById(R.id.editTagNumber);
        //nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        // Get name and email from global/application context
        final String eventImageString  = globalVariable.getEventImage();

        if(eventImageString != null && eventImageString.length() > 0) {
            ImageView imageViewEventImage = (ImageView) findViewById(R.id.imageViewEventImage);
            byte[] decodedString = Base64.decode(eventImageString, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageViewEventImage.setImageBitmap(decodedByte);
        }
        else {
            Intent intent = new Intent(MainActivity21.this, SetupEvent.class );
            finish();
            startActivity(intent);

        }




   Button buttonYes = (Button)findViewById(R.id.buttonYes);
        buttonYes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MySOAPCallActivity cs = new MySOAPCallActivity();
                try{
                    EditText editTagNumber = (EditText)findViewById(R.id.editTagNumber);

                    String tagNo = editTagNumber.getText().toString();

                    final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

                    // Get name and email from global/application context
                    final String eventName  = globalVariable.getEventName();

                    if(eventName.length() > 0) {


                        //TextView hidden = (TextView) findViewById(R.id.labelScanResult);

                        //String Reason = hidden.getText().toString();

                        String Reason = "Allow Access";

                        AllowDenyParams params = new AllowDenyParams(cs, tagNo, Reason, false, eventName);

                        new CallSoapAllowDenyAccess().execute(params);
                    }
                }
                catch(Exception ex) {
                    ad.setTitle("Error!");
                    ad.setMessage(ex.toString());
                }
                ad.show();

            }
        });

        Button buttonNo = (Button)findViewById(R.id.buttonNo);
        buttonNo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MySOAPCallActivity cs = new MySOAPCallActivity();
                try {
                    EditText editTagNumber = (EditText) findViewById(R.id.editTagNumber);

                    String tagNo = editTagNumber.getText().toString();


                    final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

                    // Get name and email from global/application context
                    final String eventName  = globalVariable.getEventName();

                    if(eventName.length() > 0) {


                        TextView hidden = (TextView) findViewById(R.id.labelScanResult);

                        String Reason = hidden.getText().toString();

                        AllowDenyParams params = new AllowDenyParams(cs, tagNo, Reason, true, eventName);

                        new CallSoapAllowDenyAccess().execute(params);
                    }

                } catch (Exception ex) {
                    ad.setTitle("Error!");
                    ad.setMessage(ex.toString());
                }
                ad.show();

            }
        });





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //Logic business after the web service complete here
//Do the thing that modify the UI in a function like this
    private void onTaskCompleted(Object _response)
    {


    }

    public void openSetupNow(View view) {
        Intent in = new Intent(MainActivity21.this, MainActivity.class);
        startActivity(in);
        finish();
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

    private static class TagParams {
        MySOAPCallActivity foo;
        String tagNumber;
        String eventName;



        TagParams(MySOAPCallActivity foo, String tagNumber, String eventName) {
            this.foo = foo;
            this.tagNumber = tagNumber;
            this.eventName = eventName;

        }
    }

    private static class AllowDenyParams {
        MySOAPCallActivity foo;
        String TagNumber;
        String ReasonForBlocking;
        boolean Block;
        String EventName;


        AllowDenyParams(MySOAPCallActivity foo, String TagNumber, String ReasonForBlocking, boolean Block, String EventName) {
            this.foo = foo;
            this.TagNumber = TagNumber;
            this.ReasonForBlocking = ReasonForBlocking;
            this.Block = Block;
            this.EventName = EventName;

        }
    }







    public class CallSoapTicketValidForEvent extends AsyncTask<TagParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(TagParams... params) {
            return params[0].foo.TicketValidForEvent(params[0].tagNumber, params[0].eventName);
        }

        protected void onPostExecute(String result) {
            // TODO: check this.exception
            // TODO: do something with the feed

            //TextView currentEventsResult =(TextView)findViewById(R.id.labelGetCurrentEventsResult);
            //currentEventsResult.setText(result);

            TextView labelScanResult = (TextView)findViewById(R.id.labelScanResult);


            if(result.toLowerCase().contains("no valid ticket")|| result.toLowerCase().contains("not found"))//"no valid ticket"
            {
                labelScanResult.setText(result);
            }
            else
            {
                labelScanResult.setText("Success!");

                //TableLayout tableLayout = (TableLayout) findViewById(R.id.tab);
                //tableLayout.removeAllViews();


                String[] rows = result.toString().split("\\n?\\n");
                String profilePic = rows[3];

                if(profilePic.length() > 0) {
                    ImageView imageViewProfilePic = (ImageView) findViewById(R.id.imageViewProfilePic);
                    byte[] decodedString = Base64.decode(profilePic, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imageViewProfilePic.setImageBitmap(decodedByte);
                }


                TextView nameSurname = (TextView)findViewById(R.id.nameSurname);
                nameSurname.setText(rows[0]);
                TextView idNumber = (TextView)findViewById(R.id.idNumber);
                idNumber.setText(rows[1]);
                TextView ticketClass = (TextView)findViewById(R.id.ticketClass);
                ticketClass.setText(rows[2]);


            }

        }





    }

/*NFC
    @Override
    protected void onNewIntent( Intent intent) {

        super.onNewIntent(intent);
        final  AlertDialog ad=new AlertDialog.Builder(this).create();
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
           // Toast.makeText(this, "NfcIntent!", Toast.LENGTH_SHORT).show();


                Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

                if(parcelables != null && parcelables.length > 0){
                    readTextFromMessage((NdefMessage)parcelables [0]);

                    MySOAPCallActivity cs = new MySOAPCallActivity();
                    try{
                        EditText editTagNumber = (EditText)findViewById(R.id.editTagNumber);

                        String tagNo = editTagNumber.getText().toString();

                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

                        // Get name and email from global/application context
                        final String eventName  = globalVariable.getEventName();

                        if(eventName.length() > 0) {



                            TagParams params = new TagParams(cs, tagNo, eventName);

                            new CallSoapTicketValidForEvent().execute(params);
                        }

                    }
                    catch(Exception ex) {
                        ad.setTitle("Error!");
                        ad.setMessage(ex.toString());
                    }
                    ad.show();




                }else{
                    Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_SHORT).show();
                }




        }
    }

    private void readTextFromMessage(NdefMessage ndefMessage) {

        NdefRecord[] ndefRecords = ndefMessage.getRecords();

        if (ndefRecords != null && ndefRecords.length > 0) {

            NdefRecord ndefRecord = ndefRecords[0];

            String tagContent = getTextFromNdefRecord(ndefRecord);

            txtTagContent.setText(tagContent);

        } else
        {
            Toast.makeText(this, "No NDEF records found!", Toast.LENGTH_SHORT).show();
        }

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

*/

    public class CallSoapAllowDenyAccess extends AsyncTask<AllowDenyParams, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(AllowDenyParams... params) {
            return params[0].foo.AllowDenyAccess(params[0].TagNumber, params[0].ReasonForBlocking, params[0].Block, params[0].EventName);
        }

        protected void onPostExecute(String result) {

            //TextView labelFinalConfirmation = (TextView)findViewById(R.id.labelFinalConfirmation);
            //labelFinalConfirmation.setText(result);

            TextView labelScanResult = (TextView)findViewById(R.id.labelScanResult);
            labelScanResult.setText(result);

            //TableLayout tableLayout = (TableLayout) findViewById(R.id.tab);
            //tableLayout.removeAllViews();

        }





    }


/* NFC STUFF COMMENTED OUT

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
        Intent intent = new Intent(this, MainActivity21.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
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
    */
}

