package SistemaAlquiler;
import java.util.Date;


public class Licencia {
	String numero;
	String paisExpedicion;
	Date fechaVencimiento;
	
    /**
     * Constructor de la clase Licencia.
     *
     * @param numero           El número de la licencia.
     * @param paisExpedicion   El país de expedición de la licencia.
     * @param fechaVencimiento La fecha de vencimiento de la licencia.
     */
	public Licencia(String numero, String paisExpedicion, Date fechaVencimiento) {
		this.numero = numero;
		this.paisExpedicion = paisExpedicion;
		this.fechaVencimiento = fechaVencimiento;
		
	}
	
	//Getters
    /**
     * Obtiene el número de la licencia.
     *
     * @return El número de la licencia.
     */
	public String getNumero() {
		return this.numero;
	}
	
    /**
     * Obtiene el país de expedición de la licencia.
     *
     * @return El país de expedición de la licencia.
     */
	public String getPaisExpedicion() {
		return this.paisExpedicion;
	}
	
    /**
     * Obtiene la fecha de vencimiento de la licencia.
     *
     * @return La fecha de vencimiento de la licencia.
     */
	public Date getFechaVencimineto() {
		return this.fechaVencimiento;
	}
	
	//Setters
    /**
     * Establece el número de la licencia.
     *
     * @param numero El número de la licencia.
     */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
    /**
     * Establece el país de expedición de la licencia.
     *
     * @param paisExpedicion El país de expedición de la licencia.
     */
	public void setPaisExpedicion(String paisExpedicion) {
		this.paisExpedicion = paisExpedicion;
	}
	
    /**
     * Establece la fecha de vencimiento de la licencia.
     *
     * @param fechaVencimiento La fecha de vencimiento de la licencia.
     */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	
	
	
}
