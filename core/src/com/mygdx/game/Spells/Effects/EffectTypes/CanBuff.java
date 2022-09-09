package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanBuffSystem;

public interface CanBuff extends CanEffectType
{
    public CanBuffSystem getBuffSystem();
}
