package robots;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTests {


    @Test(expected = IllegalArgumentException.class)
    public void test_Constructor_ShouldThrow_When_InvalidCapacity() {
        new Service("Service1", -25);
    }

    @Test(expected = NullPointerException.class)
    public void test_Constructor_ShouldThrow_When_EmptyName() {
        new Service("", 25);
    }

    @Test(expected = NullPointerException.class)
    public void test_Constructor_ShouldThrow_When_NameNull() {
        new Service(null, 25);
    }

    @Test
    public void test_Constructor_Should_Create_Correct_Object() {
        Service service = new Service("Service1", 25);

        assertEquals("Incorrect service name!", "Service1", service.getName());
        assertEquals("Incorrect service capacity!", 25, service.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_When_NoCapacity() {
        Service service = new Service("Service1", 0);
        Robot robot = new Robot("R2D2");
        service.add(robot);
    }

    @Test
    public void test_Add_Should_Add_Sucessfuly() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.add(robot);

        assertEquals(1, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_ShouldThrow_When_NoSuchRobot() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.remove(robot.getName());
    }

    @Test
    public void test_Remove_Should_Remove_Sucessfuly() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.add(robot);

        service.remove("R2D2");

        assertEquals(0, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ForSale_ShouldThrow_When_NoSuchRobot() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.forSale(robot.getName());
    }

    @Test
    public void test_ForSale_Should_Set_Robot_SetReadyForSale_ToFalse() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.add(robot);
        Robot actualRobot = service.forSale(robot.getName());

        assertFalse(actualRobot.isReadyForSale());
    }

    @Test
    public void test_Report_Should_Return_Correct_Result() {
        Service service = new Service("Service1", 5);
        Robot robot1 = new Robot("R2D2");
        Robot robot2 = new Robot("ROBOT2");
        Robot robot3 = new Robot("ROBOT3");
        service.add(robot1);
        service.add(robot2);
        service.add(robot3);

        String actualReport = service.report();

        assertEquals("The robot R2D2, ROBOT2, ROBOT3 is in the service Service1!", actualReport);
    }
}
