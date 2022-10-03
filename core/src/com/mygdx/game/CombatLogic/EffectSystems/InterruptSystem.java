package com.mygdx.game.CombatLogic.EffectSystems;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Interrupt;

public class InterruptSystem implements IsEffectSystem<Interrupt> {

    public static InterruptSystem instance = new InterruptSystem();
    @Override
    public void execute(Interrupt effect, BattlerFrame[] battlers) {
        battlers[effect.getTarget().getValue()].getComponent(CastComponent.class).setCastTimer(1);
    }
}
