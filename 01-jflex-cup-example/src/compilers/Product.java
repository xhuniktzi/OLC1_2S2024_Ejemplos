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
public class Product implements IStatement {
    public final IStatement left;
    public final IStatement right;

    public Product(IStatement left, IStatement right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int calc() {
        int calcLeft = left.calc();
        int calcRight = right.calc();
        
        return calcLeft * calcRight;
    }
}
