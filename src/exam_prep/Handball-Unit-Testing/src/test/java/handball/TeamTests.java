package exam_prep.Handball;

import org.junit.Assert;
import org.junit.Test;

public class TeamTests {

    @Test(expected = NullPointerException.class)
    public void test_Constructor_ShouldThrow_When_Name_isEmpty() {
        new Team("", 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Constructor_ShouldThrow_When_Position_Negative() {
        new Team("JAVA", -5);
    }

    @Test
    public void test_Constructor_ShouldCreate_Correct_Team() {
        Team team = new Team("JAVA", 5);

        Assert.assertEquals("JAVA", team.getName());
        Assert.assertEquals(5, team.getPosition());
        Assert.assertEquals(0, team.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName_ShouldThrow_When_Name_Whitespace() {
        new Team("    ", 5);
    }

    @Test(expected = NullPointerException.class)
    public void test_SetName_ShouldThrow_When_Name_Null() {
        new Team(null, 5);
    }

    @Test
    public void test_GetCount_On_Non_Empty_Team() {
        Team team = new Team("JAVA", 5);
        team.add(new exam_prep.Handball.HandballPlayer("Kaloyan"));
        team.add(new exam_prep.Handball.HandballPlayer("Peter"));
        team.add(new exam_prep.Handball.HandballPlayer("Ivan"));

        Assert.assertEquals(3, team.getCount());
    }

    @Test
    public void test_GetCount_On_Empty_Team() {
        Team team = new Team("JAVA", 5);

        Assert.assertEquals(0, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_When_Team_IsFull() {
        Team team = new Team("JAVA", 2);
        team.add(new exam_prep.Handball.HandballPlayer("Kaloyan"));
        team.add(new exam_prep.Handball.HandballPlayer("Peter"));
        team.add(new exam_prep.Handball.HandballPlayer("Ivan"));
    }

    @Test
    public void test_Add_Should_Add_When_Team_Is_NotFull() {
        Team team = new Team("JAVA", 3);
        team.add(new exam_prep.Handball.HandballPlayer("Kaloyan"));
        team.add(new exam_prep.Handball.HandballPlayer("Peter"));
        team.add(new exam_prep.Handball.HandballPlayer("Ivan"));

        Assert.assertEquals(3, team.getCount());
    }

    @Test
    public void test_Remove_Should_Remove_When_Player_Exists() {
        Team team = new Team("JAVA", 3);
        team.add(new exam_prep.Handball.HandballPlayer("Kaloyan"));
        team.add(new exam_prep.Handball.HandballPlayer("Peter"));
        team.add(new exam_prep.Handball.HandballPlayer("Ivan"));

        Assert.assertEquals(3, team.getCount());

        team.remove("Peter");

        Assert.assertEquals(2, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_Should_Throw_When_Player_Non_Exists() {
        Team team = new Team("JAVA", 3);
        team.add(new exam_prep.Handball.HandballPlayer("Kaloyan"));
        team.add(new exam_prep.Handball.HandballPlayer("Peter"));
        team.add(new exam_prep.Handball.HandballPlayer("Ivan"));

        team.remove("Alex");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_PlayerForAnotherTeam_Should_Throw_When_Player_Non_Exists() {
        Team team = new Team("JAVA", 3);
        team.add(new exam_prep.Handball.HandballPlayer("Kaloyan"));
        team.add(new exam_prep.Handball.HandballPlayer("Peter"));
        team.add(new exam_prep.Handball.HandballPlayer("Ivan"));

        team.playerForAnotherTeam("Alex");
    }

    @Test
    public void test_PlayerForAnotherTeam_Should_SetIsActive_ToFalse_When_Player_Exists() {
        Team team = new Team("JAVA", 3);
        team.add(new exam_prep.Handball.HandballPlayer("Kaloyan"));
        team.add(new exam_prep.Handball.HandballPlayer("Peter"));
        team.add(new exam_prep.Handball.HandballPlayer("Ivan"));

        exam_prep.Handball.HandballPlayer player = team.playerForAnotherTeam("Ivan");
        Assert.assertFalse(player.isActive());
    }

    @Test
    public void test_getStatistics_Should_Return_CorrectResult_When_Team_Is_Not_Empty() {
        Team team = new Team("JAVA", 3);
        team.add(new exam_prep.Handball.HandballPlayer("Kaloyan"));
        team.add(new exam_prep.Handball.HandballPlayer("Peter"));
        team.add(new exam_prep.Handball.HandballPlayer("Ivan"));

        String expectedResult = "The player Kaloyan, Peter, Ivan is in the team JAVA.";
        String actualResult = team.getStatistics();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void test_getStatistics_Should_Return_CorrectResult_When_Team_Is_Empty() {
        Team team = new Team("JAVA", 3);

        String expectedResult = "The player  is in the team JAVA.";
        String actualResult = team.getStatistics();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
