package com.mygdx.game.AssetFinder;

public enum CardBaseAsset
{
    FIRE("CardBase"),
    WIND("CardBase"),
    ARCANE("CardBase"),
    ROCK("CardBase"),
    NEUTRAL("CardBase"),
    NATURE("CardBase"),
    ;

    private String location;
    CardBaseAsset(String location)
    {
        this.location = location;
    }

    public String getLocation() {
        return "assets/CardBase/" + location + ".png";
    }
}
