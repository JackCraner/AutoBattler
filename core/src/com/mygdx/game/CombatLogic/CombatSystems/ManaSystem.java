package com.mygdx.game.CombatLogic.CombatSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;

public class ManaSystem
{

    public static ManaSystem instance = new ManaSystem();
    private ManaSystem()
    {

    }

    public boolean hasMana(BattlerFrame caster)
    {
        ManaComponent battlerComponent = caster.getComponent(ManaComponent.class);
        CastComponent battlerCastComponent = caster.getComponent(CastComponent.class);
        return battlerComponent.getCurrentMana()>= battlerCastComponent.getSpell().getManaCost();

    }

    public boolean spendMana(BattlerFrame caster)
    {
        ManaComponent battlerComponent = caster.getComponent(ManaComponent.class);
        CastComponent battlerCastComponent = caster.getComponent(CastComponent.class);
        battlerComponent.setCurrentMana(battlerComponent.getCurrentMana() - battlerCastComponent.getSpell().getManaCost());
        return true;
    }



}
