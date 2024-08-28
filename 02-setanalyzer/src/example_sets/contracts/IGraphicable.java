/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package example_sets.contracts;

import example_sets.Global;

/**
 *
 * @author xhuni
 */
public abstract class IGraphicable {
     protected final int id;
    
    // Constructor que genera el id automáticamente
    public IGraphicable() {
        this.id = Global.idCounter++;
    }

    public abstract String graph();
    
    public int getId() {return id;}

}
