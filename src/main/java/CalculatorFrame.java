import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    private JTextField displayField;

    final double pi = 3.1415926535897932384626433832795;
    long value = 0;
    long valueAfterDot = 0;
    double actualValue = 0;

    double firstArgument = 0;
    boolean dotPressed = false;
    boolean minus = false;

    double memory = 0;

    String action = "";

    boolean degEnabled = false;

    JButton degreeButton = null;
    JButton radianButton = null;


    public CalculatorFrame() {
        setTitle("Calculator by Vladislav Khorev");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        getContentPane().add(mainPanel);

        displayField = new JTextField();
        displayField.setFont(new Font(displayField.getFont().getName(), Font.PLAIN, 24));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);

        mainPanel.add(displayField, BorderLayout.NORTH);

        // Панель с кнопками
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(6, 6));
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Добавление кнопок на панель
        String[] buttons = {
                "RAD", "DEG", "M", "MR", "M+", "C",
                "sqrt", "1/x", "e", "pi", "exp", "/",
                "log10", "sin","1", "2", "3", "*",
                "log", "cos",  "4", "5", "6", "-",
                "ln", "tan", "7", "8", "9", "+",
                "pow", "ctan", "0", "+/-", ".", "=",
        };

        for (String text : buttons) {
            JButton button = new JButton(text);

            button.setBackground(new Color(163, 238, 255));
            if (text.equals("RAD"))
            {
                degreeButton = button;
                button.setBackground(new Color(255, 106, 0));
            }
            else if (text.equals("DEG"))
            {
                radianButton = button;
            }
            button.addActionListener(new ButtonClickListener());
            buttonsPanel.add(button);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            // Здесь можно добавить логику калькулятора, например:
            switch (command) {
                case "C":
                    value = 0;
                    valueAfterDot = 0;
                    actualValue = 0;
                    dotPressed = false;
                    minus = false;
                    action = "";
                    updateTextFromDigits();
                    break;
                case "1":
                    updateValueByKey(1);
                    updateTextFromDigits();
                    break;
                    case "2":
                        updateValueByKey(2);
                        updateTextFromDigits();
                    break;
                case "3":
                    updateValueByKey(3);
                    updateTextFromDigits();
                    break;
                    case "4":
                        updateValueByKey(4);
                        updateTextFromDigits();
                    break;
                    case "5":
                        updateValueByKey(5);
                        updateTextFromDigits();
                    break;
                    case "6":
                        updateValueByKey(6);
                        updateTextFromDigits();
                    break;
                    case "7":
                        updateValueByKey(7);
                        updateTextFromDigits();
                    break;
                    case "8":
                        updateValueByKey(8);
                        updateTextFromDigits();
                    break;
                    case "9":
                        updateValueByKey(9);
                        updateTextFromDigits();
                    break;
                    case "0":
                        updateValueByKey(0);
                        updateTextFromDigits();
                    break;
                case "e":
                    actualValue = 2.7182818284590452353602874713527;
                    resetKeys();
                    updateText();
                    break;
                case "pi":
                    actualValue = pi;
                    resetKeys();
                    updateText();
                    break;
                case ".":
                    dotPressed = true;
                    break;
                case "+/-":
                    minus = !minus;
                    updateTextFromDigits();
                    break;
                case "1/x":
                    actualValue = 1.0 / actualValue;
                    resetKeys();
                    updateText();
                    break;
                case "sin":
                    if (degEnabled) {
                        actualValue = Math.sin(actualValue * pi/180.0);
                    }
                    else {
                        actualValue = Math.sin(actualValue);
                    }
                    resetKeys();
                    updateText();
                    break;
                case "cos":
                    if (degEnabled) {
                        actualValue = Math.cos(actualValue * pi/180.0);
                    }
                    else {
                        actualValue = Math.cos(actualValue);
                    }
                    resetKeys();
                    updateText();
                    break;
                case "tan":
                    if (degEnabled) {
                        actualValue = Math.tan(actualValue * pi/180.0);
                    }
                    else {
                        actualValue = Math.tan(actualValue);
                    }
                    resetKeys();
                    updateText();
                    break;
                case "ctan":
                    if (degEnabled) {
                        actualValue = 1.0/Math.tan(actualValue * pi/180.0);
                    }
                    else {
                        actualValue = 1.0/Math.tan(actualValue);
                    }
                    resetKeys();
                    updateText();
                    break;
                case "sqrt":
                    if (actualValue < 0) {
                        JOptionPane.showMessageDialog(null, "Value less than 0");
                    }
                    else {
                        actualValue = Math.sqrt(actualValue);
                        resetKeys();
                        updateText();
                    }
                    break;
                case "exp":
                    if (actualValue < 0) {
                        JOptionPane.showMessageDialog(null, "Value less than 0");
                    }
                    else {
                        actualValue = Math.exp(actualValue);
                    }
                    resetKeys();
                    updateText();
                    break;
                case "ln":
                    if (actualValue < 0) {
                        JOptionPane.showMessageDialog(null, "Value less than 0");
                    }
                    else {
                        actualValue = Math.log(actualValue);
                    }
                    resetKeys();
                    updateText();
                    break;
                case "log10":
                    if (actualValue < 0) {
                        JOptionPane.showMessageDialog(null, "Value less than 0");
                    }
                    else {
                        actualValue = Math.log10(actualValue);
                    }
                    resetKeys();
                    updateText();
                    break;
                case "/":
                    firstArgument = actualValue;
                    action = "/";
                    resetKeys();
                    updateText();
                    break;
                case "*":
                    firstArgument = actualValue;
                    action = "*";
                    resetKeys();
                    updateText();
                    break;
                case "+":
                    firstArgument = actualValue;
                    action = "+";
                    resetKeys();
                    updateText();
                    break;
                case "-":
                    firstArgument = actualValue;
                    action = "-";
                    resetKeys();
                    updateText();
                    break;
                case "log":
                    firstArgument = actualValue;
                    action = "log";
                    resetKeys();
                    updateText();
                    break;
                case "pow":
                    firstArgument = actualValue;
                    action = "pow";
                    resetKeys();
                    updateText();
                    break;
                case "=":
                    if (action.equals("/"))
                    {
                        if (actualValue == 0) {
                            JOptionPane.showMessageDialog(null, "Division by 0");
                        }
                        else {
                            actualValue = firstArgument / actualValue;
                        }
                        resetKeys();
                        updateText();
                        action = "";
                    }
                    else if (action.equals("+"))
                    {
                        actualValue = firstArgument+actualValue;
                        resetKeys();
                        updateText();
                        action = "";
                    }
                    else if (action.equals("-"))
                    {
                        actualValue = firstArgument-actualValue;
                        resetKeys();
                        updateText();
                        action = "";
                    }
                    else if (action.equals("*"))
                    {
                        actualValue = firstArgument*actualValue;
                        resetKeys();
                        updateText();
                        action = "";
                    }
                    else if (action.equals("log"))
                    {
                        if ((actualValue <= 0) || (firstArgument <= 0)) {
                            JOptionPane.showMessageDialog(null, "Invalid input");
                        }
                        else {
                            actualValue = Math.log(firstArgument) / Math.log(actualValue);
                        }
                        resetKeys();
                        updateText();
                        action = "";
                    }
                    else if (action.equals("pow"))
                    {
                        if (actualValue <= 0) {
                            JOptionPane.showMessageDialog(null, "Value less than 0");
                        }
                        else {
                            actualValue = Math.pow(firstArgument, actualValue);
                        }
                        resetKeys();
                        updateText();
                        action = "";
                    }
                    break;
                case "M":
                    memory = actualValue;
                    break;
                case "MR":
                    actualValue = memory;
                    resetKeys();
                    updateText();
                    break;
                case "M+":
                    memory+= actualValue;
                    resetKeys();
                    updateText();
                    break;
                case "RAD":
                    degEnabled = false;
                    degreeButton.setBackground(new Color(255, 106, 0));
                    radianButton.setBackground(new Color(163, 238, 255));
                    break;
                case "DEG":
                    degEnabled = true;
                    degreeButton.setBackground(new Color(163, 238, 255));
                    radianButton.setBackground(new Color(255, 106, 0));
                    break;
                default:
                    break;
            }
        }
    }

    public void updateValueByKey(int number)
    {
        if (dotPressed)
        {
            if (valueAfterDot < 1000000000) {
                valueAfterDot = valueAfterDot * 10 + number;
            }
        }
        else {
            if (value < 1000000000) {
                value = value * 10 + number;
            }
        }
    }
    public void updateTextFromDigits()
    {
        actualValue = calcActualValue();

        updateText();
    }

    public void resetKeys()
    {
        value = 0;
        valueAfterDot = 0;
        minus = false;
    }

    public void updateText()
    {
        displayField.setText(Double.toString(actualValue));
    }

    double calcActualValue()
    {
        long digits = Long.toString(valueAfterDot).length();
        double bottomPart = Math.pow(10.0, (digits));

        double result = value + valueAfterDot/bottomPart;
        if (minus)
        {
            return -result;
        }
        else {
            return result;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorFrame().setVisible(true));
    }
}