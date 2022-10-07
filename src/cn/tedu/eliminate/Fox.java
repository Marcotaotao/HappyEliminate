package cn.tedu.eliminate;
import javax.swing.ImageIcon;
/** 狐狸 */
public class Fox extends Element{
    /** 构造方法 */
    public Fox(int x,int y){
        super(x,y);
    }

    @Override
    public ImageIcon getImage() {
        return Images.fox;
    }
}
