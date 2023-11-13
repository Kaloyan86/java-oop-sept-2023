package robotService.entities.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import robotService.common.ExceptionMessages;
import robotService.entities.robot.Robot;
import robotService.entities.supplements.Supplement;

import static robotService.common.ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT;
import static robotService.common.ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY;

public abstract class BaseService implements Service {

    private String name;
    private int capacity;
    private List<Supplement> supplements;
    private List<Robot> robots;

    public BaseService(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public List<Robot> getRobots() {
        return Collections.unmodifiableList(this.robots);
    }

    @Override
    public List<Supplement> getSupplements() {
        return Collections.unmodifiableList(this.supplements);
    }

    @Override

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s:\n", this.name, this.getClass().getSimpleName()));
        sb.append("Robots: ");

        if (this.robots.isEmpty()) {
            sb.append("none");
        } else {
            String robotNames = this.robots.stream()
                                           .map(Robot::getName)
                                           .collect(Collectors.joining(" "));
            sb.append(robotNames);
        }
        sb.append(System.lineSeparator());

        sb.append(String.format("Supplements: %d Hardness: %d\n", this.supplements.size(), sumHardness()));
        return sb.toString();
    }

    @Override
    public void feeding() {
        this.robots.forEach(Robot::eating);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void removeRobot(Robot robot) {
        this.robots.remove(robot);
    }

    @Override
    public void addRobot(Robot robot) {
        if (this.robots.size() >= this.capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
        this.robots.add(robot);
    }

    @Override
    public int sumHardness() {
        return this.supplements.stream().mapToInt(Supplement::getHardness).sum();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullPointerException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
