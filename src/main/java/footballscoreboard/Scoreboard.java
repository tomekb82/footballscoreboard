package footballscoreboard;

import java.util.*;

public class Scoreboard {

    private List<Match> matches;

    public Scoreboard() {
        this.matches = new ArrayList<>();
    }

    public Scoreboard startMatch(Team homeTeam, Team guestTeam){
        Match newMatch = Match.of(homeTeam, guestTeam);
        matches.add(newMatch);
        return this;
    }

    public Scoreboard updateScore(int matchIndex, Score homeScore, Score guestScore){
        if (matchIndex < 0) {
            throw new IllegalArgumentException("Index of a match should be positive value");
        }
        if (matches.isEmpty() || matchIndex + 1 > numberOfOngoingMathes()) {
            throw new IllegalStateException("Could not update score when match is not started");
        }
        return Optional.ofNullable(matches.get(matchIndex))
                .map(match -> match.withScore(homeScore, guestScore))
                .map(match -> this)
                .orElseThrow(() -> new IllegalStateException("Could not update score when match is not started"));
    }

    public Scoreboard finishMatch(int matchIndex) {
        if (matchIndex < 0) {
            throw new IllegalArgumentException("Index of a match should be positive value");
        }
        if (matches == null || matchIndex + 1 > numberOfOngoingMathes()) {
            throw new IllegalStateException("Could not finish a match when match is not started");
        }
        matches.remove(matchIndex);
        return this;
    }

    public int numberOfOngoingMathes() {
        return matches.size();
    }

    public MatchSummary getMatchSummary() {
        Collections.sort(matches, Comparator.comparingInt(Match::calculateTotalScoreValue)
                .thenComparing(Collections.reverseOrder()));
        Collections.reverse(matches);
        return new MatchSummary(matches);
    }
}
