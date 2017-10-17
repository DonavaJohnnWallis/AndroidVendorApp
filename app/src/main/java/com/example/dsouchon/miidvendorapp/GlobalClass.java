package com.example.dsouchon.miidvendorapp;

/**
 * Created by dsouchon on 9/10/2015.
 */
import android.app.Application;

public class GlobalClass extends Application{

    private String eventName;
    private String eventImage;
    private String loggedInUserName;
    private Boolean isUserLoggedIn;

    public Boolean getIsUserLoggedIn() {

        if(isUserLoggedIn == null){isUserLoggedIn = false;}
        return isUserLoggedIn;
    }

    public void setIsUserLoggedIn(Boolean aIsUserLoggedIn) {

        isUserLoggedIn = aIsUserLoggedIn;

    }


    public String getEventName() {

        return eventName;
    }

    public void setEventName(String aName) {

        eventName = aName;

    }

    public String getEventImage() {

        return eventImage;
    }

    public void setEventImage(String aEventImage) {

        eventImage = aEventImage;
    }

    public String getLoggedInUserName() {

        return eventImage;
    }

    public void setLoggedInUserName(String aLoggedInUserName) {

        loggedInUserName = aLoggedInUserName;
    }

}
