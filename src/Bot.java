
public class Bot implements IPlayer
{
    private GameBoard board;

    public Bot(GameBoard board)
    {
        this.board = board;
    }

    public void MakeMove(int type) 
    {
        if(board.NumDead() == 0)
            return;

        int bestScore = Integer.MIN_VALUE;
        int bestMoveX = -1;
        int bestMoveY = -1;
    
        for (int i = 0; i < board.GetSizeY(); i++) 
        {
            for (int j = 0; j < board.GetSizeX(); j++) 
            {
                if (board.GetCell(j, i) == 0) 
                {
                    board.SetCell(1, j, i); // Make a hypothetical move

                    int currentScore = minimax(board, 0, true); // Adjust the depth as needed

                    board.ClearCell(j, i); // Undo the hypothetical move
    
                    if (currentScore > bestScore) 
                    {
                        bestScore = currentScore;
                        bestMoveX = j;
                        bestMoveY = i;
                    }
                }
            }
        }
    
        // Make the best move
        board.SetCell(type, bestMoveX, bestMoveY);
    }
    
    public void MakeMove(int type, int x, int y){}

    private int minimax(GameBoard state, int depth, boolean isMaximizingPlayer) 
    {
        // Base case: Check if the game has ended or the maximum depth has been reached
        int score = state.Evaluate();
        if (score != -1 || depth == 0) 
        {
            return score;
        }
    
        // Recursive case
        if (isMaximizingPlayer) 
        {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < state.GetSizeY(); i++) 
            {
                for (int j = 0; j < state.GetSizeX(); j++) 
                {
                    if (state.GetCell(j, i) == 0) 
                    {
                        state.SetCell(2, j, i); // Make a hypothetical move for the maximizing player
                        int currentScore = minimax(state, depth + 1, false); // Recursive call for the minimizing player
                        state.ClearCell(j, i); // Undo the hypothetical move
    
                        bestScore = Math.max(bestScore, currentScore);
                    }
                }
            }

            return bestScore;
        } 
        else 
        {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < state.GetSizeY(); i++) 
            {
                for (int j = 0; j < state.GetSizeX(); j++) 
                {
                    if (state.GetCell(j, i) == 0)
                    {
                        state.SetCell(1, j, i); // Make a hypothetical move for the minimizing player
                        int currentScore = minimax(state, depth + 1, true); // Recursive call for the maximizing player
                        state.ClearCell(j, i); // Undo the hypothetical move
    
                        bestScore = Math.min(bestScore, currentScore);
                    }
                }
            }

            return bestScore;
        }
    }
}