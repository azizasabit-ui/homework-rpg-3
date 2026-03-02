package com.narxoz.rpg.battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncounterResult {
    private String winner;
    private int rounds;
    private final List<String> battleLog = new ArrayList<>();

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void addLog(String line) {
        battleLog.add(line);
    }

    public String getWinner() {
        return winner;
    }

    public int getRounds() {
        return rounds;
    }

    public List<String> getBattleLog() {
        return Collections.unmodifiableList(battleLog);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== ENCOUNTER RESULT ===\n");
        sb.append("Winner: ").append(winner).append("\n");
        sb.append("Rounds: ").append(rounds).append("\n");
        sb.append("Battle Log:\n");
        for (String log : battleLog) {
            sb.append("  ").append(log).append("\n");
        }
        return sb.toString();
    }
}