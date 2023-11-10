package SistemaAlquiler;


public class Cliente extends Usuario {
	private String telefono;
    private String name;
    private String correo;
    private Licencia licencia;
    private MedioPago medioPago;

    /**
     * Crea una nueva instancia de Cliente con los parámetros especificados.
     *
     * @param telefono El número de teléfono del cliente.
     * @param name El nombre del cliente.
     * @param correo La dirección de correo electrónico del cliente.
     * @param licencia La información de la licencia del cliente.
     * @param medioPago La información del medio de pago del cliente.
     * @param login El nombre de inicio de sesión del cliente.
     * @param contrasena La contraseña del cliente.
     */
    public Cliente(String telefono, String name, String correo, Licencia licencia, MedioPago medioPago, String login, String contrasena) {
    	super(login, contrasena);
    	this.telefono = telefono;
        this.correo = correo;
        this.licencia = licencia;
        this.name = name;
        this.medioPago = medioPago;
    }
    
    
    //Getters
    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Obtiene la dirección de correo electrónico del cliente.
     *
     * @return La dirección de correo electrónico del cliente.
     */
    public String getCorreo() {
        return this.correo;
    }
    
    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return El número de teléfono del cliente.
     */
    public String getTelefono() {
        return this.telefono;
    }
    
    /**
     * Obtiene la información de la licencia del cliente.
     *
     * @return La información de la licencia del cliente.
     */
    public Licencia getlicencia() {
    	return this.licencia;
    }
    
    /**
     * Obtiene la información del medio de pago del cliente.
     *
     * @return La información del medio de pago del cliente.
     */
    public MedioPago getMedioPago() {
    	return medioPago;
    }
    
    
    //Setters
    /**
     * Establece el nombre del cliente.
     *
     * @param name El nuevo nombre del cliente.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Establece la dirección de correo electrónico del cliente.
     *
     * @param correo La nueva dirección de correo electrónico del cliente.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Establece el número de teléfono del cliente.
     *
     * @param telefono El nuevo número de teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
   
    /**
     * Establece la información de la licencia del cliente.
     *
     * @param licencia La nueva información de la licencia del cliente.
     */
   public void setLicencia(Licencia licencia) {
	   this.licencia = licencia;
   }
    
   /**
    * Establece la información del medio de pago del cliente.
    *
    * @param medioPago La nueva información del medio de pago del cliente.
    */
   
    public void setNumTarjeta(MedioPago medioPago) {
    	this.medioPago = medioPago;
    }
}