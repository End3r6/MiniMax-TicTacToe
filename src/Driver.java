


public class Driver
{
    public static void main(String[] args)
    {
        GameBoard board = new GameBoard(3, 3);
        ConsolePlayer player = new ConsolePlayer(board);
        Bot bot = new Bot(board);
        User user = new User(board);

        IPlayer[] players = new IPlayer[]{user, bot}; 

        GUI gui = new GUI(board, players);
    }
}