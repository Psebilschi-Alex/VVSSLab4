package WBT;

import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.repository.IntrebariRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import static junit.framework.TestCase.assertEquals;

public class NewQuizTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private IntrebariRepository repo;
    private AppController ctr;

    @Before
    public void setUp() throws Exception {
        File f = new File("teste.txt");
        f.delete();
        repo = new IntrebariRepository("teste.txt");
        ctr = new AppController(repo);
    }

    @Test
    public void laborator3_TC01() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException {
        thrown.expect(NotAbleToCreateTestException.class);
        thrown.expectMessage("Nu exista suficiente" +
                " intrebari pentru crearea unui test!(5)");
        ctr.createNewTest();
    }
    @Test
    public void laborator3_TC02() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException {
        thrown.expect(NotAbleToCreateTestException.class);
        thrown.expectMessage("Nu exista suficiente domenii pentru crearea unui test!(5)");
        ctr.addNewIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "domeniul");
        ctr.addNewIntrebare("enunt1", "varianta1", "varianta2", "varianta3", 2, "domeniul");
        ctr.addNewIntrebare("enunt2", "varianta1", "varianta2", "varianta3", 2, "domeniul");
        ctr.addNewIntrebare("enunt3", "varianta1", "varianta2", "varianta3", 2, "domeniul");
        ctr.addNewIntrebare("enunt4", "varianta1", "varianta2", "varianta3", 2, "domeniul");
        ctr.createNewTest();
    }
    @Test
    public void laborator3_TC03() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException {
        ctr.addNewIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "domeniul");
        ctr.addNewIntrebare("enunt1", "varianta1", "varianta2", "varianta3", 2, "domeniu1");
        ctr.addNewIntrebare("enunt2", "varianta1", "varianta2", "varianta3", 2, "domeniul2");
        ctr.addNewIntrebare("enunt3", "varianta1", "varianta2", "varianta3", 2, "domeniul3");
        ctr.addNewIntrebare("enunt4", "varianta1", "varianta2", "varianta3", 2, "domeniul4");
        assertEquals(5,ctr.createNewTest().size());
    }

    @Test
    public void laborator3_TC04() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException {
        ctr.addNewIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "domeniul");
        ctr.addNewIntrebare("enunt1", "varianta1", "varianta2", "varianta3", 2, "domeniu1");
        ctr.addNewIntrebare("enunt2", "varianta1", "varianta2", "varianta3", 2, "domeniul1");
        ctr.addNewIntrebare("enunt3", "varianta1", "varianta2", "varianta3", 2, "domeniul2");
        ctr.addNewIntrebare("enunt4", "varianta1", "varianta2", "varianta3", 2, "domeniul3");
        ctr.addNewIntrebare("enunt5", "varianta1", "varianta2", "varianta3", 2, "domeniul4");
        assertEquals(5,ctr.createNewTest().size());
    }
    @Test
    public void laborator3_TC05() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException {
        ctr.addNewIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "domeniu1");
        ctr.addNewIntrebare("enunt1", "varianta1", "varianta2", "varianta3", 2, "domeniu1");
        ctr.addNewIntrebare("enunt2", "varianta1", "varianta2", "varianta3", 2, "domeniul1");
        ctr.addNewIntrebare("enunt3", "varianta1", "varianta2", "varianta3", 2, "domeniul1");
        ctr.addNewIntrebare("enunt4", "varianta1", "varianta2", "varianta3", 2, "domeniul2");
        ctr.addNewIntrebare("enunt5", "varianta1", "varianta2", "varianta3", 2, "domeniul3");
        ctr.addNewIntrebare("enunt6", "varianta1", "varianta2", "varianta3", 2, "domeniul4");
        ctr.addNewIntrebare("enunt7", "varianta1", "varianta2", "varianta3", 2, "domeniul4");
        assertEquals(5,ctr.createNewTest().size());
    }
}
