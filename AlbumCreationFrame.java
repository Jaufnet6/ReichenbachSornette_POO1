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
import java.io.ObjectOutputStream;
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

	private static Album newAlbum = new Album();
	private Icon pics[] = new Icon[10];
	private JTextField albumNameTxt;
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
		CancelButton.setBackground(SystemColor.activeCaption);
		CancelButton.setBounds(0, 679, 238, 86);
		getContentPane().add(CancelButton);
		
		JButton OKButton = new JButton("Save album");
		OKButton.addActionListener(new SaveListener());
		OKButton.setBackground(SystemColor.textHighlight);
		OKButton.setBounds(236, 679, 238, 86);
		getContentPane().add(OKButton);
		
		JLabel lblChoseThePictures = new JLabel("Add pictures in your album (max : 10)");
		lblChoseThePictures.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChoseThePictures.setBounds(12, 146, 450, 58);
		getContentPane().add(lblChoseThePictures);
		
        scroll = new JScrollPane(picturesPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 200, 476, 471);
		getContentPane().add(scroll);
	}
	
	class SaveListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(albumNameTxt.getText().equals("")){
				newAlbum.renameAlbum("New album");
			}
			else{
			newAlbum.renameAlbum(albumNameTxt.getText());
			}
			try {
				saveInFile(albumNameTxt.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	class AddPicsToAlbum implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			int cpt=0;
			JButton button = (JButton) e.getSource();

			while(cpt<10){
				pics[cpt]=button.getIcon();
				cpt++;
			}
			if (cpt==0){
				pics=null;
			}
			newAlbum.setPics(pics);
			newAlbum.setHomePic(pics[0]);
			
			//Mark already used pictures when clicked
			button.setEnabled(false);
		}
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
