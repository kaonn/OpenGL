package com.Game.game;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;







import com.Game.puzzle.Cube3;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

public class MyGL20Renderer  implements GLSurfaceView.Renderer {
	private Variables v = new Variables();
	float []mViewMatrix =new float[16];
	float []mProjectionMatrix =new float[16];
	float []mModelMatrix = new float[16];
	float []mMVPMatrix = new float[16];
	float []mTemporaryMatrix = new float[16];
	float []mCurrentRotation = new float[16];
	float []mAccumulatedRotation = new float[16];
	
	public volatile float  mDeltaX,mDeltaY,mScale=1;
	
	float[]polyGround = { -2f, 2f, 0.0f, // top left
			-2f, -2f, 0.0f, // bottom left
			2f, -2f, 0.0f, // bottom right
			2f, 2f, 0.0f }; // top right
	short[]drawOrder = { 0, 1, 2, 0, 2, 3 };
	float[]pColor =  {
			1.0f,0f,0f,1f,
			0f,0f,1f,1f,
			0f,1f,0f,1f,
			1f,0f,1f,1f		
	};
	float[]viewPolygon = {
			0,0,2,
			0,0,-5,
			0,1,0
	};
	float[]viewCube = {
			0,0,5,
			0,0,-5,
			0,1,0
	};
	float[]viewGraphAxis = {
			10f,10f,10f,
			0,0,0,
			0,1,0
	};
	
