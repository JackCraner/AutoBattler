package com.mygdx.game.SpellLogic;

import com.mygdx.game.AssetFinder.Projectiles;
import com.mygdx.game.AssetFinder.SpellSplash;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ApplyBattleground;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ApplyStatus;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.CastTimes;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Channel;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Condition;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Chance;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.ConditionObject;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.DealDamage;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ForEach;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StatusFactory;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.BattlegroundTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.SpellTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;



public enum SpellFactory
{
    FIRE0_1(SpellSplash.FIRE3STRIKE,new Spell(SpellTypes.FIRE,"Fireball",0,1,new Effect(TargetType.OTHER) {{
        addComponent(new DealDamage(2, DamageTypes.FIRE));
    }})),
    FIRE0_2(SpellSplash.FIREHAND,new Spell(SpellTypes.FIRE,"Ember",0,2,new Effect(TargetType.OTHER) {{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Chance(50));
        }},new Effect(TargetType.OTHER){{
            addComponent(new ApplyStatus(5,StatusFactory.instance.getBurn()));
        }}));
    }})),
    FIRE1_0(SpellSplash.FIREPYROBLAST,new Spell(SpellTypes.FIRE,"Fire Clap",2,1,new Effect(TargetType.OTHER) {{
        addComponent(new ApplyStatus(5,StatusFactory.instance.getBurn()));
    }})),
    /**
    SHUTUP(new Spell(SpellTypes.NATURE,"Shutup",2,20,new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(2,DamageTypes.NATURE));
    }})),
    ARCANE0_1(new Spell(SpellTypes.ARCANE,"Arcane Blast",1,1,new Effect(TargetType.OTHER) {{
        addComponent(new DealDamage(3,DamageTypes.ARCANE));
    }})),
    WIND0_1(new Spell(SpellTypes.WIND,"Wind Blow",0,3,new Effect(TargetType.OTHER) {{
        addComponent(new DealDamage(8,DamageTypes.WIND));
    }})),
    FIRE0_2(new Spell(SpellTypes.FIRE,"Hot Hands",0,1, new Effect(TargetType.SELF){{

        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Chance(50));
        }}, new Effect(TargetType.OTHER){{
            addComponent(new ApplyStatus(3, StatusFactory.instance.getBurn()));
        }}));


    }})),
    NATURE0_1(new Spell(SpellTypes.NATURE,"Poison Dart",0,2,new Effect(TargetType.OTHER){{
        addComponent(new ApplyStatus(2,StatusFactory.instance.getPoison()));
    }}) ),
    FIRE1_1(new Spell(SpellTypes.FIRE,"Fire Bomb",1,2, new Effect[]{
            new Effect(TargetType.OTHER) {{
                addComponent(new DealDamage(5, DamageTypes.FIRE));
            }},
            new Effect(TargetType.SELF) {{
                addComponent(new DealDamage(5, DamageTypes.FIRE));
            }}
    })),

    FIRE1_2(new Spell(SpellTypes.FIRE,"Fire Kick",1,1,new Effect(TargetType.OTHER){{
        addComponent(new ApplyStatus(4,StatusFactory.instance.getDamageModifier(DamageTypes.FIRE,1)));
    }})),
    NATURE2_1(new Spell(SpellTypes.NATURE,"Natures Grasp",2,3, new Effect(TargetType.OTHER){{
        addComponent(new ForEach(2, "Poison",new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(2,DamageTypes.NATURE));
        }}));
    }})),
    ICE0_1(new Spell(SpellTypes.ICE,"Ice Blast",0,1,new Effect(TargetType.OTHER){{
        addComponent(new ApplyStatus(2, StatusFactory.instance.getFreeze()));
    }})),

    ICE1_1(new Spell(SpellTypes.ICE,"Ice Shard",1,1,new Effect(TargetType.OTHER){{
        addComponent(new Channel("TEST",new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(1, DamageTypes.ICE));
        }}));
    }})),

    ICE1_2(new Spell(SpellTypes.ICE,"Ice_Test",10,1,new Effect(TargetType.SELF){{
        addComponent(new ApplyBattleground(BattlegroundTypes.TREACHEROUSGROUND));
        addComponent(new CastTimes(1));
    }})),
     **/



    ;
    Spell spell;
    Projectiles projectile;
    SpellSplash spellSplash;
    SpellFactory(SpellSplash ss, Spell s)
    {
        this.spell = s;
        this.spellSplash = ss;
        this.projectile = Projectiles.FIREBALL;
    }

    public Spell getSpell() {
        //return cloned version
        return spell.clone();
    }

    public Projectiles getProjectile() {
        return projectile;
    }

    public SpellSplash getSpellSplash() {
        return spellSplash;
    }
}


/**
 * The Target part should be taken out and made as its own component
 *
 * Take the strength part out of IsEffectComponent
 * Remove TargettableEffectComponent
 */


