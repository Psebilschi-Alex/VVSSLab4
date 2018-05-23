package evaluator.main;

import evaluator.controller.AppController;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.repository.IntrebariRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartApp {

    private static final String file = "intrebari.txt";

    public static void main(String[] args) throws IOException, IOException {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        IntrebariRepository repoIntrebari = new IntrebariRepository(file);
        AppController appController = new AppController(repoIntrebari);

        boolean activ = true;
        String optiune = null;

        while (activ) {

            System.out.println("");
            System.out.println("1.Adauga intrebare");
            System.out.println("2.Creeaza test");
            System.out.println("3.Statistica");
            System.out.println("4.Exit");
            System.out.println("");
            System.out.print("Comanda:");

            optiune = console.readLine();

            switch (optiune) {
                case "1": {
                    System.out.print("Enunt:");
                    String enunt = console.readLine();
                    System.out.print("Varianta1:");
                    String var1 = console.readLine();
                    System.out.print("Varianta2:");
                    String var2 = console.readLine();
                    System.out.print("Varianta3:");
                    String var3 = console.readLine();
                    System.out.print("Raspunsul:");
                    String corect = console.readLine();
                    System.out.print("Domeniu:");
                    String domeniu = console.readLine();
                    try {
                        appController.addNewIntrebare(enunt, var1, var2, var3, Integer.parseInt(corect), domeniu);
                    } catch (InputValidationFailedException | DuplicateIntrebareException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                }
                case "2":
                    try {
                        System.out.println();
                        Integer i = 0;
                        for (Intrebare intrebare : appController.createNewTest().getIntrebari()) {
                            i++;
                            System.out.println(i + ") " + intrebare);
                        }
                    } catch (NotAbleToCreateTestException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    Statistica statistica;
                    try {
                        statistica = appController.getStatistica();
                        System.out.println(statistica);
                    } catch (NotAbleToCreateStatisticsException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "4":
                    activ = false;
                    break;
                default:
                    break;
            }
        }

    }

}
