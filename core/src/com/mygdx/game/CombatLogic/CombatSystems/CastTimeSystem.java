package com.mygdx.game.CombatLogic.CombatSystems;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;


public class CastTimeSystem
{

    public static CastTimeSystem instance = new CastTimeSystem();
    private CastTimeSystem()
    {}


    public int calculateCastTime(BattlerFrame caster)
    {
        CastComponent battlerComponent = caster.getComponent(CastComponent.class);
        return battlerComponent.getSpell().getCastTime();


    }
    public void resetCastTime(BattlerFrame caster)
    {
        CastComponent battlerComponent = caster.getComponent(CastComponent.class);
        battlerComponent.setCastTimer(0);

    }

    public boolean isReady(BattlerFrame caster)
    {
        CastComponent battlerComponent = caster.getComponent(CastComponent.class);
        return calculateCastTime(caster) == battlerComponent.getCastTimer();
    }

    public boolean incrementCastTime(BattlerFrame caster)
    {
        CastComponent battlerComponent = caster.getComponent(CastComponent.class);
        battlerComponent.setCastTimer(battlerComponent.getCastTimer() +1);
        return true;
    }




}
