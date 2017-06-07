import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDateTime;
import javax.swing.JTextArea;
import java.awt.Font;


public class ContactFrame extends JFrame{

    private  Timer tm;
    private static LocalDateTime time;
    private JButton editButton;
    private JButton deleteButton;
    private JButton backButton;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel statusBar;
    private JLabel picLabel;
    private JButton picButton;
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
    
    //Mac : /Users/black and white/Desktop/App/Contacts
    //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Contacts
    private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Contacts";
    //Mac : /Users/black and white/Desktop/App/Backgrounds/contactPic.png
    //Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\contactPic.png
    private String defaultPic = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds\\contactPic.png";
    private String contactPicPath;
    private Icon contactPic;

    public ContactFrame(){
        super("Profile Contact");
        setForeground(new Color(255, 255, 255));
        setResizable(false);
        getContentPane().setBackground(new Color(255, 255, 255));
        setBounds(100, 100, 480, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);        
      
        if(contactPicPath==null){
        	contactPicPath=defaultPic;
        }
       
        initializeFrame();       
        
        editButton.addActionListener(new Edit_Button());
        backButton.addActionListener(new Back_Button());
        deleteButton.addActionListener(new Delete_Button());
        saveButton.addActionListener(new Save_Button());
        cancelButton.addActionListener(new Cancel_Button());
        picButton.addActionListener(new Picture_Button());
        
        }
    
    private void initializeFrame(){
      	    	
        statusBar = new JLabel();
        statusBar.setFont(new Font("Avenir", Font.PLAIN, 13));
        statusBar.setBackground(Color.DARK_GRAY);
        statusBar.setForeground(new Color(255, 255, 255));
        statusBar.setOpaque(true);
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setBounds(179, 0, 128, 20);
        getContentPane().add(statusBar);
        setTime();
        
        editButton = new JButton("Edit");
        editButton.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        editButton.setForeground(Color.DARK_GRAY);
        editButton.setBounds(45, 703, 180, 69);
        getContentPane().add(editButton);
        
        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        deleteButton.setForeground(Color.DARK_GRAY);
        deleteButton.setBounds(255, 703, 180, 69);
        getContentPane().add(deleteButton);
        
        backButton = new JButton("Back");
        backButton.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        backButton.setForeground(Color.DARK_GRAY);
        backButton.setBounds(6, 20, 125, 40);
        getContentPane().add(backButton);
        
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        saveButton.setForeground(Color.DARK_GRAY);
        saveButton.setVisible(false);
        saveButton.setBounds(349, 20, 125, 40);
        getContentPane().add(saveButton);    
        
        cancelButton = new JButton("Cancel");
        cancelButton.setVisible(false);
        cancelButton.setForeground(new Color(102, 102, 102));
        cancelButton.setBounds(45, 703, 180, 69);
        getContentPane().add(cancelButton);
        
	    picButton = new JButton(new ImageIcon(contactPicPath));
	    picButton.setVisible(false);
	    picButton.setBounds(45, 85, 145, 126);
	    getContentPane().add(picButton);
        
	    picLabel = new JLabel(new ImageIcon(contactPicPath));
	    picLabel.setBounds(45, 85, 145, 126);
	    getContentPane().add(picLabel);
               
        lblMobile = new JLabel("Mobile:");
        lblMobile.setForeground(Color.DARK_GRAY);
        lblMobile.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        lblMobile.setHorizontalAlignment(SwingConstants.LEFT);
        lblMobile.setBounds(80, 250, 61, 16);
        getContentPane().add(lblMobile);
        
        lblHome = new JLabel("Home:");
        lblHome.setForeground(Color.DARK_GRAY);
        lblHome.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        lblHome.setHorizontalAlignment(SwingConstants.LEFT);
        lblHome.setBounds(80, 291, 61, 16);
        getContentPane().add(lblHome);
        
        lblFax = new JLabel("Fax:");
        lblFax.setForeground(Color.DARK_GRAY);
        lblFax.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        lblFax.setHorizontalAlignment(SwingConstants.LEFT);
        lblFax.setBounds(80, 331, 61, 16);
        getContentPane().add(lblFax);
        
        txtMobileNumber = new JTextArea();
        txtMobileNumber.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtMobileNumber.setTabSize(0);
        txtMobileNumber.setForeground(new Color(255, 255, 255));
        txtMobileNumber.setBackground(Color.DARK_GRAY);
        txtMobileNumber.setEditable(false);
        txtMobileNumber.setBounds(184, 250, 216, 16);
        getContentPane().add(txtMobileNumber);
        
        txtHomeNumber = new JTextArea();
        txtHomeNumber.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtHomeNumber.setTabSize(0);
        txtHomeNumber.setForeground(new Color(255, 255, 255));
        txtHomeNumber.setBackground(Color.DARK_GRAY);
        txtHomeNumber.setEditable(false);
        txtHomeNumber.setBounds(184, 291, 216, 16);
        getContentPane().add(txtHomeNumber);
        
        txtFaxNumber = new JTextArea();
        txtFaxNumber.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtFaxNumber.setTabSize(0);
        txtFaxNumber.setForeground(new Color(255, 255, 255));
        txtFaxNumber.setBackground(Color.DARK_GRAY);
        txtFaxNumber.setEditable(false);
        txtFaxNumber.setBounds(184, 331, 216, 16);
        getContentPane().add(txtFaxNumber);
        
        lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.DARK_GRAY);
        lblEmail.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
        lblEmail.setBounds(80, 370, 61, 16);
        getContentPane().add(lblEmail);
        
