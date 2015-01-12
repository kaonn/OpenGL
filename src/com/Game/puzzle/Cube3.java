package com.Game.puzzle;


import android.opengl.Matrix;
import android.util.Log;
import android.widget.Toast;

import com.Game.game.*;

public class Cube3 {
	
	float []white = {
		1,1,1,1,	
	};
	float []blue= {
			0,0,1,1,
	
		};
	float []red = {
			1,0,0,1,

		};
	float []green = {
			0,1,0,1,
		};
	float []yellow = {
			0,1,1,1,
		};
	float []orange = {
			0.5f,0.5f,0.5f,1,
		};
	float []clear = {
			0,0,0,0,
		};

	Cube c1 = new Cube(white,green,orange,red,yellow, blue);
public Cube3(){
	
}
public void draw(float[] mProjectionMatrix, float[]mViewMatrix, float []mModelMatrix,float[]mTemporaryMatrix,
		float[]mCurrentRotation,float[]mAccumulatedRotation,
		float[]view){
	Matrix.translateM(mModelMatrix, 0, -2, 2, 2);
	c1.draw(mProjectionMatrix, mViewMatrix, mModelMatrix,mTemporaryMatrix,mCurrentRotation,mAccumulatedRotation, view);
	Matrix.translateM(mModelMatrix, 0, -2, 2, 2);
	c1.draw(mProjectionMatrix, mViewMatrix, mModelMatrix,mTemporaryMatrix,mCurrentRotation,mAccumulatedRotation, view);
	
	
	
}
public void rotateLayer(int layer){
	
}
}
