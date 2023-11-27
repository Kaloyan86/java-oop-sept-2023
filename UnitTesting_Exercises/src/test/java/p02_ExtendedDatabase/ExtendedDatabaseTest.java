package p02_ExtendedDatabase;

import javax.naming.OperationNotSupportedException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ExtendedDatabaseTest {

    private static final Person PERSON1 = Mockito.mock(Person.class);
    private static final Person PERSON2 = Mockito.mock(Person.class);
    private static final Person PERSON3 = Mockito.mock(Person.class);
    private static final Person[] EXPECTED_PEOPLE = {PERSON1, PERSON2, PERSON3};
    private static final Integer EXPECTED_SIZE = EXPECTED_PEOPLE.length;
    private static final Person EXPECTED_LAST_ELEMENT = EXPECTED_PEOPLE[1];

    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {

        database = new Database(EXPECTED_PEOPLE);
    }

    @Test
    public void test_Constructor_Should_Create_CorrectObject() {

        Person[] actualPeople = database.getElements();
        Integer actualSize = actualPeople.length;
        assertArrayEquals("Arrays are not the same!", EXPECTED_PEOPLE, actualPeople);
        assertEquals("Elements count is incorrect", EXPECTED_SIZE, actualSize);
        assertEquals("Index is incorrect", EXPECTED_SIZE - 1, actualSize - 1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_When_ElementsAreGreater_Than16() throws OperationNotSupportedException {
        Person[] elements = new Person[17];
        database = new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_When_ElementsAreZero() throws OperationNotSupportedException {
        database = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Add_Null_ShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_Add_ShouldAdd_Element_OnTheLastPosition() throws OperationNotSupportedException {
        Person person = Mockito.mock(Person.class);

        //        Mockito.when(person.getUsername()).thenReturn("Kaloyan");
        //        Mockito.when(PERSON1.getUsername()).thenReturn("Kaloyan");

        database.add(person);
        Person[] people = database.getElements();
        int actualSize = people.length;

        assertEquals("Invalid size", EXPECTED_SIZE + 1, actualSize);
        assertEquals(person, people[actualSize - 1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Remove_ShouldThrow_When_DatabaseEmpty() throws OperationNotSupportedException {
        for (int i = 0; i <= EXPECTED_SIZE; i++) {
            database.remove();
        }
    }

    @Test
    public void test_Remove_ShouldRemove_Sucessfuly() throws OperationNotSupportedException {
        database.remove();
        Person[] people = database.getElements();
        Person actualLastElement = people[people.length - 1];

        assertEquals("Invalid size", EXPECTED_SIZE - 1, people.length);
        assertEquals(EXPECTED_LAST_ELEMENT, actualLastElement);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByUsername_ShouldThrow_When_UserNotPresent() throws OperationNotSupportedException {
        database.findByUsername("Kaloyan");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindByUsername_ShouldThrow_When_UsernameParamIsNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test
    public void test_FindByUsername_ShouldReturn_CorrectPerson() throws OperationNotSupportedException {
        Mockito.when(PERSON1.getUsername()).thenReturn("Kaloyan");
        Person actualPerson = database.findByUsername("Kaloyan");

        assertEquals("Invalid username!", PERSON1.getUsername(), actualPerson.getUsername());
        assertEquals("Invalid id!", PERSON1.getId(), actualPerson.getId());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindById_ShouldThrow_When_NoUserPresent() throws OperationNotSupportedException {
        database.findById(5);
    }

    @Test
    public void test_FindById_ShouldReturn_CorrectPerson() throws OperationNotSupportedException {
        Mockito.when(PERSON1.getId()).thenReturn(5);
        Person actualPerson = database.findById(5);

        assertEquals("Invalid id!", PERSON1.getId(), actualPerson.getId());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_FindById_ShouldThrow_When_Id_Duplicated() throws OperationNotSupportedException {
        database.findById(0);
    }
}
