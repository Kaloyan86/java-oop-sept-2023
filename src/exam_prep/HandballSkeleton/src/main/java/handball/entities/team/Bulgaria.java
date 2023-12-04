package exam_prep.HandballSkeleton.src.main.java.handball.entities.team;

public class Bulgaria extends BaseTeam {

    private static final int INCREASE_ADVANTAGE = 115;

    public Bulgaria(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        setAdvantage(getAdvantage() + INCREASE_ADVANTAGE);
    }
}
