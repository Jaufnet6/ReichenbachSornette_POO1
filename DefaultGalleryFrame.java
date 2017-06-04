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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class DefaultGalleryFrame extends JFrame{

	private JTextField txtSearch;
	//Mac: /Users/black and white/Desktop/App/Album
	//Windows: C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\albums
	private String path = "/Users/black and white/Desktop/App/albums";
	private Icon defaultAlbum = new ImageIcon("/Users/black and white/Desktop/App/defaultPictures/album.png");
	private JLabel album1Title;
	private JScrollPane scroll;
	private JPanel albumsPanel = new JPanel();
	
	public DefaultGalleryFrame() throws ClassNotFoundException, IOException {
	    super("Albums");
	    getContentPane().setBackground(new Color(0, 102, 204));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);
		albumsPanel=loadAlbums();
		
		//Top part of the frame
		JButton addAlbum = new JButton("Add album");
		addAlbum.setForeground(new Color(102, 102, 102));
		addAlbum.addActionListener(new AddAlbumListener());
		addAlbum.setBackground(Color.GREEN);
		addAlbum.setBounds(5, 5, 100, 50);
		getContentPane().add(addAlbum);
		
		//Research components
		txtSearch = new JTextField();
		txtSearch.setForeground(new Color(102, 102, 102));
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setText("Search for an album...");
		txtSearch.setBounds(115, 5, 250, 50);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton search = new JButton("Search");
		search.setForeground(new Color(102, 102, 102));
		search.setBounds(375, 5, 100, 50);
		search.addActionListener(new SearchAlbumListener());
		getContentPane().add(search);
		
		//Bottom part
		JButton switchToPictures = new JButton("PICTURES");
		switchToPictures.setForeground(new Color(102, 102, 102));
		switchToPictures.addActionListener(new PicturesButtonListener());
		switchToPictures.setBackground(SystemColor.activeCaption);
		switchToPictures.setBounds(10, 690, 140, 70);
		getContentPane().add(switchToPictures);
		
		JLabel albums = new JLabel("ALBUMS");
		albums.setOpaque(true);
		albums.setBackground(new Color(102, 153, 51));
		albums.setHorizontalAlignment(SwingConstants.CENTER);
		albums.setBounds(325, 690, 140, 70);
		getContentPane().add(albums);
		
		//Middle part
		scroll = new JScrollPane(albumsPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(0,60,480,616);
		getContentPane().add(scroll);
		
		JButton homeButton = new JButton("HOME");
		homeButton.setForeground(new Color(102, 102, 102));
		homeButton.setBackground(Color.BLACK);
		homeButton.setBounds(165, 690, 140, 70);
		homeButton.addActionListener(new HomeListener());
		getContentPane().add(homeButton);
	}
	
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
	
	class SearchAlbumListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			String request = txtSearch.getText();
			String albPath;
			File albFolder = new File(path);
			Album albums[] = new Album[albFolder.listFiles().length];
			Album albumsFound[] = new Album[albums.length];
			int cpt=0;
			int cptFound=0;
			
			//Search if each album contains the requested string and stock them if they do
			File[] files = new File(path).listFiles();
			for(File file : files){
				if(file.isFile()){
					albPath=file.getAbsolutePath();
					try {
						albums[cpt] = readFile(albPath);
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
					if(albums[cpt].getName().contains(request)){
						albumsFound[cptFound] = albums[cpt];
	//					System.out.println("Album name : "+albumsFound[cptFound].getName());
						cptFound++;
					}
				}
				cpt++;
			}
			
			//Creating the panel that displays the searched albums
			JPanel searchedAlbumsPanel = new JPanel();
			JButton[] buttons = new JButton[cptFound];
			JLabel[] albumNames = new JLabel[cptFound];
			String[] albumIcons = new String[cptFound];
			Icon buttonImg;
			int x = 40;
			//Position for the first album
			int yAlb = 20;
			//Position for the first label
			int yLab = 200;
			int cptX = 0;
			
			//Filling the panel
			for (int i = 0; i < cptFound; i++) {
				albumIcons[i] = albumsFound[i].getHomePic();
				buttonImg = new ImageIcon(albumIcons[i]);
				buttons[i] = new JButton(buttonImg);
				buttons[i].setName(albumsFound[i].getName());
				buttons[i].addActionListener(new AlbumButtonListener());
				albumNames[i] = new JLabel(albumsFound[i].getName());
				albumNames[i].setHorizontalAlignment(SwingConstants.CENTER);
				
				switch(cptX){
				case 0:
					buttons[i].setBounds(x,yAlb,175,175);
					albumNames[i].setBounds(x,yLab,175,20);
					searchedAlbumsPanel.add(buttons[i]);
					searchedAlbumsPanel.add(albumNames[i]);
					break;
				case 1:
					buttons[i].setBounds(x+199,yAlb,175,175);
					albumNames[i].setBounds(x+199,yLab,175,20);
					searchedAlbumsPanel.add(buttons[i]);
					searchedAlbumsPanel.add(albumNames[i]);
					break;
				}
				cptX++;
				if(cptX==2){
					cptX=0;
					yAlb+=220;
					yLab+=220;
				}
			}
			searchedAlbumsPanel.setLayout(null);
			searchedAlbumsPanel.setPreferredSize(new Dimension(450,yAlb+220));
			
			//Creating the new frame, which is the same than DefaultGalleryFrame but only with good albums
			JFrame searchedAlbumsFrame = new JFrame();
			searchedAlbumsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			searchedAlbumsFrame.setSize(480, 800);
			searchedAlbumsFrame.setResizable(false);
			searchedAlbumsFrame.getContentPane().setLayout(null);
			
			JButton back = new JButton("BACK");
			back.setBackground(Color.RED);
			back.setBounds(0, 0, 98, 59);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchedAlbumsFrame.setVisible(false);
					txtSearch.setText("Search for an album...");
					setVisible(true);
				}
			});
			searchedAlbumsFrame.getContentPane().add(back);
			
			JLabel requestLabel = new JLabel();
			requestLabel.setText("Current research : "+request);
			requestLabel.setHorizontalAlignment(SwingConstants.CENTER);
			requestLabel.setBounds(59, 0, 364, 59);
			searchedAlbumsFrame.getContentPane().add(requestLabel);
			
			JLabel nothing = new JLabel("No album matching.");
			nothing.setHorizontalAlignment(SwingConstants.CENTER);
			nothing.setBounds(0,60,474,616);
					
			JScrollPane scroll = new JScrollPane(searchedAlbumsPanel);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroll.setBounds(0,60,474,616);
			
			//Checking if something has been found
			if(cptFound==0)
			searchedAlbumsFrame.getContentPane().add(nothing);
			else
			searchedAlbumsFrame.getContentPane().add(scroll);
			
			searchedAlbumsFrame.setVisible(true);
			setVisible(false);
		}
		
	}
	
	class AddAlbumListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			AlbumCreationFrame acf;
			try {
				acf = new AlbumCreationFrame();
				acf.setVisible(true);
				setVisible(false);
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
				setVisible(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Clicking on an album display its pictures
	class AlbumButtonListener implements ActionListener{

		String albName;
		
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			String name = button.getName();
			Album currentAlbum = new Album();
			File albFolder = new File(path);
			Album[] albums = new Album[albFolder.listFiles().length];
			String albPath;
			int cpt=0;
			
			//Searching for the album user clicked
			File[] files = new File(path).listFiles();
			for(File file : files){
				if(file.isFile()){
					albPath=file.getAbsolutePath();
					try {
						albums[cpt] = readFile(albPath);
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
					if(albums[cpt].getName().equals(name)){
						currentAlbum = albums[cpt];
						albName = currentAlbum.getName();
						break;
					}
				}
				cpt++;
			}
			
			//Getting my album images
			Icon[] albumImages = new ImageIcon[currentAlbum.getPics().length];
			for (int i = 0; i < currentAlbum.getPics().length; i++) {
				albumImages[i] = new ImageIcon(currentAlbum.getPics()[i]);
			}
			
			//New Frame, which is displaying my pics
			JLabel albumName = new JLabel();
			albumName.setText(currentAlbum.getName());
			albumName.setFont(new Font("Tahoma", Font.BOLD, 20));
			albumName.setHorizontalAlignment(SwingConstants.CENTER);
			albumName.setBounds(97, 0, 280, 42);
			
			//Filling the panel
			JPanel picturesPanel = new JPanel();
			try {
				picturesPanel = loadAlbumPics(albumImages);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			JScrollPane scroll;
			
			JFrame albumFrame = new JFrame();
			
			JButton back = new JButton("BACK");
			back.setBackground(SystemColor.textHighlight);
			back.setBounds(0, 0, 97, 42);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					albumFrame.setVisible(false);
					setVisible(true);
				}
			});
			
			//Deleting the current album
			JButton delete = new JButton("DELETE");
			delete.setBackground(Color.RED);
			delete.setBounds(377, 0, 97, 42);
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					File file = new File(path+"\\"+albName+".txt");
					try {					
						//Confirmation
						int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete "+albName+" ?");
						if(option==JOptionPane.YES_OPTION){
							file.delete();
							//Opening a new Gallery frame after deleting the album
							DefaultGalleryFrame dgf = new DefaultGalleryFrame();
							albumFrame.setVisible(false);
							dgf.setVisible(true);
						}
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			albumFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			albumFrame.setSize(480, 800);
			albumFrame.setResizable(false);	
			albumFrame.getContentPane().setLayout(null);
			albumFrame.getContentPane().add(back);
			albumFrame.getContentPane().add(delete);
			albumFrame.getContentPane().add(albumName);
			scroll = new JScrollPane(picturesPanel);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroll.setBounds (0, 50, 476, 775);
			albumFrame.getContentPane().add(scroll);
			albumFrame.setVisible(true);
			setVisible(false);
			
		}
			
	}
	
	private JPanel loadAlbumPics(Icon[] a) throws IOException{
		JPanel myPanel = new JPanel();
		JLabel[] labels = new JLabel[a.length];
		
		int x=22;
		int y=20;
		int cptX=0;
		
		for(int i=0; i<a.length;i++){
			labels[i] = new JLabel(a[i]);
			
			switch (cptX){
			case 0:
				labels[i].setBounds(x, y, 125, 125);
				myPanel.add(labels[i]);
				break;
			case 1:
				labels[i].setBounds(x+143, y, 125, 125);
				myPanel.add(labels[i]);
				break;
			case 2:
				labels[i].setBounds(x+287, y, 125, 125);
				myPanel.add(labels[i]);
				break;
			}
			cptX++;
			if(cptX==3){
				cptX=0;
				y+=143;
			}
		}
		myPanel.setLayout(null);
		myPanel.setPreferredSize(new Dimension(450,y+200));
		return myPanel;
	}
	
	//Loading albums already created
	private JPanel loadAlbums() throws ClassNotFoundException, IOException{
		JPanel myPanel = new JPanel();
		myPanel.setBackground(new Color(102, 153, 255));
		File albFolder = new File(path);
		Album[] albums = new Album[albFolder.listFiles().length];
		JButton[] buttons = new JButton[albFolder.listFiles().length];
		JLabel[] albumNames = new JLabel[albFolder.listFiles().length];
		String[] albumIcons = new String[albFolder.listFiles().length];
		String albPath;
		Icon buttonImg;
		int cpt = 0;
		int x = 40;
		//Position for the first album
		int yAlb = 20;
		//Position for the first label
		int yLab = 200;
		int cptX = 0;
	
		File[] files = new File(path).listFiles();
		//Filling the panel
		for(File file : files){
		  if(file.isFile()){
		    albPath=file.getAbsolutePath();
			albums[cpt] = readFile(albPath);
			albumIcons[cpt] = albums[cpt].getHomePic();
			buttonImg = new ImageIcon(albumIcons[cpt]);
			buttons[cpt] = new JButton(buttonImg);
			buttons[cpt].setName(albums[cpt].getName());
			buttons[cpt].addActionListener(new AlbumButtonListener());
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
	
	//Read an already saved album
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
