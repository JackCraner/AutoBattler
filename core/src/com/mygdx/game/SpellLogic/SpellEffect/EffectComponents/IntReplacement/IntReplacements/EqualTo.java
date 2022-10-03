package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntReplacements;

import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IsIntFormat;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

public class EqualTo implements IsIntFormat {
    private IntReplacementTypes type;
    private TargetType target;
    public EqualTo(IntReplacementTypes type, TargetType target)
    {
        this.target = target;
        this.type = type;
    }

    public TargetType getTarget() {
        return target;
    }

    public IntReplacementTypes getType() {
        return type;
    }

    @Override
    public String print() {
        return String.format(type.description,target.getName(1));
    }
}
