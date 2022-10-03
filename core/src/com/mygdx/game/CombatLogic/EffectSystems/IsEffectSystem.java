package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IsEffectComponent;

public interface IsEffectSystem<T extends IsEffectComponent>
{
    public void execute(T effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers);



}
