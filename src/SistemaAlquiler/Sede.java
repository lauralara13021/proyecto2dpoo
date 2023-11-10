package SistemaAlquiler;
import java.util.ArrayList;
import java.time.*;



public class Sede {
    private String nombre;
    private String ubicacion;
    private LocalTime InicioHorarioAtencion;
    private LocalTime FinalHorarioAtencion;
    private ArrayList<String> empleados;
    private ArrayList<Vehiculo> vehiculos;
    private String AdministradorLocal;

    /**
     * Constructor de la clase Sede para crear una nueva sede de la empresa de alquiler de vehículos.
     *
     * @param nombre             El nombre de la sede.
     * @param ubicacion          La ubicación de la sede.
     * @param InicioHorarioAtencion La hora de inicio del horario de atención de la sede.
     * @param FinalHorarioAtencion  La hora de finalización del horario de atención de la sede.
     * @param AdministradorLocal    El nombre del administrador local de la sede.
     */
    public Sede(String nombre, String ubicacion, LocalTime InicioHorarioAtencion, LocalTime FinalHorarioAtencion, String AdministradorLocal) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.InicioHorarioAtencion = InicioHorarioAtencion;
        this.FinalHorarioAtencion = FinalHorarioAtencion;
        this.empleados = new ArrayList<String>();
        this.vehiculos = new ArrayList<Vehiculo>();
        this.AdministradorLocal = AdministradorLocal;
    }

    /**
     * Obtiene el nombre de la sede.
     *
     * @return El nombre de la sede.
     */
    public String getNombre()
    {
    	return this.nombre;
    }
    
    /**
     * Obtiene la ubicación de la sede.
     *
     * @return La ubicación de la sede.
     */
    public String getUbicacion() {
    	return this.ubicacion;
    }
    
    /**
     * Obtiene la hora de inicio del horario de atención de la sede.
     *
     * @return La hora de inicio del horario de atención.
     */
    public LocalTime getInicioHorarioAtencion(){
    	return this.InicioHorarioAtencion;
    }
    
    /**
     * Obtiene la hora de finalización del horario de atención de la sede.
     *
     * @return La hora de finalización del horario de atención.
     */
    public LocalTime getFinalHorarioAtencion() {
    	return FinalHorarioAtencion;
    }
    
    /**
     * Obtiene una lista de empleados asignados a la sede.
     *
     * @return Una lista de empleados.
     */
    public ArrayList<String> getEmpleados(){
    	return this.empleados;
    }
    
    /**
     * Obtiene una lista de vehículos asociados a la sede.
     *
     * @return Una lista de vehículos.
     */
    public ArrayList<Vehiculo> getVehiculos(){
    	return this.vehiculos;
    }
    
    /**
     * Obtiene el nombre del administrador local de la sede.
     *
     * @return El nombre del administrador local.
     */
    public String getAdministradorLocal() {
    	return this.AdministradorLocal;
    }
    
    /**
     * Agrega un empleado a la lista de empleados de la sede.
     *
     * @param empleado El empleado que se va a agregar.
     */
    public void agregarEmpleado(Empleado empleado) {
    	empleados.add(empleado.getLogin());
    }
    
    /**
     * Agrega un vehículo a la lista de vehículos asociados a la sede.
     *
     * @param vehiculo El vehículo que se va a agregar.
     */
    public void agregarVehiculo(Vehiculo vehiculo) {
    	vehiculos.add(vehiculo);
    }
    
    /**
     * Elimina un vehículo de la lista de vehículos asociados a la sede.
     *
     * @param vehiculo El vehículo que se va a eliminar.
     */
    public void eliminarVehiculo(Vehiculo vehiculo) {
    	vehiculos.remove(vehiculo);
    }
    
    /**
     * Establece la ubicación de la sede.
     *
     * @param ubicacion La nueva ubicación de la sede.
     */
    public void setUbicacion(String ubicacion) {
    	this.ubicacion = ubicacion;
    }
    
    /**
     * Establece la hora de inicio del horario de atención de la sede.
     *
     * @param hora La nueva hora de inicio del horario de atención.
     */
    public void setInicioHorarioAtencion(LocalTime hora) {
    	this.InicioHorarioAtencion = hora;
    }
    
    /**
     * Establece la hora de finalización del horario de atención de la sede.
     *
     * @param hora La nueva hora de finalización del horario de atención.
     */
    public void setFinalHorarioAtencion(LocalTime hora) {
    	this.FinalHorarioAtencion = hora;
    }
    
}
