import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI 
{
    private GameBoard board;
    private IPlayer[] players;

    private JButton[][] buttons;

    private JFrame frame;
    
    private JLabel winnerLabel;

    private JPanel gamePanel;
    private JPanel gameOverPanel;
    

    public GUI(GameBoard board, IPlayer[] players) 
    {
        this.players = players;
        this.board = board;

        initialize();
    }

    public void Draw() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        gamePanel = new JPanel(new GridLayout(board.GetSizeY(), board.GetSizeX()));
        buttons = new JButton[board.GetSizeY()][board.GetSizeX()];
        for (int i = 0; i < board.GetSizeY(); i++) {
            for (int j = 0; j < board.GetSizeX(); j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(100, 100));
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                button.addActionListener(new ButtonClickListener(j, i));
                buttons[i][j] = button;
                gamePanel.add(button);
            }
        }
    
        gameOverPanel = new JPanel(new BorderLayout());

        winnerLabel = new JLabel();
        winnerLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetButtonClickListener());
        
        gameOverPanel.add(winnerLabel, BorderLayout.CENTER);
        gameOverPanel.add(resetButton, BorderLayout.SOUTH);
        gameOverPanel.setVisible(false);
    
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(gameOverPanel, BorderLayout.SOUTH);
    
        frame.pack();
        frame.setVisible(true);
    }

    private void updateButtonLabels() 
    {
        for (int i = 0; i < board.GetSizeY(); i++) 
        {
            for (int j = 0; j < board.GetSizeX(); j++) 
            {
                int cellValue = board.GetCell(j, i);
                String label = "";
                if (cellValue == 1) 
                {
                    label = "O";
                } else if (cellValue == 2) 
                {
                    label = "X";
                }
                buttons[i][j].setText(label);
                buttons[i][j].setEnabled(cellValue == 0); // Enable the button if the cell is empty
            }
        }
    }

    public void handleGameOver(int winner) 
    {
        String winnerText;
        if (winner == 0) 
        {
            winnerText = "Tie!";
        } 
        else if (winner == 1) 
        {
            winnerText = "Player One Wins!";
        } 
        else 
        {
            winnerText = "Bot Wins!";
        }
    
        winnerLabel.setText(winnerText);

        gameOverPanel.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener 
    {
        private int x;
        private int y;

        public ButtonClickListener(int x, int y) 
        {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            players[0].MakeMove(1, x, y); // Assuming the player is always 1

            players[1].MakeMove(2); // Trigger the bot

            updateButtonLabels();

            // Check for game over condition
            int winner = board.Evaluate();
            if (winner != -1) 
            {
                handleGameOver(winner);
            }
        }
    }

    private class ResetButtonClickListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            gamePanel.setVisible(true);
            gameOverPanel.setVisible(false);
            board.Reset();
            updateButtonLabels();
        }
    }

    private void initialize() 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                Draw();
            }
        });
    }
}