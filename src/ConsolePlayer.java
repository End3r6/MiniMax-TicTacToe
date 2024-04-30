import java.util.Scanner;

public class ConsolePlayer implements IPlayer
{
    private GameBoard board;

    public ConsolePlayer(GameBoard board) 
    {
        this.board = board;
    }

    public void MakeMove(int type) 
    {
        int[] move = GetMove();
        board.SetCell(type, move[0], move[1]);
    }

    public void MakeMove(int type, int x, int y){}

    public int[] GetMove() 
    {
        int[] move = new int[2];
        System.out.println("Enter your move (x, y): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Split the input string by comma and remove any whitespace
        String[] coordinates = input.split(",");
        move[0] = Integer.parseInt(coordinates[0].trim());
        move[1] = Integer.parseInt(coordinates[1].trim());

        return move;
    }
}