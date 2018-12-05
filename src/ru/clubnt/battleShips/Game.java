package ru.clubnt.battleShips;

public class Game
{

    public Game()
    {
        Board board1 = new Board();
        displayBoard(board1);
    }


    //Временный метод для вывода игрового поля в консоль. Затем все отображение должно быть в отдельноим классе
    private void displayBoard(Board board)
    {
        System.out.println();
        System.out.println();

        Board.CellType[][] cells = board.getCells();

        for (int row = 0; row < Board.BOARD_SIZE; row++)
        {
            for (int col = 0; col < Board.BOARD_SIZE; col++)
            {
                String symbol;

                if(row == 0 && col == 0)
                {
                    System.out.println("     a b c d e f g h i j");
                    System.out.println("     ____________________");
                }

                if(col == 0)
                {
                    System.out.println();
                    System.out.print(row + 1 + "|   ");
                }

                Board.CellType cellType = cells[row][col];

                switch (cellType)
                {
                    case EMPTY:
                        symbol = "_";
                        break;
                    case SHIP:
                        symbol = "O";
                        break;
                    case AROUND_SHIP:
                        symbol = "_";
                        break;
                    default:
                        symbol = " E";
                }
                System.out.print(symbol + " ");
            }
        }

        System.out.println();
        System.out.println();
    }
}
