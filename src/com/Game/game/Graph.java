package com.Game.game;

import java.util.Arrays;

import android.util.Log;

public class Graph {
	  float []coords = new float[600];
float []color = new float[800];
public Graph(){
	
}
public void draw(float[] mProjectionMatrix, float[]mViewMatrix, float []mModelMatrix,
		float[]view){
	
	float z;
	int count1=0,count2=0;
	
	for(float i = 0;i<1;i+=.1){
		
			z = i*i;
			coords[count1]=i;
			coords[count1+1]=0;
			coords[count1+2]=z;
			count1+=3;
			color[count2] = 1;
			color[count2+1] = 0;
			color[count2+2] = 0;
			color[count2+3] = 1;
			count2+=4;
		
		Log.v("math", Arrays.toString(coords));
		Point p = new Point(coords, color);
		p.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, view);
	}
}
}
