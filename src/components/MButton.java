package components;

import javax.swing.*;

// ★: 通过继承JButton变成自己的类, 添加属性, 轻松获取坐标
public class MButton extends JButton
{
    private int x;
    private int y;

    public MButton() { }

    public MButton(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int get_Y() { return y; }

    public void setY(int y) { this.y = y; }

    public int get_X() { return x; }

    public void setX(int x) { this.x = x; }
}
