package com.mygdx.game.Spells.Statuss;

import com.mygdx.game.Spells.Asset;
import com.mygdx.game.Spells.Effects.Effect;
import com.mygdx.game.Spells.Effects.EffectType;
import com.mygdx.game.Spells.Spell;
import com.mygdx.game.Spells.SpellEffectType;

import java.util.HashMap;

public enum StatusAsset
{
    POISON("OnPoison"),
    BURN("Burn"),
    COUNTERSPELL("Counter"),
    EXPUNGE("Expunge");

    private String icon;

    StatusAsset(String icon)
    {
        this.icon = icon;
    }
    public String getIcon() {
        return "assets/" + icon+".png";
    }

    public static HashMap<EffectType, StatusAsset> statusAssetReference = new HashMap<>();

    static {
        statusAssetReference.put(EffectType.POISON,StatusAsset.POISON);
        statusAssetReference.put(EffectType.BURN,StatusAsset.BURN);
        statusAssetReference.put(EffectType.COUNTERSPELL,StatusAsset.COUNTERSPELL);
        statusAssetReference.put(EffectType.EXPUNGE,StatusAsset.EXPUNGE);
    }

}
