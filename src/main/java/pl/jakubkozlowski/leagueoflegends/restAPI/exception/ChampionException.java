package pl.jakubkozlowski.leagueoflegends.restAPI.exception;

public class ChampionException extends Exception {
    public ChampionException(String message) {
        super(message);
    }

    public static ChampionException doesNotExist() {
        return new ChampionException("Champion does not exist");
    }

    public static ChampionException wrongData() {
        return new ChampionException("Champion data is not correct");
    }

    public static ChampionException isNoLongerAvailable() {
        return new ChampionException("Champion is no longer available for edit");
    }
}
