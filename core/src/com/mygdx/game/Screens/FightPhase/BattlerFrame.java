package com.mygdx.game.Screens.FightPhase;

import com.mygdx.game.Spells.Effects.EffectType;
import com.mygdx.game.Spells.SpellEffectType;
import com.mygdx.game.Spells.Statuss.Status;

import java.util.ArrayList;
import java.util.Arrays;

public class BattlerFrame
{
    private int health;
    private int mana;
    private ArrayList<Status> statusTypes;
    private int cooldown;
    private int spellPointer;
    public BattlerFrame(int h,int m,int c,int sp,Status[] statusTypes)
    {
        this.health = h;
        this.mana = m;
        this.cooldown = c;
        this.statusTypes = new ArrayList<Status>(Arrays.asList(statusTypes));
        this.spellPointer = sp;
    }
    public BattlerFrame(int h,int m,int c,int sp,ArrayList statusTypes)
    {
        this.health = h;
        this.mana = m;
        this.cooldown = c;
        this.statusTypes = statusTypes;
        this.spellPointer = sp;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpellPointer() {
        return spellPointer;
    }

    public void setSpellPointer(int spellPointer) {
        this.spellPointer = spellPointer;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public ArrayList<Status> getAllStatus() {
        return statusTypes;
    }

    public Status getStatus(SpellEffectType type)
    {
        for (Status s: statusTypes)
        {
            if (s.getType() == type)
            {
                return s;
            }

        }
        return null;
    }


    public BattlerFrame clone()
    {
        ArrayList<Status> newStatus = new ArrayList<>();
        for (Status s: statusTypes)
        {
            newStatus.add(s.clone());
        }
        return new BattlerFrame(health,mana,cooldown,spellPointer,newStatus);
    }

    public String printFrame()
    {
        return null;
    }
}
