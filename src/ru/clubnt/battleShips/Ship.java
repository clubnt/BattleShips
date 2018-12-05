package ru.clubnt.battleShips;

import java.util.Random;


public class Ship
{


    public enum ShipOrientation
    {
        HORIZONTAL,
        VERTICAL;

        public static ShipOrientation getRandomValue()
        {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    private ShipOrientation _orientation;
    private int _size;


    public Ship(int size, ShipOrientation orientation)
    {
        _orientation = orientation;
        _size = size;
    }


    public ShipOrientation getOrientation()
    {
        return _orientation;
    }


    public int getSize()
    {
        return _size;
    }


}
