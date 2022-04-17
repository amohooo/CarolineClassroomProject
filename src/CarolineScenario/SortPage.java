package CarolineScenario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class SortPage extends JFrame
{


    private Student[] students;
    private String searchName;
    JTextField[][] textFields;
    SpringLayout myLayout = new SpringLayout();
    public SortPage(Student[] array, String search)
    {
        students = array;
        searchName = search;
        textFields = new JTextField[students.length][3];

        //setSize(250, 250);
        setSize(300, students.length * 50 + 100);
        setLocation(400,50);
        setLayout(myLayout);
        AddWindowListerToFrame();
        SetupTextFields();
        DisplayStudents();
        //DisplaySearchStudent();
        setVisible(true);
    }

    private void AddWindowListerToFrame()
    {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });

    }
    private void SetupTextFields()
    {
        for(int y = 0; y< textFields.length; y++)
        {
            for( int x = 0 ; x< textFields[y].length; x++)
            {
                int xPos = x * 80 + 25;
                int yPos = y * 25 + 25;
                textFields[y][x] = UIComponents.CreateAJTextField(65, 20, xPos,yPos,this,myLayout);
                textFields[y][x].setEditable(false);
            }
        }
    }
    private void DisplayStudents()
    {
        Arrays.sort(students);
        for(int i = 0 ; i < students.length;i++)
        {
                textFields[i][0].setText(students[i].getStudentName());
                textFields[i][1].setText(Integer.toString(students[i].getyPos()));
                textFields[i][2].setText(Integer.toString(students[i].getxPos()));
        }
    }

}
