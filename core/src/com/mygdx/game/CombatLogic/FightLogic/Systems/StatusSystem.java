package com.mygdx.game.CombatLogic.FightLogic.Systems;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanEffectSystem;
import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanStatusSystem;
import com.mygdx.game.Spells.Effects.CanEffect;
import com.mygdx.game.Spells.Effects.EffectTypes.StatusEffect;
import com.mygdx.game.Spells.Statuss.Status;

public class StatusSystem
{

    public static StatusSystem instance = new StatusSystem();
    private StatusSystem()
    {}


    public void handleTick(Status s, BattlerFrame holder)
    {
        CanStatusSystem handler = (CanStatusSystem) s.getType().getSystem();

        if (handler != null)
        {
            handler.tick(s,holder);
        }
        else
        {
            System.out.println(s.getType().toString() + " NO SYSTEM");
        }

    }



    public static class PoisonSystem implements CanStatusSystem
    {
        public static PoisonSystem instance = new PoisonSystem();
        final int tickTime = 2;

        @Override
        public boolean tick(Status s, BattlerFrame user)
        {

            user.setHealth(user.getHealth() - s.getStackNumber());
            if (s.getStackNumber() == 1)
            {

                user.getAllStatus().remove(s);
            }
            else
            {
                s.setTickCooldown(tickTime);
                s.setStackNumber(s.getStackNumber()-1);
            }


            return false;

        }

        @Override
        public void execute(CanEffect effect, BattlerFrame[] battlers)
        {
            for (Status s: battlers[effect.getTarget().getValue()].getAllStatus())
            {
                if (s.getType() == StatusEffect.POISON)
                {
                    s.setStackNumber(s.getStackNumber()+(int)effect.getStrength());

                    return;
                }
            }

            battlers[effect.getTarget().getValue()].getAllStatus().add(new Status(StatusEffect.POISON,(int)effect.getStrength(),0));

        }
    }
    public static final class BurnSystem implements CanStatusSystem
    {
        public static final BurnSystem instance = new BurnSystem();
        final int tickTime = 2;


        @Override
        public boolean tick(Status s, BattlerFrame user)
        {
            user.setHealth(user.getHealth() - s.getStackNumber());
            return false;
        }

        @Override
        public void execute(CanEffect effect, BattlerFrame[] battlers)
        {
            for (Status s: battlers[effect.getTarget().getValue()].getAllStatus())
            {
                if (s.getType() == StatusEffect.BURN)
                {
                    s.setStackNumber(s.getStackNumber()+(int)effect.getStrength());

                    return;
                }
            }

            battlers[effect.getTarget().getValue()].getAllStatus().add(new Status(StatusEffect.BURN,(int)effect.getStrength(),0));

        }
    }

    public static final class FreezeSystem implements CanStatusSystem
    {
        public static FreezeSystem instance = new FreezeSystem();
        @Override
        public void execute(CanEffect effect, BattlerFrame[] battlers)
        {
            for (Status s: battlers[effect.getTarget().getValue()].getAllStatus())
            {
                if (s.getType() == StatusEffect.FREEZE)
                {
                    s.setStackNumber(s.getStackNumber()+(int)effect.getStrength());

                    return;
                }
            }

            battlers[effect.getTarget().getValue()].getAllStatus().add(new Status(StatusEffect.FREEZE,(int)effect.getStrength(),0));
        }

        @Override
        public boolean tick(Status s, BattlerFrame user)
        {
            return false;
        }
    }

    public static final class RipTideSystem implements CanStatusSystem
    {
        public static RipTideSystem instance = new RipTideSystem();
        @Override
        public void execute(CanEffect effect, BattlerFrame[] battlers)
        {
            for (Status s: battlers[effect.getTarget().getValue()].getAllStatus())
            {
                if (s.getType() == StatusEffect.RIPTIDE)
                {
                    s.setStackNumber(s.getStackNumber()+(int)effect.getStrength());

                    return;
                }
            }

            battlers[effect.getTarget().getValue()].getAllStatus().add(new Status(StatusEffect.RIPTIDE,(int)effect.getStrength(),0));
        }

        @Override
        public boolean tick(Status s, BattlerFrame user) {
            return false;
        }
    }

    public static final class BrittleSystem implements CanStatusSystem
    {
        public static BrittleSystem instance = new BrittleSystem();
        @Override
        public void execute(CanEffect effect, BattlerFrame[] battlers)
        {
            for (Status s: battlers[effect.getTarget().getValue()].getAllStatus())
            {
                if (s.getType() == StatusEffect.BRITTLE)
                {
                    s.setStackNumber(s.getStackNumber()+(int)effect.getStrength());

                    return;
                }
            }

            battlers[effect.getTarget().getValue()].getAllStatus().add(new Status(StatusEffect.BRITTLE,(int)effect.getStrength(),0));
        }

        @Override
        public boolean tick(Status s, BattlerFrame user) {
            return false;
        }
    }



    public static final class ExpungeSystem implements CanStatusSystem
    {

        public static final ExpungeSystem instance = new ExpungeSystem();
        final int tickTime = 2;





        @Override
        public boolean tick(Status s, BattlerFrame user) {
            return false;
        }

        @Override
        public void execute(CanEffect effect, BattlerFrame[] battlers) {

        }
    }
}
