import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Color;
import java.time.LocalDateTime;
import javax.swing.JTextArea;
import java.awt.Font;


public class ContactFrame{

    private JFrame frame;
    private  Timer tm;
    private static LocalDateTime time;
    private JButton editButton;
    private JButton deleteButton;
    private JButton backButton;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel statusBar;
    private JLabel picLabel;
    private JLabel lblMobile;
    private JLabel lblHome;
    private JLabel lblFax;
    private JLabel lblEmail;
    private JLabel lblAddress;
    private JLabel lblBirthday;
    private JLabel lblNotes;
    private JTextArea txtMobileNumber;
    private JTextArea txtHomeNumber;
    private JTextArea txtFaxNumber;
    private JTextArea txtEmail;
    private JTextArea txtAddress;
    private JTextArea txtBirthday;
    private JTextArea txtNotes;
    private JTextArea txtFirstName;
    private JTextArea txtLastName;
    private JTextArea txtCompany;
    
    private String path = "/Users/black and white/Desktop/App";
    private Icon contactPic = new ImageIcon("/Users/black and white/Desktop/1.png");


    public static void main(String[] args) {
        try {
            ContactFrame window = new ContactFrame();
            window.frame.setVisible(true);
            window.frame.setResizable(false);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public ContactFrame(){
        frame = new JFrame("Profile Contact");
        frame.getContentPane().setForeground(new Color(255, 255, 255));
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 480, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);        
        
        initializeFrame();       
        
        editButton.addActionListener(new Edit_Button());
        backButton.addActionListener(new Back_Button());
        deleteButton.addActionListener(new Delete_Button());
        saveButton.addActionListener(new Save_Button());
        cancelButton.addActionListener(new Cancel_Button());
        
        }
    
    private void initializeFrame(){
        
        statusBar = new JLabel();
        statusBar.setBackground(new Color(102, 102, 102));
        statusBar.setForeground(new Color(255, 255, 255));
        statusBar.setOpaque(true);
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setBounds(179, 0, 128, 20);
        frame.getContentPane().add(statusBar);
        setTime();
        
        editButton = new JButton("Edit");
        editButton.setForeground(new Color(102, 102, 102));
        editButton.setBounds(45, 703, 180, 69);
        frame.getContentPane().add(editButton);
        
        deleteButton = new JButton("Delete");
        deleteButton.setForeground(new Color(102, 102, 102));
        deleteButton.setBounds(255, 703, 180, 69);
        frame.getContentPane().add(deleteButton);
        
        backButton = new JButton("Back");
        backButton.setForeground(new Color(102, 102, 102));
        backButton.setBounds(6, 20, 125, 40);
        frame.getContentPane().add(backButton);
        
        saveButton = new JButton("Save");
        saveButton.setForeground(new Color(102, 102, 102));
        saveButton.setVisible(false);
        saveButton.setBounds(349, 20, 125, 40);
        frame.getContentPane().add(saveButton);    
        
        cancelButton = new JButton("Cancel");
        cancelButton.setVisible(false);
        cancelButton.setForeground(new Color(102, 102, 102));
        cancelButton.setBounds(45, 703, 180, 69);
        frame.getContentPane().add(cancelButton);
        
        picLabel = new JLabel(contactPic);
        picLabel.setBounds(80, 95, 145, 126);
        frame.getContentPane().add(picLabel);
        
        lblMobile = new JLabel("Mobile:");
        lblMobile.setForeground(new Color(102, 102, 102));
        lblMobile.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        lblMobile.setHorizontalAlignment(SwingConstants.LEFT);
        lblMobile.setBounds(80, 250, 61, 16);
        frame.getContentPane().add(lblMobile);
        
        lblHome = new JLabel("Home:");
        lblHome.setForeground(new Color(102, 102, 102));
        lblHome.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        lblHome.setHorizontalAlignment(SwingConstants.LEFT);
        lblHome.setBounds(80, 291, 61, 16);
        frame.getContentPane().add(lblHome);
        
        lblFax = new JLabel("Fax:");
        lblFax.setForeground(new Color(102, 102, 102));
        lblFax.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        lblFax.setHorizontalAlignment(SwingConstants.LEFT);
        lblFax.setBounds(80, 331, 61, 16);
        frame.getContentPane().add(lblFax);
        
        txtMobileNumber = new JTextArea();
        txtMobileNumber.setForeground(new Color(255, 255, 255));
        txtMobileNumber.setBackground(new Color(102, 102, 102));
        txtMobileNumber.setEditable(false);
        txtMobileNumber.setBounds(184, 250, 216, 16);
        frame.getContentPane().add(txtMobileNumber);
        
        txtHomeNumber = new JTextArea();
        txtHomeNumber.setForeground(new Color(255, 255, 255));
        txtHomeNumber.setBackground(new Color(102, 102, 102));
        txtHomeNumber.setEditable(false);
        txtHomeNumber.setBounds(184, 291, 216, 16);
        frame.getContentPane().add(txtHomeNumber);
        
        txtFaxNumber = new JTextArea();
        txtFaxNumber.setForeground(new Color(255, 255, 255));
        txtFaxNumber.setBackground(new Color(102, 102, 102));
        txtFaxNumber.setEditable(false);
        txtFaxNumber.setBounds(184, 331, 216, 16);
        frame.getContentPane().add(txtFaxNumber);
        
        lblEmail = new JLabel("Email:");
        lblEmail.setForeground(new Color(102, 102, 102));
        lblEmail.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setBounds(80, 370, 61, 16);
        frame.getContentPane().add(lblEmail);
        
        txtEmail = new JTextArea();
        txtEmail.setForeground(new Color(255, 255, 255));
        txtEmail.setBackground(new Color(102, 102, 102));
        txtEmail.setEditable(false);
        txtEmail.setBounds(184, 370, 216, 16);
        frame.getContentPane().add(txtEmail);
        
        lblAddress = new JLabel("Address:");
        lblAddress.setForeground(new Color(102, 102, 102));
        lblAddress.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
        lblAddress.setBounds(80, 411, 61, 16);
        frame.getContentPane().add(lblAddress);
        
        txtAddress = new JTextArea();
        txtAddress.setForeground(new Color(255, 255, 255));
        txtAddress.setBackground(new Color(102, 102, 102));
        txtAddress.setEditable(false);
        txtAddress.setBounds(184, 411, 216, 16);
        frame.getContentPane().add(txtAddress);
        
        lblBirthday = new JLabel("Birthday:");
        lblBirthday.setForeground(new Color(102, 102, 102));
        lblBirthday.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        lblBirthday.setHorizontalAlignment(SwingConstants.LEFT);
        lblBirthday.setBounds(80, 453, 61, 16);
        frame.getContentPane().add(lblBirthday);
        
        txtBirthday = new JTextArea();
        txtBirthday.setForeground(new Color(255, 255, 255));
        txtBirthday.setBackground(new Color(102, 102, 102));
        txtBirthday.setEditable(false);
        txtBirthday.setBounds(184, 453, 216, 16);
        frame.getContentPane().add(txtBirthday);
        
        lblNotes = new JLabel("Notes:");
        lblNotes.setForeground(new Color(102, 102, 102));
        lblNotes.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        lblNotes.setHorizontalAlignment(SwingConstants.LEFT);
        lblNotes.setBounds(80, 498, 61, 16);
        frame.getContentPane().add(lblNotes);
        
        txtNotes = new JTextArea();
        txtNotes.setForeground(new Color(255, 255, 255));
        txtNotes.setBackground(new Color(102, 102, 102));
        txtNotes.setEditable(false);
        txtNotes.setBounds(80, 543, 320, 114);
        frame.getContentPane().add(txtNotes);
        
        txtFirstName = new JTextArea();
        txtFirstName.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        txtFirstName.setAlignmentX(10f);
        txtFirstName.setForeground(new Color(102, 102, 102));
        txtFirstName.setEditable(false);
        txtFirstName.setText("First Name");
        txtFirstName.setBounds(310, 99, 134, 20);
        frame.getContentPane().add(txtFirstName);
        txtFirstName.setColumns(10);
        
        txtLastName = new JTextArea();
        txtLastName.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        txtLastName.setAlignmentY(SwingConstants.RIGHT);
        txtLastName.setForeground(new Color(102, 102, 102));
        txtLastName.setEditable(false);
        txtLastName.setText("Last Name");
        txtLastName.setColumns(10);
        txtLastName.setBounds(310, 139, 134, 20);
        frame.getContentPane().add(txtLastName);
        
        txtCompany = new JTextArea();
        txtCompany.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        txtCompany.setAlignmentY(SwingConstants.LEFT);
        txtCompany.setForeground(new Color(102, 102, 102));
        txtCompany.setEditable(false);
        txtCompany.setText("Company");
        txtCompany.setColumns(10);
        txtCompany.setBounds(310, 179, 134, 20);
        frame.getContentPane().add(txtCompany);
        
        
    }

    //Time on status bar
    private void setTime(){
        
        tm = new Timer(1000, new ActionListener() {          
            public void actionPerformed(ActionEvent e) {
                time = LocalDateTime.now();
                statusBar.setText(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());                
            }
        });
        tm.start();
    }
        
    class Edit_Button implements ActionListener{ //sets all Text Area to editable

        public void actionPerformed(ActionEvent e) {
            allowingEditableContent();
        }
        
    }
    
    class Delete_Button implements ActionListener{ //Delete saved File of the person 

        Contact person;
        File file;
        
        public void actionPerformed(ActionEvent e) {
            
            try {
                if((new File(path + "/" + txtLastName + ".txt")).exists() == true || (new File(path + "/" + txtFirstName + ".txt")).exists()){
                    person = readFile();
                    System.out.println(person);
                    if(person.getFirstName().equals("")){
                        file = new File(path + "/" + txtLastName + ".txt");
                        file.delete();
                    } 
                    else if(person.getLastName().equals("")){
                        file = new File(path + "/" + person.getFirstName() + ".txt");
                        file.delete();
                    }  
                }                 
                
                deleteAll();
                txtFirstName.setText("First Name");
                txtLastName.setText("Last Name");
                    
            } catch (ClassNotFoundException | IOException e1) {
                e1.getMessage();
            }
            
        }
        
    }
    
    class Back_Button implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            
        }
        
    }
    
    class Save_Button implements ActionListener{ //Serialize the new or modifiy contact and Text areas are set to non editable

        public void actionPerformed(ActionEvent e) { 

            blockingEditableContent();
            
            Contact contact = new Contact();
            contact.setFirstName(txtFirstName.getText());
            contact.setLastName(txtLastName.getText());
            contact.setCompanyName(txtCompany.getText());
            contact.setMobileNumber(txtMobileNumber.getText());
            contact.setHomeNumber(txtHomeNumber.getText());
            contact.setFaxNumber(txtFaxNumber.getText());
            contact.setEmail(txtEmail.getText());
            contact.setBirthday(txtBirthday.getText());
            contact.setNote(txtNotes.getText());
            
            try {
                
                createDirectory();
                
                if(txtFirstName.getText().equals("First Name") || txtLastName.getText().equals("Last Name")){ //If firstname and lastname not entered cannot save
                    allowingEditableContent();
                    return;
                }
                
                if(txtLastName.getText().equals(""))
                    saveInFile(contact, txtFirstName.getText());
                else if(txtLastName.getText().equals("") && txtFirstName.getText().equals(""))
                    return;
                else
                    saveInFile(contact, txtLastName.getText());
            } catch (IOException e1) {
                e1.getMessage();
            }
            
        }
        
    }
    
    class Cancel_Button implements ActionListener { //Blocks editable content and resets all text areas

        public void actionPerformed(ActionEvent e) {            
            blockingEditableContent();    
            try {
                resetTextArea();
            } catch (FileNotFoundException e1) {
                e1.getMessage();
            } catch (ClassNotFoundException e1) {
                e1.getMessage();
            } catch (IOException e1) {
                e1.getMessage();
            }
        }
        
    }
    
    private void createDirectory(){ //Create directory App where is stocked the contacts
        File contactFolder = new File(path);
        contactFolder.mkdir();
    }
    
    private void saveInFile(Contact contact, String name) throws IOException{ //Serialize in folder the contact
        
        FileOutputStream fichier = new FileOutputStream(path + "/" + name +".txt");  
        BufferedOutputStream bfichier = new BufferedOutputStream(fichier);
        ObjectOutputStream obfichier = new ObjectOutputStream(bfichier);
        obfichier.writeObject(contact);
        obfichier.close();
    }
    
    private Contact readFile() throws ClassNotFoundException, IOException{  //Read a already saved contact
        FileInputStream fichier;
        Contact person;
        
        if(txtLastName.getText().equals("") == false){
            fichier = new FileInputStream(path + "/" + txtLastName + ".txt");
        }
        else{
            fichier = new FileInputStream(path + "/" + txtFirstName + ".txt");
        }
        BufferedInputStream bfichier = new BufferedInputStream(fichier);
        ObjectInputStream obfichier = new ObjectInputStream(bfichier);
        person = (Contact) obfichier.readObject();
        obfichier.close();
        System.out.println(person);
        return person;
    }
    
    private void resetTextArea() throws IOException, ClassNotFoundException{ //Resets to previous conditions: if new contact all clear else original information
        
        Contact person;
        
        if(txtFirstName.getText().equals("First Name") && txtLastName.getText().equals("Last Name")){
            deleteAll();
            txtFirstName.setText("First Name");
            txtLastName.setText("Last Name");
        } else {
            person = readFile();
            txtFirstName.setText(person.getFirstName());
            txtLastName.setText(person.getFirstName());
            txtCompany.setText(person.getFirstName());
            txtMobileNumber.setText(person.getFirstName());
            txtHomeNumber.setText(person.getFirstName());
            txtFaxNumber.setText(person.getFirstName());
            txtEmail.setText(person.getFirstName());
            txtAddress.setText(person.getFirstName());
            txtBirthday.setText(person.getFirstName());
            txtNotes.setText(person.getFirstName());

        }
        
    }
    
    private void deleteAll(){
        txtFirstName.setText("");
        txtLastName.setText("");
        txtCompany.setText("");
        txtMobileNumber.setText("");
        txtHomeNumber.setText("");
        txtFaxNumber.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtBirthday.setText("");
        txtNotes.setText("");
    }
    
    private void allowingEditableContent(){
        saveButton.setVisible(true);
        editButton.setVisible(false);
        cancelButton.setVisible(true);
        txtFirstName.setEditable(true);
        txtLastName.setEditable(true);
        txtCompany.setEditable(true);
        txtMobileNumber.setEditable(true);
        txtHomeNumber.setEditable(true);
        txtFaxNumber.setEditable(true);
        txtEmail.setEditable(true);
        txtAddress.setEditable(true);
        txtBirthday.setEditable(true);
        txtNotes.setEditable(true); 
    }
    
    private void blockingEditableContent(){
        saveButton.setVisible(false);
        editButton.setVisible(true);
        cancelButton.setVisible(false);
        txtFirstName.setEditable(false);
        txtLastName.setEditable(false);
        txtCompany.setEditable(false);
        txtMobileNumber.setEditable(false);
        txtHomeNumber.setEditable(false);
        txtFaxNumber.setEditable(false);
        txtEmail.setEditable(false);
        txtAddress.setEditable(false);
        txtBirthday.setEditable(false);
        txtNotes.setEditable(false);
    }
    
}
