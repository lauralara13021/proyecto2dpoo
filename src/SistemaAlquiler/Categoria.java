package SistemaAlquiler;

public class Categoria {
	private String Nombre;
	private int TarifaTempAlta;
	private int TarifaTemBaja;
	private int valorAdicionalConductor;
	private int valorSedeDiferente;
	
	/**
	 * Crea una nueva instancia de Categoria con los parámetros especificados.
	 *
	 * @param Nombre El nombre de la categoría.
	 * @param TarifaTempAlta La tarifa en temporada alta.
	 * @param TarifaTempBaja La tarifa en temporada baja.
	 * @param valorAdicionalConductor El valor adicional para conductor.
	 * @param valorSedeDiferente El valor adicional por sede diferente.
	 */
	public Categoria(String Nombre, int TarifaTempAlta, int TarifaTempBaja, int valorAdicionalConductor, int valorSedeDiferente) {
		this.Nombre = Nombre;
		this.TarifaTempAlta = TarifaTempAlta;
		this.TarifaTemBaja = TarifaTempBaja;
		this.valorAdicionalConductor = valorAdicionalConductor;
		this.valorSedeDiferente = valorSedeDiferente;
	}
	
	
	//Getters
	/**
	 * Obtiene el nombre de la categoría.
	 *
	 * @return El nombre de la categoría.
	 */
	public String getNombre() {
		return this.Nombre;
	}
	
	/**
	 * Obtiene la tarifa en temporada alta.
	 *
	 * @return La tarifa en temporada alta.
	 */
	
	public int getTarifaTempAlta() {
		return this.TarifaTempAlta;
	}
	
	/**
	 * Obtiene la tarifa en temporada baja.
	 *
	 * @return La tarifa en temporada baja.
	 */
	public int getTarifaTempBaja() {
		return this.TarifaTemBaja;
	}
	
	/**
	 * Obtiene el valor adicional para conductor.
	 *
	 * @return El valor adicional para conductor.
	 */
	public int getvalorAdicionalConductor() {
		return this.valorAdicionalConductor;
	}
	
	/**
	 * Obtiene el valor adicional por sede diferente.
	 *
	 * @return El valor adicional por sede diferente.
	 */
	public int getvalorSedeDiferente() {
		return this.valorSedeDiferente;
	}
	
	
	//Setters
	/**
	 * Establece el nombre de la categoría.
	 *
	 * @param Nombre El nuevo nombre de la categoría.
	 */
	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}
	
	/**
	 * Establece la tarifa en temporada alta.
	 *
	 * @param TarifaTempAlta La nueva tarifa en temporada alta.
	 */
	public void setTarifaTempAlta(int TarifaTempAlta) {
		this.TarifaTempAlta = TarifaTempAlta;
	}
	
	/**
	 * Establece la tarifa en temporada baja.
	 *
	 * @param TarifaTempBaja La nueva tarifa en temporada baja.
	 */
	public void setTarifaTempBaja(int TarifaTempBaja) {
		this.TarifaTemBaja = TarifaTempBaja;
	}
	
	/**
	 * Establece el valor adicional por sede diferente.
	 *
	 * @param valorSedeDiferente El nuevo valor adicional por sede diferente.
	 */
	public void setValorSedeDiferente(int valorSedeDiferente) {
		this.valorSedeDiferente = valorSedeDiferente;
	}
	
	/**
	 * Establece el valor adicional para conductor.
	 *
	 * @param valorAdicionalConductor El nuevo valor adicional para conductor.
	 */
	public void setValorAdicionalConductor(int valorAdicionalConductor) {
		this.valorAdicionalConductor = valorAdicionalConductor;
	}
}
