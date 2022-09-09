package com.mygdx.game.Cards;

public enum CardTypes
{
    FIRE("CardBase"),
    WIND("CardBase"),
    ICE("CardBase"),
    NATURE("CardBase"),
    ROCK("CardBase"),
    ARCANE("CardBase"),
    NEUTRAL("CardBase"),
    PERK("Perk"),
    ;

    String cardFace;
    CardTypes(String cardFace)
    {
        this.cardFace = cardFace;
    }

    public String getCardFace() {
        return cardFace + ".png";
    }
}
