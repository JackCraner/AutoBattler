package com.mygdx.game.CombatLogic;

import com.mygdx.game.Perks.Perk;
import com.mygdx.game.CombatLogic.FightLogic.Cast;
import com.mygdx.game.Screens.FightPhase.BattlerConstants;
import com.mygdx.game.Screens.FightPhase.BattlerFrameGraphic;
import com.mygdx.game.Spells.Effects.EffectTypes.CanEffectType;
import com.mygdx.game.Spells.Statuss.Status;

import java.util.ArrayList;

public class BattlerFrame implements BattlerFrameGraphic
{
    private int health;
    private int mana;
    private ArrayList<Status> statusTypes;
    private Cast cast;
    private BattlerConstants battlerConstant;
    public BattlerFrame(BattlerConstants battlerConstant,int h,int m,ArrayList<Status> statusTypes, Cast cast)
    {
        this.battlerConstant = battlerConstant;
        this.health = h;
        this.mana = m;
        this.statusTypes = statusTypes;
        this.cast = cast;
    }



    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public BattlerConstants getBattlerConstant() {
        return battlerConstant;
    }

    @Override
    public ArrayList<Status> getAllStatus() {
        return statusTypes;
    }


    @Override
    public Cast getCurrentCast() {
        return cast;
    }

    public void setCast(Cast cast) {
        this.cast = cast;
    }

    @Override
    public Perk[] getPerkList() {
        return new Perk[0];
    }

    public Status getStatus(CanEffectType type)
    {
        for (Status s: statusTypes)
        {
            if (s.getType() == type)
            {
                return s;
            }

        }
        return null;
    }




    public BattlerFrame clone()
    {
        ArrayList<Status> newStatus = new ArrayList<>();
        for (Status s: statusTypes)
        {
            newStatus.add(s.clone());
        }
        return new BattlerFrame(battlerConstant,health,mana,newStatus,cast.clone());
    }

    public String printFrame()
    {
        String s= "";
        s += "HP: " + getHealth() + "  Mana: " + getMana() + "\n";
        s +="Casting: " + cast.getSpellWrapper().getSpell() + " CD: " + cast.getCastTimer() + " Code: " + cast.getCastCode() + "\n";
        for (Status status:getAllStatus())
        {
            s += "Status: " + status.getType().getName() + " Stack: " + status.getStackNumber() + " CD: " +status.getTickCooldown() + "\n";
        }
        return  s + "\n";
    }
}
