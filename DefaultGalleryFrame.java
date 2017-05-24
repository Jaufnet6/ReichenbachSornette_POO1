import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

public class DefaultGalleryFrame extends JFrame{

	private JTextField txtSearch;
	private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\albums";
	private Icon defaultAlbum = new ImageIcon("C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery\\defaultPictures\\album.png");
	private JLabel album1Title;
	private JScrollPane scroll;
	private JPanel albumsPanel = new JPanel();
	
	public DefaultGalleryFrame() throws ClassNotFoundException, IOException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);
		albumsPanel=loadAlbums();
		
		JButton addAlbum = new JButton("Add album");
		addAlbum.addActionListener(new AddAlbumListener());
		addAlbum.setBackground(Color.GREEN);
		addAlbum.setBounds(0, 0, 60, 59);
		getContentPane().add(addAlbum);
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSearch.setText("Search an album...");
		txtSearch.setBounds(59, 0, 365, 59);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton search = new JButton("New button");
		search.setBounds(420, 0, 54, 59);
		getContentPane().add(search);
		
		JButton switchToPictures = new JButton("PICTURES");
		switchToPictures.addActionListener(new PicturesButtonListener());
		switchToPictures.setBackground(SystemColor.activeCaption);
		switchToPictures.setBounds(0, 679, 238, 86);
		getContentPane().add(switchToPictures);
		
		JLabel albums = new JLabel("ALBUMS");
		albums.setOpaque(true);
		albums.setBackground(SystemColor.textHighlight);
		albums.setHorizontalAlignment(SwingConstants.CENTER);
		albums.setBounds(237, 679, 237, 86);
		getContentPane().add(albums);
			
		scroll = new JScrollPane(albumsPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(0,60,474,616);
		getContentPane().add(scroll);
	}
	
	class AddAlbumListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			AlbumCreationFrame acf;
			try {
				acf = new AlbumCreationFrame();
				acf.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	class PicturesButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			PicturesFrame picFrame;
			try {
				picFrame = new PicturesFrame();
				picFrame.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private JPanel loadAlbums() throws ClassNotFoundException, IOException{
		JPanel myPanel = new JPanel();
		File albFolder = new File(path);
		Album[] albums = new Album[albFolder.listFiles().length];
		JButton[] buttons = new JButton[albFolder.listFiles().length];
		JLabel[] albumNames = new JLabel[albFolder.listFiles().length];
		Icon[] albumIcons = new Icon[albFolder.listFiles().length];
		String albPath;
		int cpt = 0;
		int x = 40;
		//Position for the first album
		int yAlb = 20;
		//Position for the first label
		int yLab = 200;
		int cptX = 0;
	
		File[] files = new File(path).listFiles();
		for(File file : files){
		  if(file.isFile()){
		    albPath=file.getAbsolutePath();
			albums[cpt] = readFile(albPath);
			albumIcons[cpt] = albums[cpt].getHomePic();
			buttons[cpt] = new JButton(albumIcons[cpt]);
//			buttons[cpt].addActionListener(new AlbumButtonListener());
			albumNames[cpt] = new JLabel(albums[cpt].getName());
			albumNames[cpt].setHorizontalAlignment(SwingConstants.CENTER);

			
			switch(cptX){
			case 0:
				buttons[cpt].setBounds(x,yAlb,175,175);
				albumNames[cpt].setBounds(x,yLab,175,20);
				myPanel.add(buttons[cpt]);
				myPanel.add(albumNames[cpt]);
				break;
			case 1:
				buttons[cpt].setBounds(x+199,yAlb,175,175);
				albumNames[cpt].setBounds(x+199,yLab,175,20);
				myPanel.add(buttons[cpt]);
				myPanel.add(albumNames[cpt]);
				break;
			}
			cptX++;
			if(cptX==2){
				cptX=0;
				yAlb+=220;
				yLab+=220;
			}
			cpt++;
		  }
		}
		myPanel.setLayout(null);
		myPanel.setPreferredSize(new Dimension(450,yAlb+220));
	
		return myPanel;
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
}
