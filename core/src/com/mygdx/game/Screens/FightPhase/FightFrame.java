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
        s += "HP: " + enemy.getHealth() + "\n";
        for (Status status:enemy.getAllStatus())
        {
            s += "Status: " + status.getType().getName() + " Stack: " + status.getStackNumber() + " CD: " +status.getTickCooldown() + "\n";
         }

        s += "Player:=\n";
        s +="HP: " + player.getHealth() + "\n";
        for (Status status:player.getAllStatus())
        {
            s += "Status: " + status.getType().getName() + " Stack: " + status.getStackNumber() + " CD: " +status.getTickCooldown() + "\n";
        }
        s+="\n";
        return s;
        //return "Player HP:" + player.getHealth() + "Player CD: " + player.getCooldown() + " Casting:" + player.getSpellPointer() + "\n" +"Enemy HP:" + enemy.getHealth() + "Enemy CD: " + enemy.getCooldown() + " Casting:" + enemy.getSpellPointer();
    }
}
