package TestingLevels;

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
import java.util.Map;

public class StatisticsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private Map<String, Integer> intrebariDomenii;
    private AppController ctr;
    private IntrebariRepository repo;

    @Before
    public void setUp() throws Exception {
        File f = new File("teste.txt");
        f.delete();
        repo = new IntrebariRepository("teste.txt");
        ctr = new AppController(repo);
    }

    @Test
    public void AddStatistics_EmptyRepository_ThrowsException() throws NotAbleToCreateStatisticsException {
        thrown.expect(NotAbleToCreateStatisticsException.class);
        thrown.expectMessage("Repository-ul nu contine nicio intrebare!");
        Statistica statistica = ctr.getStatistica();
        int numberOf = statistica.getIntrebariDomenii().size();
    }

    @Test
    public void AddStatistics_AddQuestion_ValidConfiguration() throws NotAbleToCreateStatisticsException, DuplicateIntrebareException, InputValidationFailedException {
        //thrown.expect(NotAbleToCreateStatisticsException.class);
       // thrown.expectMessage("Repository-ul nu contine nicio intrebare!");
        ctr.addNewIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "domeniul");
        Statistica statistica = ctr.getStatistica();
        int numberOf = statistica.getIntrebariDomenii().size();
        Assert.assertEquals(1, numberOf);
    }

}
