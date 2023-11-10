package SistemaAlquiler;
import java.util.Date;

public class MedioPago {
	String numero;
	String tipo;
	Date fechaVencimiemto;
	
    /**
     * Constructor de la clase MedioPago.
     *
     * @param numero           El número del medio de pago.
     * @param tipo             El tipo de medio de pago (por ejemplo, tarjeta de crédito).
     * @param fechaVencimiento La fecha de vencimiento del medio de pago.
     */
	public MedioPago(String numero, String tipo, Date fechaVencimiento) {
		this.numero = numero;
		this.tipo = tipo;
		this.fechaVencimiemto = fechaVencimiento;
	}

    //Getters
	   /**
     * Obtiene el número del medio de pago.
     *
     * @return El número del medio de pago.
     */
	public String getNumero() {
		return this.numero;
	}
	
    /**
     * Obtiene el tipo del medio de pago.
     *
     * @return El tipo del medio de pago.
     */
	public String getTipo() {
		return this.tipo;
	}
	
    /**
     * Obtiene la fecha de vencimiento del medio de pago.
     *
     * @return La fecha de vencimiento del medio de pago.
     */
	public Date getFechaVencimiento() {
		return this.fechaVencimiemto;
	}
	
	
	//Setters
    /**
     * Establece el número del medio de pago.
     *
     * @param numero El número del medio de pago.
     */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
    /**
     * Establece el tipo del medio de pago.
     *
     * @param tipo El tipo del medio de pago.
     */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
    /**
     * Establece la fecha de vencimiento del medio de pago.
     *
     * @param fechaVencimiento La fecha de vencimiento del medio de pago.
     */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiemto = fechaVencimiento;
	}
}

