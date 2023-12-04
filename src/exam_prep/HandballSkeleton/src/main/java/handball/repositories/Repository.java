package exam_prep.HandballSkeleton.src.main.java.handball.repositories;

import handball.entities.equipment.Equipment;

public interface Repository {
    void add(Equipment equipment);
    boolean remove(Equipment equipment);
    Equipment findByType(String type);
}
