/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example_sets.operations;

import example_sets.contracts.IOperation;
import example_sets.symbols.SymTable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author xhuni
 */
public class Complement implements IOperation {
    public final IOperation op;

    public Complement(IOperation op) {
        this.op = op;
    }
    
    @Override
    public Set<Integer> eval(SymTable table) {
        Set<Integer> result = new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        result.removeAll(op.eval(table));
        return result;
    }


    @Override
    public String graph() {
        StringBuilder str = new StringBuilder();
        str.append("O_")
                .append(id);
        
        str.append("[label=\"Complemento\"];\n");
        
        str.append("O_").append(id).append(" -> ")
                .append("O_").append(op.getId()).append(";\n");
        
        str.append(op.graph());
        return str.toString();
    }
}