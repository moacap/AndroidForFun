package com.androidforfun;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Get the location of the mobile user and display its latitude and longitude
 */
public class LocalisationTaker extends Activity implements LocationListener
{
	private LocationManager lManager;
	private EditText edit_lat, edit_long;
	
    @Override
	protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localisation_taker);

        // Retrieve the two text editors from the GUI
        edit_lat = (EditText) findViewById(R.id.edit_lat);
        edit_long = (EditText) findViewById(R.id.edit_long);
        
        // Instanciate the LocationManager
        lManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        // Get the list of all the location providers available
        List <String> providers = lManager.getProviders(true);

        // For each of them
		for(String provider : providers)
		{
			// Try to locate the user every minute
			lManager.requestLocationUpdates(provider, 60000, 0, this);
		}
		
		// Open a progress bar
		setProgressBarIndeterminateVisibility(true);
    }

    /**
     * The location has been found: displays the latitude and longitude and stop updating the location 
     */
	@Override
	public void onLocationChanged(Location location)
	{
		// Stop the progress bar
		setProgressBarIndeterminateVisibility(false);

		// Display the latitude and longitude
		edit_lat.setText(String.valueOf(location.getLatitude()));
		edit_long.setText(String.valueOf(location.getLongitude()));

		// Stop the user localisation
		lManager.removeUpdates(this);
	}

	/**
	 * Found that a provider is disabled
	 */
	@Override
	public void onProviderDisabled(String provider)
	{
		// Display a message to the user
		Toast.makeText(this,
			String.format("Impossible to get your location from \"%s\"", provider),
			Toast.LENGTH_SHORT).show();

		// Stop trying to get the location
		lManager.removeUpdates(this);

		// Remove the progress bar
		setProgressBarIndeterminateVisibility(false);
	}

	@Override
	public void onProviderEnabled(String arg0) 
	{
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2)
	{
	}
}
