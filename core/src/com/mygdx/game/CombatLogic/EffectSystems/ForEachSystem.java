package com.mygdx.game.CombatLogic.EffectSystems;



import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.EffectListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.EffectOnBattler;
import com.mygdx.game.CombatLogic.CombatSystems.CastSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ForEach;

import java.util.Objects;

public class ForEachSystem implements IsEffectSystem<ForEach>
{
    public static ForEachSystem instance = new ForEachSystem();

    @Override
    public void execute(ForEach effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {

        EffectListComponent battlerComponent = newBattlers[effect.getTarget().getValue()].getComponent(EffectListComponent.class);
        for (EffectOnBattler e: battlerComponent.getEffectOnBattlers())
        {

            if (Objects.equals(e.getStatusObject().getStatus_name(), effect.getDeterminant()))
            {

                for (int i =(int)effect.getRatio(); i<=e.getStackNumber();i += effect.getRatio())
                {

                    CastSystem.instance.routeEffect(effect.getEffect(), battlers, newBattlers);

                }
            }
        }
    }
}
