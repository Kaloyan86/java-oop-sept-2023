package exam_prep.HandballSkeleton.src.main.java.handball.entities.gameplay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import handball.entities.equipment.Equipment;
import handball.entities.team.Team;

import static handball.common.ExceptionMessages.GAMEPLAY_NAME_NULL_OR_EMPTY;

public abstract class BaseGameplay implements Gameplay {

    private String name;
    private int capacity;
    private Collection<Equipment> equipments;
    private Collection<Team> teams;

    public BaseGameplay(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        this.equipments = new ArrayList<>();
        this.teams = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(GAMEPLAY_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int allProtection() {
        return this.equipments.stream()
                              .mapToInt(Equipment::getProtection)
                              .sum();
    }

    @Override
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    @Override
    public void removeTeam(Team team) {
        this.teams.remove(team);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        this.equipments.add(equipment);
    }

    @Override
    public void teamsInGameplay() {
        this.teams.forEach(Team::play);
    }

    @Override
    public Collection<Team> getTeam() {
        return Collections.unmodifiableCollection(this.teams);
    }

    @Override
    public Collection<Equipment> getEquipments() {
        return Collections.unmodifiableCollection(this.equipments);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        //"{gameplayName} {gameplayType}
        sb.append(this.name).append(" ").append(this.getClass().getSimpleName());
        sb.append(System.lineSeparator());
        // Team: {teamName1} {teamName2} (…) / Team: none
        String team = this.teams.isEmpty() ? "Team: none" : "Team:";
        sb.append(team);
        this.teams.forEach(t -> sb.append(" ").append(t.getName()));
         sb.append(System.lineSeparator());
        // Equipment: {equipmentsCount}, Protection: {allProtection}"
        sb.append("Equipment: ")
          .append(this.equipments.size())
          .append(", ")
          .append("Protection: ")
          .append(allProtection());

        return sb.toString();
    }
}
