package fivechess1;

import java.awt.Color;

/*
    棋子类
 */
public class Point {
    private int x;      //在标记数组中的坐标
    private int y;
    private Color color;    // 棋子的颜色
    public static final int DIAMETER = 30;  //棋子半径
    public Point(int x, int y, Color color) {
        // TODO 自动生成的构造函数存根
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Color getColor() {
        return color;
    }
}
