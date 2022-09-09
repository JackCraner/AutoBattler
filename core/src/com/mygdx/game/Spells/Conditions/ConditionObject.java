package com.mygdx.game.Spells.Conditions;

public class ConditionObject
{


    ConditionType type;
    float value;
    public ConditionObject(ConditionType type, float value)
    {
        this.type = type;
        this.value =value;
    }

    public ConditionType getType() {
        return type;
    }

    public float getValue() {
        return value;
    }

    public String getDescription()
    {

        if (value == 0)
        {

            return type.getDescription();
        }
        else
        {

            return String.format(getType().getDescription(),value);
        }

    }
}
