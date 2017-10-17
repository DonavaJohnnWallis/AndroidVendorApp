package com.example.dsouchon.miidvendorapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reset101 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset101);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);





    }
    public void Login(View view) {

        final AlertDialog ad = new AlertDialog.Builder(this).create();

        EditText editVendorCode = (EditText) findViewById(R.id.editText);
        EditText editVendorPin = (EditText) findViewById(R.id.editText2);

        if(editVendorCode.getText().toString().equals("007") && editVendorPin.getText().toString().equals("700"))
        {
            Button button = (Button)findViewById(R.id.button2);
            button.setEnabled(true);
        }
    }

    public void Reset(View view) {
        ResetLocalSettings();
        Toast.makeText(this, "Application reset.", Toast.LENGTH_SHORT);
        Intent intent1 = new Intent(Reset101.this, MainActivity.class );
        finish();
        startActivity(intent1);
    }

    public void ResetLocalSettings(){
        Local.Set(getApplicationContext(), "VendorLoggedIn", "0" );
        Local.Set(getApplicationContext(), "VendorCode", "0" );

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
