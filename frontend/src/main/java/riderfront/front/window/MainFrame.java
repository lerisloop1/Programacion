package riderfront.front.window;


import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import jakarta.annotation.PostConstruct;
import riderfront.front.dtos.CompraDto;
import riderfront.front.dtos.MotoDto;
import riderfront.front.dtos.PersonaDto;
import riderfront.front.entities.Compra;
import riderfront.front.webservicespersona.CompraApiPersona;
import riderfront.front.webservicespersona.MotoApiMoto;
import riderfront.front.webservicespersona.PersonaApiPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.formdev.flatlaf.FlatLightLaf;
@Component
public class MainFrame extends JFrame {
    @Autowired
    private PersonaApiPersona personaService;
    @Autowired
    private CompraApiPersona compraService;
    @Autowired
    private MotoApiMoto motoService;

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);
    private final JTextArea txtOutput = new JTextArea();

    public MainFrame() {
        FlatLightLaf.setup();
        setTitle("Gestión de Compras de Motos");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    public void initializeUI() {
        // Barra lateral
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(5, 1, 5, 5));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnPersonas = new JButton(" Personas");
        JButton btnMotos = new JButton("node -vMotos");
        JButton btnCompras = new JButton(" Compras");
        JButton btnNuevaCompra = new JButton(" Registrar Compra");
        JButton btnSalir = new JButton(" Salir");

        sidePanel.add(btnPersonas);
        sidePanel.add(btnMotos);
        sidePanel.add(btnCompras);
        sidePanel.add(btnNuevaCompra);
        sidePanel.add(btnSalir);

        // Panel de contenido dinámico
        contentPanel.add(panelPersonas(), "personas");
        contentPanel.add(panelMotos(), "motos");
        contentPanel.add(panelCompras(), "compras");

        add(sidePanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Listeners
        btnPersonas.addActionListener(e -> showPanel("personas"));
        btnMotos.addActionListener(e -> showPanel("motos"));
        btnCompras.addActionListener(e -> showPanel("compras"));
        btnNuevaCompra.addActionListener(e -> abrirDialogoCompra());
        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void showPanel(String name) {
        cardLayout.show(contentPanel, name);
    }

    // === Panel Personas ===
    private JPanel panelPersonas() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea txt = new JTextArea();
        txt.setEditable(false);
        JButton refresh = new JButton(" Actualizar");
        refresh.addActionListener(e -> {
            List<PersonaDto> personas = personaService.getPersonas();
            txt.setText("=== LISTADO DE PERSONAS ===\n");
            for (PersonaDto p : personas) {
                txt.append(p.getId() + " - " + p.getNombre() + " " + p.getApellido() + "\n");
            }
        });
        panel.add(new JScrollPane(txt), BorderLayout.CENTER);
        panel.add(refresh, BorderLayout.SOUTH);
        return panel;
    }

    // === Panel Motos ===
    private JPanel panelMotos() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea txt = new JTextArea();
        txt.setEditable(false);
        JButton refresh = new JButton(" Actualizar");
        refresh.addActionListener(e -> {
            List<MotoDto> motos = motoService.getMotos();
            txt.setText("=== LISTADO DE MOTOS ===\n");
            for (MotoDto m : motos) {
                txt.append(m.getMatricula() + " - " + m.getModelo() + " (" + m.getMarca() + ")\n");
            }
        });
        panel.add(new JScrollPane(txt), BorderLayout.CENTER);
        panel.add(refresh, BorderLayout.SOUTH);
        return panel;
    }

    // === Panel Compras ===
    private JPanel panelCompras() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea txt = new JTextArea();
        txt.setEditable(false);
        JButton refresh = new JButton(" Actualizar");
        refresh.addActionListener(e -> {
            List<CompraDto> compras = compraService.getCompras();
            txt.setText("=== LISTADO DE COMPRAS ===\n");
            for (CompraDto c : compras) {
                txt.append("Compra #" + c.getId() + " | Persona: " + c.getPersonaId() + " | Moto: " + c.getMotoMatricula() + "\n");
            }
        });
        panel.add(new JScrollPane(txt), BorderLayout.CENTER);
        panel.add(refresh, BorderLayout.SOUTH);
        return panel;
    }

    // === Diálogo para registrar una compra ===
    private void abrirDialogoCompra() {
        JDialog dialog = new JDialog(this, "Registrar Compra", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        dialog.setLocationRelativeTo(this);

        JComboBox<String> comboPersonas = new JComboBox<>();
        for (PersonaDto p : personaService.getPersonas()) {
            comboPersonas.addItem(p.getId() + " - " + p.getNombre());
        }

        JComboBox<String> comboMotos = new JComboBox<>();
        for (MotoDto m : motoService.getMotos()) {
            comboMotos.addItem(m.getMatricula() + " - " + m.getModelo());
        }

        JTextField txtId = new JTextField();

        JButton btnGuardar = new JButton(" Registrar");
        JButton btnCancelar = new JButton(" Cancelar");

        dialog.add(new JLabel("Persona:"));
        dialog.add(comboPersonas);
        dialog.add(new JLabel("Moto:"));
        dialog.add(comboMotos);
        dialog.add(btnGuardar);
        dialog.add(btnCancelar);

        btnGuardar.addActionListener(e -> {
            try {
                CompraDto compra = new CompraDto();
                compra.setPersonaId(comboPersonas.getSelectedItem().toString().split(" - ")[0]);
                compra.setMotoMatricula(comboMotos.getSelectedItem().toString().split(" - ")[0]);

                CompraDto response = compraService.comprarMoto(compra);
                JOptionPane.showMessageDialog(dialog, "Compra registrada con éxito:\n" + response);
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Error al registrar compra:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }
}