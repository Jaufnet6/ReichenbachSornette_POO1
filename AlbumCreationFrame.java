
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;


public class AlbumCreationFrame extends JFrame{

	private Album newAlbum = new Album();
	private JTextField albumNameTxt;
	private JLabel nameError = new JLabel("");
	//Mac: /Users/black and white/Desktop/App
	//Windows: C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet
	private String path = "/Users/black and white/Desktop/App";
	private JPanel picturesPanel = new JPanel();
	private JScrollPane scroll;
	
	public AlbumCreationFrame() throws IOException {
		super("New Album");
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);		
		picturesPanel=loadPics();

		//Top part of the frame
		JLabel albumName = new JLabel("Album name :");
		albumName.setForeground(Color.WHITE);
		albumName.setFont(new Font("Avenir Next", Font.BOLD | Font.ITALIC, 16));
		albumName.setBounds(12, 35, 155, 49);
		getContentPane().add(albumName);
		
		albumNameTxt = new JTextField();
		albumNameTxt.setHorizontalAlignment(SwingConstants.CENTER);
		albumNameTxt.setFont(new Font("Avenir Next", Font.BOLD, 13));
		albumNameTxt.setBounds(179, 38, 283, 43);
		getContentPane().add(albumNameTxt);
		albumNameTxt.setColumns(10);
		
		//Bottom part of the frame
		JButton CancelButton = new JButton("CANCEL");
		CancelButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
		CancelButton.addActionListener(new CancelListener());
		CancelButton.setBackground(Color.LIGHT_GRAY);
		CancelButton.setBounds(0, 676, 240, 50);
		getContentPane().add(CancelButton);
		
		JButton OKButton = new JButton("SAVE");
		OKButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
		OKButton.setForeground(Color.BLACK);
		OKButton.addActionListener(new SaveListener());
		OKButton.setBackground(Color.DARK_GRAY);
		OKButton.setBounds(240, 676, 240, 50);
		getContentPane().add(OKButton);
		
		JButton homeButton = new JButton("HOME");
		homeButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
		homeButton.setForeground(Color.WHITE);
		homeButton.setBackground(Color.BLACK);
		homeButton.setBounds(0, 723, 480, 55);
		homeButton.addActionListener(new HomeListener());
		getContentPane().add(homeButton);
		
		//Pics part
		JLabel addPic = new JLabel("Add pictures in your album");
		addPic.setForeground(Color.WHITE);
		addPic.setFont(new Font("Avenir Next", Font.BOLD | Font.ITALIC, 16));
		addPic.setBounds(12, 146, 450, 58);
		getContentPane().add(addPic);
		
        scroll = new JScrollPane(picturesPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 200, 476, 471);
		getContentPane().add(scroll);
		
		//Error : if no name or already existing name typed
		nameError.setForeground(Color.RED);
		nameError.setFont(new Font("Avenir Next", Font.BOLD, 13));
		nameError.setHorizontalAlignment(SwingConstants.CENTER);
		nameError.setBounds(15, 97, 450, 36);
		getContentPane().add(nameError);
		
