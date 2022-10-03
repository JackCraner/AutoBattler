package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ChangeCasttimeSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ModifierType;

public class ChangeCastTime extends IsEffectComponent{

    private int castTimeModifier;
    private ModifierType castTimeType;
    public ChangeCastTime(int modifier, ModifierType type)
    {
        this.castTimeModifier = modifier;
        this.castTimeType = type;
    }

    public int getCastTimeModifier() {
        return castTimeModifier;
    }

    public ModifierType getCastTimeType() {
        return castTimeType;
    }

    @Override
    public String printEffect() {
        return (castTimeType==ModifierType.ABSOLUTE?"Cast Time of " + castTimeModifier: (castTimeModifier>0?"Increased ":"Decreased ") + " Cast Time by" + castTimeModifier);
    }

    @Override
    public void getExecution(BattlerFrame[] battlers)
    {
        ChangeCasttimeSystem.instance.execute(this,battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new ChangeCastTime(castTimeModifier, castTimeType);
    }
}
