package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.RepeatN;

public class RepeatSystem implements IsEffectSystem<RepeatN>
{
    public static RepeatSystem instance = new RepeatSystem();
    private RepeatSystem()
    {

    }
    @Override
    public void execute(RepeatN effect, BattlerFrame[] battlers)
    {

        CastComponent battlerComponent = battlers[effect.getTarget().getValue()].getComponent(CastComponent.class);
        Effect[] spellEffectList = battlerComponent.getSpell().getEffects();
        Effect[] newSpellEffectList = new Effect[spellEffectList.length * effect.getNumberOfRepeats()];
        for (int i=0;i<effect.getNumberOfRepeats();i++)
        {
            for (int e = 0; e<spellEffectList.length;e++)
            {
                newSpellEffectList[(i*spellEffectList.length)+e] = spellEffectList[e];
            }
        }

        battlerComponent.getSpell().setEffect(newSpellEffectList);
    }
}