		JLabel lblBackground = new JLabel(new ImageIcon("/Users/black and white/Desktop/App/Backgrounds/background.png"));
        lblBackground.setBounds(0, 0, 480, 778);
        getContentPane().add(lblBackground);
		
	}
	
	//Go back to homescreen
	class HomeListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			HomeScreen hs;
			try {
				hs = new HomeScreen();
				setVisible(false);
				hs.setVisible(true);
			} catch (ClassNotFoundException | InterruptedException | IOException e) {
				e.printStackTrace();
			}
		
		}
	
	}
	
	//Go back to DefaultGalleryFrame
	class CancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try {
				DefaultGalleryFrame dgf = new DefaultGalleryFrame();
				dgf.setVisible(true);
				setVisible(false);
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		
	}
	
	//Serialize new album
	class SaveListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			File albFolder = new File(path+"/albums");
			Album[] albums = new Album[albFolder.listFiles().length];
			String[] newPics = new String[10];
			File[] files = new File(path+"/albums").listFiles();
			String albPath;
			int cpt=0;

			if(albumNameTxt.getText().equals("")){
				//User must choose a name
				nameError.setText("Error : please choose a name.");
				nameError.setFont(new Font("Avenir Next", Font.BOLD, 13));
			}
			else {
				
				for(File file : files){
					  if(file.isFile()){
						  albPath=file.getAbsolutePath();
						  try {
							  albums[cpt] = readFile(albPath);
						  } catch (ClassNotFoundException | IOException e1) {
							  e1.printStackTrace();
						  }
					  }
					  //User can't have 2 albums with the same name
					  if(albumNameTxt.getText().equals(albums[cpt].getName())){
							nameError.setText("Error : "+albums[cpt].getName()+" already exists.");
							albumNameTxt.setText("");
					  }
					  cpt++;
					}
				if(albumNameTxt.getText().equals("")==false){
					newAlbum.renameAlbum(albumNameTxt.getText());
					try {
						saveInFile(newAlbum.getName());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//Go back to DefaultGalleryFrame after saving
					setVisible(false);
					DefaultGalleryFrame dgf;
					try {
						dgf = new DefaultGalleryFrame();
						dgf.setVisible(true);

					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	class AddPicsToAlbum implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//Copy the clicked button
			JButton button = (JButton) e.getSource();
			//Stock pictures paths in an array
			String[] newAddedPics = new String[newAlbum.getPics().length+1];
			String img = button.getName();
			for(int i=0;i<newAlbum.getPics().length;i++){
				newAddedPics[i]=newAlbum.getPics()[i];
			}
			newAddedPics[newAlbum.getPics().length] = img;
			newAlbum.setPics(newAddedPics);
			newAlbum.setHomePic(img);
			//Mark already used pictures when clicked
			button.setEnabled(false);
		}
	}
	
	//Read a already saved album
	private Album readFile(String path) throws ClassNotFoundException, IOException{  
		
		Album alb;
		FileInputStream fichier = new FileInputStream(path);
	    BufferedInputStream bfichier = new BufferedInputStream(fichier);
	    ObjectInputStream obfichier = new ObjectInputStream(bfichier);
	    alb = (Album) obfichier.readObject();
	    obfichier.close();
	    return alb;
	    
	 }
	
	//Serialize in folder the album
    private void saveInFile(String name) throws IOException{ 
    	 	
        FileOutputStream fos = new FileOutputStream(path+"/albums/"+name+".txt");  
        BufferedOutputStream bfos = new BufferedOutputStream(fos);
        ObjectOutputStream obfos = new ObjectOutputStream(bfos);
        obfos.writeObject(newAlbum);
        obfos.close();
        
    }
	
    //Load pictures from Gallery folder
	private JPanel loadPics() throws IOException{
		JPanel myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
		FileInputStream fr;
		BufferedInputStream bfr;
		File picFolder = new File(path+"/gallery");
		Icon[] images = new ImageIcon[picFolder.listFiles().length];
		JButton[] buttons = new JButton[picFolder.listFiles().length];
		String imgPath;

		//Position for the first image
		int x=22;
		int y=20;
		int cptX=0;
		
		//Loading pictures
		for(int i=0;i<images.length;i++){
			imgPath=path+"/gallery/"+Integer.toString(i)+".jpg";
			fr = new FileInputStream(imgPath);
			bfr = new BufferedInputStream(fr);
			images[i] = new ImageIcon(imgPath);
			//Every picture is a button
			buttons[i] = new JButton(images[i]);
			buttons[i].setName(imgPath);
			buttons[i].addActionListener(new AddPicsToAlbum());
			
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
				y += 143;
			}
			bfr.close();
		}
		myPanel.setLayout(null);
		//y+145 => y(total y size)+125(img size)+20(initial border)
		myPanel.setPreferredSize(new Dimension(450,y+145));
		return myPanel;
	}
}
