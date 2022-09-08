package com.mygdx.game.Spells.Statuss;

import com.mygdx.game.Spells.Effects.EffectTypes.CanEffectType;

public class Status {

    private CanEffectType type;
    private int stackNumber;


    private int tickCooldown;

    public Status(CanEffectType statusType, int stackNumber, int cd) {
       this.type = statusType;
       this.tickCooldown = cd;
       this.stackNumber = stackNumber;
    }

    public CanEffectType getType() {
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
