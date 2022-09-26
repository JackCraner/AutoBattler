package com.mygdx.game.CombatLogic;

import com.mygdx.game.CombatLogic.BattlerFrames.CircularList;
import com.mygdx.game.CombatLogic.BattlerFrames.ModifierArray;
import com.mygdx.game.SpellLogic.Spell;


import java.util.ArrayList;
import java.util.Arrays;

public class Battler
{

    int health;
    int maxMana;
    int currentMana;
    private ModifierArray armorSet;
    private ModifierArray buffSet;
    private CircularList<Spell> spellList;

    public Battler(int health, int mana, ModifierArray armorSet, ModifierArray buffSet,Spell[] spellsList)
    {
        this.health = health;
        this.maxMana = mana;
        this.currentMana = mana;
        this.armorSet = armorSet;
        this.buffSet = buffSet;
        this.spellList = new CircularList<Spell>(spellsList);
    }

    public ModifierArray getArmorSet() {
        return armorSet;
    }

    public ModifierArray getBuffSet() {
        return buffSet;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int mana) {
        this.currentMana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public CircularList<Spell> getSpellList() {
        return spellList;
    }

    public void setSpellList(ArrayList<Spell> spellList) {
        this.spellList = new CircularList<>(spellList);
    }
}
