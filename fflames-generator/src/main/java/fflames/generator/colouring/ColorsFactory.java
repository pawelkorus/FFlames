package fflames.generator.colouring;

import java.util.ArrayList;

import java.awt.Color;

import fflames.generator.IColour;

public class ColorsFactory {
    
    /** Creates a new instance of ColorsFactory */
    public ColorsFactory() {
    }
    
    public IColour getColoring(int index, ArrayList<Color> parameters) {
        switch(index) {
            case 0: return new BlackWhite();
            case 1: return new LinearBlackWhite();
            case 2: return new LogBlackWhite();
            case 3: return new RGBColoring(parameters);
            case 4: return new LinearRGBColoring(parameters);
            case 5: return new LogRGBColoring(parameters);
            default: return new BlackWhite();
        }
    }    
}
