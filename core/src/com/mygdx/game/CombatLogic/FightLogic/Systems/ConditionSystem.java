package com.mygdx.game.CombatLogic.FightLogic.Systems;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.CombatLogic.FightLogic.Cast;
import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanConditionSystem;
import com.mygdx.game.Spells.Conditions.Condition;

import java.util.Random;

public class ConditionSystem
{

    public static ConditionSystem instance = new ConditionSystem();
    private ConditionSystem()
    {

    }

    public boolean checkCondition(Condition condition, BattlerFrame user, BattlerFrame enemy)
    {
        return condition.getCondition().getSystem().check(condition,user,enemy);
    }

    public static final class NullSystem implements CanConditionSystem
    {
        public static NullSystem instance = new NullSystem();
        @Override
        public boolean check(Condition c, BattlerFrame caster, BattlerFrame other) {
            return true;
        }
    }


    public static final class OnPoison implements CanConditionSystem
    {
        public static OnPoison instance = new OnPoison();


        @Override
        public boolean check(Condition c, BattlerFrame caster, BattlerFrame other) {
            return false;
        }
    }

    public static final class OnPercentSystem implements CanConditionSystem
    {
        public static OnPercentSystem instance = new OnPercentSystem();
        Random ran = new Random();
        @Override
        public boolean check(Condition c, BattlerFrame caster, BattlerFrame other)
        {
            return (ran.nextInt(100) > c.getValue());

        }
    }

}
