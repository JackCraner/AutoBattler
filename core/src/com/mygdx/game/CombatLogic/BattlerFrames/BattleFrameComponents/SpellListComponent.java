package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.CircularList;
import com.mygdx.game.SpellLogic.Spell;

import java.util.List;

public class SpellListComponent extends BattleFrameComponent{


    private CircularList<Spell> spellList;
    public SpellListComponent()
    {
        spellList= new CircularList<>();
    }

    public SpellListComponent(CircularList<Spell> defaultList)
    {
        this.spellList = defaultList;
    }

    public List<Spell> getSpellList() {
        return spellList.getList();
    }

    public void pushNext()
    {
        spellList.pushList();
    }
    public Spell getCurrent()
    {

        return spellList.getCurrent();
    }
    public Spell getNext()
    {
        spellList.pushList();
        return spellList.getCurrent();
    }
    public void addSpell(Spell s)
    {
        spellList.addItem(s);
    }
    public void removeSpell(Spell s)
    {
        spellList.removeItem(s);
    }


    @Override
    public BattleFrameComponent clone()
    {
        CircularList<Spell> newList = new CircularList<>(spellList.getPointer());
        for (Spell s: spellList.getList())
        {

            newList.addItem(s.clone());
        }

        return new SpellListComponent(newList);
    }

    @Override
    public String printComponent() {
        String s =  "SpellList:: ";
        for (Spell spell:spellList.getList())
        {
            s+=spell.getName() + " | ";
        }
        return s;
    }
}
