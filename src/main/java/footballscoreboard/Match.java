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
        if (homeTeam == null || guestTeam == null) {
            throw new IllegalStateException("Two teams are required to play a match");
        }
        return new Match(homeTeam, guestTeam);
    }

    Match withScore(Score homeScore, Score guestScore){
        this.homeScore = homeScore;
        this.guestScore = guestScore;

        return this;
    }

    Score calculateTotalScore() {
        return Score.of(homeScore.getValue() + getGuestScore().getValue());
    }

    Score getHomeScore() {
        return homeScore;
    }

    Score getGuestScore() {
        return guestScore;
    }

    Team getHomeTeam() {
        return homeTeam;
    }

    Team getGuestTeam() {
        return guestTeam;
    }

}
