package com.mygdx.game.SpellLogic.SpellEffect.Enums;

import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ApplyStatus;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ArmorModifier;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Condition;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Chance;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.ConditionObject;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.IsCasting;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.DealDamage;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainLoseMana;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainMaxStat;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StatusFactory;

import java.util.Random;

public enum BattlegroundTypes
{
    NULL(new Effect(TargetType.SELF)),
    SUNNY(new Effect(TargetType.SELF){{
        addComponent(new DealDamage(2,DamageTypes.FIRE));
    }}),
    DREAMGROVE(new Effect(TargetType.SELF){{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new IsCasting(SpellTypes.NATURE,TargetType.SELF));
        }}, new Effect(TargetType.SELF){{
            addComponent(new GainMaxStat(StatType.HEALTH,1));
        }}));
    }}),
    TREACHEROUSGROUND(new Effect(TargetType.SELF){{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Chance(50));
        }},new Effect(TargetType.SELF){{
            addComponent(new ArmorModifier(DamageTypes.ROCK,1));
        }}, new Effect(TargetType.SELF){{
            addComponent(new ArmorModifier(DamageTypes.ROCK,-1));
        }}));
    }}),
    CATEGORY5(new Effect(TargetType.SELF){{
        addComponent(new GainLoseMana(-1));
        addComponent(new ApplyStatus(1, StatusFactory.instance.getWindRush()));
    }})
    ;
    private Effect effect;
    BattlegroundTypes(Effect e)
    {
        this.effect = e;
    }

    public Effect getEffect() {
        return effect;
    }
}
