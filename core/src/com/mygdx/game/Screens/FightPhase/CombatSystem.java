package com.mygdx.game.Screens.FightPhase;

import com.mygdx.game.Spells.Effects.Effect;
import com.mygdx.game.Spells.Effects.EffectBase;
import com.mygdx.game.Spells.Effects.EffectType;
import com.mygdx.game.Spells.Spell;
import com.mygdx.game.Spells.SpellEffectType;
import com.mygdx.game.Spells.Statuss.Status;

import java.util.HashMap;
import java.util.Map;

public class CombatSystem
{
    public static Map<SpellEffectType,EffectSystem> logicMap = new HashMap<>();


    static
    {
        logicMap.put(EffectType.DAMAGE,new DamageSystem());
        logicMap.put(EffectType.HEAL,new HealSystem());
        logicMap.put(EffectType.POISON,new PoisonSystem());
        logicMap.put(EffectType.BURN,new BurnSystem());
        logicMap.put(EffectType.COUNTERSPELL,new CounterSpellSystem());
        logicMap.put(EffectType.EXPUNGE,new ExpungeSystem());
        logicMap.put(EffectType.POISONHEAL,new PoisonHealSystem());
    }
    public CombatSystem()
    {

    }

    public boolean castSpell(Spell s, BattlerFrame userCurrent, BattlerFrame userNew, BattlerFrame targetCurrent, BattlerFrame targetNew)
    {
        if (checkForMana(s,userCurrent,userNew) && checkForBlocks(userCurrent,userNew,targetCurrent,targetNew))
        {
            for (Effect e:s.getEffects())
            {
                EffectSystem handler = logicMap.get(e.getType());
                handler.execute(e,userCurrent,userNew,targetCurrent,targetNew);
            }
        }

        return false;
    }

    public boolean gameTick(Status s,BattlerFrame userCurrent, BattlerFrame userNew)
    {
        EffectSystem handler = logicMap.get(s.getType());
        handler.tick(s,userCurrent,userNew);
        return false;
    }
    public boolean checkForBlocks(BattlerFrame userCurrent, BattlerFrame userNew, BattlerFrame targetCurrent, BattlerFrame targetNew)
    {
        for (Status status: targetCurrent.getAllStatus())
        {
            if (status.getType() == EffectType.COUNTERSPELL)
            {
                if (status.getStackNumber() == 1)
                {
                    for (Status status1: targetNew.getAllStatus())
                    {
                        if (status.getType() == EffectType.COUNTERSPELL)
                        {
                            targetNew.getAllStatus().remove(status1);
                        }
                    }

                }
                else
                {
                    status.setStackNumber(status.getStackNumber() - 1);
                }


                return false;
            }
        }
        return true;
    }
    public boolean checkForMana(Spell s,BattlerFrame userCurrent, BattlerFrame userNew)
    {
        if (userNew.getMana() > s.getManaCost())
        {
            userNew.setMana(userCurrent.getMana() - s.getManaCost());
            userNew.setSpellSuccess(true);
            return true;
        }
        else
        {
            userNew.setSpellSuccess(false);
            return false;
        }

    }


    private static final class DamageSystem implements EffectSystem
    {


        @Override
        public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew)
        {
            Effect damage = (Effect)effect;
            if (targetNew.getHealth() - damage.getStrength() < 0)
            {
                targetNew.setHealth(0);
            }
            else
            {
                targetNew.setHealth(targetNew.getHealth() - damage.getStrength());
            }

        }

