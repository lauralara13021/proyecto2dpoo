package Inventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import SistemaAlquiler.Vehiculo;
import SistemaAlquiler.VehiculoRentalSystem;
import SistemaAlquiler.AgendaCarro;
import SistemaAlquiler.Categoria;
import SistemaAlquiler.Cliente;
import SistemaAlquiler.Conductor;
import SistemaAlquiler.Empleado;
import SistemaAlquiler.Licencia;
import SistemaAlquiler.MedioPago;
import SistemaAlquiler.Reserva;
import SistemaAlquiler.Sede;
import SistemaAlquiler.Seguro;

public class Inventario {
	
	/**
	 * Carga la información de sedes desde un archivo de texto y las agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "Sedes" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Las sedes válidas en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán las sedes.
	 * @throws IOException Si ocurre un error al leer el archivo de sedes.
	 */
	public void CargarSedes(VehiculoRentalSystem rentalSystem) {
        try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Sedes"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] SedeInfo = line.split(",");
                if (SedeInfo.length == 5) {
                    // Asegúrate de que haya suficientes elementos en la línea
                	LocalTime startHour = LocalTime.parse(SedeInfo[2]);
                	LocalTime endHour = LocalTime.parse(SedeInfo[2]);
                	Sede sede = new Sede(SedeInfo[0], SedeInfo[1], startHour, endHour, SedeInfo[4]);
                    rentalSystem.addSede(sede);
                	
                } else {
                    // No hay suficientes elementos en la línea del archivo de carros, muestra un mensaje de advertencia
                    System.out.println("Advertencia: línea incorrecta en el archivo de carros: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de carros desde un archivo de texto y los agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "carrosDatos" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Los carros válidos en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán los carros.
	 * @throws IOException Si ocurre un error al leer el archivo de carros.
	 */
	public void CargarCarros(VehiculoRentalSystem rentalSystem) {
        try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/carrosDatos"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] carInfo = line.split(",");
                if (carInfo.length == 8) {
                    // Asegúrate de que haya suficientes elementos en la línea
                    
                	Vehiculo car = new Vehiculo(carInfo[0], carInfo[1], carInfo[2], carInfo[3], carInfo[4], carInfo[5], Integer.parseInt(carInfo[6]), carInfo[7]);
                    rentalSystem.addVehiculo(car);
                    rentalSystem.CargaCarrosASede(car);
                } else {
                    // No hay suficientes elementos en la línea del archivo de carros, muestra un mensaje de advertencia
                    System.out.println("Advertencia: línea incorrecta en el archivo de carros: " + line);
                    System.out.println(line);
                }	
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de categorías desde un archivo de texto y las agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "Categorias" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Las categorías válidas en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán las categorías.
	 * @throws IOException Si ocurre un error al leer el archivo de categorías.
	 */
	public void CargarCategorias(VehiculoRentalSystem rentalSystem) {
        try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Categorias"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] CategoriaInfo = line.split(",");
                if (CategoriaInfo.length == 5) {
                    // Asegúrate de que haya suficientes elementos en la línea
                    
                	
                    Categoria categoria = new Categoria(CategoriaInfo[0], Integer.parseInt(CategoriaInfo[1]), Integer.parseInt(CategoriaInfo[2]), Integer.parseInt(CategoriaInfo[3]), Integer.parseInt(CategoriaInfo[4]));
                    rentalSystem.addCategorias(categoria);
                	
                	
                } else {
                    // No hay suficientes elementos en la línea del archivo de carros, muestra un mensaje de advertencia
                    System.out.println("Advertencia: línea incorrecta en el archivo de categorias: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de empleados desde un archivo de texto y los agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "Empleados" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Los empleados válidos en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán los empleados.
	 * @throws IOException Si ocurre un error al leer el archivo de empleados.
	 */
	public void CargarEmpleados(VehiculoRentalSystem rentalSystem) {
        try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Empleados"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] empleadoInfo = line.split(",");
                Empleado empleado = new Empleado(empleadoInfo[0], empleadoInfo[1], empleadoInfo[2]);
                rentalSystem.addEmpleado(empleado);
            	rentalSystem.CargaEmpleadosASede(empleado);
         
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de reservas desde un archivo de texto y las agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "Reservas" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Las reservas válidas en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán las reservas.
	 * @throws IOException Si ocurre un error al leer el archivo de reservas.
	 */
	public void CargarReservas(VehiculoRentalSystem rentalSystem) {
        try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Reservas"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] infoReserva = line.split(",");
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                	Date fechaEntrega = dateFormat.parse(infoReserva[3]);
                	Date fechaRetorno = dateFormat.parse(infoReserva[4]);
                    
                	Reserva reserva = new Reserva(infoReserva[0], fechaEntrega, fechaRetorno, infoReserva[5], infoReserva[6], infoReserva[1], infoReserva[2], infoReserva[9]);
                	reserva.setPrecio(Double.parseDouble(infoReserva[7]));
                	reserva.setPrecioAbonado(Double.parseDouble(infoReserva[8]));
                    
                    rentalSystem.addReserva(reserva);
                }catch(ParseException e) {
             	   System.out.println("Invalid date format");
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de clientes desde un archivo de texto y los agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "Clientes" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Los clientes válidos en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán los clientes.
	 * @throws IOException Si ocurre un error al leer el archivo de clientes.
	 */
	public void CargarClientes(VehiculoRentalSystem rentalSystem) {
		try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Clientes"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] clienteInfo = line.split(",");
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date fechaExpedicion = dateFormat.parse(clienteInfo[5]);
                    Date fechaVencimiento = dateFormat.parse(clienteInfo[8]);
                    
                    Licencia licencia = new Licencia(clienteInfo[3], clienteInfo[4], fechaExpedicion);
                    MedioPago medioPago = new MedioPago(clienteInfo[6], clienteInfo[7], fechaVencimiento);
                    Cliente cliente = new Cliente(clienteInfo[0], clienteInfo[1], clienteInfo[2], licencia, medioPago, clienteInfo[9], clienteInfo[10]);
                    
                    rentalSystem.addCliente(cliente);
               } catch (ParseException e) {
            	   System.out.println("Invalid date format");
               }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de seguros desde un archivo de texto y los agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "Seguros" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Los seguros válidos en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán los seguros.
	 * @throws IOException Si ocurre un error al leer el archivo de seguros.
	 */
	public void CargarSeguros(VehiculoRentalSystem rentalSystem) {
		try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Seguros"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] seguroInfo = line.split(",");
                if (seguroInfo.length == 2) {
                    // Asegúrate de que haya suficientes elementos en la línea
                    
                    Seguro seguro = new Seguro(seguroInfo[0], Double.parseDouble(seguroInfo[1]));
                    rentalSystem.addSeguro(seguro);
                	
                	
                } else {
                    // No hay suficientes elementos en la línea del archivo de carros, muestra un mensaje de advertencia
                    System.out.println("Advertencia: línea incorrecta en el archivo de seguros: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de usuarios y contraseñas desde un archivo de texto y los agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "usuariosYcontraseñas" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Los usuarios y contraseñas válidos en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán los usuarios y contraseñas.
	 * @throws IOException Si ocurre un error al leer el archivo de usuarios y contraseñas.
	 */
	public void CargarUsuarioContrasenas(VehiculoRentalSystem rentalSystem) {
		try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/usuariosYcontraseñas"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] UyCinfo = line.split(",");
                rentalSystem.addUsuarioYContraseña(UyCinfo[0], UyCinfo[1]);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de conductores desde un archivo de texto y los agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "Conductores" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Los conductores válidos en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán los conductores.
	 * @throws IOException Si ocurre un error al leer el archivo de conductores.
	 */
	public void CargarConductores(VehiculoRentalSystem rentalSystem) {
        try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Conductores"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] conductorInfo = line.split(",");
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                	Date date = dateFormat.parse(conductorInfo[5]);
                    
                    Licencia licencia = new Licencia(conductorInfo[3], conductorInfo[4], date);
                    
                    Conductor nuevoConductor = new Conductor(conductorInfo[0], conductorInfo[1], conductorInfo[2], licencia);
                    rentalSystem.addConductor(nuevoConductor);
                }catch(ParseException e) {
             	   System.out.println("Invalid date format");
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de agendas de carros desde un archivo de texto y las agrega al sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "AgendasCarros" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Las agendas de carros válidas en el archivo se agregan al sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán las agendas de carros.
	 * @throws IOException Si ocurre un error al leer el archivo de agendas de carros.
	 */
	public void CargarAgendasCarros(VehiculoRentalSystem rentalSystem) {
        try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/AgendasCarros"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] agendaInfo = line.split(",");
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
                    Date fechaInicio = dateFormat.parse(agendaInfo[1]);
                    Date fechaFinal = dateFormat.parse(agendaInfo[2]);
                    AgendaCarro agendaCarro = new AgendaCarro(fechaInicio, fechaFinal, agendaInfo[3]);
                    rentalSystem.addAgendasCarros(agendaInfo[0], agendaCarro);
                    rentalSystem.CargaAgendaACarros(agendaInfo[0], agendaCarro);
                    
            	}catch (ParseException e) {
	            	   System.out.println("Invalid date format");
	               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Carga la información de seguros de reservas desde un archivo de texto y las asocia a las reservas en el sistema de alquiler de vehículos.<br>
	 * <b>Pre:</b> El archivo "SegurosReserva" debe existir y contener información válida en el formato especificado.<br>
	 * <b>Post:</b> Los seguros de reservas válidos en el archivo se asocian a las reservas correspondientes en el sistema de alquiler especificado.
	 *
	 * @param rentalSystem El sistema de alquiler al que se agregarán los seguros de reservas.
	 * @throws IOException Si ocurre un error al leer el archivo de seguros de reservas.
	 */
	public void CargarSegurosReservas(VehiculoRentalSystem rentalSystem) {
		try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/SegurosReserva"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] seguroInfo = line.split(",");
                
                rentalSystem.addSegurosReserva(seguroInfo[0], seguroInfo[1]);
                rentalSystem.CargarSegurosAReserva(seguroInfo[0], seguroInfo[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Aplicación principal que carga toda la información necesaria al sistema de alquiler y muestra el menú principal.
	 * @throws IOException Si ocurre un error en cualquiera de las operaciones de carga de datos.
	 */
	public void aplicacion() {
		VehiculoRentalSystem rentalSystem = new VehiculoRentalSystem();
		
		CargarCategorias(rentalSystem);
		CargarSedes(rentalSystem);
		CargarSeguros(rentalSystem);
		CargarClientes(rentalSystem);
		CargarEmpleados(rentalSystem);
		CargarConductores(rentalSystem);
		CargarReservas(rentalSystem);
		CargarCarros(rentalSystem);
		CargarAgendasCarros(rentalSystem);
		CargarSegurosReservas(rentalSystem);
		CargarUsuarioContrasenas(rentalSystem);
		
		
		rentalSystem.mostrarMenu();
		
	}
}

