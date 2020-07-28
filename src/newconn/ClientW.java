package newconn;

import components.*;
/**
 * @description:
 * @author: Pcy
 * @date: 20/7/27 16:16
 */
public class ClientW
{
    public static void main(String[] args)
    {
        try
        {
            //指定ID,白棋
            Client client = new Client(2,Order.SECOND);
            client.frame.setClient(client);
            client.start();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("***客户端启动失败***");
        }
    }
}

