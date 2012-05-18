/*
 * ColorsFactory.java
 *
 * Created on April 1, 2008, 11:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package myfractals;

import myfractals.kolorowania.*;
/**
 *
 * @author victories
 */
public class ColorsFactory {
    
    /** Creates a new instance of ColorsFactory */
    public ColorsFactory() {
    }
    
    public IKolor getColoring(int index, java.util.Vector<java.awt.Color> parameters) {
        switch(index) {
            case 0: return new BlackWhite();
            case 1: return new LinearBlackWhite();
            case 2: return new LogBlackWhite();
            case 3: return new LinearColoring(parameters);
            default: return new BlackWhite();
        }
    }    
}
