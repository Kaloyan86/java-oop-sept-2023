package exam_prep.HandballSkeleton.src.main.java.handball.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import handball.entities.equipment.ElbowPad;
import handball.entities.equipment.Equipment;
import handball.entities.equipment.Kneepad;
import handball.entities.gameplay.Gameplay;
import handball.entities.gameplay.Indoor;
import handball.entities.gameplay.Outdoor;
import handball.entities.team.Bulgaria;
import handball.entities.team.Germany;
import handball.entities.team.Team;
import handball.repositories.EquipmentRepository;
import handball.repositories.Repository;

import static handball.common.ConstantMessages.*;
import static handball.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository equipment;
    private Collection<Gameplay> gameplays;

    public ControllerImpl() {
        this.equipment = new EquipmentRepository();
        this.gameplays = new ArrayList<>();
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        // if (!"Outdoor".equals(gameplayType) && !"Indoor".equals(gameplayType)) {
        if (!("Outdoor".equals(gameplayType) || "Indoor".equals(gameplayType))) {
            throw new NullPointerException(INVALID_GAMEPLAY_TYPE);
        }

        Gameplay gameplay = null;

        switch (gameplayType) {
            case "Outdoor":
                gameplay = new Outdoor(gameplayName);
                break;
            case "Indoor":
                gameplay = new Indoor(gameplayName);
                break;
        }

        this.gameplays.add(gameplay);
        return String.format(SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
    }

    @Override
    public String addEquipment(String equipmentType) {
        if (!"Kneepad".equals(equipmentType) && !"ElbowPad".equals(equipmentType)) {
            throw new IllegalArgumentException(INVALID_EQUIPMENT_TYPE);
        }
        Equipment equipment = null;
        switch (equipmentType) {
            case "Kneepad":
                equipment = new Kneepad();
                break;
            case "ElbowPad":
                equipment = new ElbowPad();
                break;
        }
        this.equipment.add(equipment);
        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_TYPE, equipmentType);
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        Equipment equipment = this.equipment.findByType(equipmentType);
        if (equipment == null) {
            throw new IllegalArgumentException(NO_EQUIPMENT_FOUND);
        }
        Gameplay gameplay = this.gameplays.stream()
                                          .filter(game -> game.getName().equals(gameplayName))
                                          .findFirst()
                                          .orElse(null);

        gameplay.addEquipment(equipment);

        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY, equipmentType, gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {

        if (!"Bulgaria".equals(teamType) && !"Germany".equals(teamType)) {
            throw new IllegalArgumentException(INVALID_TEAM_TYPE);
        }

        Team team = null;

        Gameplay gameplay = this.gameplays.stream()
                                          .filter(game -> game.getName().equals(gameplayName))
                                          .findFirst()
                                          .orElse(null);

        switch (teamType) {
            case "Bulgaria":
                team = new Bulgaria(teamName, country, advantage);

                if (gameplay.getClass().getSimpleName().equals("Outdoor")) {
                    gameplay.addTeam(team);
                } else {
                    return GAMEPLAY_NOT_SUITABLE;
                }

                break;
            case "Germany":
                team = new Germany(teamName, country, advantage);

                if (gameplay.getClass().getSimpleName().equals("Indoor")) {
                    gameplay.addTeam(team);
                } else {
                    return GAMEPLAY_NOT_SUITABLE;
                }
                break;
        }

        return String.format(SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
    }

    @Override
    public String playInGameplay(String gameplayName) {

        Gameplay gameplay = this.gameplays.stream()
                                          .filter(game -> game.getName().equals(gameplayName))
                                          .findFirst()
                                          .orElse(null);

        gameplay.getTeam().forEach(Team::play);

        return String.format(TEAMS_PLAYED, gameplay.getTeam().size());
    }

    @Override
    public String percentAdvantage(String gameplayName) {

        Gameplay gameplay = this.gameplays.stream()
                                          .filter(game -> game.getName().equals(gameplayName))
                                          .findFirst()
                                          .orElse(null);

        int advantageSum = gameplay.getTeam()
                                   .stream()
                                   .mapToInt(Team::getAdvantage)
                                   .sum();

        return String.format(ADVANTAGE_GAMEPLAY, gameplayName, advantageSum);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        gameplays.forEach(gameplay -> sb.append(gameplay.toString())
                                        .append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
