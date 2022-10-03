package com.mygdx.game.CombatLogic.EffectSystems;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.EffectListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.SpellListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.EffectOnBattler;
import com.mygdx.game.CombatLogic.CombatSystems.CastSystem;
import com.mygdx.game.CombatLogic.CombatSystems.NumberSystem;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Condition;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Chance;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.ConditionObject;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.HasEffect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Inequality;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.IsCasting;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.IsConditionComponent;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Unique;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;


import java.util.Iterator;
import java.util.Random;

public class ConditionSystem implements IsEffectSystem<Condition>
{
    public static ConditionSystem instance = new ConditionSystem();
    private ConditionSystem()
    {

    }


    @Override
    public void execute(Condition effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {

        ConditionObject conditionType = effect.getType();
        boolean check = checkCondition(conditionType, battlers, newBattlers);
        if (check)
        {
            CastSystem.instance.routeEffect(effect.getEffect(true), battlers, newBattlers);
        }
        else if (effect.hasOnFail())
        {
            CastSystem.instance.routeEffect(effect.getEffect(false), battlers, newBattlers);
        }


    }
    public boolean checkCondition(ConditionObject cObject, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {
        boolean stepper = true;
        for (Iterator<? extends IsConditionComponent> it = cObject.getComponentIterator(); it.hasNext(); )
        {
            IsConditionComponent cc = it.next();
            if (cc instanceof Chance)
            {
                stepper = chance((Chance )cc);
            }
            else if (cc instanceof HasEffect)
            {
                stepper = hasEffect((HasEffect) cc,battlers[((HasEffect) cc).getTarget().getValue()]);
            }
            else if (cc instanceof IsCasting)
            {
                stepper =isCasting((IsCasting) cc,newBattlers[((IsCasting) cc).getTarget().getValue()]);
            }
            else if (cc instanceof Unique)
            {
                stepper = unique((Unique)cc,battlers[TargetType.SELF.getValue()]);
            }
            else if (cc instanceof Inequality)
            {
                stepper = inequality((Inequality)cc,battlers );
            }

            if (!stepper)
            {
 ;
                return false;
            }
        }

        return true;
    }






    private Random ran = new Random();
    public boolean chance(Chance type)
    {
        int check = ran.nextInt(100);

        return check<type.getPercentage();
    }
    public boolean hasEffect(HasEffect type, BattlerFrame target)
    {
        for (EffectOnBattler e: target.getComponent(EffectListComponent.class).getEffectOnBattlers())
        {
            if (e.getStatusObject().getStatus_name() == type.getRequiredEffect())
            {
                if (e.getStackNumber() >= type.getNum())
                {
                    return true;
                }

            }
        }
        return false;
    }
    public  boolean isCasting (IsCasting type, BattlerFrame target)
    {
        if (target.getComponent(CastComponent.class).getSpell().getSpellType() == type.getSpellType())
        {
            return true;
        }
        return false;
    }
    public boolean unique(Unique type, BattlerFrame target)
    {
        String spellName = target.getComponent(CastComponent.class).getSpell().getName();
        int counter = 0;
        for (Spell s: target.getComponent(SpellListComponent.class).getSpellList())
        {
            if (s.getName() == spellName)
            {
                counter ++;
            }
        }
        return (counter<=1);
    }
    public boolean inequality(Inequality type, BattlerFrame[] battlers)
    {
        return NumberSystem.instance.handleIntFormat(type.getPara1(),battlers) > NumberSystem.instance.handleIntFormat(type.getPara2(),battlers);
    }


}
