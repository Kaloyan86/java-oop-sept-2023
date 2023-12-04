package exam_prep.HandballSkeleton.src.main.java.handball.repositories;

import java.util.ArrayList;
import java.util.Collection;

import handball.entities.equipment.Equipment;

public class EquipmentRepository implements Repository {

    private Collection<Equipment> equipments;

    public EquipmentRepository() {
        this.equipments = new ArrayList<>();
    }

    @Override
    public void add(Equipment equipment) {
        this.equipments.add(equipment);
    }

    @Override
    public boolean remove(Equipment equipment) {
        return this.equipments.remove(equipment);
    }

    @Override
    public Equipment findByType(String type) {
        return this.equipments.stream()
                              .filter(equipment -> equipment.getClass().getSimpleName().equals(type))
                              .findFirst()
                              .orElse(null);
    }
}
