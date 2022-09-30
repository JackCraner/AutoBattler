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


//ARCANE
    ARCANEGLOWHAND("Arcane/1"),
    ARCANEFINGERPOINT("Arcane/2"),
    ARCANEHANDSPHERE("Arcane/3"),
    ARCANEHANDGRASP("Arcane/4"),
    ARCANE3STRIKE("Arcane/5"),
    ARCANESYMBOL("Arcane/6"),
    ARCANESKULL("Arcane/8"),
    ARCANEFOOT("Arcane/9"),
    ARCANERAIN("Arcane/10"),
    ARCANESPHERE("Arcane/11"),
    ARCANESPIRAL("Arcane/12"),
    ARCANEGRAB("Arcane/13"),
    ARCANESHIELD("Arcane/14"),
    ARCANEBIGSTRIKE("Arcane/15"),
    ARCANEDISINTIGRATE("Arcane/19"),
    ARCANESTAFF("Arcane/23"),
    ARCANESOULSUCK("Arcane/28"),
    ARCANEEYE("Arcane/42"),
    ARCANEBOTTLE("Arcane/45"),

    //ICE
    ICEBOLT("Ice/1"),
    ICEFROZEN("Ice/2"),
    ICESHARD("Ice/3"),
    ICEBREATH("Ice/4"),
    ICEHOLDICEBERG("Ice/5"),
    ICEMANYSHARDS("Ice/6"),
    ICEFROZENSTUCK("Ice/7"),
    ICESHIELD("Ice/9"),
    ICEFASTSHARD("Ice/10"),
    ICEARMOR("Ice/11"),
    ICERAIN("Ice/12"),
    ICESHIELD2("Ice/13"),
    ICEFROZEN2("Ice/14"),
    ICESTAFF("Ice/15"),
    ICEFACE("Ice/16"),
    ICEPOTION("Ice/17"),
    ICEEXPLOSION("Ice/18"),
    ICESNOWFLAKE("Ice/19"),
    ICESHATTER("Ice/20"),

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
