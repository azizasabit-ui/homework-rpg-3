package com.narxoz.rpg.adapter;

import com.narxoz.rpg.hero.Hero;

public class HeroCombatantAdapter implements Combatant {
    private Hero hero;
    
    public HeroCombatantAdapter(Hero hero) {
        this.hero = hero;
    }
    
    @Override
    public String getName() {
        return hero.getName();
    }
    
    @Override
    public int getAttackPower() {
        return hero.getPower();
    }
    
    @Override
    public void takeDamage(int damage) {
        hero.receiveDamage(damage);
    }
    
    @Override
    public boolean isAlive() {
        return hero.isAlive();
    }
    
    public Hero getHero() {
        return hero;
    }
}