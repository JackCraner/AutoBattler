package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent;


import com.mygdx.game.AssetFinder.SpellSplash;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerStates;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ApplyStatus;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ArmorModifier;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ChangeCastTime;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ChangeCost;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ChangeStatus;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.Condition;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.ConditionObject;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.HasEffect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.DamageModifier;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.DealDamage;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ForEach;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.RemoveStatus;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.DurationBased;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.OnSpellBased;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.TickBased;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ModifierType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

public class StatusFactory
{
    public static StatusFactory instance = new StatusFactory();
    private StatusFactory()
    {

    }

    public StatusObject getBurn()
    {
        return new StatusObject(StaticStatus.BURN.getName(), StaticStatus.BURN.getIcon(), new TickBased(3,false),new Effect(TargetType.SELF){{
            addComponent(new ForEach(1,"Burn",new Effect(TargetType.SELF){{
                addComponent(new DealDamage(1, DamageTypes.FIRE));
            }}));

        }});
    }
    public StatusObject getPoison()
    {
        return new StatusObject(StaticStatus.POISON.getName(),StaticStatus.POISON.getIcon(), new TickBased(3,true),new Effect(TargetType.SELF){{
            addComponent(new ForEach(1,"Poison",new Effect(TargetType.SELF){{
                addComponent(new DealDamage(1,DamageTypes.FIRE));
            }}));

        }});
    }
    public StatusObject getFreeze()
    {
        return new StatusObject(StaticStatus.FREEZE.getName(),StaticStatus.FREEZE.getIcon(), new TickBased(1,false),new Effect(TargetType.SELF){{
            addComponent(new Condition(new ConditionObject(){{
                addComponent(new HasEffect(10,"Freeze",TargetType.SELF));
            }}, new Effect(TargetType.SELF){{
                addComponent(new ApplyStatus(3, getFrozen()));
            }}));
        }});
    }
    public StatusObject getFrozen()
    {
        return new StatusObject(StaticStatus.FROZEN.getName(),StaticStatus.FROZEN.getIcon(), new DurationBased(new Effect(TargetType.SELF){{
            addComponent(new ChangeStatus(BattlerStates.READY));
        }}),new Effect(TargetType.SELF){{
            addComponent(new ChangeStatus(BattlerStates.STUNNED));
            addComponent(new RemoveStatus(-1,"Freeze"));
        }});
    }
    public StatusObject getBrittle()
    {
        return new StatusObject(StaticStatus.BRITTLE.getName(),StaticStatus.BRITTLE.getIcon(), new TickBased(1,false),new Effect(TargetType.SELF){{
            addComponent(new Condition(new ConditionObject(){{
                addComponent(new HasEffect(10,StaticStatus.BRITTLE.getName(), TargetType.SELF));
            }},new Effect(TargetType.SELF){{
                addComponent(new ApplyStatus(3,getBroken()));
            }}));
        }});
    }
    public StatusObject getBroken()
    {
        return new StatusObject(StaticStatus.BROKEN.getName(),StaticStatus.BROKEN.getIcon(), new DurationBased(new Effect(TargetType.SELF){{
            for (DamageTypes t: DamageTypes.values())
            {
                addComponent(new ArmorModifier(t,1));
            }

        }}), new Effect(TargetType.SELF){{
            for (DamageTypes t: DamageTypes.values())
            {
                addComponent(new ArmorModifier(t,-1));
            }
        }});
    }
    public StatusObject getWindRush()
    {
        return new StatusObject(StaticStatus.WINDRUSH.getName(),StaticStatus.WINDRUSH.getIcon(), new TickBased(1,false),new Effect(TargetType.SELF){{
        }});
    }
    public StatusObject getMoonFire()
    {
        return new StatusObject(StaticStatus.MOONFIRE.getName(),StaticStatus.MOONFIRE.getIcon(), new OnSpellBased(),new Effect(TargetType.SELF){{
            addComponent(new ChangeCost(1, ModifierType.ABSOLUTE));
        }});
    }

    public StatusObject getCurse()
    {
        return new StatusObject(StaticStatus.CURSE.getName(), StaticStatus.CURSE.getIcon(), new OnSpellBased(),new Effect(TargetType.SELF){{
            addComponent(new ChangeCost(1,ModifierType.ADDITIVE));
            addComponent(new ChangeCastTime(1,ModifierType.ADDITIVE));
        }});
    }

    public boolean isStatic(String check)
    {
        for (StaticStatus s: StaticStatus.values())
        {
            if (s.getName() == check)
            {
                return true;
            }
        }
        return false;
    }

    public StatusObject getDamageModifier(final DamageTypes type, final int strength)
    {
        //sus
        return new StatusObject(type.name() + " Modifier","assets/SpellSplash/Status/"+type.name()+" Modifier.png", new DurationBased(new Effect(TargetType.SELF){{
            addComponent(new DamageModifier(-strength,type));
        }}),new Effect(TargetType.SELF){{
            addComponent(new DamageModifier(strength,type));
        }});
    }
    public StatusObject getStun(String name, String icon)
    {
        return new StatusObject(name, icon, new DurationBased(new Effect(TargetType.SELF){{
            addComponent(new ChangeStatus(BattlerStates.READY));
        }}), new Effect(TargetType.OTHER){{
            addComponent(new ChangeStatus(BattlerStates.STUNNED));
        }});
    }





}
