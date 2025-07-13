import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ATMGUI {
    private static double balance = 10000;
    private static final String USER_ID = "user123";
    private static final String USER_PIN = "1234";
    private static ArrayList<String> history = new ArrayList<>();

    public static void main(String[] args) {
        showLoginScreen();
    }

    private static void showLoginScreen() {
        JFrame loginFrame = new JFrame("✨ ATM Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(350, 200);
        loginFrame.setLayout(new GridLayout(5, 1, 10, 10));
        loginFrame.getContentPane().setBackground(new Color(30, 30, 60));

        JTextField userIdField = new JTextField();
        JPasswordField pinField = new JPasswordField();
        JButton loginBtn = new JButton("🔐 Login");

        JLabel idLabel = new JLabel("User ID:");
        JLabel pinLabel = new JLabel("PIN:");

        idLabel.setForeground(Color.WHITE);
        pinLabel.setForeground(Color.WHITE);

        loginFrame.add(idLabel);
        loginFrame.add(userIdField);
        loginFrame.add(pinLabel);
        loginFrame.add(pinField);
        loginFrame.add(loginBtn);

        loginBtn.setBackground(new Color(0, 204, 255));
        loginBtn.setFocusPainted(false);

        loginBtn.addActionListener(e -> {
            String id = userIdField.getText();
            String pin = new String(pinField.getPassword());

            if (USER_ID.equals(id) && USER_PIN.equals(pin)) {
                loginFrame.dispose();
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "❌ Invalid ID or PIN", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    private static void showMainMenu() {
        JFrame menuFrame = new JFrame("🏦 ATM Dashboard");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(400, 400);
        menuFrame.setLayout(new GridLayout(6, 1, 10, 10));
        menuFrame.getContentPane().setBackground(new Color(20, 24, 50));

        JButton balanceBtn = new JButton("💰 View Balance");
        JButton withdrawBtn = new JButton("💸 Withdraw");
        JButton depositBtn = new JButton("💳 Deposit");
        JButton transferBtn = new JButton("📤 Transfer");
        JButton historyBtn = new JButton("📜 Transaction History");
        JButton exitBtn = new JButton("🚪 Exit");

        JButton[] buttons = {balanceBtn, withdrawBtn, depositBtn, transferBtn, historyBtn, exitBtn};
        Color btnColor = new Color(0, 153, 255);

        for (JButton btn : buttons) {
            btn.setBackground(btnColor);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            menuFrame.add(btn);
        }

        balanceBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(menuFrame, "💰 Current Balance: ₹" + balance)
        );

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(menuFrame, "Enter amount to withdraw:");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0 && amount <= balance) {
                        balance -= amount;
                        history.add("Withdrew ₹" + amount);
                        JOptionPane.showMessageDialog(menuFrame, "✅ ₹" + amount + " withdrawn successfully!");
                    } else {
                        JOptionPane.showMessageDialog(menuFrame, "⚠️ Invalid amount or insufficient balance.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(menuFrame, "❗ Please enter a valid number.");
                }
            }
        });

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(menuFrame, "Enter amount to deposit:");
            if (input != null) {
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0) {
                        balance += amount;
                        history.add("Deposited ₹" + amount);
                        JOptionPane.showMessageDialog(menuFrame, "✅ ₹" + amount + " deposited successfully!");
                    } else {
                        JOptionPane.showMessageDialog(menuFrame, "⚠️ Invalid deposit amount.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(menuFrame, "❗ Please enter a valid number.");
                }
            }
        });

        transferBtn.addActionListener(e -> {
            JTextField receiver = new JTextField();
            JTextField amountField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(2, 2));
            panel.add(new JLabel("Receiver ID:"));
            panel.add(receiver);
            panel.add(new JLabel("Amount (₹):"));
            panel.add(amountField);

            int result = JOptionPane.showConfirmDialog(menuFrame, panel, "Transfer Money", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0 && amount <= balance) {
                        balance -= amount;
                        history.add("Transferred ₹" + amount + " to " + receiver.getText());
                        JOptionPane.showMessageDialog(menuFrame, "✅ Transferred ₹" + amount + " to " + receiver.getText());
                    } else {
                        JOptionPane.showMessageDialog(menuFrame, "⚠️ Invalid amount or insufficient funds.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(menuFrame, "❗ Enter a valid number.");
                }
            }
        });

        historyBtn.addActionListener(e -> {
            if (history.isEmpty()) {
                JOptionPane.showMessageDialog(menuFrame, "📝 No transactions yet.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (String record : history) {
                    sb.append(record).append("\n");
                }
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                JOptionPane.showMessageDialog(menuFrame, new JScrollPane(textArea), "📜 Transaction History", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(menuFrame, "🙏 Thank you for using the ATM!");
            menuFrame.dispose();
        });

        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);
    }
}
