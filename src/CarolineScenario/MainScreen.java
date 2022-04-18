package CarolineScenario;


import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Array;
import java.util.Arrays;
import java.util.LinkedList;

public class MainScreen extends JFrame implements ActionListener
{
    SpringLayout myLayout = new SpringLayout();
    JButton btnExit, btnOpen, btnSave, btnBinary, btnSort,btnRAF,btnClear;
    JTextField[][] textFields = new JTextField[19][10]; //text field table is set with 19 rows and 10 columns
    JTextField txtSearch, txtTeacher, txtClass, txtRoom, txtDate;
    JLabel lblTeacher, lblClass, lblRoom, lblDate, lblNote;
//this is the main screen set up
    public MainScreen()
    {
        setSize(1200,650);
        setLocation(200,200);
        AddWindowListerToFrame();
        setLayout(myLayout);
        SetUpButtons();
        SetTextField();
        SetupLabels();
        setVisible(true);
    }
    //this method is for set up button
    private void SetUpButtons()//set button name, location, size and style
    {
        btnExit = UIComponents.CreateJButton("Exit",80,25,1050,540,this,this,myLayout);
        btnOpen = UIComponents.CreateJButton("Open",80,25,50,520,this,this,myLayout);
        btnSave = UIComponents.CreateJButton("Save",80,25,162,520,this,this,myLayout);
        btnBinary = UIComponents.CreateJButton("Binary Search",120,25,610,520,this,this,myLayout);
        btnSort = UIComponents.CreateJButton("Sort",80,25,274,520,this,this,myLayout);
        btnRAF = UIComponents.CreateJButton("RAF",80,25,498,520,this,this,myLayout);
        btnClear = UIComponents.CreateJButton("Clear",80,25,386,520,this,this,myLayout);
    }
    private void SetupLabels() //set Label text, location, size and style
    {
        lblTeacher = UIComponents.CreateAJLabel("* Teacher Name: ", 120, 25, 50, 10, this, myLayout);
        lblClass = UIComponents.CreateAJLabel("* Class: ", 80, 25, 270, 10, this, myLayout);
        lblRoom = UIComponents.CreateAJLabel("* Room:", 80, 25, 490, 10, this, myLayout);
        lblDate = UIComponents.CreateAJLabel("* Date:", 80, 25, 710, 10, this, myLayout);
        lblNote = UIComponents.CreateAJLabel("Note: * is mandatory to be filled, otherwise it would save to an empty file", 980, 25, 50, 550, this, myLayout);
    }

