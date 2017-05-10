import java.awt.BorderLayout;


import javax.swing.*;

/*
Author : 	Jaufray Sornette
Date : 		May 6, 2017
*/

public class ErrorMessage extends JFrame{
    
    private static final long serialVersionUID = 1L;
    private JTextArea error = new JTextArea("Error!\nYou have forgotten a number");
    private Icon gif = new ImageIcon("/Users/black and white/Google Drive/Work/HES-SO/Semester 2/Prog/Projet/200.gif");
    private JLabel label = new JLabel(gif);
    
    public ErrorMessage(){
        super("Error");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 400);
        add(error, BorderLayout.NORTH);
        add(label);
    }

}
 