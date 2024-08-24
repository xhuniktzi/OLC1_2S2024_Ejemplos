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
    final IOperation op;

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
    public IOperation simplify(SymTable table) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}