/*
Author :    Jaufray Sornette
Date :      May 12, 2017
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HomeScreen extends JFrame{
    
    
    private static JLabel hours = new JLabel();    
    private static LocalDateTime time;
    private Timer tm;
    private JPanel up = new JPanel();
    private JPanel down = new JPanel();
    private Icon image = new ImageIcon("/Users/black and white/Desktop/tiger.jpg");
    private JLabel background = new JLabel(image);
    
    public HomeScreen() throws InterruptedException, IOException{
        setSize(480, 800);
        addWindowListener(new Window_Close());
        
        //Every second changes the hours label
        tm = new Timer(1000, new ActionListener() {          
            public void actionPerformed(ActionEvent e) {
                time = LocalDateTime.now();
                hours.setText(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());                
            }
        });
        
        //up.add(hours,BorderLayout.NORTH);
        add(background, BorderLayout.NORTH);
        background.setLayout(new FlowLayout());
        background.add(hours, BorderLayout.NORTH);

        tm.start();
        
    }
    
    
    
}

//Want to have all 3 options: 
//Shutdown
//Cancel
//Reboot

class Window_Close extends WindowAdapter{
    
    public void windowClosing(WindowEvent e) {           
        int ret = JOptionPane.showConfirmDialog(null, "Shutdown options");
        if (ret == JOptionPane.YES_OPTION)
            System.exit(0);
        if(ret == JOptionPane.NO_OPTION){
            System.exit(0);
        }        
    }
}