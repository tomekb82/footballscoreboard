package footballscoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void shouldUpdateScore() {
        //given
        Match match = Match.of(new Team("homes"), new Team("guests"));
        Score homeScore = Score.of(1);
        Score guestScore = Score.of(2);

        //when
        match.updateScore(homeScore, guestScore);

        //then
        assertEquals(1, match.getHomeScore().getScore());
        assertEquals(2, match.getGuestScore().getScore());
    }

    @Test
    void shouldCalculateTotalScore() {
        //given
        Match match = Match.of(new Team("homes"), new Team("guests"));
        Score homeScore = Score.of(1);
        Score guestScore = Score.of(2);
        match.updateScore(homeScore, guestScore);

        //when
        Score totalScore = match.calculateTotalScore();

        //then
        assertEquals(3, totalScore.getScore());
    }
}
