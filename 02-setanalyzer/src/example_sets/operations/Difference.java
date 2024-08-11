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
public class Difference implements IOperation {
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
    public IOperation simplify(SymTable table) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}