        @Override
        public boolean tick(Status s,BattlerFrame user, BattlerFrame userNew)
        {
            return false;
        }
    }
    private static final class HealSystem implements EffectSystem
    {

        @Override
        public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew) {
            userNew.setHealth(user.getHealth()+((Effect) effect).getStrength());
        }

        @Override
        public boolean tick(Status s, BattlerFrame user, BattlerFrame userNew) {
            return false;
        }
    }
    private static final class PoisonHealSystem implements EffectSystem
    {
        @Override
        public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew) {
            Status s = target.getStatus(EffectType.POISON);
            if (s!=null)
            {
                userNew.setHealth(userNew.getHealth()+s.getStackNumber());
            }

        }

        @Override
        public boolean tick(Status s, BattlerFrame user, BattlerFrame userNew) {
            return false;
        }
    }

    private static final class PoisonSystem implements EffectSystem
    {
        final int tickTime = 2;
        @Override
        public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew)
        {
            for (Status s: targetNew.getAllStatus())
            {
                if (s.getType() == EffectType.POISON)
                {
                    s.setStackNumber(s.getStackNumber()+effect.getStrength());

                    return;
                }
            }
            targetNew.getAllStatus().add(new Status(EffectType.POISON,effect.getStrength(),getTickTime(targetNew) +1));

        }

        @Override
        public boolean tick(Status status, BattlerFrame user, BattlerFrame userNew)
        {

            if (status.getTickCooldown() == 0)
            {
                userNew.setHealth(userNew.getHealth() - status.getStackNumber());
                if (status.getStackNumber() == 1)
                {

                    userNew.getAllStatus().remove(status);
                }
                else
                {
                    status.setTickCooldown(getTickTime(userNew));
                    status.setStackNumber(status.getStackNumber()-1);
                }
            }
            else
            {
                status.setTickCooldown(status.getTickCooldown() - 1);
            }
            return false;


        }

        private int getTickTime(BattlerFrame userNew)
        {
            Status s = userNew.getStatus(EffectType.EXPUNGE);
            if(s !=null)
            {
                return tickTime -2;
            }
            else
            {
                return tickTime;
            }
        }
    }
    private static final class BurnSystem implements EffectSystem
    {
        final int tickTime = 2;
        @Override
        public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew) {
            for (Status s: targetNew.getAllStatus())
            {
                if (s.getType() == EffectType.BURN)
                {
                    s.setStackNumber(s.getStackNumber()+effect.getStrength());

                    return;
                }
            }
            targetNew.getAllStatus().add(new Status(EffectType.BURN,effect.getStrength(),tickTime+1));
        }

        @Override
        public boolean tick(Status s, BattlerFrame user, BattlerFrame userNew) {
            if (s.getTickCooldown() == 0) {
                userNew.setHealth(userNew.getHealth() - s.getStackNumber());
                s.setTickCooldown(tickTime);
            }
            else
            {
                s.setTickCooldown(s.getTickCooldown() - 1);
            }

            return false;
        }
    }
    private static final class CounterSpellSystem implements EffectSystem
    {

        @Override
        public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew)
        {
            for (Status s: userNew.getAllStatus())
            {
                if (s.getType() == EffectType.COUNTERSPELL)
                {
                    s.setStackNumber(Math.max(effect.getStrength(),s.getStackNumber()));

                    return;
                }
            }
            userNew.getAllStatus().add(new Status(EffectType.COUNTERSPELL,effect.getStrength(),-1));
        }

        @Override
        public boolean tick(Status s, BattlerFrame user, BattlerFrame userNew)
        {


            return false;
        }
    }
    private static final class ExpungeSystem implements EffectSystem
    {
        int tickTime = 0;
        @Override
        public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew)
        {
            Status s = targetNew.getStatus(EffectType.EXPUNGE);
            if (s !=null)
            {
                s.setStackNumber(Math.max(effect.getStrength(),s.getStackNumber()));
            }
            else
            {
                targetNew.getAllStatus().add(new Status(EffectType.EXPUNGE,effect.getStrength(),tickTime+1));
            }
            s = targetNew.getStatus(EffectType.POISON);
            if (s!=null)
            {
                s.setTickCooldown(0);
            }


        }

        @Override
        public boolean tick(Status s, BattlerFrame user, BattlerFrame userNew)
        {
            if (s.getStackNumber() == 1)
            {
                userNew.getAllStatus().remove(s);
            }
            else
            {
                if (s.getTickCooldown() == 0) {
                    s.setStackNumber(s.getStackNumber()-1);
                    s.setTickCooldown(tickTime);
                }
                else
                {
                    s.setTickCooldown(s.getTickCooldown() - 1);
                }
            }

            return false;
        }
    }



}
