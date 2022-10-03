package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ArmorArray;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ArmorModifier;

public class ArmorModifierSystem implements IsEffectSystem<ArmorModifier> {
    public static ArmorModifierSystem instance = new ArmorModifierSystem();
    private ArmorModifierSystem()
    {

    }

    @Override
    public void execute(ArmorModifier effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {
        ArmorArray battlerComponent = battlers[effect.getTarget().getValue()].getComponent(ArmorArray.class);
        ArmorArray battlerComponentNew = newBattlers[effect.getTarget().getValue()].getComponent(ArmorArray.class);
        battlerComponentNew.setArmorFor(effect.getType(),battlerComponent.getArmorFor(effect.getType()) + effect.getMod());
    }


}
