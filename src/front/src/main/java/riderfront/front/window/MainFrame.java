package riderfront.front.window;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.*;

import riderfront.front.dtos.CompraDto;
import riderfront.front.dtos.PersonaDto;
import riderfront.front.entities.Compra;
import riderfront.front.webservicespersona.CompraApiPersona;
import riderfront.front.webservicespersona.PersonaApiPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainFrame extends JFrame {
    @Autowired
    private PersonaApiPersona personaService;
    @Autowired
    private CompraApiPersona compraService;

    private final JTextField txtPersonaId = new JTextField(10);
    private final JTextField txtCompraId = new JTextField(10);
    private JComboBox<String> comboPersonas;
    private JComboBox<String> comboCompras;
    private final JTextArea txtOutput = new JTextArea(5, 30);
    private final Map<String, Integer> personaIdMap = new HashMap<>();
    private final Map<String, Integer> compraIdMap = new HashMap<>();

    public MainFrame() {
        setSize(800, 300);
    }

    public void initializeUI() {
        setTitle("Compra De Moto (Spring Boot + Swing)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        java.util.List<PersonaDto> personaDtosList = personaService.getPersonas();
        java.util.List<CompraDto> compraDtosList = compraService.getCompras();

        DefaultComboBoxModel<String> modeloPersonas = new DefaultComboBoxModel<>();
        for (PersonaDto persona : personaDtosList) {
            String displayKey = String.format("%s, %s",
                    persona.getApellido(),
                    persona.getNombre());
            modeloPersonas.addElement(displayKey);
            personaIdMap.put(displayKey, Integer.valueOf(persona.getId()));
        }
        comboPersonas = new JComboBox<>(modeloPersonas);

        DefaultComboBoxModel<String> modeloCompras = new DefaultComboBoxModel<>();
        for (CompraDto c : compraDtosList) {
            String displayKey = String.format("%s, %s",
                    c.getPersonaId(),
                    c.getMotoMatricula());
            modeloCompras.addElement(displayKey );
            compraIdMap.put(displayKey, c.getIdCompra());
        }
        comboCompras = new JComboBox<>(modeloCompras);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Seleccionar Persona:"));
        inputPanel.add(comboPersonas);

        inputPanel.add(new JLabel("Seleccionar Compra:"));
        inputPanel.add(comboCompras);

        inputPanel.add(new JLabel("ID Persona (e.g., 101):"));
        inputPanel.add(txtPersonaId);

        inputPanel.add(new JLabel("ID Compra (e.g., 500):"));
        inputPanel.add(txtCompraId);

        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registro de Operaciones"));
        add(scrollPane, BorderLayout.CENTER);

        setPreferredSize(new Dimension(800, 300));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void enrollStudent() {

        int personaId, compraId;
        try {
            String selectedPersona = (String) comboPersonas.getSelectedItem();
            String selectedCompra = (String) comboCompras.getSelectedItem();
            System.out.println("valor combo persona: " + selectedPersona);
            System.out.println("valor combo compra: " + selectedCompra);
            System.out.println("valor id persona: " + personaIdMap.get(selectedPersona));
            System.out.println("valor id compra: " + compraIdMap.get(selectedCompra));

            personaId = personaIdMap.get(selectedPersona);
            compraId = compraIdMap.get(selectedCompra);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese IDs numéricos válidos.", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result;

        txtOutput.append(result + "\n");
        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());

        if (result.startsWith("ÉXITO")) {
            JOptionPane.showMessageDialog(this, result, "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, result, "Error en Compra", JOptionPane.WARNING_MESSAGE);
        }


    }

    public void showFrame() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

}
