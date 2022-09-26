package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ChannelSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;

public class Channel extends IsEffectComponent
{

    private Effect effect;
    private String spellName;
    public Channel(String spellName, Effect effect) {
        this.spellName = spellName;
        this.effect = effect;
    }

    public String getSpellName() {
        return spellName;
    }

    public Effect getEffect() {
        return effect;
    }

    @Override
    public String printEffect() {
        return "Channel";
    }

    @Override
    public void getExecution(BattlerFrame[] battlers)
    {
        ChannelSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new Channel(spellName,effect);
    }
}
