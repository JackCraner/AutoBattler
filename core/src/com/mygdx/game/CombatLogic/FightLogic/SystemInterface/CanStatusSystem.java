package com.mygdx.game.CombatLogic.FightLogic.SystemInterface;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.Spells.Statuss.Status;

public interface CanStatusSystem extends CanEffectSystem
{

    public boolean tick(Status s, BattlerFrame user);
}
