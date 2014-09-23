package com.dipankar.androidtutorials;

/***************************************************************************************************
 * Work Item 1 : Android touch basics
 http://www.vogella.com/tutorials/AndroidTouch/article.html
 *
 *
 * ************************************************************************************************/
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;



public class SingleTouchActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Setting the a runtime view-  Dynamic making view
           - Make a view and set it by setContentView
         */
        setContentView(new SingleTouchEventView(this, null));
    }
}
/* Crete a view - that is extend of view class */
class SingleTouchEventView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    public SingleTouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }
    /* Dwawing some thing */

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    /* Crete a OnTouch event , whicg take a motion event and use switch case to find loc etc */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("SingleTouchEventView", "action down");
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                Log.d("SingleTouchEventView","action move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("SingleTouchEventView","action up");
                path.addCircle(eventX, eventY, 50, Path.Direction.CW);
                // nothing to do
                break;
            default:
                return false;
        }

        // Schedules a repaint.
        invalidate();
        return true;
    }
}


