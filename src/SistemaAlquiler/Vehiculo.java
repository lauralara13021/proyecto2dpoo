package SistemaAlquiler;
import java.util.ArrayList;
import java.util.Date;

import SistemaAlquiler.Vehiculo;



public class Vehiculo {
	private String vehiculoID;
    private String marca;
    private String modelo;
    private String color;
    private String tipoTransmision;
    private int capacidad;
    private String Categoria;
    private ArrayList<AgendaCarro> AgendaVehiculo;
    private String ubicacion;

    /**
     * Constructor para crear un nuevo vehículo con información básica.
     *
     * @param vehiculoID      El identificador único del vehículo.
     * @param marca           La marca del vehículo.
     * @param modelo          El modelo del vehículo.
     * @param Categoria       La categoría del vehículo.
     * @param color           El color del vehículo.
     * @param tipoTransmision El tipo de transmisión del vehículo.
     * @param capacidad       La capacidad del vehículo (número de pasajeros).
     * @param ubicacion       La ubicación actual del vehículo.
     */
    public Vehiculo(String vehiculoID, String marca, String modelo, String Categoria, String color, String tipoTransmision, int capacidad, String ubicacion ) {
        this.vehiculoID = vehiculoID;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.tipoTransmision = tipoTransmision;
        this.color = color;
        this.Categoria = Categoria;
        this.AgendaVehiculo = new ArrayList<AgendaCarro>();
        this.ubicacion = ubicacion;
    }
    
    //Getters
    /**
     * Obtiene el ID del vehículo.
     *
     * @return El ID del vehículo.
     */
    public String getVehiculoId() {
        return vehiculoID;
    }
    
    /**
     * Obtiene la marca del vehículo.
     *
     * @return La marca del vehículo.
     */
    public String getmarca() {
        return marca;
    }

    /**
     * Obtiene el modelo del vehículo.
     *
     * @return El modelo del vehículo.
     */
    public String getmodelo() {
        return modelo;
    }
    
    /**
     * Obtiene la capacidad del vehículo.
     *
     * @return La capacidad del vehículo.
     */
    public int getCapacidad() {
    	return capacidad;
    }
   
    /**
     * Obtiene el tipo de transmisión del vehículo.
     *
     * @return El tipo de transmisión del vehículo.
     */
    public String getTipoTransmision() {
    	return tipoTransmision;
    }
    
    /**
     * Obtiene el color del vehículo.
     *
     * @return El color del vehículo.
     */
    public String getColor() {
    	return color;
    }
    
    /**
     * Obtiene la categoría del vehículo.
     *
     * @return La categoría del vehículo.
     */
    public String getCategoria() {
    	return Categoria;
    }
    
    /**
     * Obtiene la agenda de tareas programadas para el vehículo.
     *
     * @return La lista de agendas de tareas para el vehículo.
     */
    public ArrayList<AgendaCarro> getAgendaVehiculo() {
    	return AgendaVehiculo;
    }
    
    /**
     * Obtiene la ubicación actual del vehículo.
     *
     * @return La ubicación actual del vehículo.
     */
    public String getUbicacion() {
    	return this.ubicacion;
    }

    /**
     * Establece la ubicación actual del vehículo.
     *
     * @param ubicacion La nueva ubicación del vehículo.
     */
    public void setUbicacion(String ubicacion) {
    	this.ubicacion = ubicacion;
    }

    /**
     * Reserva el vehículo para un rango de fechas.
     *
     * @param FechaInicio La fecha de inicio de la reserva.
     * @param FechaFinal  La fecha de finalización de la reserva.
     * @return La agenda de reserva para el vehículo.
     */
    public AgendaCarro reservar(Date FechaInicio, Date FechaFinal) {
    	AgendaCarro rentado = new AgendaCarro(FechaInicio, FechaFinal, "Reservado");
    	this.AgendaVehiculo.add(rentado);
    	
    	return rentado;
    }
    
    /**
     * Reserva el vehículo para una tarea de limpieza.
     *
     * @param FechaInicio La fecha de inicio de la tarea de limpieza.
     * @param FechaFinal  La fecha de finalización de la tarea de limpieza.
     * @return La agenda de tarea de limpieza para el vehículo.
     */
    public AgendaCarro entregar(Date FechaInicio, Date FechaFinal) {
    	AgendaCarro enLimpieza = new AgendaCarro(FechaInicio, FechaFinal, "Limpieza");
    	this.AgendaVehiculo.add(enLimpieza);
    	
    	return enLimpieza;
    }
    
    /**
     * Reserva el vehículo para una tarea de mantenimiento.
     *
     * @param FechaInicio La fecha de inicio de la tarea de mantenimiento.
     * @param FechaFinal  La fecha de finalización de la tarea de mantenimiento.
     * @return La agenda de tarea de mantenimiento para el vehículo.
     */
    public AgendaCarro mantenimiento(Date FechaInicio, Date FechaFinal) {
    	AgendaCarro enMantenimiento = new AgendaCarro(FechaInicio, FechaFinal, "Mantenimiento");
    	this.AgendaVehiculo.add(enMantenimiento);
    	
    	return enMantenimiento;
    }
    
    /**
     * Elimina un rango de indisponibilidad del vehículo en la agenda.
     *
     * @param RangoIndisponibilidad El rango de indisponibilidad a eliminar de la agenda.
     */
    public void EliminarIndisponibilidad(AgendaCarro RangoIndisponibilidad) {
    	this.AgendaVehiculo.remove(RangoIndisponibilidad);
    }
    
    /**
     * Agrega un rango de indisponibilidad al vehículo en la agenda.
     *
     * @param RangoIndisponibilidad El rango de indisponibilidad a agregar a la agenda.
     */
    public void AñadirIndisponibilidad(AgendaCarro RangoIndisponibilidad) {
    	this.AgendaVehiculo.add(RangoIndisponibilidad);
    }
    
    /**
     * Valida la disponibilidad del vehículo durante un rango de fechas.
     *
     * @param FechaInicio La fecha de inicio del rango a verificar.
     * @param FechaFinal  La fecha de finalización del rango a verificar.
     * @return `true` si el vehículo está disponible durante el rango, `false` en caso contrario.
     */
    public boolean ValidarDisponibilidad(Date FechaInicio, Date FechaFinal) {
    	
    	boolean disponibilidad = true;
    	if(!this.AgendaVehiculo.isEmpty()) {
    		for (AgendaCarro Indisponibilidad : this.AgendaVehiculo) {
        		if (FechaInicio.before(Indisponibilidad.getFechaInicio()) && FechaFinal.before(Indisponibilidad.getFechaInicio())) {
        			if (FechaInicio.after(Indisponibilidad.getFechaFinal()) && FechaFinal.after(Indisponibilidad.getFechaFinal())) {
        				
        				disponibilidad = true;
        			}
        			else {
        				
        				disponibilidad  = false;
        			}		
        		}
        		else {
        			
        			disponibilidad = false;
        		}
        	}
    	}
    	
    	
    	return disponibilidad;
    }
    
    
}