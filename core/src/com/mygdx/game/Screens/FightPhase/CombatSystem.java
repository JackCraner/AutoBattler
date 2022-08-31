package com.mygdx.game.Screens.FightPhase;

import com.mygdx.game.Spells.Effects.Effect;
import com.mygdx.game.Spells.Effects.EffectTypes.EffectType;
import com.mygdx.game.Spells.Effects.EffectTypes.HealEffect;
import com.mygdx.game.Spells.Effects.EffectTypes.ManaEffect;
import com.mygdx.game.Spells.Spell;
import com.mygdx.game.Spells.SpellEffectType;
import com.mygdx.game.Spells.Statuss.Status;

import java.util.HashMap;
import java.util.Map;

public class CombatSystem
{

    public CombatSystem()
    {

    }

    public boolean castSpell(Spell s, BattlerFrame userCurrent, BattlerFrame userNew, BattlerFrame targetCurrent, BattlerFrame targetNew)
    {
        if (checkForMana(s,userCurrent,userNew) && checkForBlocks(userCurrent,userNew,targetCurrent,targetNew))
        {
            spendMana(s,userCurrent,userNew);
            for (Effect e:s.getEffects())
            {
                EffectSystem handler = (e.getType().getSystem());
                handler.execute(e,userCurrent,userNew,targetCurrent,targetNew);
            }
        }

        return false;
    }

    public boolean gameTick(Status s,BattlerFrame userCurrent, BattlerFrame userNew)
    {

        s.getType().getSystem().tick(s,userCurrent,userNew);
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
            userNew.setSpellSuccess(true);
            return true;
        }
        else
        {
            userNew.setSpellSuccess(false);
            return false;
        }

    }
    public void spendMana(Spell s,BattlerFrame userCurrent, BattlerFrame userNew)
    {
        userNew.setMana(userCurrent.getMana() - s.getManaCost());
    }



    public static final class DamageSystem implements EffectSystem
    {
        public final static DamageSystem instance = new DamageSystem();

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

    public static final class ManaSystem implements EffectSystem
    {
        public final static  ManaSystem instance = new ManaSystem();

        @Override
        public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew)
        {

            if (effect.getType() == ManaEffect.MANA)
            {
                //userNew.setMana(user.getMana() + effect.getStrength());
            }
            else if (effect.getType() == ManaEffect.POISON)
            {
            }

        }

        @Override
        public boolean tick(Status s, BattlerFrame user, BattlerFrame userNew)
        {
            return false;
        }

    }

    public static final class HealSystem implements EffectSystem
    {
        public static final HealSystem instance = new HealSystem();
        @Override
        public void execute(Effect effect, BattlerFrame user, BattlerFrame userNew, BattlerFrame target, BattlerFrame targetNew)
        {

            if (effect.getType() == HealEffect.HEAL)
            {
                userNew.setHealth(user.getHealth()+ effect.getStrength());
            }
            else if(effect.getType() == HealEffect.POISONHEAL)
            {
                Status s = target.getStatus(EffectType.POISON);
                if (s!=null)
                {
                    userNew.setHealth(user.getHealth()+s.getStackNumber());

                }
            }


        }

        @Override
        public boolean tick(Status s, BattlerFrame user, BattlerFrame userNew) {
            return false;
        }


    }

    public static final class PoisonSystem implements EffectSystem
    {
        public static final PoisonSystem instance = new PoisonSystem();
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
    public static final class BurnSystem implements EffectSystem
    {
        public static final BurnSystem instance = new BurnSystem();
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
    public static final class CounterSpellSystem implements EffectSystem
    {
        public static final CounterSpellSystem instance = new CounterSpellSystem();
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
    public static final class ExpungeSystem implements EffectSystem
    {
        public static final ExpungeSystem instance = new ExpungeSystem();
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
