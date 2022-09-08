package com.mygdx.game.CombatLogic.FightLogic;

import java.util.ArrayList;
import java.util.List;

public class CircularList<T>
{

    List<T> list;
    int pointer;
    public CircularList()
    {
        list = new ArrayList<>();
        this.pointer = 0;
    }
    public CircularList(List<T> set)
    {
        list = set;
        this.pointer = 0;
    }
    public void set(List<T> a)
    {
        list = a;
    }

    public T get(int index)
    {
        return list.get(index);
    }
    public T next()
    {
        if (pointer == list.size())
        {
            pointer = 0;
        }
        T returnObject = list.get(pointer);
        pointer ++;
        return returnObject;
    }




}
