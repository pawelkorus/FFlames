package fflames.base.variation;

import fflames.base.IVariation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VariationsFactory {
	private static final String[] names = {
		"Linear", "Sinusoidal", "Spherical",
		"Swirl", "Horseshoe", "Polar",
		"Handkerchief", "Heart", "Disc",
		"Spiral", "Hyperbolic", "Diamond",
		"Ex", "Julia", "Bent",
		"Waves", "Fisheye", "Popcorn",
		"Exponential", "Power", "Cosine",
		"Rings", "Fan", "Blob",
		"PDJ", "Fan2", "Rings2",
		"Eyefish", "Bubble", "Cylinder",
		"Perspective", "Noise", "JuliaN",
		"JuliaScope", "Blur", "Gaussian",
		"RadialBlur", "Pie", "Ngon",
		"Curl", "Rectangles", "Arch",
		"Tangent", "Square", "Rays",
		"Blade", "Secant", "Cross"
	};

	public static IVariation getVariation(String name, Double coefficient) {
		String packageName = VariationsFactory.class.getPackage().getName();
		String variationFullyQualifiedClassName = packageName + "." + name;
		
		try {
			
			Class c = Class.forName(variationFullyQualifiedClassName);
			Constructor constructor = c.getConstructor(Double.class);
			return (IVariation) constructor.newInstance(coefficient);
			
		} catch(ClassNotFoundException | 
				InstantiationException | 
				NoSuchMethodException |
				IllegalAccessException |
				InvocationTargetException e) 
		{
			e.printStackTrace();
		}
		
		return new NullVariation();
	}

	public static IVariation getVariation(int index, Double coefficient) {
		return getVariation(names[index], coefficient);
	}

	public static int getVariationIndex(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].compareTo(name) == 0) {
				return i;
			}
		}
		return 0;
	}

	public static String getVariationName(int index) {
		return names[index];
	}

	public static int getVariationQuantity() {
		return names.length;
	}
	
	private VariationsFactory() {};
}
