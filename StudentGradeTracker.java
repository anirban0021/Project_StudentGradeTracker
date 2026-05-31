import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGradeTracker extends JFrame {
    private ArrayList<String> studentNames = new ArrayList<>();
    private ArrayList<Double> studentGrades = new ArrayList<>();

    private JTextField nameField;
    private JTextField gradeField;
    private JTextArea displayArea;

    public StudentGradeTracker() {
        // Setup the Main Window
        setTitle("Student Grade Tracker");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Setup the Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Student Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);

        JButton addButton = new JButton("Add Grade");
        JButton summaryButton = new JButton("Show Summary Report");

        inputPanel.add(addButton);
        inputPanel.add(summaryButton);

        add(inputPanel, BorderLayout.NORTH);

        // Setup the Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Records & Output"));
        add(scrollPane, BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        summaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSummary();
            }
        });
        
        // Allow adding via 'Enter' key in the grade field
        gradeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String gradeStr = gradeField.getText().trim();

        if (name.isEmpty() || gradeStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both the student name and grade.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double grade = Double.parseDouble(gradeStr);
            if (grade < 0) {
                JOptionPane.showMessageDialog(this, "Grade cannot be negative.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            studentNames.add(name);
            studentGrades.add(grade);
            
            displayArea.append(String.format("Added: %-15s | Grade: %.2f\n", name, grade));
            
            // Clear input fields for next entry
            nameField.setText("");
            gradeField.setText("");
            nameField.requestFocus();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid grade. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSummary() {
        if (studentGrades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No grades have been added yet.", "Empty List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        double sum = 0;
        double highest = studentGrades.get(0);
        double lowest = studentGrades.get(0);

        // Track lists of names to handle ties smoothly
        ArrayList<String> highestStudents = new ArrayList<>();
        ArrayList<String> lowestStudents = new ArrayList<>();

        displayArea.append("\n====================================\n");
        displayArea.append("       STUDENT GRADE SUMMARY       \n");
        displayArea.append("====================================\n");

        // First pass: Find the absolute highest and lowest numeric values
        for (int i = 0; i < studentGrades.size(); i++) {
            double grade = studentGrades.get(i);
            sum += grade;
            
            if (grade > highest) {
                highest = grade;
            }
            if (grade < lowest) {
                lowest = grade;
            }
        }

        // Second pass: Collect everyone who matches those top and bottom values
        for (int i = 0; i < studentGrades.size(); i++) {
            double grade = studentGrades.get(i);
            if (grade == highest) {
                highestStudents.add(studentNames.get(i));
            }
            if (grade == lowest) {
                lowestStudents.add(studentNames.get(i));
            }
        }

        double average = sum / studentGrades.size();

        // Join the names nicely with commas if there are multiples
        String highestDisplay = String.join(", ", highestStudents);
        String lowestDisplay = String.join(", ", lowestStudents);

        displayArea.append(String.format("Total Students : %d\n", studentGrades.size()));
        displayArea.append(String.format("Class Average  : %.2f\n", average));
        displayArea.append(String.format("Highest Score  : %.2f (by %s)\n", highest, highestDisplay));
        displayArea.append(String.format("Lowest Score   : %.2f (by %s)\n", lowest, lowestDisplay));
        displayArea.append("====================================\n\n");
    }

    public static void main(String[] args) {
        // Use system look and feel for a more native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentGradeTracker().setVisible(true);
            }
        });
    }
}