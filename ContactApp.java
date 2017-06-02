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
import javax.naming.RefAddr;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ContactApp extends JFrame{

    private  Timer tm;
    private static LocalDateTime time;
    private JLabel statusBar;
    private JScrollPane scroll;
    private JPanel contactPanel;
    private JButton addButton;
    private JButton homeButton;
    //Mac : /Users/black and white/Desktop/App/Backgrounds/plus.png
    //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\plus.png
    private Icon addIcon = new ImageIcon("C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\plus.png");
    //Mac : /Users/black and white/Desktop/App/Contacts
    //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Contacts
    private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Contacts";
    private Contact[] contacts;
    private JButton[] buttons;
    private JLabel[] lbllastNameOut;
    private JLabel[] lblOneInfoOut;
    private File contactFolder = new File(path);

    public ContactApp() throws ClassNotFoundException, IOException {
        initialize();
    }

    private void initialize() throws ClassNotFoundException, IOException {
        setResizable(false);
        getContentPane().setBackground(new Color(153, 204, 255));
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
 
        contactPanel = loadContacts();
        
        
        scroll = new JScrollPane(contactPanel);
        scroll.setBounds(0,46,480,642);
        getContentPane().add(scroll);
        
        addButton = new JButton(addIcon);
        
        addButton.setBackground(new Color(255, 255, 255));
        addButton.setOpaque(true);
        addButton.setContentAreaFilled(false);
        addButton.setBorderPainted(true);
        addButton.setBounds(400, 2, 40, 40);
        getContentPane().add(addButton);
        
        homeButton = new JButton("Home");
        homeButton.setBounds(6, 700, 470, 70);
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
        contacts = new Contact[contactFolder.listFiles().length];
        buttons = new JButton[contactFolder.listFiles().length];
        JLabel[]lblfirstNames = new JLabel[contactFolder.listFiles().length];
        JLabel[]lbllastName = new JLabel[contactFolder.listFiles().length];
        JLabel[]lblOneInfo = new JLabel[contactFolder.listFiles().length];
        JLabel[]lblInfo = new JLabel[contactFolder.listFiles().length]; 
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
        //Position for the infos Label
        int xlblInfo;
        int ylblInfo = 45;
        
        
        File[] files = new File(path).listFiles();
        for(File file : files){
            if(file.isFile()){
                contactPath=file.getAbsolutePath();
                contacts[cpt] = readFile(contactPath);
                
                xlblInfo = 200;
                
                if(!contactPath.contains(".DS_Store")){ //only for mac but still works on windows
                    buttons[cpt] = new JButton();             
                    buttons[cpt].setBounds(xButton,yButton,100,100);
                    buttons[cpt].setOpaque(true);
                    myPanel.add(buttons[cpt]);
                    buttons[cpt].addActionListener(new Contact_Button(ylblInfo-10)); // button to enter each contact's details (getting the position of the Infolbl
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
                    //display one info between number, home number and email (depending what information has been entered)
                    if(!contacts[cpt].getMobileNumber().equals("")){
                        lblInfo[cpt] = new JLabel(contacts[cpt].getMobileNumber());
                    } else if(!contacts[cpt].getHomeNumber().equals("")){
                        lblInfo[cpt] = new JLabel(contacts[cpt].getHomeNumber());
                    } else if(!contacts[cpt].getEmail().equals("")){
                        lblInfo[cpt] = new JLabel(contacts[cpt].getEmail());
                        xlblInfo = 160;
                    }
                    else 
                        lblInfo[cpt] = new JLabel("No Info");

                    lblInfo[cpt].setBounds(xlblInfo,ylblInfo, 150, 20);
                    myPanel.add(lblInfo[cpt]);
                    
                    //Sets the position for the next labels and buttons
                    yButton += 105;
                    yLabelFirstName += 105;
                    yLabelLastName += 105;
                    yLabelOneInfo += 105;
                    ylblInfo += 105;
                    cpt++;
                }         
    
             }
        }
        //get the JLabel[] to get it out later
        lblOneInfoOut = lblOneInfo;
        lbllastNameOut = lbllastName;
        
        myPanel.setLayout(null);
        myPanel.setBounds(18, 45, 455, 650);
        myPanel.setBackground(new Color(102, 153, 204));
        myPanel.setPreferredSize(new Dimension(455,yButton));
    
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
    
    class Contact_Button implements ActionListener{ //open the contact connected to the button
        
        private int position;
        
        public Contact_Button(int position){
            this.position = position;
        }

        public void actionPerformed(ActionEvent e) {
            Contact readContact = null;
            ContactFrame contact = new ContactFrame();
            int realposition = position / 105;

            try{

                if(lbllastNameOut[realposition] != null){
                    readContact = readFile(path + "/" + lbllastNameOut[realposition].getText() + ".txt");
                }
                else {
                    readContact = readFile(path + "/" + lblOneInfoOut[realposition].getText() + ".txt");
                } 
                
                contact.setTxtFirstName(readContact.getFirstName());
                contact.setTxtLastName(readContact.getLastName());
                contact.setTxtCompany(readContact.getCompanyName());
                contact.setTxtMobileNumber(readContact.getMobileNumber());
                contact.setTxtHomeNumber(readContact.getHomeNumber());
                contact.setTxtEmail(readContact.getEmail());
                contact.setTxtFaxNumber(readContact.getFaxNumber());
                contact.setTxtAddress(readContact.getAddress());
                contact.setTxtBirthday(readContact.getBirthday());
                contact.setTxtNotes(readContact.getNote());
                
                contact.setVisible(true);
                setVisible(false);
            }catch (ClassNotFoundException | IOException e1){
                System.out.println(e1);;
            }
              
        }   
        
    }
    
    class Add_Button implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            ContactFrame newContact = new ContactFrame();
            newContact.setVisible(true);
            setVisible(false);
            
        }
        
    }
    
    class Home_Button implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            try{
                HomeScreen homescreen = new HomeScreen();
                homescreen.setVisible(true);
                setVisible(false);
            } catch(Exception e1){
                System.out.println(e1);
            }

            
        }
        
    }
}
