package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;

import com.mygdx.game.SpellLogic.SpellEffect.Enums.BattlegroundTypes;


public class BattlegroundComponent extends BattleFrameComponent{

    private BattlegroundTypes battleground;
    public BattlegroundComponent(BattlegroundTypes battleground)
    {
        this.battleground =battleground;
    }

    public BattlegroundTypes getBattleground() {
        return battleground;
    }

    public void setBattleground(BattlegroundTypes battleground) {
        this.battleground = battleground;
    }

    @Override
    public BattleFrameComponent clone() {
        return new BattlegroundComponent(battleground);
    }

    @Override
    public String printComponent() {
        return "Current Battleground: " + battleground.name();
    }
}
