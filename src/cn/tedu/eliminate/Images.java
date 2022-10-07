package cn.tedu.eliminate;

import javax.swing.*;

/** 图片类 */
public class Images {
    public static ImageIcon background;
    public static ImageIcon bear;
    public static ImageIcon bird;
    public static ImageIcon fox;
    public static ImageIcon frog; //青蛙图
    public static ImageIcon[] bombs; //爆炸图数组
    static {
        background = new ImageIcon("img/background.png");
        bear = new ImageIcon("img/bear.png");
        bird = new ImageIcon("img/bird.png");
        fox = new ImageIcon("img/fox.png");
        frog = new ImageIcon("img/frog.png");
        bombs = new ImageIcon[4];
        for(int i=0;i<bombs.length;i++){
            bombs[i] = new ImageIcon("img/bom"+(i+1)+".png");
        }
    }

    public static void main(String[] args) {
        System.out.println(background.getImageLoadStatus()); //返回8表示读取成功
        System.out.println(bear.getImageLoadStatus());
        System.out.println(bird.getImageLoadStatus());
        System.out.println(fox.getImageLoadStatus());
        System.out.println(frog.getImageLoadStatus());
        for(int i=0;i<bombs.length;i++){

        }
    }
}
