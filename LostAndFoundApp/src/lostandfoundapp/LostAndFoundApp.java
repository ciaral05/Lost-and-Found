package lostandfoundapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LostAndFoundApp extends JFrame {

    private ItemManager itemManager;
    private UndoManager undoManager;

    private JTextField idField;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField locationField;
    private JTextField dateField;
    private JTextField personField;

    private JComboBox<String> typeComboBox;

    private JTable itemTable;
    private DefaultTableModel tableModel;

    public LostAndFoundApp() {
        itemManager = new ItemManager();
        undoManager = new UndoManager();

        setTitle("Lost and Found App");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initGUI();
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        formPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        formPanel.add(descriptionField);

        formPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        formPanel.add(locationField);

        formPanel.add(new JLabel("Date:"));
        dateField = new JTextField();
        formPanel.add(dateField);

        formPanel.add(new JLabel("Type:"));
        typeComboBox = new JComboBox<>(new String[]{"Lost", "Found"});
        formPanel.add(typeComboBox);

        formPanel.add(new JLabel("Owner/Finder Name:"));
        personField = new JTextField();
        formPanel.add(personField);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Name", "Description", "Location", "Date", "Type", "Person"});

        itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton undoButton = new JButton("Undo Delete");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(clearButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        addButton.addActionListener((ActionEvent e) -> addItem());
        deleteButton.addActionListener((ActionEvent e) -> deleteItem());
        undoButton.addActionListener((ActionEvent e) -> undoDelete());
        clearButton.addActionListener((ActionEvent e) -> clearFields());
    }

    private void addItem() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String description = descriptionField.getText();
            String location = locationField.getText();
            String date = dateField.getText();
            String person = personField.getText();
            String type = typeComboBox.getSelectedItem().toString();

            Item item;

            if (type.equals("Lost")) {
                item = new LostItem(id, name, description, location, date, person);
            } else {
                item = new FoundItem(id, name, description, location, date, person);
            }

            itemManager.create(item);
            refreshTable();
            clearFields();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for ID.");
        }
    }

    private void deleteItem() {
        int selectedRow = itemTable.getSelectedRow();

        if (selectedRow != -1) {
            int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            Item item = itemManager.read(id);

            if (item != null) {
                undoManager.pushDeletedItem(item);
                itemManager.delete(id);
                refreshTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.");
        }
    }

    private void undoDelete() {
        Item restoredItem = undoManager.undoDelete();

        if (restoredItem != null) {
            itemManager.create(restoredItem);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "No deleted item to restore.");
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        descriptionField.setText("");
        locationField.setText("");
        dateField.setText("");
        personField.setText("");
        typeComboBox.setSelectedIndex(0);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        for (Item item : itemManager.getAllItems()) {
            String person = "";

            if (item instanceof LostItem) {
                person = ((LostItem) item).getOwnerName();
            } else if (item instanceof FoundItem) {
                person = ((FoundItem) item).getFinderName();
            }

            tableModel.addRow(new Object[]{
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getLocation(),
                    item.getDate(),
                    item.getType(),
                    person
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LostAndFoundApp().setVisible(true);
        });
    }
}
