package newconn;

import connect.*;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * @description:
 * @author: Pcy
 * @date: 20/7/27 14:56
 */
public class Server
{
    // 通过记录消息数来实现交替
    public static int cntOfMsg = 1;
    private ServerSocket server;

    private Map<String, ObjectOutputStream> oosMap;
    public Server() throws IOException
    {
        server = new ServerSocket(8888);
        oosMap = new HashMap<>();
    }

    public static void main(String[] args)
    {
        Server server = null;
        try
        {
            server = new Server();
            server.start();
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("***服务器启动失败***");
        }
    }

    /**
     * @description: start
     * @return: void
     * @throws: IOException
     * @version:
     * @date: 20/7/27 23:12
     */
    public void start() throws IOException
    {
        while (true)
        {
            // 监听客户端连接
            Socket socket = server.accept();
            System.out.println("***新的客户端连接***");
            ClientHandler handler = new ClientHandler(socket);
            new Thread(handler).start();
        }
    }

    /**
     * @description: sendMsg
     * 将指定信息发送给所有客户端
     * @param: msg-- 需要发送的消息
     * @return: void
     * @throws: IOException
     * @version:
     * @date: 20/7/27 23:14
     */
    private void sendMsg(Message msg) throws IOException
    {
        Set<Map.Entry<String, ObjectOutputStream>> entryset = oosMap.entrySet();
        for (Map.Entry<String, ObjectOutputStream> entry : entryset)
        {
            ObjectOutputStream oos = entry.getValue();
            oos.writeObject(msg);
            oos.flush();
        }
    }

    /**
     * @description: sendSB
     * 将Message发送给指定客户端
     * @param: id--客户端ID
     * @param: msg
     * @return: void
     * @throws: IOException
     * @version:
     * @date: 20/7/27 23:15
     */
    private void sendSB(String id, Message msg) throws IOException
    {
        ObjectOutputStream out = oosMap.get(id);
        if (out != null)
        {
            out.writeObject(msg);
            out.flush();
        }
        else
            System.out.println("failed");
    }

    /**
     * @description: sendSB
     * 将Message对象转换为InfoPerStep对象
     * @param: id
     * @param: info
     * @return: void
     * @throws: IOException
     * @version:
     * @date: 20/7/27 23:16
     */
    private void sendSB(String id, InfoPerStep info) throws IOException
    {
        ObjectOutputStream out = oosMap.get(id);
        if (out != null)
        {
            out.writeObject(info);
            out.flush();
        }
        else
            System.out.println("failed");
    }

    private synchronized void addToMap(String nickName, ObjectOutputStream out)
    { oosMap.put(nickName, out);}

    private class ClientHandler implements Runnable
    {
        private Socket socket;
        private String clientID;

        public ClientHandler(Socket socket)
        { this.socket = socket; }

        /**
         * @description: 对于客户端建立的线程的主要流程
         * @return: void
         * @throws: None
         * @version:
         * @date: 20/7/27 23:13
         */
        @Override
        public void run()
        {
            try
            {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is)
                );
                //获取该线程下的客户端ID
                clientID = br.readLine();
                System.out.println("***成功取名为" + clientID + "***");
                // 加入到map中便于管理
                addToMap(clientID, oos);
                // cntOfMsg每4为一个周期
                if ("1".equalsIgnoreCase(clientID))
                {
                    Message first = new Message("It's your turn(1)", cntOfMsg);
                    sendSB("1", first);
                    System.out.println("first = " + first);
                    ++cntOfMsg;
                }
                InfoPerStep tmp = null;
                while (true)
                {
                    // 接收客户端下棋信息并打印 ( 2 , 4 )
                    {
                        tmp = (InfoPerStep) ois.readObject();
                        System.out.println(tmp);
                        ++cntOfMsg;
                    }
                    if (((cntOfMsg % 4) == 1) && (tmp.getClientID() == 2))
                    {
                        Message msg = new Message("it's your turn", cntOfMsg);
                        sendSB("1", msg);
                        System.out.println("对1发送" + msg);
                        sendSB("1", tmp);
                        System.out.println("对1发送" + tmp);
                        ++cntOfMsg;
                    }
                    // 第三步: 对ID为"2"的发送:"it's your turn"
                    if ((cntOfMsg % 4) == 3 &&(tmp.getClientID() == 1))
                    {
                        Message msg = new Message("it's your turn", cntOfMsg);
                        sendSB("2", msg);
                        System.out.println("对2发送" + msg);
                        sendSB("2", tmp);
                        System.out.println("对2发送" + tmp);
                        ++cntOfMsg;
                    }
                }
            } catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}

