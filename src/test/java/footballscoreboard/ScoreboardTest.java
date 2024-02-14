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
        Exception exception = assertThrows(IllegalArgumentException.class,
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
                () -> scoreboard.updateScore(0, homeScore, guestScore));

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
    void shouldUpdateScoreManyTimes() {
        //given
        Team homes = Team.of("homes");
        Team guests = Team.of("guests");
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startMatch(homes, guests);
        scoreboard.updateScore(0, Score.of(1), Score.of(0));

        //when
        scoreboard.updateScore(0, Score.of(1), Score.of(3));

        assertEquals(1, scoreboard.getMatchSummary().matches().size());
        assertEquals(1, scoreboard.getMatchSummary().matches().get(0).getHomeScore().getValue());
        assertEquals(3, scoreboard.getMatchSummary().matches().get(0).getGuestScore().getValue());
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
        assertEquals(3, scoreboard.getMatchSummary().matches().get(0).calculateTotalScoreValue());
    }

    @Test
    void shouldFailToFinishMatchWhenMatchIndexIsNegative() {
        //given
        Scoreboard scoreboard = new Scoreboard();

        //when
        Exception exception = assertThrows(IllegalArgumentException.class,
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
        //and start all matches
        scoreboard.startMatch(Team.of( "Mexico"), Team.of("Canada"))
            .startMatch(Team.of( "Spain"), Team.of("Brazil"))
            .startMatch(Team.of( "Germany"), Team.of("France"))
            .startMatch(Team.of( "Uruguay"), Team.of("Italy"))
            .startMatch(Team.of( "Argentina"), Team.of("Australia"));

        //and update scores
        scoreboard
            .updateScore(0, Score.of(0), Score.of(5))
            .updateScore(1, Score.of(10), Score.of(2))
            .updateScore(2, Score.of(2), Score.of(2))
            .updateScore(3, Score.of(6), Score.of(6))
            .updateScore(4, Score.of(3), Score.of(1));

        //when
        MatchSummary matchSummary = scoreboard.getMatchSummary();

        assertEquals(5, matchSummary.matches().size());
        assertEquals("Uruguay 6 - Italy 6", matchSummary.matches().get(0).print());
        assertEquals("Spain 10 - Brazil 2", matchSummary.matches().get(1).print());
        assertEquals("Mexico 0 - Canada 5", matchSummary.matches().get(2).print());
        assertEquals("Argentina 3 - Australia 1", matchSummary.matches().get(3).print());
        assertEquals("Germany 2 - France 2", matchSummary.matches().get(4).print());

        //finally finish all matches
        scoreboard
            .finishMatch(4)
            .finishMatch(3)
            .finishMatch(2)
            .finishMatch(1)
            .finishMatch(0);

        assertEquals(0, scoreboard.numberOfOngoingMathes());
    }

}
