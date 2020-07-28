package components;

import java.io.*;

/**
 * @description:
 * @author: Pcy
 * @date: 20/7/22 8:11
 */
public class Tool
{
    //map:存放棋盘元素的二维数组
    //x,y:最后下的一个旗子的坐标
    //num:设定连续多少个旗子就为赢
    public static boolean is_win(int[][] map, int x, int y, int num, int ROW, int COL)
    {
        int count = 0;
        int winflag = 1;//第一个点不用再次读取
        int cur;//记录当前所下的棋
        int i, j;
        cur = map[x][y];
        //垂直方向判断
        //往上读取num个点, y不变,x--
        for (i = x - 1, j = y; i >= 0 && count++ < num; i--)//读取num次,或者遇到边界停止
        {
            if (map[i][j] == cur)
                winflag++;
            else
                break;//如果不是,就不是连续相同的棋子
        }
        //往下读取num个点,y不变,x++
        count = 0;//读取次数清零
        for (i = x + 1, j = y; i < ROW && count++ < num; i++)
        {
            if (map[i][j] == cur)
                winflag++;
            else
                break;//如果不是,就不是连续相同的棋子
        }
        count = 0;//读取次数清零
        if (winflag >= num)//垂直方向读取完毕,判断输赢,赢返回真
            return true;
        else
            winflag = 1;
        //水平判断
        //往右方向,x不变,y++
        for (i = x, j = y + 1; j < COL && count++ < num; j++)
        {
            if (map[i][j] == cur)
                winflag++;
            else
                break;//如果不是,就不是连续相同的棋子
        }
        count = 0;
        //往左方向,x不变,y--
        for (i = x, j = y - 1; j >= 0 && count++ < num; j--)
        {
            if (map[i][j] == cur)
                winflag++;
            else
                break;//如果不是,就不是连续相同的棋子
        }
        count = 0;
        //水平方向读取完毕,判读输赢
        if (winflag >= num)//垂直方向读取完毕,判断输赢,赢返回真
            return true;
        else
            winflag = 1;
        //主对角线方向判断
        //往右下方向,x++,y++
        for (i = x + 1, j = y + 1; i < ROW && j < 3 && count++ < num; i++, j++)
        {
            if (map[i][j] == cur)
                winflag++;
            else
                break;//如果不是,就不是连续相同的棋子
        }
        count = 0;
        //往左上方向,x--,y--
        for (i = x - 1, j = y - 1; i >= 0 && j >= 0 && count++ < num; i--, j--)
        {
            if (map[i][j] == cur)
                winflag++;
            else
                break;//如果不是,就不是连续相同的棋子
        }
        count = 0;
        //主对角线读取完毕,判读输赢
        if (winflag >= num)//垂直方向读取完毕,判断输赢,赢返回真
            return true;
        else
            winflag = 1;
        //斜对角线方向判断
        //往右上,x--,y++
        for (i = x - 1, j = y + 1; i >= 0 && j < COL && count++ < num; i--, j++)
        {
            if (map[i][j] == cur)
                winflag++;
            else
                break;//如果不是,就不是连续相同的棋子
        }
        count = 0;
        //往左下方向,x++,y--
        for (i = x + 1, j = y - 1; i < ROW && j >= 0 && count++ < num; i++, j--)
        {
            if (map[i][j] == cur)
                winflag++;
            else
                break;//如果不是,就不是连续相同的棋子
        }
        count = 0;
        //斜对角方向读取完毕,判读输赢
        if (winflag >= num)//垂直方向读取完毕,判断输赢,赢返回真
            return true;
        else
            winflag = 1;
        //所有方向判断完毕,没出现胜负
        return false;
    }

    public static void inAndOut_All(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int cnt;
        while ((cnt = in.read(buffer)) != -1)
            out.write(buffer, 0, cnt);
    }

    public static String in_Line(InputStream in) throws IOException
    {
        int c;
        StringBuilder s = new StringBuilder();
        do
        {
            c = in.read();
            if (c == '\n')
                break;
            s.append(c);
        } while (c != -1);
        return s.toString();
    }
}

