package com.mygdx.game.CombatLogic.FightLogic.Systems;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanConditionSystem;
import com.mygdx.game.Spells.Conditions.Condition;

public class ConditionSystem
{

    public static ConditionSystem instance = new ConditionSystem();
    private ConditionSystem()
    {

    }

    public boolean checkCondition(Condition condition, BattlerFrame user, BattlerFrame enemy)
    {
        return condition.getCondition().getSystem().check();
    }



    public static final class OnPoison implements CanConditionSystem
    {
        public static OnPoison instance = new OnPoison();

        @Override
        public boolean check() {
            return false;
        }
    }
}
