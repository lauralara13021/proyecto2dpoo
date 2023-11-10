package SistemaAlquiler;

public class Seguro {
    private String nombre;
    private double costoPorDia;

    /**
     * Constructor para crear un nuevo seguro con un nombre y costo por día específicos.
     *
     * @param nombre       El nombre del seguro.
     * @param costoPorDia  El costo del seguro por día.
     */
    public Seguro(String nombre, double costoPorDia) {
        this.nombre = nombre;
        this.costoPorDia = costoPorDia;
    }

    /**
     * Obtiene el nombre del seguro.
     *
     * @return El nombre del seguro.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el costo del seguro por día.
     *
     * @return El costo del seguro por día.
     */
    public double getCostoPorDia() {
        return costoPorDia;
    }
    
    /**
     * Establece un nuevo costo del seguro por día.
     *
     * @param costoPorDia El nuevo costo del seguro por día.
     */
    public void SetCostoPorDia(double costoPorDia) {
    	this.costoPorDia = costoPorDia;
    }
}

