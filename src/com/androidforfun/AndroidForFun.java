package com.androidforfun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Home activity of the android application.
 * Let the user choose between taking a picture or retrieving its GPS location.
 */
public class AndroidForFun extends Activity implements OnClickListener
{
	private Button picture_button;
	private Button localisation_button;

    @Override
	protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_for_fun);

        // Retrieve the buttons from the GUI
        picture_button = (Button) findViewById(R.id.picture_button);
        localisation_button = (Button) findViewById(R.id.localisation_button);
        
        // Set a click listener on each button
        picture_button.setOnClickListener(this);
        localisation_button.setOnClickListener(this);
    }

    /**
     * When a click is handled on one of the buttons
     */
	@Override
	public void onClick(View v)
	{
        Intent i = null;
        
        // Depending on the button, do an action or another
		switch (v.getId())
		{
			case R.id.picture_button:
				// The "picture" button has been clicked, let's go to the PictureTaker activity.
		        i = new Intent(this, PictureTaker.class);
		        this.startActivityForResult(i, 0);
				break;
				
			case R.id.localisation_button:
				// The "localisation" button has been clicked, let's got to the LocalisationTaker activity
		        i = new Intent(this, LocalisationTaker.class);
		        this.startActivity(i);
				break;
				
			default:
				break;
		}
	}

	/**
	 * Result returned by the PictureTaker activity.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK)
		{
			byte[] picture = data.getByteArrayExtra("picture");
			System.out.println("Picture: " + picture.toString());
		}
	}
}
