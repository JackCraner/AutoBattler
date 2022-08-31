package com.mygdx.game.Screens.FightPhase;

import com.mygdx.game.Spells.Effects.Effect;
import com.mygdx.game.Spells.Effects.EffectBase;
import com.mygdx.game.Spells.Statuss.Status;

public interface EffectSystem
{
    public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew);
    public boolean tick(Status s, BattlerFrame user, BattlerFrame userNew);

}
