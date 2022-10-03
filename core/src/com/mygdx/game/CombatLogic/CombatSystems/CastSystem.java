package com.mygdx.game.CombatLogic.CombatSystems;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BattlerState;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastHistoryComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.SpellListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IsEffectComponent;


import java.util.Iterator;

public class CastSystem
{
    public static CastSystem instance = new CastSystem();
    private CastSystem()
    {

    }

    public void castSpell(BattlerFrame caster, BattlerFrame other, BattlerFrame casterNew, BattlerFrame otherNew)
    {
        Spell s = casterNew.getComponent(CastComponent.class).getSpell();

        for (Effect e: s.getEffects())
        {
            routeEffect(e,new BattlerFrame[]{caster,other}, new BattlerFrame[]{casterNew,otherNew});
        }
        casterNew.getComponent(SpellListComponent.class).pushNext();

    }
    public void routeEffect(Effect e, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {

        for (Iterator<? extends IsEffectComponent> it = e.getComponentIterator(); it.hasNext(); )
        {
            IsEffectComponent cs = it.next();
            cs.getExecution(battlers, newBattlers);
        }

    }

    public void archiveCast(BattlerFrame newBattler)
    {
        CastHistoryComponent battlerCastHistory = newBattler.getComponent(CastHistoryComponent.class);
        CastComponent battleCurrentCast = newBattler.getComponent(CastComponent.class);
        BattlerState battlerCurrentState = newBattler.getComponent(BattlerState.class);

        battlerCastHistory.addCastHistoryItem(battleCurrentCast.getSpell(),battlerCurrentState.getState());


    }
    public void updateCastComponent(BattlerFrame newBattler)
    {
        CastComponent battlerCastComponent = newBattler.getComponent(CastComponent.class);
        battlerCastComponent.setSpell(newBattler.getComponent(SpellListComponent.class).getCurrent());

    }



}
