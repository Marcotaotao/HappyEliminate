package cn.tedu.eliminate;
import javax.swing.ImageIcon;

/** 鸟 */
public class Birf extends Element{
    /** 构造方法 */
    public Birf(int x,int y){
        super(x,y);
    }

    public ImageIcon getImage() {
        return Images.bird;
    }
}
