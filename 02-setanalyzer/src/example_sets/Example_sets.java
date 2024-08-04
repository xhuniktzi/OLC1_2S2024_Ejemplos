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
                        CONJ conja : {1,2,3}
                        CONJ datab : {4,5,6}
                        CONJ setc : {3}
                        CONJ conjU : {9}
                        OPERATION (conja U datab)
                        
                        OPERATION (conjU U setc)
                        
                        
                        """;

        Lexer scanner = new Lexer(new StringReader(input1));
        Parser parser = new Parser(scanner);
        parser.parse();
        LinkedList<IStatement> AST = parser.AST;
        SymTable environment = new SymTable();
        
        for (IStatement s : AST) {
            s.execute(environment);
        }
    }
    
}
