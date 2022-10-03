package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.BattleFieldSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.BattlegroundTypes;

public class ApplyBattleground extends IsEffectComponent
{

    private BattlegroundTypes battleground;
    public ApplyBattleground(BattlegroundTypes battleground)
    {
        this.battleground = battleground;
    }
    @Override
    public String printEffect() {
        return "Change battleground to " + battleground.name();
    }

    public BattlegroundTypes getBattleground() {
        return battleground;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers, BattlerFrame[] newBattlers) {

        BattleFieldSystem.instance.execute(this,battlers,newBattlers );
    }

    @Override
    public IsEffectComponent clone() {
        return new ApplyBattleground(battleground);
    }
}
