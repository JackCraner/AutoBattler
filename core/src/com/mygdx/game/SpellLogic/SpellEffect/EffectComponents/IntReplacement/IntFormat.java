package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement;

import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.CombatSystems.NumberSystem;

public class IntFormat
{
    private IsIntFormat root;
    private NumberBranch[] branches;
    public IntFormat(IsIntFormat root, NumberBranch... branches)
    {
        this.root = root;
        this.branches = branches;
    }
    public IntFormat(int root, NumberBranch... branches)
    {
        this(new Absolute(root),branches);
    }

    public IsIntFormat getRoot() {
        return root;
    }

    public NumberBranch[] getBranches() {
        return branches;
    }
    public int execute(BattlerFrame[] battlers)
    {
        return NumberSystem.instance.handleIntFormat(this,battlers);
    }

    public String print()
    {
        if (root instanceof Absolute && branches.length == 0)
        {
            return root.print();
        }
        else if (branches.length == 0)
        {
            return "Equal to " + root.print();
        }
        else
        {
            String s = "Equal to " + root.print();
            for (NumberBranch branch:branches)
            {
                s += " " + branch.print();
            }
            return s;
        }
    }
}
