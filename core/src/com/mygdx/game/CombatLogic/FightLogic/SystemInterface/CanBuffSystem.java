package com.mygdx.game.CombatLogic.FightLogic.SystemInterface;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.CombatLogic.FightLogic.Cast;
import com.mygdx.game.CombatLogic.FightLogic.SpellLogic.SpellWrapper;
import com.mygdx.game.Spells.Effects.CanEffect;

public interface CanBuffSystem extends CanStatusSystem {

    public void apply(SpellWrapper spell, BattlerFrame caster);

}
