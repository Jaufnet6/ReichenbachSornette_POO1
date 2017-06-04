import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;

public class PicturesFrame extends JFrame{

	private JPanel picturesPanel = new JPanel();
	private JScrollPane scroll;
	//Mac: /Users/black and white/Desktop/App/Gallery
	//Windows: C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery
	private String path = "/Users/black and white/Desktop/App/gallery";

	public PicturesFrame() throws IOException {
		super("Pictures");
		getContentPane().setBackground(new Color(0, 102, 204));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);
		picturesPanel=loadPics();
		
		//Bottom buttons
		JButton switchToAlbums = new JButton("ALBUMS");
		switchToAlbums.addActionListener(new AlbumButtonListener());
		switchToAlbums.setBackground(SystemColor.inactiveCaption);
		switchToAlbums.setBounds(325, 690, 140, 70);
		getContentPane().add(switchToAlbums);
		
		JLabel pictures = new JLabel("PICTURES");
		pictures.setBackground(new Color(102, 153, 51));
		pictures.setOpaque(true);
		pictures.setHorizontalAlignment(SwingConstants.CENTER);
		pictures.setBounds(10, 690, 140, 70);
		getContentPane().add(pictures);
		
		//My pictures
        scroll = new JScrollPane(picturesPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 0, 476, 675);
        getContentPane().add(scroll);
        
        JButton homeButton = new JButton("HOME");
        homeButton.setForeground(new Color(0, 0, 0));
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
	
	class AlbumButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			try {
				DefaultGalleryFrame albumFrame = new DefaultGalleryFrame();
				albumFrame.setVisible(true);
				setVisible(false);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class PictureButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//Getting pic from the pressed button
			Icon img = ((AbstractButton) e.getSource()).getIcon();
			
			JLabel image = new JLabel(img);
			image.setBounds(10, 55, 452, 697);
			
			JButton back = new JButton("BACK");
			back.setBackground(SystemColor.textHighlight);
			back.setBounds(0, 0, 97, 42);
			
			//Frame that displays the pic in big
			JFrame bigPic = new JFrame();
			bigPic.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			bigPic.setSize(480, 800);
			bigPic.setResizable(false);	
			bigPic.getContentPane().setLayout(null);
			bigPic.getContentPane().add(image);
			bigPic.getContentPane().add(back);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bigPic.setVisible(false);
					setVisible(true);
				}
			});
			bigPic.setVisible(true);
			setVisible(false);
			}
			
		}
	
	private JPanel loadPics() throws IOException{
		JPanel myPanel = new JPanel();
		myPanel.setBackground(new Color(102, 153, 255));
		FileInputStream fr;
		BufferedInputStream bfr;
		File picFolder = new File(path);
		Icon[] images = new ImageIcon[picFolder.listFiles().length];
		JButton[] buttons = new JButton[picFolder.listFiles().length];
		String imgPath;

		//Position for the first image
		int x=22;
		int y=20;
		int cptX=0;
		
		for(int i=0;i<images.length;i++){
			imgPath=path+"/"+Integer.toString(i)+".jpg";
			fr = new FileInputStream(imgPath);
			bfr = new BufferedInputStream(fr);
			images[i] = new ImageIcon(imgPath);
			buttons[i] = new JButton(images[i]);
			buttons[i].addActionListener(new PictureButtonListener());
			
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
