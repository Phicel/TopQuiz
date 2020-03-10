package model;

public class Score {
    private int mScore;
    private User mUser;

    // score en fonction de l'utilisateur
    public Score(int score, User user) {
        mScore = score;
        mUser = user;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}