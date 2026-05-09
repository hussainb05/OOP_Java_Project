import java.util.Scanner;

 class BugDetectiveAI {

    static class Bug {
        String line;
        String message;

        Bug(String line, String message) {
            this.line = line;
            this.message = message;
        }

        void display() {
            System.out.println(" Bug Found: " + message);
            System.out.println("   Line: " + line);
            System.out.println();
        }
    }

    static class CodeAnalyzer {

        Bug[] bugs = new Bug[100];   // array instead of ArrayList
        int bugCount = 0;

        void analyze(String code) {
            String[] lines = code.split("\n");

            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];

                // Rule 1: Missing semicolon
                if (line.contains("System.out.println") && !line.trim().endsWith(";")) {
                    bugs[bugCount++] = new Bug(line, "Missing semicolon ';'");
                }

                // Rule 2: = instead of ==
                if (line.contains("if") && line.contains("=") && !line.contains("==")) {
                    bugs[bugCount++] = new Bug(line, "Use '==' instead of '=' in condition");
                }

                // Rule 3: Empty if block
                if (line.contains("if") && line.contains("{}")) {
                    bugs[bugCount++] = new Bug(line, "Empty if block detected");
                }

                // Rule 4: Division by zero
                if (line.contains("/") && line.contains("0")) {
                    bugs[bugCount++] = new Bug(line, "Possible division by zero");
                }
            }
        }

        void showReport() {
            System.out.println("\n===== BUG REPORT =====");

            if (bugCount == 0) {
                System.out.println("No bugs found. Code looks clean!");
            } else {
                for (int i = 0; i < bugCount; i++) {
                    bugs[i].display();
                }
            }
        }
    }

    // ========== MAIN ==========
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CodeAnalyzer analyzer = new CodeAnalyzer();

        System.out.println("===== BUG DETECTIVE AI =====");
        System.out.println("Paste your code (type END to finish):");

        String code = "";

        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("END")) break;
            code += line + "\n";
        }

        analyzer.analyze(code);
        analyzer.showReport();

        System.out.println("\nThank you for using Bug Detective AI!");
    }
}