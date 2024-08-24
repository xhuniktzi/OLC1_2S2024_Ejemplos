package example_sets.contracts;


import example_sets.symbols.SymTable;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author xhuni
 */
public abstract class IOperation extends IGraphicable{
    public abstract Set<Integer> eval(SymTable table);
}
