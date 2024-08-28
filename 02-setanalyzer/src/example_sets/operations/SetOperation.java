/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example_sets.operations;

import example_sets.contracts.IOperation;
import example_sets.contracts.IStatement;
import example_sets.symbols.SymTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author xhuni
 */
public class SetOperation extends IStatement {
    private final IOperation op;
    private List<String> appliedLaws = new ArrayList<String>();


    public SetOperation(IOperation op) {
        this.op = op;
    }

    @Override
    public void execute(SymTable table) {
        // Evaluar la operación original
        Set<Integer> originalResult = op.eval(table);
        
        // Simplificar la operación
        IOperation simplifiedOp = simplify(op);
        Set<Integer> simplifiedResult = simplifiedOp.eval(table);

        // Imprimir resultados
        System.out.println("Resultado de la operación original: " + originalResult);
        System.out.println("Resultado de la operación simplificada: " + simplifiedResult);
        
        // Imprimir leyes aplicadas
        if (!appliedLaws.isEmpty()) {
            System.out.println("Leyes aplicadas:");
            for (String law : appliedLaws) {
                System.out.println("- " + law);
            }
        } else {
            System.out.println("No se aplicaron leyes de simplificación.");
        }
    }

    @Override
    public String graph() {
        StringBuilder str = new StringBuilder();
        str.append("S_").append(id);
        str.append("[label=\"Operacion\"];\n");
        
        str.append("S_").append(id)
                .append(" -> ")
                .append("O_").append(op.getId())
                .append("\n");
        
        str.append(op.graph());
        
        return str.toString();
    }
    
    
    private IOperation simplify(IOperation operation){
        // idempotencia: A U A = A, A & A = A
        if (operation instanceof Union opUnion){
            if (opUnion.op1 instanceof SetReference ref1 && opUnion.op2 instanceof SetReference ref2){
                if (ref1.name.equals(ref2.name)){
                    appliedLaws.add("Idempotencia");
                    return opUnion.op1;
                }
            }
        }
      
        // doble complemento: ^^ A = A
        if (operation instanceof Complement opComplement){
            if (opComplement.op instanceof Complement opComplement2){
                appliedLaws.add("Doble complemento");
                return opComplement;
            }
        }
        
        return operation;
    }
    
}
