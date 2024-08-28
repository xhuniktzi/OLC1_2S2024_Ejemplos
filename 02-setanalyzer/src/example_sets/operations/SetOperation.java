/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example_sets.operations;

import example_sets.contracts.IOperation;
import example_sets.contracts.IStatement;
import example_sets.symbols.SymTable;
import java.util.Set;

/**
 *
 * @author xhuni
 */
public class SetOperation extends IStatement {
    private final IOperation op;

    public SetOperation(IOperation op) {
        this.op = op;
    }

    @Override
    public void execute(SymTable table) {
        Set<Integer> result = this.op.eval(table);
        System.out.println("Resultado de la operación: " + result);
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
    
    
}
