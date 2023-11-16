package reflection_and_annotation.barracksWars.core.commands;

import reflection_and_annotation.barracksWars.interfaces.Repository;
import reflection_and_annotation.barracksWars.interfaces.Unit;
import reflection_and_annotation.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class AddCommand extends Command {
    public AddCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        Unit unitToAdd = null;
        try {
            unitToAdd = this.getUnitFactory().createUnit(unitType);
        } catch (ExecutionControl.NotImplementedException e) {
            e.printStackTrace();
        }

        this.getRepository().addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }
}
