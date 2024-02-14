package footballscoreboard;

class Score {
    private final int score;

    private Score(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score could not be negative");
        }
        this.score = score;
    }

    static Score of(int score) {
        return new Score(score);
    }

    static Score zero(){
        return new Score(0);
    }

    public int getScore() {
        return score;
    }
}
