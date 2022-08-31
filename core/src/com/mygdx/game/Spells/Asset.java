package com.mygdx.game.Spells;

import java.util.HashMap;


public enum Asset
{
    FIREBALL("Damage","Fireball"),
    POISON("Poison","PoisonBall"),
    HEALBALL("OnHP", "PoisonBall"),
    COUNTERSPELL("Counter","Fireball"),
    BURN("Burn","Fireball"),
    EXPUNGE("Expunge","PoisonBall");

    private String icon;
    private String projectile;
    Asset(String icon, String projectile)
    {
        this.icon = icon;
        this.projectile = projectile;
    }

    public String getIcon() {
        return "assets/" + icon+".png";
    }

    public String getProjectile() {
        return projectile +".png";
    }

}
