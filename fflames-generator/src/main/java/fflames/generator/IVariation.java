package fflames.generator;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Variation interface
 */
public interface IVariation {

	/**
	 * Funkcja zwracaj�ca wsp�czynnik wariacji
	 *
	 * @return Warto�� double wsp�czynnika
	 */
	public Double getCoefficient();

	/**
	 * Ustawia warto�� wsp�czynnika dla danej wariacji
	 *
	 * @param _coefficient warto�� nowego wsp�czynnika
	 */
	public void setCoefficient(Double _coefficient);

	/**
	 * Funkcja obliczaj�ca wsp�rz�dne nowego punktu przez przekszta�cenie
	 * obiektu wej�ciowego przez odpowiedni� wariacje
	 *
	 * @param point wspo�rz�dne przekszta�canego punktu
	 * @return Obiekt typu Point2D o nowych wsp�rz�dnych
	 */
	public Point2D calculate(Point2D point);

	/**
	 * Funkcja zwracaj�ca ilo�� dodatkowych parametr�w
	 *
	 * @return warto�� int oznaczaj�ca ilo�� dodatkowych parametr�w
	 */
	public int getParametersQuantity();

	/**
	 * Set variation parameters
	 *
	 * @param parameters collection containing parameters
	 */
	public void setParameters(ArrayList<Double> parameters);

	/**
	 * Funkcja zwracająca dla danej wariacji ustawione parametry. Na pierwszej
	 * pozycji znajduje się współczynnik, na dalszych dodatkowe paramtry
	 *
	 * @return Wektro zawierający wartości dodatkowych parametów
	 */
	public ArrayList<Double> getParameters();

	/**
	 * Returns the name of the variation
	 *
	 * @return String
	 */
	public String getName();

	/**
	 * Funkcja ta okre�la czy dana wariacja jest zale�na od wsp�czynnik�w
	 * transformacji afinicznej.
	 *
	 * @return true je�li transformacja jest zale�na od wsp�czynnik�w
	 * przekszta�cenia afinicznego. W innym wypadku zwraca warto�� false.
	 */
	public boolean isDependent();

	/**
	 * Cheks corectenss of the variation
	 *
	 * @return true if the variation is correct. false otherwise.
	 */
	public boolean isValid();
}
