package com.mygdx.game.Screens.FightPhase;

import com.mygdx.game.Spells.Statuss.Status;

public class FightFrame
{


    private BattlerFrame player;
    private BattlerFrame enemy;
    public FightFrame(BattlerFrame player,BattlerFrame enemy)
    {
        this.player = player;
        this.enemy = enemy;
    }

    public BattlerFrame getEnemy() {
        return enemy;
    }

    public BattlerFrame getPlayer() {
        return player;
    }
    public FightFrame clone()
    {
        return new FightFrame(player.clone(),enemy.clone());
    }
    public String printFrame()
    {
        String s = "Enemy:=\n";
        s += enemy.printFrame();
        s += "Player:=\n";
        s += player.printFrame();
        return s;
        //return "Player HP:" + player.getHealth() + "Player CD: " + player.getCooldown() + " Casting:" + player.getSpellPointer() + "\n" +"Enemy HP:" + enemy.getHealth() + "Enemy CD: " + enemy.getCooldown() + " Casting:" + enemy.getSpellPointer();
    }
}
