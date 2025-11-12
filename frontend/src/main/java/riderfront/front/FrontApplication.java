package riderfront.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import riderfront.front.window.MainFrame;

import javax.swing.*;


@SpringBootApplication
@EnableFeignClients

public class FrontApplication implements CommandLineRunner {

	// Spring inyecta el componente MainFrame
	@Autowired
	private MainFrame mainFrame;

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(FrontApplication.class, args);
	}


	// a veces no carga porque es llamado sin que termine de inicializar todo el contexto de spring boot
	public void run(String... args) throws Exception {
		System.out.println(">>> Iniciando interfaz Swing...");
		SwingUtilities.invokeLater(() -> {
			// Llamar a la inicialización de componentes (que usa los servicios inyectados)
			mainFrame.initializeUI();
			mainFrame.setVisible(true);
			System.out.println(">>> MainFrame inicializado correctamente");
		});
	}

}
