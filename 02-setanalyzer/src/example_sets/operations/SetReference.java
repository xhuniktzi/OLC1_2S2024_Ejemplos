/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example_sets.operations;

import example_sets.contracts.IOperation;
import example_sets.symbols.SymTable;
import java.util.Set;

/**
 *
 * @author xhuni
 */
public class SetReference extends IOperation {
    public final String name;

    public SetReference(String name) {
        this.name = name;
    }

    @Override
    public Set<Integer> eval(SymTable table) {
        if (!table.contains(name)){
            throw new RuntimeException("Not Found: " + name);
        }
        
        return table.get(name);
    }


    @Override
    public String graph() {
        StringBuilder str = new StringBuilder();
        str.append("O_")
                .append(id);
        str.append("[label=\"").append(name).append("\"];\n");
        
        return str.toString();
    }
}
