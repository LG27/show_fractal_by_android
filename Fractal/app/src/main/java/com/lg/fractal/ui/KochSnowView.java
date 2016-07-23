package com.lg.fractal.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class KochSnowView extends View {

    public KochSnowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public KochSnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KochSnowView(Context context) {
        super(context);
        init();
    }

    int depth;
    private Paint p;
    private Paint clear;
    private Canvas canvas;
//    private Handler h = new Handler(){
//      public void handleMessage(android.os.Message msg) {
//          drawView(canvas);
//      };  
//    };

    public void init() {
        depth = 1;
        p = new Paint();
        clear = new Paint();
        p.setStrokeWidth(5);
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了  
        p.setColor(Color.BLACK);
        clear.setColor(Color.WHITE);
        clear.setStyle(Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        this.canvas = canvas;

        //        snowflake(getWidth()/2,getHeight()/2-getWidth()/2*3/Math.sqrt(3), 0, getHeight()/2+getWidth()/2*3/Math.sqrt(3)/2, depth, canvas);
        //        snowflake(0, getHeight()/2+getWidth()/2*3/Math.sqrt(3)/2,getWidth(), getHeight()/2+getWidth()/2*3/Math.sqrt(3)/2, depth, canvas);
        //        snowflake(getWidth(), getHeight()/2+getWidth()/2*3/Math.sqrt(3)/2,getWidth()/2,getHeight()/2-getWidth()/2*3/Math.sqrt(3), depth, canvas);
        drawView(canvas);
    }

    private void drawView(Canvas canvas) {
        //canvas.drawRect(0, 0, getWidth(), getHeight(), clear);
        snowflake(540, 336.5, 0, 1271.75, depth, canvas);
        snowflake(0, 1271.75, 1080, 1271.75, depth, canvas);
        snowflake(1080, 1271.75, 540, 336.5, depth, canvas);
    }

    void snowflake(double x1, double y1, double x2, double y2, int depth, Canvas canvas) {
        if (depth <= 1) {
            canvas.drawLine((int) x1, (int) y1, (int) x2, (int) y2, p);
        }
        else {
            double x4 = x1 * 2 / 3 + x2 * 1 / 3;
            double y4 = y1 * 2 / 3 + y2 * 1 / 3;
            double x5 = x1 * 1 / 3 + x2 * 2 / 3;
            double y5 = y1 * 1 / 3 + y2 * 2 / 3;
            double x6 = (x4 + x5) / 2 + (y4 - y5) * Math.sqrt(3) / 2;
            double y6 = (y4 + y5) / 2 + (x5 - x4) * Math.sqrt(3) / 2;

            snowflake(x1, y1, x4, y4, depth - 1, canvas);
            snowflake(x4, y4, x6, y6, depth - 1, canvas);
            snowflake(x6, y6, x5, y5, depth - 1, canvas);
            snowflake(x5, y5, x2, y2, depth - 1, canvas);
        }
    }
    private long times = 0;
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            times = System.currentTimeMillis();
            Log.e("LG","MotionEvent.ACTION_DOWN:"+times);
            break;
        case MotionEvent.ACTION_UP:
            
            if ((System.currentTimeMillis() - times) < 500) {
                depth++;
                if(depth<7){
                    invalidate();
                }else{
                    Toast.makeText(getContext(), "超过点击次数手机会卡死的！", 0).show();
                }
                //invalidate();
                //this.draw(canvas);
                //drawView(canvas);
                //h.sendEmptyMessage(0);
            }
            else {
                Toast.makeText(getContext(), "时间太长", 0).show();
            }
            Log.e("LG",depth+":MotionEvent.ACTION_UP:"+times);
            break;
        default:
            break;
        }
        return true;
        //return super.onTouchEvent(event);
    }

}
