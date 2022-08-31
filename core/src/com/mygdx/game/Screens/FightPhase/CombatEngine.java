package com.mygdx.game.Screens.FightPhase;

import com.mygdx.game.Characters.Battler;
import com.mygdx.game.Spells.Effects.EffectType;
import com.mygdx.game.Spells.Spell;
import com.mygdx.game.Spells.SpellEffectType;
import com.mygdx.game.Spells.Statuss.Status;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombatEngine
{
    Battler player;
    Battler enemy;
    CombatSystem cS;


    LinkedList<FightFrame> fightSequence = new LinkedList<>();
    List<Spell>[] spellLists = new List[2];
    int turnCounter;
    public CombatEngine(Battler p,Battler e)
    {
        this.player = p;
        this.enemy =e;
        this.cS = new CombatSystem();


        spellLists[0] = player.getSpellDeck().getSpellList();
        spellLists[1] = enemy.getSpellDeck().getSpellList();
        turnCounter = 0;


        simulate();

    }

    public void simulate()
    {
        BattlerFrame initalPlayer = new BattlerFrame(player.getMaxHealth(),player.getMana(),spellLists[0].get(0).getCastTime(),0,new Status[]{});
        BattlerFrame initalEnemy = new BattlerFrame(enemy.getMaxHealth(),enemy.getMana(),spellLists[1].get(0).getCastTime(),0,new Status[]{});
        fightSequence.add(gameStep(new FightFrame(initalPlayer,initalEnemy)));


        while(!checkEnd() && turnCounter < 100)
        {

            FightFrame lastFrame = fightSequence.get(fightSequence.size()-1);
            fightSequence.add(gameStep(lastFrame));
            turnCounter ++;
        }
        for (FightFrame f: fightSequence)
        {
            System.out.println(f.printFrame());
        }


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


    public FightFrame gameStep(final FightFrame f)
    {
        BattlerFrame[] battlers = {f.getPlayer(),f.getEnemy()};
        BattlerFrame[] newBattlers = {f.getPlayer().clone(),f.getEnemy().clone()};
        for (int i = 0; i <battlers.length;i++)
        {
            for (SpellEffectType effect: CombatSystem.logicMap.keySet())
            {
                Status s = newBattlers[i].getStatus(effect);

                if (s !=null)
                {
                    cS.gameTick(s,battlers[i],newBattlers[i]);
                }

            }
            if (battlers[i].getCooldown() == 0)
            {



                    cS.castSpell(spellLists[i].get(battlers[i].getSpellPointer()),battlers[i],newBattlers[i],battlers[Math.abs(i-1)],newBattlers[Math.abs(i-1)]);
                    if (battlers[i].getSpellPointer() == spellLists[i].size()-1)
                    {
                        newBattlers[i].setSpellPointer(0);
                    }
                    else
                    {
                        newBattlers[i].setSpellPointer(battlers[i].getSpellPointer()+1);
                    }
                    newBattlers[i].setCooldown(spellLists[i].get(newBattlers[i].getSpellPointer()).getCastTime());






            }
            newBattlers[i].setCooldown(newBattlers[i].getCooldown()-1);
        }
        return new FightFrame(newBattlers[0],newBattlers[1]);

    }

    public LinkedList<FightFrame> getFightSequence() {
        return fightSequence;
    }
}
