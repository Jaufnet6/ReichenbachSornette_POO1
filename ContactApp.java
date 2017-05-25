import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class ContactApp extends JFrame{

    private  Timer tm;
    private static LocalDateTime time;
    private JLabel statusBar;
    private JLabel lblOneInfo;
    private JScrollPane scroll;
    private JPanel contactPanel;
    private JButton addButton;
    private JButton homeButton;
    private Icon addIcon = new ImageIcon("/Users/black and white/Desktop/App/Backgrounds/plus.png");
    private String path = "/Users/black and white/Desktop/App/Contacts";



    public ContactApp() throws ClassNotFoundException, IOException {
        initialize();
    }

    private void initialize() throws ClassNotFoundException, IOException {
        setResizable(false);
        getContentPane().setBackground(new Color(255, 255, 255));
        setBounds(100, 100, 480, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);        
        
        statusBar = new JLabel();
        statusBar.setBackground(new Color(102, 102, 102));
        statusBar.setForeground(new Color(255, 255, 255));
        statusBar.setOpaque(true);
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setBounds(179, 0, 128, 20);
        getContentPane().add(statusBar);
        setTime();       
 
        /*
        contactPanel = new JPanel();
        contactPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        contactPanel.setBackground(Color.WHITE);
        contactPanel.setBounds(18, 45, 455, 650);
        contactPanel.setLayout(null);
        */
        contactPanel = loadContacts();
        
        lblOneInfo = new JLabel("");
        lblOneInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblOneInfo.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        lblOneInfo.setBounds(40, 45, 100, 20);
        contactPanel.add(lblOneInfo);
        
        scroll = new JScrollPane(contactPanel);
        scroll.setBounds(0,46,480,642);
        getContentPane().add(scroll);
        
        addButton = new JButton(addIcon);
        
        addButton.setBackground(new Color(255, 255, 255));
        addButton.setOpaque(true);
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(false);
        addButton.setBounds(400, 2, 40, 40);
        getContentPane().add(addButton);
        
        homeButton = new JButton("Home");
        homeButton.setBounds(18, 700, 455, 70);
        getContentPane().add(homeButton);
        
        addButton.addActionListener(new Add_Button());  
        homeButton.addActionListener(new Home_Button());
        
    }
    
    private void setTime(){
        
        tm = new Timer(1000, new ActionListener() {          
            public void actionPerformed(ActionEvent e) {
                time = LocalDateTime.now();
                statusBar.setText(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());                
            }
        });
        tm.start();
    }
    
    private JPanel loadContacts() throws ClassNotFoundException, IOException{
        JPanel myPanel = new JPanel();
        File contactFolder = new File(path);
        Contact[] contacts = new Contact[contactFolder.listFiles().length];
        JButton[] buttons = new JButton[contactFolder.listFiles().length];
        JLabel[] lblfirstNames = new JLabel[contactFolder.listFiles().length];
        JLabel[] lbllastName = new JLabel[contactFolder.listFiles().length];
        JLabel[] lblOneInfo = new JLabel[contactFolder.listFiles().length];


        String contactPath;
        
        int cpt = 0;
        //Position for the first Button
        int xButton = 350;
        int yButton = 5;
        //Position for the first name label
        int xLabelFirstName = 40;
        int yLabelFirstName = 25;
        
        //Position for the last name label
        int xLabelLastName = 40;
        int yLabelLastName = 55;
        //Position for the OneInfo label
        int xLabelOneInfo = 40;
        int yLabelOneInfo = 45;
        
        
        File[] files = new File(path).listFiles();
        for(File file : files){
            if(file.isFile()){
                contactPath=file.getAbsolutePath();
                contacts[cpt] = readFile(contactPath);
                
                
                if(!contactPath.contains(".DS_Store")){
                    buttons[cpt] = new JButton(); //still have to put the photos            
                    buttons[cpt].setBounds(xButton,yButton,100,100);
                    buttons[cpt].setOpaque(true);
                    myPanel.add(buttons[cpt]);
                  //If both first name and last name are present
                    if(!contacts[cpt].getFirstName().equals("") && !contacts[cpt].getLastName().equals("")){
                        lblfirstNames[cpt] = new JLabel(contacts[cpt].getFirstName());
                        lblfirstNames[cpt].setBounds(xLabelFirstName,yLabelFirstName, 100, 20);
                        lbllastName[cpt] = new JLabel(contacts[cpt].getLastName());
                        lbllastName[cpt].setBounds(xLabelLastName,yLabelLastName, 100, 20);
                        myPanel.add(lblfirstNames[cpt]);
                        myPanel.add(lbllastName[cpt]);
                        //if only first name is present
                    } else if(contacts[cpt].getFirstName().equals("")){
                        lblOneInfo[cpt] = new JLabel(contacts[cpt].getLastName());
                        lblOneInfo[cpt].setBounds(xLabelOneInfo,yLabelOneInfo, 100, 20);
                        myPanel.add(lblOneInfo[cpt]);
                        //if only last name is present
                    } else if(contacts[cpt].getLastName().equals("")){
                        lblOneInfo[cpt] = new JLabel(contacts[cpt].getFirstName());
                        lblOneInfo[cpt].setBounds(xLabelOneInfo,yLabelOneInfo, 100, 20);
                        myPanel.add(lblOneInfo[cpt]);
                    } 
                    yButton += 105;
                    yLabelFirstName += 105;
                    yLabelLastName += 105;
                    yLabelOneInfo += 105;
                    cpt++;
                }         
    
             }
        }
        
        myPanel.setLayout(null);
        //myPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        myPanel.setBounds(18, 45, 455, 650);
        myPanel.setBackground(Color.WHITE);
        myPanel.setPreferredSize(new Dimension(455,yButton+220));
    
        return myPanel;
    }
    
    private Contact readFile(String contactPath) throws ClassNotFoundException, IOException{  //Read an already saved contact
        if(contactPath.contains(".txt")){
            FileInputStream fichier = new FileInputStream(contactPath);
            Contact person;
            BufferedInputStream bfichier = new BufferedInputStream(fichier);
            ObjectInputStream obfichier = new ObjectInputStream(bfichier);
            person = (Contact) obfichier.readObject();
            obfichier.close();
            return person;
        }
        
        return null;
    }
    
    class Add_Button implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            ContactFrame newContact = new ContactFrame();
            newContact.setVisible(true);
            
        }
        
    }
    
    class Home_Button implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            
        }
        
    }
    
}
