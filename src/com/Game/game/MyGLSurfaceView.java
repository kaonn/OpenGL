package com.Game.game;



import android.content.Context;
import android.graphics.PointF;
import android.opengl.GLSurfaceView;
import android.util.FloatMath;
import android.view.MotionEvent;



class MyGLSurfaceView extends GLSurfaceView {
	MyGL20Renderer mRenderer= new MyGL20Renderer();
	float mPreviousX,mPreviousY,mDensity;
    public MyGLSurfaceView(Context context){
        super(context);

        // Set the Renderer for drawing on the GLSurfaceView
        
        setEGLContextClientVersion(2);
        setRenderer(mRenderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }	 
    public boolean onTouchEvent(MotionEvent event) 
	{
	
			boolean zoom;
			boolean ignoreLF;
			int action = event.getAction()&MotionEvent.ACTION_MASK;
			
			if(event.getPointerCount() ==2){
				zoom = true;
			}
			else zoom = false;
			
			if (event != null)
			{		
				float x = event.getX();
				float y = event.getY();
				
				if(zoom){
					switch(action){
					case MotionEvent.ACTION_POINTER_1_DOWN:
						
					
					
					
					}
					
					
					return true;
				}
				else{
				
			if (event.getAction() == MotionEvent.ACTION_MOVE)
				{
					if (mRenderer != null)
					{
						float deltaX = (x - mPreviousX) / 2f;
						float deltaY = (y - mPreviousY) / 2f;
						
						mRenderer.mDeltaX += deltaX;
						mRenderer.mDeltaY += deltaY;												
					}
				}	
				
				mPreviousX = x;
				mPreviousY = y;
				
				return true;
			}
			
			}
			
			
			
			else
			{
				return super.onTouchEvent(event);
			}
		
		
		
	}
    
    private float spacing(MotionEvent event) {
    	float x = event.getX(0) - event.getX(1);
    	float y = event.getY(0) - event.getY(1);
    	return FloatMath.sqrt(x * x + y * y);
    	}
    private void midPoint(PointF point, MotionEvent event) {
    	float x = event.getX(0) + event.getX(1);
    	float y = event.getY(0) + event.getY(1);
    	point.set(x / 2, y / 2);
    	}
    public void setRenderer(MyGL20Renderer renderer, float density) 
	{
		mRenderer = renderer;
		mDensity = density;
		super.setRenderer(renderer);
	}
}