package gui;

import logic.MathematicalOperations;
import logic.PolynomialParser;
import model.DivisionResult;
import model.Polynomial;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JTextField activeTextField;
    private JPanel inputPanel;
    private JTextField polynomial1Text;
    private JTextField polynomial2Text;
    private JLabel resultLabel;
    private JPanel keyboardPanel;
    private JPanel operationsPanel;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton deriveButton;
    private JButton integrateButton;
    private JButton deleteButton;

    public MainFrame(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        layoutComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Initialize panels
        inputPanel = new JPanel(new GridLayout(3, 1));
        keyboardPanel = new JPanel(new GridLayout(5, 4));
        operationsPanel = new JPanel(new GridLayout(6, 1));

        // Initialize components
        polynomial1Text = new JTextField(20);
        polynomial1Text.setEditable(false);
        polynomial1Text.addMouseListener(new TextFieldMouseListener(polynomial1Text));
        polynomial2Text = new JTextField(20);
        polynomial2Text.setEditable(false);
        polynomial2Text.addMouseListener(new TextFieldMouseListener(polynomial2Text));
        resultLabel = new JLabel("Result:");

        addButton = createStyledButton("Add");
        subtractButton = createStyledButton("Subtract");
        multiplyButton = createStyledButton("Multiply");
        divideButton = createStyledButton("Divide");
        deriveButton = createStyledButton("Derive");
        integrateButton = createStyledButton("Integrate");
        deleteButton = createStyledButton("Delete");
        deleteButton.addActionListener(new KeyboardButtonListener());

        // Action listeners to operation buttons
        addButton.addActionListener(new OperationButtonListener());
        subtractButton.addActionListener(new OperationButtonListener());
        multiplyButton.addActionListener(new OperationButtonListener());
        divideButton.addActionListener(new OperationButtonListener());
        deriveButton.addActionListener(new OperationButtonListener());
        integrateButton.addActionListener(new OperationButtonListener());

        // Action listeners to keyboard buttons
        String[] keyboardButtonTexts = {"x", "^", "+", "-", "*", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        for (String buttonText : keyboardButtonTexts) {
            JButton button = createStyledButton(buttonText);
            button.addActionListener(new KeyboardButtonListener());
            keyboardPanel.add(button);
        }
        keyboardPanel.add(deleteButton);
    }

    private void layoutComponents() {
        // Add components to input panel
        JLabel polynomial1Label = new JLabel("Polynomial 1:");
        polynomial1Label.setForeground(Color.WHITE); // Set text color to white
        polynomial1Label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18)); // Set font size to 18
        inputPanel.add(polynomial1Label);
        polynomial1Text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18)); // Set font size to 18
        inputPanel.add(polynomial1Text);

        JLabel polynomial2Label = new JLabel("Polynomial 2:");
        polynomial2Label.setForeground(Color.WHITE); // Set text color to white
        polynomial2Label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18)); // Set font size to 18
        inputPanel.add(polynomial2Label);
        polynomial2Text.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18)); // Set font size to 18
        inputPanel.add(polynomial2Text);

        resultLabel.setForeground(Color.WHITE); // Set text color to white
        resultLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18)); // Set font size to 18
        inputPanel.add(resultLabel);

        // Add components to operations panel
        operationsPanel.add(addButton);
        operationsPanel.add(subtractButton);
        operationsPanel.add(multiplyButton);
        operationsPanel.add(divideButton);
        operationsPanel.add(deriveButton);
        operationsPanel.add(integrateButton);

        // Add panels and separators to main frame
        setLayout(new BorderLayout());

        // Add keyboard panel and input panel to the west side
        JPanel westPanel = new JPanel(new BorderLayout());
        westPanel.add(inputPanel, BorderLayout.NORTH);
        westPanel.add(keyboardPanel, BorderLayout.CENTER); // Interchanged positions
        add(westPanel, BorderLayout.WEST);

        // Add operations panel to the east side
        add(operationsPanel, BorderLayout.EAST);

        // Add separator in the center
        add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER);

        customizeComponents();
    }

    private void customizeComponents() {
        // Customization of components
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(46, 46, 46)); // Dark grey background
        inputPanel.setForeground(Color.WHITE); // White text color

        keyboardPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        keyboardPanel.setBackground(new Color(46, 46, 46)); // Dark grey background

        operationsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        operationsPanel.setBackground(new Color(46, 46, 46)); // Dark grey background

        // Customization of buttons
        Font buttonFont = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        addButton.setFont(buttonFont);
        subtractButton.setFont(buttonFont);
        multiplyButton.setFont(buttonFont);
        divideButton.setFont(buttonFont);
        deriveButton.setFont(buttonFont);
        integrateButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(63, 114, 155)); // Dark blue background
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false); // Remove focus border
        button.setBorderPainted(true); // Make border visible
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(173, 216, 230)), // Light blue border
                BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Padding
        button.setPreferredSize(new Dimension(80, 50)); // Button size
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16)); // Font
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        button.setMargin(new Insets(0, 0, 0, 0)); // Remove default margins
        return button;
    }

    private class KeyboardButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();

            if (buttonText.equals("Delete")) {
                // Delete last character from active text field
                if (activeTextField != null && !activeTextField.getText().isEmpty()) {
                    activeTextField.setText(activeTextField.getText().substring(0, activeTextField.getText().length() - 1));
                }
            } else {
                if (activeTextField != null) {
                    activeTextField.setText(activeTextField.getText() + buttonText);
                }
            }
        }
    }

    private class TextFieldMouseListener extends MouseAdapter {
        private final JTextField textField;

        public TextFieldMouseListener(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            activeTextField = textField;
        }
    }

    private class OperationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source instanceof JButton) {
                String polynomial1Str = polynomial1Text.getText();

                try {
                    Polynomial polynomial1 = PolynomialParser.parse(polynomial1Str);
                    polynomial1 = polynomial1.sortTermsByExponent();

                    Polynomial polynomial2 = null; // Initialize polynomial2 to null

                    try {
                        polynomial2 = PolynomialParser.parse(polynomial2Text.getText());
                        polynomial2 = polynomial2.sortTermsByExponent();
                    } catch (IllegalArgumentException ex) {
                        polynomial2 = null; // Set polynomial2 to null
                    }

                    if (source == addButton) {
                        if (polynomial2 == null) {
                            throw new IllegalArgumentException("Polynomial2 is empty!");
                        }
                        performAddition(polynomial1, polynomial2);
                    } else if (source == subtractButton) {
                        if (polynomial2 == null) {
                            throw new IllegalArgumentException("Polynomial2 is empty!");
                        }
                        performSubtraction(polynomial1, polynomial2);
                    } else if (source == multiplyButton) {
                        if (polynomial2 == null) {
                            throw new IllegalArgumentException("Polynomial2 is empty!");
                        }
                        performMultiplication(polynomial1, polynomial2);
                    } else if (source == divideButton) {
                        if (polynomial2 != null && polynomial2.isZero()) {
                            throw new IllegalArgumentException("Division by zero is not allowed.");
                        }
                        performDivision(polynomial1, polynomial2);
                        performDivision(polynomial1, polynomial2);
                    } else if (source == deriveButton) {
                        performDerivation(polynomial1);
                    } else if (source == integrateButton) {
                        performIntegration(polynomial1);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Polynomial", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private void performAddition(Polynomial polynomial1, Polynomial polynomial2) {
            Polynomial sum = MathematicalOperations.add(polynomial1, polynomial2);
            resultLabel.setText("Sum: " + sum);
        }

        private void performSubtraction(Polynomial polynomial1, Polynomial polynomial2) {
            Polynomial difference = MathematicalOperations.subtract(polynomial1, polynomial2);
            resultLabel.setText("Difference: " + difference);
        }

        private void performMultiplication(Polynomial polynomial1, Polynomial polynomial2) {
            Polynomial product = MathematicalOperations.multiply(polynomial1, polynomial2);
            resultLabel.setText("Product: " + product);
        }

        private void performDivision(Polynomial polynomial1, Polynomial polynomial2) {
            try {
                DivisionResult divisionResult = MathematicalOperations.divide(polynomial1, polynomial2);
                resultLabel.setText("Quotient: " + divisionResult.getQuotient() + ", Remainder: " + divisionResult.getRemainder());
            } catch (ArithmeticException e) {
                resultLabel.setText("Error: Division by zero");
            }
        }

        private void performDerivation(Polynomial polynomial) {
            Polynomial derivative = MathematicalOperations.derive(polynomial);
            resultLabel.setText("Derivative: " + derivative);
        }

        private void performIntegration(Polynomial polynomial) {
            Polynomial integral = MathematicalOperations.integrate(polynomial);
            resultLabel.setText("Integral: " + integral);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame("Polynomial Calculator");
            frame.setVisible(true);
            frame.getContentPane().setBackground(new Color(46, 46, 46)); // Dark grey background
        });
    }
}
