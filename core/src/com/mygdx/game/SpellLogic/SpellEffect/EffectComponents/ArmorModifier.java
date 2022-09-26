package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ArmorModifierSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;

public class ArmorModifier extends IsEffectComponent
{
    private int mod;
    private DamageTypes type;
    public ArmorModifier(DamageTypes type, int armorModifier)
    {
        this.mod = armorModifier;
        this.type =type;
    }

    public DamageTypes getType() {
        return type;
    }

    public int getMod() {
        return mod;
    }

    @Override
    public String printEffect() {
        return null;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        ArmorModifierSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new ArmorModifier(type,mod);
    }
}
