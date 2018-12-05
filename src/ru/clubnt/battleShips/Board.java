package ru.clubnt.battleShips;


import java.util.Random;


public class Board
{

    public static final int BOARD_SIZE = 10;
    private final int _SHIPS_COUNT = 10;

    private Ship[] _shipsList;
    private CellType[][] _cellTypesMap; //[rows, cols]

    private Random _random = new Random();

//    private enum CellState
//    {
//        EMPTY,
//        KILL,
//        MISS;
//    }

    public enum CellType
    {
        SHIP,
        AROUND_SHIP,
        EMPTY
    }


    public Board()
    {
        generateShips();
    }


//    public Ship getShipAtPoint(int row, int col)
//    {
//        return _shipsList[0];
//    }


    public CellType[][] getCells()
    {
        return _cellTypesMap;
    }


    private void generateShips()
    {
        _shipsList = new Ship[_SHIPS_COUNT];
        _cellTypesMap = new CellType[BOARD_SIZE][BOARD_SIZE];

        //Заполняем карту типов ячеек значениями по умоляинию(EMPTY)
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            for (int j = 0; j < BOARD_SIZE; j++)
            {
                _cellTypesMap[i][j] = CellType.EMPTY;
            }
        }

        int shipSize;

        //Определяем размер корабля в зависимости от его порядкового номера(коряво)
        for (int i = 0; i < _SHIPS_COUNT; i++)
        {
            if(i > 5)
            {
                shipSize = 1;
            }
            else if(i > 2)
            {
                shipSize = 2;
            }
            else if(i > 0)
            {
                shipSize = 3;
            }
            else
            {
                shipSize = 4;
            }

            //Случайным образом определяем поворот корабля(вертикальный или горизонтальный)
            Ship.ShipOrientation orientation = Ship.ShipOrientation.getRandomValue();
            Ship newShip = new Ship(shipSize, orientation);
            placeShip(newShip);
//            _shipsList[i] = newShip;
        }
    }


    //Рекурсивно пытается разместить корабль в случайной клетке поля
    private void placeShip(Ship ship)
    {
        int randRow = _random.nextInt(BOARD_SIZE);
        int randCol = _random.nextInt(BOARD_SIZE);
        int shipSize = ship.getSize();

        switch (ship.getOrientation())
        {
            //В зависимости от ориентации корабля и его положения заполняем карту типов ячеек. Выставляем ячейкам тип SHIP если в ней корабль, AROUND_SHIP ячейкам  по периметру корабля
            case VERTICAL:
                for (int row = randRow; row < randRow + shipSize; row++)
                {
                    if(row > (BOARD_SIZE - 1) || _cellTypesMap[row][randCol] != CellType.EMPTY)
                    {
                        placeShip(ship);
                        return;
                    }
                }

                for (int row = randRow; row < randRow + shipSize; row++)
                {
                    _cellTypesMap[row][randCol] = CellType.SHIP;

                    if((randCol - 1) >= 0)
                    {
                        _cellTypesMap[row][randCol - 1] = CellType.AROUND_SHIP;
                    }

                    if((randCol + 1) < BOARD_SIZE)
                    {
                        _cellTypesMap[row][randCol + 1] = CellType.AROUND_SHIP;
                    }
                }

                if((randRow - 1) >= 0)
                {
                    _cellTypesMap[randRow - 1][randCol] = CellType.AROUND_SHIP;

                    if((randCol - 1) >= 0)
                    {
                        _cellTypesMap[randRow - 1][randCol - 1] = CellType.AROUND_SHIP;
                    }

                    if((randCol + 1) < BOARD_SIZE)
                    {
                        _cellTypesMap[randRow - 1][randCol + 1] = CellType.AROUND_SHIP;
                    }
                }

                if((randRow + shipSize) < BOARD_SIZE)
                {
                    _cellTypesMap[randRow + shipSize][randCol] = CellType.AROUND_SHIP;

                    if((randCol - 1) >= 0)
                    {
                        _cellTypesMap[randRow + shipSize][randCol - 1] = CellType.AROUND_SHIP;
                    }
                    if((randCol + 1) < BOARD_SIZE)
                    {
                        _cellTypesMap[randRow + shipSize][randCol + 1] = CellType.AROUND_SHIP;
                    }
                }
                break;

            case HORIZONTAL:
                for (int col = randCol; col < randCol + ship.getSize(); col++)
                {
                    if(col > (BOARD_SIZE - 1) || _cellTypesMap[randRow][col] != CellType.EMPTY)
                    {
                        placeShip(ship);
                        return;
                    }
                }

                for (int col = randCol; col < randCol + ship.getSize(); col++)
                {
                    _cellTypesMap[randRow][col] = CellType.SHIP;

                    if((randRow - 1) >= 0)
                    {
                        _cellTypesMap[randRow - 1][col] = CellType.AROUND_SHIP;
                    }

                    if((randRow + 1) < 10)
                    {
                        _cellTypesMap[randRow + 1][col] = CellType.AROUND_SHIP;
                    }
                }

                if((randCol - 1) >= 0)
                {
                    _cellTypesMap[randRow][randCol - 1] = CellType.AROUND_SHIP;

                    if((randRow - 1) >= 0)
                    {
                        _cellTypesMap[randRow - 1][randCol - 1] = CellType.AROUND_SHIP;
                    }

                    if((randRow + 1) < 10)
                    {
                        _cellTypesMap[randRow + 1][randCol - 1] = CellType.AROUND_SHIP;
                    }
                }

                if((randCol + shipSize) < 10)
                {
                    _cellTypesMap[randRow][randCol + shipSize] = CellType.AROUND_SHIP;

                    if((randRow - 1) >= 0)
                    {
                        _cellTypesMap[randRow - 1][randCol + shipSize] = CellType.AROUND_SHIP;
                    }

                    if((randRow + 1) < 10)
                    {
                        _cellTypesMap[randRow + 1][randCol + shipSize] = CellType.AROUND_SHIP;
                    }
                }


                break;
        }
    }
}
