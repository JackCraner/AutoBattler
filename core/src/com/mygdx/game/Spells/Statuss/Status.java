package com.mygdx.game.Spells.Statuss;

import com.mygdx.game.Spells.Effects.Effect;
import com.mygdx.game.Spells.Effects.EffectBase;
import com.mygdx.game.Spells.Effects.EffectType;
import com.mygdx.game.Spells.SpellEffectType;

public class Status {

    private SpellEffectType type;
    private int stackNumber;


    private int tickCooldown;

    public Status(SpellEffectType statusType, int stackNumber, int cd) {
       this.type = statusType;
       this.tickCooldown = cd;
       this.stackNumber = stackNumber;
    }

    public SpellEffectType getType() {
        return type;
    }

    public void setTickCooldown(int tickCooldown) {
        this.tickCooldown = tickCooldown;
    }

    public int getTickCooldown() {
        return tickCooldown;
    }

    public void setStackNumber(int stackNumber) {
        this.stackNumber = stackNumber;
    }

    public int getStackNumber() {
        return stackNumber;
    }
    public Status clone()
    {
        return new Status(type,stackNumber,tickCooldown);
    }
}
