package com.mygdx.game.SpellLogic;

import com.mygdx.game.AssetFinder.Projectiles;
import com.mygdx.game.AssetFinder.SpellSplash;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ApplyBattleground;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ApplyStatus;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ArmorModifier;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.CastTimes;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ChangeCastTime;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ChangeCost;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Channel;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Condition;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Chance;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.ConditionObject;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.HasEffect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.IsCasting;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Unique;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.DealDamage;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ForEach;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainLoseMana;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainMaxStat;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.HealHealth;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntFormat;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntReplacements.EqualTo;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntReplacements.IntReplacementTypes;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.NumberBranch;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.Symbols;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Interrupt;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.RemoveStatus;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.RepeatN;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StaticStatus;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StatusFactory;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StatusObject;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.DurationBased;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.OnSpellBased;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.BattlegroundTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.EffectType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ModifierType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.SpellTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.StatType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;



public enum SpellFactory
{
    FIRE0_1(SpellSplash.FIRE3STRIKE,new Spell(SpellTypes.FIRE,"Fireball",0,1,new Effect(TargetType.OTHER) {{
        addComponent(new DealDamage(new IntFormat(new EqualTo(IntReplacementTypes.CurrentHealth,TargetType.OTHER), new NumberBranch(Symbols.SUBTRACT, 5)), DamageTypes.NEUTRAL));
    }})),
    FIRE0_2(SpellSplash.FIREHAND,new Spell(SpellTypes.FIRE,"Ember",2,2,new Effect(TargetType.OTHER) {{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Chance(50));
        }},new Effect(TargetType.OTHER){{
            addComponent(new ApplyStatus(3,StatusFactory.instance.getBurn()));
        }}));
    }})),
    FIRE1_1(SpellSplash.FIREBITE,new Spell(SpellTypes.FIRE,"Molten Consumption",1,2,new Effect[]{new Effect(TargetType.OTHER,EffectType.POSITIVE){{
        addComponent(new DealDamage(8,DamageTypes.NEUTRAL));
    }}, new Effect(TargetType.SELF, EffectType.NEGATIVE){{
        addComponent(new GainMaxStat(StatType.MANA,-1));
    }}
    })),
    FIRE1_2(SpellSplash.FIRESTAFF, new Spell(SpellTypes.FIRE,"Magma Frenzy",1,2,new Effect(TargetType.SELF){{
    addComponent(new ApplyStatus(3,new StatusObject("Magma Frenzy", SpellSplash.FIRESTAFF.getLocation(),new OnSpellBased(),new Effect(TargetType.SELF){{
        addComponent(new ChangeCastTime(-1, ModifierType.ADDITIVE));
    }})));}
    })),
    FIRE1_3(SpellSplash.FIREBURNT, new Spell(SpellTypes.FIRE,"Fiery Anguish",1,1,new Effect[]{
            new Effect(TargetType.SELF){{
                addComponent(new HealHealth(3));
            }}, new Effect(TargetType.SELF, EffectType.NEGATIVE){{
        addComponent(new ApplyStatus(1,StatusFactory.instance.getBurn()));
    }}
    })),
    FIRE2_1(SpellSplash.FIREWAVE, new Spell(SpellTypes.FIRE,"Calling of Lava",2,3, new Effect(TargetType.OTHER){{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new HasEffect(1, StaticStatus.BURN.getName(),TargetType.OTHER));
        }}, new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(5,DamageTypes.NEUTRAL));
            addComponent(new ArmorModifier(DamageTypes.NEUTRAL, -1));
        }}));
    }})),
    FIRE2_2(SpellSplash.FIREMEDITATE, new Spell(SpellTypes.FIRE,"Engulfing Clarification",2,1, new Effect(TargetType.SELF){{
        addComponent(new ForEach(1,StaticStatus.BURN.getName(),new Effect(TargetType.SELF){{
            addComponent(new GainLoseMana(2));
        }}));
    }})),
    FIRE2_3(SpellSplash.FIRESTAFFSHOOT, new Spell(SpellTypes.FIRE, "Scorch", 2,2, new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(8,DamageTypes.NEUTRAL));
    }})),
    FIRE3_1(SpellSplash.FIRESPEED, new Spell(SpellTypes.FIRE, "Hot Rush",3,2, new Effect(TargetType.SELF){{
        addComponent(new ApplyStatus(1,new StatusObject("Hot Rush",SpellSplash.FIRESPEED.getLocation(), new OnSpellBased(), new Effect(TargetType.SELF){{
            addComponent(new ChangeCastTime(1,ModifierType.ABSOLUTE));
        }})));
    }})),
    FIRE4_1(SpellSplash.FIREMETEORS, new Spell(SpellTypes.FIRE,"Meteors",4,3, new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(10,DamageTypes.NEUTRAL));
        addComponent(new ApplyStatus(3,StatusFactory.instance.getBurn()));
    }})),
    FIRE4_2(SpellSplash.FIREPHENIX, new Spell(SpellTypes.FIRE,"Phoenix",4,4,new Effect(TargetType.SELF){{
        addComponent(new ApplyStatus(1,new StatusObject("Phoenix",SpellSplash.FIREPHENIX.getLocation(), new OnSpellBased(), new Effect(TargetType.SELF){{
            addComponent(new ChangeCost(0,ModifierType.ABSOLUTE));
            addComponent(new RepeatN(1));
        }})));
        addComponent(new CastTimes(1));
    }})),
    FIRE5_1(SpellSplash.FIREPYROBLAST, new Spell(SpellTypes.FIRE,"Pyroblast",5,1, new Effect(TargetType.OTHER){{
        addComponent(new ForEach(1,StaticStatus.BURN.getName(), new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(2,DamageTypes.NEUTRAL));
        }}));
    }})),
    FIRE5_2(SpellSplash.FIRERITUAL, new Spell(SpellTypes.FIRE,"Fire Rune",5,2, new Effect(TargetType.OTHER){{
        addComponent(new Channel("Fire Rune", new Effect(TargetType.OTHER){{
            addComponent(new ApplyStatus(2,StatusFactory.instance.getBurn()));
        }}));
    }})),

    WIND0_1(SpellSplash.WIND3STRIKE,new Spell(SpellTypes.WIND,"Tri Gust",0,3,new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(7,DamageTypes.NEUTRAL));
    }})),

    WIND0_2(SpellSplash.WINDPOINT, new Spell(SpellTypes.WIND,"Touch of Wind",0,1,new Effect(TargetType.SELF){{
        addComponent(new GainLoseMana(2));
    }})),
    WIND0_3(SpellSplash.WINDHANDSPHERE, new Spell(SpellTypes.WIND,"Serenity Zephyr",0,2, new Effect(TargetType.SELF){{
        addComponent(new ApplyStatus(2,StatusFactory.instance.getWindRush()));
    }})),
    WIND1_1(SpellSplash.WINDSTAFF, new Spell(SpellTypes.WIND, "Air Staff",1,1,new Effect[]{new Effect(TargetType.SELF){{
        addComponent(new ForEach(1, StaticStatus.WINDRUSH.getName(), new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(1,DamageTypes.NEUTRAL));
        }}));
    }}, new Effect(TargetType.SELF){{
        addComponent(new RemoveStatus(-1,StaticStatus.WINDRUSH.getName()));
    }}})),
    WIND2_1(SpellSplash.WINDBLOW, new Spell(SpellTypes.WIND,"Interrupting Gale",2,1,new Effect(TargetType.OTHER){{
        addComponent(new Interrupt());
    }})),
    WIND2_2(SpellSplash.WINDSTAFF2, new Spell(SpellTypes.WIND, "Hot Swap",2,2,new Effect(TargetType.SELF){{
        addComponent(new ForEach(1,StaticStatus.BURN.getName(), new Effect(TargetType.SELF){{
            addComponent(new ApplyStatus(1,StatusFactory.instance.getWindRush()));
        }}));
        addComponent(new RemoveStatus(-1,StaticStatus.BURN.getName()));
    }})),
    WIND2_3(SpellSplash.WINDSPHERE, new Spell(SpellTypes.WIND, "Orb of Tempest",2,1,new Effect(TargetType.SELF){{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Unique());
        }}, new Effect(TargetType.SELF){{
            addComponent(new ApplyStatus(4,StatusFactory.instance.getWindRush()));
        }}));
    }})),
    WIND2_4(SpellSplash.WINDPULSE, new Spell(SpellTypes.WIND, "Growing Gale",2,3,new Effect(TargetType.OTHER){{
        addComponent(new Channel("Growing Gale",new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(8,DamageTypes.NEUTRAL));
        }}));
    }})),
    WIND2_5(SpellSplash.WINDPOTION, new Spell(SpellTypes.WIND,"Rushing Potion",2,1,new Effect(TargetType.SELF){{
        addComponent(new HealHealth(4));
        addComponent(new ApplyStatus(2,StatusFactory.instance.getWindRush()));
    }})),
    WIND3_1(SpellSplash.WINDSPEED, new Spell(SpellTypes.WIND,"Tailwind",3,1,new Effect(TargetType.SELF){{
        addComponent(new ApplyStatus(1,new StatusObject("Tailwind", SpellSplash.WINDSPEED.getLocation(), new OnSpellBased(), new Effect(TargetType.SELF){{
            addComponent(new Condition(new ConditionObject(){{
                addComponent(new IsCasting(SpellTypes.WIND,TargetType.SELF));
            }}, new Effect(TargetType.SELF){{
                addComponent(new RepeatN(1));
            }}));
        }})));
    }})),
    WIND3_2(SpellSplash.WINDTORNADO,new Spell(SpellTypes.WIND,"Tornado",3,2, new Effect[]{new Effect(TargetType.OTHER){{
        addComponent(new GainLoseMana(-5));
    }}, new Effect(TargetType.SELF,EffectType.NEGATIVE){{
        addComponent(new DealDamage(5, DamageTypes.NEUTRAL));
    }}})),
    WIND3_3(SpellSplash.WINDRUNE, new Spell(SpellTypes.WIND,"Tempest Conversion",3,1,new Effect(TargetType.SELF){{
        addComponent(new ForEach(1,StaticStatus.WINDRUSH.getName(),new Effect(TargetType.SELF){{
            addComponent(new GainMaxStat(StatType.HEALTH,1));
        }}));
    }})),
    WIND4_1(SpellSplash.WINDSHIELD, new Spell(SpellTypes.WIND,"Storm Shield",4,1,new Effect(TargetType.SELF){{
        addComponent(new ApplyStatus(5,new StatusObject("Storm Shield",SpellSplash.WINDSHIELD.getLocation(), new DurationBased(new Effect(TargetType.SELF){{
            addComponent(new ArmorModifier(-2));
        }}), new Effect(TargetType.SELF){{
            addComponent(new ArmorModifier(2));
        }})));
    }})),
    WIND4_2(SpellSplash.WINDBOMB, new Spell(SpellTypes.WIND,"Boreas Bomb",4,2,new Effect[]{new Effect(TargetType.OTHER){{
        addComponent(new GainMaxStat(StatType.HEALTH,-8));
    }}, new Effect(TargetType.SELF,EffectType.NEGATIVE){{
        addComponent(new ApplyStatus(3,new StatusObject("Boreas Bomb",SpellSplash.WINDBOMB.getLocation(),new DurationBased(new Effect(TargetType.SELF){{
            addComponent(new ArmorModifier(+3));
        }}) , new Effect(TargetType.SELF){{
            addComponent(new ArmorModifier(-3));
        }})));
    }}})),
    WIND4_3(SpellSplash.WINDRAIN, new Spell(SpellTypes.WIND,"Storm",4,1,new Effect[]{new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(7));
    }}, new Effect(TargetType.SELF,EffectType.NEGATIVE){{
        addComponent(new DealDamage(7));
    }}})),
    WIND4_4(SpellSplash.WINDMETEOR, new Spell(SpellTypes.WIND,"Tempest Blast",4,4, new Effect(TargetType.OTHER){{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Unique());
        }}, new Effect(TargetType.OTHER){{
            addComponent(new ForEach(1,StaticStatus.WINDRUSH.getName(), new Effect(TargetType.OTHER){{
                addComponent(new DealDamage(2));
            }}));
            addComponent(new RemoveStatus(-1,StaticStatus.WINDRUSH.getName()));
        }}));
    }})),
    WIND5_1(SpellSplash.WINDENLIGHTEN, new Spell(SpellTypes.WIND,"Eurus' Enlightenment",5,2, new Effect(TargetType.SELF){{
        addComponent(new HealHealth(-1));
        addComponent(new ApplyStatus(10,StatusFactory.instance.getWindRush()));
        addComponent(new CastTimes(1));
    }})),
    WIND5_2(SpellSplash.WINDTRANSFORM, new Spell(SpellTypes.WIND, "Rage of the Wind",5,1,new Effect(TargetType.SELF){{
        addComponent(new ForEach(2, StaticStatus.WINDRUSH.getName(), new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(1));
        }}));
        addComponent(new ForEach(2,StaticStatus.WINDRUSH.getName(), new Effect(TargetType.SELF){{
            addComponent(new HealHealth(1));
            addComponent(new GainLoseMana(1));
        }}));
        addComponent(new RemoveStatus(-1, StaticStatus.WINDRUSH.getName()));
    }})),

    WIND5_3(SpellSplash.WINDGUST, new Spell(SpellTypes.WIND,"Category 5", 5,3,new Effect(TargetType.SELF){{
        addComponent(new ApplyBattleground(BattlegroundTypes.CATEGORY5));
        addComponent(new CastTimes(1));
    }}))



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


