package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanEffectSystem;

public interface CanEffectType
{

    public String getDescription();
    public String getName();
    public CanEffectSystem getSystem();

}