        txtEmail = new JTextArea();
        txtEmail.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtEmail.setTabSize(0);
        txtEmail.setForeground(new Color(255, 255, 255));
        txtEmail.setBackground(Color.DARK_GRAY);
        txtEmail.setEditable(false);
        txtEmail.setBounds(184, 370, 216, 16);
        getContentPane().add(txtEmail);
        
        lblAddress = new JLabel("Address:");
        lblAddress.setForeground(Color.DARK_GRAY);
        lblAddress.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
        lblAddress.setBounds(80, 411, 61, 16);
        getContentPane().add(lblAddress);
        
        txtAddress = new JTextArea();
        txtAddress.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtAddress.setTabSize(0);
        txtAddress.setForeground(new Color(255, 255, 255));
        txtAddress.setBackground(Color.DARK_GRAY);
        txtAddress.setEditable(false);
        txtAddress.setBounds(184, 411, 216, 16);
        getContentPane().add(txtAddress);
        
        lblBirthday = new JLabel("Birthday:");
        lblBirthday.setForeground(Color.DARK_GRAY);
        lblBirthday.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        lblBirthday.setHorizontalAlignment(SwingConstants.LEFT);
        lblBirthday.setBounds(80, 453, 61, 16);
        getContentPane().add(lblBirthday);
        
        txtBirthday = new JTextArea();
        txtBirthday.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtBirthday.setTabSize(0);
        txtBirthday.setForeground(new Color(255, 255, 255));
        txtBirthday.setBackground(Color.DARK_GRAY);
        txtBirthday.setEditable(false);
        txtBirthday.setBounds(184, 453, 216, 16);
        getContentPane().add(txtBirthday);
        
        lblNotes = new JLabel("Notes:");
        lblNotes.setForeground(Color.DARK_GRAY);
        lblNotes.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        lblNotes.setHorizontalAlignment(SwingConstants.LEFT);
        lblNotes.setBounds(80, 498, 61, 16);
        getContentPane().add(lblNotes);
        
        txtNotes = new JTextArea();
        txtNotes.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtNotes.setForeground(new Color(255, 255, 255));
        txtNotes.setBackground(Color.DARK_GRAY);
        txtNotes.setEditable(false);
        txtNotes.setBounds(80, 543, 320, 114);
        getContentPane().add(txtNotes);
        
        txtFirstName = new JTextArea();
        txtFirstName.setTabSize(0);
        txtFirstName.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtFirstName.setAlignmentX(10f);
        txtFirstName.setForeground(Color.DARK_GRAY);
        txtFirstName.setEditable(false);
        txtFirstName.setText("First Name");
        txtFirstName.setBounds(276, 100, 134, 20);
        getContentPane().add(txtFirstName);
        txtFirstName.setColumns(10);
        
        txtLastName = new JTextArea();
        txtLastName.setTabSize(0);
        txtLastName.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtLastName.setAlignmentY(SwingConstants.RIGHT);
        txtLastName.setForeground(Color.DARK_GRAY);
        txtLastName.setEditable(false);
        txtLastName.setText("Last Name");
        txtLastName.setColumns(10);
        txtLastName.setBounds(276, 140, 134, 20);
        getContentPane().add(txtLastName);
        
        txtCompany = new JTextArea();
        txtCompany.setTabSize(0);
        txtCompany.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        txtCompany.setAlignmentY(SwingConstants.LEFT);
        txtCompany.setForeground(Color.DARK_GRAY);
        txtCompany.setEditable(false);
        txtCompany.setText("Company");
        txtCompany.setColumns(10);
        txtCompany.setBounds(276, 180, 134, 20);
        getContentPane().add(txtCompany);       
        
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
           
    //getters and setters for txtAreas
    public String getTxtMobileNumber() {
        return txtMobileNumber.getText();
    }

    public void setTxtMobileNumber(String txtMobileNumber) {
        this.txtMobileNumber.setText(txtMobileNumber);
    }

    public String getTxtHomeNumber() {
        return txtHomeNumber.getText();
    }

