import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*java.swing is the library which is used to create GUIs.
java awt provides fundamental toolkit for building graphical applications in Java.
java.awt.event.ActionEvent and actionListner handle events related to user actions, such as button
clicks and menu selections, in graphical user interfaces (GUIs). It allow  to respond to user
interactions by defining actions and event listeners.*/

public class ToDoList {

    private JTextArea taskTextArea;
    private JTextField taskTextField;
    //text area and text width with the variable name tasktextarea and tasktextfield

    public ToDoList() {  //creating consructer
        taskTextArea = new JTextArea(20, 30);  //we have given the size of textarea of 20 rows and 30 columns
        taskTextField = new JTextField(20); //and textfield has size of 20 columns

        JFrame frame = new JFrame("To-Do List");  //title of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //frame closing 
        frame.setSize(700, 700); //size of frame
        frame.getContentPane().setBackground(new Color(250, 250, 250)); //background color of frame
        /* The content pane is a container that is used to add components to a frame */
        taskTextArea.setForeground(Color.red);  //background color of frame

        JPanel mainPanel = new JPanel(); // is a container that can hold other GUI components.
        mainPanel.setLayout(new BorderLayout()); 
        mainPanel.setBackground(new Color(240, 240, 240)); // gray color with RGB values

        mainPanel.add(new JScrollPane(taskTextArea), BorderLayout.CENTER);
        taskTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); 

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout()); //arranges components in a row, and when the row is full, it wraps to the next row.
        inputPanel.setBackground(new Color(220, 220, 220)); 

        //using interface method named actionperformed
        
        JButton addButton = new JButton("Add");
        styleButton(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        JButton deleteButton = new JButton("Delete");
        styleButton(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        JButton editButton = new JButton("Edit");
        styleButton(editButton);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTask();
            }
        });

        JButton markAsReadButton = new JButton("Mark as Read");
        styleButton(markAsReadButton);
        markAsReadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markAsRead();
            }
        });

        JButton sizeButton = new JButton("Size");
        styleButton(sizeButton);
        sizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFontSize();
            }
        });

        JButton styleButton = new JButton("Style");
        styleButton(styleButton);
        styleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFontStyle();
            }
        });

        //all the buttons are added into input panel
        inputPanel.add(taskTextField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(editButton);
        inputPanel.add(markAsReadButton);
        inputPanel.add(sizeButton);
        inputPanel.add(styleButton);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);

        frame.setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(123, 123, 255));
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    private void addTask() {
        String task = taskTextField.getText(); //taking task in string in textfield if the task is not empty it will append string into textarea
        if (!task.isEmpty()) {
            taskTextArea.append(task + "\n");
            taskTextField.setText("");  //and further clearing the textfield
        }
    }

    //method of delete the task
    private void deleteTask() {
        String taskToDelete = JOptionPane.showInputDialog("Enter the task to delete:");
        //first tastTodelete wil take a string if that string exists in textarea then it will delete it otherwise it will show the messege that string not fount
        String text = taskTextArea.getText();
        if (text.contains(taskToDelete)) {
            text = text.replaceFirst(taskToDelete + "\n", "");
            taskTextArea.setText(text);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found!");
        }
    }

    //edit task
    private void editTask() {
        //in this method tasktoedit will take any task from user if it exits in textarea then it will ask user to modify it otherwise text not found message will be displayed
        String taskToEdit = JOptionPane.showInputDialog("Enter the task to edit:");
        String newText = JOptionPane.showInputDialog("Enter the new task:");
        String text = taskTextArea.getText();
        if (text.contains(taskToEdit)) {
            text = text.replaceFirst(taskToEdit + "\n", newText + "\n");
            taskTextArea.setText(text);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found!");
        }
    }

    private void markAsRead() {
        //mark as read methed will ask user that which task he wants to complete then if it find the entered task by user in textarea then in side of it it will add completed 
        String taskToMark = JOptionPane.showInputDialog("Enter the task to mark as completed:");
        String text = taskTextArea.getText();
        if (text.contains(taskToMark)) {
            text = text.replaceFirst(taskToMark + "\n", taskToMark + " (Completed)\n");
            taskTextArea.setText(text);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found!");
        }
    }

    //changing font size it will provide different of sizes and default size id 14
    private void changeFontSize() {
        String[] options = {"12", "14", "16", "18", "20"};
        String selectedSize = (String) JOptionPane.showInputDialog(null, "Select font size:", "Font Size", JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        if (selectedSize != null) {
            int fontSize = Integer.parseInt(selectedSize); //converting to integer
            Font currentFont = taskTextArea.getFont();
            taskTextArea.setFont(new Font(currentFont.getName(), currentFont.getStyle(), fontSize));
        }
    }

    private void changeFontStyle() {
        //giving different style options to user
        String[] options = {"Plain", "Bold", "Italic", "Bold and Italic"};
        String selectedStyle = (String) JOptionPane.showInputDialog(null, "Select font style:", "Font Style", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        //deafault it is plain
        if (selectedStyle != null) {
            int style = Font.PLAIN;
            if (selectedStyle.equals("Bold")) {
                style = Font.BOLD;
            } else if (selectedStyle.equals("Italic")) {
                style = Font.ITALIC;
            } else if (selectedStyle.equals("Bold and Italic")) {
                style = Font.BOLD | Font.ITALIC;
            }

            Font currentFont = taskTextArea.getFont();
            taskTextArea.setFont(new Font(currentFont.getName(), style, currentFont.getSize()));
        }
    }

    public static void main(String[] args) {
        //this method is used to execute a Runnable on the Event Dispatch Thread (EDT)
        //inner class that implements the Runnable interface which defines the code to be executed when the Runnable is run.
        //Inside the run method, it creates an instance of a ToDoListApp. This suggests that ToDoListApp is the main class or entry point for your Swing application.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoList();
            }
        });
    }
}
