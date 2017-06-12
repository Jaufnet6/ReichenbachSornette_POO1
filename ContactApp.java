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
import javax.swing.JTextField;
import java.awt.Font;

public class ContactApp extends JFrame{

    private  Timer tm;
    private static LocalDateTime time;
    private JLabel statusBar;
    private JScrollPane scroll;
    private JScrollPane scrollSearch;
    private JPanel contactPanel;
    private JPanel searchPanel;
    private JButton addButton;
    private JButton homeButton;
    private JButton cancelButton;
    private JButton searchButton;
    //Mac : /Users/black and white/Desktop/App/Backgrounds/plus.png
    //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\plus.png
    private Icon addIcon = new ImageIcon("C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\plus.png");
    //Mac : /Users/black and white/Desktop/App/Contacts
    //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Contacts
    private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Contacts";
    private JLabel[] lbllastNameOut;
    private JLabel[] lblOneInfoOut;
    private File contactFolder = new File(path);
    private JTextField txtSearch;
       
    private JButton[]buttons;
    private JLabel[]lblfirstNames;
    private JLabel[]lbllastName;
    private JLabel[]lblOneInfo;
    private JLabel[]lblInfo;
    //Position for the panels
    private int xButton = 350;
    private int xLabelFirstName = 40;
    private int xLabelLastName = 40;
    private int xLabelOneInfo = 40;
    private int xlblInfo = 180;
    private JLabel lblbackground;


    public ContactApp() throws ClassNotFoundException, IOException {
        super("Contacts");
    	initialize();
    }

    private void initialize() throws ClassNotFoundException, IOException {
        setResizable(false);
        getContentPane().setBackground(Color.DARK_GRAY);
        setBounds(100, 100, 480, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);        
        
        statusBar = new JLabel();
        statusBar.setFont(new Font("Avenir Next", Font.BOLD, 15));
        statusBar.setBackground(Color.DARK_GRAY);
        statusBar.setForeground(new Color(255, 255, 255));
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setBounds(179, 0, 128, 20);
        getContentPane().add(statusBar);
        setTime();       
 
        contactPanel = loadContacts(); 
        
        scroll = new JScrollPane(contactPanel);
        scroll.setBounds(0,65,480,656);
        getContentPane().add(scroll);
        
        addButton = new JButton(addIcon);
        
        addButton.setBackground(new Color(255, 255, 255));
        addButton.setOpaque(true);
        addButton.setContentAreaFilled(true);
        addButton.setBorderPainted(false);
        addButton.setBounds(427, 28, 30, 30);
        getContentPane().add(addButton);
        
        homeButton = new JButton("HOME");
        homeButton.setForeground(Color.DARK_GRAY);
        homeButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
        homeButton.setBounds(0, 723, 480, 55);
        getContentPane().add(homeButton);
        
        txtSearch = new JTextField();
        txtSearch.setForeground(Color.LIGHT_GRAY);
        txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
        txtSearch.setText("Search...");
        txtSearch.setFont(new Font("Avenir Next", Font.BOLD, 13));
        txtSearch.setBounds(100, 23, 312, 40);
        getContentPane().add(txtSearch);
        txtSearch.setColumns(10);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.DARK_GRAY);
        cancelButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
        cancelButton.setBounds(6, 23, 89, 40);
        cancelButton.setVisible(false);
        getContentPane().add(cancelButton);
        
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
        searchButton.setBounds(6, 23, 89, 40);
        getContentPane().add(searchButton);
        
        //Mac : /Users/black and white/Desktop/App/Backgrounds/background.png
        //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\background.png
        lblbackground = new JLabel(new ImageIcon("C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\background.png"));
        lblbackground.setBounds(0, 0, 476, 776);
        getContentPane().add(lblbackground);
        
