package com.mygdx.game.Spells;

import java.util.HashMap;


public enum Asset
{

    FIREBALL("Damage","Fireball"),
    POISON("Poison","PoisonBall"),
    HEALBALL("OnHP", "PoisonBall"),
    COUNTERSPELL("Counter","Fireball"),
    BURN("Burn","Fireball"),
    EXPUNGE("Expunge","PoisonBall"),
    MANA("Mana1","Fireball"),



    ARCANEBOLT("Arcane/1","Fireball"),
    ARCANEMISSLE("Arcane/5","Fireball"),
    GUST("Wind/2", "Fireball"),
    FIREBOLT("Fire/Pyroblast","Fireball"),

    ;

    private String icon;
    private String projectile;
    Asset(String icon, String projectile)
    {
        this.icon = icon;
        this.projectile = projectile;
    }

    public String getIcon() {
        return "assets/SpellSplash/" + icon+".png";
    }

    public String getProjectile() {
        return projectile +".png";
    }

}
