/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package example_sets;

import example_sets.contracts.IStatement;
import example_sets.symbols.SymTable;
import java.io.StringReader;
import java.util.LinkedList;

/**
 *
 * @author xhuni
 */
public class Example_sets {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String input1 = """
                        CONJ setA : {1, 2, 3}
                        CONJ setB : {4, 5, 6
                        OPERATION (^ & setA setB )
                        CONJ setC : {8 9,}
                        OPERATION ( U setA setC )
                        """;

        Lexer scanner = new Lexer(new StringReader(input1));
        Parser parser = new Parser(scanner);
        try {
        parser.parse();
        LinkedList<IStatement> AST = parser.AST;
        SymTable environment = new SymTable();
        
        for (IStatement s : AST) {
            s.execute(environment);
        }
        } catch (Exception ex){
            System.err.println("Excepción capturada");
        } finally {
            if (!scanner.lexicalErrors.isEmpty()){
                System.out.println("Errores lexicos: ");
                for (String err: scanner.lexicalErrors){
                    System.err.println(err);
                }
            }
            
            if (!parser.syntaxErrors.isEmpty()){
                System.out.println("Errores sintacticos: ");
                for (String err: parser.syntaxErrors){
                    System.err.println(err);
                }
            }
        }

    }
    
}
