package com.vladimir.karagioz.nanodraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GraphicsView extends View {
	float touchX = 0;
	float touchY = 0;
	private Bitmap  mBitmap;
	private Paint 	mBitmapPaint;
    private Canvas  mCanvas;
    private Path    mPath;
    private Paint   mPaint;
    int paintColor = 0xFFAAAAAA; //Default Color

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    
	public GraphicsView(Context context) {
		super(context);
		mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		// TODO Auto-generated constructor stub
		mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
	}

	public GraphicsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GraphicsView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }
	private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }
	public boolean onTouchEvent(MotionEvent event)
	{
		/* Old Code
		if(event.getAction() == MotionEvent.ACTION_DOWN) { 
			touchX = event.getX();
			touchY = event.getY();
			invalidate(); 
			}
		if(event.getAction() == MotionEvent.ACTION_MOVE) { 
			touchX = event.getX();
			touchY = event.getY();
			invalidate(); 
			}
		return true;
		*/
		touchX = event.getX();
		touchY = event.getY();
		switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            touch_start(touchX, touchY);
            invalidate();
            break;
        case MotionEvent.ACTION_MOVE:
            touch_move(touchX, touchY);
            invalidate();
            break;
        case MotionEvent.ACTION_UP:
            touch_up();
            invalidate();
            break;
    }
    return true;
	}
	 protected void onDraw(Canvas canvas)
		{
		 /*
		 // загружаем иконку из ресурсов в объект myBitmap
		 Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		 // рисуем myBitmap на канве в координатах 10, 10
		 canvas.drawBitmap(myBitmap, touchX, touchY, null);	
		 Paint paint = new Paint();
		 paint.setColor(Color.BLACK);
		 paint.setTextSize(20);
		 paint.setAntiAlias(true);
		 canvas.drawRect(touchX,touchY,touchX+10,touchY+10,paint);
		 */
		 //canvas.drawColor(paintColor);
		 mPaint.setColor(paintColor);	

         canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

         canvas.drawPath(mPath, mPaint);
		}
	 
	 
}
