package newconn;

import components.*;
import connect.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 * @description:
 * @author: Pcy
 * @date: 20/7/27 10:59
 */
public class Client
{

    public static void main(String[] args)
    {
        try
        {
            // 指定ID=1,黑棋
            Client client = new Client(1, Order.FIRST);
            client.frame.setClient(client);
            client.start();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("***客户端启动失败***");
        }
    }

    private Socket socket;
    // unused
    private boolean isPlaying;

    // 标识不同的客户端实例
    private int ID;
    // 绑定图形界面
    public MFrame frame;
    // 每一步下棋时发送的消息的对象
    public InfoPerStep msgOnClick;
    // unused
    public boolean flag = false;

    // 从服务器接收的下棋坐标
    // 用来染色
    public int x;
    public int y;

    public int getID()
    { return ID; }

    public void setID(int ID)
    { this.ID = ID; }

    public Client(int id, Order order) throws IOException
    {
        this.ID = id;
        System.out.println("***链接服务器***");
        socket = new Socket("localhost", 8888);
        System.out.println("***链接成功***");
        System.out.println("ID = " + ID);
        frame = new MFrame(new BoardInfo(order));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void start() throws IOException
    {
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(os), true
        );
        ObjectOutputStream oos = new ObjectOutputStream(os);
        SeverHandler handler = new SeverHandler();
        new Thread(handler).start();
        // 将自己的ID写到服务器
        String sID = String.valueOf(getID());
        pw.println(sID);
        Scanner sc = new Scanner(System.in);
        String ensure = "y";
        // 如果输入Y则才会发送msgOnClick消息
        while ("y".equalsIgnoreCase(ensure))
        {
            if (msgOnClick != null)
            {
                oos.writeObject(msgOnClick);
                oos.flush();
            }
            // 通过Scanner进行阻塞
            ensure = sc.nextLine();
            frame.label.setText("落子之后输入Y继续");
        }
    }

    // 线程主要功能 接收消息
    private class SeverHandler implements Runnable
    {
        @Override
        public void run()
        {
            // 判断颜色
            Color color = null;
            if (ID == 1)
                color = Color.WHITE;
            if (ID == 2)
                color = Color.BLACK;
            try
            {
                InputStream is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                while (true)
                {
                    Object o = ois.readObject();
                    // 如果是InfoPerStep
                    // 输出并染色
                    if (o instanceof InfoPerStep)
                    {
                        System.out.println(o);
                        x = ((InfoPerStep) o).getX();
                        y = ((InfoPerStep) o).getY();
                        MFrame.paintBtn(color, frame.buttons, x, y);
                        continue;
                    }
                    // 将label设置为"it's your turn"
                    if (o instanceof Message)
                    {
                        System.out.println(o);
                        frame.label.setText(((Message) o).getS());
                    }
                }
            } catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}

