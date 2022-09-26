package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;



import com.mygdx.game.CombatLogic.BattlerFrames.EffectOnBattler;

import java.util.ArrayList;

public class EffectListComponent extends BattleFrameComponent{



    ArrayList<EffectOnBattler> effectOnBattlers;
    public EffectListComponent()
    {
        effectOnBattlers = new ArrayList<>();
    }
    private EffectListComponent(ArrayList<EffectOnBattler> defaultList)
    {
        this.effectOnBattlers =defaultList;
    }

    public ArrayList<EffectOnBattler> getEffectOnBattlers() {
        return effectOnBattlers;
    }

    @Override
    public BattleFrameComponent clone()
    {
        ArrayList<EffectOnBattler> newEffectOnBattler = new ArrayList<>();
        for (EffectOnBattler e: effectOnBattlers)
        {
            newEffectOnBattler.add(e.clone());
        }
        return new EffectListComponent(newEffectOnBattler);
    }

    @Override
    public String printComponent() {
        String s ="Effects On Battlers:: ";
        for (EffectOnBattler e:getEffectOnBattlers())
        {
            s+=e.print() + " | ";
        }
        return s;
    }
}
