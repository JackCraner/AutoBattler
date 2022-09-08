package com.mygdx.game.CombatLogic;

import com.mygdx.game.Characters.Battler;
import com.mygdx.game.Screens.FightPhase.BattlerConstants;
import com.mygdx.game.CombatLogic.FightLogic.Cast;
import com.mygdx.game.CombatLogic.FightLogic.CastCodeTypes;
import com.mygdx.game.CombatLogic.FightLogic.Systems.BuffSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.CombatSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.PerkSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.StatusSystem;
import com.mygdx.game.SingleGame;
import com.mygdx.game.Spells.Spell;
import com.mygdx.game.Spells.Statuss.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombatManager
{




    LinkedList<FightFrame> fightSequence = new LinkedList<>();
    List<Spell>[] spellLists = new List[2];
    Battler[] battlers;
    int turnCounter;
    public CombatManager(Battler p, Battler e)
    {

        this.battlers = new Battler[]{p,e};

        List<Spell>[] spellLists = new List[2];
        spellLists[0] = Arrays.asList( PerkSystem.instance.applyPerks(battlers[0].getSpells(), battlers[0].getPerks()));
        spellLists[1] = Arrays.asList( PerkSystem.instance.applyPerks(battlers[1].getSpells(), battlers[1].getPerks()));
        turnCounter = 0;

        BattlerConstants playerConstant = new BattlerConstants(spellLists[0],battlers[0].getPerks());
        BattlerConstants enemyConstant = new BattlerConstants(spellLists[1],battlers[1].getPerks());
        BattlerFrame initPlayer = new BattlerFrame(playerConstant,battlers[0].getMaxHealth(), battlers[0].getMana(), new ArrayList<Status>(), new Cast(CastCodeTypes.SUCCESS, playerConstant.getBattlerSpellList().next()));
        BattlerFrame initEnemy = new BattlerFrame(enemyConstant,battlers[1].getMaxHealth(), battlers[1].getMana(), new ArrayList<Status>(), new Cast(CastCodeTypes.SUCCESS, enemyConstant.getBattlerSpellList().next()));
        simulate(initPlayer,initEnemy);

    }

    public void simulate(BattlerFrame initPlayer, BattlerFrame initEnemy)
    {

        fightSequence.add(gameStep(new FightFrame(initPlayer,initEnemy)));
        while(!checkEnd() && turnCounter < 30)
        {
            FightFrame lastFrame = fightSequence.get(fightSequence.size()-1);
            fightSequence.add(gameStep(lastFrame));
            turnCounter ++;
        }
        for (FightFrame f: fightSequence)
        {
            System.out.println(f.printFrame());
        }
        /**
         *

        BattlerFrame initalPlayer = new BattlerFrame(SingleGame.maxHealth,player.getMana(),spellLists[0].get(0).getOrangeBox(),0,new ArrayList<Status>(),true);
        BattlerFrame initalEnemy = new BattlerFrame(SingleGame.maxHealth,enemy.getMana(),spellLists[1].get(0).getOrangeBox(),0,new ArrayList<Status>(),true);
        fightSequence.add(gameStep(new FightFrame(initalPlayer,initalEnemy)));


        while(!checkEnd() && turnCounter < 100)
        {

            FightFrame lastFrame = fightSequence.get(fightSequence.size()-1);
            fightSequence.add(gameStep(lastFrame));

            turnCounter ++;
        }
        player.setMana(fightSequence.get(fightSequence.size()-1).getPlayer().getMana());
        for (FightFrame f: fightSequence)
        {
            System.out.println(f.printFrame());
        }

    **/
    }
    public boolean checkEnd()
    {
        FightFrame f = fightSequence.get(fightSequence.size() -1);
        if (f.getEnemy().getHealth() <=0 || f.getPlayer().getHealth() <= 0)
        {

            return true;
        }
        return false;
    }


    public FightFrame gameStep(final FightFrame f) {
        BattlerFrame[] newBattlerFrame = {f.getPlayer().clone(), f.getEnemy().clone()};

        for (int i = 0; i < newBattlerFrame.length; i++) {
            for (Status s : newBattlerFrame[i].getAllStatus())
            {
                if (s.getTickCooldown() == 3)
                {
                    StatusSystem.instance.handleTick(s, newBattlerFrame[i]);
                    s.setTickCooldown(0);
                }
                s.setTickCooldown(s.getTickCooldown()+1);

            }

            BuffSystem.instance.applyBuffs(newBattlerFrame[i]);

            checkSpell(newBattlerFrame[i].getCurrentCast(),newBattlerFrame[i]);

            if (newBattlerFrame[i].getCurrentCast().getCastTimer() == newBattlerFrame[i].getCurrentCast().getSpellWrapper().getCastTime())
            {
                CombatSystem.instance.castSpell(newBattlerFrame[i].getCurrentCast(), newBattlerFrame[i], newBattlerFrame[Math.abs(i - 1)]);
                newBattlerFrame[i].setCast(new Cast(CastCodeTypes.SUCCESS, newBattlerFrame[i].getBattlerConstant().getBattlerSpellList().next()));
            }
            newBattlerFrame[i].getCurrentCast().setCastTimer(newBattlerFrame[i].getCurrentCast().getCastTimer() +1);
        }
        return new FightFrame(newBattlerFrame[0], newBattlerFrame[1]);

    }


    public void checkSpell(Cast c, BattlerFrame caster)
    {
        if (c.getSpellWrapper().getManaCost() >caster.getMana())
        {
            c.setCastCode(CastCodeTypes.MANA_FAILURE);
        }

    }
        /**
        BattlerFrame[] battlers = {f.getPlayer(),f.getEnemy()};
        BattlerFrame[] newBattlers = {f.getPlayer().clone(),f.getEnemy().clone()};
        for (int i = 0; i <battlers.length;i++) {
            for (Status s : newBattlers[i].getAllStatus()) {

                cS.gameTick(s, battlers[i], newBattlers[i]);


            }

            if (battlers[i].getCooldown() == 0) {

                cS.castSpell(spellLists[i].get(battlers[i].getSpellPointer()), battlers[i], newBattlers[i], battlers[Math.abs(i - 1)], newBattlers[Math.abs(i - 1)]);
                if (battlers[i].getSpellPointer() == spellLists[i].size() - 1) {
                    newBattlers[i].setSpellPointer(0);
                } else {
                    newBattlers[i].setSpellPointer(battlers[i].getSpellPointer() + 1);
                }


                if (newBattlers[i].getSpellSuccess()) {
                    newBattlers[i].setCooldown(spellLists[i].get(newBattlers[i].getSpellPointer()).getOrangeBox());
                    newBattlers[i].setCooldown(newBattlers[i].getCooldown() - 1);
                }



            } else {
                newBattlers[i].setCooldown(battlers[i].getCooldown() - 1);
            }

            if (!(cS.checkForMana(spellLists[i].get(newBattlers[i].getSpellPointer()), battlers[i], newBattlers[i])))
            {
                newBattlers[i].setCooldown(0);
            }
        }

        formalizeBattlerFrame(newBattlers[0]);
        formalizeBattlerFrame(newBattlers[1]);
        return new FightFrame(newBattlers[0],newBattlers[1]);

         **/


    public LinkedList<FightFrame> getFightSequence() {
        return fightSequence;
    }

    public void formalizeBattlerFrame(BattlerFrame bf)
    {
        if (bf.getHealth() > SingleGame.maxHealth)
        {
            bf.setHealth(SingleGame.maxHealth);
        }
        if (bf.getHealth() <0)
        {
            bf.setHealth(0);
        }


        if (bf.getMana() > SingleGame.maxMana)
        {
            bf.setMana(SingleGame.maxMana);
        }
    }
}
