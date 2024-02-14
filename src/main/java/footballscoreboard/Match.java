package footballscoreboard;

import java.util.Objects;

class Match implements Comparable<Match>{
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

    int calculateTotalScoreValue() {
        return homeScore.getValue() + getGuestScore().getValue();
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

    @Override
    public int compareTo(Match o) {
        return Integer.compare(this.calculateTotalScoreValue(), o.calculateTotalScoreValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(homeTeam.name(), match.homeTeam.name())
                && Objects.equals(guestTeam.name(), match.guestTeam.name())
                && homeScore.getValue() == match.homeScore.getValue()
                && guestScore.getValue() == match.guestScore.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam.name(), guestTeam.name(), homeScore.getValue(), guestScore.getValue());
    }

    public String print() {
        return homeTeam.name() + " " + homeScore.getValue() + " - "
                + guestTeam.name() + " " + guestScore.getValue();
    }
}
