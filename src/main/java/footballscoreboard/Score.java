package footballscoreboard;

class Score {
    private final int value;

    private Score(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Score could not be negative");
        }
        this.value = value;
    }

    static Score of(int value) {
        return new Score(value);
    }

    static Score zero(){
        return new Score(0);
    }

    int getValue() {
        return value;
    }
}
