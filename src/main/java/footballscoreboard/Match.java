package footballscoreboard;

class Match {
    private final Team homeTeam;
    private final Team guestTeam;
    private Score homeScore;
    private Score guestScore;

    private Match(Team homeTeam, Team guestTeam) {
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.homeScore = Score.zero();
        this.guestScore = Score.zero();
    }

    static Match of(Team homeTeam, Team guestTeam) {
        return new Match(homeTeam, guestTeam);
    }

    void updateScore(Score homeScore, Score guestScore){

    }

    int calculateTotalScore() {
        return -1;
    }

    public Score getHomeScore() {
        return homeScore;
    }

    public Score getGuestScore() {
        return guestScore;
    }

}
