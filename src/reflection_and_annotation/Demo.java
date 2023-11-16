package reflection_and_annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import inheritance.person.Person;

public class Demo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        // access the class
        Class<Person> personClass = Person.class;

        // access the fields
        Field[] allFields = personClass.getDeclaredFields(); // return all fields (public, protected, private)
        Field[] publicFields = personClass.getFields(); // return only public fields

        for (Field field : allFields) {
            System.out.println(field.getName());
            System.out.println(Modifier.toString(field.getModifiers()));
            System.out.println(Modifier.isPublic(field.getModifiers()));// check whether a modifier is public
            System.out.println(Modifier.isProtected(field.getModifiers()));// check whether a modifier is protected
            System.out.println(Modifier.isPrivate(field.getModifiers()));// check whether a modifier is private
        }

        // access the constructor
        Constructor<Person> publicConstructor = personClass.getDeclaredConstructor(String.class, int.class);
        Constructor<Person> privateConstructor = personClass.getDeclaredConstructor();
        privateConstructor.setAccessible(true);

        Person peter = publicConstructor.newInstance("Peter", 20); // new Person("Peter", 20);
        Person privatePerson = privateConstructor.newInstance(); // new Person();

        System.out.println(peter.getName());
        System.out.println(privatePerson.getName());

        // access the methods
        Method[] declaredMethods = personClass.getDeclaredMethods();
        Method method1 = declaredMethods[0];
        System.out.println(method1.invoke(peter));
        Method privateMethod = declaredMethods[1];
        privateMethod.setAccessible(true);
        privateMethod.invoke(privatePerson);

        System.out.println();
        System.out.println("--------Change private field value------");
        // Change private field value
        Field field = allFields[0];
        Field field2 = allFields[1];
        System.out.printf("Person is %s %s\n", privatePerson.getName(), privatePerson.lastName);
        field.setAccessible(true);
        field.set(privatePerson, "Kaloyan");
        field2.set(privatePerson, "Georgiev");
        System.out.printf("Person is %s %s\n", privatePerson.getName(), privatePerson.lastName);

        System.out.println();
    }
}
