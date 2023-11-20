package robotService.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

import robotService.entities.robot.FemaleRobot;
import robotService.entities.robot.MaleRobot;
import robotService.entities.robot.Robot;
import robotService.entities.services.MainService;
import robotService.entities.services.SecondaryService;
import robotService.entities.services.Service;
import robotService.entities.supplements.MetalArmor;
import robotService.entities.supplements.PlasticArmor;
import robotService.entities.supplements.Supplement;
import robotService.repositories.Repository;
import robotService.repositories.SupplementRepository;

import static robotService.common.ConstantMessages.*;
import static robotService.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository supplements;
    private Collection<Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new ArrayList<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        switch (type) {
            case "MainService":
                service = new MainService(name);
                break;
            case "SecondaryService":
                service = new SecondaryService(name);
                break;
            default:
                throw new NullPointerException(INVALID_SERVICE_TYPE);
        }
        services.add(service);
        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {

        Supplement supplement;

        switch (type) {
            case "PlasticArmor":
                supplement = new PlasticArmor();
                break;
            case "MetalArmor":
                supplement = new MetalArmor();
                break;
            default:
                throw new IllegalArgumentException(INVALID_SUPPLEMENT_TYPE);
        }
        supplements.addSupplement(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = supplements.findFirst(supplementType);

        if (supplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        Service service = getService(serviceName);

        service.addSupplement(supplement);
        supplements.removeSupplement(supplement);

        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {

        Robot robot;

        switch (robotType) {
            case "MaleRobot":
                robot = new MaleRobot(robotName, robotKind, price);
                break;
            case "FemaleRobot":
                robot = new FemaleRobot(robotName, robotKind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }

        Service service = getService(serviceName);

        String serviceType = service.getClass().getSimpleName();
        String output;

        if (robotType.equals("MaleRobot") && serviceType.equals("MainService")) {
            output = String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
            service.addRobot(robot);
        } else if (robotType.equals("FemaleRobot") && serviceType.equals("SecondaryService")) {
            output = String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
            service.addRobot(robot);
        } else {
            output = UNSUITABLE_SERVICE;
        }
        return output;
    }

    @Override
    public String feedingRobot(String serviceName) {
        Service service = getService(serviceName);
        service.feeding();
        return String.format(FEEDING_ROBOT, service.getRobots().size());
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service service = getService(serviceName);

        double robotsSum = service.getRobots()
                                  .stream()
                                  .mapToDouble(Robot::getPrice)
                                  .sum();

        double suplementsSum = service.getSupplements()
                                      .stream()
                                      .mapToDouble(Supplement::getPrice)
                                      .sum();

        return String.format(VALUE_SERVICE, service.getName(), robotsSum + suplementsSum);
    }

    @Override
    public String getStatistics() {
        return this.services.stream()
                            .map(Service::getStatistics)
                            .collect(Collectors.joining(System.lineSeparator())).trim();
    }

    private Service getService(String serviceName) {
        return services.stream()
                       .filter(s -> s.getName().equals(serviceName))
                       .findFirst()
                       .get();
    }
}
