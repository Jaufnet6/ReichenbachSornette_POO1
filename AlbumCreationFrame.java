import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Button;
import java.awt.Color;


import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Font;


public class AlbumCreationFrame extends JFrame{

	private Album newAlbum = new Album();
	private Icon[] pics = new ImageIcon[10];
	private JTextField albumNameTxt;
	private JLabel nameError = new JLabel("");
	private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet";
	private JPanel picturesPanel = new JPanel();
	private JScrollPane scroll;
	
	public AlbumCreationFrame() throws IOException {
		super("New Album");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);		
		picturesPanel=loadPics();

		JLabel albumName = new JLabel("Album name :");
		albumName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		albumName.setBounds(12, 35, 155, 49);
		getContentPane().add(albumName);
		
		albumNameTxt = new JTextField();
		albumNameTxt.setBounds(179, 38, 283, 43);
		getContentPane().add(albumNameTxt);
		albumNameTxt.setColumns(10);
		
		JButton CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new CancelListener());
		CancelButton.setBackground(SystemColor.activeCaption);
		CancelButton.setBounds(0, 679, 238, 86);
		getContentPane().add(CancelButton);
		
		JButton OKButton = new JButton("Save album");
		OKButton.addActionListener(new SaveListener());
		OKButton.setBackground(SystemColor.textHighlight);
		OKButton.setBounds(236, 679, 238, 86);
		getContentPane().add(OKButton);
		
		JLabel maxPic = new JLabel("Add pictures in your album (max : 10)");
		maxPic.setFont(new Font("Tahoma", Font.PLAIN, 24));
		maxPic.setBounds(12, 146, 450, 58);
		getContentPane().add(maxPic);
		
        scroll = new JScrollPane(picturesPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 200, 476, 471);
		getContentPane().add(scroll);
		
		nameError.setForeground(Color.RED);
		nameError.setFont(new Font("Tahoma", Font.BOLD, 15));
		nameError.setHorizontalAlignment(SwingConstants.CENTER);
		nameError.setBounds(12, 97, 450, 36);
		getContentPane().add(nameError);
	}
	
	class CancelListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
		
	}
	
	class SaveListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			File albFolder = new File(path+"\\albums");
			Album[] albums = new Album[albFolder.listFiles().length];
			File[] files = new File(path+"\\albums").listFiles();
			String albPath;
			int cpt=0;

			if(albumNameTxt.getText().equals("")){
				//User must choose a name
				nameError.setText("Error : please choose a name.");
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
					newAlbum.setPics(pics);
					try {
						saveInFile(newAlbum.getName());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					setVisible(false);
				}
			}
		}
	}

	
	class AddPicsToAlbum implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//Copy the clicked button and his img
			JButton button = (JButton) e.getSource();
//			Icon img = ((JButton)e.getSource());
			//Stock pictures in an array
			Icon[] newAddedPics = new ImageIcon[pics.length];
			for(int i=0;i<pics.length;i++){
				newAddedPics[i]=pics[i];
			}
			newAddedPics[newAddedPics.length-1]=img;
			pics=newAddedPics;
			
			newAlbum.setPics(pics);
			newAlbum.setHomePic(pics[0]);
			
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
    	 	
        FileOutputStream fos = new FileOutputStream(path+"\\albums\\"+name+".txt");  
        BufferedOutputStream bfos = new BufferedOutputStream(fos);
        ObjectOutputStream obfos = new ObjectOutputStream(bfos);
        obfos.writeObject(newAlbum);
        obfos.close();
        
        
    }
	
	private JPanel loadPics() throws IOException{
		JPanel myPanel = new JPanel();
		FileInputStream fr;
		BufferedInputStream bfr;
		File picFolder = new File(path+"\\gallery");
		Icon[] images = new ImageIcon[picFolder.listFiles().length];
		JButton[] buttons = new JButton[picFolder.listFiles().length];
		String imgPath;

		//Position for the first image
		int x=22;
		int y=20;
		int cptX=0;
		
		for(int i=0;i<images.length;i++){
			imgPath=path+"\\gallery\\"+Integer.toString(i)+".jpg";
			fr = new FileInputStream(imgPath);
			bfr = new BufferedInputStream(fr);
			images[i] = new ImageIcon(imgPath);
			buttons[i] = new JButton(images[i]);
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
