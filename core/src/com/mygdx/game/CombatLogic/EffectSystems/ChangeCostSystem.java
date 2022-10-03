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
    public void execute(ChangeCost effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {
        if (effect.getType() == ModifierType.ABSOLUTE)
        {
            newBattlers[effect.getTarget().getValue()].getComponent(CastComponent.class).getSpell().setManaCost(effect.getNewCost());
        }
        else if (effect.getType() == ModifierType.ADDITIVE)
        {
            Spell s = newBattlers[effect.getTarget().getValue()].getComponent(CastComponent.class).getSpell();
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
