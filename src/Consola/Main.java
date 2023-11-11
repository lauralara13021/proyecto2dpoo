package Consola;


import Inventario.Inventario;


public class Main {
	
	/**
	 * Método principal (main) de la aplicación que inicia la ejecución del programa.
	 *
	 * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
	 */
	public static void main(String[] args) {
        Main main = new Main();
        main.Ejecutar();
    }
	
	
	/**
	 * Método que inicia la aplicación al crear una instancia de la clase 'Inventario' y ejecutar su método 'aplicacion'.
	 */
	public void Ejecutar() {
		Inventario inventario = new Inventario();
		inventario.aplicacion();
	}


}