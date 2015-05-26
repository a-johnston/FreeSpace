package lambda.freespace;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.TextureView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Joshua on 5/25/2015.
 */
public class CameraInput implements TextureView.SurfaceTextureListener {
    SurfaceTexture mSurfaceTexture;
    Camera mCamera;
    Bitmap mBitmap;
    TextureView mTextureView;
    ArrayList<Bitmap> inputImages = new ArrayList<Bitmap>();
    ArrayList<Bitmap> outputImages = new ArrayList<Bitmap>();

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.i("cam", "AVAILABLE");
        mCamera = Camera.open();
        try {
            mCamera.setPreviewTexture(surface);
            mCamera.startPreview();
            mBitmap = mTextureView.getBitmap();
        } catch (IOException ioe) {
            System.out.println("Camera Preview Failed :(");
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        //don't need to worry about this
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        mSurfaceTexture = getSurfaceTexture();
        //inputImages.add(mTextureView.getBitmap());
        mBitmap = mTextureView.getBitmap();
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface){
        mCamera.stopPreview();
        mCamera.release();
        return true;
    }

    public SurfaceTexture getSurfaceTexture(){
        return mSurfaceTexture;
    }
}

