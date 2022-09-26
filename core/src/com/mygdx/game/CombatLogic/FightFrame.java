package com.mygdx.game.CombatLogic;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;

public class FightFrame
{
    private BattlerFrame player;
    private BattlerFrame enemy;

    public FightFrame(BattlerFrame player, BattlerFrame enemy)
    {
        this.player = player;
        this.enemy = enemy;


    }
    public BattlerFrame[] getBattleFrames()
    {
        return new BattlerFrame[]{player,enemy};
    }
    public FightFrame clone()
    {
        return new FightFrame(player.clone(),enemy.clone());
    }



    public String printFrame() {
       String s ="";
        s += "Player:=\n";
        s += player.printFrame() + "\n";
        s += "Enemy:=\n";
        s += enemy.printFrame() + "\n";
        s += "-----------------------";
        return s;
    }



}
