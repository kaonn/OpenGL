package com.Game.game;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.Game.game.util.SystemUiHider;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Program;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;
	
	
	private MyGLSurfaceView mGLView;
	List<Map<String, String>> itemList = new ArrayList<Map<String,String>>();
	private Variables v = new Variables(); 
	
	private MyGL20Renderer renderer = new MyGL20Renderer();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		   setContentView(R.layout.activity_fullscreen);
		   mGLView = new MyGLSurfaceView(this);
		  
	    
	       
	       initList();

	       // We get the ListView component from the layout
	       final ListView lv = (ListView) findViewById(R.id.listView);

	       // This is a simple adapter that accepts as parameter
	       // Context
	       // Data list
	       // The row layout that is used during the row creation
	       // The keys used to retrieve the data
	       // The View id used to show the data. The key number and the view id must match
	       SimpleAdapter simpleAdpt = new SimpleAdapter(this, itemList, android.R.layout.simple_list_item_2, new String[] {"item"}, new int[] {android.R.id.text1});

	       lv.setAdapter(simpleAdpt);
	       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	    	     public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
	    	                             long id) {

	    	    	 switch((int)lv.getItemIdAtPosition(position)){
	    				case 0:
	    					Variables.mode=0;
	    					Intent i = new Intent(FullscreenActivity.this,CubeActivity.class);
	    					
	    					startActivity(i);
	    					break;
	    				case 1:
	    					Variables.mode=1;
	    					Intent j = new Intent(FullscreenActivity.this,CubeActivity.class);
	    					
	    					startActivity(j);
	    					break;
	    				case 2:
	    					Variables.mode=2;
	    					Intent k = new Intent(FullscreenActivity.this,CubeActivity.class);
	    					
	    					startActivity(k);
	    
	    					break;
	    				case 3:
	    					Variables.mode=3;
	    					Intent l = new Intent(FullscreenActivity.this,CubeActivity.class);
	    					
	    					startActivity(l);
	    					break;
	    				case 4:
	    					Variables.mode=4;
	    					Intent m = new Intent(FullscreenActivity.this,CubeActivity.class);
	    					
	    					startActivity(m);
	    					break;
	    				case 5:
	    					Variables.mode=5;
	    					Intent n = new Intent(FullscreenActivity.this,CubeActivity.class);
	    					
	    					startActivity(n);
	    					break;
	    					
	    	    	 }


	    	     }
	    	});
	       
	}
	

	private void initList() {
	
		itemList.add(createitem("item", "Click to see a spinning polygon"));
	    itemList.add(createitem("item", "Graph"));
	    itemList.add(createitem("item", "Cube3"));
	    itemList.add(createitem("item", "Tetrahedron"));
	    itemList.add(createitem("item", "Click to see a cube"));
	    itemList.add(createitem("item", "Octahedron"));
	    
	    

	}

	private HashMap<String, String> createitem(String key, String name) {
	    HashMap<String, String> item = new HashMap<String, String>();
	    item.put(key, name);

	    return item;
	}

	

	
}
