package com.mygdx.game.AssetFinder;

public enum SpellSplash
{
//Fire
    FIREFACE("Fire/1"),
    FIREHAND("Fire/2"),
    FIREPHENIX("Fire/3"),
    FIRETORNADO("Fire/4"),
    FIREMEDITATE("Fire/5"),
    FIRESPEED("Fire/6"),
    FIREWAVE("Fire/7"),
    FIREMETEORS("Fire/8"),
    FIREBURNT("Fire/9"),
    FIREPYROBLAST("Fire/10"),
    FIRESTAFF("Fire/11"),
    FIREBITE("Fire/12"),
    FIRE3STRIKE("Fire/13"),
    FIRESTAFFSHOOT("Fire/14"),
    FIRERITUAL("Fire/15"),
    FIREHANDCLICK("Fire/16"),
    FIREASTROID("Fire/17"),
    ;

    private String location;
    SpellSplash(String location)
    {
        this.location= location;
    }

    public String getLocation() {
        return "assets/SpellSplash/" + location + ".png";
    }
}
