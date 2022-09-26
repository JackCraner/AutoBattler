package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BattlegroundComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.CombatSystems.CastSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ApplyBattleground;

public class BattleFieldSystem implements IsEffectSystem<ApplyBattleground>
{
    public static BattleFieldSystem instance = new BattleFieldSystem();


    public void execute(ApplyBattleground ab, BattlerFrame[] battlers)
    {
        for (int i =0;i<battlers.length;i++)
        {
            battlers[i].getComponent(BattlegroundComponent.class).setBattleground(ab.getBattleground());
        }
    }
    public void applyBattleground(BattlerFrame[] battlers)
    {
        for (int i =0;i<battlers.length;i++)
        {
            CastSystem.instance.routeEffect(battlers[i].getComponent(BattlegroundComponent.class).getBattleground().getEffect(),new BattlerFrame[]{battlers[i],battlers[i]});
        }
    }




}
