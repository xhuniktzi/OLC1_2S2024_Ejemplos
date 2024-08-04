/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example_sets.symbols;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author xhuni
 */
public class SymTable {
    private Map<String, Set<Integer>> table;
    
    public SymTable(){
        this.table = new HashMap<>();
    }
    
    public void add(String name, Set<Integer> elements){
        this.table.put(name, elements);
    }
    
    public Set<Integer> get(String name){
        return this.table.get(name);
    }
    
    public boolean contains(String name){
        return this.table.containsKey(name);
    }
}
