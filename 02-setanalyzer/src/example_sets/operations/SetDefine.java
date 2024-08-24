/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example_sets.operations;

import example_sets.contracts.IStatement;
import example_sets.symbols.SymTable;
import java.util.Set;

/**
 *
 * @author xhuni
 */
public class SetDefine extends IStatement {
    private final String name;
    private final Set<Integer> elements;

    public SetDefine(String name, Set<Integer> elements) {
        this.name = name;
        this.elements = elements;
    }

    @Override
    public void execute(SymTable table) {
        table.add(name, elements);
        System.out.println("Conjunto: " + name + ", definido con los elementos: " + elements);
    }

    @Override
    public String graph() {
        StringBuilder str = new StringBuilder();
        str.append("S_")
                .append(id);
        str.append("[label=\"Definir conjunto: ")
                .append(name).append("\"];\n");
        
        for (Integer element : elements) {
            str.append("S_").append(id)
                    .append(" -> ")
                    .append("S_").append(id)
                    .append("_E_").append(element).append("\n");
            
            
            str.append("S_").append(id)
                    .append("_E_").append(element)
                    .append("[label=\"Elemento: ").append(element).append("\"];\n");
        }
        return str.toString();
    }
   
    
}
