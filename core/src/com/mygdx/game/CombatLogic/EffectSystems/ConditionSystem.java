package com.mygdx.game.CombatLogic.EffectSystems;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.EffectListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.EffectOnBattler;
import com.mygdx.game.CombatLogic.CombatSystems.CastSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Condition;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Chance;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.ConditionObject;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.HasEffect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.IsCasting;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.IsConditionComponent;



import java.util.Iterator;
import java.util.Random;

public class ConditionSystem implements IsEffectSystem<Condition>
{
    public static ConditionSystem instance = new ConditionSystem();
    private ConditionSystem()
    {

    }


    @Override
    public void execute(Condition effect, BattlerFrame[] battlers)
    {

        ConditionObject conditionType = effect.getType();
        boolean check = checkCondition(conditionType, battlers);

        CastSystem.instance.routeEffect(effect.getEffect(check), battlers);
    }
    public boolean checkCondition(ConditionObject cObject, BattlerFrame[] battlers)
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
                stepper =isCasting((IsCasting) cc,battlers[((IsCasting) cc).getTarget().getValue()]);
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


}
