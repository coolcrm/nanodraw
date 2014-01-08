package com.vladimir.karagioz.nanodraw;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity 
 {
	
	int mouseX; //current position X
	int mouseY; //current position Y
	int pmouseX; //previous position X
	int pmouseY; //previous position Y
	int colorCode = 0xFFAAAAAA;//Default Drawign Color = red
	GraphicsView myview; //Is Defined Here - we will access this item later too
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myview=new GraphicsView(this); // создаем объект myview класса GraphicsView
		
		setContentView(myview); // отображаем его в Activity
		//setContentView(R.layout.activity_main);
	}
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/

	// Implement the OnClickListener callback
    public void hello_Click	(View v) {
      // do something when the button is clicked
    	Log.v("Test", "It Was Click");
    }
    
  
  	 
  	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mPaint.setXfermode(null);
        //mPaint.setAlpha(0xFF);

        switch (item.getItemId()) {
            case R.id.color:
                //Call for new Activity
            	//Create new intent
            	Intent intent = new Intent(this, SettingsActivity.class);
            	//startActivity(intent);
            	startActivityForResult(intent, 1);
            	Log.v("Color", "Color dialog started");

            	return true;/*
            case EMBOSS_MENU_ID:
                if (mPaint.getMaskFilter() != mEmboss) {
                    mPaint.setMaskFilter(mEmboss);
                } else {
                    mPaint.setMaskFilter(null);
                }
                return true;
            case BLUR_MENU_ID:
                if (mPaint.getMaskFilter() != mBlur) {
                    mPaint.setMaskFilter(mBlur);
                } else {
                    mPaint.setMaskFilter(null);
                }
                return true;
            case ERASE_MENU_ID:
                mPaint.setXfermode(new PorterDuffXfermode(
                                                        PorterDuff.Mode.CLEAR));
                return true;
            case SRCATOP_MENU_ID:
                mPaint.setXfermode(new PorterDuffXfermode(
                                                    PorterDuff.Mode.SRC_ATOP));
                mPaint.setAlpha(0x80);
                return true;*/
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu); //Main here = Menu->Main.xml
        return true;
    }
    //Pull the result from options activity (Color)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      //switch(requestCode) {
        //case (MY_CHILD_ACTIVITY) : {
          if (resultCode == Activity.RESULT_OK) { 
            // TODO Extract the data returned from the child Activity.
        	  colorCode = data.getIntExtra("drawColor", 0xFFAAAAAA);//Default is Red
        	  //Resetting the View Color
        	  //GraphicsView myDrawView = (GraphicsView) Activity.getCurrentFocus();//findViewById(R.id.content);
        	  myview.paintColor = colorCode;
        	  myview.invalidate(); //Redraw
        	  Log.v("Color", String.valueOf(colorCode));
          }
          //break;
        //} 
      //}
    }
}
