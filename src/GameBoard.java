

public class GameBoard
{
    private Table table;

    int sizeX, sizeY;

    public GameBoard(int sizeX, int sizeY)
    {
        this.table = new Table(sizeX, sizeY);

        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public Table GetTable()
    {
        return table;
    }

    public int NumDead()
    {
        int dead = 0;
        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                if(GetCell(x, y) == 0)
                {
                    dead++;
                }
            }
        }

        return dead;
    }

    
    public void Reset()
    {
        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                ClearCell(x, y);
            }
        }
    }
    
    public void SetCell(int value, int posX, int posY)
    {
        this.table.SetCell((byte)value, posX, posY);
    }

    public void ClearCell(int posX, int posY)
    {
        this.table.SetCell((byte)0, posX, posY);
    }
    
    public byte GetCell(int posX, int posY)
    {
        return this.table.GetCell(posX, posY);
    }
    
    public int GetSizeX()
    {
        return this.sizeX;
    }
    public int GetSizeY()
    {
        return this.sizeY;
    }

    public int Evaluate()
    {
        // Check rows
        for (int i = 0; i < sizeY; i++)
        {
            if (table.GetCell(0, i) == table.GetCell(1, i) && table.GetCell(1, i) == table.GetCell(2, i))
            {
                if (table.GetCell(0, i) == 1)
                return 1; // Player one wins
                else if (table.GetCell(0, i) == 2)
                return 2; // Player two wins
            }
        }
        
        // Check columns
        for (int i = 0; i < sizeX; i++)
        {
            if (table.GetCell(i, 0) == table.GetCell(i, 1) && table.GetCell(i, 1) == table.GetCell(i, 2))
            {
                if (table.GetCell(i, 0) == 1)
                return 1; // Player one wins
                else if (table.GetCell(i, 0) == 2)
                return 2; // Player two wins
            }
        }
        
        // Check diagonals
        if (table.GetCell(0, 0) == table.GetCell(1, 1) && table.GetCell(1, 1) == table.GetCell(2, 2))
        {
            if (table.GetCell(0, 0) == 1)
            return 1; // Player one wins
            else if (table.GetCell(0, 0) == 2)
            return 2; // Player two wins
        }
        
        if (table.GetCell(0, 2) == table.GetCell(1, 1) && table.GetCell(1, 1) == table.GetCell(2, 0))
        {
            if (table.GetCell(0, 2) == 1)
                return 1; // Player one wins
            else if (table.GetCell(0, 2) == 2)
                return 2; // Player two wins
        }

        if (NumDead() == 0)
            return 0; // Tie

        return -1; // Game still in progress
    }

    public boolean isEmpty()
    {
        return this.table.isEmpty();
    }

    public boolean isFull()
    {
        return table.isFull();
    }

    public void Display()
    {
        String output = "";
        for (int i = 0; i < sizeY; i++)
        {
            for (int j = 0; j < sizeX; j++)
            {
                output += table.GetCell(j, i) + " ";
            }

            output += "\n";
        }

        System.out.println(output);
    }
}