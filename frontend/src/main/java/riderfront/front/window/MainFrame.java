package riderfront.front.window;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;

import riderfront.front.dtos.CompraDto;
import riderfront.front.dtos.MotoDto;
import riderfront.front.dtos.PersonaDto;
import riderfront.front.entities.Compra;
import riderfront.front.webservicespersona.CompraApiPersona;
import riderfront.front.webservicespersona.MotoApiMoto;
import riderfront.front.webservicespersona.PersonaApiPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainFrame extends JFrame {

    @Autowired
    private PersonaApiPersona personaService;
    @Autowired
    private CompraApiPersona compraService;
    @Autowired
    private MotoApiMoto motoService;

    private JComboBox<String> comboPersonas;
    private JComboBox<String> comboMotos;
    private JTextArea txtOutput;

    private final Map<String, String> personaIdMap = new HashMap<>();
    private final Map<String, String> motoMatriculaMap = new HashMap<>();

    public MainFrame() {
        setTitle("🏍️ Sistema de Compras de Motos - Spring Boot + Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    public void initializeUI() {
        System.out.println(">>> Inicializando interfaz...");

        // --- Panel superior: Selección de persona y moto ---
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Seleccionar Datos"));

        List<PersonaDto> personas = personaService.getPersonas();
        List<MotoDto> motos = motoService.getMotos();

        DefaultComboBoxModel<String> modeloPersonas = new DefaultComboBoxModel<>();
        for (PersonaDto p : personas) {
            String label = p.getNombre() + " " + p.getApellido();
            modeloPersonas.addElement(label);
            personaIdMap.put(label, p.getId());
        }
        comboPersonas = new JComboBox<>(modeloPersonas);

        DefaultComboBoxModel<String> modeloMotos = new DefaultComboBoxModel<>();
        for (MotoDto m : motos) {
            String label = m.getModelo() + " (" + m.getMatricula() + ")";
            modeloMotos.addElement(label);
            motoMatriculaMap.put(label, m.getMatricula());
        }
        comboMotos = new JComboBox<>(modeloMotos);

        topPanel.add(new JLabel("Persona:"));
        topPanel.add(comboPersonas);
        topPanel.add(new JLabel("Moto:"));
        topPanel.add(comboMotos);

        // --- Panel central: salida de texto ---
        txtOutput = new JTextArea(10, 40);
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtOutput);
        scroll.setBorder(BorderFactory.createTitledBorder(" Registro de operaciones"));

        // --- Panel inferior: botones ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnRegistrar = new JButton(" Registrar Compra");
        JButton btnPersonas = new JButton(" Ver Personas");
        JButton btnMotos = new JButton("️ Ver Motos");
        JButton btnCompras = new JButton(" Ver Compras");

        bottomPanel.add(btnRegistrar);
        bottomPanel.add(btnPersonas);
        bottomPanel.add(btnMotos);
        bottomPanel.add(btnCompras);

        // --- Acciones ---
        btnRegistrar.addActionListener(e -> registrarCompra());
        btnPersonas.addActionListener(e -> mostrarPersonas());
        btnMotos.addActionListener(e -> mostrarMotos());
        btnCompras.addActionListener(e -> mostrarCompras());

        // --- Ensamblar ---
        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void registrarCompra() {
        try {
            String personaKey = (String) comboPersonas.getSelectedItem();
            String motoKey = (String) comboMotos.getSelectedItem();

            if (personaKey == null || motoKey == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar persona y moto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String personaId = personaIdMap.get(personaKey);
            String motoMatricula = motoMatriculaMap.get(motoKey);

            CompraDto compra = new CompraDto();
            compra.setPersonaId(personaId);
            compra.setMotoMatricula(motoMatricula);

            CompraDto resultado = compraService.comprarMoto(compra);

            txtOutput.append("Compra registrada: Persona " + personaId + " compró " + motoMatricula + "\n");
        } catch (Exception ex) {
            txtOutput.append(" Error al registrar compra: " + ex.getMessage() + "\n");
            ex.printStackTrace();
        }
    }

    private void mostrarPersonas() {
        txtOutput.append("\n===  LISTADO DE PERSONAS ===\n");
        personaService.getPersonas().forEach(p ->
                txtOutput.append(String.format("ID: %s | %s %s\n", p.getId(), p.getNombre(), p.getApellido())));
    }

    private void mostrarMotos() {
        txtOutput.append("\n===  LISTADO DE MOTOS ===\n");
        motoService.getMotos().forEach(m ->
                txtOutput.append(String.format("%s | %s | Disponible: %s\n", m.getMatricula(), m.getModelo(), m.isDisponibilidad())));
    }

    private void mostrarCompras() {
        txtOutput.append("\n===  LISTADO DE COMPRAS ===\n");
        compraService.getCompras().forEach(c ->
                txtOutput.append(String.format("Compra #%s | Persona: %s | Moto: %s\n", c.getId(), c.getPersonaId(), c.getMotoMatricula())));
    }
}
