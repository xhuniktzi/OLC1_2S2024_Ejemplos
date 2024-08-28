/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package example_sets.contracts;

import example_sets.symbols.SymTable;

/**
 *
 * @author xhuni
 */
public abstract class IStatement extends IGraphicable {
    public abstract void execute(SymTable table);
}
