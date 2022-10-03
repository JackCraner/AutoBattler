package com.mygdx.game.CombatLogic;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ArmorArray;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BattlegroundComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BattlerState;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BuffArray;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastHistoryComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.EffectListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.HealthComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.SpellListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerStates;
import com.mygdx.game.CombatLogic.CombatSystems.CastSystem;
import com.mygdx.game.CombatLogic.CombatSystems.CastTimeSystem;
import com.mygdx.game.CombatLogic.CombatSystems.ManaSystem;
import com.mygdx.game.CombatLogic.EffectSystems.ApplyStatusSystem;
import com.mygdx.game.CombatLogic.EffectSystems.BattleFieldSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.BattlegroundTypes;

import java.util.LinkedList;
import java.util.Random;

public class CombatManager {


    public CombatManager()
    {

    }
    public BattlerFrame convertBattlerToBF(Battler b)
    {
        BattlerFrame bfs = new BattlerFrame();
        bfs.addComponent(new BattlegroundComponent(BattlegroundTypes.NULL));
        bfs.addComponent(new ArmorArray());
        bfs.addComponent(new BuffArray());
        bfs.addComponent(new EffectListComponent());
        bfs.addComponent(new CastHistoryComponent());
        bfs.addComponent(new BattlerState());
        bfs.addComponent(new SpellListComponent(b.getSpellList()));
        bfs.addComponent(new CastComponent(b.getSpellList().getCurrent()));
        bfs.addComponent(new HealthComponent(b.getHealth()));
        bfs.addComponent(new ManaComponent(b.getMaxMana(),b.getCurrentMana()));
        return bfs;
    }

    public LinkedList<FightFrame> simulateFight(Battler[] battlers)
    {

        BattlerFrame bs0 = convertBattlerToBF(battlers[0]);
        BattlerFrame bs1 = convertBattlerToBF(battlers[1]);


        LinkedList<FightFrame> fightFrameList = new LinkedList<>();
        fightFrameList.add(new FightFrame(bs0,bs1));
        int turnCounter = 0;


        while (!checkGameOver(fightFrameList.getLast().getBattleFrames()) && turnCounter < 40 )
        {

            fightFrameList.add(gameStep(fightFrameList.getLast()));
            turnCounter ++;
        }

        for (int i=0;i<fightFrameList.size();i++)
        {
            System.out.println("Turn: " + i + "\n");
            System.out.println(fightFrameList.get(i).printFrame());
        }
        return fightFrameList;
    }
    Random r = new Random();
    public FightFrame gameStep(FightFrame lastFrame)
    {
        //who ever has more current mana goes first

        //BattlerFrame[] newBattlers = Arrays.stream(battlers).map(BattlerFrame::clone).toArray(BattlerFrame[]::new);
        BattlerFrame[] battlers = lastFrame.getBattleFrames();
        FightFrame newFightFrame = lastFrame.clone();
        BattlerFrame[] newBattlers = newFightFrame.getBattleFrames();

        //cast
        BattleFieldSystem.instance.applyBattleground(battlers,newBattlers);


        //Order
        int startI = 0;
        int step = 1;
        boolean order = true;
        if (battlers[1].getComponent(ManaComponent.class).getCurrentMana() == battlers[0].getComponent(ManaComponent.class).getCurrentMana())
        {
         if (r.nextInt(10)> 5)
         {
             startI = 1;
             step =-1;
             order = false;
         }
        } else if (battlers[1].getComponent(ManaComponent.class).getCurrentMana() > battlers[0].getComponent(ManaComponent.class).getCurrentMana())
        {
            startI = 1;
            step =-1;
            order = false;
        }



        for (int i=startI;defineOrder(i,order);i+=step)
        {

            ApplyStatusSystem.instance.checkAllStatusEffect(battlers[i],newBattlers[i]);
        }





        for (int i=startI;defineOrder(i,order);i+=step)
        {

            if (battlers[i].getComponent(BattlerState.class).getState() == BattlerStates.READY)
            {

                if (ManaSystem.instance.hasMana(battlers[i]))
                {
                    if (CastTimeSystem.instance.isReady(battlers[i]))
                    {

                        CastSystem.instance.castSpell(battlers[i],battlers[Math.abs(i-1)],newBattlers[i],newBattlers[Math.abs(i - 1)]);
                        ManaSystem.instance.spendMana(newBattlers[i]);
                        CastTimeSystem.instance.resetCastTime(newBattlers[i]);
                        CastSystem.instance.archiveCast(newBattlers[i]);
                        CastSystem.instance.updateCastComponent(newBattlers[i]);
                        ApplyStatusSystem.instance.checkAllOnSpellEffect(battlers[i],newBattlers[i]);


                    }
                }
                else
                {
                    CastTimeSystem.instance.resetCastTime(newBattlers[i]);
                    newBattlers[i].getComponent(CastComponent.class).setSpell(newBattlers[i].getComponent(SpellListComponent.class).getNext());
                    newBattlers[i].getComponent(CastComponent.class).setCastTimer(1);

                }
                    CastTimeSystem.instance.incrementCastTime(newBattlers[i]);

            }
            else
            {
                newBattlers[i].getComponent(CastComponent.class).setCastTimer(1);
            }


        }




        return newFightFrame;


    }



    private boolean checkGameOver(BattlerFrame[] battlers)
    {
        for (BattlerFrame b: battlers)
        {
            if (b.getComponent(HealthComponent.class).getCurrentHealth() <= 0)
            {
                return true;
            }
        }
        return false;
    }
    public boolean defineOrder(int i,boolean t)
    {
        if (t)
        {
            return i < 2;
        }
        else
        {
            return i>=0;
        }
    }



}
