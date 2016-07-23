package com.lg.fractal.ui;

public class Vector {  
    private int pointX;  
    private int pointY;  
    Vector(int x, int y) {  
        pointX = x;  
        pointY = y;  
    }  
    public int getX() { return pointX;}  
    public int getY() {return pointY;}  
//�����ļӷ�����  
    public Vector add(Vector point) {  
        return new Vector(this.pointX + point.getX(), this.pointY  
                + point.getY());  
    }  
//����������  
    public Vector multiPly(double number) {  
        return new Vector((int) (number * pointX), (int) (number * pointY));  
    }  
//�����ļ�������  
    public Vector subtract(Vector point) {  
        return new Vector(this.pointX - point.getX(), this.pointY  
                - point.getY());  
    }  
//��������ķ�����  
    public Vector vertial() {  
        return new Vector(pointY, -pointX).multiPly(Math.sqrt(3) / 6);  
    }  
}  

