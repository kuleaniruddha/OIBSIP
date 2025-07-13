import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessTheNumberGUI extends JFrame implements ActionListener {

    private int numberToGuess;
    private int attemptsLeft = 5;

    private JLabel heading, message, attempts;
    private JTextField guessField;
    private JButton guessButton, resetButton;

    public GuessTheNumberGUI() {
        // Generate a random number
        numberToGuess = new Random().nextInt(100) + 1;

        // Frame setup
        setTitle("🎯 Guess The Number");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(30, 30, 40));
        setLayout(new GridLayout(6, 1, 10, 10));

        // Heading
        heading = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setForeground(Color.CYAN);
        add(heading);

        // Attempts left
        attempts = new JLabel("Attempts Left: " + attemptsLeft, SwingConstants.CENTER);
        attempts.setFont(new Font("Arial", Font.BOLD, 18));
        attempts.setForeground(Color.ORANGE);
        add(attempts);

        // Message
        message = new JLabel("", SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.PLAIN, 18));
        message.setForeground(Color.LIGHT_GRAY);
        add(message);

        // Input
        guessField = new JTextField();
        guessField.setFont(new Font("Arial", Font.PLAIN, 18));
        guessField.setHorizontalAlignment(JTextField.CENTER);
        add(guessField);

        // Guess button
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 18));
        guessButton.setBackground(new Color(70, 130, 180));
        guessButton.setForeground(Color.WHITE);
        guessButton.addActionListener(this);
        add(guessButton);

        // Reset button
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(new Color(60, 179, 113));
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(e -> resetGame());
        add(resetButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = guessField.getText();
        try {
            int guess = Integer.parseInt(input);
            if (guess < 1 || guess > 100) {
                message.setText("Please enter a number between 1 and 100.");
            } else {
                attemptsLeft--;
                attempts.setText("Attempts Left: " + attemptsLeft);

                if (guess == numberToGuess) {
                    message.setText("🎉 Correct! The number was " + numberToGuess);
                    guessButton.setEnabled(false);
                } else if (guess < numberToGuess) {
                    message.setText("Too low! Try a higher number.");
                } else {
                    message.setText("Too high! Try a lower number.");
                }

                if (attemptsLeft == 0 && guess != numberToGuess) {
                    message.setText("❌ Out of attempts! The number was " + numberToGuess);
                    guessButton.setEnabled(false);
                }
            }
        } catch (NumberFormatException ex) {
            message.setText("⚠️ Please enter a valid number!");
        }

        guessField.setText("");
    }

    private void resetGame() {
        numberToGuess = new Random().nextInt(100) + 1;
        attemptsLeft = 5;
        guessButton.setEnabled(true);
        attempts.setText("Attempts Left: " + attemptsLeft);
        message.setText("");
        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuessTheNumberGUI::new);
    }
}
