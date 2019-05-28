package fivechess1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ChessBoard extends JPanel implements MouseListener {
    public static final int MARGIN=30;//边距
    public static final int GRID_SPAN=35;//网格间距
    public static final int ROWS=15;//棋盘行数
    public static final int COLS=15;//棋盘列数
    private int[][] mark = new int[ROWS+2][COLS+2];
    Point[] chessList=new Point[(ROWS+1)*(COLS+1)];//初始每个数组元素为null
    boolean isBlack=true;//true 黑   false白
    boolean gameOver=false;//结束标记
    int chessCount;//当前棋盘棋子的个数


    Color colortemp;
    public ChessBoard() {
        addMouseListener(this); //添加鼠标监听

    }


    @Override
    protected void paintComponent(Graphics g) {
        // TODO 自动生成的方法存根
        super.paintComponent(g);

        for(int i=0;i<=ROWS;i++){//横
            g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);
        }
        for(int i=0;i<=COLS;i++){//竖
            g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);
        }


        for(int i=0; i<chessCount; i++) {   //描绘棋子
            int pos_x = chessList[i].getX()*GRID_SPAN + MARGIN;
            int pos_y = chessList[i].getY()*GRID_SPAN + MARGIN;
            g.setColor(chessList[i].getColor());
            colortemp=chessList[i].getColor();
            if(colortemp==Color.black){
                RadialGradientPaint paint = new RadialGradientPaint(pos_x-Point.DIAMETER/2+25, pos_y-Point.DIAMETER/2+10, 20, new float[]{0f, 1f}
                        , new Color[]{Color.WHITE, Color.BLACK});
                ((Graphics2D) g).setPaint(paint);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

            }
            else if(colortemp==Color.white){
                RadialGradientPaint paint = new RadialGradientPaint(pos_x-Point.DIAMETER/2+25, pos_y-Point.DIAMETER/2+10, 70, new float[]{0f, 1f}
                        , new Color[]{Color.WHITE, Color.BLACK});
                ((Graphics2D) g).setPaint(paint);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);

            }

            Ellipse2D e = new Ellipse2D.Float(pos_x-Point.DIAMETER/2, pos_y-Point.DIAMETER/2, 34, 35);
            ((Graphics2D) g).fill(e);

            //最后一个加个红色边框

            if(i==chessCount-1){
                g.setColor(Color.red);
                g.drawRect(pos_x-Point.DIAMETER/2, pos_y-Point.DIAMETER/2,34, 35);
            }
        }

    }


    public int getx(int x) {    //画板上的坐标  --> 数组中的坐标
        return (x-MARGIN+GRID_SPAN/2)/GRID_SPAN;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO 鼠标点击

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO 鼠标按下
        if(gameOver)
            return ;
        int x = e.getX();
        int y = e.getY();
        x = getx(x);
        y = getx(y);
        if(x<0 || x>ROWS || y<0 || y>COLS || mark[x][y]!=0)	//不能下在这里
            return ;
        mark[x][y] = isBlack?1:2;       //标记
        Point temp = new Point(x, y, isBlack?Color.black:Color.white);
        chessList[chessCount++] = temp;

        repaint();  //调用重画

        if(Judge()) {       //判断
            gameOver = true;
            String str = isBlack?"黑方":"白方";
            JOptionPane.showMessageDialog(null, str + "获胜");
            //New_Game();
            //return ;
        }
        isBlack = !isBlack;
        //System.out.println(x + "," + y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO 释放

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO 进入

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO 离开

    }

    private boolean isOut(int x, int y) {       //判断是否超出棋盘
        if(x<0 || y<0 || x>ROWS || y>COLS)
            return false;
        return true;
    }

    private boolean Judge() {
        int[][] xiang = { {1,0},{0,1},{1,1},{1,-1},{-1,0},{0,-1},{-1,-1},{-1,1}};   //八个方向
        int[] cnt = {0, 0, 0, 0};
        System.out.println(isBlack?"hei":"bai");
        int x = chessList[chessCount-1].getX();
        int y = chessList[chessCount-1].getY();
        System.out.println(x + "," + y);
        int chess = isBlack?1:0;
        for(int i=0; i<8; i++) {
            int tx = x + xiang[i][0];
            int ty = y + xiang[i][1];
            if(!isOut(tx, ty))
                continue;
            while(mark[tx][ty]%2 == chess && mark[tx][ty]>0) {
                cnt[i%4]++;
                tx += xiang[i][0];
                ty += xiang[i][1];
                if(!isOut(tx, ty))
                    break;
            }
        }
        for(int i=0; i<4; i++) {
            if(cnt[i] >= 4)
                return true;
        }
        return false;
    }



    public void Back_Chess() {		//悔棋
        if(chessCount == 0)
            return ;
        chessCount--;
        int x = chessList[chessCount].getX();
        int y = chessList[chessCount].getY();
        mark[x][y] = 0;
        chessList[chessCount] = null;
        isBlack = !isBlack;
        gameOver = false;
        repaint();
    }
    public void New_Game() {		//重开新游戏
        int x, y;
        for(int i=0; i<chessCount; i++) {
            x = chessList[i].getX();
            y = chessList[i].getY();
            mark[x][y] = 0;
            chessList[i] = null;
        }
        isBlack = true;
        gameOver = false;
        chessCount = 0;
        repaint();
    }
}