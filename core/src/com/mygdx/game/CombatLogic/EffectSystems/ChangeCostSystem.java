package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ChangeCost;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ModifierType;

public class ChangeCostSystem implements IsEffectSystem<ChangeCost> {
    public static ChangeCostSystem instance = new ChangeCostSystem();

    private ChangeCostSystem()
    {

    }


    @Override
    public void execute(ChangeCost effect, BattlerFrame[] battlers)
    {
        if (effect.getType() == ModifierType.ABSOLUTE)
        {
            battlers[effect.getTarget().getValue()].getComponent(CastComponent.class).getSpell().setManaCost(effect.getNewCost());
        }
        else if (effect.getType() == ModifierType.ADDITIVE)
        {
            Spell s = battlers[effect.getTarget().getValue()].getComponent(CastComponent.class).getSpell();
            if (s.getManaCost() + effect.getNewCost() < 0)
            {
                s.setManaCost(0);
            }
            else
            {
                s.setManaCost(s.getManaCost() + effect.getNewCost());
            }

        }

    }
}
