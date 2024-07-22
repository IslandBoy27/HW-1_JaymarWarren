import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Multas de Tránsito");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        Transito sistemaTransito = new Transito();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Componentes para agregar multas
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        JTextField codigoField = new JTextField(15);
        inputPanel.add(codigoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Nombre del Infractor:"), gbc);
        gbc.gridx = 1;
        JTextField nombreField = new JTextField(15);
        inputPanel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Tipo de Multa (LEVE, MEDIO, GRAVE):"), gbc);
        gbc.gridx = 1;
        JTextField tipoField = new JTextField(15);
        inputPanel.add(tipoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton agregarButton = new JButton("Agregar Multa");
        inputPanel.add(agregarButton, gbc);

        // Panel para los botones de acciones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 5, 5));
        JButton buscarButton = new JButton("Buscar Multa");
        JButton pagarButton = new JButton("Pagar Multa");
        JButton imprimirButton = new JButton("Imprimir Multas");
        JButton infoButton = new JButton("Información del Sistema");

        buttonPanel.add(buscarButton);
        buttonPanel.add(pagarButton);
        buttonPanel.add(imprimirButton);
        buttonPanel.add(infoButton);

        // Añadir paneles al frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Acción para agregar multas
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(codigoField.getText());
                    String nombre = nombreField.getText();
                    String tipo = tipoField.getText();
                    sistemaTransito.agregarMulta(codigo, nombre, tipo);
                    JOptionPane.showMessageDialog(frame, "Multa agregada: Código " + codigo + ", Nombre " + nombre + ", Tipo " + tipo, "Multa Agregada", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Código debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para buscar multas
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(codigoField.getText());
                    Multa multa = sistemaTransito.buscarMulta(codigo);
                    if (multa != null) {
                        JOptionPane.showMessageDialog(frame, "Multa encontrada:\n" + multa, "Buscar Multa", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No existe la multa con el código " + codigo, "Buscar Multa", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Código debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para pagar multas
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(codigoField.getText());
                    Multa multa = sistemaTransito.buscarMulta(codigo);
                    if (multa != null) {
                        multa.pay();
                        JOptionPane.showMessageDialog(frame, "Multa con Código " + codigo + " fue pagada.", "Pagar Multa", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No existe la multa con el código " + codigo, "Pagar Multa", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Código debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para imprimir multas
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder multasInfo = new StringBuilder("Multas en el sistema:\n");
                for (int i = 0; i < sistemaTransito.getContador(); i++) {
                    multasInfo.append(sistemaTransito.getMultas()[i].toString()).append("\n");
                }
                JOptionPane.showMessageDialog(frame, multasInfo.toString(), "Imprimir Multas", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Acción para mostrar información del sistema
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int multasPagadas = 0;
                int multasPendientes = 0;
                double montoPagado = 0;
                double montoPendiente = 0;

                for (int i = 0; i < sistemaTransito.getContador(); i++) {
                    if (sistemaTransito.getMultas()[i].isPagada()) {
                        multasPagadas++;
                        montoPagado += sistemaTransito.getMultas()[i].getMontoPagar();
                    } else {
                        multasPendientes++;
                        montoPendiente += sistemaTransito.getMultas()[i].getMontoPagar();
                    }
                }

                String info = "Cantidad de Multas generadas: " + sistemaTransito.getContador() + "\n"
                        + "Cantidad de Multas Pagadas: " + multasPagadas + " por un monto de Lps " + montoPagado + "\n"
                        + "Cantidad de Multas pendientes: " + multasPendientes + " por un monto de Lps " + montoPendiente;

                JOptionPane.showMessageDialog(frame, info, "Información del Sistema", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}
