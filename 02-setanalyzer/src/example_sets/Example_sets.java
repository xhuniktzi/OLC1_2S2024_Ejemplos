package example_sets;

import example_sets.contracts.IStatement;
import example_sets.symbols.SymTable;
import java.io.StringReader;
import java.util.LinkedList;

public class Example_sets {

    public static void main(String[] args) throws Exception {
        String input1 = """
                        CONJ setA : {1, 2, 3}
                        CONJ setB : {4, 5, 6
                        OPERATION (^ & setA setB )
                        CONJ setC : {8 9,}
                        OPERATION ( U setA setC )
                        """;

        Lexer scanner = new Lexer(new StringReader(input1));
        Parser parser = new Parser(scanner);
        LinkedList<IStatement> AST = null;
        SymTable environment = new SymTable();

        try {
            parser.parse();
            AST = parser.AST;
        } catch (Exception ex) {
            System.err.println("Excepción capturada: " + ex.getMessage());
        } finally {
            // Ahora se imprime tanto errores léxicos como sintácticos al final del parsing
            if (!scanner.lexicalErrors.isEmpty()) {
                System.out.println("Errores lexicos:");
                for (String err : scanner.lexicalErrors) {
                    System.err.println(err);
                }
            }

            if (!parser.syntaxErrors.isEmpty()) {
                System.out.println("Errores sintacticos:");
                for (String err : parser.syntaxErrors) {
                    System.err.println(err);
                }
            }

            // Si no hubo errores fatales, ejecutar los AST
            if (AST != null && parser.syntaxErrors.isEmpty()) {
                for (IStatement s : AST) {
                    s.execute(environment);
                }
            }
        }
    }
}
