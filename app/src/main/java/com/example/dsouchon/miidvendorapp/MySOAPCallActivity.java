package com.example.dsouchon.miidvendorapp;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
    public class MySOAPCallActivity
    {
        //soaps
        public final String SOAP_ACTION = "http://tempuri.org/GetUserForTag";
        public final String SOAP_ACTIONLoginEventOrganiser = "http://tempuri.org/LoginEventOrganiser";
        public final String SOAP_ACTIONAllowDenyAccess = "http://tempuri.org/AllowDenyAccess";
        public final String SOAP_ACTIONTicketValidForEvent = "http://tempuri.org/TicketValidForEvent";
        public final String SOAP_ACTIONGetCurrentEvents = "http://tempuri.org/GetCurrentEvents";
        public final String SOAP_ACTIONGetEventImage = "http://tempuri.org/GetEventImage";

       //new soaps
       public final String SOA_RegisterDevice0 = "http://tempuri.org/RegisterDevice0";
        public final String SOA_VendorLogin1 = "http://tempuri.org/VendorLoginGetEvents1";
        public final String SOA_SetEvent2 = "http://tempuri.org/SetEvent2";
        public final String SOA_AddStaff3a = "http://tempuri.org/AddStaff3a";
        public final String SOA_GetStaff3c = "http://tempuri.org/GetStaff3c";
        public final String SOA_StartShift3b = "http://tempuri.org/StartShift3b";
        public final String SOA_StaffLogin4 = "http://tempuri.org/StaffLogin4";
        public final String SOA_ScanTagForPayment6 = "http://tempuri.org/ScanTagForPayment6";
        public final String SOA_PaymentResult7 = "http://tempuri.org/PaymentResult7";
        public final String SOA_ScanTagForPaymentRefund6 = "http://tempuri.org/ScanTagForPaymentRefund6";
        public final String SOA_PaymentResultRefund7 = "http://tempuri.org/PaymentResultRefund7";
        public final String SOA_CloseShift9 = "http://tempuri.org/CloseShift9";

        //webmethods
        public  final String GetUserForTag = "GetUserForTag";
        public  final String LoginEventOrganiser = "LoginEventOrganiser";
        public  final String AllowDenyAccess = "AllowDenyAccess";
        public  final String TicketValidForEvent = "TicketValidForEvent";
        public  final String GetCurrentEvents = "GetCurrentEvents";
        public  final String GetEventImage = "GetEventImage";

        //new webmethods
        public final String RegisterDevice0 = "RegisterDevice0";
        public  final String VendorLogin1 = "VendorLoginGetEvents1";
        public  final String SetEvent2 = "SetEvent2";
        public  final String AddStaff3a = "AddStaff3a";
        public  final String GetStaff3c = "GetStaff3c";
        public  final String StartShift3b = "StartShift3b";
        public  final String StaffLogin4 = "StaffLogin4";
        public  final String ScanTagForPayment6 = "ScanTagForPayment6";
        public  final String ScanTagForPaymentRefund6 = "ScanTagForPaymentRefund6";
        public  final String PaymentResult7 = "PaymentResult7";
        public  final String PaymentResultRefund7 = "PaymentResultRefund7";
        public  final String CloseShift9 = "CloseShift9";



        public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

        //public  final String SOAP_ADDRESS = "http://demo.miid.co.zw/miidwebservice.asmx";
        //public  final String SOAP_ADDRESS = "http://training.miid.co.zw/miidwebservice.asmx";
        public  final String SOAP_ADDRESS = "https://miid.co.za/miidwebservice.asmx";
       //public  final String SOAP_ADDRESS = "https://www.miid.co.za/miidwebservice.asmx";

        public MySOAPCallActivity()
        {
        }


        //New Methods

        //1. RegisterDevice0
        public String RegisterDevice0(String DeviceCode)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,RegisterDevice0);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("DeviceCode");
            pi.setValue(DeviceCode);
            pi.setType(String.class);
            request.addProperty(pi);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_RegisterDevice0, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }



        //1. VendorLogin1
        public String VendorLogin1(Integer VendorPin, String VendorCode)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,VendorLogin1);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("VendorCode");
            pi.setValue(VendorCode);
            pi.setType(String.class);
            request.addProperty(pi);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("VendorPin");
            pi2.setValue(VendorPin);
            pi2.setType(Integer.class);
            request.addProperty(pi2);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_VendorLogin1, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }


        //2 SetEvent2
        public String SetEvent2(Integer EventID, Integer VendorID, String DeviceCode)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,SetEvent2);

            PropertyInfo piE=new PropertyInfo();
            piE.setName("EventID");
            piE.setValue(EventID);
            piE.setType(Integer.class);
            request.addProperty(piE);

            PropertyInfo piV=new PropertyInfo();
            piV.setName("VendorID");
            piV.setValue(VendorID);
            piV.setType(Integer.class);
            request.addProperty(piV);

            PropertyInfo piD=new PropertyInfo();
            piD.setName("DeviceCode");
            piD.setValue(DeviceCode);
            piD.setType(String.class);
            request.addProperty(piD);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_SetEvent2, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }

        //1. AddStaff3a
        public String AddStaff3a(Integer VendorID, String StaffCode, Integer StaffPin, Integer EventID, Integer DeviceID, Boolean OverwritePin)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, AddStaff3a);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("VendorID");
            pi.setValue(VendorID);
            pi.setType(Integer.class);
            request.addProperty(pi);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("StaffCode");
            pi2.setValue(StaffCode);
            pi2.setType(String.class);
            request.addProperty(pi2);

            PropertyInfo piS=new PropertyInfo();
            piS.setName("StaffPin");
            piS.setValue(StaffPin);
            piS.setType(Integer.class);
            request.addProperty(piS);

            PropertyInfo piE=new PropertyInfo();
            piE.setName("EventID");
            piE.setValue(EventID);
            piE.setType(Integer.class);
            request.addProperty(piE);

            PropertyInfo piD=new PropertyInfo();
            piD.setName("DeviceID");
            piD.setValue(DeviceID);
            piD.setType(Integer.class);
            request.addProperty(piD);

            PropertyInfo pOverwritePin =new PropertyInfo();
            pOverwritePin.setName("OverwritePin");
            pOverwritePin.setValue(OverwritePin);
            pOverwritePin.setType(Boolean.class);
            request.addProperty(pOverwritePin);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_AddStaff3a, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }

        public String GetStaff3c(Integer VendorID, Integer EventID)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, GetStaff3c);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("VendorID");
            pi.setValue(VendorID);
            pi.setType(Integer.class);
            request.addProperty(pi);



            PropertyInfo piE=new PropertyInfo();
            piE.setName("EventID");
            piE.setValue(EventID);
            piE.setType(Integer.class);
            request.addProperty(piE);




            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_GetStaff3c, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }

        //1. StartShift3b
        public String StartShift3b(Integer CurrentVendorID, Integer CurrentDevicePin, String CurrentStaffCode, Integer EventID, String DeviceCode)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,StartShift3b);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("CurrentVendorID");
            pi.setValue(CurrentVendorID);
            pi.setType(Integer.class);
            request.addProperty(pi);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("CurrentDevicePin");
            pi2.setValue(CurrentDevicePin);
            pi2.setType(Integer.class);
            request.addProperty(pi2);

            PropertyInfo piCS=new PropertyInfo();
            piCS.setName("CurrentStaffCode");
            piCS.setValue(CurrentStaffCode);
            piCS.setType(String.class);
            request.addProperty(piCS);

            PropertyInfo piE=new PropertyInfo();
            piE.setName("EventID");
            piE.setValue(EventID);
            piE.setType(Integer.class);
            request.addProperty(piE);

            PropertyInfo piD=new PropertyInfo();
            piD.setName("DeviceCode");
            piD.setValue(DeviceCode);
            piD.setType(String.class);
            request.addProperty(piD);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_StartShift3b, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }

        //1. StaffLogin4
        public String StaffLogin4(String DeviceCode, Integer VendorID, Integer EventID, String StaffCode, Integer StaffPin, Integer ShiftNo)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,StaffLogin4);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("DeviceCode");
            pi.setValue(DeviceCode);
            pi.setType(String.class);
            request.addProperty(pi);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("VendorID");
            pi2.setValue(VendorID);
            pi2.setType(Integer.class);
            request.addProperty(pi2);

            PropertyInfo pi3=new PropertyInfo();
            pi3.setName("EventID");
            pi3.setValue(EventID);
            pi3.setType(Integer.class);
            request.addProperty(pi3);

            PropertyInfo pi4=new PropertyInfo();
            pi4.setName("StaffCode");
            pi4.setValue(StaffCode);
            pi4.setType(String.class);
            request.addProperty(pi4);

            PropertyInfo pi5=new PropertyInfo();
            pi5.setName("StaffPin");
            pi5.setValue(StaffPin);
            pi5.setType(Integer.class);
            request.addProperty(pi5);

            PropertyInfo pi6=new PropertyInfo();
            pi6.setName("ShiftNo");
            pi6.setValue(ShiftNo);
            pi6.setType(Integer.class);
            request.addProperty(pi6);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_StaffLogin4, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }

        //1. ScanTagForPayment6
        public String ScanTagForPayment6(String TagNumber)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,ScanTagForPayment6);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("TagNumber");
            pi.setValue(TagNumber);
            pi.setType(String.class);
            request.addProperty(pi);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_ScanTagForPayment6, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }

        //1. ScanTagForPayment6
        public String ScanTagForPaymentRefund6(String TagNumber)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,ScanTagForPaymentRefund6);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("TagNumber");
            pi.setValue(TagNumber);
            pi.setType(String.class);
            request.addProperty(pi);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_ScanTagForPaymentRefund6, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }


        //1. PaymentResult7
        public String PaymentResult7(Integer PinNumber, String TagNumber, Integer Amount, Integer EventID, Integer VendorID, String DeviceCode, Integer StaffID, Integer ShiftNo)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,PaymentResult7);


            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("PIN");
            pi2.setValue(PinNumber);
            pi2.setType(Integer.class);
            request.addProperty(pi2);

            PropertyInfo pi=new PropertyInfo();
            pi.setName("TagNumber");
            pi.setValue(TagNumber);
            pi.setType(String.class);
            request.addProperty(pi);

            PropertyInfo piA=new PropertyInfo();
            piA.setName("Amount");
            piA.setValue(Amount);
            piA.setType(Integer.class);
            request.addProperty(piA);

            PropertyInfo piE=new PropertyInfo();
            piE.setName("EventID");
            piE.setValue(EventID);
            piE.setType(Integer.class);
            request.addProperty(piE);

            PropertyInfo piV=new PropertyInfo();
            piV.setName("VendorID");
            piV.setValue(VendorID);
            piV.setType(Integer.class);
            request.addProperty(piV);

            PropertyInfo piD=new PropertyInfo();
            piD.setName("DeviceCode");
            piD.setValue(DeviceCode);
            piD.setType(String.class);
            request.addProperty(piD);

            PropertyInfo piSt=new PropertyInfo();
            piSt.setName("StaffID");
            piSt.setValue(StaffID);
            piSt.setType(Integer.class);
            request.addProperty(piSt);

            PropertyInfo piSht=new PropertyInfo();
            piSht.setName("ShiftNo");
            piSht.setValue(ShiftNo);
            piSht.setType(Integer.class);
            request.addProperty(piSht);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_PaymentResult7, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }


        //1. PaymentResult7
        public String PaymentResultRefund7(Integer PinNumber, String TagNumber, Integer Amount, Integer EventID, Integer VendorID, String DeviceCode, Integer StaffID, Integer ShiftNo)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,PaymentResultRefund7);


            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("PIN");
            pi2.setValue(PinNumber);
            pi2.setType(Integer.class);
            request.addProperty(pi2);

            PropertyInfo pi=new PropertyInfo();
            pi.setName("TagNumber");
            pi.setValue(TagNumber);
            pi.setType(String.class);
            request.addProperty(pi);

            PropertyInfo piA=new PropertyInfo();
            piA.setName("Amount");
            piA.setValue(Amount);
            piA.setType(Integer.class);
            request.addProperty(piA);

            PropertyInfo piE=new PropertyInfo();
            piE.setName("EventID");
            piE.setValue(EventID);
            piE.setType(Integer.class);
            request.addProperty(piE);

            PropertyInfo piV=new PropertyInfo();
            piV.setName("VendorID");
            piV.setValue(VendorID);
            piV.setType(Integer.class);
            request.addProperty(piV);

            PropertyInfo piD=new PropertyInfo();
            piD.setName("DeviceCode");
            piD.setValue(DeviceCode);
            piD.setType(String.class);
            request.addProperty(piD);

            PropertyInfo piSt=new PropertyInfo();
            piSt.setName("StaffID");
            piSt.setValue(StaffID);
            piSt.setType(Integer.class);
            request.addProperty(piSt);

            PropertyInfo piSht=new PropertyInfo();
            piSht.setName("ShiftNo");
            piSht.setValue(ShiftNo);
            piSht.setType(Integer.class);
            request.addProperty(piSht);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_PaymentResultRefund7, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }


        //1. CloseShift9
        public String CloseShift9(Integer VendorPin, String VendorCode, Integer VendorID, Integer EventID, String DeviceCode, Integer ShiftNo, Integer StaffID)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,CloseShift9);


            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("VendorPin");
            pi2.setValue(VendorPin);
            pi2.setType(Integer.class);
            request.addProperty(pi2);

            PropertyInfo pi=new PropertyInfo();
            pi.setName("VendorCode");
            pi.setValue(VendorCode);
            pi.setType(String.class);
            request.addProperty(pi);

            PropertyInfo piA=new PropertyInfo();
            piA.setName("VendorID");
            piA.setValue(VendorID);
            piA.setType(Integer.class);
            request.addProperty(piA);

            PropertyInfo piE=new PropertyInfo();
            piE.setName("EventID");
            piE.setValue(EventID);
            piE.setType(Integer.class);
            request.addProperty(piE);

            PropertyInfo piV=new PropertyInfo();
            piV.setName("DeviceCode");
            piV.setValue(DeviceCode);
            piV.setType(String.class);
            request.addProperty(piV);

            PropertyInfo piD=new PropertyInfo();
            piD.setName("ShiftNo");
            piD.setValue(ShiftNo);
            piD.setType(Integer.class);
            request.addProperty(piD);

            PropertyInfo piSt=new PropertyInfo();
            piSt.setName("StaffID");
            piSt.setValue(StaffID);
            piSt.setType(Integer.class);
            request.addProperty(piSt);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOA_CloseShift9, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();


        }



        //End New Methods

        public String Call(String TagNumber)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetUserForTag);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("TagNumber");
            pi.setValue(TagNumber);
            pi.setType(String.class);
            request.addProperty(pi);
            //pi=new PropertyInfo();
            //pi.setName("b");
            //pi.setValue(b);
            //pi.setType(Integer.class);
            //request.addProperty(pi);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOAP_ACTION, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();
        }

        public String Login(String UserName, String Password)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,LoginEventOrganiser);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("UserName");
            pi.setValue(UserName);
            pi.setType(String.class);
            request.addProperty(pi);

            PropertyInfo pi2 =new PropertyInfo();
            pi2.setName("Password");
            pi2.setValue(Password);
            pi2.setType(String.class);
            request.addProperty(pi2);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOAP_ACTIONLoginEventOrganiser, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();
        }

        public String GetCurrentEvents(String UserName, String Password)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetCurrentEvents);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("UserName");
            pi.setValue(UserName);
            pi.setType(String.class);
            request.addProperty(pi);

            PropertyInfo pi2 =new PropertyInfo();
            pi2.setName("Password");
            pi2.setValue(Password);
            pi2.setType(String.class);
            request.addProperty(pi2);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOAP_ACTIONGetCurrentEvents, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();
        }

        public String AllowDenyAccess(String TagNumber, String ReasonForBlocking, boolean Block, String EventName)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,AllowDenyAccess);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("TagNumber");
            pi.setValue(TagNumber);
            pi.setType(String.class);
            request.addProperty(pi);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("ReasonForBlocking");
            pi2.setValue(ReasonForBlocking);
            pi2.setType(String.class);
            request.addProperty(pi2);

            PropertyInfo pi3=new PropertyInfo();
            pi3.setName("Block");
            pi3.setValue(Block);
            pi3.setType(Boolean.class);
            request.addProperty(pi3);

            PropertyInfo pi4=new PropertyInfo();
            pi4.setName("EventName");
            pi4.setValue(EventName);
            pi4.setType(String.class);
            request.addProperty(pi4);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
            Object response=null;
            try
            {
                httpTransport.call(SOAP_ACTIONAllowDenyAccess, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();
        }
        public String TicketValidForEvent(String TagNumber, String EventName)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,TicketValidForEvent);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("TagNumber");
            pi.setValue(TagNumber);
            pi.setType(String.class);
            request.addProperty(pi);

            PropertyInfo pi2=new PropertyInfo();
            pi2.setName("EventName");
            pi2.setValue(EventName);
            pi2.setType(String.class);
            request.addProperty(pi2);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 14000);
            Object response=null;
            try
            {
                httpTransport.call(SOAP_ACTIONTicketValidForEvent, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();
        }


        public String GetEventImage( String EventName)
        {
            SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetEventImage);
            PropertyInfo pi=new PropertyInfo();
            pi.setName("EventName");
            pi.setValue(EventName);
            pi.setType(String.class);
            request.addProperty(pi);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 14000);
            Object response=null;
            try
            {
                httpTransport.call(SOAP_ACTIONGetEventImage, envelope);
                response = envelope.getResponse();
            }
            catch (Exception exception)
            {
                response=exception.toString();
            }
            return response.toString();
        }

    }


