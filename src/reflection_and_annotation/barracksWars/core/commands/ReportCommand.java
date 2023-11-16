package reflection_and_annotation.barracksWars.core.commands;

import reflection_and_annotation.barracksWars.interfaces.Repository;
import reflection_and_annotation.barracksWars.interfaces.UnitFactory;

public class ReportCommand extends Command {
    public ReportCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String output = this.getRepository().getStatistics();
        return output;
    }
}
