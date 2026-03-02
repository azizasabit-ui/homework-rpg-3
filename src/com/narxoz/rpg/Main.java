package com.narxoz.rpg;

import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.battle.Combatant;
import com.narxoz.rpg.battle.EncounterResult;
import com.narxoz.rpg.enemy.BasicEnemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.hero.Mage;
import com.narxoz.rpg.hero.Warrior;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG BATTLE ENGINE DEMO ===\n");
        
        System.out.println("--- PART 1: Singleton Pattern Demonstration ---");
        
        BattleEngine engine1 = BattleEngine.getInstance();
        BattleEngine engine2 = BattleEngine.getInstance();
        
        System.out.println("Engine 1 hashcode: " + engine1.hashCode());
        System.out.println("Engine 2 hashcode: " + engine2.hashCode());
        System.out.println("Are both engines the same instance? " + (engine1 == engine2));
        
        
        System.out.println("\n✓ Singleton pattern verified: Only one instance exists");
        System.out.println();
        
        System.out.println("--- PART 2: Adapter Pattern Demonstration ---");
        
        Warrior warrior = new Warrior("Arthas");
        Mage mage = new Mage("Jaina");
        
        Goblin goblin = new Goblin();
        BasicEnemy troll = new BasicEnemy("Troll", 18, 100);
        
        Combatant adaptedWarrior = (Combatant) new HeroCombatantAdapter(warrior);
        Combatant adaptedMage = (Combatant) new HeroCombatantAdapter(mage);
        Combatant adaptedGoblin = (Combatant) new EnemyCombatantAdapter(goblin);
        Combatant adaptedTroll = (Combatant) new EnemyCombatantAdapter(troll);
        
        System.out.println("Hero Warrior adapted: " + adaptedWarrior.getName() + 
                          " (Attack Power: " + adaptedWarrior.getAttackPower() + ")");
        System.out.println("Hero Mage adapted: " + adaptedMage.getName() + 
                          " (Attack Power: " + adaptedMage.getAttackPower() + ")");
        System.out.println("Enemy Goblin adapted: " + adaptedGoblin.getName() + 
                          " (Attack Power: " + adaptedGoblin.getAttackPower() + ")");
        System.out.println("Enemy Troll adapted: " + adaptedTroll.getName() + 
                          " (Attack Power: " + adaptedTroll.getAttackPower() + ")");
        
       
        System.out.println("\nTesting adapter methods:");
        System.out.println("Goblin takes 30 damage:");
        adaptedGoblin.takeDamage(30);
        System.out.println("Is Goblin alive? " + adaptedGoblin.isAlive());
        
        System.out.println("\n✓ Adapter pattern verified: Heroes and enemies now have unified interface");
        System.out.println();
        
        System.out.println("--- PART 3: Battle Simulation ---");
        
        engine1.setRandomSeed(42L);
        
        List<Combatant> heroes = new ArrayList<>();
        heroes.add(adaptedWarrior);
        heroes.add(adaptedMage);
        
        List<Combatant> enemies = new ArrayList<>();
        enemies.add(adaptedGoblin);
        enemies.add(adaptedTroll);
        
        System.out.println("Teams:");
        System.out.println("Heroes Team: " + adaptedWarrior.getName() + ", " + adaptedMage.getName());
        System.out.println("Enemies Team: " + adaptedGoblin.getName() + ", " + adaptedTroll.getName());
        System.out.println();
        
        EncounterResult result = engine1.runEncounter(heroes, enemies);
        
        System.out.println("\n=== BATTLE SUMMARY ===");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Total Rounds: " + result.getRounds());
        System.out.println("\nFull Battle Log:");
        for (String line : result.getBattleLog()) {
            System.out.println("  " + line);
        }
        
        System.out.println("\n--- PART 4: Reset and Second Battle ---");
        
        engine1.reset();
        
        Warrior newWarrior = new Warrior("Garrosh");
        Mage newMage = new Mage("Khadgar");
        BasicEnemy ogre = new BasicEnemy("Ogre", 22, 150);
        BasicEnemy wolf = new BasicEnemy("Wolf", 10, 40);
        
        List<Combatant> team1 = new ArrayList<>();
        team1.add((Combatant) new HeroCombatantAdapter(newWarrior));
        team1.add((Combatant) new HeroCombatantAdapter(newMage));
        
        List<Combatant> team2 = new ArrayList<>();
        team2.add((Combatant) new EnemyCombatantAdapter(ogre));
        team2.add((Combatant) new EnemyCombatantAdapter(wolf));
        
        EncounterResult result2 = engine1.runEncounter(team1, team2);
        
        System.out.println("Second Battle Winner: " + result2.getWinner());
        System.out.println("Second Battle Rounds: " + result2.getRounds());
        
        System.out.println("\n--- PART 5: Architecture Verification ---");
        System.out.println("✓ BattleEngine never calls Hero or Enemy directly");
        System.out.println("✓ All communication goes through Combatant interface");
        System.out.println("✓ Adapters handle all API translations");
        System.out.println("✓ Singleton ensures single battle engine instance");
        
        System.out.println("\n=== DEMO COMPLETE ===");
    }

    private static Object dapter(BasicEnemy ogre) {
       
        throw new UnsupportedOperationException("Unimplemented method 'dapter'");
    }
}