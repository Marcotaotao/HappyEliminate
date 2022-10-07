package cn.tedu.eliminate;
import javax.swing.ImageIcon;
/** 熊 */
public class Bear extends Element{
    /** 构造方法 */
    public Bear(int x,int y){
        super(x,y);
    }

    public ImageIcon getImage() {
        return Images.bear;
    }
}
