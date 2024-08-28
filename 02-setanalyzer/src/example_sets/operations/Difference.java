/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example_sets.operations;

import example_sets.contracts.IOperation;
import example_sets.symbols.SymTable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xhuni
 */
public class Difference extends IOperation {
    private final IOperation op1, op2;

    public Difference(IOperation op1, IOperation op2) {
        this.op1 = op1;
        this.op2 = op2;
    }
    
    @Override
    public Set<Integer> eval(SymTable table) {
        Set<Integer> result = new HashSet<>(op1.eval(table));
        result.removeAll(op2.eval(table));
        return result;
    }

    @Override
    public String graph() {
        StringBuilder str = new StringBuilder();
        str.append("O_")
                .append(id);
        
        str.append("[label=\"Diferencia\"];\n");
        
        str.append("O_").append(id).append(" -> ")
                .append("O_").append(op1.getId()).append(";\n");
        
        str.append(op1.graph());
        
                str.append("O_").append(id).append(" -> ")
                .append("O_").append(op2.getId()).append(";\n");
        
        str.append(op2.graph());
        
        return str.toString();
    }
    
}