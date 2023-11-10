package Consola;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Inventario.Inventario;

public class VentanaIncioDeSesion extends JFrame{
	JTextField usuario = new JTextField();
	JTextField contrasenia = new JTextField();
	public VentanaIncioDeSesion() {
		setTitle("Iniciar Sesion ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 200);
		setResizable(true);
		setLocationRelativeTo(null);
		
		JPanel myPanel = new JPanel(new GridLayout(3, 2));
		
		getContentPane().add(myPanel);
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        
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
		 }
		 registrarse.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
		         opcion1AdminGeneral();
		     }

		 });
	 
		    
	}
	private void opcion1AdminGeneral() {
		 
	}
    
}