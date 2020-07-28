package connect;

import java.io.Serializable;
/**
 * @description:
 * @author: Pcy
 * @date: 20/7/26 18:07
 */
public class InfoPerStep extends Message implements Serializable
{
    private static final long serialVersionUID = 5336447384775884358L;
    // 棋盘坐标
    private int x = 0;

    private int y = 0;

    private int clientID;

    public InfoPerStep(int x, int y, int clientID)
    {
        this.x = x;
        this.y = y;
        this.clientID = clientID;
    }

    public InfoPerStep()
    { }

    public int getX()
    { return x; }

    public void setX(int x)
    { this.x = x; }

    public int getY()
    { return y; }

    public void setY(int y)
    { this.y = y; }

    public int getClientID()
    { return clientID; }

    public void setClientID(int clientID)
    { this.clientID = clientID; }

    @Override
    public String toString()
    {
        return "InfoPerStep{" +
               "x=" + x +
               ", y=" + y +
               ", clientID=" + clientID +
               '}';
    }
}

