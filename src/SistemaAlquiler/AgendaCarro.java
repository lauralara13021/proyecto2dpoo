package SistemaAlquiler;

import java.util.Date;

public class AgendaCarro {
	private Date FechaInicio;
	private Date FechaFinal;
	private String EstadoCarro;
	
	/**
	 * Crea una nueva instancia de AgendaCarro con las fechas de inicio y final y el estado del carro especificados.
	 *
	 * @param FechaInicio La fecha de inicio de la agenda del carro.
	 * @param FechaFinal La fecha final de la agenda del carro.
	 * @param EstadoCarro El estado del carro en la agenda.
	 * @throws IllegalArgumentException Si la fecha de inicio es posterior a la fecha final.
	 */
	public AgendaCarro(Date FechaInicio, Date FechaFinal, String EstadoCarro){
		this.FechaInicio = FechaInicio;
		this.FechaFinal = FechaFinal;
		this.EstadoCarro = EstadoCarro;	
	}
	
	/**
	 * Obtiene la fecha de inicio de la agenda del carro.
	 *
	 * @return La fecha de inicio de la agenda del carro.
	 */
	public Date getFechaInicio() {
		return FechaInicio;
	}
	
	/**
	 * Obtiene la fecha final de la agenda del carro.
	 *
	 * @return La fecha final de la agenda del carro.
	 */
	public Date getFechaFinal() {
		return FechaFinal;
	}
	
	/**
	 * Obtiene el estado del carro en la agenda.
	 *
	 * @return El estado del carro en la agenda.
	 */
	public String getEstadoCarro(){
		return EstadoCarro;
		
	}
	
	/**
	 * Establece la fecha de inicio de la agenda del carro.
	 *
	 * @param FechaInicio La nueva fecha de inicio de la agenda del carro.
	 * @throws IllegalArgumentException Si la nueva fecha de inicio es posterior a la fecha final.
	 */
	public void setFechaInicio(Date FechaInicio) {
		this.FechaInicio = FechaInicio;
	}
	
	/**
	 * Establece la fecha final de la agenda del carro.
	 *
	 * @param FechaFinal La nueva fecha final de la agenda del carro.
	 * @throws IllegalArgumentException Si la fecha de inicio es posterior a la nueva fecha final.
	 */
	public void setFechaFinal(Date FechaFinal) {
		this.FechaFinal = FechaFinal;
	}
	
	/**
	 * Establece el estado del carro en la agenda.
	 *
	 * @param EstadoCarro El nuevo estado del carro en la agenda.
	 */
	public void setEstadoCarro(String EstadoCarro){
		this.EstadoCarro = EstadoCarro;
		
	}
}
