package com.narxoz.rpg.battle;

public class EncounterResult {

    private final String winner;
    private final int rounds;

    public EncounterResult(String winner, int rounds) {
        this.winner = winner;
        this.rounds = rounds;
    }

    public String getWinner() {
        return winner;
    }

    public int getRounds() {
        return rounds;
    }
}