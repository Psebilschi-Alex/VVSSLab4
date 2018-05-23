package evaluator;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.repository.IntrebariRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.Random;

import static junit.framework.TestCase.assertTrue;

public class IntrebariRepositoryTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private IntrebariRepository repo;

    @Before
    public void setUp() throws Exception {
        File f = new File("teste.txt");
        f.delete();
        repo = new IntrebariRepository("teste.txt");
    }

    @Test
    public void TC1_ECP() throws DuplicateIntrebareException, InputValidationFailedException {

        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "domeniu");
        assertTrue("should be true, it is:" + rez, rez);
    }

    @Test
    public void TC2_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        thrown.expect(InputValidationFailedException.class);
        thrown.expectMessage("Enuntul este vid!");
        boolean rez = repo.addIntrebare("", "varianta1", "varianta2", "varianta3", 3, "domeniu");
    }

    @Test
    public void TC3_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        thrown.expect(InputValidationFailedException.class);
        thrown.expectMessage("Varianta1 este vida!");
        boolean rez = repo.addIntrebare("enunt", "", "varianta2", "varianta3", 2, "domeniu");
    }

    @Test
    public void TC4_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        thrown.expect(InputValidationFailedException.class);
        thrown.expectMessage("Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}");
        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 0, "domeniu");
    }

    @Test
    public void TC15_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "domeniu");
        assertTrue("should be true, it is:" + rez, rez);
    }
    @Test
    public void TC5_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        thrown.expect(InputValidationFailedException.class);
        thrown.expectMessage("Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}");
        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 4, "domeniu");
    }

    @Test
    public void TC6_ECP() throws DuplicateIntrebareException, InputValidationFailedException {
        thrown.expect(InputValidationFailedException.class);
        thrown.expectMessage("Domeniul este vid!");
        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, "");
    }

    @Test
    public void TC3_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        boolean rez = repo.addIntrebare("M", "varianta1", "varianta2", "varianta3", 2, "domeniu");
        assertTrue("should be true, it is:" + rez, rez);
    }
    @Test
    public void TC4_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        boolean rez = repo.addIntrebare(generateString(100), "varianta1", "varianta2", "varianta3", 2, "domeniu");
        assertTrue("should be true, it is:" + rez, rez);
    }
    @Test
    public void TC5_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        boolean rez = repo.addIntrebare(generateString(99), "varianta1", "varianta2", "varianta3", 2, "domeniu");
        assertTrue("should be true, it is:" + rez, rez);
    }
    @Test
    public void TC6_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        thrown.expect(InputValidationFailedException.class);
        thrown.expectMessage("Lungimea enuntului depaseste 100 de caractere!");
        boolean rez = repo.addIntrebare(generateString(101), "varianta1", "varianta2", "varianta3", 2, "domeniu");
    }
    @Test
    public void TC21_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, generateString(1));
        assertTrue("should be true, it is:" + rez, rez);
    }
    @Test
    public void TC22_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, generateString(29));
        assertTrue("should be true, it is:" + rez, rez);
    }
    @Test
    public void TC23_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, generateString(30));
        assertTrue("should be true, it is:" + rez, rez);
    }
    @Test
    public void TC24_BVA() throws DuplicateIntrebareException, InputValidationFailedException {
        thrown.expect(InputValidationFailedException.class);
        thrown.expectMessage("Lungimea domeniului depaseste 30 de caractere!");
        boolean rez = repo.addIntrebare("enunt", "varianta1", "varianta2", "varianta3", 2, generateString(31));
    }
    private String generateString( int length)
    {
        String characters= "abcdefghijklmnopqrstuvwxyz";
        Random rng = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
