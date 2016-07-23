package com.lg.fractal.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class LevyView extends View {

    public LevyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LevyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LevyView(Context context) {
        super(context);
        init();
    }

    int depth;
    private Paint p;
    private Canvas canvas;

    public void init() {
        depth = 1;
        p = new Paint();
        p.setStrokeWidth(5);
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了  
        p.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        this.canvas = canvas;

        drawView(getWidth() / 4, getHeight() / 2, getWidth() * 3 / 4, getHeight() / 2, depth,
                canvas);
    }

    private void drawView(float x1, float y1, float x2, float y2, int d, Canvas canvas) {
        if (d > 0) {
            float x3 = (x1 + y1 + x2 - y2) / 2;
            float y3 = (x2 + y2 + y1 - x1) / 2;
            drawView(x1, y1, x3, y3, d - 1, canvas);
            drawView(x3, y3, x2, y2, d - 1, canvas);
            canvas.drawLine(Math.round(x1), Math.round(y1), Math.round(x2), Math.round(y2), p);
        }
    }

    private long times = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            times = System.currentTimeMillis();
            break;
        case MotionEvent.ACTION_UP:

            if ((System.currentTimeMillis() - times) < 500) {
                depth++;
                invalidate();
            }
            else {
                depth--;
                if (depth > 0) {
                    invalidate();
                }
                else {
                    Toast.makeText(getContext(), "无法再减少了！", 0).show();
                }
                Toast.makeText(getContext(), "长按减少深度！", 0).show();
            }
            break;
        default:
            break;
        }
        return true;
    }

}
