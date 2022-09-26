package com.mygdx.game.AssetFinder;

public enum Projectiles
{
    FIREBALL("Fireball");


    ;
    private String location;
    Projectiles(String location)
    {
        this.location = location;
    }

    public String getLocation() {
        return "assets/Projectiles/" + location + ".png";
    }
}

