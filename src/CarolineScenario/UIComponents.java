package CarolineScenario;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;




public class UIComponents
{
    public static JButton CreateJButton(String name, int sizeX, int sizeY, int posX, int posY, ActionListener listener, Frame frame, SpringLayout layout)
    {
        JButton myButton = new JButton(name); //Creates button and sets text
        myButton.addActionListener(listener); //Adds action listener to register click events
        myButton.setPreferredSize(new Dimension(sizeX,sizeY)); //Sets button size
        layout.putConstraint(SpringLayout.WEST,myButton,posX, SpringLayout.WEST,  frame); //Sets button's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myButton,posY, SpringLayout.NORTH,  frame); //Sets button's Y Coordinates
        frame.add(myButton); //Adds button to frame
        return myButton; //Returns completed button to caller.
    }

    public static JTextField CreateAJTextField(int sizeX, int sizeY, int posX, int posY, Frame frame, SpringLayout layout)
    {
        JTextField myTextField = new JTextField(); //Creates JTextField and sets size
        myTextField.setPreferredSize(new Dimension(sizeX,sizeY));
        layout.putConstraint(SpringLayout.WEST,myTextField,posX, SpringLayout.WEST,  frame);//Sets text field's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myTextField,posY, SpringLayout.NORTH,  frame);//Sets text field's Y Coordinates
        frame.add(myTextField); //Adds text field to frame
        return  myTextField; //Returns completed text field to caller
    }

    public static JLabel CreateAJLabel(String text, int sizeX, int sizeY, int posX, int posY, Frame frame, SpringLayout layout)
    {
        JLabel myLabel = new JLabel(text); //Creates label and sets text
        myLabel.setPreferredSize(new Dimension(sizeX,sizeY));
        layout.putConstraint(SpringLayout.WEST,myLabel,posX, SpringLayout.WEST,  frame);//Sets labels's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myLabel,posY, SpringLayout.NORTH,  frame);//Sets label's Y Coordinates
        frame.add(myLabel); // Adds label to frame
        return myLabel; //Returns completed label to caller.
    }
    public static JTextArea CreateAJTextArea(SpringLayout myLayout, Frame myFrame, int x, int y, int w, int h)
    {
        JTextArea myTextArea = new JTextArea(w,h);
        myFrame.add(myTextArea);
        myLayout.putConstraint(SpringLayout.WEST, myTextArea, x, SpringLayout.WEST, myFrame);
        myLayout.putConstraint(SpringLayout.NORTH, myTextArea, y, SpringLayout.NORTH, myFrame);
        return myTextArea;
    }
    public static JComboBox CreateAJComboBox(SpringLayout myLayout, Frame myFrame, int x, int y, int w, int h)
    {
        //JComboBox myComboBox = new JComboBox(w,h);
        JComboBox<String> myComboBox = new JComboBox<String>();
        myFrame.add(myComboBox);
        myLayout.putConstraint(SpringLayout.WEST, myComboBox, x, SpringLayout.WEST, myFrame);
        myLayout.putConstraint(SpringLayout.NORTH, myComboBox, y, SpringLayout.NORTH, myFrame);
        return myComboBox;
    }


}
/*
 public class UIComponents
{
    public static JButton CreateJButton(String name, int sizeX, int sizeY, int posX, int posY, ActionListener listener, Frame frame, SpringLayout layout)
    {
        JButton myButton = new JButton(name); //Creates button and sets text
        myButton.addActionListener(listener); //Adds action listener to register click events
        myButton.setPreferredSize(new Dimension(sizeX,sizeY)); //Sets button size
        layout.putConstraint(SpringLayout.WEST,myButton,posX, SpringLayout.WEST,  frame); //Sets button's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myButton,posY, SpringLayout.NORTH,  frame); //Sets button's Y Coordinates
        frame.add(myButton);
        myButton.setBackground(new Color(108, 50, 208));
        myButton.setBorderPainted(false);
        myButton.setForeground(new Color(0xBBB9AF));
        //myButton.setFocusPainted(false);
        return myButton;
    }

    public static JTextField CreateAJTextField(int size, int posX, int posY, Frame frame, SpringLayout layout)
    {
        JTextField myTextField = new JTextField(size); //Creates JTextField and sets size
        layout.putConstraint(SpringLayout.WEST,myTextField,posX, SpringLayout.WEST,  frame);//Sets text field's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myTextField,posY, SpringLayout.NORTH,  frame);//Sets text field's Y Coordinates
        frame.add(myTextField); //Adds text field to frame
        myTextField.setBackground(new Color(167, 113, 234));
        myTextField.setBorder(null);
        myTextField.setForeground(new Color(0xEEEEE5));
        myTextField.setFont(new Font("dialog",Font.BOLD, 12));
        return  myTextField; //Returns completed text field to caller
    }


    public static JLabel CreateAJLabel(String text, int posX, int posY, Frame frame, SpringLayout layout)
    {
        JLabel myLabel = new JLabel(text); //Creates label and sets text
        layout.putConstraint(SpringLayout.WEST,myLabel,posX, SpringLayout.WEST,  frame);//Sets label's X Coordinates
        layout.putConstraint(SpringLayout.NORTH,myLabel,posY, SpringLayout.NORTH,  frame);//Sets label's Y Coordinates
        frame.add(myLabel); // Adds label to frame
        myLabel.setForeground(new Color(0xBBB9AF));
        return myLabel; //Returns completed label to caller.
    }
    public static JTextArea CreateATextArea(SpringLayout myLayout, Frame myFrame, int x, int y, int w, int h)
    {
        JTextArea myTextArea = new JTextArea(w, h);
        myFrame.add(myTextArea);
        myLayout.putConstraint(SpringLayout.WEST, myTextArea, x, SpringLayout.WEST, myFrame);
        myLayout.putConstraint(SpringLayout.NORTH, myTextArea, y, SpringLayout.NORTH, myFrame);
        myFrame.setBackground(new Color(167, 113, 234));
        myFrame.setForeground(new Color(0xEEEEE5));
        return myTextArea;
    }

    public static JComboBox CreateAComboBox(String[] strMonth, int posX, int posY, int W, int H, Frame frame, SpringLayout myLayout){
        JComboBox comboBox = new JComboBox(strMonth);
        myLayout.putConstraint(SpringLayout.WEST,comboBox,posX, SpringLayout.WEST,  frame);//Sets label's X Coordinates
        myLayout.putConstraint(SpringLayout.NORTH,comboBox,posY, SpringLayout.NORTH,  frame);//Sets label's Y Coordinates
        comboBox.setPreferredSize(new Dimension(W, H));
        comboBox.setSelectedIndex(0);
        frame.add(comboBox);
        comboBox.setForeground(new Color(0xEEEEE5));
        comboBox.setBackground(new Color(167, 113,234));
        comboBox.setBorder(null);
        return comboBox;
    }
}*/


