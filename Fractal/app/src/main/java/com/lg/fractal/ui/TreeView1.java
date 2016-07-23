package com.lg.fractal.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TreeView1 extends View {

    private Paint p;

    public TreeView1(Context context) {
        super(context);
        init();
    }

    public TreeView1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TreeView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        p = new Paint();
        p.setStrokeWidth(5);
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了  
        p.setColor(Color.BLACK);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        write_node(canvas,level,L,T0,0,0);
    }

    int level = 12;//递归深度
    double L = 400.0;//初始长度
    double t = 90.0*(Math.PI/180.0);//叉间角度
    double T0 = 90.0*(Math.PI/180.0);//主干的生长角度
    double ratio_x = 0.8;
    double ratio_y = 0.8;
    double z =2.0/3.0;
    
    

    private void write_node(Canvas g, int n, double l, double arg, int x, int y) {
        int xx,yy,i;
        xx=(int)(l*Math.cos(arg)*ratio_x);
        yy=(int)(l*Math.sin(arg)*ratio_y);
        g.drawLine(x+(int)(getWidth()*0.5), getHeight()*3/4-y,(xx+x)+(int)(getWidth()*0.5), getHeight()*3/4-(yy+y),p);
        if(n>0){
           write_node(g, n-1, l*z, (arg-t/2.0)+0.0*t/1.0, x+xx, yy+y); 
           write_node(g, n-1, l*z, (arg-t/2.0)+1.0*t/1.0, x+xx, yy+y); 
        }
        
    }


}
