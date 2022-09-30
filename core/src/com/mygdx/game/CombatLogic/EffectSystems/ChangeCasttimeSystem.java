package com.mygdx.game.CombatLogic.EffectSystems;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ChangeCastTime;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ModifierType;

public class ChangeCasttimeSystem implements IsEffectSystem<ChangeCastTime> {
    public static ChangeCasttimeSystem instance = new ChangeCasttimeSystem();

    private ChangeCasttimeSystem()
    {

    }
    @Override
    public void execute(ChangeCastTime effect, BattlerFrame[] battlers)
    {
        if (effect.getCastTimeType() == ModifierType.ABSOLUTE)
        {
            battlers[effect.getTarget().getValue()].getComponent(CastComponent.class).getSpell().setCastTime(effect.getCastTimeModifier());
        }
        else if (effect.getCastTimeType() == ModifierType.ADDITIVE)
        {
            Spell s = battlers[effect.getTarget().getValue()].getComponent(CastComponent.class).getSpell();
            if (s.getCastTime() + effect.getCastTimeModifier() < 1)
            {
                s.setCastTime(1);
            }
            else
            {
                s.setCastTime(s.getCastTime() + effect.getCastTimeModifier());
            }

        }
    }
}
