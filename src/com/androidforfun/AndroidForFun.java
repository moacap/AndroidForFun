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
	private static final int PICTURE_1 = 0, PICTURE_2 = 1, VIDEO_1 = 2;
	
	private Button picture_1_button, localisation_button, picture_2_button, video_1_button;

    @Override
	protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_for_fun);

        // Retrieve the buttons from the GUI
        picture_1_button = (Button) findViewById(R.id.picture_1_button);
        localisation_button = (Button) findViewById(R.id.localisation_button);
        picture_2_button = (Button) findViewById(R.id.picture_2_button);
        video_1_button = (Button) findViewById(R.id.video_1_button);
        
        // Set a click listener on each button
        picture_1_button.setOnClickListener(this);
        localisation_button.setOnClickListener(this);
        picture_2_button.setOnClickListener(this);
        video_1_button.setOnClickListener(this);
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
			case R.id.picture_1_button:
				// The "picture_1" button has been clicked, let's go to the PictureTaker activity.
		        i = new Intent(this, PictureTaker.class);
		        this.startActivityForResult(i, AndroidForFun.PICTURE_1);
				break;
				
			case R.id.localisation_button:
				// The "localisation" button has been clicked, let's got to the LocalisationTaker activity
		        i = new Intent(this, LocalisationTaker.class);
		        this.startActivity(i);
				break;
				
			case R.id.picture_2_button:
				// The "picture_2" button has been clicked, let's launch the standard Intent action
				// to have the camera application capture an image and return it.
		        i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		        this.startActivityForResult(i, AndroidForFun.PICTURE_2);
				break;
				
			case R.id.video_1_button:
				// The "video_1" button has been clicked, let's launch the standard Intent action
				// to have the camera application capture a video and return it.
		        i = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
		        this.startActivityForResult(i, AndroidForFun.VIDEO_1);
				break;
				
			default:
				break;
		}
	}

	/**
	 * Result returned by the PictureTaker or the camera application activity intent.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode)
		{
			case AndroidForFun.PICTURE_1:
				if (resultCode == RESULT_OK)
				{
					byte[] picture = data.getByteArrayExtra("picture");
					System.out.println("Picture Data 1: " + picture.toString());
				}
				break;
			
			case AndroidForFun.PICTURE_2:
				if (resultCode == RESULT_OK)
				{
					System.out.println("Picture Data: 2" + data.getData().toString());
				}
				break;
			
			case AndroidForFun.VIDEO_1:
				if (resultCode == RESULT_OK)
				{
					System.out.println("Video Data: " + data.getData().toString());
				}
				break;
				
			default:
				break;
		}
	}
}
