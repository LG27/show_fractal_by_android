package com.lg.fractal.ui;

// 摇曳的分形树（分形频道：fractal.cn）2004
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class Leaf_M extends View implements Runnable {

    public Leaf_M(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public Leaf_M(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Leaf_M(Context context) {
        super(context);
        init(context);
    }

    private Thread t;

    float D = -10;//树的弯曲角度C
    float K = 40;//树杈的伸展角度B
    boolean dstatus = true;
    public static final double PI = Math.PI / 180;
    //int width=600;
    //int height=600;
    private Canvas canvas;

    private Paint p;
    private Context context;
    private Handler h = new Handler() {
        public void handleMessage(android.os.Message msg) {
            //改变角度，算出值
            if (dstatus) {
                D += 0.2f;
                if (D >= 10)
                    dstatus = false;
            }
            else {
                D -= 0.2f;
                if (D <= -10)
                    dstatus = true;
            }

            if (K < 60)
                K = K + 0.2f;
            invalidate();
        };
    };

    public void init(Context context) {
        this.context = context;
        t = new Thread(this);
        t.start();
        p = new Paint();
        p.setStrokeWidth(1);
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了  
        p.setColor(Color.BLACK);
    }

    public void run() {
//        try {
//            Thread.sleep(50);
            h.sendEmptyMessage(0);
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//            System.out.println(e.toString());
//        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        drawLeaf(canvas, getWidth() / 2, getHeight() * 3 / 4, 80, 270, K, D);
        t.run();
    }

    public void drawLeaf(Canvas g, double x, double y, double L, double a, float B, float C) {
        double x1, x2, x1L, x2L, x2R, x1R, y1, y2, y1L, y2L, y2R, y1R;

        float s1 = 5;
        float s2 = 3;
        float s3 = 1.1f;

        if (L > s1) {
            x2 = x + L * Math.cos(a * PI);
            y2 = y + L * Math.sin(a * PI);
            x2R = x2 + L / s2 * Math.cos((a + B) * PI);
            y2R = y2 + L / s2 * Math.sin((a + B) * PI);
            x2L = x2 + L / s2 * Math.cos((a - B) * PI);
            y2L = y2 + L / s2 * Math.sin((a - B) * PI);

            x1 = x + L / s2 * Math.cos(a * PI);
            y1 = y + L / s2 * Math.sin(a * PI);
            x1L = x1 + L / s2 * Math.cos((a - B) * PI);
            y1L = y1 + L / s2 * Math.sin((a - B) * PI);
            x1R = x1 + L / s2 * Math.cos((a + B) * PI);
            y1R = y1 + L / s2 * Math.sin((a + B) * PI);

            g.drawLine((int) x, (int) y, (int) x2, (int) y2, p);
            g.drawLine((int) x2, (int) y2, (int) x2R, (int) y2R, p);
            g.drawLine((int) x2, (int) y2, (int) x2L, (int) y2L, p);
            g.drawLine((int) x1, (int) y1, (int) x1L, (int) y1L, p);
            g.drawLine((int) x1, (int) y1, (int) x1R, (int) y1R, p);

            drawLeaf(g, x2, y2, L / s3, a + C, B, C);
            drawLeaf(g, x2R, y2R, L / s2, a + B, B, C);
            drawLeaf(g, x2L, y2L, L / s2, a - B, B, C);
            drawLeaf(g, x1L, y1L, L / s2, a - B, B, C);
            drawLeaf(g, x1R, y1R, L / s2, a + B, B, C);
        }
    }
}
