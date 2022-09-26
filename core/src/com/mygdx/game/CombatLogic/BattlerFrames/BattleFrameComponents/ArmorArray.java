package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.ModifierArray;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;



public class ArmorArray extends BattleFrameComponent
{
    private ModifierArray armorArray;
    public ArmorArray()
    {
        this.armorArray = new ModifierArray();
    }
    private ArmorArray(ModifierArray array)
    {
        this.armorArray=  array;
    }

    public int getArmorFor(DamageTypes type)
    {
        return armorArray.getModifierFor(type);
    }
    public void setArmorFor(DamageTypes type, int newStrength)
    {
        armorArray.setModifierFor(type, newStrength);
    }

    @Override
    public BattleFrameComponent clone() {
        return new ArmorArray(armorArray.clone());
    }

    @Override
    public String printComponent() {
        String s = "ARMOR:: ";
        for (DamageTypes t: DamageTypes.values())
        {
            s += t.name() + ": " + getArmorFor(t) + " | ";
        }
        return s;
    }
}
