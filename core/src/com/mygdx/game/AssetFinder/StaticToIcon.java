package com.mygdx.game.AssetFinder;

import java.util.HashMap;

public class StaticToIcon
{

    public static StaticToIcon instance = new StaticToIcon();
    private StaticToIcon()
    {

    }


    public static HashMap<String,String> converter = new HashMap<>();
    {{
        converter.put("Burn","Burn");
        converter.put("Frozen", "Freeze");
        converter.put("Freeze", "8");
        converter.put("Poison","OnPoison");
        converter.put("Brittle","Brittle");
        converter.put("FIRE Modifier", "Fire Modifier");
        converter.put("ARCANE Modifier" , "Arcane Modifier");
        converter.put("ICE Modifier", "Ice Modifier");
        converter.put("Moon Fire", "7");
    }
    }
}
