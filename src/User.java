public class User implements IPlayer
{
    private GameBoard board;

    public User(GameBoard board)
    {
        this.board = board;
    }

    public void MakeMove(int type, int x, int y)
    {
        // Make the best move
        board.SetCell(type, x, y);
    }
}