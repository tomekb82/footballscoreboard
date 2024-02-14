package footballscoreboard;

import java.util.ArrayList;
import java.util.List;

class Scoreboard {

    private List<Match> matches;

    public Scoreboard() {
        this.matches = new ArrayList<>();
    }

    void startMatch(Team homeTeam, Team guestTeam){

    }

    void updateScore(int matchIndex, Score score){

    }

    void finishMatch() {

    }

    MatchSummary getMatchSummary() {
        return null;
    }
}
