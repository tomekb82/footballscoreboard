package footballscoreboard;

import java.util.ArrayList;
import java.util.List;

class Scoreboard {

    private List<Match> matches;

    public Scoreboard() {
        this.matches = new ArrayList<>();
    }

    Scoreboard startMatch(Team homeTeam, Team guestTeam){

        return this;
    }

    Scoreboard updateScore(int matchIndex, Score homeScore, Score guestScore){
        return this;
    }

    Scoreboard finishMatch(int matchIndex) {
        return this;
    }

    int numberOfOngoingMathes() {
        return -1;
    }

    MatchSummary getMatchSummary() {
        return null;
    }
}
