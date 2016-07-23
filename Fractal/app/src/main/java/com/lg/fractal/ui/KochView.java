package com.lg.fractal.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class KochView extends View {
    List<Vector> list = new ArrayList<Vector>();

    private int NUM = 3;
    private Paint p;
    private Canvas c;

    public KochView(Context context) {
        super(context);
        init();
    }

    public KochView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public KochView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        p = new Paint();
        p.setStrokeWidth(5);
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了  
        p.setColor(Color.BLACK);

    }

    //设计思路
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        this.c = canvas;
        Vector vectorStart = new Vector(0, getHeight() / 2);
        Vector vectorEnd = new Vector(getWidth(), getHeight() / 2);
        create(5, vectorStart, vectorEnd);
        Vector temp = vectorStart;
        for (Vector vec : list) {
            canvas.drawLine(temp.getX(), temp.getY(), vec.getX(), vec.getY(), p);
            temp = vec;
        }
        canvas.drawLine(temp.getX(), temp.getY(), vectorEnd.getX(), vectorEnd.getY(), p);
    }

    public void create(int n, Vector startPosition, Vector endPosition) {
        if (n == 0)
           ;
        else {
            Vector temp = endPosition.subtract(startPosition);
            Vector vectorFirst = startPosition.add(temp.multiPly(0.333));//求得第一个点的坐标  
            create(n - 1, startPosition, vectorFirst);//在第一个点和第二个点递归调用create  
            list.add(vectorFirst);//把第一个点的坐标加入list  
            Vector vectorSecond = temp.multiPly(0.5).add(startPosition).add(temp.vertial());//求得第二个点的坐标  
            create(n - 1, vectorFirst, vectorSecond);//在第二个点和第三个点递归调用create  
            list.add(vectorSecond);//把第二个点的坐标加入list  
            Vector vectorThird = startPosition.add(temp.multiPly(0.666));//求得第三个点的坐标  
            create(n - 1, vectorSecond, vectorThird);//在第三个点和第四个点递归调用create  
            list.add(vectorThird);//把第三个点的坐标加入list  
            create(n - 1, vectorThird, endPosition);//在第四个点和第五个点递归调用create  
        }

    }

}
