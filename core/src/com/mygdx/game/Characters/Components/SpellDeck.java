package com.mygdx.game.Characters.Components;

import com.mygdx.game.Spells.Spell;

import java.util.ArrayList;
import java.util.List;

public class SpellDeck
{

    ArrayList<Spell> spellList = new ArrayList<>();
    public SpellDeck()
    {

    }
    public void addSpell(Spell s)
    {
        spellList.add(s);

    }

    public void setSpellList(ArrayList<Spell> spellList) {
        this.spellList = spellList;
    }

    public List<Spell> getSpellList() {
        return spellList;
    }
}