    public void setTxtHomeNumber(String txtHomeNumber) {
        this.txtHomeNumber.setText(txtHomeNumber);
    }

    public String getTxtFaxNumber() {
        return txtFaxNumber.getText();
    }

    public void setTxtFaxNumber(String txtFaxNumber) {
        this.txtFaxNumber.setText(txtFaxNumber);
    }

    public String getTxtEmail() {
        return txtEmail.getText();
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail.setText(txtEmail);
    }

    public String getTxtAddress() {
        return txtAddress.getText();
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress.setText(txtAddress);
    }

    public String getTxtBirthday() {
        return txtBirthday.getText();
    }

    public void setTxtBirthday(String txtBirthday) {
        this.txtBirthday.setText(txtBirthday);
    }

    public String getTxtNotes() {
        return txtNotes.getText();
    }

    public void setTxtNotes(String txtNotes) {
        this.txtNotes.setText(txtNotes);
    }

    public String getTxtFirstName() {
        return txtFirstName.getText();
    }

    public void setTxtFirstName(String txtFirstName) {
        this.txtFirstName.setText(txtFirstName);
    }

    public String getTxtLastName() {
        return txtLastName.getText();
    }

    public void setTxtLastName(String txtLastName) {
        this.txtLastName.setText(txtLastName);
    }

    public String getTxtCompany() {
        return txtCompany.getText();
    }

    public void setTxtCompany(String txtCompany) {
        this.txtCompany.setText(txtCompany);
    }
        
    public JLabel setPicLabel(String path){
    	ImageIcon img = new ImageIcon(path);
    	picLabel.setIcon(img);
    	return picLabel;
    }
    
    public JButton setPicButton(String path){
    	ImageIcon img = new ImageIcon(path);
    	picButton.setIcon(img);
    	return picButton;
    }
    
    public void setContactPicPath(String path){
    	this.contactPicPath=path;
    }

    class Edit_Button implements ActionListener{ //sets all Text Area to editable

        public void actionPerformed(ActionEvent e) {
            allowingEditableContent();
        }
        
    }
    
    class Delete_Button implements ActionListener{ //Delete saved File of the person and returns to the ContactApp frame

        Contact person;
        File file;
        
        public void actionPerformed(ActionEvent e) {
            
            try {
                
                int ret = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?");
                if (ret == JOptionPane.YES_OPTION){
                    if((new File(path + "\\" +  txtLastName.getText() + txtFirstName.getText() + ".txt")).exists()) {
                        person = readFile();
                        file = new File(path + "\\" +  txtLastName.getText() + txtFirstName.getText() + ".txt");
                        file.delete();

                    } 
                }
            ContactApp contactapp = new ContactApp();
            contactapp.setVisible(true);
            setVisible(false);
                    
            } catch (ClassNotFoundException | IOException e1) {
                e1.getMessage();
            }
            
        }
        
    }
    
    class Back_Button implements ActionListener{ //shuts down this frame and goes back to the contactapp frame

        public void actionPerformed(ActionEvent e) {
            ContactApp contactapp;
            try {
                contactapp = new ContactApp();
                contactapp.setVisible(true);
                setVisible(false);
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }

            
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
            contact.setAddress(txtAddress.getText());
            contact.setBirthday(txtBirthday.getText());
            contact.setNote(txtNotes.getText());
            contact.setPicPath(contactPicPath);
            try {
                
                createDirectory();
                                
                if(txtFirstName.getText().equals("First Name") || txtLastName.getText().equals("Last Name")){ //If firstname and lastname not entered cannot save
                    allowingEditableContent();
                    return;
                }

                saveInFile(contact, txtFirstName.getText(), txtLastName.getText());
            } catch (IOException e1) {
                e1.getMessage();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            
            
            try {
                ContactApp cont = new ContactApp();
                cont.setVisible(true);
                setVisible(false);
                
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
            
        }
        
    }
    
    class Picture_Button implements ActionListener{
       	
    	JButton currentContactPicButton = new JButton();
		JFrame pictures = new JFrame();   		

    	public void actionPerformed(ActionEvent e) {
   		    
    		setVisible(false);
    		
    		currentContactPicButton = (JButton) e.getSource();
    		JLabel selectPic = new JLabel("Select a picture.");
    		selectPic.setFont(new Font("Avenir Next", Font.PLAIN, 15));
    		selectPic.setForeground(Color.BLACK);
    		selectPic.setBounds(97, 0, 388, 42);
    		selectPic.setHorizontalAlignment(SwingConstants.CENTER);
    		JButton cancel = new JButton("Cancel");
    		cancel.setFont(new Font("Avenir Next", Font.PLAIN, 13));
    		cancel.setForeground(Color.BLACK);
    		cancel.setBounds(0, 0, 97, 42);
    		cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentContactPicButton.setName(defaultPic);
					currentContactPicButton.setIcon(new ImageIcon(defaultPic));
					pictures.setVisible(false);
					setVisible(true);
				}
			});
	   		JPanel allPics = new JPanel();
	   		try {
	   			allPics = loadPics();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	   		
	   		pictures.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        pictures.setSize(480, 800);
	       
	        JScrollPane scroll = new JScrollPane(allPics);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        scroll.setBounds(0, 150, 480, 675);
	        
	        pictures.add(cancel);
	        pictures.add(selectPic);
	        pictures.add(scroll);
	   		pictures.setVisible(true);
    	}
    	
