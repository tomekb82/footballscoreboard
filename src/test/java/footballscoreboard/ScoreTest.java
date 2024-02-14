package footballscoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreTest {

    @Test
    void shouldNotCreateMatchWithNegativeScore() {
        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Score.of(-1));

        //then
        assertTrue(exception.getMessage().contains("Score could not be negative"));
    }
}
