import javax.swing.*;
public class chess {
    public static void main(String[] args){
        chess frame = new chess();
        System.out.println("Hello World!");
    }
    public chess(){
        JFrame newframe = new JFrame("first");
        newframe.setSize(640,480);
        newframe.setVisible(true);
    }
}
