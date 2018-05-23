package evaluator.controller;

import java.util.LinkedList;
import java.util.List;

import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.model.Test;
import evaluator.repository.IntrebariRepository;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;

public class AppController {

	private IntrebariRepository intrebariRepository;

	public AppController(IntrebariRepository intrebariRepository) {
		this.intrebariRepository = intrebariRepository;
	}

	public void addNewIntrebare(String enunt, String var1, String var2, String var3, int corect, String domeniu) throws DuplicateIntrebareException, InputValidationFailedException {
		intrebariRepository.addIntrebare(enunt, var1, var2, var3, corect, domeniu);
	}

	public Test createNewTest() throws NotAbleToCreateTestException {
		if (intrebariRepository.getIntrebari().size() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente" +
					" intrebari pentru crearea unui test!(5)");
		if (intrebariRepository.getNumberOfDistinctDomains() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente domenii" +
					" pentru crearea unui test!(5)");
		List<Intrebare> testIntrebari = new LinkedList<Intrebare>();
		List<String> domenii = new LinkedList<String>();
		Intrebare intrebare;
		Test test = new Test();
		while (testIntrebari.size() < 5) {
			intrebare = intrebariRepository.pickRandomIntrebare();
			if (!testIntrebari.contains(intrebare) && !domenii.contains(intrebare.getDomeniu())) {
				testIntrebari.add(intrebare);
				domenii.add(intrebare.getDomeniu());
			}
		}
		test.setIntrebari(testIntrebari);
		return test;
	}

	public Statistica getStatistica() throws NotAbleToCreateStatisticsException {

		if (intrebariRepository.getIntrebari().isEmpty())
			throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");

		Statistica statistica = new Statistica();
		for (String domeniu : intrebariRepository.getDistinctDomains()) {
			statistica.add(domeniu, intrebariRepository.getNumberOfIntrebariByDomain(domeniu));
		}

		return statistica;
	}

}
