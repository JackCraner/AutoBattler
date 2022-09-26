package com.mygdx.game.CombatLogic.EffectSystems;



import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastHistoryComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.SpellListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerStates;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.CastTimes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

import java.util.Map;

public class CastNumberSystem implements IsEffectSystem<CastTimes> {
    public static CastNumberSystem instance = new CastNumberSystem();
    private CastNumberSystem()
    {

    }
    @Override
    public void execute(CastTimes effect, BattlerFrame[] battlers)
    {

        Spell battlerCastSpell = battlers[TargetType.SELF.getValue()].getComponent(SpellListComponent.class).getCurrent();
        CastHistoryComponent battlerCastHistory = battlers[TargetType.SELF.getValue()].getComponent(CastHistoryComponent.class);
        if (effect.getNumOfCasts() == 1)
        {
            battlers[TargetType.SELF.getValue()].getComponent(SpellListComponent.class).removeSpell(battlerCastSpell);
        }
        else
        {
            int counter = 1;
            for (Map.Entry<Spell, BattlerStates> entry : battlerCastHistory.getCastHistoryList())
            {
                if (entry.getKey().getName() == battlerCastSpell.getName())
                {
                    counter++;
                }
            }
            if (counter == effect.getNumOfCasts())
            {
                battlers[TargetType.SELF.getValue()].getComponent(SpellListComponent.class).removeSpell(battlerCastSpell);
            }
        }


    }
}
