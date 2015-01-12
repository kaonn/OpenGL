package com.Game.game;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

public class Triangle {

	private final String vertexShaderCode =
			"attribute vec4 vPosition;"
			+"attribute vec4 aColor;"
			+"varying vec4 vColor;"
			+ "void main() {"
			+"vColor = aColor;"
			+ "  gl_Position = vPosition;" 
			+ "}";

	private final String fragmentShaderCode = 
			"precision mediump float;"
			+"varying vec4 vColor;"
			+ "void main() {"
			+ "  gl_FragColor = vColor;" 
			+ "}";

	private FloatBuffer vertexBuffer;
	private FloatBuffer colorBuffer;

	MyGL20Renderer renderer = new MyGL20Renderer();

	// number of coordinates per vertex in this array
	static final int COORDS_PER_VERTEX = 3;
	final float triangleCoords[] = { // in counterclockwise order:
	0.0f, 0.622008459f, 0.0f, // top
			-0.5f, -0.311004243f, 0.0f, // bottom left
			0.5f, -0.311004243f, 0.0f // bottom right
	};

	// Set color with red, green, blue and alpha (opacity) values
	float color[] = { 1.0f, 0f, 0f, 1f,
			0f, 0f, 1f, 1f,
			0f, 1f, 0f, 1f,

	};

	public Triangle() {
		// initialize vertex byte buffer for shape coordinates
		ByteBuffer bb = ByteBuffer.allocateDirect(
		// (number of coordinate values * 4 bytes per float)
				triangleCoords.length * 4);
		// use the device hardware's native byte order
		bb.order(ByteOrder.nativeOrder());

		// create a floating point buffer from the ByteBuffer
		vertexBuffer = bb.asFloatBuffer();
		// add the coordinates to the FloatBuffer
		vertexBuffer.put(triangleCoords);
		// set the buffer to read the first coordinate
		vertexBuffer.position(0);

		// color buffer
		ByteBuffer cbb = ByteBuffer.allocateDirect(color.length * 4);
		cbb.order(ByteOrder.nativeOrder());
		colorBuffer = cbb.asFloatBuffer();
		colorBuffer.put(color);
		colorBuffer.position(0);

		// creates OpenGL ES program executables
	}

	public void draw() {

		int mProgram = renderer.createProgram(vertexShaderCode,
				fragmentShaderCode);
		
		
		
		int mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
		GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
				GLES20.GL_FLOAT, false, 0, vertexBuffer);
		GLES20.glEnableVertexAttribArray(mPositionHandle);
		

		int mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
		GLES20.glVertexAttribPointer(mColorHandle, 4, GLES20.GL_FLOAT, false,
				4 * 4, colorBuffer);
		GLES20.glEnableVertexAttribArray(mColorHandle);
		

		// GLES20.glUniform4fv(mColorHandle, 1, color, 0);

		// Draw the triangle
		GLES20.glDrawArrays(GLES20.GL_POINTS, 0, 3);

		// Disable vertex array
		GLES20.glDisableVertexAttribArray(mPositionHandle);
	}
}