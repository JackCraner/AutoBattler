package com.mygdx.game.CombatLogic.BattlerFrames;

import com.mygdx.game.SpellLogic.Spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CircularList<T>
{
    ArrayList<T> list = new ArrayList<>();
    int pointer;
    public CircularList()
    {
        this.pointer = 0;
    }
    public CircularList(ArrayList<T> set)
    {
        this.list =  set;
        this.pointer = 0;
    }
    public CircularList(int pointer)
    {
        this.list = new ArrayList<>();
        this.pointer = pointer;
    }

    public CircularList(ArrayList<T> set, int pointer)
    {
        this.list = set;
        this.pointer = pointer;
    }
    public CircularList(T[] set)
    {
        this.list =  new ArrayList<>(Arrays.asList(set));
        this.pointer = 0;
    }


    public T getCurrent()
    {
        return list.get(pointer);
    }
    public void pushList()
    {
        pointer++;
        if (pointer == list.size())
        {
            pointer =0;
        }

    }
    public void addItem(T newItem)
    {
        list.add(newItem);
    }
    public void removeItem(T item)
    {
        list.remove(item);
        pushList();
    }
    public List<T> getList() {
        return list;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }






}
