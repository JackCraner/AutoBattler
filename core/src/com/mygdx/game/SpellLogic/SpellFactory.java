package com.mygdx.game.SpellLogic;

import com.mygdx.game.AssetFinder.Projectiles;
import com.mygdx.game.AssetFinder.SpellSplash;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.AddSpell;
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
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Inequality;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.IsCasting;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.Unique;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.DamageModifier;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.DealDamage;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ForEach;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainLoseMana;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.GainMaxStat;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.HealHealth;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.Absolute;
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
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ChangeType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.EffectType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ModifierType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.SpellTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.StatType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;



public enum SpellFactory
{
    FIRE0_1(SpellSplash.FIRE3STRIKE,new Spell(SpellTypes.FIRE,"Fireball",0,1,new Effect(TargetType.OTHER) {{
       addComponent(new DealDamage(5));
    }})),
    FIRE0_2(SpellSplash.FIREHAND,new Spell(SpellTypes.FIRE,"Ember",0,2,new Effect(TargetType.OTHER) {{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Chance(50));
        }},new Effect(TargetType.OTHER){{
            addComponent(new ApplyStatus(10,StatusFactory.instance.getBurn()));
        }}));
    }}, "50% Chance to Apply 2 Burn")),
    FIRE0_3(SpellSplash.FIRETORNADO, new Spell(SpellTypes.FIRE,"Burning Best",0,1, new Effect[]{new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(7));
    }}, new Effect(TargetType.SELF,EffectType.NEGATIVE){{
        addComponent(new ApplyStatus(2, StatusFactory.instance.getBurn()));
    }}}, "Deal 7 Damage but Apply 2 Burn to Yourself")),
    FIRE1_1(SpellSplash.FIREBITE,new Spell(SpellTypes.FIRE,"Molten Consumption",1,2,new Effect[]{new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(13,DamageTypes.NEUTRAL));
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
                addComponent(new HealHealth(5));
            }}, new Effect(TargetType.SELF, EffectType.NEGATIVE){{
        addComponent(new ApplyStatus(1,StatusFactory.instance.getBurn()));
    }}
    })),
    FIRE2_1(SpellSplash.FIREWAVE, new Spell(SpellTypes.FIRE,"Calling of Lava",2,3, new Effect(TargetType.OTHER){{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new HasEffect(1, StaticStatus.BURN.getName(),TargetType.OTHER));
        }}, new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(10,DamageTypes.NEUTRAL));
            addComponent(new ArmorModifier(DamageTypes.NEUTRAL, -1));
        }}));
    }})),
    FIRE2_2(SpellSplash.FIREMEDITATE, new Spell(SpellTypes.FIRE,"Engulfing Clarification",2,1, new Effect(TargetType.SELF){{
        addComponent(new ForEach(1,StaticStatus.BURN.getName(),new Effect(TargetType.SELF){{
            addComponent(new GainLoseMana(2));
        }}));
    }})),
    FIRE2_3(SpellSplash.FIRESTAFFSHOOT, new Spell(SpellTypes.FIRE, "Scorch", 2,2, new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(12,DamageTypes.NEUTRAL));
    }})),
    FIRE3_1(SpellSplash.FIRESPEED, new Spell(SpellTypes.FIRE, "Hot Rush",3,2, new Effect(TargetType.SELF){{
        addComponent(new ApplyStatus(1,new StatusObject("Hot Rush",SpellSplash.FIRESPEED.getLocation(), new OnSpellBased(), new Effect(TargetType.SELF){{
            addComponent(new ChangeCastTime(1,ModifierType.ABSOLUTE));
        }})));
    }})),
    FIRE4_1(SpellSplash.FIREMETEORS, new Spell(SpellTypes.FIRE,"Meteors",4,3, new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(10,DamageTypes.NEUTRAL));
        addComponent(new ApplyStatus(10,StatusFactory.instance.getBurn()));
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
        addComponent(new DealDamage(18,DamageTypes.NEUTRAL));
    }})),

    WIND0_2(SpellSplash.WINDPOINT, new Spell(SpellTypes.WIND,"Touch of Wind",0,1,new Effect(TargetType.SELF){{
        addComponent(new GainLoseMana(2));
    }})),
    WIND0_3(SpellSplash.WINDHANDSPHERE, new Spell(SpellTypes.WIND,"Serenity Zephyr",0,2, new Effect(TargetType.SELF){{
        addComponent(new ApplyStatus(2,StatusFactory.instance.getWindRush()));
    }})),
    WIND1_1(SpellSplash.WINDSTAFF, new Spell(SpellTypes.WIND, "Air Staff",1,1,new Effect[]{new Effect(TargetType.SELF){{
        addComponent(new ForEach(1, StaticStatus.WINDRUSH.getName(), new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(2,DamageTypes.NEUTRAL));
        }}));
    }}, new Effect(TargetType.SELF){{
        addComponent(new RemoveStatus(-1,StaticStatus.WINDRUSH.getName()));
    }}}, "Consume ALL Windrush Stacks and Deal 2 Damage per Stack")),
    WIND1_2(SpellSplash.WINDWISP, new Spell(SpellTypes.WIND,"Mess of the Wind",1,3,new Effect(TargetType.SELF){{
        addComponent(new ApplyStatus(new IntFormat(new EqualTo(IntReplacementTypes.TurnNumber,TargetType.SELF)),StatusFactory.instance.getWindRush()));
    }})),
    WIND1_3(SpellSplash.WINDSTRIKE, new Spell(SpellTypes.WIND,"Gusting Strike",1,2,new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(new IntFormat(new EqualTo(IntReplacementTypes.LastSpellMana,TargetType.SELF), new NumberBranch(Symbols.MULTIPLY,3))));
    }}, "Deal Damage equal to 3 X the Mana Cost of your last spell")),
    WIND2_1(SpellSplash.WINDBLOW, new Spell(SpellTypes.WIND,"Interrupting Gale",2,1,new Effect(TargetType.OTHER){{
        addComponent(new Interrupt());
    }})),
    WIND2_2(SpellSplash.WINDSTAFF2, new Spell(SpellTypes.WIND, "Hot Swap",2,2,new Effect(TargetType.SELF){{
        addComponent(new ForEach(1,StaticStatus.BURN.getName(), new Effect(TargetType.SELF){{
            addComponent(new ApplyStatus(1,StatusFactory.instance.getWindRush()));
        }}));
        addComponent(new RemoveStatus(-1,StaticStatus.BURN.getName()));
    }}, "Convert all Burn Stacks to Windrush Stacks")),
    WIND2_3(SpellSplash.WINDSPHERE, new Spell(SpellTypes.WIND, "Orb of Tempest",2,1,new Effect(TargetType.SELF){{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Unique());
        }}, new Effect(TargetType.SELF){{
            addComponent(new ApplyStatus(4,StatusFactory.instance.getWindRush()));
        }}));
    }}, "If Unique, then Apply 4 Stacks of Windrush")),
    WIND2_4(SpellSplash.WINDPULSE, new Spell(SpellTypes.WIND, "Growing Gale",2,3,new Effect(TargetType.OTHER){{
        addComponent(new Channel("Growing Gale",new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(8,DamageTypes.NEUTRAL));
        }}));
    }})),
    WIND2_5(SpellSplash.WINDPOTION, new Spell(SpellTypes.WIND,"Rushing Potion",2,1,new Effect(TargetType.SELF){{
        addComponent(new HealHealth(10));
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
            addComponent(new GainMaxStat(StatType.HEALTH,2));
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
        addComponent(new GainMaxStat(StatType.HEALTH,-10));
    }}, new Effect(TargetType.SELF,EffectType.NEGATIVE){{
        addComponent(new ApplyStatus(3,new StatusObject("Boreas Bomb",SpellSplash.WINDBOMB.getLocation(),new DurationBased(new Effect(TargetType.SELF){{
            addComponent(new ArmorModifier(+3));
        }}) , new Effect(TargetType.SELF){{
            addComponent(new ArmorModifier(-3));
        }})));
    }}})),
    WIND4_3(SpellSplash.WINDRAIN, new Spell(SpellTypes.WIND,"Storm",4,1,new Effect[]{new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(15));
    }}, new Effect(TargetType.SELF,EffectType.NEGATIVE){{
        addComponent(new DealDamage(15));
    }}})),
    WIND4_4(SpellSplash.WINDMETEOR, new Spell(SpellTypes.WIND,"Tempest Blast",4,4, new Effect(TargetType.OTHER){{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Unique());
        }}, new Effect(TargetType.OTHER){{
            addComponent(new ForEach(1,StaticStatus.WINDRUSH.getName(), new Effect(TargetType.OTHER){{
                addComponent(new DealDamage(4));
            }}));
            addComponent(new RemoveStatus(-1,StaticStatus.WINDRUSH.getName()));
        }}));
    }})),
    WIND5_1(SpellSplash.WINDENLIGHTEN, new Spell(SpellTypes.WIND,"Eurus' Enlightenment",5,2, new Effect(TargetType.SELF){{
        addComponent(new HealHealth(new IntFormat(new EqualTo(IntReplacementTypes.MaxHealth,TargetType.SELF))));
        addComponent(new ApplyStatus(10,StatusFactory.instance.getWindRush()));
        addComponent(new CastTimes(1));
    }})),
    WIND5_2(SpellSplash.WINDTRANSFORM, new Spell(SpellTypes.WIND, "Rage of the Wind",5,1,new Effect(TargetType.SELF){{
        addComponent(new ForEach(1, StaticStatus.WINDRUSH.getName(), new Effect(TargetType.OTHER){{
            addComponent(new DealDamage(1));
        }}));
        addComponent(new ForEach(1,StaticStatus.WINDRUSH.getName(), new Effect(TargetType.SELF){{
            addComponent(new HealHealth(1));
            addComponent(new GainLoseMana(1));
        }}));
        addComponent(new RemoveStatus(-1, StaticStatus.WINDRUSH.getName()));
    }})),

    WIND5_3(SpellSplash.WINDGUST, new Spell(SpellTypes.WIND,"Category 5", 5,3,new Effect(TargetType.SELF){{
        addComponent(new ApplyBattleground(BattlegroundTypes.CATEGORY5));
        addComponent(new CastTimes(1));
    }})),


    ARCANE0_1(SpellSplash.ARCANEGLOWHAND, new Spell(SpellTypes.ARCANE, "Arcano",0,1, new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(4));
    }})),
    ARCANE0_2(SpellSplash.ARCANEHANDGRASP, new Spell(SpellTypes.ARCANE, "Life Swap",0,2,new Effect[]{new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(new IntFormat(new EqualTo(IntReplacementTypes.LastSpellMana,TargetType.OTHER), new NumberBranch(Symbols.MULTIPLY,2))));
    }}, new Effect(TargetType.SELF){{
        addComponent(new HealHealth(new IntFormat(new EqualTo(IntReplacementTypes.LastSpellMana,TargetType.OTHER), new NumberBranch(Symbols.MULTIPLY,2))));
    }}}, "Heal and Deal Damage equal to 2 X Enemies last spell Cost")),
    ARCANE0_3(SpellSplash.ARCANESWAP, new Spell(SpellTypes.ARCANE, "Magic Swap", 0,1,new Effect[]{new Effect(TargetType.SELF){{
        addComponent(new GainMaxStat(StatType.HEALTH,5));
    }}, new Effect(TargetType.SELF,EffectType.NEGATIVE){{
        addComponent(new GainMaxStat(StatType.MANA,-5));
    }}}, "Convert 5 Permanent Mana into 5 Permanent Health")),
    ARCANE1_1(SpellSplash.ARCANEWEB, new Spell(SpellTypes.ARCANE, "Cursed Web", 1,2,new Effect(TargetType.OTHER)
    {{
        addComponent(new Condition(new ConditionObject(){{
            addComponent(new Unique());
        }}, new Effect(TargetType.OTHER){{
            addComponent(new ApplyStatus(3,StatusFactory.instance.getCurse()));
        }}));
    }})),
    ARCANE1_2(SpellSplash.ARCANEBOTTLE, new Spell(SpellTypes.ARCANE,"Cleanse",1,1,new Effect(TargetType.SELF){{
        addComponent(new RemoveStatus(5,"Random"));
    }})),
    ARCANE1_3(SpellSplash.ARCANESPHERE, new Spell(SpellTypes.ARCANE, "Arcane Blast",1,3, new Effect(TargetType.OTHER){{
        addComponent(new DealDamage(20));
        addComponent(new GainLoseMana(-5));
    }})),
    ARCANE1_4(SpellSplash.ARCANESPIRAL, new Spell(SpellTypes.ARCANE, "Astral Astonish",1,1,new Effect(TargetType.SELF){{
        addComponent(new GainLoseMana(10));
        addComponent(new CastTimes(1));
    }})),
    ARCANE2_1(SpellSplash.ARCANESHIELD, new Spell(SpellTypes.ARCANE, "Mana Absorb",2,1,new Effect(TargetType.SELF){{
        addComponent(new GainLoseMana(new IntFormat(new EqualTo(IntReplacementTypes.LastSpellMana,TargetType.SELF)), ChangeType.INCREASE));
    }}, "Gain Permanent Mana equal to Cost of your last spell Casted")),
    ARCANE2_2(SpellSplash.)



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