        addButton.addActionListener(new Add_Button());  
        homeButton.addActionListener(new Home_Button());
        searchButton.addActionListener(new Search_Button());;
        cancelButton.addActionListener(new Cancel_Button());
    }
    
    private void setTime(){
        
        tm = new Timer(50, new ActionListener() {          
            public void actionPerformed(ActionEvent e) {
                time = LocalDateTime.now();
                statusBar.setText(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());                
            }
        });
        tm.start();
    }
    
    private JPanel loadContacts() throws ClassNotFoundException, IOException{
        JPanel myPanel = new JPanel();
        Contact[] contacts = new Contact[contactFolder.listFiles().length];
        buttons = new JButton[contactFolder.listFiles().length];
        lblfirstNames = new JLabel[contactFolder.listFiles().length];
        lbllastName = new JLabel[contactFolder.listFiles().length];
        lblOneInfo = new JLabel[contactFolder.listFiles().length];
        lblInfo = new JLabel[contactFolder.listFiles().length]; 
        String contactPath;
        
        int cpt = 0;
        //Position for the first Button 
        int yButton = 5;
        //Position for the first name label
        int yLabelFirstName = 25;
        //Position for the last name label
        int yLabelLastName = 55;
        //Position for the OneInfo label
        int yLabelOneInfo = 45;
        //Position for the infos Label
        int ylblInfo = 45;
        
        
        File[] files = new File(path).listFiles();
        for(File file : files){
            if(file.isFile()){
                contactPath=file.getAbsolutePath();
                contacts[cpt] = readFile(contactPath);
                
                
                if(!contactPath.contains(".DS_Store")){ //only for mac but still works on windows
                    addContactToPanel(myPanel, contacts, yButton, yLabelFirstName, yLabelLastName, yLabelOneInfo, ylblInfo, cpt);                   
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
        myPanel.setBackground(new Color(255, 255, 255));
        myPanel.setPreferredSize(new Dimension(455,yButton));
    
        return myPanel;
    }
    
    private void addContactToPanel(JPanel myPanel, Contact[] contacts, int yButton, int yLabelFirstName, int yLabelLastName, int yLabelOneInfo, int ylblInfo, int cpt){ //Adding the contact to the panel
        Icon contactPic = new ImageIcon(contacts[cpt].getPicPath());
    	buttons[cpt] = new JButton(contactPic);             
        buttons[cpt].setBounds(xButton,yButton,100,100);
        buttons[cpt].setOpaque(true);
        buttons[cpt].setBorderPainted(false);
        myPanel.add(buttons[cpt]);
      //If both first name and last name are present
        if(!contacts[cpt].getFirstName().equals("") && !contacts[cpt].getLastName().equals("")){
            lblfirstNames[cpt] = new JLabel(contacts[cpt].getFirstName());
            lblfirstNames[cpt].setFont(new Font("Avenir Next", Font.BOLD, 13));
            lblfirstNames[cpt].setForeground(Color.DARK_GRAY);
            lblfirstNames[cpt].setBounds(xLabelFirstName,yLabelFirstName, 100, 20);
            lbllastName[cpt] = new JLabel(contacts[cpt].getLastName());
            lbllastName[cpt].setFont(new Font("Avenir Next", Font.BOLD, 13));
            lbllastName[cpt].setForeground(Color.DARK_GRAY);
            lbllastName[cpt].setBounds(xLabelLastName,yLabelLastName, 100, 20);
            lblOneInfo[cpt] = null;
            myPanel.add(lblfirstNames[cpt]);
            myPanel.add(lbllastName[cpt]);
            //if only first name is present
        } else if(contacts[cpt].getFirstName().equals("")){
            lblOneInfo[cpt] = new JLabel(contacts[cpt].getLastName());
            lblOneInfo[cpt].setFont(new Font("Avenir Next", Font.BOLD, 13));
            lblOneInfo[cpt].setForeground(Color.DARK_GRAY);
            lblOneInfo[cpt].setBounds(xLabelOneInfo,yLabelOneInfo, 100, 20);
            lbllastName[cpt]= null;
            myPanel.add(lblOneInfo[cpt]);
            //if only last name is present
        } else if(contacts[cpt].getLastName().equals("")){
            lblOneInfo[cpt] = new JLabel(contacts[cpt].getFirstName());
            lblOneInfo[cpt].setFont(new Font("Avenir Next", Font.BOLD, 13));
            lblOneInfo[cpt].setForeground(Color.DARK_GRAY);
            lblOneInfo[cpt].setBounds(xLabelOneInfo,yLabelOneInfo, 100, 20);
            lbllastName[cpt]= null;
            myPanel.add(lblOneInfo[cpt]);
        } 
        //display one info between number, home number and email (depending what information has been entered)
        if(!contacts[cpt].getMobileNumber().equals("")){
            lblInfo[cpt] = new JLabel(contacts[cpt].getMobileNumber());
        } else if(!contacts[cpt].getHomeNumber().equals("")){
            lblInfo[cpt] = new JLabel(contacts[cpt].getHomeNumber());
        } else if(!contacts[cpt].getEmail().equals("")){
            lblInfo[cpt] = new JLabel(contacts[cpt].getEmail());
        }
        else {
            lblInfo[cpt] = new JLabel("No Info");
        }
        lblInfo[cpt].setFont(new Font("Avenir Next", Font.BOLD, 13));
        lblInfo[cpt].setForeground(Color.DARK_GRAY);

        buttons[cpt].addActionListener(new Contact_Button(ylblInfo-10)); // button to enter each contact's details (getting the position of the OneInfolbl
        buttons[cpt].setOpaque(false);
        lblInfo[cpt].setBounds(xlblInfo,ylblInfo, 150, 20);
        myPanel.add(lblInfo[cpt]);
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

                if(lbllastNameOut[realposition] != null && lblOneInfoOut[realposition] == null){
                    readContact = readFile(path + "\\" + lbllastNameOut[realposition].getText() + lblfirstNames[realposition].getText() + ".txt");
                }
                else {
                    readContact = readFile(path + "\\" + lblOneInfoOut[realposition].getText() + ".txt");
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
                contact.setPicLabel(readContact.getPicPath());
                contact.setPicButton(readContact.getPicPath());
                contact.setContactPicPath(readContact.getPicPath());
                
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
            newContact.allowingEditableContent();
            setVisible(false);
            
        }
        
    }
    
    private JPanel loadSearch(String request) throws ClassNotFoundException, IOException{
        JPanel myPanel = new JPanel();
        int cptContacts = 0;
        int cptContactsFound = 0;
        File contactFolder = new File(path);
        Contact[] contactsInFolder = new Contact[contactFolder.listFiles().length];
        Contact[] contactsFound = new Contact[contactsInFolder.length];
        String contactPath;
        
        //Position for the first Button 
        int yButton = 5;
        //Position for the first name label
        int yLabelFirstName = 25;
        //Position for the last name label
        int yLabelLastName = 55;
        //Position for the OneInfo label
        int yLabelOneInfo = 45;
        //Position for the infos Label
        int ylblInfo = 45;
        
        //Searching in the repository the contacts with the requested information and adding it to the contactsFound
        File[] fileNames = new File(path).listFiles();
        for(File file: fileNames){
            contactPath=file.getAbsolutePath();            
            
            if(!contactPath.contains(".DS_Store")){
                contactsInFolder[cptContacts] = readFile(contactPath);
                if(contactsInFolder[cptContacts].getLastName().toLowerCase().contains(request) || contactsInFolder[cptContacts].getFirstName().toLowerCase().contains(request) || contactsInFolder[cptContacts].getMobileNumber().equals(request) || contactsInFolder[cptContacts].getEmail().toLowerCase().equals(request) || contactsInFolder[cptContacts].getHomeNumber().equals(request)){ //if the last name or the first name matches the request or numbers and eamil == searched text
                    contactsFound[cptContactsFound] = contactsInFolder[cptContacts];
                    
                    addContactToPanel(myPanel, contactsFound, yButton, yLabelFirstName, yLabelLastName, yLabelOneInfo, ylblInfo, cptContactsFound);
                    cptContactsFound++;
                    yButton += 105;
                    yLabelFirstName += 105;
                    yLabelLastName += 105;
                    yLabelOneInfo += 105;
                    ylblInfo += 105;

                }
                cptContacts++;
            }           
        }
        //get the JLabel[] OneInfo or Lastname to get it out later
        lblOneInfoOut = lblOneInfo;
        lbllastNameOut = lbllastName;
        
        myPanel.setLayout(null);
        myPanel.setBounds(18, 45, 455, 650);
        myPanel.setBackground(new Color(255, 255, 255));
        myPanel.setPreferredSize(new Dimension(455,yButton));
        
        return myPanel;
    }
    
    class Search_Button implements ActionListener{ //searching the contacts and gives out a panel with only the contacts with the correspond information
        
        String request;

        public void actionPerformed(ActionEvent e) {
            
            try {
                request = txtSearch.getText().toLowerCase();
                if(request.equals("search...")){
                    return;
                }else {
                    
                    searchPanel = loadSearch(request);
                    scroll.setVisible(false);
                    scrollSearch = new JScrollPane(searchPanel);
                    scrollSearch.setBounds(0,65,480,656);
                    getContentPane().add(scrollSearch);
                    getContentPane().add(lblbackground);
                    searchButton.setVisible(false);
                    cancelButton.setVisible(true);
                }
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
            
        }
        
    }
    
    class Cancel_Button implements ActionListener{ //Canceling the search of contact

        public void actionPerformed(ActionEvent e) {
            try {
                contactPanel = loadContacts();
                scrollSearch.setVisible(false);
                scroll = new JScrollPane(contactPanel);
                scroll.setBounds(0,65,480,656);
                getContentPane().add(scroll);
                getContentPane().add(lblbackground);
                txtSearch.setText("Search...");
                searchButton.setVisible(true);
                cancelButton.setVisible(false);
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }           
            
        }
        
    }
    
    class Home_Button implements ActionListener{ //going back to homeScreen frame

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
