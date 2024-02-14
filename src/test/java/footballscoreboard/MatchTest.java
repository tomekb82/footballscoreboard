package footballscoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void twoTeamsAreRequiredToStartAMatch() {
        //given
        Team homes = Team.of("homes");
        Team missingGuests = null;

        //when
        Exception exception = assertThrows(IllegalStateException.class,
                () -> Match.of(homes, missingGuests));

        //then
        assertTrue(exception.getMessage().contains("Two teams are required to play a match"));
    }

    @Test
    void teamWithNoNameCouldNotStartAMatch() {
        //given
        Team homes = Team.of("homes");
        Team guestWithoutName = Team.of("");

        //when
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> Match.of(homes, guestWithoutName));

        //then
        assertTrue(exception.getMessage().contains("Team with no name could not start a match"));
    }

    @Test
    void shouldUpdateScore() {
        //given
        Match match = Match.of(Team.of("homes"), Team.of("guests"));
        Score homeScore = Score.of(1);
        Score guestScore = Score.of(2);

        //when
        match.updateScore(homeScore, guestScore);

        //then
        assertEquals(1, match.getHomeScore().getValue());
        assertEquals(2, match.getGuestScore().getValue());
    }

    @Test
    void shouldCalculateTotalScore() {
        //given
        Match match = Match.of(Team.of("homes"), Team.of("guests"));
        Score homeScore = Score.of(1);
        Score guestScore = Score.of(2);
        match.updateScore(homeScore, guestScore);

        //when
        Score totalScore = match.calculateTotalScore();

        //then
        assertEquals(3, totalScore.getValue());
    }
}
