package com.narxoz.rpg.battle;

import com.narxoz.rpg.adapter.Combatant;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BattleEngine {

    private static BattleEngine instance;   
    private Random random;

    private BattleEngine() {
        this.random = new Random();
    }

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public void reset() {
        instance = null;
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {

        System.out.println("=== BATTLE START ===");

        int round = 1;

        while (!teamA.isEmpty() && !teamB.isEmpty()) {

            System.out.println("\n--- Round " + round + " ---");

            attackPhase(teamA, teamB);
            attackPhase(teamB, teamA);

            round++;
        }

        String winner = teamA.isEmpty() ? "Team B" : "Team A";

        System.out.println("\n=== BATTLE ENDED ===");
        System.out.println("Winner: " + winner);

        return new EncounterResult(winner, round - 1);
    }

    private void attackPhase(List<Combatant> attackers, List<Combatant> defenders) {

        Iterator<Combatant> attackerIterator = attackers.iterator();

        while (attackerIterator.hasNext() && !defenders.isEmpty()) {

            Combatant attacker = attackerIterator.next();
            Combatant defender = defenders.get(random.nextInt(defenders.size()));

            if (!attacker.isAlive()) continue;

            int damage = attacker.getAttackPower();

            System.out.println(attacker.getName() + " attacks " +
                    defender.getName() + " for " + damage);

            defender.takeDamage(damage);

            if (!defender.isAlive()) {
                System.out.println(defender.getName() + " has died!");
                defenders.remove(defender);
            }
        }
    }
}