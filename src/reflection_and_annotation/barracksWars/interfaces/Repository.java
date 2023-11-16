package reflection_and_annotation.barracksWars.interfaces;

public interface Repository {

	void addUnit(Unit unit);

	String getStatistics();

	void removeUnit(String unitType);
}
