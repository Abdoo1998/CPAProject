package GUI;


import org.junit.Assert;
import org.junit.Test;

public class GUITests {

    @Test
    public void generateStringTest1() {
        Assert.assertTrue("Complete Start time field.".equals(CPANewTaskGUI.
                generateCompleteString(false, true, false)));
    }

    @Test
    public void generateStringTest2() {

        Assert.assertTrue("Complete Task name field.".equals(CPANewTaskGUI.
                generateCompleteString(true, false, false)));
    }

    @Test
    public void generateStringTest3() {

        Assert.assertTrue("Complete Duration field.".equals(CPANewTaskGUI.
                generateCompleteString(false, false, true)));
    }

    @Test
    public void generateStringTest4() {

        Assert.assertTrue("Complete Task name and Start time fields.".equals(CPANewTaskGUI.
                generateCompleteString(true, true, false)));
    }

    @Test
    public void generateStringTest5() {

        Assert.assertTrue("Complete Start time and Duration fields.".equals(CPANewTaskGUI.
                generateCompleteString(false, true, true)));
    }

    @Test
    public void generateStringTest6() {
        Assert.assertTrue("Complete Task name, Start time and Duration fields.".equals(CPANewTaskGUI.
                generateCompleteString(true, true, true)));
    }
}
