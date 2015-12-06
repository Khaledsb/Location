package com.example.khaledsb.location;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener{
    LocationManager lm;
    TextView lt, ln;
    String provider;
    Location l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ln=(TextView)findViewById(R.id.lng);
        lt=(TextView)findViewById(R.id.lat);
        //get location service
        lm=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        Criteria c=new Criteria();
        //criteria object will select best service based on
        //Accuracy, power consumption, response, bearing and monetary cost
        //set false to use best service otherwise it will select the default Sim network
        //and give the location based on sim network
        //now it will first check satellite than Internet than Sim network location
        provider=lm.getBestProvider(c, false);
        //now you have best provider
        //get location
        l=lm.getLastKnownLocation(provider);
        if(l!=null)
        {
            //get latitude and longitude of the location
            double lng=l.getLongitude();
            double lat=l.getLatitude();
            //display on text view
            ln.setText(""+lng);
            lt.setText(""+lat);
        }
        else
        {
            ln.setText("No Provider");
            lt.setText("No Provider");
        }
    }
    //If you want location on changing place also than use below method
    //otherwise remove all below methods and don't implement location listener
    @Override
    public void onLocationChanged(Location arg0)
    {
        double lng=l.getLongitude();
        double lat=l.getLatitude();
        ln.setText(""+lng);
        lt.setText(""+lat);
    }

    @Override
    public void onProviderDisabled(String arg0) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub
    }
}