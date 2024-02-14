package footballscoreboard;

public class Team {

    private String name;

    private Team (String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Team with no name could not start a match");
        }
        this.name = name;
    }

    static Team of(String name) {
        return new Team(name);
    }

    String name() {
        return name;
    }
}
