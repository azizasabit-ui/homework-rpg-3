package com.narxoz.rpg.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random;
    private List<String> battleLog;

    private BattleEngine() {
        this.random = new Random();
        this.battleLog = new ArrayList<>();
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
        battleLog.clear();
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        battleLog.clear();
        battleLog.add("=== BATTLE START ===");
        battleLog.add("Team A: " + getTeamNames(teamA));
        battleLog.add("Team B: " + getTeamNames(teamB));
    
        List<Combatant> teamACopy = new ArrayList<>(teamA);
        List<Combatant> teamBCopy = new ArrayList<>(teamB);
        
        int round = 1;
        String winner = null;
        
        while (winner == null) {
            battleLog.add("\n--- ROUND " + round + " ---");
            
            battleLog.add("Team A attacks:");
            performTeamAttack(teamACopy, teamBCopy);
            
            if (isTeamDefeated(teamBCopy)) {
                winner = "Team A";
                break;
            }
            
            battleLog.add("\nTeam B attacks:");
            performTeamAttack(teamBCopy, teamACopy);
            
            if (isTeamDefeated(teamACopy)) {
                winner = "Team B";
                break;
            }
            
            removeDeadCombatants(teamACopy);
            removeDeadCombatants(teamBCopy);
            
            round++;
        }
        
        battleLog.add("\n=== BATTLE END ===");
        battleLog.add("Winner: " + winner);
        
        EncounterResult result = new EncounterResult();
        result.setWinner(winner);
        result.setRounds(round);
        for (String log : battleLog) {
            result.addLog(log);
        }
        
        return result;
    }
    
    private void performTeamAttack(List<Combatant> attackers, List<Combatant> defenders) {
        for (Combatant attacker : attackers) {
            if (!attacker.isAlive()) continue;
            
            List<Combatant> livingDefenders = getLivingCombatants(defenders);
            if (livingDefenders.isEmpty()) break;
            
            Combatant defender = livingDefenders.get(random.nextInt(livingDefenders.size()));
            int damage = attacker.getAttackPower();
            
            battleLog.add(attacker.getName() + " attacks " + defender.getName() + 
                         " for " + damage + " damage!");
            defender.takeDamage(damage);
            
            if (!defender.isAlive()) {
                battleLog.add(defender.getName() + " has been defeated!");
            }
        }
    }
    
    private List<Combatant> getLivingCombatants(List<Combatant> team) {
        List<Combatant> living = new ArrayList<>();
        for (Combatant c : team) {
            if (c.isAlive()) {
                living.add(c);
            }
        }
        return living;
    }
    
    private boolean isTeamDefeated(List<Combatant> team) {
        return getLivingCombatants(team).isEmpty();
    }
    
    private void removeDeadCombatants(List<Combatant> team) {
        team.removeIf(c -> !c.isAlive());
    }
    
    private String getTeamNames(List<Combatant> team) {
        StringBuilder sb = new StringBuilder();
        for (Combatant c : team) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(c.getName());
        }
        return sb.toString();
    }
}