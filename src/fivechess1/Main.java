package fivechess1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Main extends JFrame {
    private ChessBoard chessBoard;
    private JPanel toolbar;
    private JButton startButton,backButton,exitButton;

    private JMenuBar menuBar;
    private JMenu sysMenu;
    private JMenuItem startMenuItem,exitMenuItem,backMenuItem;

    public Main() {
        super("五子棋");
        chessBoard = new ChessBoard();

        Container contentPane=getContentPane();
        contentPane.add(chessBoard);
        chessBoard.setOpaque(true);

        menuBar = new JMenuBar();
        sysMenu = new JMenu("菜单");

        startMenuItem=new JMenuItem("新游戏");
        backMenuItem =new JMenuItem("悔棋");
        exitMenuItem =new JMenuItem("退出");

        sysMenu.add(startMenuItem);
        sysMenu.add(backMenuItem);
        sysMenu.add(exitMenuItem);

        MyItemListener lis = new MyItemListener();
        startMenuItem.addActionListener(lis);
        backMenuItem.addActionListener(lis);
        exitMenuItem.addActionListener(lis);

        menuBar.add(sysMenu);//将系统菜单添加到菜单栏上
        this.setJMenuBar(menuBar);//将menuBar设置为菜单栏

        toolbar=new JPanel();//工具面板实例化
        //三个按钮初始化
        startButton=new JButton("新游戏");
        backButton=new JButton("悔棋");
        exitButton=new JButton("退出");
        //将工具面板按钮用FlowLayout布局
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        //将三个按钮添加到工具面板
        toolbar.add(startButton);
        toolbar.add(backButton);
        toolbar.add(exitButton);
        //将三个按钮注册监听事件
        startButton.addActionListener(lis);
        exitButton.addActionListener(lis);
        backButton.addActionListener(lis);
        //将工具面板布局到界面”南方“也就是下方
        add(toolbar,BorderLayout.SOUTH);
        add(chessBoard);//将面板对象添加到窗体上
        //设置界面关闭事件
        this.setLocation(75, 75);
        this.setSize(800, 700);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class MyItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO 自动生成的方法存根
            Object obj = e.getSource();
            if(obj == exitMenuItem || obj == exitButton)	//退出
                System.exit(0);
            else if(obj == backMenuItem || obj == backButton)
                chessBoard.Back_Chess();
            else {
                chessBoard.New_Game();
            }
        }

    }

    public static void main(String[] args) {
        new Main();
    }
}
