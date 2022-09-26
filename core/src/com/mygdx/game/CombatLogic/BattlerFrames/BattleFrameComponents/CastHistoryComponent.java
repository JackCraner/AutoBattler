package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.BattlerStates;
import com.mygdx.game.SpellLogic.Spell;



import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;

public class CastHistoryComponent extends BattleFrameComponent
{

    LinkedList<Map.Entry<Spell, BattlerStates>> castHistoryList;
    public CastHistoryComponent()
    {
        castHistoryList = new LinkedList<>();
    }
    private CastHistoryComponent(LinkedList<Map.Entry<Spell, BattlerStates>> list)
    {
        castHistoryList = list;
    }

    public LinkedList<Map.Entry<Spell, BattlerStates>> getCastHistoryList() {
        return castHistoryList;
    }

    public void addCastHistoryItem(Spell spell,BattlerStates state)
    {
        this.castHistoryList.add(new AbstractMap.SimpleEntry<>(spell,state));
    }


    @Override
    public BattleFrameComponent clone()
    {
        LinkedList<Map.Entry<Spell, BattlerStates>> newList = new LinkedList<>();
        for (Map.Entry<Spell,BattlerStates> e: castHistoryList)
        {
            newList.add(new AbstractMap.SimpleEntry<>(e.getKey(),e.getValue()));
        }
        return new CastHistoryComponent(newList);
    }

    @Override
    public String printComponent() {
        String s = "Cast History:: ";
        for (Map.Entry<Spell,BattlerStates> entry: castHistoryList)
        {
            s+= entry.getKey().getName() + " : " + entry.getValue() + " | ";
        }
        return s;
    }
}
