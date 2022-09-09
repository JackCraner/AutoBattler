package com.mygdx.game.CombatLogic.FightLogic.Systems;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.CombatLogic.FightLogic.Cast;
import com.mygdx.game.CombatLogic.FightLogic.SpellLogic.SpellBuff;
import com.mygdx.game.CombatLogic.FightLogic.SpellLogic.SpellWrapper;
import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanBuffSystem;
import com.mygdx.game.Spells.Effects.CanEffect;
import com.mygdx.game.Spells.Statuss.Status;

public class BuffSystem
{
    public static BuffSystem instance = new BuffSystem();
    private BuffSystem()
    {}

    public void applyBuffs(BattlerFrame caster)
    {
        Cast currentCast = caster.getCurrentCast();




    }


    private static class ManaCostSystem implements CanBuffSystem
    {


        @Override
        public void apply(SpellWrapper spell, BattlerFrame caster) {

        }

        @Override
        public void execute(CanEffect effect, BattlerFrame[] battlers) {

        }

        @Override
        public boolean tick(Status s, BattlerFrame user) {
            return false;
        }
    }
    public static class DamageBuffSystem implements CanBuffSystem
    {

        public static DamageBuffSystem instance = new DamageBuffSystem();
        @Override
        public void apply(SpellWrapper spell, BattlerFrame caster) {

        }

        @Override
        public void execute(CanEffect effect, BattlerFrame[] battlers) {

        }

        @Override
        public boolean tick(Status s, BattlerFrame user) {
            return false;
        }
    }

}
