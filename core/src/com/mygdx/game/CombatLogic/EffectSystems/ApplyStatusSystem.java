package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.EffectListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.EffectOnBattler;
import com.mygdx.game.CombatLogic.CombatSystems.CastSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ApplyStatus;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StatusFactory;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.DurationBased;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.OnSpellBased;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.TickBased;


public class ApplyStatusSystem implements IsEffectSystem<ApplyStatus> {

    public static ApplyStatusSystem instance = new ApplyStatusSystem();


    @Override
    public void execute(ApplyStatus effect, BattlerFrame[] battlers)
    {

        EffectListComponent battlerComponent = battlers[effect.getTarget().getValue()].getComponent(EffectListComponent.class);
        for (EffectOnBattler eb : battlerComponent.getEffectOnBattlers())
        {
            if (effect.getStatusObject().getStatus_name() == eb.getStatusObject().getStatus_name() && StatusFactory.instance.isStatic(effect.getStatusObject().getStatus_name()))
            {

                eb.setStackNumber(eb.getStackNumber() + effect.getStrength());
                return;
            }
        }

        EffectOnBattler eb = new EffectOnBattler(effect.getStatusObject(),effect.getStrength());
        battlerComponent.getEffectOnBattlers().add(eb);
        if (effect.getStatusObject().getType() instanceof DurationBased)
        {
            doStatusEffect(eb,battlers[effect.getTarget().getValue()]);
        }
    }

    public void checkAllStatusEffect(BattlerFrame host)
    {
        EffectListComponent battlerComponent = host.getComponent(EffectListComponent.class);
        for (int i =0; i<battlerComponent.getEffectOnBattlers().size();i++)

        {
            if (battlerComponent.getEffectOnBattlers().get(i).getStatusObject().getType() instanceof TickBased) {
                handleTickEffect(battlerComponent.getEffectOnBattlers().get(i), host);
            } else if (battlerComponent.getEffectOnBattlers().get(i).getStatusObject().getType() instanceof DurationBased) {
                handleDurationEffect(battlerComponent.getEffectOnBattlers().get(i), host);
            }




        }


    }
    public void checkAllOnSpellEffect(BattlerFrame host)
    {
        EffectListComponent battlerComponent = host.getComponent(EffectListComponent.class);
        boolean hasOnSpell = false;

        for (int i =0; i<battlerComponent.getEffectOnBattlers().size();i++)
        {
            if (battlerComponent.getEffectOnBattlers().get(i).getStatusObject().getType() instanceof OnSpellBased)
            {
                if (!hasOnSpell)
                {
                    hasOnSpell = true;

                    host.getComponent(CastComponent.class).setSpell(host.getComponent(CastComponent.class).getSpell().cleanClone());
                }

                handleOnSpell(battlerComponent.getEffectOnBattlers().get(i),host);

            }
        }
    }



    public void handleDurationEffect(EffectOnBattler effect, BattlerFrame host)
    {
       if (incrementDownEffect(effect,host))
       {
           Effect undoEffect = ((DurationBased)effect.getStatusObject().getType()).getWhenFinished();
           CastSystem.instance.routeEffect(undoEffect, new BattlerFrame[]{host,host});
       }



    }
    public void handleTickEffect(EffectOnBattler effect, BattlerFrame host)
    {
        TickBased effectType =(TickBased) effect.getStatusObject().getType();
        if(effect.getCd() == effectType.getSpeed())
        {
            doStatusEffect(effect,host);
            effect.setCd(0);
            if (effectType.getDoesDecrease())
            {
                incrementDownEffect(effect,host);
            }


        }
        effect.setCd(effect.getCd()+1);
    }
    public void handleOnSpell(EffectOnBattler effect,BattlerFrame host)
    {
        OnSpellBased effectType = (OnSpellBased) effect.getStatusObject().getType();

        if (host.getComponent(CastComponent.class).getCastTimer() == 0)
        {

            doStatusEffect(effect,host);
            incrementDownEffect(effect,host);
        }
    }
    //HandleOnSpell delayed by 1 turn
    //Same as on Frozen hmmmm



    public void doStatusEffect(EffectOnBattler effect, BattlerFrame host)
    {
        CastSystem.instance.routeEffect(effect.getStatusObject().getStatusEffect(), new BattlerFrame[]{host,host});
    }


    public boolean incrementDownEffect(EffectOnBattler effect, BattlerFrame host)
    {
        EffectListComponent battlerComponent = host.getComponent(EffectListComponent.class);
        effect.setStackNumber(effect.getStackNumber() - 1);
        if (effect.getStackNumber() == 0 )
        {
            battlerComponent.getEffectOnBattlers().remove(effect);
            return true;
        }
        return false;
    }

}
