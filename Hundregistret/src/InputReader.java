// Jan Pakos japa4307

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class InputReader {
    private static ArrayList<InputStream> usedStreams = new ArrayList<>();
    private Scanner scanner;
    private InputStream source;

    public InputReader() {
        this(System.in);
    }

    public InputReader(InputStream in) {
        if (usedStreams.contains(in)) {
            throw new IllegalStateException("An InputReader for this InputStream already exists.");
        }
        this.source = in;
        usedStreams.add(in);
        scanner = new Scanner(in);
    }

    public int readInt(String prompt) {
        System.out.print(prompt + " ?> ");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print(prompt + " ?> ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public double readDouble(String prompt) {
        System.out.print(prompt + " ?> ");
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print(prompt + " ?> ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public String readLine(String prompt) {
        System.out.print(prompt + " ?> ");
        return scanner.nextLine().trim();
    }
}
