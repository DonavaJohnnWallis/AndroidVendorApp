package com.example.dsouchon.miidvendorapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

/**
 * Created by Donavan on 2017/01/06.
 */

public class Pop extends Activity implements View.OnClickListener {

    ViewFlipper viewFlipper;
    Button next;
    Button previous;
    Button nexta;
    Button previousa;
    Button nextone;
    Button previousone;
    Button nexttwo;
    Button previoustwo;
    Button nextthree;
    Button previousthree;
    Button nextfour;
    Button previousfour;
    Button nextfive;
    Button previousfive;
    Button nextsix;
    Button previoussix;
    Button nextb;
    Button previousb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutrial);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);



        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .85));


        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        next = (Button) findViewById(R.id.next);
        previous = (Button) findViewById(R.id.previous);

        next.setOnClickListener(this);
        previous.setOnClickListener(this);


        nexta = (Button) findViewById(R.id.nexta);
        previousa = (Button) findViewById(R.id.previousa);

        nexta.setOnClickListener(this);
        previousa.setOnClickListener(this);

        nextone = (Button) findViewById(R.id.nextone);
        previousone = (Button) findViewById(R.id.previousone);

        nextone.setOnClickListener(this);
        previousone.setOnClickListener(this);

        nexttwo = (Button) findViewById(R.id.nexttwo);
        previoustwo = (Button) findViewById(R.id.previoustwo);

        nexttwo.setOnClickListener(this);
        previoustwo.setOnClickListener(this);

        nextthree = (Button) findViewById(R.id.nextthree);
        previousthree = (Button) findViewById(R.id.previousthree);

        nextthree.setOnClickListener(this);
        previousthree.setOnClickListener(this);

        nextfour = (Button) findViewById(R.id.nextfour);
        previousfour = (Button) findViewById(R.id.previousfour);

        nextfour.setOnClickListener(this);
        previousfour.setOnClickListener(this);

        nextfive = (Button) findViewById(R.id.nextfive);
        previousfive = (Button) findViewById(R.id.previousfive);

        nextfive.setOnClickListener(this);
        previousfive.setOnClickListener(this);

        nextb = (Button) findViewById(R.id.nextb);
        previousb = (Button) findViewById(R.id.previousb);

        nextb.setOnClickListener(this);
        previousb.setOnClickListener(this);


        nextsix = (Button) findViewById(R.id.nextsix);
        previoussix = (Button) findViewById(R.id.previoussix);

        nextsix.setOnClickListener(this);
        previoussix.setOnClickListener(this);









    }

    @Override
    public void onClick(View v) {



        if (v == next) {
            viewFlipper.showNext();

        } else if (v == previous) {
            finish();
        }

        if (v == nextone) {
            viewFlipper.showNext();

        } else if (v == previousone) {
            viewFlipper.showPrevious();
        }

        if (v == nexttwo) {
            viewFlipper.showNext();

        } else if (v == previoustwo) {
            viewFlipper.showPrevious();
        }

        if (v == nextthree) {
            viewFlipper.showNext();

        } else if (v == previousthree) {
            viewFlipper.showPrevious();
        }

        if (v == nextfour) {
            viewFlipper.showNext();

        } else if (v == previousfour) {
            viewFlipper.showPrevious();
        }

        if (v == nextfive) {
            viewFlipper.showNext();

        } else if (v == previousfive) {
            viewFlipper.showPrevious();
        }

        if (v == nextsix) {
            finish();

        } else if (v == previoussix) {
            finish();
        }





        if (v == nexta) {
            viewFlipper.showNext();

        } else if (v == previousa) {
            viewFlipper.showPrevious();
        }


        if (v == nextb) {
            viewFlipper.showNext();

        } else if (v == previousb) {
            viewFlipper.showPrevious();
        }






    }







}