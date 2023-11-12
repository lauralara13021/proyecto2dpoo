package SistemaAlquiler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.lang.Math;




public class VehiculoRentalSystem extends JFrame{
	private static final long serialVersionUID = 1L;
	public List<Vehiculo> cars;
    public List<Reserva> reservas;
    private HashMap<String, Categoria> categorias;
    private HashMap<String,Cliente> clientes;
    public List<Seguro> segurosDisponibles;
    private Map<String, String> usuariosYContraseñas;
    private List<Conductor> conductores;
    public Map<String, Sede> sedes;
    private List<Empleado> empleados;
    private Map<String, List<AgendaCarro>> agendasCarros;
    private Map<String, List<String>> segurosReserva;
    private static VehiculoRentalSystem instance;
    private List<Categoria>precioCategoria;
    
    /**
     * Constructor de la clase VehiculoRentalSystem. Inicializa las listas y mapas necesarios.
     */
    public VehiculoRentalSystem() {
        cars = new ArrayList<>();
        reservas = new ArrayList<>();
        categorias = new HashMap<>();
        clientes = new HashMap<>();
        segurosDisponibles = new ArrayList<>();
        usuariosYContraseñas =  new HashMap<>();
        conductores = new ArrayList<>();
        sedes = new HashMap<>();
        empleados = new ArrayList<>();
        agendasCarros = new HashMap<>();
        segurosReserva = new HashMap<>();
        precioCategoria = new ArrayList<>();
    }
    
    public static VehiculoRentalSystem getInstance() {
        if (instance == null) {
            instance = new VehiculoRentalSystem();
        }
        return instance;
    }
    
    
    
    //Vehiculo
    /**
     * Agrega un vehículo al sistema.
     *
     * @param car El vehículo a agregar.
     */
    public void addVehiculo(Vehiculo car) {
        cars.add(car);
    }
    
    /**
     * Escribe la información de un vehículo en el archivo de inventario.
     *
     * @param car El vehículo que se va a escribir en el archivo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void escribirVehiculo(Vehiculo car) {
    	String rutaArchivo = "InventarioDatos/carrosDatos";
        String contenido = "\n" + car.getVehiculoId() + "," + car.getmarca() + "," + car.getmodelo() + "," 
    	+ car.getCategoria( )+ "," + car.getColor() + "," + car.getTipoTransmision() + "," + car.getCapacidad() 
    	+ "," + car.getUbicacion();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
        	writer.write(contenido);  
        } catch (IOException e) {
        	System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Elimina un vehículo al sistema.
     *
     * @param car El vehículo a eliminar.
     * @return 
     */
    public void removeVehiculo(Vehiculo vehiculo) {
    	cars.remove(vehiculo);
    }
    
