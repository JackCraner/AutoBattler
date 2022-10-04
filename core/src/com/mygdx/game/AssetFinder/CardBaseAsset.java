package com.mygdx.game.AssetFinder;

public enum CardBaseAsset
{
    FIRE("Card2"),
    WIND("Card2"),
    ARCANE("Card2"),
    ROCK("Card2"),
    NEUTRAL("Card2"),
    NATURE("Card2"),
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
