package com.mygdx.game.CombatLogic.CombatSystems;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastHistoryComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.HealthComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.SpellListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.TurnCounterComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.Absolute;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntFormat;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntReplacements.EqualTo;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntReplacements.IntReplacementTypes;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IsIntFormat;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.NumberBranch;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.Symbols;

public class NumberSystem
{
    public static NumberSystem instance = new NumberSystem();

    private NumberSystem()
    {

    }
    public int handleIntFormat(IntFormat intFormat, BattlerFrame[] battlers)
    {
        int headNumber = handleIsIntFormat(intFormat.getRoot(),battlers);
        for (NumberBranch branch:intFormat.getBranches())
        {
            headNumber = handleBranch(headNumber,branch,battlers);
        }
        return headNumber;
    }
    public int handleBranch(int headNumber,NumberBranch branch,BattlerFrame[] battlers)
    {
        if (branch.getSymbol() == Symbols.ADD)
        {
            return headNumber + handleIsIntFormat(branch.getNumber(),battlers);
        }
        else if (branch.getSymbol() == Symbols.SUBTRACT)
        {
            return headNumber - handleIsIntFormat(branch.getNumber(),battlers);
        }
        else if (branch.getSymbol() == Symbols.MULTIPLY)
        {
            return headNumber * handleIsIntFormat(branch.getNumber(),battlers);
        }
        else if (branch.getSymbol() == Symbols.DIVIDE)
        {
            return headNumber / handleIsIntFormat(branch.getNumber(),battlers);
        }
        return  headNumber;
    }
    public int handleIsIntFormat(IsIntFormat intFormat, BattlerFrame[] battlers)
    {
        if (intFormat instanceof Absolute)
        {
            return (((Absolute) intFormat).getNumber());
        }
        else
        {
            return handleIntReplacement((EqualTo) intFormat,battlers);
        }
    }
    public int handleIntReplacement(EqualTo intReplacement, BattlerFrame[] battlers)
    {
        if (intReplacement.getType() == IntReplacementTypes.CurrentHealth)
        {
            return battlers[intReplacement.getTarget().getValue()].getComponent(HealthComponent.class).getCurrentHealth();
        }
        else if (intReplacement.getType() == IntReplacementTypes.CurrentMana)
        {
            return battlers[intReplacement.getTarget().getValue()].getComponent(ManaComponent.class).getCurrentMana();
        }
        else if (intReplacement.getType() == IntReplacementTypes.TurnNumber)
        {
            return battlers[intReplacement.getTarget().getValue()].getComponent(TurnCounterComponent.class).getTurnCounter();
        }
        else if (intReplacement.getType() == IntReplacementTypes.LastSpellMana)
        {
            return battlers[intReplacement.getTarget().getValue()].getComponent(CastComponent.class).getSpell().getManaCost();
        }
        else if (intReplacement.getType() == IntReplacementTypes.NumberOfSpells)
        {
            return battlers[intReplacement.getTarget().getValue()].getComponent(SpellListComponent.class).getSpellList().size();
        }
        else if (intReplacement.getType() == IntReplacementTypes.NumberOfCasts)
        {
            return battlers[intReplacement.getTarget().getValue()].getComponent(CastHistoryComponent.class).getCastHistoryList().size();
        }
        else if (intReplacement.getType() == IntReplacementTypes.MaxHealth)
        {
            return battlers[intReplacement.getTarget().getValue()].getComponent(HealthComponent.class).getMaxHealth();
        }
        else if (intReplacement.getType() == IntReplacementTypes.MaxMana)
        {
            return battlers[intReplacement.getTarget().getValue()].getComponent(ManaComponent.class).getMaxMana();
        }

        return 0;
    }

}