    /**
     * Elimina la información de un vehículo del archivo de inventario.
     *
     * @param vehiculo El vehículo que se va a eliminar del archivo.
     * @throws IOException Si ocurre un error al eliminar la información del vehículo.
     */
    public void eliminarVehiculo(Vehiculo vehiculo) {
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/carrosDatos"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] carInfo = line.split(",");
                if (vehiculo.getVehiculoId().equals(carInfo[0])) {
                }else {
                	input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/carrosDatos");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Cliente
    /**
     * Agrega un cliente al sistema.
     *
     * @param cliente El cliente a agregar.
     */
    public void addCliente(Cliente cliente) {
        clientes.put(cliente.getName(), cliente);
    }
    
    /**
     * Escribe la información de un cliente en el archivo de clientes.
     *
     * @param cliente El cliente que se va a escribir en el archivo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void escribirCliente(Cliente cliente) {
    	
    	String rutaArchivo = "InventarioDatos/Clientes";
    	SimpleDateFormat fechaVencimiento = new SimpleDateFormat("dd/MM/yyyy");
    	String vencimientoLicencia = fechaVencimiento.format(cliente.getlicencia().getFechaVencimineto());
    	String vencimientoTarjeta = fechaVencimiento.format(cliente.getMedioPago().getFechaVencimiento());
    	
        String contenido = cliente.getTelefono() + "," + cliente.getName()+ "," + cliente.getCorreo() 
        + "," + cliente.getlicencia().getNumero()+ "," + cliente.getlicencia().getPaisExpedicion() + "," 
        + vencimientoLicencia + "," + cliente.getMedioPago().getNumero() + "," 
        + cliente.getMedioPago().getTipo() + "," + vencimientoTarjeta + "," 
        + cliente.getLogin() + "," + cliente.getContrasena() + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
        	writer.write(contenido);  
        } catch (IOException e) {
        	System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }

    //categorias
    /**
     * Agrega una categoria al sistema.
     *
     * @param categoria La categoría a agregar.
     */
    public void addCategorias(Categoria categoria) {
    	categorias.put(categoria.getNombre(), categoria);
    }

    //Seguro
    /**
     * Agrega un seguro al sistema.
     *
     * @param seguro El seguro a agregar.
     */
    public void addSeguro(Seguro seguro) {
    	segurosDisponibles.add(seguro);
    }
    
    /**
     * Modifica el precio de un seguro en el archivo de seguros.
     *
     * @param seguro      El seguro que se va a modificar.
     * @param nuevoPrecio El nuevo precio del seguro.
     * @throws IOException Si ocurre un error al modificar el seguro en el archivo.
     */
    public void modificarSeguro(Seguro seguro, double nuevoPrecio) {
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Seguros"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] seguroInfo = line.split(",");
                if (seguro.getNombre().equals(seguroInfo[0])) {
                	input += line.replaceAll(seguroInfo[1], String.valueOf(nuevoPrecio))+"\n";
                }else {
                    input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/Seguros");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    public void modificarPrecioCategoria(Categoria categoria, double nuevoPrecio) {
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Categorias"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] seguroInfo = line.split(",");
                if (categoria.getNombre().equals(seguroInfo[0])) {
                	input += line.replaceAll(seguroInfo[1], String.valueOf(nuevoPrecio))+"\n";
                }else {
                    input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/Categorias");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    //Sede
    /**
     * Agrega una sede al sistema.
     *
     * @param sede La sede a agregar.
     */
    public void addSede(Sede sede) {
    	sedes.put(sede.getNombre(), sede);
    }
    
    /**
     * Modifica el horario de atención de una sede en el archivo de sedes.
     *
     * @param sede         La sede cuyo horario se va a modificar.
     * @param hora         La nueva hora de inicio o final de atención.
     * @param finalOinicial Indica si se va a modificar el horario final o inicial.
     * @throws IOException Si ocurre un error al modificar el horario de la sede en el archivo.
     */
    public void modificarHorarioSede(Sede sede, LocalTime hora, String finalOinicial) {
    	int posInfo;
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Sedes"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] sedeInfo = line.split(",");
                if (finalOinicial.equals("final")) {
                	posInfo = 3;
    
                }else {
                	posInfo = 2;
                }
                
                if (sede.getNombre().equals(sedeInfo[0])) {
                	String horaStr = formatter.format(hora);
                	input += line.replaceAll(sedeInfo[posInfo], horaStr )+"\n";
                }else {
                    input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/Sedes");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Modifica la dirección de una sede en el archivo de sedes.
     *
     * @param sede      La sede cuya dirección se va a modificar.
     * @param direccion La nueva dirección de la sede.
     * @throws IOException Si ocurre un error al modificar la dirección de la sede en el archivo.
     */
    public void modificarDireccionSede(Sede sede, String direccion) {
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Sedes"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] sedeInfo = line.split(",");
                if (sede.getNombre().equals(sedeInfo[0])) {
                	input += line.replaceAll(sedeInfo[1], direccion)+"\n";
                }else {
                    input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/Sedes");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void modificarPreciosCategoria(String nombreCategoria, int nuevaTarifaTempAlta, int nuevaTarifaTempBaja, int nuevaTarifaConductor,int nuevoPrecioSede) {
        List<Categoria> categorias = obtenerCategoriasDesdeArchivo();

        // Buscar la categoría por nombre
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                // Actualizar los precios
                categoria.setTarifaTempAlta(nuevaTarifaTempAlta);
                categoria.setTarifaTempBaja(nuevaTarifaTempBaja);
                categoria.setValorAdicionalConductor(nuevaTarifaConductor);
                categoria.setValorSedeDiferente(nuevoPrecioSede);

                // Guardar los cambios en el archivo de texto
                guardarCategoriasEnArchivo(categorias);
                return;
            }
        }

        // Si llegamos aquí, la categoría no se encontró
        System.out.println("Categoría no encontrada.");
    }

    private void guardarCategoriasEnArchivo(List<Categoria> categorias) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("InventarioDatos/Categorias"))) {
            for (Categoria categoria : categorias) {
                writer.println(categoria.toStringParaArchivo());
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }
    private List<Categoria> obtenerCategoriasDesdeArchivo() {
        List<Categoria> categorias = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Categorias"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    String nombre = partes[0];
                    int tarifaTempAlta = Integer.parseInt(partes[1]);
                    int tarifaTempBaja = Integer.parseInt(partes[2]);
                    int valorAdicionalConductor = Integer.parseInt(partes[3]);
                    int valorSedeDiferente = Integer.parseInt(partes[4]);

                    Categoria categoria = new Categoria(nombre, tarifaTempAlta, tarifaTempBaja, valorAdicionalConductor, valorSedeDiferente);
                    categorias.add(categoria);
                } else {
                    System.out.println("Formato incorrecto en línea: " + linea);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }

        return categorias;
    }

    
    //Empleado
    /**
     * Agrega un empleado al sistema.
     *
     * @param empleado El empleado a agregar.
     */
    public void addEmpleado(Empleado empleado) {
    	empleados.add(empleado);
    }
    
    /**
     * Escribe la información de un empleado en el archivo de empleados.
     *
     * @param empleado El empleado que se va a escribir en el archivo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void escribirEmpleado(Empleado empleado) {
    	String rutaArchivo = "InventarioDatos/Empleados";
        String contenido = "\n" + empleado.getSede() + "," + empleado.getLogin()+ "," + empleado.getContrasena();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
        	writer.write(contenido); 
        } catch (IOException e) {
        	System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    
    //usuarioYcontraseña
    /**
     * Agrega un par de usuario y contraseña al mapa de usuarios y contraseñas.
     *
     * @param login      El nombre de usuario.
     * @param contrasena La contraseña asociada al usuario.
     */
    public  void addUsuarioYContraseña(String login, String contrasena) {
    	usuariosYContraseñas.put(login, contrasena);
	}
    
    /**
     * Escribe un par de usuario y contraseña en el archivo de usuarios y contraseñas.
     *
     * @param login      El nombre de usuario.
     * @param contrasena La contraseña asociada al usuario.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void escribirUsuarioContrasena(String login, String contrasena) {
    	String rutaArchivo = "InventarioDatos/usuariosYcontraseñas";
        String contenido = login + "," + contrasena + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
        	writer.write(contenido);  // Agrega una nueva línea al final
        } catch (IOException e) {
        	System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    //Reserva
    /**
     * Agrega una reserva al sistema.
     *
     * @param reserva La reseva a agregar.
     */
    public void addReserva(Reserva reserva) {
    	reservas.add(reserva);
    }
    
    /**
     * Escribe la información de una reserva en el archivo de reservas.
     *
     * @param reserva La reserva que se va a escribir en el archivo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void escribirReserva(Reserva reserva) {
    	String rutaArchivo = "InventarioDatos/Reservas";
    	
    	SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
    	String fechaEntrega = fecha.format(reserva.getFechaEntrega());
    	String fechaRetorno = fecha.format(reserva.getFechaRetorno());
    	
        String contenido = reserva.getCategoria() + "," + reserva.getIdSedeRecoger()+ "," 
    	+ reserva.getIdSedeDevolver() + "," + fechaEntrega + "," + fechaRetorno 
    	+ "," + reserva.getCliente() + "," + reserva.getIdCarro() + "," + reserva.getPrecioBase() + "," 
    	+ reserva.getPrecioAbonado() + "," + reserva.getEstado() + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
        	writer.write(contenido);  
        } catch (IOException e) {
        	System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    
    /**
     * Modifica la sede de entrega o retorno en el archivo de reservas.
     *
     * @param reserva       La reserva cuya sede se va a modificar.
     * @param sede          La nueva sede de entrega o retorno.
     * @param entregaOretorno Indica si se va a modificar la sede de entrega o retorno.
     * @throws IOException Si ocurre un error al modificar la sede en el archivo.
     */
    public void modificarSedeReserva(Reserva reserva, String sede, String entregaOretorno, String cliente) {
    	int posInfo;
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Reservas"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] infoReserva = line.split(",");
                if (entregaOretorno.equals("entrega")) {
                	posInfo = 1;
                }else {
                	posInfo = 2;
                }
                
                
                if (reserva.getCliente().equals(cliente)) {
                	
                	input += line.replaceFirst(infoReserva[posInfo], sede) + "\n";
                }else {
                    input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/Reservas");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Modifica la fecha de entrega o retorno en el archivo de reservas.
     *
     * @param reserva       La reserva cuya fecha se va a modificar.
     * @param fecha         La nueva fecha de entrega o retorno.
     * @param entregaOretorno Indica si se va a modificar la fecha de entrega o retorno.
     * @throws IOException Si ocurre un error al modificar la fecha en el archivo.
     */
    public void modificarFechaReserva(Reserva reserva, Date fecha, String entregaOretorno) {
    	int posInfo;
    	String infoFecha;
    	SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Reservas"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] reservaInfo = line.split(",");
                if (entregaOretorno.equals("entrega")) {
                	posInfo = 3;
                	infoFecha = date.format(reserva.getFechaEntrega());
    
                }else {
                	posInfo = 4;
                	infoFecha = date.format(reserva.getFechaRetorno());
                }
                
                if (infoFecha.equals(reservaInfo[posInfo])) {
                	String fechaStr = date.format(fecha);
                	input += line.replaceAll(reservaInfo[posInfo], fechaStr ) + "\n";
                	System.out.print(line);
                }else {
                    input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/Reservas");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Modifica el precio de la reserva en el archivo, en caso de que se hayan generado cambios.
     *
     * @param reserva       La reserva cuyo precio se va a modificar.
     * @param nuevoPrecio   El nuevo precio de la reserva.
     * @param cliente       Indica el nombre del cliente dueño de la reserva.
     * @throws IOException  Si ocurre un error al modificar el precio en el archivo.
     */
    public void modificarPrecioReserva(Reserva reserva, double nuevoPrecio, String cliente) {
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Reservas"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] infoReserva = line.split(",");
                
                if (reserva.getCliente().equals(cliente)) {
                	input += line.replaceAll(infoReserva[7], String.valueOf(nuevoPrecio))+"\n";
                }else {
                    input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/Reservas");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void modificarEstadoReserva(Reserva reserva, String nuevoEstado, String cliente) {
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/Reservas"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] infoReserva = line.split(",");
                
                if (reserva.getCliente().equals(cliente)) {
                	input += line.replaceAll(infoReserva[9],nuevoEstado)+"\n";
                }else {
                    input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/Reservas");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Conductor
    /**
     * Agrega un conductor a la lista de conductores.
     *
     * @param conductor El conductor que se va a agregar.
     */
    public void addConductor(Conductor conductor) {
    	conductores.add(conductor);
    }    
    
    /**
     * Escribe la información de un conductor en el archivo de conductores.
     *
     * @param conductor El conductor que se va a escribir en el archivo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void escribirConductor(Conductor conductor) {
    	String rutaArchivo = "InventarioDatos/Conductores";
    	
    	SimpleDateFormat fechaVencimiento = new SimpleDateFormat("dd/MM/yyyy");
    	String vencimientoLicencia = fechaVencimiento.format(conductor.getLicencia().getFechaVencimineto());
    	
        String contenido = conductor.getNombre() + "," +  conductor.getTelefono() + "," 
    	+ conductor.getCorreo() + "," + conductor.getLicencia().getNumero() + ","
        + conductor.getLicencia().getPaisExpedicion() + "," + vencimientoLicencia + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
        	writer.write(contenido);  
        } catch (IOException e) {
        	System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    //AgendaCarros
    /**
     * Agrega una agenda de carro a la lista de agendas de un carro identificado por su ID.
     *
     * @param IdCarro    El ID del carro al que se va a agregar la agenda.
     * @param agendaCarro La agenda de carro que se va a agregar.
     */
    public void addAgendasCarros(String IdCarro, AgendaCarro agendaCarro) {
    	if(agendasCarros.containsKey(IdCarro) == true) {
    		agendasCarros.get(IdCarro).add(agendaCarro);
    	}else {
    		ArrayList<AgendaCarro> listaAgendas = new ArrayList<>();
    		agendasCarros.put(IdCarro, listaAgendas);
    	}
    } 
    
    /**
     * Escribe la información de una agenda de carro en el archivo de agendas de carros.
     *
     * @param IdCarro    El ID del carro al que se relaciona la agenda.
     * @param agendaCarro La agenda de carro que se va a escribir en el archivo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void escribirAgendasCarros(String IdCarro, AgendaCarro agendaCarro) {
    	String rutaArchivo = "InventarioDatos/AgendasCarros";
    	
    	SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
    	String fechaInicio = fecha.format(agendaCarro.getFechaInicio());
    	String fechaFinal = fecha.format(agendaCarro.getFechaFinal());
    	
        String contenido = IdCarro + "," +  fechaInicio + "," 
    	+ fechaFinal + "," +agendaCarro.getEstadoCarro() + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
        	writer.write(contenido);  
        } catch (IOException e) {
        	System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    /**
     * Modifica la fecha de inicio o final de una agenda de carro en el archivo de agendas de carros.
     *
     * @param agendaCarro     La agenda de carro cuya fecha se va a modificar.
     * @param fecha           La nueva fecha de inicio o final.
     * @param finalOinicial   Indica si se va a modificar la fecha de inicio o final.
     * @throws IOException Si ocurre un error al modificar la fecha en el archivo.
     */
    public void modificarAgendasCarros(AgendaCarro agendaCarro, Date fecha, String finalOinicial) {
    	int posInfo;
    	String infoFecha;
    	SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
    	try (BufferedReader reader = new BufferedReader(new FileReader("InventarioDatos/AgendasCarros"))) {
    		String line;
    		String input = "";
    		while ((line = reader.readLine()) != null) {
                String[] agendaInfo = line.split(",");
                if (finalOinicial.equals("inicial")) {
                	posInfo = 0;
                	infoFecha = date.format(agendaCarro.getFechaInicio());
    
                }else {
                	posInfo = 1;
                	infoFecha = date.format(agendaCarro.getFechaFinal());
                }
                
                if (infoFecha.equals(agendaInfo[posInfo])) {
                	String fechaStr = date.format(fecha);
                	input += line.replaceAll(agendaInfo[posInfo], fechaStr)+"\n";
                }else {
                    input += line+"\n";
                }
            }
    		 FileOutputStream fileOut = new FileOutputStream("InventarioDatos/AgendasCarros");
    		    fileOut.write(input.getBytes());
    		    fileOut.close();
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //SegurosReserva
    /**
     * Agrega un seguro a la lista de seguros de una reserva de un cliente.
     *
     * @param nombreCliente El nombre del cliente al que se va a agregar el seguro.
     * @param nombreSeguro  El nombre del seguro que se va a agregar.
     */
    public void addSegurosReserva(String nombreCliente, String nombreSeguro) {
    	if(segurosReserva.containsKey(nombreCliente) == true) {
    		segurosReserva.get(nombreCliente).add(nombreSeguro);
    	}else {
    		ArrayList<AgendaCarro> listaAgendas = new ArrayList<>();
    		agendasCarros.put(nombreCliente, listaAgendas);
    	}
    }
    
    /**
     * Escribe la información de un seguro de reserva en el archivo de seguros de reserva.
     *
     * @param nombreCliente El nombre del cliente al que se relaciona el seguro.
     * @param nombreSeguro  El nombre del seguro que se va a escribir en el archivo.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void escribirSegurosReserva(String nombreCliente, String nombreSeguro) {
    	String rutaArchivo = "InventarioDatos/SegurosReserva";
        String contenido = nombreCliente + "," +  nombreSeguro + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
        	writer.write(contenido);  
        } catch (IOException e) {
        	System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    
    
    //Extra
    /**
     * Consulta la información de un vehículo por su ID, mostrando sus agendas y ubicación.
     *
     * @param vehiculoID El ID del vehículo que se va a consultar.
     */
    public void consultarVehiculo(String vehiculoID) {
        Vehiculo vehiculoConsultado = null;

        for (Vehiculo vehiculo : cars) {
            if (vehiculo.getVehiculoId().equals(vehiculoID)) {
                vehiculoConsultado = vehiculo;
                break;
            }
        }

        if (vehiculoConsultado != null) {
            for(AgendaCarro indisponibilidad : vehiculoConsultado.getAgendaVehiculo()){
            	System.out.println("El vehículo no estará disponible desde " + indisponibilidad.getFechaInicio());
            	System.out.println("hasta " + indisponibilidad.getFechaFinal());
            }
            System.out.println("UBICACIÓN");
            System.out.println("Se encuentra en la sede: " + vehiculoConsultado.getUbicacion());
           
            for(Reserva reserva: reservas) {
            	if (reserva.getIdCarro().equals(vehiculoID)) {
            		System.out.println("Reservas:");
            		System.out.println("Reservado desde: " + reserva.getFechaEntrega());
            		System.out.println("Reservado hasta: " + reserva.getFechaRetorno());
            		System.out.println("Reservado por: " + reserva.getCliente());
            		System.out.println("Estado actual de la reserva: " + reserva.getEstado());
            		System.out.println("-------------------------------------------------------");
            		
            	}
            	else {System.out.println("El vehículo no se encuentra reservado");}
            }
            
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    
    }
    
    
    /**
     * Registra un nuevo cliente a través de entrada desde el escáner y lo almacena en el sistema.
     *
     * @param scanner    El escáner para la entrada del cliente.
     * @param login      El nombre de usuario del cliente.
     * @param contrasena La contraseña del cliente.
     * @return El nuevo cliente registrado.
     */
    public  Cliente RegistarCliente(Scanner scanner, String login, String contrasena) {
    	Cliente newCliente = null;
    	
    	System.out.print("Escirba su nombre completo: ");
    	String clienteName = scanner.nextLine();
    	System.out.print("Digite su telefono: ");
    	String telefono = scanner.nextLine();
    	System.out.print("Digite su correo: ");
    	String correo = scanner.nextLine();
    	
    	System.out.print("Digite el numero de su licencia de conducción: ");
    	String numeroLicencia = scanner.nextLine();
    	System.out.print("Digite el país de expedición de su licencia de conducción: ");
    	String PaisLicencia = scanner.nextLine();
    	System.out.print("Digite la fecha de vencimiento de su licencia (dd/MM/yyyy): ");
    	String VencimientoLicenciaStr = scanner.nextLine();
    	try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date VencimientoLicencia = dateFormat.parse(VencimientoLicenciaStr);
            
            Licencia licencia = new Licencia(numeroLicencia, PaisLicencia, VencimientoLicencia);
            
            System.out.print("Digite el número de su tarjeta: ");
        	String numeroTarjeta = scanner.nextLine();
        	System.out.print("Digite el tipo de tarjeta que tiene (VISA, Mastercard, ...): ");
        	String tipoTarjeta = scanner.nextLine();
        	System.out.print("Digite la fecha de vencimiento de su tarjeta (dd/MM/yyyy): ");
        	String VencimientoTarjetaStr = scanner.nextLine();
            Date VencimientoTarjeta = dateFormat.parse(VencimientoTarjetaStr);
            
            MedioPago medioPago = new MedioPago(numeroTarjeta, tipoTarjeta, VencimientoTarjeta);
        	
        	newCliente = new Cliente(telefono, clienteName, correo, licencia, medioPago, login, contrasena);
        	Conductor conductor = new Conductor(clienteName, telefono, correo, licencia);
        	
        	addConductor(conductor);
        	escribirConductor(conductor);
        	addCliente(newCliente);
        	escribirCliente(newCliente);
        	
    	} catch (ParseException e){
    		System.err.println("La fecha no está en formato (dd/MM/yyyy");
    	}
    	return newCliente;
    }
    
    /**
     * Registra un nuevo conductor a través de entrada desde el escáner y lo almacena en el sistema.
     *
     * @param scanner El escáner para la entrada del conductor.
     * @return El nuevo conductor registrado.
     */
    public  Conductor RegistrarConductor(Scanner scanner) {
    	Conductor nuevoConductor = null;
    	
    	System.out.print("Nombre conductor: ");
    	String conductorName = scanner.nextLine();
    	System.out.print("Telefono conductor: ");
    	String telefono = scanner.nextLine();
    	System.out.print("Correo conductor: ");
    	String correo = scanner.nextLine();
    	
    	System.out.print("numero licencia de conducción: ");
    	String numeroLicencia = scanner.nextLine();
    	System.out.print("país de expedición de licencia de conducción: ");
    	String PaisLicencia = scanner.nextLine();
    	System.out.print("fecha de vencimiento de licencia: ");
    	String VencimientoLicenciaStr = scanner.nextLine();
    	try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date VencimientoLicencia = dateFormat.parse(VencimientoLicenciaStr);
            
            Licencia licencia = new Licencia(numeroLicencia, PaisLicencia, VencimientoLicencia);
            
            nuevoConductor = new Conductor(conductorName, telefono, correo, licencia);
            addConductor(nuevoConductor);
            escribirConductor(nuevoConductor);
            
    	} catch(ParseException e) {
    		System.err.println("La fecha no está en formato (dd/MM/yyyy");
    	}
    	return nuevoConductor;
    	
    }
    
    /**
     * Determina la tarifa de alquiler para un vehículo basada en la fecha de inicio y la categoría.
     *
     * @param startDate      La fecha de inicio del alquiler.
     * @param categoriaInput La categoría del vehículo seleccionado.
     * @return La tarifa calculada.
     */
    public  int DeterminarTarifa(Date startDate, String categoriaInput) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int month = calendar.get(Calendar.MONTH);
        boolean temporadaAlta = (month >= Calendar.JULY && month <= Calendar.DECEMBER);
        
        
        int tarifa = 0;
        Categoria categoria = categorias.get(categoriaInput);
        if (temporadaAlta == true) {
        	tarifa = categoria.getTarifaTempAlta();
        } else {
        	tarifa = categoria.getTarifaTempBaja();
        }
        return tarifa;
    }
    
    /**
     * Devuelve un vehículo, realiza las actualizaciones correspondientes y muestra la información.
     *
     * @param carId     El ID del vehículo que se va a devolver.
     * @param fechaHoy  La fecha actual.
     * @param fechaFinal La fecha de devolución del vehículo.
     */
    public void returnVehiculo(String carId, Date fechaHoy, Date fechaFinal) {
    	Vehiculo carToReturn = null;
        for (Vehiculo car : cars) {
            if (car.getVehiculoId().equals(carId)) {
                carToReturn = car;
                break;
            }
        }

        if (carToReturn != null) {
            Cliente cliente = null;
            for (Reserva reserva : reservas) {
            	System.out.print(reserva.getIdCarro());
                if (reserva.getIdCarro().equals(carId)) {
                	cliente = clientes.get(reserva.getCliente());
                    carToReturn.setUbicacion(reserva.getIdSedeDevolver());
                    if (reserva.getIdSedeDevolver() != reserva.getIdSedeRecoger()) {
                    	Sede sedeDevolver = sedes.get(reserva.getIdSedeDevolver());
                    	sedeDevolver.agregarVehiculo(carToReturn);
                    	Sede sedeRecoger = sedes.get(reserva.getIdSedeRecoger());
                    	sedeRecoger.eliminarVehiculo(carToReturn);
                    	reserva.setEstado("terminada");
                    	modificarEstadoReserva(reserva, "terminada", cliente.getName());
                    }
                    break;
                }
            }

            if (cliente != null) {
            	AgendaCarro agenda = carToReturn.entregar(fechaHoy, fechaFinal);
                addAgendasCarros(carToReturn.getVehiculoId(), agenda);
                escribirAgendasCarros(carToReturn.getVehiculoId(), agenda);
                
                System.out.println("Vehículo retornado de manera exitosa por el cliente: " + cliente.getName());
                System.out.println("A partir de este momento la tarjeta del cliente queda nuevamente activada");
            } else {
                System.out.println("El vehículo no fue rentado o falta información");
            }
        } else {
            System.out.println("ID inválido o vehículo no retornado");
        }
    }
    
    /**
     * Marca un vehículo como en mantenimiento y registra la agenda de mantenimiento.
     *
     * @param carId       El ID del vehículo que se va a marcar como en mantenimiento.
     * @param fechaInicio La fecha de inicio del mantenimiento.
     * @param fechaFinal  La fecha de finalización del mantenimiento.
     */
    public  void carroEnMantenimiento(String carId, Date fechaInicio, Date fechaFinal) {
    	Vehiculo carroMantenimiento = null;
    	for (Vehiculo car : cars) {
            if (car.getVehiculoId().equals(carId)) {
                carroMantenimiento = car;
                break;
            }
    	}
        if (carroMantenimiento != null) {
            AgendaCarro agenda = carroMantenimiento.mantenimiento(fechaInicio, fechaFinal);
           	addAgendasCarros(carroMantenimiento.getVehiculoId(), agenda);
           	escribirAgendasCarros(carroMantenimiento.getVehiculoId(), agenda);
           	System.out.println("El carro con placa " + carId + "estará en mantenimiento desde " + fechaInicio);
           	System.out.println("hasta " + fechaFinal);
        }else {
           	System.out.println("ID inválido o vehículo no retornado");         
        }
    }
    
    /**
     * Permite al cliente seleccionar seguros para su reserva y actualiza el precio total.
     *
     * @param scanner       El escáner para la entrada del cliente.
     * @param reservaActual La reserva actual del cliente.
     * @param precio        El precio base del alquiler.
     * @param dias          El número de días de alquiler.
     * @return El nuevo precio total con los seguros seleccionados.
     */
    public  double seleccionarSeguros(Scanner scanner, Reserva reservaActual, double precio, int dias) {
    	boolean continuar = true;
    	double totalPrice = precio;
    	System.out.print("¿Desea agregar seguros? (Y/N): ");
        String agregarSegurosStr = scanner.nextLine();
        
        if (agregarSegurosStr.equalsIgnoreCase("Y")) {
	    	while (continuar) {
	            System.out.println("Seleccione un seguro:");
	
	            for (int i = 0; i < segurosDisponibles.size(); i++) {
	                System.out.println((i + 1) + ". " + segurosDisponibles.get(i).getNombre());
	            }
	            System.out.print("Ingrese el número del seguro o 0 para finalizar: ");
	            int seguroChoice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline
	
	            if (seguroChoice == 0) {
	                continuar = false;
	            } else if (seguroChoice >= 1 && seguroChoice <= segurosDisponibles.size()) {
	                Seguro selectedSeguro = segurosDisponibles.get(seguroChoice - 1);
	
	                // Agregar el seguro a la instancia de Rental actual
	                reservaActual.AgregarSeguro(selectedSeguro);
	                addSegurosReserva(reservaActual.getCliente(), selectedSeguro.getNombre());
	                escribirSegurosReserva(reservaActual.getCliente(), selectedSeguro.getNombre());
	                System.out.println("Seguro '" + selectedSeguro.getNombre() + "' agregado.");
	            } else {
	                System.out.println("Opción no válida.");
	            }
	        }
	    	// Actualizar el precio total con el costo de los seguros seleccionados
	        totalPrice = reservaActual.getPrecioConSeguros(precio, dias);
	        
	        System.out.printf("Nuevo precio total con seguros: $%.2f%n", totalPrice);

        }
        return totalPrice;
     }
    
    /**
     * Permite al cliente agregar conductores adicionales a su reserva.
     *
     * @param scanner        El escáner para la entrada del cliente.
     * @param reservaEvaluada La reserva en la que se agregarán conductores adicionales.
     */
    public  void AgregarConductoresReserva(Scanner scanner, Reserva reservaEvaluada) {
    	boolean continuar = true;
    	while(continuar) {
    		System.out.println("¿Desea agregar más conductores a su renta? (Y/N): ");
        	String AgregarConductores = scanner.nextLine();
        	if (AgregarConductores.equalsIgnoreCase("Y")) {
        		System.out.println("¿El conductor ya está registrado? (Y/N): ");
        		String registrar = scanner.nextLine();
        		if (registrar.equalsIgnoreCase("Y")) {
        			System.out.println("Escriba el correo del conductor: ");
            		String correoConductor = scanner.nextLine();
            		for(Conductor conductor: conductores) {
            	   		if (conductor.getCorreo().equals(correoConductor)) {
            	    		reservaEvaluada.AgregarConductor(conductor);
            	    	}
            	    }
        		}else {
        			Conductor nuevoConductor = RegistrarConductor(scanner);
        			reservaEvaluada.AgregarConductor(nuevoConductor);
        		}
        	}else {
        		continuar = false;
        	}
    	}
    }
    
    /**
     * Permite saber cuantos días dura una reserva creada por el cliente.
     *
     * @param startDate la fecha en la que va a iniciar la reserva.
     * @param endDate   La fecha en la que se terminará la reserva.
     */
    public int cantidadDiasRenta(Date startDate, Date endDate) {
    	long tiempoMilisegundos = endDate.getTime() - startDate.getTime();
        double diasReserva = (double) (tiempoMilisegundos / (1000 * 60 * 60 * 24));
        int diasRenta = (int) diasReserva;
        if (diasReserva != Math.floor(diasReserva)){
        	diasRenta += 1;
        }
        return diasRenta;
    }
    
    /**
     * Carga un empleado a todas las sedes disponibles.
     *
     * @param empleado El empleado que se va a cargar en las sedes.
     */
    public void CargaEmpleadosASede(Empleado empleado) {
    	for(Sede sede : sedes.values()) {
    		if(empleado.getSede().equals(sede.getNombre())) {
    			sede.agregarEmpleado(empleado);
    		}
    	}
    }
    
    /**
     * Carga un vehículo a todas las sedes disponibles.
     *
     * @param vehiculo El vehículo que se va a cargar en las sedes.
     */
    public void CargaCarrosASede(Vehiculo vehiculo) {
    	for(Sede sede : sedes.values()) {
    		if (vehiculo.getUbicacion().equals(sede.getNombre())){
    			sede.agregarVehiculo(vehiculo);;
    		}
    	}
    }
    
    /**
     * Carga una agenda de carro a un vehículo específico.
     *
     * @param IdCarro     El ID del vehículo al que se va a agregar la agenda.
     * @param agendaCarro La agenda de carro que se va a cargar en el vehículo.
     */
    public void CargaAgendaACarros(String IdCarro, AgendaCarro agendaCarro) {
    	for (Vehiculo carro: cars) {
    		if (carro.getVehiculoId().equals(IdCarro)){
    			carro.AñadirIndisponibilidad(agendaCarro);
    		}
    	}
    }
    
    /**
     * Carga un seguro a una reserva específica para un cliente.
     *
     * @param nombreCliente El nombre del cliente al que se va a cargar el seguro.
     * @param nombreSeguro  El nombre del seguro que se va a cargar en la reserva.
     */
    public void CargarSegurosAReserva(String nombreCliente, String nombreSeguro) {
    	for (Reserva reserva: reservas) {
    		if (reserva.getCliente().equals(nombreCliente)){
    			for(Seguro seguro: segurosDisponibles) {
    				if (seguro.getNombre().equals(nombreSeguro)) {
    					reserva.AgregarSeguro(seguro);
    				}
    			}
    		}
    	}
    }
    

    
    
    
    //TODO//
    /**
     * Muestra el menú principal del sistema y maneja las interacciones iniciales.
     */
    public void mostrarMenu() {
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes entre botones

        JButton botonCliente = new JButton("Soy Cliente");
        JButton botonAdminGeneral = new JButton("Soy Administrador General");
        JButton botonEmpleado = new JButton("Soy Empleado");
        JButton botonSalir = new JButton("Salir");

        add(botonCliente, gbc);
        add(botonAdminGeneral, gbc);
        add(botonEmpleado, gbc);
        add(botonSalir, gbc);
        
        botonCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
        		setTitle("Iniciar Sesion ");
        		setDefaultCloseOperation(EXIT_ON_CLOSE);
        		setLocationRelativeTo(null);
        		
        		JPanel myPanel = new JPanel(new GridLayout(3, 2));
        		
        		getContentPane().add(myPanel);
                myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                JTextField usuario = new JTextField();
            	JTextField contrasenia = new JTextField();
        		 myPanel.add(new JLabel("Ingrese su nombre de usuario"));
        		 myPanel.add(usuario);
        		 myPanel.add(new JLabel("Ingrese su contraseña"));
        		 myPanel.add(contrasenia);
        		 JButton registrarse = new JButton("¿No tienes cuenta? Registrate aquí");
        		 myPanel.add(registrarse);
        		 int result = JOptionPane.showConfirmDialog(null, myPanel,
        	                "Ingrese los datos de inicio de sesion", JOptionPane.OK_CANCEL_OPTION);
        		 if (result == JOptionPane.OK_OPTION) {
        	            String usuarioTexto = usuario.getText();
        	            String contraseniaTexto = contrasenia.getText();
        	            //Cliente newCliente = validacionCliente()
        		 }
        		 registrarse.addActionListener(new ActionListener() {
        			 @Override
        			 public void actionPerformed(ActionEvent e) {
        		         validacionAdminGeneral();
        		     }

        		 });
        	 
                 setVisible(true);
        	}
        	private void opcion1AdminGeneral() {
        		 
        	}
        });
        botonAdminGeneral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuAdminGeneral();
            }
         });
        setVisible(true);
	}
    
    
    //CLIENTE//
    /**
     * Muestra el menú principal del cliente y maneja las interacciones del cliente.
     *
     * @param scanner    El escáner para la entrada del cliente.
     * @param newCliente El cliente que ha iniciado sesión o se ha registrado.
     */
    public void mostrarMenuCliente(Scanner scanner, Cliente newCliente) {
		boolean continuar = true;
		
		while (continuar) {
            System.out.println("\n===== Vehiculo Rental System =====\n");
            System.out.println("1. Alquilar un vehículo");
            System.out.println("2. Modificar información entrega en reserva");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción numérica: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
            	opcion1Cliente(scanner, newCliente);
            }
            else if (choice == 2){
            	opcion2Cliente(scanner, newCliente);
            }
            else if (choice == 3) {
            	continuar = false;
            }
            else {
            	System.out.print("Ingrese una opción válida: ");
            }
		}
	}
    
    /**
     * Opción 1 para que un cliente alquile un vehículo.
     *<b>Pre:</b> Scanner y newCliente no deben ser nulos.
     * <b>Post:</b> El cliente puede alquilar un vehículo y gestionar la reserva si están disponibles y en el formato correcto.
     * @param scanner     Objeto Scanner para la entrada del usuario.
     * @param newCliente  Objeto Cliente representando al cliente actual.
     * @throws ParseException si hay un error en el formato de la fecha.
     */
    public void opcion1Cliente(Scanner scanner, Cliente newCliente) {
        
        System.out.print("\n==== Alquilar un vehículo ====\n");
        System.out.print("Ingrese la categoría que desea alquilar (SUV, pequeño, van, lujoso): ");
        String categoriaInput = scanner.nextLine();

            
        System.out.print("Ingrese la fecha de inicio de renta (dd/MM/yyyy): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Ingrese la hora de inicio de renta (HH/mm): ");
        String startHourStr = scanner.nextLine();
        System.out.print("Ingrese la fecha final de renta (dd/MM/yyyy): ");
        String endDateStr = scanner.nextLine();
        System.out.print("Ingrese la hora de inicio de renta (HH/mm): ");
        String endHourStr = scanner.nextLine();
        System.out.println("Ingrese el nombre de la sede en la que va a recoger el carro");
        System.out.println("1. Sucursal Central");
        System.out.println("2. Sucursal Norte");
        System.out.println("3. Sucursal Sur");
        System.out.println("Ingrese el nombre como aparece en las opciones: ");
        String NombreSedeRecoger = scanner.nextLine();
        System.out.println("Ingrese el nombre de la sede en la que va a devolver el carro");
        System.out.println("1. Sucursal Central");
        System.out.println("2. Sucursal Norte");
        System.out.println("3. Sucursal Sur");
        System.out.println("Ingrese el nombre como aparece en las opciones: ");
        
        String NombreSedeDevolver = scanner.nextLine();
        
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
            Date startDate = dateFormat.parse(startDateStr + "/" + startHourStr);
            Date endDate = dateFormat.parse(endDateStr + "/" + endHourStr);
            
            List<Vehiculo> availableCarsIncategoria = new ArrayList<>();
            for (Sede sede : sedes.values()) {
    			if (sede.getNombre().equals(NombreSedeRecoger)){
    				for (Vehiculo car : sede.getVehiculos()) {
    					String categoria = car.getCategoria();
    					if (car.ValidarDisponibilidad(startDate, endDate) && categoria.equals(categoriaInput)) {
    	            		availableCarsIncategoria.add(car);
    	                }
    	                
    	            }
    			}
    		}

            if (!availableCarsIncategoria.isEmpty()) {
                // Seleccionar un carro aleatorio de la categoría
                Random random = new Random();
                Vehiculo selectedCar = availableCarsIncategoria.get(random.nextInt(availableCarsIncategoria.size()));
                
                // Calcular la diferencia de días entre las fechas
                int diasRenta = cantidadDiasRenta(startDate, endDate);

                // Determinar si estamos en temporada alta (por ejemplo, de julio a diciembre)
                int tarifa = DeterminarTarifa(startDate, categoriaInput);
                

                // Resto del código para alquilar el coche
                // Utiliza el método calculatePrice con diasRenta y temporadaAlta
                // para calcular el precio y mostrarlo al usuario.
                boolean reservaVigente = false;
                if(reservas.size() != 0) {
                	for(Reserva res: reservas) {
                    	if(res.getCliente().equals(newCliente.getName())&& res.getEstado().equals("vigente")) {
                    		reservaVigente = true;
                    	}
                	}
                    if(!reservaVigente) {
                    	generarReserva(categoriaInput, startDate, endDate, newCliente, selectedCar, 
                				NombreSedeRecoger, NombreSedeDevolver, diasRenta, tarifa, scanner);
                    }else {
                    	System.out.println("Ya tiene una reserva vigente, no puede generar otra reserva");
                    }
                }else {
                	generarReserva(categoriaInput, startDate, endDate, newCliente, selectedCar, 
            				NombreSedeRecoger, NombreSedeDevolver, diasRenta, tarifa, scanner);
                }
                
                
                
                
            } else {
                System.out.println("\nNo hay carros disponibles en la categoría seleccionada, disculpas.");
            }
            
            } catch (ParseException e) {
                System.out.println("Invalid date format");
            }
        }
    
    private void generarReserva(String categoriaInput, Date startDate, Date endDate, Cliente newCliente, 
    		Vehiculo selectedCar, String NombreSedeRecoger, String NombreSedeDevolver, int diasRenta, int tarifa, Scanner scanner) {
    	Reserva reserva = new Reserva(categoriaInput, startDate, endDate, newCliente.getName(), selectedCar.getVehiculoId(), NombreSedeRecoger, NombreSedeDevolver, "vigente");
        
        Categoria categoria = categorias.get(categoriaInput);
        double totalPrice = reserva.getPrecio(diasRenta, tarifa,  categoria.getvalorSedeDiferente());
        System.out.println("\n===== Información del alquiler =====\n");
        System.out.println("Cliente Name: " + newCliente.getName());
        System.out.println("Vehiculo: " + selectedCar.getmarca() + " " + selectedCar.getmodelo());
        System.out.println("Rental Days: " + diasRenta);
        System.out.printf("Precio total: $%.2f%n", totalPrice);

        totalPrice = seleccionarSeguros(scanner, reserva, totalPrice, diasRenta);
        reserva.setPrecio(totalPrice);
        
        System.out.print("\n¿Confirma el alquiler? (Y/N): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("Y")) {
        	addReserva(reserva);
        	AgendaCarro agenda = selectedCar.reservar(startDate, endDate);
        	addAgendasCarros(selectedCar.getVehiculoId(), agenda);
        	escribirAgendasCarros(selectedCar.getVehiculoId(), agenda);
            System.out.println("\nCarro alquilado exitosamente");
            double treintaPct = reserva.get30ptcPrecio(totalPrice);
            reserva.setPrecioAbonado(treintaPct);
            escribirReserva(reserva);
            System.out.print("Se te descontó el 30% del valor de la compra de tu tarjeta: $" + treintaPct);
            
        } else {
            System.out.println("\nRenta cancelada");
        }
    }
    
    /**
     * Opción 2 para que un cliente modifique una reserva existente.
     *<b>Pre:</b> Scanner y newCliente no deben ser nulos.
     *<b>Post:</b> El cliente puede modificar una reserva existente si la reserva pertenece a ese cliente.
     * @param scanner     Objeto Scanner para la entrada del usuario.
     * @param newCliente  Objeto Cliente representando al cliente actual.
     * @throws ParseException si hay un error en el formato de la fecha.
     */
    public void opcion2Cliente(Scanner scanner, Cliente newCliente) {
		for(Reserva reserva: reservas) {
			if (reserva.getCliente().equals(newCliente.getName())&& reserva.getEstado().equals("vigente")) {
				System.out.println("===== Modificar reserva =====");
	            System.out.println("1. Cambiar fecha en la que se devuelve el carro");
	            System.out.println("2. Cambiar lugar de devuelta del carro");
	            System.out.print("Ingrese una opción numérica: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();
	            
	            
	            if(choice == 1){
	            	System.out.println("Escribe la nueva fecha (dd/MM/yyyy/HH/mm): ");
	            	String fechaDevolverStr = scanner.nextLine();
	            	try {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
	                    Date fechaDevolver = dateFormat.parse(fechaDevolverStr);
	                    for(Vehiculo carro : cars) {
	                    	if(carro.getVehiculoId().equals(reserva.getIdCarro())) {
	                    		for(AgendaCarro agenda : carro.getAgendaVehiculo()) {
	                    			if(reserva.getFechaRetorno().equals(agenda.getFechaFinal())) {
	                    				modificarAgendasCarros(agenda, fechaDevolver, "Final");
	                    				agenda.setFechaFinal(fechaDevolver);
	                    			}
	                    		}
	                    	}
	                    }
	                    
	                    modificarFechaReserva(reserva, fechaDevolver, "retorno");
	                    reserva.setFechaRetorno(fechaDevolver);
	                    double precio = reserva.getPrecio(cantidadDiasRenta(reserva.getFechaEntrega(), reserva.getFechaRetorno()), DeterminarTarifa(reserva.getFechaEntrega(),  reserva.getCategoria()), categorias.get(reserva.getCategoria()).getvalorSedeDiferente());
	                    double precioTotal = reserva.getPrecioConSeguros(precio, cantidadDiasRenta(reserva.getFechaEntrega(), reserva.getFechaRetorno()));
	                    reserva.setPrecio(precioTotal);
	                    modificarPrecioReserva(reserva, precioTotal, reserva.getCliente());
	                    
	            	}catch(ParseException e) {
	            		System.out.println("Invalid date format. Please use dd/MM/yyyy.");
	            	}	
	           
	                
	            } else if (choice == 2) {
	            	System.out.println("Escriba el nuevo nombre de la sede donde se va a devolver el carro");
	            	String NombreSede = scanner.nextLine();
	            	
	            	modificarSedeReserva(reserva, NombreSede, "devuelta", reserva.getCliente());
	                reserva.setIdSedeDevolver(NombreSede);
	                double precio = reserva.getPrecio(cantidadDiasRenta(reserva.getFechaEntrega(), reserva.getFechaRetorno()), DeterminarTarifa(reserva.getFechaEntrega(),  reserva.getCategoria()), categorias.get(reserva.getCategoria()).getvalorSedeDiferente());
                    double precioTotal = reserva.getPrecioConSeguros(precio, cantidadDiasRenta(reserva.getFechaEntrega(), reserva.getFechaRetorno()));
                    modificarPrecioReserva(reserva, precioTotal, reserva.getCliente());
                    reserva.setPrecio(precioTotal);
	                
	            } else {
	            	System.out.println("Elige una opción válida");
	            	
	            }
			}
		}
	}
	
    /**
     * Validación y autenticación del cliente.
     *<b>Pre:</b> Scanner no debe ser nulo.
     *<b>Post:</b> Se devuelve un objeto Cliente si la autenticación es exitosa, o null en caso contrario.
     * @param scanner Objeto Scanner para la entrada del usuario.
     * @return Objeto Cliente si se autentica correctamente, o null si falla la autenticación.
     */
    public  Cliente validacionCliente(String clienteUsername, String clientePassword) {
 		// Menú para el cliente
 		Cliente newCliente = null;
         boolean clienteAutenticado = false;
         
         while (!clienteAutenticado) {
             
             // Verificar si el usuario ya existe en el mapa
             if (usuariosYContraseñas.containsKey(clienteUsername)) {
                 // Verificar si la contraseña coincide
                 if (usuariosYContraseñas.get(clienteUsername).equals(clientePassword)) {
                 	for (Cliente cliente : clientes.values()) {
                 		if (cliente.getLogin().equals(clienteUsername)) {
                 			newCliente = cliente;
                 		}
                 	}
                     clienteAutenticado = true;
                 } else {
                     System.out.println("Contraseña incorrecta. Inténtelo de nuevo.");
                 }
             } else {
                 // El usuario no existe en el mapa, permitir registro
             	dispose();
         		setTitle("Registrarse ");
         		setDefaultCloseOperation(EXIT_ON_CLOSE);
         		setLocationRelativeTo(null);
         		
         		JPanel myPanel = new JPanel(new GridLayout(3, 2));
         		
         		getContentPane().add(myPanel);
                 myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
                 JTextField usuario = new JTextField();
             	JTextField contrasenia = new JTextField();
         		 myPanel.add(new JLabel("Ingrese su nombre de usuario"));
         		 myPanel.add(usuario);
         		 myPanel.add(new JLabel("Ingrese su contraseña"));
         		 myPanel.add(contrasenia);
         		 int result = JOptionPane.showConfirmDialog(null, myPanel,
         	                "Ingrese los datos de inicio de sesion", JOptionPane.OK_CANCEL_OPTION);
         		 if (result == JOptionPane.OK_OPTION) {
      	            String usuarioTexto = usuario.getText();
      	            String contraseniaTexto = contrasenia.getText();
      	            //newCliente = RegistarCliente(usuarioTexto,contraseniaTexto);
      		 }        		 
             }
         }
         return newCliente;
 	}
	
	//EMPLEADO//
    /**
	 * Muestra el menú de opciones para un empleado y gestiona las operaciones seleccionadas.
	 * <b>Pre:</b> El parámetro scanner no debe ser nulo.
     * <b>Post:</b> Muestra un menú de opciones para un empleado y gestiona las opciones ingresadas.
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 * @throws ParseException Si ocurre un error de análisis de fecha.
	 */
	public void mostrarMenuEmpleado() {
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;
		while (continuar) {
            System.out.println("===== Vehiculo Rental System =====");
            System.out.println("1. Recibir Carro");
            System.out.println("2. Generar reserva");
            System.out.println("3. Entregar Carro");
            System.out.println("4. Reportar que un carro necesita mantenimiento");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción numérica: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
            	opcion1Empleado(scanner);
            }
            else if (choice == 2){
            	opcion2Empleado(scanner);
            }
            else if (choice == 3){
            	opcion3Empleado(scanner);
            }
            else if (choice == 4){
            	opcion4Empleado(scanner);
            }
            else if (choice == 5) {
            	continuar = false;
            }
            else {
            	System.out.print("Ingrese una opción válida: ");
            }
		}
	}
	
	/**
	 * Permite al empleado registrar la devolución de un vehículo y realiza las operaciones asociadas.
	 * <b>Pre:</b> El parámetro scanner no debe ser nulo.
     * <b>Post:</b> Permite al empleado registrar la devolución de un vehículo y realiza las operaciones asociadas.
	 * @param scanner     El objeto Scanner para la entrada de usuario.
	 * @throws ParseException Si ocurre un error de análisis de fecha.
	 */
	public void opcion1Empleado(Scanner scanner) {
		System.out.println("\n======= Retornar vehículo ======= \n");
        System.out.println("Ingrese el nombre del cliente que está retornando el vehículo: ");
        System.out.println("(El cliente debe tener una reserva vigente para poder retornar el vehículo)");
        String clienteNombre = scanner.nextLine();
        System.out.print("Ingrese la fecha de hoy (dd/MM/yyyy): ");
        String fechaHoyStr = scanner.nextLine();
        try {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaHoy = dateFormat.parse(fechaHoyStr);
            
            LocalDate localDate = fechaHoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaSumada = localDate.plusDays(2);
            Date fechaFinal = Date.from(fechaSumada.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            for(Reserva reserva: reservas) {
            	if(reserva.getCliente().equals(clienteNombre) && reserva.getEstado().equals("vigente")) {
            		String carId = reserva.getIdCarro();
            		returnVehiculo(carId, fechaHoy, fechaFinal);
            	}else {
            		System.out.println("El cliente no tiene ninguna reserva vigente");
            	}
            }
            
           
           
        }catch(ParseException e) {
        	System.err.println("La fecha no está en formato (dd/MM/yyyy");
        }
	}
	
	/**
	 * Permite al empleado generar una reserva para un cliente existente.
	 * <b>Pre:</b> El parámetro scanner no debe ser nulo. El cliente debe estar registrado en el sistema.
     * <b>Post:</b> Permite al empleado generar una reserva para un cliente existente.
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 */
	public void opcion2Empleado(Scanner scanner) {
		
		System.out.println("Nombre del cliente: ");
		String NombreCliente = scanner.nextLine();
		for(String clienteStr: clientes.keySet()) {
			if(clienteStr.equals(NombreCliente)) {
				Cliente cliente = clientes.get(NombreCliente);
				opcion1Cliente(scanner, cliente);
			}else {
				System.out.println("El cliente no está registrado en el sistema, pidale que se registre");
			}
		}
	}
	
	/**
	 * Permite al empleado registrar la entrega de un vehículo y realizar cálculos relacionados con la reserva.
	 * <b>Pre:</b> El parámetro scanner no debe ser nulo.
     * <b>Post:</b> Permite al empleado registrar la entrega de un vehículo y realizar cálculos relacionados con la reserva.
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 * @throws ParseException Si ocurre un error de análisis de fecha.
	 */
	public void opcion3Empleado(Scanner scanner) {
		System.out.println("\n======= Entregar vehículo ======= \n");
        System.out.print("Ingrese el nombre del cliente para buscar su reserva: ");
        String NombreCliente = scanner.nextLine();
        
        Reserva reservaEvaluada = null;
        for(Reserva reserva: reservas) {
        	if(reserva.getCliente().equals(NombreCliente)) {
        		reservaEvaluada = reserva;
        		AgregarConductoresReserva(scanner, reservaEvaluada);
        		
	            Categoria categoria = categorias.get(reservaEvaluada.getCategoria());
	            
	            double PrecioBase = reservaEvaluada.getPrecioBase();
	            double PrecioAbonado = reservaEvaluada.getPrecioAbonado();
	            double PrecioConductores = reservaEvaluada.getPrecioConductores(PrecioBase, categoria.getvalorAdicionalConductor());
	            double PrecioTodo = PrecioConductores - PrecioAbonado;
	            
	            System.out.println("La entrega del vehículo fue exitosa ");
	            System.out.println("El total que se le descontará de la tarjeta es de: " + PrecioTodo);
	            System.out.println("A partir de este momento la tarjeta del cliente queda bloqueda");
	            
	            for(Vehiculo car : cars) {
	            	if(car.getVehiculoId().equals(reservaEvaluada.getIdCarro())) {
	            		car.setUbicacion("Alquilado");
	            	}
	            }
	    
            
        	} else {
            	System.out.println("No hay reservas a su nombre");
            }
        }
	}
	
	/**
	 * Permite al empleado registrar que un vehículo necesita mantenimiento y programar su fecha de regreso.
	 * <b>Pre:</b> El parámetro scanner no debe ser nulo.
     * <b>Post:</b> Permite al empleado registrar que un vehículo necesita mantenimiento y programar su fecha de regreso.
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 * @throws ParseException Si ocurre un error de análisis de fecha.
	 */
	public void opcion4Empleado(Scanner scanner) {
		System.out.print("Ingrese el ID(placa) del vehículo que necesita mantenimiento: ");
        String carId = scanner.nextLine();
        System.out.print("Ingrese la fecha en la que el vehículo estará en mantenimiento (dd/MM/yyyy): ");
        String fechaHoyStr = scanner.nextLine();
        System.out.print("Ingrese la cantidad de días que el vehículo estará en mantenimiento: ");
        String cantDiasStr = scanner.nextLine();
        int cantDias = Integer.parseInt(cantDiasStr);
        
        try {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaHoy = dateFormat.parse(fechaHoyStr);
            
            LocalDate localDate = fechaHoy.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaSumada = localDate.plusDays(cantDias-1);
            Date fechaFinal = Date.from(fechaSumada.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            carroEnMantenimiento(carId, fechaHoy, fechaFinal);
        }catch(ParseException e) {
        	System.err.println("La fecha no está en formato (dd/MM/yyyy");
        }
	}
	
	/**
	 * Realiza la validación de un empleado, solicitando su nombre de usuario y contraseña.
	 * Si las credenciales son válidas, muestra el menú de opciones del empleado correspondiente.
	 *
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 * @throws ParseException Si ocurre un error de análisis de fecha.
	 */
	public void validacionEmpleado(Scanner scanner) {
        boolean empleadoAutenticado = true;
        
        while(empleadoAutenticado) {
        	System.out.print("Ingrese su nombre de usuario: ");
            String empleadoUsername = scanner.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String empleadoPassword = scanner.nextLine();
            
            for(Empleado empleado : empleados) {
            	
            	if(empleado.getLogin().equals(empleadoUsername) && empleado.getContrasena().equals(empleadoPassword)) {
            		empleadoAutenticado = false;
            		mostrarMenuEmpleado();
            		break;
            	}
            }
            		
            if(empleadoAutenticado == true) {
            	System.out.println("Nombre de usuario o contraseña incorrectos. Acceso denegado.");
            }
            	
        }
		
	}
	
	//ADMINISTRADOR//
	/**
     * Muestra el menú principal de los administradores para que puedan especificar sin son el 
	 * administrador local de alguna sede o el general.
     */
	public void menuAdministrador() {
		Scanner scanner = new Scanner(System.in);
		Boolean continuar = true;
		while(continuar) {
			System.out.println("========= Menú Administrador =========");
		    System.out.println("1. Administrador General");
		    System.out.println("2. Administrador local");
		    System.out.println("3. Salir al menu principal");
		    System.out.print("Ingrese una opción numérica: ");
			
		    int choice = scanner.nextInt();
		    scanner.nextLine(); // Consume newline
		    
		    if(choice == 1){
		    	validacionAdminGeneral();
		    }else if(choice == 2) {
		    	System.out.println("========= Elija una sede =========");
			    System.out.println("1. Sucursal Central");
			    System.out.println("2. Sucursal Norte");
			    System.out.println("2. Sucursal Sur");
			    System.out.print("Ingrese una opción numérica: ");
			    int sedeOpcion = scanner.nextInt();
			    scanner.nextLine();
			    
			    if(sedeOpcion == 1) {
			    	validacionAdminLocal(scanner, "Central");
			    }else if(sedeOpcion == 2) {
			    	validacionAdminLocal(scanner, "Norte");
			    }else if(sedeOpcion == 3) {
			    	validacionAdminLocal(scanner, "Sur");
			    }
		    }else if(choice == 3) {
		    	continuar = false;
		    }else {
		    	System.out.print("Elija una opción válida");
		    }
		}
	}
	
	//Administrador Local
	/**
	 * Muestra el menú del Administrador Local para una sede específica
	 * y permite al administrador local realizar diversas operaciones.
	 * usuario y contraseña para admin sede central: admin_central
	 * usuario y contraseña para admin sede norte: admin_norte
	 * usuario y contraseña para admin sede sur: admin_sur
	 * 
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 * @param sede    La sede en la que el administrador local está trabajando.
	 */
	public void menuAdminLocal(Scanner scanner, String sede) {
		
		Boolean continuar = true;
		
		while(continuar) {
			System.out.println("========= Menú Administrador =========");
		    System.out.println("1. Modificar info sede");
		    System.out.println("2. Agregar Empleado");
		    System.out.println("3. Salir al menu");
		    System.out.print("Ingrese una opción numérica: ");
		    
		    int choice = scanner.nextInt();
		    scanner.nextLine(); // Consume newline
		    
		    if (choice == 1) {
		    	opcion1AdminLocal(scanner, sede);
		    }else if(choice == 2) {
		    	opcion2AdminLocal(scanner, sede);
		    }else if(choice == 3) {
		    	continuar = false;
		    }else {
		    	System.out.print("Ingrese una opción válida");
		    }
		}
	}
	
	/**
	 * Permite al administrador local modificar la información de una sede,
	 * incluyendo la dirección y el horario de atención.
	 *
	 * @param scanner  El objeto Scanner para la entrada de usuario.
	 * @param sedeStr  El nombre de la sede que se va a modificar.
	 */
	public void opcion1AdminLocal(Scanner scanner, String sedeStr) {
		String sucursal = "Sucursal " + sedeStr;
		
		Sede sede = sedes.get(sucursal);
		
		System.out.println("1. Modificar direccion de la sede");
	    System.out.println("2. Modificar inicio de horario de atención");
	    System.out.println("2. Modificar fin de horario de atencion");
	    System.out.println("4. Salir al menu");
	    System.out.print("Ingrese una opción numérica: ");
	    int opcion = scanner.nextInt();
	    scanner.nextLine();
	    
	    if(opcion == 1) {
	    	System.out.println("Escribe la nueva dirección: ");
	    	String ubicacion = scanner.nextLine();
		    sede.setUbicacion(ubicacion);
		    modificarDireccionSede(sede, ubicacion);
	    } else if(opcion == 2) {
	    	System.out.println("Escribe la nueva hora (HH:mm:ss): ");
	    	String hora = scanner.nextLine();
	    	LocalTime horaInicial = LocalTime.parse(hora);
		    sede.setInicioHorarioAtencion(horaInicial);
		    modificarHorarioSede(sede, horaInicial, "incial");
	    }else if(opcion == 3) {
	    	System.out.println("Escribe la nueva hora (HH:mm:ss): ");
	    	String hora = scanner.nextLine();
	    	LocalTime horaFinal = LocalTime.parse(hora);
		    sede.setFinalHorarioAtencion(horaFinal);
		    modificarHorarioSede(sede, horaFinal, "final");
	    }else {
	    	System.out.println("Escriba una opción válida");
	    }
	}  
	
	/**
	 * Permite al administrador local agregar un nuevo empleado a la sede actual.
	 *
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 * @param sedeStr El nombre de la sede a la que se agrega el empleado.
	 */
	public void opcion2AdminLocal(Scanner scanner, String sedeStr) {
		String sucursal = "Sucursal " + sedeStr;
		
		Sede sede = sedes.get(sucursal);
		
		System.out.print("Ingrese el nombre de usuario del nuevo empleado: ");
		String empleadoUsername = scanner.nextLine();
		System.out.print("Ingrese la contraseña del nuevo empleado: ");
		String empleadoPassword = scanner.nextLine();
		Empleado nuevoEmpleado = new Empleado(sucursal, empleadoUsername, empleadoPassword);
		sede.agregarEmpleado(nuevoEmpleado);
		empleados.add(nuevoEmpleado);
		escribirEmpleado(nuevoEmpleado);
	}
	
	/**
	 * Realiza la autenticación y validación del administrador local antes de permitir
	 * el acceso al menú del administrador local.
	 *
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 * @param sede    El nombre de la sede en la que se encuentra el administrador local.
	 * @throws IllegalArgumentException Si la sede no coincide con "Central", "Norte" o "Sur".
	 */
	public void validacionAdminLocal(Scanner scanner, String sede) {
		
		String ADMIN_USERNAME = "";
		String ADMIN_PASSWORD = "";
		
		if (sede == "Central") {
			ADMIN_USERNAME = "admin_central";
	        ADMIN_PASSWORD = "admin_central";
		}else if(sede == "Norte") {
			ADMIN_USERNAME = "admin_norte";
	        ADMIN_PASSWORD = "admin_norte";
		}else {
			ADMIN_USERNAME = "admin_sur";
	        ADMIN_PASSWORD = "admin_sur";
		}
		
        
        boolean acceso = true;
		// Autenticación del administrador
        while(acceso) {
	        System.out.print("Ingrese el nombre de usuario del administrador: ");
	        String adminUsername = scanner.nextLine();
	        System.out.print("Ingrese la contraseña del administrador: ");
	        String adminPassword = scanner.nextLine();
	
	        if (adminUsername.equals(ADMIN_USERNAME) && adminPassword.equals(ADMIN_PASSWORD)) {
	            // Si las credenciales son correctas, mostrar el menú del administrador
	            menuAdminLocal(scanner, sede);
	            acceso = false;
	        }else {
	            System.out.println("Nombre de usuario o contraseña incorrectos. Acceso denegado.");
	        }
        }
   }
	
	
	//MENU QUE YA SE TRADUJO

	//Administrador general
	/**
	 * Muestra el menú del Administrador General y permite al administrador realizar
	 * diversas operaciones, como agregar y eliminar carros, consultar información de vehículos,
	 * configurar seguros, ver la lista completa de carros, o volver al menú principal.
	 * usuario y contraseña: admiG
	 * 
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 */
	
	public void menuAdminGeneral() {
	    if (validacionAdminGeneral()) {
	        setTitle("Interfaz Administrador General");
	        setSize(300, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	       
	        getContentPane().removeAll();

	        JPanel menuPanel = new JPanel();
	        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

	        JButton agregarCarroButton = new JButton("Agregar Carro");
	        JButton eliminarCarroButton = new JButton("Eliminar Carro");
	        JButton verificarInfoVehiculoButton = new JButton("Verificar Información de Vehículo");
	        JButton configurarSegurosButton = new JButton("Configurar Seguros");
	        JButton configurarPreciosButton = new JButton("Configurar Precios");
	        JButton salirButton = new JButton("Salir al Menú Principal");
	        menuPanel.add(agregarCarroButton);
	        menuPanel.add(eliminarCarroButton);
	        menuPanel.add(verificarInfoVehiculoButton);
	        menuPanel.add(configurarSegurosButton);
	        menuPanel.add(configurarPreciosButton);
	        menuPanel.add(salirButton);

	        agregarCarroButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                opcion1AdminGeneral();
	            }
	        });

	        eliminarCarroButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                opcion2AdminGeneral();
	            }
	        });

	        verificarInfoVehiculoButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String vehiculoID = JOptionPane.showInputDialog("Ingrese el ID (placa) del vehículo que desea consultar:");
	                if (vehiculoID != null) {
	                	mostrarInformacionVehiculoEnVentana(vehiculoID);
	                }
	            }
	        });

	        configurarSegurosButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String[] seguros = {"Seguro Bajo", "Seguro Medio", "Seguro Alto"};
	                JComboBox<String> seguroComboBox = new JComboBox<>(seguros);

	                JPanel myPanel = new JPanel(new GridLayout(2, 2));
	                myPanel.add(new JLabel("Seleccione el seguro:"));
	                myPanel.add(seguroComboBox);

	                int result = JOptionPane.showConfirmDialog(null, myPanel,
	                        "Seleccione el seguro", JOptionPane.OK_CANCEL_OPTION);

	                if (result == JOptionPane.OK_OPTION) {
	                    String selectedSeguro = (String) seguroComboBox.getSelectedItem();
	                    opcion5AdminGeneral(selectedSeguro);
	                }
	            }
	        });
	        
	        configurarPreciosButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String[] categorias = {"SUV", "pequeño", "lujoso", "van"};
	                JComboBox<String> categoriaComboBox = new JComboBox<>(categorias);

	                JTextField tempAltaField = new JTextField();
	                JTextField tempBajaField = new JTextField();
	                JTextField conductorField = new JTextField();
	                JTextField sedeField = new JTextField();

	                JPanel myPanel = new JPanel(new GridLayout(6, 4));
	                myPanel.add(new JLabel("Seleccione la categoría:"));
	                myPanel.add(categoriaComboBox);
	                myPanel.add(new JLabel("Nueva tarifa temporada alta:"));
	                myPanel.add(tempAltaField);
	                myPanel.add(new JLabel("Nueva tarifa temporada baja:"));
	                myPanel.add(tempBajaField);
	                myPanel.add(new JLabel("Nueva tarifa conductor adicional:"));
	                myPanel.add(conductorField);
	                myPanel.add(new JLabel("Nueva tarifa recoger esta categoría en otra sede:"));
	                myPanel.add(sedeField);

	                int preciosResult = JOptionPane.showConfirmDialog(null, myPanel,
	                        "Configurar Precios", JOptionPane.OK_CANCEL_OPTION);

	                if (preciosResult == JOptionPane.OK_OPTION) {
	                    String selectedCategoria = (String) categoriaComboBox.getSelectedItem();
	                    try {
	                        int nuevaTarifaTempAlta = Integer.parseInt(tempAltaField.getText());
	                        int nuevaTarifaTempBaja = Integer.parseInt(tempBajaField.getText());
	                        int nuevaTarifaConductor = Integer.parseInt(conductorField.getText());
	                        int nuevaTarifaSede = Integer.parseInt(sedeField.getText());

	                        // Llama al método para modificar los precios
	                        modificarPreciosCategoria(selectedCategoria, nuevaTarifaTempAlta, nuevaTarifaTempBaja,nuevaTarifaConductor,nuevaTarifaSede);

	                        JOptionPane.showMessageDialog(null, "Precios actualizados con éxito.");
	                    } catch (NumberFormatException ex) {
	                        JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos.");
	                    }
	                }
	            }
	        });


	        salirButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
	        });

	        // Agregar directamente el panel de menú al JFrame
	        add(menuPanel);

	        // Validar y refrescar
	        revalidate();
	        repaint();

	        setLocationRelativeTo(null);
	        setVisible(true);
	    }
	}
	

	/**
	 * Permite al administrador general configurar el precio de un seguro específico.
	 *
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 * @param seguro  El nombre del seguro que se desea configurar.
	 */
	private void opcion1AdminGeneral() {
        JTextField vehiculoIDField = new JTextField();
        JTextField marcaField = new JTextField();
        JTextField modeloField = new JTextField();
        String[] categorias = {"SUV", "Pequeño", "Lujoso", "Van"};
        JComboBox<String> categoriaComboBox = new JComboBox<>(categorias);
        JTextField capacidadField = new JTextField();
        JTextField colorField = new JTextField();
        JTextField transmisionField = new JTextField();
        String[] sedes = {"Sucursal Central", "Sucursal Norte", "Sucursal Sur"};
        JComboBox<String> sedeComboBox = new JComboBox<>(sedes);

        JPanel myPanel = new JPanel(new GridLayout(8, 2));
        myPanel.add(new JLabel("Placa del carro:"));
        myPanel.add(vehiculoIDField);
        myPanel.add(new JLabel("Marca:"));
        myPanel.add(marcaField);
        myPanel.add(new JLabel("Modelo:"));
        myPanel.add(modeloField);
        myPanel.add(new JLabel("Categoría:"));
        myPanel.add(categoriaComboBox);
        myPanel.add(new JLabel("Capacidad:"));
        myPanel.add(capacidadField);
        myPanel.add(new JLabel("Color:"));
        myPanel.add(colorField);
        myPanel.add(new JLabel("Transmisión:"));
        myPanel.add(transmisionField);
        myPanel.add(new JLabel("Sede:"));
        myPanel.add(sedeComboBox);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Ingrese los datos del nuevo carro", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String vehiculoID = vehiculoIDField.getText();
            String marca = marcaField.getText();
            String modelo = modeloField.getText();
            String categoria = (String) categoriaComboBox.getSelectedItem();
            int capacidad = Integer.parseInt(capacidadField.getText());
            String color = colorField.getText();
            String transmision = transmisionField.getText();
            String sede = (String) sedeComboBox.getSelectedItem();

            Vehiculo newCar = new Vehiculo(vehiculoID, marca, modelo, categoria, color, transmision, capacidad, sede);
            addVehiculo(newCar);
            escribirVehiculo(newCar);
            JOptionPane.showMessageDialog(null, "Carro agregado con éxito a la sede " + sede);
        }
    }
	
	/**
	 * Permite al administrador general eliminar un carro del sistema.
	 *
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 */
    public void opcion2AdminGeneral() {
        // Eliminar carro
        String carId = JOptionPane.showInputDialog("Ingrese el ID (Placa) del carro que desea eliminar:");

        Vehiculo carToDelete = null;
        for (Vehiculo car : cars) {
            if (car.getVehiculoId().equals(carId)) {
                carToDelete = car;
                break;
            }
        }

        if (carToDelete != null) {
            removeVehiculo(carToDelete);
            eliminarVehiculo(carToDelete);
            JOptionPane.showMessageDialog(null, "Carro eliminado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "ID inválido o carro no encontrado.");
        }
    }

	
    private void opcion5AdminGeneral(String nombreSeguro) {
        String nuevoPrecioStr = JOptionPane.showInputDialog("Escriba el nuevo precio del seguro " + nombreSeguro + ":");

        try {
            double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
            for (Seguro seg : segurosDisponibles) {
                if (seg.getNombre().equals(nombreSeguro)) {
                    seg.SetCostoPorDia(nuevoPrecio);
                    modificarSeguro(seg, nuevoPrecio);
                    JOptionPane.showMessageDialog(null, "Precio del seguro actualizado con éxito.");
                    System.out.println("Precio del seguro actualizado con éxito.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Seguro no encontrado.");
            System.out.println("Seguro no encontrado.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para el nuevo precio.");
            System.out.println("Error: Ingrese un valor numérico válido para el nuevo precio.");
        }
    }

	/**
	 * Realiza la validación y autenticación del administrador general.
	 *
	 * @param scanner El objeto Scanner para la entrada de usuario.
	 */
	  private boolean validacionAdminGeneral() {
	        final String ADMIN_USERNAME = "admiG";
	        final String ADMIN_PASSWORD = "admiG";

	        // Autenticación del administrador
	        while (true) {
	            String adminUsername = JOptionPane.showInputDialog("Ingrese el nombre de usuario del administrador:");
	            String adminPassword = JOptionPane.showInputDialog("Ingrese la contraseña del administrador:");

	            if (adminUsername != null && adminPassword != null &&
	                    adminUsername.equals(ADMIN_USERNAME) && adminPassword.equals(ADMIN_PASSWORD)) {
	                // Si las credenciales son correctas, retorna true para mostrar el menú
	                return true;
	            } else {
	                JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos. Acceso denegado.");
	            }
	        }
	    }
	
	  private void mostrarInformacionVehiculoEnVentana(String vehiculoID) {
		    Vehiculo vehiculoConsultado = null;

		    // Buscar el vehículo en la lista de carros
		    for (Vehiculo vehiculo : cars) {
		        if (vehiculo.getVehiculoId().equals(vehiculoID)) {
		            vehiculoConsultado = vehiculo;
		            break;
		        }
		    }

		    if (vehiculoConsultado != null) {
		        StringBuilder infoVehiculo = new StringBuilder();
		        for (AgendaCarro indisponibilidad : vehiculoConsultado.getAgendaVehiculo()) {
		            infoVehiculo.append("El vehículo no estará disponible desde ").append(indisponibilidad.getFechaInicio()).append("\n");
		            infoVehiculo.append("hasta ").append(indisponibilidad.getFechaFinal()).append("\n");
		            infoVehiculo.append("-------------------------------------------------------\n");
		            infoVehiculo.append("-------------------------------------------------------\n");
		        }
		        infoVehiculo.append("* Se encuentra en la sede: ").append(vehiculoConsultado.getUbicacion()).append("\n");
		        infoVehiculo.append("* Color: ").append(vehiculoConsultado.getColor()).append("\n");
		        infoVehiculo.append("* Marca: ").append(vehiculoConsultado.getmarca()).append("\n");
		        infoVehiculo.append("* Tipo de transmisión: ").append(vehiculoConsultado.getTipoTransmision()).append("\n");
		        infoVehiculo.append("* Modelo: ").append(vehiculoConsultado.getmodelo()).append("\n");
		        infoVehiculo.append("* Categoría: ").append(vehiculoConsultado.getCategoria()).append("\n");
		        infoVehiculo.append("-------------------------------------------------------\n");
		        infoVehiculo.append("-------------------------------------------------------\n");
		        infoVehiculo.append("Reservas:\n");
		        for (Reserva reserva : reservas) {
		            if (reserva.getIdCarro().equals(vehiculoID)) {
		                infoVehiculo.append("Reservado desde: ").append(reserva.getFechaEntrega()).append("\n");
		                infoVehiculo.append("Reservado hasta: ").append(reserva.getFechaRetorno()).append("\n");
		                infoVehiculo.append("Reservado por: ").append(reserva.getCliente()).append("\n");
		                infoVehiculo.append("Estado actual de la reserva: ").append(reserva.getEstado()).append("\n");
		                infoVehiculo.append("-------------------------------------------------------\n");
		                infoVehiculo.append("-------------------------------------------------------\n");
		            }
		            else {
		            	infoVehiculo.append("Este vehículo no está reservado");
		            	
		            }
		        }

		        // Mostrar la información en una ventana de diálogo
		        JOptionPane.showMessageDialog(null, infoVehiculo.toString(), "Información del Vehículo", JOptionPane.INFORMATION_MESSAGE);
		    } else {
		        JOptionPane.showMessageDialog(null, "Vehículo no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}
	

}