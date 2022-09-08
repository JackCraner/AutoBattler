package com.mygdx.game.CombatLogic.FightLogic.SystemInterface;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.Spells.Effects.CanEffect;

public interface CanEffectSystem
{
    public void execute(CanEffect effect, BattlerFrame[] battlers);


}
