package p01_Database;

import javax.naming.OperationNotSupportedException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    private static final Integer[] EXPECTED_ELEMENTS = {1, 2, 3, 4, 5};
    private static final int EXPECTED_SIZE = EXPECTED_ELEMENTS.length;
    private static final int EXPECTED_INDEX = EXPECTED_ELEMENTS.length - 1;
    private static final Integer EXPECTED_LAST_ELEMENT = EXPECTED_ELEMENTS[3];

    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(EXPECTED_ELEMENTS);
    }

    @Test
    public void test_Constructor_Should_Create_CorrectObject() throws OperationNotSupportedException {

        Integer[] actualElements = database.getElements();
        int actualSize = actualElements.length;
        int actualIndex = actualSize - 1;

        Assert.assertArrayEquals(EXPECTED_ELEMENTS, actualElements);
        assertEquals(EXPECTED_SIZE, actualSize);
        assertEquals(EXPECTED_INDEX, actualIndex);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_When_ElementsAreGreater_Than16() throws OperationNotSupportedException {
        Integer[] elements = new Integer[17];
        database = new Database(elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_When_ElementsAreZero() throws OperationNotSupportedException {
        Database database = new Database();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void test_Add_Null_ShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void test_Add_ShouldAdd_Element_OnTheLastPosition() throws OperationNotSupportedException {
        database.add(25);
        Integer[] elements = database.getElements();
        int actualSize = elements.length;

       // assertEquals("Invalid size",EXPECTED_SIZE, actualSize);
        assertEquals(Integer.valueOf(25), elements[actualSize - 1]);
    }

    @Test
    public void test_Add_Should_IncreaseSize_WhenAddElement() throws OperationNotSupportedException {
        database.add(25);
        Integer[] elements = database.getElements();
        int actualSize = elements.length;

        assertEquals(EXPECTED_SIZE + 1, actualSize);
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
        Integer[] elements = database.getElements();
        Integer actualLastElement = elements[elements.length - 1];

        assertEquals(EXPECTED_LAST_ELEMENT, actualLastElement);
    }

    @Test
    public void test_Remove_Should_DecreaseSize_When_RemoveElement() throws OperationNotSupportedException {
        database.remove();
        int actualSize = database.getElements().length;

        assertEquals(EXPECTED_SIZE - 1, actualSize);
    }
}
