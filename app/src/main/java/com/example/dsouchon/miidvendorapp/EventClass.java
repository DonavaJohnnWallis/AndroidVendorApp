package com.example.dsouchon.miidvendorapp;

/**
 * Created by dsouchon on 12/4/2015.
 */

public class    EventClass {
    // Okay, full acknowledgment that public members are not a good idea, however
    // this is a Spinner demo not an exercise in java best practices.
    public Integer id = 0;
    public String name = "";
    public String abbrev = "";

    // A simple constructor for populating our member variables for this tutorial.
    public EventClass( Integer _id, String _name )
    {
        id = _id;
        name = _name;
        //abbrev = _abbrev;
    }

    // The toString method is extremely important to making this class work with a Spinner
    // (or ListView) object because this is the method called when it is trying to represent
    // this object within the control.  If you do not have a toString() method, you WILL
    // get an exception.
    public String toString()
    {
        return( name );//+ " (" + abbrev + ")" );
    }
}
