package com.lingavin.gplayer;

import java.lang.ref.WeakReference;

import android.os.Build;
import android.view.Surface;
import android.view.SurfaceHolder;

public class MediaPlayer{
	
	private int mNativeContext;
	private SurfaceHolder mSurfaceHolder;
	private Surface mSurface;
	
	static{
		/**注意调用关系*/
		System.loadLibrary("ffmpeg");
		System.loadLibrary("SDL");
		System.loadLibrary("mediaplayer");
		System.loadLibrary("mediaplayer_jni");
		nativeInit();
	}

	public MediaPlayer(){
		nativeSetup();
	}
	
	public native void setDataSource(String path);
	private native void setVideoSurface(Surface surface);
	private native void nativePrepare(int sdkVersion);
	private static native final void nativeInit();
	private native void nativeSetup();
	private native void nativeStart();
	private native void nativeSuspend();
//	private native int nativeSuspendOrResume(boolean isSuspend);
	private native boolean nativeIsPlaying();
	
	public void setPath(String mFilePath) {
		setDataSource(mFilePath);
	}

	public void start() {
		
		nativeStart();
	}

	public void setDisplay(SurfaceHolder holder) {
		mSurfaceHolder = holder;
		if(mSurfaceHolder != null){
			mSurface = mSurfaceHolder.getSurface();
		}else{
			mSurface = null;
		}
		setVideoSurface(mSurface);
	}

	public void prepare() {
		nativePrepare(Integer.parseInt(Build.VERSION.SDK));
	}

	public void suspend() {
		nativeSuspend();
	}

	public void resume() {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public boolean isPlaying() {
		return nativeIsPlaying();
	}

	public int getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

}
