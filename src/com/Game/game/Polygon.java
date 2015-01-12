package com.Game.game;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

public class Polygon {
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
	float angle;

	 // order to draw vertices
	short drawOrder[];
	public Polygon(int order, float []color){
		angle = (180-(360/order))/2;
		float coords[] = new float[(order+1)*3];
		drawOrder = new short[order+2];
		float angle = (float) (2*Math.PI/order);
		coords[0]=0;
		coords[1]=0;
		coords[2]=0;
		
		int count = 0;
		
		for(int i = 3;i<(order+1)*3;i+=3){
			coords[i] = (float) Math.cos(angle*count);
			coords[i+1] = (float) Math.sin(angle*count);
			coords[i+2] = 0;
			count++;
		}
		for(short i = 0;i<=order;i++){
			drawOrder[i] = i;
		}
		drawOrder[order+1]=1;
		
	
		
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

	public Polygon(int order) {
		angle = (180-(360/order))/2;
		
		float color[] = new float[(order+1)*4];
		float coords[] = new float[(order+1)*3];
		drawOrder = new short[order+2];
		float angle = (float) (2*Math.PI/order);
		coords[0]=0;
		coords[1]=0;
		coords[2]=0;
		Random R = new Random();
		float r,g,b,a;
		for(int i = 0;i<(order+1)*4;i+=4){
			r = R.nextFloat();
			g = R.nextFloat();
			b = R.nextFloat();
			a = R.nextFloat();
			color[i] = r;
			color[i+1] = g;
			color[i+2] = b;
			color[i+3] = a;
			
		}
		int count = 0;
		
		for(int i = 3;i<(order+1)*3;i+=3){
			coords[i] = (float) Math.cos(angle*count);
			coords[i+1] = (float) Math.sin(angle*count);
			coords[i+2] = 0;
			count++;
		}
		for(short i = 0;i<=order;i++){
			drawOrder[i] = i;
		}
		drawOrder[order+1]=1;
		
		
		
		
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
		
		Matrix.rotateM(mModelMatrix, 0, angle, 0, 0, 1);
		
		Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
	        
	    Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0);

	    GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);
	    
		// Draw the square
		GLES20.glDrawElements(GLES20.GL_TRIANGLE_FAN, drawOrder.length,
				GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

		// Disable vertex array
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}
}
