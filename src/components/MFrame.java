package components;

import connect.*;
import newconn.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class MFrame extends JFrame
{
    public static void main(String[] args)
    {
        MFrame demo = new MFrame(new BoardInfo(Order.FIRST));
        demo.setVisible(true);
        demo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private Container checkerBoard; // 棋盘
    private Container msg; // 提示信息
    private Container Options; // 选项
    public MButton[][] buttons = new MButton[BoardInfo.ROW][BoardInfo.COL];
    public JLabel label; // 标签 提示信息

    private static String win = "you win!"; // "获胜信息"
    private static String lose = "you lose!"; // "失败信息"

    public BoardInfo info;
    public Client client; // 操纵client数据

    public Client getClient()
    { return client; }

    public void setClient(Client client)
    { this.client = client; }

    public MFrame(BoardInfo info) throws HeadlessException
    {
        this.info = info;
        setTitle("五子棋对战");
        setLayout(null);
        setBounds(0, 0, 1000, 800);
        checkerBoard = getContentPane();
        for (int i = 0; i < BoardInfo.ROW; i++)
            for (int j = 0; j < BoardInfo.COL; j++)
            {
                if (info.status == Order.FIRST)
                    makeButton(Color.BLACK, buttons, i, j);
                else if (info.status == Order.SECOND)
                    makeButton(Color.WHITE, buttons, i, j);
            }
        msg = getContentPane();
        label = new JLabel("落子之后输入Y继续");
        // 设置label属性
        label.setBounds(500, 20, 500, 100);
        label.setFont(new Font("Dialog", Font.PLAIN, 30));
        label.setForeground(Color.RED);
        msg.add(label);
        // 退出按钮
        Options = getContentPane();
        JButton btnQuit = new JButton("退出");
        btnQuit.setBounds(1050, 250, 100, 50);
        Options.add(btnQuit);
        btnQuit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO
            }
        });
    }

    /**
     * @description: makeButton
     * 生成棋盘上每一个点的button
     * @param: bgColor
     * 点击之后,按钮的背景色
     * @param: array
     * 二维的button数组
     * @param: x
     * 按钮位置横坐标
     * @param: y
     * 按钮位置纵坐标
     * @return: void
     * @throws: None
     * @version:
     * @date: 20/7/22 6:32
     */
    public void makeButton(Color bgColor, MButton[][] array, int x, int y)
    {
        // 1. 创建button对象,并初始化属性
        array[x][y] = new MButton();
        array[x][y].setBounds((15 + x * 50), (215 + y * 50), 20, 20);
        // ★: 每个按钮需要获得对应的坐标
        array[x][y].setX(x);
        array[x][y].setY(y);
        // 2. 按钮绑定在容器上
        checkerBoard.add(array[x][y]);
        // 3. 按钮上添加监听器
        array[x][y].addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // ★: getSource方法 反射定位到按钮对象
                MButton mButton = (MButton) e.getSource();
                // 1. 设置颜色
                mButton.setBackground(bgColor);
                int xx = mButton.get_X();
                int yy = mButton.get_Y();
                // 2. 改变在棋盘上的数据
                if (info.status == Order.FIRST)
                    info.chessArray[xx][yy] = 1;
                else if (info.status == Order.SECOND)
                    info.chessArray[xx][yy] = 2;
                client.msgOnClick = new InfoPerStep(xx, yy);
//                client.flag = true;
                System.out.println(client.msgOnClick);
                // 3. 判断输赢
                if (Tool.is_win(info.chessArray, xx, yy, 5, BoardInfo.ROW, BoardInfo.COL))
                {
                    UIManager.put("OptionPane.messageFont", new FontUIResource(
                            new Font("宋体", Font.PLAIN, 16)));
                    JOptionPane.showMessageDialog(null, win);
                }
            }
        });
    }

    public static void paintBtn(Color bgColor, MButton[][] buttons, int x, int y)
    {
        MButton button = buttons[x][y];
        button.setBackground(bgColor);
    }
}



