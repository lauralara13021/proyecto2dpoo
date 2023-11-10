package SistemaAlquiler;

public class Conductor {
	String Nombre;
	 String telefono;
	 String correo;
	 Licencia licencia;
	 
	 /**
	     * Constructor de la clase Conductor.
	     *
	     * @param Nombre    El nombre del conductor.
	     * @param telefono  El número de teléfono del conductor.
	     * @param correo    El correo electrónico del conductor.
	     * @param licencia  La licencia de conducir del conductor.
	 */
	 public Conductor(String Nombre, String telefono, String correo, Licencia licencia) {
		 this.Nombre = Nombre;
		 this.telefono = telefono;
		 this.correo = correo;
		 this.licencia = licencia;
	 }
	
	//getters
	 /**
	     * Obtiene el nombre del conductor.
	     *
	     * @return El nombre del conductor.
	 */	   
	 public String getNombre() {
		 return this.Nombre;
	 }
	 
	  /**
	     * Obtiene el número de teléfono del conductor.
	     *
	     * @return El número de teléfono del conductor.
	  */
	 public String getTelefono() {
		 return this.telefono;
	 }
	 
	 /**
	     * Obtiene el correo electrónico del conductor.
	     *
	     * @return El correo electrónico del conductor.
	 */	 
	 public String getCorreo() {
		 return this.correo;
	 }
	 
	    /**
	     * Obtiene la licencia de conducir del conductor.
	     *
	     * @return La licencia de conducir del conductor.
	   */
	 public Licencia getLicencia() {
		 return this.licencia;
	 }
}
