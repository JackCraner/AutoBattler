package com.mygdx.game.Characters;

import com.mygdx.game.Characters.Components.SpellDeck;
import com.mygdx.game.Spells.Spell;

import java.util.ArrayList;

public class Battler
{

    private int health = 100;
    private int mana = 50;
    private SpellDeck spellDeck;

    private CharacterTypes cT;
    public Battler(CharacterTypes cT)
    {
        this.spellDeck = new SpellDeck();
        spellDeck.addSpell(Spell.FIREBALL);
        spellDeck.addSpell(Spell.FIREBALL);
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


    public SpellDeck getSpellDeck() {
        return spellDeck;
    }
}
