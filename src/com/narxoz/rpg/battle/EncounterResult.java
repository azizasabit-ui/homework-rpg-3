package com.narxoz.rpg.battle;

<<<<<<< HEAD
public class EncounterResult {

    private final String winner;
    private final int rounds;

    public EncounterResult(String winner, int rounds) {
        this.winner = winner;
        this.rounds = rounds;
    }

=======
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

>>>>>>> 016c9dc0046ff3c12de1cc5c4e9e7de99d9e1c43
    public String getWinner() {
        return winner;
    }

    public int getRounds() {
        return rounds;
    }
<<<<<<< HEAD
}
=======

    public List<String> getBattleLog() {
        return Collections.unmodifiableList(battleLog);
    }
}
>>>>>>> 016c9dc0046ff3c12de1cc5c4e9e7de99d9e1c43
