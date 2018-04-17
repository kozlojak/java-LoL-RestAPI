package pl.jakubkozlowski.learning.firststeps.exception;

public class ChampionNotExistException extends ChampionException {

    public ChampionNotExistException() {
        super("Champion does not exist.");
    }

}
