package evaluator.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleHelper
{
    private Scanner scanner;

    public ConsoleHelper() {
        super();
        this.scanner = new Scanner(System.in);
    }

    public String readString() {
        String str = null;

        while (str == null) {
            try {
                str = scanner.nextLine();
            } catch (Exception ex) {
                str = null;
            }
        }

        return str;
    }

    /**
     * Citeste un text si verifica erorile, recitind pana cand
     * se va citi un text valid.
     *
     * @param msg
     *   Mesajul afisat in caz de eroare.
     * @return
     *   Un text valid.
     */
    public String readString(String msg) {
        String str = null;

        while (str == null) {
            try {
                str = scanner.nextLine();
            } catch (Exception ex) {
                str = null;
                writeLine(msg);
            }
        }

        return str;
    }

    /**
     * Citeste un numar intre min si max.Repeta citirea pana cand numarul este valid.
     *
     * @param min
     *   Numarul minim.
     * @param max
     *   Numarul maxim.
     * @param msg
     *   Mesajul afisat in caz de eroare.
     * @return
     *   Numarul citit.
     */
    public int readNumber(int min, int max, String msg) {
        writeLine(msg);
        Integer nb = null;

        while (nb == null) {
            try {
                nb = scanner.nextInt();
                scanner.nextLine();

                if (nb < min || nb > max) {
                    throw new Exception();
                }
            } catch (InputMismatchException ex) {
                scanner.nextLine();
                writeLine(msg);
            } catch (Exception ex) {
                nb = null;
                writeLine(msg);
            }
        }

        return nb.intValue();
    }

    /**
     * Citeste un numar valid si afiseaza un mesaj in caz de eroare.
     * Repeta citirea pana la un numar valid.
     *
     * @param msg
     *   Mesajul de afisat.
     * @return
     *   Numarul citit.
     */
    public int readNumber(String msg) {
        return readNumber(Integer.MIN_VALUE, Integer.MAX_VALUE, msg);
    }

    /**
     * Afiseaza un text si asteapta apasarea tastei 0 pentru a continua executia.
     */
    public void backToMenu() {
        writeLine("Intoduceti 0 pentru a va intoarce");
        readNumber(0, 0, "Intoduceti 0 pentru a va intoarce");
    }

    /**
     * Afiseaza un obiect.
     *
     * @param str
     *   Obiectul de afisat.
     */
    public void write(Object str) {
        System.out.print(str);
    }

    /**
     * Afiseaza un obiect urmat de o linie noua.
     *
     * @param str
     *   Obiectul de afisat.
     */
    public void writeLine(Object str) {
        System.out.println(str);
    }
}
