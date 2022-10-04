package com.mygdx.game.CombatLogic.EffectSystems;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastHistoryComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.SpellListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.AddSpell;

public class AddSpellSystem implements IsEffectSystem<AddSpell>
{
    public static AddSpellSystem instance = new AddSpellSystem();
    private AddSpellSystem()
    {

    }



    @Override
    public void execute(AddSpell effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {
        if (effect.getSpell() == null)
        {
            CastHistoryComponent castHistory = newBattlers[Math.abs(effect.getTarget().getValue()-1)].getComponent(CastHistoryComponent.class);
            if (castHistory.getCastHistoryList().size() >= 1)
            {
                Spell spell = castHistory.getCastHistoryList().get(castHistory.getCastHistoryList().size()-1).getKey();
                newBattlers[effect.getTarget().getValue()].getComponent(SpellListComponent.class).addSpell(spell);
            }

        }
        else
        {
            newBattlers[effect.getTarget().getValue()].getComponent(SpellListComponent.class).addSpell(effect.getSpell());
        }


    }
}
