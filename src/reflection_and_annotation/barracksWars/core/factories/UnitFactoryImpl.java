package reflection_and_annotation.barracksWars.core.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import reflection_and_annotation.barracksWars.interfaces.Unit;
import reflection_and_annotation.barracksWars.interfaces.UnitFactory;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "reflection_and_annotation.barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) {
        Unit newUnit = null;

        try {
            Class unitClass = Class.forName(UNITS_PACKAGE_NAME +  unitType);
            Constructor<Unit> unitConstr = unitClass.getConstructor();
            newUnit = unitConstr.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
           e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return newUnit;
    }
}
