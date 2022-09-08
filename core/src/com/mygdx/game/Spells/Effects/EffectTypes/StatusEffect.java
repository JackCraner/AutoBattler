package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanStatusSystem;
import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanEffectSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.StatusSystem;

public enum StatusEffect implements CanEffectType {



    POISON("POISON","Apply %.0f Stacks of Poison", StatusSystem.PoisonSystem.instance, StatusType.DEBUFF),
    BURN("Burn", "Apply %.0f Stacks of Burn", StatusSystem.BurnSystem.instance,StatusType.DEBUFF),
    FREEZE("Freeze", "Apply %.0f Stacks of Freeze", StatusSystem.FreezeSystem.instance,StatusType.DEBUFF),
    BRITTLE("Brittle", "Apply %.0f Stacks of Brittle", StatusSystem.BrittleSystem.instance,StatusType.DEBUFF),
    RIPTIDE("Riptide", "Apply %.0f Stacks of Riptide", StatusSystem.RipTideSystem.instance,StatusType.BUFF),
    EXPUNGE("Expunge","Poisons tick faster for %.0f seconds", StatusSystem.ExpungeSystem.instance,StatusType.BUFF);

    private String description;
    private String name;
    private CanEffectSystem system;
    private StatusType statusType;
    StatusEffect(String name, String description, CanStatusSystem system, StatusType statusType)
    {
        this.description = description;
        this.name = name;
        this.system = system;
        this.statusType = statusType;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    @Override
    public CanEffectSystem getSystem() {
        return system;
    }

    public StatusType getStatusType() {
        return statusType;
    }
}
