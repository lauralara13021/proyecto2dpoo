package Consola;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaInicial extends JFrame{
	public VentanaInicial() {
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
                VentanaIncioDeSesion sesionCliente = new VentanaIncioDeSesion();
                sesionCliente.setVisible(true);
            }
        });
        botonAdminGeneral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazMenuAdmi menuadmi= new InterfazMenuAdmi();
                menuadmi.setVisible(true);
            }
         });
	}
}