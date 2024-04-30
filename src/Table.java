

public class Table
{
    private byte table[][];

    private int sizeX, sizeY;

    public Table(int sizeX, int sizeY)
    {
        table = new byte[sizeX][sizeY];

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        for (int posX = 0; posX < sizeX; posX++)
        {
            for(int posY = 0; posY < sizeY; posY++)
            {
                table[posX][posY] = 0;
            }
        }
    }

    public boolean isEmpty()
    {
        int liveCells = 0;
        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                if(GetCell(x, y) != 0)
                {
                    liveCells++;
                }
            }
        }

        return liveCells == 0;
    }

    public boolean isFull()
    {
        int liveCells = 0;
        int size = sizeX * sizeY;

        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                if(GetCell(x, y) == 0)
                {
                    liveCells++;
                }
            }
        }

        return liveCells >= size;
    }

    public void SetCell(byte value, int posX, int posY)
    {
        table[posX][posY] = value;
    }

    public byte GetCell(int posX, int posY)
    {
        return table[posX][posY];
    }
}