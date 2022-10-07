package cn.tedu.eliminate;
import javax.swing.ImageIcon;
/** 青蛙 */
public class Frog extends Element{
    /** 构造方法 */
    public Frog(int x,int y){
        super(x,y);
    }

    @Override
    public ImageIcon getImage() {
        return Images.frog;
    }
}
