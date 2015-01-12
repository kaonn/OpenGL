package com.Game.game;

import android.app.Activity;
import android.os.Bundle;

public class CubeActivity extends Activity{
	private MyGLSurfaceView mGLView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		mGLView = new MyGLSurfaceView(this);
		super.onCreate(savedInstanceState);
		setContentView(mGLView);
		
	}

}
