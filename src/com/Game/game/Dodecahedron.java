package com.Game.game;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class Dodecahedron {
	private final String vertexShaderCode =
			"uniform mat4 u_MVPMatrix;" 
			+"attribute vec4 aPosition;"
			+"attribute vec4 aColor;"
			+"varying vec4 vColor;"
			+ "void main() {"
			+"vColor = aColor;"
			 + "   gl_Position = u_MVPMatrix * aPosition;  " 	
			 
			+ "}";

	private final String fragmentShaderCode = 
			"precision mediump float;"
			+"varying vec4 vColor;"
			+ "void main() {"
			+ "  gl_FragColor = vColor;" 
			+ "}";
	
	

	float []mViewMatrix =new float[16];
	float []mProjectionMatrix =new float[16];
	float []mModelMatrix = new float[16];
	float []mMVPMatrix = new float[16];
	
	
	private FloatBuffer vertexBuffer;
	private ShortBuffer drawListBuffer;
	private FloatBuffer colorBuffer;
	// number of coordinates per vertex in this array
	static final int COORDS_PER_VERTEX = 3;
	float coords1[] = { 
			1,1,1,
			1,1,-1,//1,1
			1,-1,1,
			-1,1,1,
			1,-1,-1,//1,4
			-1,1,-1,
			-1,-1,1,
			-1,-1,-1,
			
			0, 1/1.618f, 1/1.618f,
			0, -1/1.618f, -1/1.618f,//1,3
			0, -1/1.618f, 1/1.618f,
			0, 1/1.618f, -1/1.618f,//1,2
			
			 1/1.618f, 1/1.618f,0,
			 -1/1.618f, -1/1.618f,0,
			 -1/1.618f, 1/1.618f,0,
			 1/1.618f, -1/1.618f,0,
			 
			 	 1/1.618f, 0,1/1.618f, 
				 -1/1.618f, 0,-1/1.618f,
				 -1/1.618f, 0,1/1.618f,
				 1/1.618f, 0,-1/1.618f,
			
			
	};

	private short drawOrder1[] = {
					
	
						
			
	}; // order to draw vertices
	 	

	    

	    float coords[] = {
	        // front
	        -1, -1,  1,
	         1, -1,  1,
	        -1,  1,  1,
	        -1,  1,  1,
	         1, -1,  1,
	         1,  1,  1, 

	        // back
	        -1, -1, -1,
	        -1,  1, -1,
	         1, -1, -1,
	         1, -1, -1,
	        -1,  1, -1,
	         1,  1, -1,

	        // right (+x)
	         1, -1, -1, 
	         1,  1, -1, 
	         1, -1,  1, 
	         1, -1,  1, 
	         1,  1, -1, 
	         1,  1,  1, 

	        // left (-x)
	        -1, -1, -1, 
	        -1, -1,  1, 
	        -1,  1, -1, 
	        -1,  1, -1, 
	        -1, -1,  1, 
	        -1,  1,  1, 

	        // bottom
	        -1, -1, -1,
	         1, -1, -1,
	        -1, -1,  1,
	        -1, -1,  1,
	         1, -1, -1,
	         1, -1,  1,

	        // top
	        -1,  1, -1,
	        -1,  1,  1,
	         1,  1, -1,
	         1,  1, -1,
	        -1,  1,  1,
	         1,  1,  1,
	    };

	    short drawOrder[] = { 
	    	
		         
		         
		         
		        
		     
		     
	    		 30,31,32,
	    		 33,34,35,//back

	        24,25,26,
	        27,28,29,//bottom
	        
		     18,19,20,
		     21,22,23,//right
		     
		     12,13,14,
		     15,16,17,//left
		     
		     6, 7, 8,
	         9,10,11,//top
		     
		 	0, 1, 2, 
	         3, 4, 5,//front
		     
	        
	    };


	public Dodecahedron(float []color1, float[]color2, float []color3, float[]color4,float[]color5,float[]color6) {
		
		float color[] = {
		        color1[0], color1[1], color1[2], color1[3],
		        color1[0], color1[1], color1[2], color1[3],
		        color1[0], color1[1], color1[2], color1[3],
		        color1[0], color1[1], color1[2], color1[3],
		        color1[0], color1[1], color1[2], color1[3],
		        color1[0], color1[1], color1[2], color1[3],

		        color2[0], color2[1], color2[2], color2[3],
		        color2[0], color2[1], color2[2], color2[3],
		        color2[0], color2[1], color2[2], color2[3],
		        color2[0], color2[1], color2[2], color2[3],
		        color2[0], color2[1], color2[2], color2[3],
		        color2[0], color2[1], color2[2], color2[3],

		        color3[0], color3[1], color3[2], color3[3],
		        color3[0], color3[1], color3[2], color3[3],
		        color3[0], color3[1], color3[2], color3[3],
		        color3[0], color3[1], color3[2], color3[3],
		        color3[0], color3[1], color3[2], color3[3],
		        color3[0], color3[1], color3[2], color3[3],

		        color4[0], color4[1], color4[2], color4[3],
		        color4[0], color4[1], color4[2], color4[3],
		        color4[0], color4[1], color4[2], color4[3],
		        color4[0], color4[1], color4[2], color4[3],
		        color4[0], color4[1], color4[2], color4[3],
		        color4[0], color4[1], color4[2], color4[3],

		        color5[0], color5[1], color5[2], color5[3],
		        color5[0], color5[1], color5[2], color5[3],
		        color5[0], color5[1], color5[2], color5[3],
		        color5[0], color5[1], color5[2], color5[3],
		        color5[0], color5[1], color5[2], color5[3],
		        color5[0], color5[1], color5[2], color5[3],

		        color6[0], color6[1], color6[2], color6[3],
		        color6[0], color6[1], color6[2], color6[3],
		        color6[0], color6[1], color6[2], color6[3],
		        color6[0], color6[1], color6[2], color6[3],
		        color6[0], color6[1], color6[2], color6[3],
		        color6[0], color6[1], color6[2], color6[3],
		    };
		
		
		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bb = ByteBuffer.allocateDirect(
		// (# of coordinate values * 4 bytes per float)
				coords.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(coords);
		vertexBuffer.position(0);

		// initialize byte buffer for the draw list
		ByteBuffer dlb = ByteBuffer.allocateDirect(
		// (# of coordinate values * 2 bytes per short)
				drawOrder.length * 4);
		
		dlb.order(ByteOrder.nativeOrder());
		drawListBuffer = dlb.asShortBuffer();
		drawListBuffer.put(drawOrder);
		drawListBuffer.position(0);
		
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(color.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(color);
		colorBuffer.position(0);
		
		
		
	}
public Dodecahedron(float []color) {
		
		
		
		
		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bb = ByteBuffer.allocateDirect(
		// (# of coordinate values * 4 bytes per float)
				coords1.length * 4);
		bb.order(ByteOrder.nativeOrder());
		vertexBuffer = bb.asFloatBuffer();
		vertexBuffer.put(coords1);
		vertexBuffer.position(0);

		// initialize byte buffer for the draw list
		ByteBuffer dlb = ByteBuffer.allocateDirect(
		// (# of coordinate values * 2 bytes per short)
				drawOrder.length * 4);
		
		dlb.order(ByteOrder.nativeOrder());
		drawListBuffer = dlb.asShortBuffer();
		drawListBuffer.put(drawOrder1);
		drawListBuffer.position(0);
		
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(color.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(color);
		colorBuffer.position(0);
		
		
		
	}

	public void draw(float[] mProjectionMatrix, float[]mViewMatrix, float []mModelMatrix,float[]mTemporaryMatrix,
			float[]mCurrentRotation,float[]mAccumulatedRotation,
			float[]view
			) {

		MyGL20Renderer renderer = new MyGL20Renderer();

		float []mMVPMatrix = new float[16];
		int mProgram = renderer.createProgram(vertexShaderCode, fragmentShaderCode);

		// get handle to vertex shader's vPosition member
		int mPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
		
		// Enable a handle to the triangle vertices
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		
		// Prepare the triangle coordinate data
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, 3*4, vertexBuffer);
		


		// get handle to fragment shader's vColor member
		int mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
		
		// Set color for drawing the triangle
		GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_FLOAT, false,
				4 * 4, colorBuffer);
		GLES20.glEnableVertexAttribArray(mColorHandle);
		

		int mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVPMatrix");
		
		Matrix.setLookAtM( mViewMatrix, 0,   view[0],view[1],view[2],
				 view[3],view[4],view[5],
				 view[6],view[7],view[8]);
		
		
		Matrix.multiplyMM(mTemporaryMatrix, 0, mCurrentRotation, 0, mAccumulatedRotation, 0);
    	System.arraycopy(mTemporaryMatrix, 0, mAccumulatedRotation, 0, 16);
    	    	
        // Rotate the cube taking the overall rotation into account.     	
    	Matrix.multiplyMM(mTemporaryMatrix, 0, mViewMatrix, 0, mAccumulatedRotation, 0);
    	System.arraycopy(mTemporaryMatrix, 0, mViewMatrix, 0, 16);   
		
		
		Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
	        
	    Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

	    GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
	    GLES20.glEnable( GLES20.GL_DEPTH_TEST );
	    GLES20.glDepthFunc( GLES20.GL_LEQUAL );
	    GLES20.glDepthMask( true );
	     
	   
		// Draw the square
		GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length,
				GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

		// Disable vertex array
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}


	public void draw(float[] mProjectionMatrix, float[]mViewMatrix, float []mModelMatrix,
			float[]view
			) {

		MyGL20Renderer renderer = new MyGL20Renderer();

		float []mMVPMatrix = new float[16];
		int mProgram = renderer.createProgram(vertexShaderCode, fragmentShaderCode);

		// get handle to vertex shader's vPosition member
		int mPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
		
		// Enable a handle to the triangle vertices
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		
		// Prepare the triangle coordinate data
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, 3*4, vertexBuffer);
		


		// get handle to fragment shader's vColor member
		int mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
		
		// Set color for drawing the triangle
		GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_FLOAT, false,
				4 * 4, colorBuffer);
		GLES20.glEnableVertexAttribArray(mColorHandle);
		

		int mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVPMatrix");
		
		Matrix.setLookAtM( mViewMatrix, 0,   view[0],view[1],view[2],
				 view[3],view[4],view[5],
				 view[6],view[7],view[8]);
		
		
		
		
		Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
	        
	    Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

	    GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
	    
		// Draw the square
		GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length,
				GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

		// Disable vertex array
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}
}
