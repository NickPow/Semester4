package com.movietheater;

import javax.swing.*;
import java.awt.*;

public class TheaterGUI {
    private Theater theater;
    private JFrame frame;
    private JPanel seatPanel;
    private JLabel statusLabel;

    public TheaterGUI() {
        theater = new Theater();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Movie Theater Reservation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setLayout(new BorderLayout());

        // Screen Label
        JLabel screenLabel = new JLabel("[ SCREEN ]", SwingConstants.CENTER);
        screenLabel.setFont(new Font("Arial", Font.BOLD, 18));
        screenLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(screenLabel, BorderLayout.NORTH);

        // Seat Grid with 7 visible rows (6 + 1 walkway) and 8 columns
        seatPanel = new JPanel(new GridLayout(7, 8, 5, 5)); // 7 visual rows x 8 cols
        updateSeatButtons();

        // Bottom panel with status + exit
        statusLabel = new JLabel("Click a seat to select/deselect.");
        JButton saveAndExitButton = new JButton("Confirm Seats & Exit");

        saveAndExitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                frame,
                "Confirm seat selection and exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                theater.saveSeatingChart();
                JOptionPane.showMessageDialog(frame, "Your seating has been saved.");
                frame.dispose();
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(saveAndExitButton, BorderLayout.EAST);

        frame.add(seatPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void updateSeatButtons() {
        seatPanel.removeAll();

        for (int row = 0; row < 7; row++) { // 6 seat rows + 1 walkway row
            for (int col = 0; col < 8; col++) {

                // Row 3 = Walkway
                if (row == 3) {
                    JLabel label;
                
                    if (col == 0 || col == 7) {
                        label = new JLabel("[ DOOR ]", SwingConstants.CENTER);
                    } else if (col == 3 || col == 4) {
                        label = new JLabel("[ Walkway ]", SwingConstants.CENTER);
                    } else {
                        label = new JLabel(); // Empty
                    }
                
                    label.setFont(new Font("Arial", Font.PLAIN, 10));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    seatPanel.add(label);
                    continue;
                }
                
                

                // Adjust for skipped pathway row
                int actualRow = row > 3 ? row - 1 : row;

                Seat seat = theater.getSeatByCoordinates(actualRow, col);
                if (seat == null) {
                    seatPanel.add(new JLabel());
                    continue;
                }

                JButton button = new JButton(seat.getSeatId());
                button.setBackground(seat.isReserved() ? Color.RED : Color.GREEN);
                button.setOpaque(true);
                button.setForeground(Color.BLACK);
                button.setFocusPainted(false);
                button.setToolTipText("Click to toggle selection");

                button.addActionListener(e -> {
                    if (seat.isReserved()) {
                        seat.cancel();
                        statusLabel.setText("Deselected seat " + seat.getSeatId());
                    } else {
                        seat.reserve();
                        statusLabel.setText("Selected seat " + seat.getSeatId());
                    }
                    updateSeatButtons();
                });

                seatPanel.add(button);
            }
        }

        seatPanel.revalidate();
        seatPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TheaterGUI::new);
    }
}
