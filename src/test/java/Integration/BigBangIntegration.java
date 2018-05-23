package Integration;

import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Statistica;
import evaluator.repository.IntrebariRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class BigBangIntegration {
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
    public void AddIntrebare() throws DuplicateIntrebareException, InputValidationFailedException {

        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "domeniu");
        assertTrue("should be true, it is:" + rez, rez);
    }

    @Test
    public void CreateQuiz() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException {
        ctr.addNewIntrebare("enunt", "variantaI", "variantaII", "variantaIII", 2, "domeniul");
        ctr.addNewIntrebare("enunt1", "variantaI", "variantaII", "variantaIII", 2, "domeniu1");
        ctr.addNewIntrebare("enunt2", "variantaI", "variantaII", "variantaIII", 2, "domeniul2");
        ctr.addNewIntrebare("enunt3", "variantaI", "variantaII", "variantaIII", 2, "domeniul3");
        ctr.addNewIntrebare("enunt4", "variantaI", "variantaII", "variantaIII", 2, "domeniul4");
        assertEquals(5,ctr.createNewTest().size());
    }

    @Test
    public void CreateStatistics() throws NotAbleToCreateStatisticsException, DuplicateIntrebareException, InputValidationFailedException {
        ctr.addNewIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "domeniul");
        Statistica statistica = ctr.getStatistica();
        int numberOf = statistica.getIntrebariDomenii().size();
        Assert.assertEquals(1, numberOf);
    }

    @Test
    public void AddQuestionAndCreateQuiz() throws NotAbleToCreateTestException, DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateStatisticsException {
        boolean rez = repo.addIntrebare("enunt332", "varianta1", "varianta2", "varianta3", 3, "domeniu3");
        assertTrue("should be true, it is:" + rez, rez);

        ctr.addNewIntrebare("enunt", "variantaI", "variantaII", "variantaIII", 2, "domeniul");
        ctr.addNewIntrebare("enunt1", "variantaI", "variantaII", "variantaIII", 2, "domeniu11");
        ctr.addNewIntrebare("enunt2", "variantaI", "variantaII", "variantaIII", 2, "domeniul2");
        ctr.addNewIntrebare("enunt3", "variantaI", "variantaII", "variantaIII", 2, "domeniul3");
        ctr.addNewIntrebare("enunt4", "variantaI", "variantaII", "variantaIII", 2, "domeniul4");
        assertEquals(5,ctr.createNewTest().size());

        Statistica statistica = ctr.getStatistica();
        int numberOf = statistica.getIntrebariDomenii().size();
        Assert.assertEquals(6, numberOf);
    }
}
