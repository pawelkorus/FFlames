package fflames.generator.variation;

import fflames.generator.IVariation;

public class VariationsFactory {

	private static final String[] names = {"Linear", "Sinusoidal", "Spherical",
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
		"Blade", "Secant", "Cross"};

	public static IVariation getWariation(String name, Double coefficient) {
		if (name.compareTo("Linear") == 0) {
			return new Linear(coefficient);
		}
		if (name.compareTo("Sinusoidal") == 0) {
			return new Sinusoidal(coefficient);
		}
		if (name.compareTo("Spherical") == 0) {
			return new Spherical(coefficient);
		}
		if (name.compareTo("Swirl") == 0) {
			return new Swirl(coefficient);
		}
		if (name.compareTo("Horseshoe") == 0) {
			return new Horseshoe(coefficient);
		}
		if (name.compareTo("Polar") == 0) {
			return new Polar(coefficient);
		}
		if (name.compareTo("Handkerchief") == 0) {
			return new Handkerchief(coefficient);
		}
		if (name.compareTo("Heart") == 0) {
			return new Heart(coefficient);
		}
		if (name.compareTo("Disc") == 0) {
			return new Disc(coefficient);
		}
		if (name.compareTo("Spiral") == 0) {
			return new Spiral(coefficient);
		}
		if (name.compareTo("Hyperbolic") == 0) {
			return new Hyperbolic(coefficient);
		}
		if (name.compareTo("Diamond") == 0) {
			return new Diamond(coefficient);
		}
		if (name.compareTo("Ex") == 0) {
			return new Ex(coefficient);
		}
		if (name.compareTo("Julia") == 0) {
			return new Julia(coefficient);
		}
		if (name.compareTo("Bent") == 0) {
			return new Bent(coefficient);
		}
		if (name.compareTo("Waves") == 0) {
			return new Waves(coefficient);
		}
		if (name.compareTo("Fisheye") == 0) {
			return new Fisheye(coefficient);
		}
		if (name.compareTo("Popcorn") == 0) {
			return new Popcorn(coefficient);
		}
		if (name.compareTo("Exponential") == 0) {
			return new Exponential(coefficient);
		}
		if (name.compareTo("Power") == 0) {
			return new Power(coefficient);
		}
		if (name.compareTo("Cosine") == 0) {
			return new Cosine(coefficient);
		}
		if (name.compareTo("Rings") == 0) {
			return new Rings(coefficient);
		}
		if (name.compareTo("Fan") == 0) {
			return new Fan(coefficient);
		}
		if (name.compareTo("Blob") == 0) {
			return new Blob(coefficient);
		}
		if (name.compareTo("PDJ") == 0) {
			return new PDJ(coefficient);
		}
		if (name.compareTo("Fan2") == 0) {
			return new Fan2(coefficient);
		}
		if (name.compareTo("Rings2") == 0) {
			return new Rings2(coefficient);
		}
		if (name.compareTo("Eyefish") == 0) {
			return new Eyefish(coefficient);
		}
		if (name.compareTo("Bubble") == 0) {
			return new Bubble(coefficient);
		}
		if (name.compareTo("Cylinder") == 0) {
			return new Cylinder(coefficient);
		}
		if (name.compareTo("Perspective") == 0) {
			return new Perspective(coefficient);
		}
		if (name.compareTo("Noise") == 0) {
			return new Noise(coefficient);
		}
		if (name.compareTo("JuliaN") == 0) {
			return new JuliaN(coefficient);
		}
		if (name.compareTo("JuliaScope") == 0) {
			return new JuliaScope(coefficient);
		}
		if (name.compareTo("Blur") == 0) {
			return new Blur(coefficient);
		}
		if (name.compareTo("Gaussian") == 0) {
			return new Gaussian(coefficient);
		}
		if (name.compareTo("RadialBlur") == 0) {
			return new RadialBlur(coefficient);
		}
		if (name.compareTo("Pie") == 0) {
			return new Pie(coefficient);
		}
		if (name.compareTo("Ngon") == 0) {
			return new Ngon(coefficient);
		}
		if (name.compareTo("Curl") == 0) {
			return new Curl(coefficient);
		}
		if (name.compareTo("Rectangles") == 0) {
			return new Rectangles(coefficient);
		}
		if (name.compareTo("Arch") == 0) {
			return new Arch(coefficient);
		}
		if (name.compareTo("Tangent") == 0) {
			return new Tangent(coefficient);
		}
		if (name.compareTo("Square") == 0) {
			return new Square(coefficient);
		}
		if (name.compareTo("Rays") == 0) {
			return new Rays(coefficient);
		}
		if (name.compareTo("Blade") == 0) {
			return new Blade(coefficient);
		}
		if (name.compareTo("Secant") == 0) {
			return new Secant(coefficient);
		}
		if (name.compareTo("TwinTrian") == 0) {
			return new TwinTrian(coefficient);
		}
		if (name.compareTo("Cross") == 0) {
			return new Cross(coefficient);
		}
		return new NullVariation();
	}

	public static IVariation getVariation(int index, Double coefficient) {
		return getWariation(names[index], coefficient);
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
