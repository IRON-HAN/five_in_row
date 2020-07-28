package components;

/**
 * @description: 想改MVC模式结果没成功的失败品
 * @author: Pcy
 * @date: 20/7/27 9:05
 */
public class BoardInfo
{
    public static final int COL = 14; // 棋盘列数
    public static final int ROW = 16; // 棋盘行数

    public int[][] chessArray; // 记录棋盘状态★
    public Order status; // 先后手状态

    public BoardInfo(Order status)
    {
        this.status = status;
        chessArray = new int[ROW][COL];
    }

    public BoardInfo()
    { chessArray = new int[ROW][COL]; }

    public Order getStatus()
    { return status; }

    public void setStatus(Order status)
    { this.status = status; }
}