    private void SetTextField() //set text location, size and style
    {
        txtSearch = UIComponents.CreateAJTextField(110, 25, 740,520,this,myLayout);
        ///For y (vertically) loading from the first table from the first column to the left direction x (horizontally)till the end of this line:
        // for (int x = 0; x< textFields.length[y]; x++), after loading the first line then go to the second line… up until the last line：
        // for ( int y = 0; y< textFields.length[y]; y++):

        for (int y = 0; y < textFields.length; y++)
        {
            for ( int x = 0; x < textFields[y].length ; x++ )
            {
                int xPos = 108 * x + 50;
                int yPos = 24 * y + 40;
                textFields[y][x] = UIComponents.CreateAJTextField(110, 25, xPos,yPos,this,myLayout);
                textFields[y][x].addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        super.focusLost(e);
                        JTextField text = (JTextField)e.getSource();
                        if (text.getText().isEmpty()== false && text.getText().equalsIgnoreCase("Desk") || text.getText().equalsIgnoreCase("BKGRND FILL"))
                        {
                            text.setForeground(new Color(0x00D0FF));
                            text.setBackground(new Color(0x00D0FF));
                        }
                        else
                        {
                            text.setBackground(Color.WHITE);
                        }
                    }
                });
            }
        }
        txtTeacher = UIComponents.CreateAJTextField(110, 25, 150, 10, this, myLayout);
        txtClass = UIComponents.CreateAJTextField(110, 25, 320, 10, this, myLayout);
        txtRoom = UIComponents.CreateAJTextField(110, 25, 540, 10, this, myLayout);
        txtDate = UIComponents.CreateAJTextField(110, 25, 755, 10, this, myLayout);
    }


    private void AddWindowListerToFrame()
    {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }// Close the application

    @Override
    public void actionPerformed(ActionEvent e) // key (button) listener, performing action when click different buttons
    {
        if (e.getSource() == btnExit)
        {
            System.exit(0);
        }
        if (e.getSource() == btnSave)
        {
            SaveToFile();
            //SaveToRafFile();
            //Save to csv file
        }
        if (e.getSource() == btnOpen)
        {
            //OpenFromFile();
            SelectFileToLoad();
            //Select and open select csv or raf file
        }
        if (e.getSource() == btnBinary)
        {
            Student[] students = GatherStudentDetailsForSortForm();
            for (int y = 0; y < textFields.length; y++) {
                for (int x = 0; x < textFields[y].length; x++) {
                    if (textFields[y][x].getText().equalsIgnoreCase(txtSearch.getText()) && textFields[y][x].getText().isEmpty()== false)
                    {
                        textFields[y][x].setBackground(Color.MAGENTA);
                    }//highlight the student's in the table
                    if (txtSearch.getText().isEmpty() == true && textFields[y][x].getText().equalsIgnoreCase("Desk") == false)
                    {
                        textFields[y][x].setBackground(Color.white);
                    }
                    //if the typed in text in Search text field is matching the student's name in above table, highlight the name in the table.
                    //if add for loop here, the name will be highlighted only when doing binary search
                    //if add in the method of GatherStudentDetailsForSortForm, it will highlight the student's name when doing both sorting and binary search.
                }
            }
            new SortingPage(students, txtSearch.getText());
        }
        if (e.getSource() == btnClear)
        {
            ClearTables();
        }
        if (e.getSource() == btnRAF)
        {
            SaveToRafFile();
        }
        if (e.getSource() == btnSort)
        {
            Student[] students = GatherStudentDetailsForSortForm();
            new SortPage(students, txtSearch.getText() );
        }
    }
    private void ClearTables()
    {
        txtTeacher.setText("");
        txtClass.setText("");
        txtRoom.setText("");
        txtDate.setText("");
        //Clear the top rows (4 text fields)  in the application by setting the text field empty.
        for (int y = 0; y < textFields.length; y++ )
        {
            for (int x = 0; x < textFields[y].length; x++)
            {
                textFields[y][x].setText("");
                textFields[y][x].setBackground(Color.WHITE);
            }
        }
        // clear the textFields table below, set all text fields empty and set the background colour to white
    }

    private Student[] GatherStudentDetailsForSortForm()
    {
        LinkedList<Student>  studentList = new LinkedList<>();
        //grab the student list for the linkedList
        for(int y = 0 ;  y < textFields.length ; y++)
        {
            for( int x = 0; x< textFields[y].length; x++)
            {
                if(textFields[y][x].getText().isEmpty()== false &&
                        textFields[y][x].getText().equalsIgnoreCase("Desk") == false &&
                        textFields[y][x].getText().equalsIgnoreCase("Front Desk") == false)
                //when getting the student name, skip all empty fields, skip all fields with "desk", and skip the field with "Front Desk"
                {
                    studentList.add(new Student(textFields[y][x].getText(),y,x));
                }
//                if (textFields[y][x].getText().equalsIgnoreCase(txtSearch.getText()) && textFields[y][x].getText().isEmpty()== false)
//                {
//                    textFields[y][x].setBackground(Color.MAGENTA);
//                }//highlight the student's in the table
                if (txtSearch.getText().isEmpty() == true && textFields[y][x].getText().equalsIgnoreCase("Desk") == false)
                {
                    textFields[y][x].setBackground(Color.white);
                }
                //set the colour back to white when the name is not being searched
                //if add in here, it will highlight the student's name when doing both sorting and binary search when put student's name in the search text field.
            }
        }
        return studentList.toArray(new Student[studentList.size()]);

    }

    private void SaveToFile()
    {
        FileDialog write = new FileDialog(this, "Pick File Location", FileDialog.SAVE);
        write.setDirectory("C://"); //set the direction of the file to be saved in
        write.setVisible(true);
        String filePath = write.getDirectory() + write.getFile();
        if (write.getFile() == "")
        {
            return;
        }
        if ( !filePath.endsWith(".csv")) //if the file type is csv file
        {
            filePath += ".csv";// set to save the default file type to csv file if the file is not a csv file
        }

        try
        {
            if(txtTeacher.getText().equals("") || txtClass.getText().equals("") || txtRoom.getText().equals("") ||
                    txtDate.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Make sure fill up all top rows");
            }//make sure all top row text field needs to be filled out then to be saved
            else
            {
                BufferedWriter br = new BufferedWriter(new FileWriter(filePath));
                br.write("Teacher: " + "," + txtTeacher.getText() + "\n" + "Class: " + "," +
                        txtClass.getText() + "\n" + "Room: " + "," + txtRoom.getText() + "\n" + "Date: " +
                        "," +  txtDate.getText() + "\n" );
                //the top row will be saved with the form of:
                //first line: "Teacher:" + the text in the teacher's text field
                //Second line: "Class:" + the text in the Class's text field
                //Third line: "Room: " + the text in the room's text field
                //Fourth line: "Date" + the text in the date's text field

                for (int y = 0; y < textFields.length; y++)
                {
                    for (int x = 0; x < textFields[y].length; x++)
                    {
                        if (textFields[y][x].getText().isEmpty() == false)// && textFields[y][x].getText().equalsIgnoreCase("Desk") == false)
                        {
                            if (textFields[y][x].getText().equalsIgnoreCase("Desk") == true) {
                                String desks = x + "," + y + "," + textFields[y][x].getText() + "," + "BLUE";
                                br.write(desks);
                            }
                            else {
                                String students = x + "," + y + "," + textFields[y][x].getText();
                                br.write(students);
                            }
                            br.newLine();
                        }
                        //From the fifth line, if the text in text field is desk
                        // it would be saved " x coordinate, y coordinate, desk, blue"
                        // From the fifth line, if the text in text field is student name
                        // it would be saved " x coordinate, y coordinate, student name"
                    }
                }
                br.close();
                JOptionPane.showMessageDialog(null, "Record Saved");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, " Something went wrong try again");
        }
//if the file has been saved successfully, show message "record saved."
//if not, show message "something went wrong try again"
    }


    private void OpenFromFile(String filePath)
    {

        try
        {
            BufferedReader bv = new BufferedReader(new FileReader(filePath));
            String line;
            //int i = 0;
            while((line = bv.readLine()) !="")
            {
                String[] temp1 = line.split(",");
                txtTeacher.setText(temp1[1]);
                //read from the first line the second column from the csv file then write to the teacher's text field in the top row in the application
                while((line = bv.readLine()) !="")
                {
                    String[] temp2 = line.split(",");
                    txtClass.setText(temp2[1]);
                    //read from the second line the second column from the csv file then write to the Class's text field in the top row in the application
                    while((line = bv.readLine()) !="")
                    {
                        String[] temp3 = line.split(",");
                        txtRoom.setText(temp3[1]);
                        //read from the third line the second column from the csv file then write to the room's text field in the top row in the application
                        while((line = bv.readLine()) !="")
                        {
                            String[] temp4 = line.split(",");
                            txtDate.setText(temp4[1]);
                            //read from the fourth line the second column from the csv file then write to the date's text field in the top row in the application
                            while ((line = bv.readLine()) != "")
                            {
                                String[] temp = line.split(",");
                                int xPos = Integer.parseInt(temp[0]);
                                int yPos = Integer.parseInt(temp[1]);
                                textFields[yPos][xPos].setText(temp[2]);
                                if (temp[2].equalsIgnoreCase("BKGRND FILL") || temp[2].equalsIgnoreCase("Desk"))
                                {
                                    textFields[yPos][xPos].setText("Desk");
                                    textFields[yPos][xPos].setForeground(new Color(0x00D0FF));
                                    textFields[yPos][xPos].setBackground(new Color(0x00D0FF));
                                }
                                //read from the fifth line in the csv file, first column set the coordinate x
                                //read from the fifth line in the csv file, second column set the coordinate y
                                //read from the fifth line in the csv file, third column read the name with its location coordinate x and coordinate y
                                // if the name reading is "BKGRD FILL" or "desk" change the text to "desk" put into the textfiled with coordinate. change that block background and the word colour to blue

                            }
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {

        }
    }
    private void SelectFileToLoad()//setting a method of selecting the file
    {
        FileDialog read = new FileDialog(this, "Pick File Open", FileDialog.LOAD);
        read.setDirectory("C://"); // where the location should be chosen from
        read.setVisible(true);
        String filePath = read.getDirectory() + read.getFile();

        if(read.getFile() == null)
        {
            return;
        }
        ClearTables();
        if(read.getFile().endsWith(".RAF"))//to make sure the file need to be chosen is an RAF file.
        {
            //Run raf read method
            ReadFromRafFile(filePath);
            return;
        }
        OpenFromFile(filePath);
    }
    private void SaveToRafFile()
    {
        FileDialog read = new FileDialog(this, "Save to RAF File", FileDialog.SAVE);
        read.setDirectory("C://"); // set the location that the RAF file should be saving to
        read.setVisible(true);
        String filePath = read.getDirectory() + read.getFile();

        if(read.getFile() == null)
        {
            return;
        }
        if (!filePath.endsWith(".RAF")){
            filePath += ".RAF";
        }
        // set the saved file type "RAF" file
        try
        {
            RandomAccessFile raf = new RandomAccessFile(filePath,"rw");
            int count = 4;

            for (int y = 0; y < textFields.length; y++)
            {
                for ( int x = 0; x < textFields[y].length ; x++ )
                {
                    if (textFields[y][x].getText().isEmpty() == false)
                    {
                        int pointer = count * 100; //write to the writing point start with 400 (4 * 100) and every 100
                        raf.seek(pointer);
                        raf.writeUTF(textFields[y][x].getText()); //what's been writing is the name put in to the application table with coordinate x and y
                        raf.seek(pointer + 50);
                        raf.writeInt(y);//write the coordinate y from the writing point start with 450 (4 * 100 + 50) and every 100 after 450
                        raf.seek(pointer + 75);
                        raf.writeInt(x);//write coordinate x from the writing point start with 475 (4 * 100 + 75) and every 100 after 475

                        count ++;
                    }
                }
            }
            raf.seek(0); //write to the writing point start with 0 with the teacher's text field in the top row in the application
            raf.writeUTF (txtTeacher.getText());
            raf.seek(100);//write to the writing point start with 100 with the class's text field in the top row in the application
            raf.writeUTF (txtClass.getText());
            raf.seek(200);//write to the writing point start with 200 with the room's text field in the top row in the application
            raf.writeUTF (txtRoom.getText());
            raf.seek(300);//write to the writing point start with 300 with the date's text field in the top row in the application
            raf.writeUTF (txtDate.getText());
            raf.close();
        }


        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    private void ReadFromRafFile(String filePath)
    {
        try
        {
            RandomAccessFile raf = new RandomAccessFile(filePath,"r");
            int count = 4;
            long length = raf.length();

            raf.seek(0 );//read from the reading point start with 0 put into teacher's text field in the top row in the application
            txtTeacher.setText( raf.readUTF() );
            raf.seek(100);//read from the reading point start with 100 put into class's text field in the top row in the application
            txtClass.setText( raf.readUTF());
            raf.seek(200);//read from the reading point start with 200 put into room's text field in the top row in the application
            txtRoom.setText( raf.readUTF());
            raf.seek(300);//read from the reading point start with 300 put into date's text field in the top row in the application
            txtDate.setText( raf.readUTF());

            while(count * 100 < length)
            {
                int pointer = count * 100;
                raf.seek(pointer);
                String name = raf.readUTF();
                //read from the reading point start with 400 (4 * 100) and every 100 after 400 from RAF file set to name then write the name into Application
                if(name.isEmpty())
                {
                    count ++;
                    continue;
                }
                //skip the empty space/location
                raf.seek(pointer + 50);
                int yPos = raf.readInt();
                //read from the reading point start with 450 (4 * 100 + 50) and every 100 after 450 from RAF file with coordinate y
                raf.seek( pointer + 75);
                //read from the reading point start with 475 (4 * 100 + 75) and every 100 after 475 from RAF file with coordinate y
                int xPos = raf.readInt();
                textFields[yPos][xPos].setText(name);
                if(textFields[yPos][xPos].getText().equalsIgnoreCase("Desk"))
                {
                    textFields[yPos][xPos].setBackground(new Color(0x00D0FF));
                    textFields[yPos][xPos].setForeground(new Color(0x00D0FF));
                }
                // when what's been reading is "desk", set the background colour and the word "desk" to blue
                else
                {
                    textFields[yPos][xPos].setForeground(Color.black);
                }
                count++;
                //set other student name write in black

            }
            raf.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
}
