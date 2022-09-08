package com.mygdx.game.Characters;

import com.mygdx.game.Perks.Perk;
import com.mygdx.game.Spells.Spell;

import java.util.ArrayList;
import java.util.List;

public class Battler
{

    private int health = 100;
    private int mana = 50;
    private ArrayList<Spell> spellDeck;
    private ArrayList<Perk> perks;

    private CharacterTypes cT;
    public Battler(CharacterTypes cT)
    {
        this.spellDeck = new ArrayList<>();
        this.perks = new ArrayList<>();
        spellDeck.add(Spell.FIREBALL);
        spellDeck.add(Spell.FIREBALL);
    }

    public CharacterTypes getcT() {
        return cT;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxHealth()
    {
        return health;
    }

    public Perk[] getPerks()
    {
        return perks.toArray(new Perk[perks.size()]);

    }


    public Spell[] getSpells()
    {
        return spellDeck.toArray(new Spell[spellDeck.size()]);
    }
    public void addSpell(Spell s)
    {
        spellDeck.add(s);
    }
    public void setSpellDeck(List<Spell> newSpellDeck)
    {
        this.spellDeck = (ArrayList<Spell>) newSpellDeck;
    }
}
