package com.mygdx.game.CombatLogic.BattlerFrames;

import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BattleFrameComponent;


import java.util.ArrayList;

public class BattlerFrame
{

    ArrayList<BattleFrameComponent> battlerComponents;

    public BattlerFrame()
    {
        battlerComponents = new ArrayList<>();
    }

    public <T extends BattleFrameComponent> T getComponent(Class<T> componentClass)
    {
        for (BattleFrameComponent ec: battlerComponents)
        {
            if ((componentClass.isAssignableFrom(ec.getClass()))){
                return componentClass.cast(ec);
            }
        }
        System.out.println("Component: " + componentClass.getName() + " Not Found");
        return null;

    }
    public void addComponent(BattleFrameComponent ec)
    {

        this.battlerComponents.add(ec);
    }
    public <T extends BattleFrameComponent> void removeComponent(Class<T> componentClass)
    {
        for (int i= 0; i<battlerComponents.size();i++)
        {

            if (battlerComponents.get(i).getClass().isAssignableFrom(componentClass))
            {
                battlerComponents.remove(i);
                return;
            }
        }
    }
    public String printFrame()
    {
        String s = "Components: \n";
        for (BattleFrameComponent bfs: battlerComponents)
        {
            s+=bfs.printComponent() + "\n";
        }
        return s;
    }
    public BattlerFrame clone()
    {
        BattlerFrame newObject = new BattlerFrame();
        for (BattleFrameComponent bfs:battlerComponents)
        {
            newObject.addComponent(bfs.clone());
        }
        return newObject;
    }



}
