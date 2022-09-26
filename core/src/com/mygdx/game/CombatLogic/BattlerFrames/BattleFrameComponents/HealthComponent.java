package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;

public class HealthComponent extends BattleFrameComponent
{
    private int currentHealth;
    private int maxHealth;

    public HealthComponent(int maxHealth)
    {
        this(maxHealth,maxHealth);
    }
    private HealthComponent(int maxHealth, int currentHealth)
    {
        this.currentHealth =currentHealth;
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public BattleFrameComponent clone() {
        return new HealthComponent(maxHealth,currentHealth);
    }

    @Override
    public String printComponent() {
        return "Current Health: " + getCurrentHealth() + "     Max health:" + getMaxHealth();
    }
}