    	private JPanel loadPics() throws IOException{
    		JPanel myPanel = new JPanel();
    		myPanel.setBackground(Color.WHITE);
    		FileInputStream fr;
    		BufferedInputStream bfr;
    		//Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery
    		//Mac: /Users/black and white/Desktop/App/gallery
    		String picFolderPath = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery";
    		File picFolder = new File(picFolderPath);
    		Icon[] images = new ImageIcon[picFolder.listFiles().length];
    		JButton[] buttons = new JButton[picFolder.listFiles().length];
    		String imgPath;

    		//Position for the first image
    		int x=22;
    		int y=55;
    		int cptX=0;
    		
    		for(int i=0;i<images.length;i++){
    			imgPath=picFolderPath+"\\"+Integer.toString(i)+".jpg";
    			fr = new FileInputStream(imgPath);
    			bfr = new BufferedInputStream(fr);
    			images[i] = new ImageIcon(imgPath);
    			buttons[i] = new JButton(images[i]);
    			buttons[i].setName(imgPath);
    			buttons[i].addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					JButton currentButton = new JButton();
    					currentButton = (JButton) e.getSource();
    					currentContactPicButton.setName(currentButton.getName());
    					currentContactPicButton.setIcon(new ImageIcon(currentButton.getName()));
    					contactPicPath = currentButton.getName();
    					pictures.setVisible(false);
    					setVisible(true);
    				}
    			});
    			
    			switch (cptX){
    			case 0:
    				buttons[i].setBounds(x, y, 125, 125);
    				myPanel.add(buttons[i]);
    				break;
    			case 1:
    				buttons[i].setBounds(x+143, y, 125, 125);
    				myPanel.add(buttons[i]);
    				break;
    			case 2:
    				buttons[i].setBounds(x+287, y, 125, 125);
    				myPanel.add(buttons[i]);
    				break;
    			}
    			cptX++;
    			if(cptX==3){
    				cptX=0;
    				y+=143;
    			}
    			bfr.close();
    		}
    		myPanel.setLayout(null);
    		//y+145 => y(total y size)+125(img size)+20(initial border)
    		myPanel.setPreferredSize(new Dimension(450,y+145));
    		return myPanel;
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
    
    private void createDirectory(){ //Create directory Contacts where is stocked the contacts
        File contactFolder = new File(path);
        contactFolder.mkdir();
    }
    
    private void saveInFile(Contact contact, String firstName, String lastName) throws IOException{ //Serialize in folder the contact
        
        FileOutputStream fichier = new FileOutputStream(path + "\\" + lastName + firstName +".txt");  
        BufferedOutputStream bfichier = new BufferedOutputStream(fichier);
        ObjectOutputStream obfichier = new ObjectOutputStream(bfichier);
        obfichier.writeObject(contact);
        obfichier.close();
    }
    
    private Contact readFile() throws ClassNotFoundException, IOException{  //Read a already saved contact
        FileInputStream fichier;
        Contact person;
        fichier = new FileInputStream(path + "\\" +  txtLastName.getText() + txtFirstName.getText() + ".txt");

        BufferedInputStream bfichier = new BufferedInputStream(fichier);
        ObjectInputStream obfichier = new ObjectInputStream(bfichier);
        person = (Contact) obfichier.readObject();
        obfichier.close();
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
            txtLastName.setText(person.getLastName());
            txtCompany.setText(person.getCompanyName());
            txtMobileNumber.setText(person.getMobileNumber());
            txtHomeNumber.setText(person.getHomeNumber());
            txtFaxNumber.setText(person.getFaxNumber());
            txtEmail.setText(person.getEmail());
            txtAddress.setText(person.getAddress());
            txtBirthday.setText(person.getBirthday());
            txtNotes.setText(person.getNote());
            contactPic = new ImageIcon(person.getPicPath());
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
        contactPic = new ImageIcon(defaultPic);
    }
    
    public void allowingEditableContent(){
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
        picButton.setVisible(true);
        picLabel.setVisible(false);
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
        picButton.setVisible(false);
        picLabel.setVisible(true);
    }
    
}
