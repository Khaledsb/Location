package com.example.khaledsb.location;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {

	public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
		double earthRadius = 6371000; //meters
		double dLat = Math.toRadians(lat2-lat1);
		double dLng = Math.toRadians(lng2-lng1);
		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
						Math.sin(dLng/2) * Math.sin(dLng/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		float dist = (float) (earthRadius * c);

		return dist;
	}
	
	Button btnShowLocation;
	
	// GPSTracker class
	GPSTracker gps;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {		
				// create class object
		        gps = new GPSTracker(AndroidGPSTrackingActivity.this);

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();

					double Distance;
					Distance = distFrom((float) 36.5925907, 2.9051544f, 36.5805505f, 2.914749f);

					// \n is for new line
		        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude+" " +
							"  diastance "+Distance, Toast.LENGTH_LONG).show();
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	gps.showSettingsAlert();
		        }
				
			}
		});
    }
    
}