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
public class SetDefine implements IStatement {
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
    
   
    
}
