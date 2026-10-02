package framework.util;

import java.util.Scanner;

/**
 * Utilitas pembacaan input konsol. Isinya sama di semua proyek.
 * Prompt selalu diakhiri " : " dan hasil input di-trim.
 * Jika input habis (EOF), dikembalikan "x" agar aplikasi keluar lewat alur normal.
 */
public class InputUtil {
    private static final String EXIT_TOKEN = "x";
    private static final Scanner scanner = new Scanner(System.in);

    private InputUtil() {
    }

    public static String input(String info) {
        System.out.print(info + " : ");
        if (!scanner.hasNextLine()) {
            return EXIT_TOKEN;
        }
        return scanner.nextLine().trim();
    }
}
