package example_sets;

import example_sets.contracts.IStatement;
import example_sets.symbols.SymTable;
import java.io.StringReader;
import java.util.LinkedList;

public class Example_sets {

    public static void main(String[] args) throws Exception {
        String input1 = """
                        CONJ setA : {1, 2, 3}
                        OPERATION ( U setA setA )
                        OPERATION (^^ setA)
                        """;

        Lexer scanner = new Lexer(new StringReader(input1));
        Parser parser = new Parser(scanner);
        LinkedList<IStatement> AST = null;
        SymTable environment = new SymTable();

        try {
            parser.parse();
            AST = parser.AST;
        } catch (Exception ex) {
            // Capturar cualquier excepción durante el proceso de parsing
            System.out.println("Excepción capturada: " + ex.getMessage());
        } finally {
            // Ahora se imprime tanto errores léxicos como sintácticos al final del parsing
            if (!scanner.lexicalErrors.isEmpty()) {
                System.out.println("Errores lexicos:");
                for (String err : scanner.lexicalErrors) {
                    System.out.println(err); // Usar System.out en lugar de System.err
                }
            }

            if (!parser.syntaxErrors.isEmpty()) {
                System.out.println("Errores sintacticos:");
                for (String err : parser.syntaxErrors) {
                    System.out.println(err); // Usar System.out en lugar de System.err
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
