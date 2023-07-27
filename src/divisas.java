import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;


public class divisas extends JFrame implements ActionListener {
    private Map<String, Double> tasasDeCambio;

    private JTextField cantidadOrigenField;
    private JComboBox<String> monedaOrigenCombo;
    private JComboBox<String> monedaDestinoCombo;
    private JLabel resultadoLabel;
    private JPanel panel;
    private ImageIcon backgroundImageIcon;

    public divisas() {
        setTitle("Conversor de Divisas");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        tasasDeCambio = new HashMap<>();

        tasasDeCambio.put("COP-USD", 0.00026); // 1 COP = 0.00026 USD
        tasasDeCambio.put("COP-EUR", 0.00022); // 1 COP = 0.00022 EUR
        tasasDeCambio.put("COP-GBP", 0.00019); // 1 COP = 0.00019 GBP
        tasasDeCambio.put("COP-JPY", 0.028);    // 1 COP = 0.028 JPY
        tasasDeCambio.put("COP-KRW", 0.3);      // 1 COP = 0.3 KRW
        tasasDeCambio.put("USD-COP", 3928.68); // 1 USD = 3928.68 COP
        tasasDeCambio.put("EUR-COP", 4542.99); // 1 EUR = 4542.99 COP
        tasasDeCambio.put("GBP-COP", 5241.16); // 1 GBP = 5241.16 COP
        tasasDeCambio.put("JPY-COP", 35.71);   // 1 JPY = 35.71 COP
        tasasDeCambio.put("KRW-COP", 3.34);    // 1 KRW = 3.34 COP


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        monedaOrigenCombo = new JComboBox<>(new String[]{"COP", "USD", "EUR", "GBP", "JPY", "KRW"});
        monedaDestinoCombo = new JComboBox<>(new String[]{"COP", "USD", "EUR", "GBP", "JPY", "KRW"});
        cantidadOrigenField = new JTextField(10);
        JButton convertirButton = new JButton("Convertir");
        convertirButton.addActionListener(this);

        resultadoLabel = new JLabel("Resultado:");

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(monedaOrigenCombo, constraints);

        constraints.gridx = 1;
        panel.add(new JLabel("a"), constraints);

        constraints.gridx = 2;
        panel.add(monedaDestinoCombo, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(cantidadOrigenField, constraints);

        constraints.gridx = 1;
        panel.add(convertirButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        panel.add(resultadoLabel, constraints);

        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
       String monedaOrigen = (String) monedaOrigenCombo.getSelectedItem();
       String monedaDestino = (String) monedaDestinoCombo.getSelectedItem();
       double cantidadOrigen = Double.parseDouble(cantidadOrigenField.getText());

       String claveTasaCambio = monedaOrigen + "-" + monedaDestino;
        if (tasasDeCambio.containsKey(claveTasaCambio)) {
            double tasaCambio = tasasDeCambio.get(claveTasaCambio);
            double cantidadDestino = cantidadOrigen * tasaCambio;
            DecimalFormat df = new DecimalFormat("#.##");
            resultadoLabel.setText("Resultado: " + df.format(cantidadDestino) + " " + monedaDestino);
        } else {resultadoLabel.setText("No se encontró una tasa de cambio para realizar la conversión.");}




    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            divisas conversor = new divisas();
            conversor.setVisible(true);
        });
    }
}