	float[]cColor = {
			1,1,1,1,
			0,0.5f,0.5f,1,
			0.5f,1,0.5f,1,
			1,1,0,1,
			1,0,1,1,
			1,0,0.5f,1,
			0.5f,0.5f,0.5f,1,
			0,1,1,1,
			
			1,1,1,1,
			0,0.5f,0.5f,1,
			0.5f,1,0.5f,1,
			1,1,0,1,
			1,0,1,1,
			1,0,0.5f,1,
			0.5f,0.5f,0.5f,1,
			0,1,1,1,
			
			1,1,1,1,
			0,0.5f,0.5f,1,
			0.5f,1,0.5f,1,
			1,1,0,1,
			1,0,1,1,
			1,0,0.5f,1,
			0.5f,0.5f,0.5f,1,
			0,1,1,1,
			
			1,1,1,1,
			0,0.5f,0.5f,1,
			0.5f,1,0.5f,1,
			1,1,0,1,
			1,0,1,1,
			1,0,0.5f,1,
			0.5f,0.5f,0.5f,1,
			0,1,1,1,
			
			1,1,1,1,
			0,0.5f,0.5f,1,
			0.5f,1,0.5f,1,
			1,1,0,1,
			1,0,1,1,
			1,0,0.5f,1,
			0.5f,0.5f,0.5f,1,
			0,1,1,1,
			
	};

	
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0f, 0f, 0f, 01f);
        Matrix.setIdentityM(mAccumulatedRotation, 0);
    }
    
    Graph g = new Graph();
    
    float []coords = {
    				1,1,0,
    				0,1.5f,0
    				
    				};
    
    float []colorTetrahedron = {1,0,0,0.6f,
    							0,1,0,.6f,
    							1,1,0,.6f,
    							0,0,1,.6f,
    			};
    float []colorOctahedron = {
    		
			
			1,1,0,.6f,
			0,0,1,.6f,
			0,1,1,.6f,
			1,0,1,0.6f,
			1,0,0,0.6f,
			0,1,0,.6f,
};
    
    float []GraphAxisCoords = {
			0,0,1000,
			0,0,-1000,
			1000,0,0,
			-1000,0,0,
			0,1000,0,
			0,-1000,0,
			};
    float []GraphAxisColor = {
			1,1,1,1,
			1,1,1,1,
			1,1,1,1,
			1,1,1,1,
			1,1,1,1,
			1,1,1,1,
			};
    //Point point = new Point(coords, color);
    Cube3 rc = new Cube3();

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
	
	Polygon p = new Polygon(9);
    @Override
	

	public void onDrawFrame(GL10 unused) {
        // Redraw background color
    	  GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    	
        
       Triangle t = new Triangle(); 
        
        Cube c = new Cube(white,green,orange,red,yellow, blue);
       Tetrahedron T = new Tetrahedron(colorTetrahedron);
       Octahedron O = new Octahedron(colorOctahedron);
        Line line = new Line (GraphAxisCoords, GraphAxisColor);
        
        long time = SystemClock.uptimeMillis() % 10000L;
        float angleInDegrees = (36f / 1000.0f) * ((int) time);
       
       
      
        
      
       if(Variables.mode == 0){
    	   
    	  
    	   
    	   Matrix.setIdentityM(mModelMatrix, 0);
           Matrix.translateM(mModelMatrix, 0, 0, .1f, 0);
           Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 0, 0, 1f);
           p.draw(mProjectionMatrix, mViewMatrix, mModelMatrix,viewPolygon);
           
       }
       else if(Variables.mode == 4){
    	   Matrix.setIdentityM(mModelMatrix, 0);
           Matrix.translateM(mModelMatrix, 0, 0, 0f, 0);
           //Matrix.scaleM(mModelMatrix, 0, 2, 2, 2);
           //Matrix.scaleM(mModelMatrix, 0, mScale, mScale, mScale);
           
           Matrix.setIdentityM(mCurrentRotation, 0);   
           Matrix.rotateM(mCurrentRotation, 0, mDeltaX, 0f, 1f, 0f);
           Matrix.rotateM(mCurrentRotation, 0, mDeltaY, 1f, 0f, 0f);
    	   c.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, mTemporaryMatrix, mCurrentRotation, mAccumulatedRotation, viewCube);
    	   
    	   Matrix.translateM(mModelMatrix, 0, 1.5f, 0, 0);
    	   c.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, mTemporaryMatrix, mCurrentRotation, mAccumulatedRotation, viewCube);
    	   mDeltaX = 0;
    	   mDeltaY = 0;
       }
       else if(Variables.mode == 1){
    	  
    	   
    	   Matrix.setIdentityM(mModelMatrix, 0);
          // Matrix.translateM(mModelMatrix, 0, 0, 1f, 0);
          // Matrix.rotateM(mModelMatrix, 0, angleInDegrees, 0.6f, 0.4f, .5f);
    	  Matrix.scaleM(mModelMatrix, 0, 1000, 1000, 1000);
    	   line.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, viewGraphAxis);
    	   g.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, viewGraphAxis);
    	   //point.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, viewGraphAxis);
       }
 else if(Variables.mode == 2){
    	  
    	   
    	   Matrix.setIdentityM(mModelMatrix, 0);
          // Matrix.translateM(mModelMatrix, 0, 0, 1f, 0);
    	   Matrix.setIdentityM(mCurrentRotation, 0);   
           Matrix.rotateM(mCurrentRotation, 0, mDeltaX, 0f, 1f, 0f);
           Matrix.rotateM(mCurrentRotation, 0, mDeltaY, 1f, 0f, 0f);
    	   
           rc.draw(mProjectionMatrix, mViewMatrix, mModelMatrix,mTemporaryMatrix, mCurrentRotation, mAccumulatedRotation, viewCube);
           
           rc.draw(mProjectionMatrix, mViewMatrix, mModelMatrix,mTemporaryMatrix, mCurrentRotation, mAccumulatedRotation, viewCube);
           mDeltaX = 0;
    	   mDeltaY = 0;
       }
 else if(Variables.mode == 3){
	  
	   
	   Matrix.setIdentityM(mModelMatrix, 0);
    // Matrix.translateM(mModelMatrix, 0, 0, 1f, 0);
	   Matrix.setIdentityM(mCurrentRotation, 0);   
	   Matrix.scaleM(mModelMatrix, 0, 2, 2, 2);
     Matrix.rotateM(mCurrentRotation, 0, mDeltaX, 0f, 1f, 0f);
     Matrix.rotateM(mCurrentRotation, 0, mDeltaY, 1f, 0f, 0f);
     T.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, mTemporaryMatrix, mCurrentRotation, mAccumulatedRotation, viewCube);
	   
     
     mDeltaX = 0;
	   mDeltaY = 0;
 }
 else if(Variables.mode == 5){
	  
	   
	   Matrix.setIdentityM(mModelMatrix, 0);
  // Matrix.translateM(mModelMatrix, 0, 0, 1f, 0);
	   Matrix.setIdentityM(mCurrentRotation, 0);   
	   Matrix.scaleM(mModelMatrix, 0, 2, 2, 2);
   Matrix.rotateM(mCurrentRotation, 0, mDeltaX, 0f, 1f, 0f);
   Matrix.rotateM(mCurrentRotation, 0, mDeltaY, 1f, 0f, 0f);
   O.draw(mProjectionMatrix, mViewMatrix, mModelMatrix, mTemporaryMatrix, mCurrentRotation, mAccumulatedRotation, viewCube);
	   
   
   mDeltaX = 0;
	   mDeltaY = 0;
}
    }
    

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        final float ratio = (float) width / height;
		final float left = -ratio;
		final float right = ratio;
		final float bottom = -1f;
		final float top = 1f;
		final float near = 1f;
		final float far = 1000.0f;
		
		Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
		mProjectionMatrix[8] = mProjectionMatrix[8]/2f;
      
        
    }

    public int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        
        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
    public int  createProgram(String vertexShaderCode, String fragmentShaderCode){
    	  int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
          int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
          
          int mProgram = GLES20.glCreateProgram();             // create empty OpenGL ES Program
         
          GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
          GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
          GLES20.glLinkProgram(mProgram);     
          // Add program to OpenGL ES environment
      	
          GLES20.glUseProgram(mProgram);
    	return mProgram;
    }

}

