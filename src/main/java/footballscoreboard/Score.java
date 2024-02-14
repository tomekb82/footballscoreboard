package footballscoreboard;

public class Score {
    private final int value;

    private Score(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Score could not be negative");
        }
        this.value = value;
    }

    public static Score of(int value) {
        return new Score(value);
    }

    public static Score zero(){
        return new Score(0);
    }

    public int getValue() {
        return value;
    }
}
