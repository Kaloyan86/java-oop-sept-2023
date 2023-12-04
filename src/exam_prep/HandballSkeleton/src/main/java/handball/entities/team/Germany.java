package exam_prep.HandballSkeleton.src.main.java.handball.entities.team;

public class Germany extends BaseTeam {

    private static final int INCREASE_ADVANTAGE = 145;

    public Germany(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        setAdvantage(getAdvantage() + INCREASE_ADVANTAGE);
    }
}
