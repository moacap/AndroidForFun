package com.androidforfun;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class Preview extends SurfaceView implements SurfaceHolder.Callback
{
    SurfaceHolder surfaceHolder;
    Camera camera;

    Preview(Context context)
    {
        super(context);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        camera = Camera.open();
        try
        {
           camera.setPreviewDisplay(holder);
        }
        catch (IOException exception)
        {
            camera.release();
            camera = null;
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h)
    {
        Camera.Parameters parameters = camera.getParameters();

        List<Size> sizes = parameters.getSupportedPreviewSizes();
        parameters.setPreviewSize(sizes.get(0).width, sizes.get(0).height);

        camera.setParameters(parameters);
        camera.startPreview();
    }
    
    /**
     * Return the camera object of the camera preview
     */
    public Camera getCamera()
    {
    	return camera;
    }
}