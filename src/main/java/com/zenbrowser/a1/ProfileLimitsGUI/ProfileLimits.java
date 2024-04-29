package com.zenbrowser.a1.ProfileLimitsGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProfileLimits extends JFrame {

    public ProfileLimits() {
        setTitle("Limited Browser");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Profile dropdown setup
        JComboBox<String> profileDropdown = new JComboBox<>(new String[]{"Profile 1", "Profile 2", "Profile 3"});


        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new FlowLayout());
        profilePanel.add(new JLabel("Profile: "));
        profilePanel.add(profileDropdown);


        // URL input setup
        JTextField urlField = new JTextField(20);

        JPanel urlPanel = new JPanel();
        urlPanel.setLayout(new FlowLayout());
        urlPanel.add(new JLabel("Add URL: "));
        urlPanel.add(urlField, BorderLayout.CENTER);

        // Adding Button input
        JButton addAddButton = new JButton("Add URL and Limit to Profile");

        JPanel URLlimitPanel = new JPanel();
        URLlimitPanel.add(addAddButton, BorderLayout.CENTER);

        // Limit input setup
        JTextField limitField = new JTextField(20);

        JPanel limitPanel = new JPanel();
        limitPanel.setLayout(new FlowLayout());
        limitPanel.add(new JLabel("Add Limit: "));
        limitPanel.add(limitField, BorderLayout.CENTER);

        // Table setup
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"URL", "Limit"}, 0);
        JTable urlTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(urlTable);

        add(profilePanel, BorderLayout.NORTH);
        add(urlPanel, BorderLayout.WEST);
        add(tableScrollPane, BorderLayout.SOUTH);
        add(limitPanel, BorderLayout.CENTER);
        add(URLlimitPanel, BorderLayout.EAST);

        addAddButton.addActionListener(e -> {
            // Logic to add URL and limit to the table
            String url = urlField.getText();
            String limit = limitField.getText();
            if (!url.isEmpty() && !limit.isEmpty()) {
                Object[] rowData = {url, limit};
                tableModel.addRow(rowData);
                // Clear input fields after adding
                urlField.setText("");
                limitField.setText("");
            } else {
                // Display error message if fields are empty
                JOptionPane.showMessageDialog(this, "URL or Limit cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProfileLimits browser = new ProfileLimits();
            browser.setVisible(true);
        });
    }
}
