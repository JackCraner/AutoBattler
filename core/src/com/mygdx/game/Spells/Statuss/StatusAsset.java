package com.mygdx.game.Spells.Statuss;

import com.mygdx.game.Spells.Effects.EffectTypes.StatusEffect;

import java.util.HashMap;

public enum StatusAsset
{
    POISON("OnPoison"),
    BURN("Burn"),
    COUNTERSPELL("Counter"),
    EXPUNGE("Expunge"),
    BRITTLE("Brittle"),
    FREEZE("Freeze"),
    RIPTIDE("Riptide"),

    ;

    private String icon;

    StatusAsset(String icon)
    {
        this.icon = icon;
    }
    public String getIcon() {
        return "assets/" + icon+".png";
    }

    public static HashMap<StatusEffect, StatusAsset> statusAssetReference = new HashMap<>();

    static {
        statusAssetReference.put(StatusEffect.POISON,StatusAsset.POISON);
        statusAssetReference.put(StatusEffect.BURN,StatusAsset.BURN);
        statusAssetReference.put(StatusEffect.EXPUNGE,StatusAsset.EXPUNGE);
        statusAssetReference.put(StatusEffect.BRITTLE,StatusAsset.BRITTLE);
        statusAssetReference.put(StatusEffect.FREEZE,StatusAsset.FREEZE);
        statusAssetReference.put(StatusEffect.RIPTIDE,StatusAsset.RIPTIDE);
    }

}
