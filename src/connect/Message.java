package connect;

import java.io.Serializable;

/**
 * @description:
 * @author: Pcy
 * @date: 20/7/22 8:49
 */
public class Message implements Serializable
{
    private static final long serialVersionUID = -7435122297172836264L;
    private String s;

    public int cnt;

    public Message(String s, int cnt)
    {
        this.s = s;
        this.cnt = cnt;
    }

    public Message() { }

    public Message(String s) { this.s = s; }

    public String getS() { return s; }

    public void setS(String s) { this.s = s; }

    @Override
    public String toString()
    {
        return "Message{" +
               "s='" + s + '\'' +
               ", cnt=" + cnt +
               '}';
    }
}

