package com.mygdx.game.Screens.FightPhase;

import com.mygdx.game.Perks.Perk;
import com.mygdx.game.CombatLogic.FightLogic.CircularList;
import com.mygdx.game.Spells.Spell;

import java.util.List;

public class BattlerConstants
{


    private CircularList<Spell> battlerSpellList;
    private Perk[] battlerPerkList;
    public BattlerConstants(List<Spell> spellList, Perk[] perkList)
    {
        this.battlerSpellList = new CircularList<>(spellList);
        this.battlerPerkList = perkList;
    }

    public Perk[] getBattlerPerkList() {
        return battlerPerkList;
    }

    public CircularList<Spell> getBattlerSpellList() {
        return battlerSpellList;
    }
}
