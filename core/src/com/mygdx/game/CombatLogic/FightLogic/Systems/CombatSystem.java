package com.mygdx.game.CombatLogic.FightLogic.Systems;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.CombatLogic.FightLogic.Cast;
import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanEffectSystem;
import com.mygdx.game.Spells.Effects.CanEffect;
import com.mygdx.game.Spells.Effects.Effect;

public class CombatSystem
{

    public static CombatSystem instance = new CombatSystem();
    private CombatSystem()
    {}


    public boolean castSpell(Cast c, BattlerFrame caster, BattlerFrame other)
    {


        //Check for mana
        //Do spell
        //Set New cast
        for (CanEffect effect:c.getSpellWrapper().getCondition().getEffect(ConditionSystem.instance.checkCondition(c.getSpellWrapper().getCondition(),caster,other)))
        {
            CanEffectSystem handler = effect.getSystem();
            if (handler != null)
            {
                handler.execute(effect,new BattlerFrame[]{caster,other});
            }
            else
            {
                System.out.println(effect.getType().toString() + " NO SYSTEM");
            }

        }
        return false;

    }


    public static class DamageSystem implements CanEffectSystem {

        public static DamageSystem instance = new DamageSystem();
        @Override
        public void execute(CanEffect effect,BattlerFrame[] battlers)
        {
            battlers[effect.getTarget().getValue()].setHealth(battlers[effect.getTarget().getValue()].getHealth() - (int)effect.getStrength());
        }
    }

    public static class HealSystem implements CanEffectSystem {

        @Override
        public void execute(CanEffect effect,BattlerFrame[] battlers)
        {
            battlers[effect.getTarget().getValue()].setHealth(battlers[effect.getTarget().getValue()].getHealth() + (int)effect.getStrength());
        }
    }

    public static class ManaSystem implements CanEffectSystem
    {

        @Override
        public void execute(CanEffect effect, BattlerFrame[] battlers)
        {
            battlers[effect.getTarget().getValue()].setMana(battlers[effect.getTarget().getValue()].getMana() + (int)effect.getStrength());
        }

    }





}
