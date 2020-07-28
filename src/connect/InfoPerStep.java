package connect;

import java.io.Serializable;
/**
 * @description:
 * @author: Pcy
 * @date: 20/7/26 18:07
 */
public class InfoPerStep extends Message implements Serializable
{
    // 棋盘坐标
    private int x = 0;
    private int y = 0;

    public InfoPerStep(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    { return x; }

    public void setX(int x)
    { this.x = x; }

    public int getY()
    { return y; }

    public void setY(int y)
    { this.y = y; }


    private static final long serialVersionUID = 5336447384775884358L;

    public InfoPerStep()
    { }

    @Override
    public String toString()
    {
        return "InfoPerStep{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }
}

