package footballscoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {

    @Test
    void shouldStartAMatch() {
        //given
        Team homes = Team.of("homes");
        Team guests = Team.of("guests");
        Scoreboard scoreboard = new Scoreboard();

        //when
        scoreboard.startMatch(homes, guests);

        assertEquals(1, scoreboard.numberOfOngoingMathes());
    }

    @Test
    void shouldFailWhenProvidedNegativeIndexForAMatch() {
        //given
        Scoreboard scoreboard = new Scoreboard();
        Score homeScore = Score.zero();
        Score guestScore = Score.zero();

        //when
        Exception exception = assertThrows(IllegalStateException.class,
                () -> scoreboard.updateScore(-1, homeScore, guestScore));

        //then
        assertTrue(exception.getMessage().contains("Index of a match should be positive value"));
    }

    @Test
    void shouldFailWhenUpdateScoreOnNonStartedMatch() {
        //given
        Scoreboard scoreboard = new Scoreboard();
        Score homeScore = Score.zero();
        Score guestScore = Score.zero();

        //when
        Exception exception = assertThrows(IllegalStateException.class,
                () -> scoreboard.updateScore(1, homeScore, guestScore));

        //then
        assertTrue(exception.getMessage().contains("Could not update score when match is not started"));
    }

    @Test
    void shouldUpdateScore() {
        //given
        Team homes = Team.of("homes");
        Team guests = Team.of("guests");
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch(homes, guests);
        Score homeScore = Score.of(1);
        Score guestScore = Score.of(2);

        //when
        scoreboard.updateScore(0, homeScore, guestScore);

        assertEquals(1, scoreboard.getMatchSummary().matches().size());
        assertEquals(1, scoreboard.getMatchSummary().matches().get(0).getHomeScore().getValue());
        assertEquals(2, scoreboard.getMatchSummary().matches().get(0).getGuestScore().getValue());
    }

    @Test
    void shouldCalculateTotalScore() {
        //given
        Team homes = Team.of("homes");
        Team guests = Team.of("guests");
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch(homes, guests);
        Score homeScore = Score.of(1);
        Score guestScore = Score.of(2);

        //when
        scoreboard.updateScore(0, homeScore, guestScore);

        assertEquals(1, scoreboard.getMatchSummary().matches().size());;
        assertEquals(3, scoreboard.getMatchSummary().matches().get(0).calculateTotalScore().getValue());
    }

    @Test
    void shouldFailToFinishMatchWhenMatchIndexIsNegative() {
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        Exception exception = assertThrows(IllegalStateException.class,
                () -> scoreboard.finishMatch(-1));

        //then
        assertTrue(exception.getMessage().contains("Index of a match should be positive value"));
    }

    @Test
    void shouldFailToFinishMatchWhenIsNotOngoing() {
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        Exception exception = assertThrows(IllegalStateException.class,
                () -> scoreboard.finishMatch(1));

        //then
        assertTrue(exception.getMessage().contains("Could not finish a match when match is not started"));
    }

    @Test
    void shouldFinishMatch() {
        //given
        Team homes = Team.of("homes");
        Team guests = Team.of("guests");
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch(homes, guests);
        Score homeScore = Score.of(1);
        Score guestScore = Score.of(2);
        scoreboard.updateScore(0, homeScore, guestScore);

        //when
        scoreboard.finishMatch(0);

        assertEquals(0, scoreboard.getMatchSummary().matches().size());
    }

    @Test
    void shouldScoreBoardSummaryInRightOrder() {
        //given
        Scoreboard scoreboard = new Scoreboard();
        scoreboard
            .startMatch(Team.of( "Mexico"), Team.of("Canada"))
            .updateScore(0, Score.of(0), Score.of(5));
        scoreboard
            .startMatch(Team.of( "Spain"), Team.of("Brazil"))
            .updateScore(1, Score.of(10), Score.of(2));
        scoreboard
            .startMatch(Team.of( "Germany"), Team.of("France"))
            .updateScore(2, Score.of(2), Score.of(2));
        scoreboard
            .startMatch(Team.of( "Uruguay"), Team.of("Italy"))
            .updateScore(3, Score.of(6), Score.of(6));
        scoreboard
            .startMatch(Team.of( "Argentina"), Team.of("Australia"))
            .updateScore(4, Score.of(3), Score.of(1));

        //when
        MatchSummary matchSummary = scoreboard.getMatchSummary();

        assertEquals(5, matchSummary.matches().size());
        assertEquals(Match.of(Team.of("Uruguay"), Team.of("Italy")).withScore(Score.of(6), Score.of(6)),
                matchSummary.matches().get(0));
        assertEquals(Match.of(Team.of("Spain"), Team.of("Brazil")).withScore(Score.of(10), Score.of(2)),
                matchSummary.matches().get(1));
        assertEquals(Match.of(Team.of("Mexico"), Team.of("Canada")).withScore(Score.of(0), Score.of(5)),
                matchSummary.matches().get(2));
        assertEquals(Match.of(Team.of("Argentina"), Team.of("Australia")).withScore(Score.of(3), Score.of(1)),
                matchSummary.matches().get(3));
        assertEquals(Match.of(Team.of("Germany"), Team.of("France")).withScore(Score.of(2), Score.of(2)),
                matchSummary.matches().get(4));
    }

}
