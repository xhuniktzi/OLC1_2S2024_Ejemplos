/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilers;

import interfaces.IStatement;

/**
 *
 * @author xhuni
 */
public class Numeric implements IStatement{
    public final int num;
    public Numeric(int num) {
        this.num = num;
    }

    @Override
    public int calc() {
        return num;
    }
    
    
    
}
