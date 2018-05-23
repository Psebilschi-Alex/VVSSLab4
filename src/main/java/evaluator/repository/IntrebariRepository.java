package evaluator.repository;

import java.io.*;
import java.util.*;


import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.exception.DuplicateIntrebareException;

public class IntrebariRepository {

	private List<Intrebare> intrebari;
	private String filename;

	public IntrebariRepository(String file) {
		filename = file;
		intrebari = this.loadIntrebariFromFile();
	}

	public boolean addIntrebare(String enunt, String var1, String var2, String var3, int corect, String domeniu) throws DuplicateIntrebareException, InputValidationFailedException, InputValidationFailedException {
		Intrebare i = new Intrebare(enunt, var1, var2, var3, corect, domeniu);
		if (exists(i))
			throw new DuplicateIntrebareException("Intrebarea deja exista!");
		intrebari.add(i);
		saveIntrebariToFile();
		return true;
	}

	private boolean exists(Intrebare i) {
		for (Intrebare intrebare : intrebari)
			if (intrebare.equals(i))
				return true;
		return false;
	}

	public Intrebare pickRandomIntrebare() {
		Random random = new Random();
		return intrebari.get(random.nextInt(intrebari.size()));
	}

	public int getNumberOfDistinctDomains() {
		return getDistinctDomains().size();

	}

	public Set<String> getDistinctDomains() {
		Set<String> domains = new TreeSet<String>();
		for (Intrebare intrebre : intrebari)
			domains.add(intrebre.getDomeniu());
		return domains;
	}

	public List<Intrebare> getIntrebariByDomain(String domain) {
		List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
		for (Intrebare intrebare : intrebari) {
			if (intrebare.getDomeniu().equals(domain)) {
				intrebariByDomain.add(intrebare);
			}
		}

		return intrebariByDomain;
	}

	public int getNumberOfIntrebariByDomain(String domain) {
		return getIntrebariByDomain(domain).size();
	}

	private List<Intrebare> loadIntrebariFromFile() {
		File file = new File(filename);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Intrebare> intrebari = new LinkedList<Intrebare>();
		BufferedReader br = null;
		String line = null;
		List<String> intrebareAux;
		Intrebare intrebare;

		try {
			br = new BufferedReader(new FileReader(filename));
			while ((line=br.readLine())!=null) {
				intrebareAux= Arrays.asList(line.split(","));

				intrebare = new Intrebare();
				intrebare.setEnunt(intrebareAux.get(0));
				intrebare.setVarianta1(intrebareAux.get(1));
				intrebare.setVarianta2(intrebareAux.get(2));
				intrebare.setVarianta3(intrebareAux.get(3));
				intrebare.setVariantaCorecta(Integer.parseInt(intrebareAux.get(4)));
				intrebare.setDomeniu(intrebareAux.get(5));
				intrebari.add(intrebare);

			}

		} catch (IOException e) {
			System.out.println("can`t read from file!");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println("can`t close file!");
			}
		}

		return intrebari;
	}

	private void saveIntrebariToFile() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(filename));
			for (Intrebare intrebare : intrebari) {
				bw.write(intrebare.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("can`t write to file!");
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				System.out.println("can`t close file!");
			}
		}
	}

	public List<Intrebare> getIntrebari() {
		return intrebari;
	}

	public void setIntrebari(List<Intrebare> intrebari) {
		this.intrebari = intrebari;
	}

}