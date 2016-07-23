package com.lg.fractal.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

//基于IFS系统的Julia集（分形频道：fractal.cn）2004

public class julia_ifs_View extends View implements Runnable {
  public julia_ifs_View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public julia_ifs_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public julia_ifs_View(Context context) {
        super(context);
        init();
    }

    private Paint p;

    
Thread drawThread = null;

  int h, w;
  int depth = 4;
  double t = 1.5, b = -1.5, l = -2.0, r = 2.0;
  boolean drawing = false;
  
  double c_x = -0.74543, c_y = 0.11301;
  private Canvas g;

  byte map[][];
  public void init() {
      p = new Paint();
      p.setStrokeWidth(1);
      p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了  
      p.setColor(Color.BLACK);
//      String s;
//      s = getParameter("depth");             // 得到 depth
//      if (s != null) depth = Integer.valueOf(s).intValue();
//
//      s = getParameter("c_x");            // 得到C的实部
//      if (s != null) c_x = Double.valueOf(s).doubleValue();
//      s = getParameter("c_y");             // 得到C的虚部
//      if (s != null) c_y = Double.valueOf(s).intValue();
//
//      s = getParameter("bgcolor");          // 得到背景颜色
//      if (s != null)
//        setBackground(new Color(Integer.valueOf(s, 16).intValue()));
  }
    
  public void stop()
  {
    drawThread = null;

  }
  
//  public boolean action(Event e)
//  {
//    switch (e.id) {
//      case Event.WINDOW_DESTROY:
//        System.exit(0);
//        return true;
//      default:
//        return false;
//    }
//  }
//  
//  public boolean mouseMove(Event e, int x, int y) {
//    double x1, y1;
//    
//    x1 = (double)x / w * (r - l) + l;
//    y1 = (double)y / h * (b - t) + t;
//                    
//    showStatus("(" + x1 + ", " + y1 + ")");
//    return false;
//  }
//  
//  public boolean mouseUp(Event e, int x, int y) {
//    if (!drawing) {
//      c_x = (double)x / w * (r - l) + l;
//      c_y = (double)y / h * (b - t) + t;
//      repaint();
//      return true;
//    } else
//      return false;
//  }
  
  int transX(double x)
  {
    return (int)((double)(x - l) / (r - l) * w);
  }
  
  int transY(double y)
  {
    return (int)((double)(y - t) / (b - t) * h);
  }
  
  @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.g =canvas;
        drawThread = new Thread(this, "Draw it");
        drawThread.start();
    }
  
  public void run()
  {
    
    drawing = true;
    h = getHeight(); w = getWidth();
    map = new byte[w][h];
    
    plot(1.0, 1.0,g);
    
    map = null;
    
    drawing = false;
  }
  
  void plot(double x, double y,Canvas g) {
    double r, newx, newy;
    int sgn;
    int xtmp = transX(x), ytmp = transY(y);

    map[xtmp][ytmp]++;
    g.drawLine(xtmp, ytmp, xtmp, ytmp,p);
    drawThread.yield();
  
    if (map[xtmp][ytmp] < depth) {
      x -= c_x; y -= c_y;
      r = Math.sqrt(x*x + y*y);
      sgn = (y>0)?1:(y<0)?-1:0;
      newx = Math.sqrt((r + x) / 2);
      newy = Math.sqrt((r - x) / 2) * sgn;
     
      plot(newx, newy,g);
      plot(-newx, -newy,g);
    }
  }

}