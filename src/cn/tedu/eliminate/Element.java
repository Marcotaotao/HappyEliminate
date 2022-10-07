package cn.tedu.eliminate;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;
/**
 * 元素
 */
public abstract class Element {
    private int x; //x坐标
    private int y; //y坐标
    private boolean selected; //是否选中
    private boolean eliminated; //是否可消除
    private int eliminatedIndex; //爆炸动画图片起始下标
    /** 构造方法 */
    public Element(int x, int y) {
        this.x = x;
        this.y = y;
        selected = false;
        eliminated = false;
        eliminatedIndex = 0;
    }

    /** 是否是选中的 */
    public boolean isselected(){
        return selected; //返回选中状态
    }
    /** 是否是消除的 */
    public boolean isEliminated(){
        return eliminated; //返回消除状态
    }


    /** 获取图片 */
    public abstract ImageIcon getImage();

    /** 画元素 g:画笔 */
    public void paintElement(Graphics g){
        if(isselected()){ //若选中的
            g.setColor(Color.GREEN); //设置画笔记颜色为绿色
            g.fillRect(x,y,World.ELEMENT_SIZE,World.ELEMENT_SIZE); //填充矩形
            this.getImage().paintIcon(null,g,this.x,this.y);
        }else if(isEliminated()){ //若是可消除的
            if(eliminatedIndex<Images.bombs.length){ //若没有到最后一张爆炸图
                Images.bombs[eliminatedIndex++].paintIcon(null,g,x,y); //画爆炸图
            }
        }else {//正常画的
            this.getImage().paintIcon(null,g,this.x,this.y);
        }
    }

    /** 设计元素选中状态 */
    public void setSelected(boolean selected){
        this.selected = selected;
    }
}
