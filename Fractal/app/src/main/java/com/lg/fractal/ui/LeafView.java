package com.lg.fractal.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LeafView extends View {

    public LeafView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public LeafView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LeafView(Context context) {
        super(context);
        init();
    }

    private Paint p;

    public void init() {
        p = new Paint();
        p.setStrokeWidth(1);
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了  
        p.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLeaf(canvas, getWidth()/3,getHeight()*3/4, 200, 270);
    }

    boolean bq;
    int st, r = 255, g = 255, b = 255;
    public static final double P = Math.PI / 180;

    public void drawLeaf(Canvas g, double x, double y, double L, double a) {
        double x1, x2, x1L, x2L, x1R, x2R;
        double y1, y2, y1L, y2L, y1R, y2R;
        float B = 50, C = 9, s1 = 2, s2 = 3, s3 = 1.2f;

        if (L > s1) {
            x2 = x + L * Math.cos(a * P);
            y2 = y + L * Math.sin(a * P);
            x2R = x2 + L / s2 * Math.cos((a + B) * P);
            y2R = y2 + L / s2 * Math.sin((a + B) * P);
            x2L = x2 + L / s2 * Math.cos((a - B) * P);
            y2L = y2 + L / s2 * Math.sin((a - B) * P);

            x1 = x + L / s2 * Math.cos(a * P);
            y1 = y + L / s2 * Math.sin(a * P);
            x1L = x1 + L / s2 * Math.cos((a - B) * P);
            y1L = y1 + L / s2 * Math.sin((a - B) * P);
            x1R = x1 + L / s2 * Math.cos((a + B) * P);
            y1R = y1 + L / s2 * Math.sin((a + B) * P);

            g.drawLine((int) x, (int) y, (int) x2, (int) y2, p);
            g.drawLine((int) x2, (int) y2, (int) x2R, (int) y2R, p);
            g.drawLine((int) x2, (int) y2, (int) x2L, (int) y2L, p);
            g.drawLine((int) x1, (int) y1, (int) x1L, (int) y1L, p);
            g.drawLine((int) x1, (int) y1, (int) x1R, (int) y1R, p);
            drawLeaf(g, x2, y2, L / s3, a + C);
            drawLeaf(g, x2R, y2R, L / s2, a + B);
            drawLeaf(g, x2L, y2L, L / s2, a - B);
            drawLeaf(g, x1L, y1L, L / s2, a - B);
            drawLeaf(g, x1R, y1R, L / s2, a + B);
        }
    }
}
