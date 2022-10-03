package com.mygdx.game.CombatLogic.EffectSystems;



import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastHistoryComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerStates;
import com.mygdx.game.CombatLogic.CombatSystems.CastSystem;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Channel;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

import java.util.Map;

public class ChannelSystem implements  IsEffectSystem<Channel> {

    public static ChannelSystem instance = new ChannelSystem();
    private ChannelSystem()
    {

    }



    @Override
    public void execute(Channel effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {

        CastHistoryComponent battlerHistory = battlers[TargetType.SELF.getValue()].getComponent(CastHistoryComponent.class);
        CastComponent battlerCast = newBattlers[TargetType.SELF.getValue()].getComponent(CastComponent.class);
        CastSystem.instance.routeEffect(effect.getEffect(), battlers, newBattlers);


        for (int i = 0;i< battlerHistory.getCastHistoryList().size();i++)
        {
            Map.Entry<Spell, BattlerStates> currentEntry = battlerHistory.getCastHistoryList().get(i);
            if (currentEntry.getKey().getName() == battlerCast.getSpell().getName())
            {
                CastSystem.instance.routeEffect(effect.getEffect(), battlers, newBattlers);
            }
        }


    }
}
