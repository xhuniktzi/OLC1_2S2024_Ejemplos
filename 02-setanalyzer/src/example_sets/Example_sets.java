package example_sets;

import example_sets.contracts.IStatement;
import example_sets.operations.SetDefine;
import example_sets.symbols.SymTable;
import java.io.StringReader;
import java.util.LinkedList;

public class Example_sets {
    public static void main(String[] args) throws Exception {
        String input1 = """
                        CONJ setA : {1, 2, 3}
                        CONJ setB : {4, 5, 6}
                        CONJ setC : {7, 8, 9}
                        CONJ setD : {0, 2, 4, 6, 8}
                        CONJ setE : {1, 3, 5, 7, 9}
                        CONJ setF : {0, 1, 2, 3, 4}
                        
                        OPERATION (  & U ^ setA setB - U setC setD & setE ^ setF )
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
                
                StringBuilder str = new StringBuilder();
                str.append("""
                           digraph G {
                           rootNode [label="Raiz"];
                           node[shape="rectangle"];
                           splines=polyline;
                           concentrate=true;
                           """);
                
                for (IStatement s : AST) {
                    try {
                        str.append(s.graph());
                        str.append("rootNode -> S_").append(s.getId()).append(";\n");
                    } catch (Exception e) {
                        continue;
                    }
                }
                
                str.append("}");
                
                System.out.println("Graphviz:");
                System.out.println(str);
            }
        }
    }
}
