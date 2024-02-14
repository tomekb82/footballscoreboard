package footballscoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TeamTest {

    @Test
    void teamWithNoNameCouldNotStartAMatch() {
        //when
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Team.of(""));

        //then
        assertTrue(exception.getMessage().contains("Team with no name could not start a match"));
    }
}
