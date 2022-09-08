package com.mygdx.game.Screens.FightPhase;

import com.mygdx.game.Perks.Perk;
import com.mygdx.game.CombatLogic.FightLogic.Cast;
import com.mygdx.game.Spells.Statuss.Status;

import java.util.ArrayList;

public interface BattlerFrameGraphic
{
    public int getHealth();
    public int getMana();
    public ArrayList<Status> getAllStatus();
    public Cast getCurrentCast();
    public Perk[] getPerkList();


}
