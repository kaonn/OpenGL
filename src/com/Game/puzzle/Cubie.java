package com.Game.puzzle;


import android.opengl.Matrix;
import android.util.Log;

import com.Game.game.Polygon;

public class Cubie {
	
	float degree1=0,x1=1,y1=1,z1=1;
	float degree2=0,x2=1,y2=1,z2=1;
	float degree3=0,x3=1,y3=1,z3=1;
	
	float dx = 0,dy = 0,dz = 0;
	float dx1 = 0, dy1 = 0, dz1= 0;
	float dx2 = 0, dy2 = 0, dz2= 0;
	float dx3 = 0, dy3 = 0, dz3= 0;
	
public Cubie(){
	
}
public void draw(float[] mProjectionMatrix, float[]mViewMatrix, float []mModelMatrix,
		float[]view, float[]color1,float[]color2,float[]color3){
	Polygon p1 = new Polygon(4,color1);
	Polygon p2 = new Polygon(4,color2);
	Polygon p3 = new Polygon(4,color3);
	
	Matrix.setIdentityM(mModelMatrix, 0);
	Matrix.translateM(mModelMatrix, 0, 0, 1f, 0);
	Matrix.translateM(mModelMatrix, 0, dx, dy, dz);
	Matrix.translateM(mModelMatrix, 0, dx1, dy1, dz1);
	Matrix.rotateM(mModelMatrix, 0, 90, 1, 0, 0);
	Matrix.rotateM(mModelMatrix, 0, degree1, x1, y1, z1);
	
	p1.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, view);
	
	Matrix.setIdentityM(mModelMatrix, 0);
	Matrix.translateM(mModelMatrix, 0, 0, 0, 1);
	Matrix.translateM(mModelMatrix, 0, dx, dy, dz);
	Matrix.translateM(mModelMatrix, 0, dx2, dy2, dz2);
	Matrix.rotateM(mModelMatrix, 0, degree2, x2, y2, z2);
	p2.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, view);
	
	Matrix.setIdentityM(mModelMatrix, 0);
	Matrix.translateM(mModelMatrix, 0, -1, 0, 0);
	Matrix.translateM(mModelMatrix, 0, dx, dy, dz);
	Matrix.translateM(mModelMatrix, 0, dx3, dy3, dz3);
	Matrix.rotateM(mModelMatrix, 0, 90, 0, 1, 0);
	Matrix.rotateM(mModelMatrix, 0, degree3, x3, y3, z3);
	p3.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, view);
	
}
public void rotate(float degree,float x,float y,float z){
	if(z ==0&&y==0){
		//piece 1
		degree1 = 90;
		x1 = 1;
		y1 = 0;
		z1 = 0;
		dy1 = -0.5f;
		dz1 = -0.5f;
		
		//piece 2
		degree2 = 90;
		x2 = 1;
		y2 = 0;
		z2 = 0;
		dy2 = 1;
		dz2 = -0.5f;
	}
	else if(z ==0&&x==0){
				//piece 3
				degree1 = 90;
				x3 = 0;
				y3 = 1;
				z3 = 0;
				dx3 = 0.5f;
				dz3 = 0.5f;
				
				//piece 2
				degree2 = 90;
				x2 = 0;
				y2 = 1;
				z2 = 0;
				dx2 = 0.5f;
				dz2 = -0.5f;
	}
}
public void translate(float x,float y,float z){
	dx = x;
	dy = x;
	dz = x;
	
}

}
