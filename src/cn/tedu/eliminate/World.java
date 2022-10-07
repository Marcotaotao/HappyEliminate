package cn.tedu.eliminate;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/** 窗口类 */
public class World extends JPanel{
    public static final int WIDTH = 429; //窗口的宽
    public static final int HEIGHT = 570; //窗口的高
    public static final int ROWS = 8; //8行
    public static final int COLS = 6; //6列
    public static final int ELEMENT_SIZE = 60; //元素大小
    public static final int OFFSET = 30;//偏移量(第一个元素距窗口左边缘的距离)
    private Element[][] elements = new Element[ROWS][COLS]; //元素数组8行6列

    //元素类型
    public static final int ELEMENT_TYPE_BEAR = 0; //熊
    public static final int ELEMENT_TYPE_BIRD = 1; //
    public static final int ELEMENT_TYPE_FOX = 2;
    public static final int ELEMENT_TYPE_FROG = 3;

    /** 创建元素(根据行列计算x/y坐标，而后创建对象) */
    public Element createElement(int row,int col){
        int x = OFFSET+col*ELEMENT_SIZE;//列col的值控制x坐标
        int y = OFFSET+row*ELEMENT_SIZE;//行row的值控制y坐标
        Random random = new Random(); //随机数对象
        int type = random.nextInt(4); //0到3之间
        switch (type){ //根据type值的不同来生成不同的对象
            case ELEMENT_TYPE_BEAR:
                return new Bear(x,y);
            case ELEMENT_TYPE_BIRD:
                return new Birf(x,y);
            case ELEMENT_TYPE_FOX:
                return new Fox(x,y);
            default:
                return new Frog(x,y);
        }
    }

    //元素可消状态
    public static final int ELIMINATE_NON = 0;//不可消
    public static final int ELIMINATE_ROW = 1;//行可消
    public static final int ELIMINATE_COL = 2;//列可消

    /** 检测元素是否是可消除的 返回元素可消状态(0/1/2) */
    public int canEliminate(int row,int col){
        Element e = elements[row][col];//获取当前元素
        //判断横向
        if(col>=2){
            Element e1 = elements[row][col-1];//获取当前元素前面第1个元素(0,1)
            Element e2 = elements[row][col-2];//获取当前元素前面第2个元素(0,0)
            if(e!=null && e1!=null && e2!=null){//若元素都不是null
                if(e.getClass().equals(e1.getClass()) && e.getClass().equals(e2.getClass())){
                    return ELIMINATE_ROW;

                }
            }
        }
        //判断纵向
        if(row>=2){
            Element e1 = elements[row-1][col];
            Element e2 = elements[row-2][col];
            if(e!=null && e1!=null && e2!=null){
                if(e.getClass().equals(e1.getClass()) && e.getClass().equals(e2.getClass()))
                    return ELIMINATE_COL;
            }
        }

        return ELIMINATE_NON; //返回0，表示不可消
    }

    /** 填充所有元素 */
    public void fillAllElements(){
        for (int row=0;row<ROWS;row++){ //遍历所有行
            for (int col=0;col<COLS;col++){ //遍历所有列
                do {
                    Element e = createElement(row, col); //创建对象
                    elements[row][col] = e; //将元素填充到elements数组中
                }while (canEliminate(row,col)!=ELIMINATE_NON);//若可消则重新生成元素
            }
        }
    }

    private boolean canInteractive = true;//可交互状态(默认可交换)
    private int selectedNumber = 0;//已经选中的元素个数
    private int firstRow = 0;//第一个选中元素的ROW
    private int firstCol = 0;////第一个选中元素的COL
    private int secondRow = 0;//第一个选中元素的ROW
    private int secondCol = 0;//第一个选中元素的COL

    /** 启动程序执行 */
    public void start(){
        fillAllElements(); //填充所有元素

        MouseAdapter adapter = new MouseAdapter() {
            /** 重写鼠标点击事件 */
            public void mouseClicked(MouseEvent e) {
                if(!canInteractive){//不可交互时，不响应鼠标事件
                    return;
                }
                canInteractive = false;//只要选中元素，就先将状态修改为不可交互
                int row = (e.getY()-OFFSET)/ELEMENT_SIZE;//获取选中元素的ROW
                int col = (e.getX()-OFFSET)/ELEMENT_SIZE;//获取选中元素的COL
                selectedNumber++;//选中元素个数增1
                if(selectedNumber==1){//第2次选中
                    //...
                    canInteractive = true;//可交互
                }else if(selectedNumber==2){//第2次选中
                    canInteractive = true;//可交互(在某种条件下才变为可交互)
                    selectedNumber = 0;//选中个数归0
                }
                repaint();//重画(调用paint()画)
            }
        };//鼠标侦听器
        this.addMouseListener(adapter);//添加鼠标侦听
    }

    public void paint(Graphics g){
        Images.background.paintIcon(null,g,0,0); //画背景图
        for (int row=0;row<ROWS;row++){ //遍历所有行
            for (int col=0;col<COLS;col++){ //遍历所有列
                Element e = elements[row][col]; //根据行列获取元素
                if(e!=null){ //若元素不是null
                    e.paintElement(g); //画元素
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        World world = new World();
        world.setFocusable(true);
        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT+17);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
