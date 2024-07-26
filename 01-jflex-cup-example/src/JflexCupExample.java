
import interfaces.IStatement;
import java.io.StringReader;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author xhuni
 */
public class JflexCupExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        // TODO code application logic here
        Lexer scanner = new Lexer(new StringReader("3+4*2"));
        Parser parser = new Parser(scanner);
        parser.parse();
        IStatement result = parser.AST;
        int response = result.calc();
        System.out.println("Respuesta: " + response);
    }
    
}
