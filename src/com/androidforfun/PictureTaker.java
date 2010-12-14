package com.androidforfun;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Launch a camera preview, take a picture and return it to the caller activity. 
 */
public class PictureTaker extends Activity implements OnClickListener
{
    private Preview cameraPreview;

    @Override
	protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Create the preview and add a click listener to take the snapshot
        cameraPreview = new Preview(this);
        cameraPreview.setOnClickListener(this);
        
        // Set the camera preview as the main view
        setContentView(cameraPreview);
    }

    /**
     * Picture callback that will return the image to the caller activity when
     * the snapshop will be taken
     */
    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback()
    {
		public void onPictureTaken(byte[] imageData, Camera c)
		{
			if (imageData != null)
			{
				PictureTaker.this.getIntent().putExtra("picture", imageData);
				setResult(RESULT_OK, PictureTaker.this.getIntent());
			}
		}
	};

	/**
	 * Take a picture when the user clicks on the screen
	 */
	@Override
	public void onClick(View v)
	{
		cameraPreview.getCamera().takePicture(null, mPictureCallback, mPictureCallback);
	}
}