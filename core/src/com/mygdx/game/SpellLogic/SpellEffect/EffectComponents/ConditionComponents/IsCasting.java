package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents;


import com.mygdx.game.SpellLogic.SpellEffect.Enums.SpellTypes;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

public class IsCasting extends IsConditionComponent{

    private SpellTypes spellType;
    private TargetType target;
    public IsCasting(SpellTypes spellType, TargetType target)
    {
        this.target = target;
        this.spellType = spellType;
    }

    public TargetType getTarget() {
        return target;
    }

    public SpellTypes getSpellType() {
        return spellType;
    }

    @Override
    public String print() {
        return "If " + target.name() + " is casting " + spellType.name();
    }
}
