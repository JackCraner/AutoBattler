package com.mygdx.game.Spells;

import com.mygdx.game.Screens.FightPhase.EffectSystem;

public interface SpellEffectType
{

    public String getDescription();
    public String getName();
    public EffectSystem getSystem();

}
