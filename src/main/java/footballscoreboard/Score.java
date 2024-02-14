package footballscoreboard;

class Score {
    private final int score;

    private Score(int score) {
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
