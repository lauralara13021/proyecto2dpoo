package SistemaAlquiler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Reserva {
	private String Categoria;
	private String IdSedeRecoger;
	private String IdSedeDevolver;
	//fecha debe tener DD/MM/AA/hh/mm
	private Date FechaEntrega;
	private Date FechaRetorno;
	private String Cliente;
	private String IdCarro;
	private List<Seguro> seguros;
	private List<String> conductores;
	private double precio;
	private double precioAbonado;
	private String estado;
	
	/**
     * Constructor de la clase Reserva.
     *
     * @param Categoria       La categoría del vehículo a reservar.
     * @param FechaEntrega    La fecha de entrega de la reserva.
     * @param FechaRetorno    La fecha de retorno de la reserva.
     * @param Cliente         El cliente que realiza la reserva.
     * @param IdCarro         El ID del carro reservado.
     * @param IdSedeRecoger   El ID de la sede donde se recogerá el vehículo.
     * @param IdSedeDevolver  El ID de la sede donde se devolverá el vehículo.
     */
	public Reserva(String Categoria, Date FechaEntrega, Date FechaRetorno, String Cliente, String IdCarro, String IdSedeRecoger, String IdSedeDevolver, String estado) {
		this.Categoria = Categoria;
		this.FechaEntrega = FechaEntrega;
		this.FechaRetorno = FechaRetorno;
		this.Cliente = Cliente;
		this.IdCarro = IdCarro;
		this.seguros = new ArrayList<>();
		this.conductores = new ArrayList<>();
		this.IdSedeRecoger = IdSedeRecoger;
		this.IdSedeDevolver = IdSedeDevolver;
		this.precio = 0;
		this.precioAbonado = 0;
		this.estado = estado;
	}
	
	//Getters
    /**
     * Obtiene la categoría del vehículo reservado.
     *
     * @return La categoría del vehículo.
     */
	public String getCategoria() {
		return this.Categoria;
	}
	
	/**
     * Obtiene el estado de la reserva.
     *
     * @return el estado del carro.
     */
	public String getEstado() {
		return this.estado;
	}
	
    /**
     * Obtiene la fecha de entrega de la reserva.
     *
     * @return La fecha de entrega de la reserva.
     */
	public Date getFechaEntrega() {
		return this.FechaEntrega;
	}
	
    /**
     * Obtiene la fecha de retorno de la reserva.
     *
     * @return La fecha de retorno de la reserva.
     */
	public Date getFechaRetorno() {
		return this.FechaRetorno;
	}
	
    /**
     * Obtiene el cliente que realizó la reserva.
     *
     * @return El cliente que realizó la reserva.
     */
	public String getCliente() {
		return this.Cliente;
	}
	
    /**
     * Obtiene el ID del carro reservado.
     *
     * @return El ID del carro reservado.
     */
	public String getIdCarro() {
		return this.IdCarro;
	}
	
    /**
     * Obtiene los seguros asociados a la reserva.
     *
     * @return Una lista de seguros asociados a la reserva.
     */
	public List<Seguro> getSeguros(){
		return this.seguros;
	}
	
    /**
     * Obtiene el ID de la sede donde se recogerá el vehículo.
     *
     * @return El ID de la sede de recogida.
     */
	public String getIdSedeRecoger() {
		return this.IdSedeRecoger;
	}
	
    /**
     * Obtiene el ID de la sede donde se devolverá el vehículo.
     *
     * @return El ID de la sede de devolución.
     */
	public String getIdSedeDevolver() {
		return this.IdSedeDevolver;
	}
	
    /**
     * Agrega un seguro a la reserva.
     *
     * @param seguro El seguro a agregar a la reserva.
     */
	public void AgregarSeguro(Seguro seguro) {
		seguros.add(seguro);
	}
	
	
    /**
     * Elimina un seguro de la reserva.
     *
     * @param seguro El seguro a eliminar de la reserva.
     */
	public void EliminarSeguro(Seguro seguro) {
		int pos = 0;
		for(Seguro seg: seguros) {
			pos += 1;
			if(seg == seguro) {
				seguros.remove(pos);
			}
		}
	}
	
    /**
     * Agrega un conductor a la reserva.
     *
     * @param conductor El conductor a agregar a la reserva.
     */
	public void AgregarConductor(Conductor conductor) {
		conductores.add(conductor.getNombre());
	}
	
    /**
     * Elimina un conductor de la reserva.
     *
     * @param conductor El conductor a eliminar de la reserva.
     */

	public void EliminarConductor(Conductor conductor) {
		int pos = 0;
		for(String driver: conductores) {
			pos += 1;
			if(driver == conductor.getNombre()) {
				seguros.remove(pos);
			}
		}
	}
	
	/**
     * Obtiene el precio base de la reserva.
     *
     * @return El precio base de la reserva.
     */
	public double getPrecioBase() {
		return this.precio;
	}
	
	/**
     * Obtiene el valor que el cliente abonó del precio total de la reserva.
     *
     * @return El precio abonado por el cliente de la reserva.
     */
	public double getPrecioAbonado() {
		return this.precioAbonado;
	}
	
	/**
     * Calcula el precio total de la reserva, incluyendo días de alquiler y tarifas.
     *
     * @param diasRenta            Número de días de alquiler.
     * @param tarifa               Tarifa diaria del vehículo.
     * @param valorSedeDiferente   Costo adicional por devolución en una sede diferente.
     * @return El precio total de la reserva.
     */
	//Este no incluye los seguros ni conductores
	
	public double getPrecio(int diasRenta, int tarifa , int valorSedeDiferente) {
		int precio = 0;
		precio = diasRenta*tarifa;
		if (IdSedeRecoger!= IdSedeDevolver) {
			precio += valorSedeDiferente;
		}
		return precio;
	}
	
	/**
     * Establece el precio de la reserva.
     *
     * @param precio El precio de la reserva.
     */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	/**
     * Establece el estado de la reserva.
     *
     * @param estado El estado de la reserva.
     */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
     * Establece el precio que el cliente abonó del precio total de la reserva.
     *
     * @param precio El valor abonado por el cliente de la reserva.
     */
	public void setPrecioAbonado(double precio) {
		this.precioAbonado = precio;
	}
	
	/**
     * Calcula el precio total de la reserva con conductores adicionales.
     *
     * @param precio               Precio actual de la reserva.
     * @param valorAdicionalConductor Costo adicional por cada conductor adicional.
     * @return El precio total de la reserva con conductores adicionales.
     */
	//Solo incluye los conductores
	public double getPrecioConductores(double precio, int valorAdicionalConductor) {
		precio += conductores.size()*valorAdicionalConductor;
		return precio;
	}
	
	 /**
     * Calcula el 30% del precio total de la reserva.
     *
     * @param precio Precio total de la reserva.
     * @return El 30% del precio total de la reserva.
     */
	public double get30ptcPrecio(double precio) {
		double treintaPct = precio*0.3;
		
		return treintaPct;
		
	}
	
	/**
     * Calcula el precio total de la reserva con seguros adicionales.
     *
     * @param precio    Precio actual de la reserva.
     * @param diasRenta Número de días de alquiler.
     * @return El precio total de la reserva con seguros adicionales.
     */
	//Esta incluye los seguros
	public double getPrecioConSeguros(double precio, int diasRenta) {
		for(Seguro seg: seguros) {
			precio += seg.getCostoPorDia()* diasRenta;
		}
		return precio;
	}
	
	
	//Setters
	  /**
   * Establece la fecha de entrega de la reserva.
   *
   * @param FechaEntrega La nueva fecha de entrega de la reserva.
   */
	public void setFechaEntrega(Date FechaEntrega) {
		this.FechaEntrega = FechaEntrega;
	}
  /**
   * Establece la fecha de retorno de la reserva.
   *
   * @param FechaRetorno La nueva fecha de retorno de la reserva.
   */
	public void setFechaRetorno(Date FechaRetorno) {
		this.FechaRetorno = FechaRetorno;
	}
	
  /**
   * Establece el ID de la sede donde se recogerá el vehículo.
   *
   * @param IdSedeRecoger El nuevo ID de la sede de recogida.
   */
	public void setIdSedeRecoger(String IdSedeRecoger) {
		this.IdSedeRecoger = IdSedeRecoger;
	}
	
  /**
   * Establece el ID de la sede donde se devolverá el vehículo.
   *
   * @param IdSedeDevolver El nuevo ID de la sede de devolución.
   */
	public void setIdSedeDevolver(String IdSedeDevolver) {
		this.IdSedeDevolver = IdSedeDevolver;
	}
}