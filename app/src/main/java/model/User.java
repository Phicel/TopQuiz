package model;

public class User {
    private String mFirstName;
    public int Score;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public User() { // par défaut
        this.mFirstName = "Joueur";
        this.Score = 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstName='" + mFirstName + '\'' +
                '}';
    }
}