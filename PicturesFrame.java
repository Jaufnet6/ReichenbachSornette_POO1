import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class PicturesFrame extends JFrame{

	private JPanel picturesPanel = new JPanel();
	private JScrollPane scroll;
	private JLabel lblBackground;
	//Mac: /Users/black and white/Desktop/App/gallery
	//Windows: C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery
	private String path = "/Users/black and white/Desktop/App/gallery";

	public PicturesFrame() throws IOException {
		super("Pictures");
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);
		picturesPanel=loadPics();
		
        scroll = new JScrollPane(picturesPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 0, 480, 675);
        getContentPane().add(scroll);
        
        //Bottom part
        JButton switchToAlbums = new JButton("ALBUMS");
		switchToAlbums.setFont(new Font("Avenir Next", Font.BOLD, 13));
		switchToAlbums.setForeground(Color.BLACK);
		switchToAlbums.addActionListener(new AlbumButtonListener());
		switchToAlbums.setBackground(Color.LIGHT_GRAY);
		switchToAlbums.setBounds(240, 676, 240, 50);
		getContentPane().add(switchToAlbums);
		
		JLabel pictures = new JLabel("PICTURES");
		pictures.setFont(new Font("Avenir Next", Font.BOLD, 13));
		pictures.setForeground(Color.WHITE);
		pictures.setBackground(Color.DARK_GRAY);
		pictures.setHorizontalAlignment(SwingConstants.CENTER);
		pictures.setBounds(0, 676, 240, 50);
		getContentPane().add(pictures);
		
        JButton homeButton = new JButton("HOME");
        homeButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
        homeButton.setForeground(Color.BLACK);
        homeButton.setBounds(0, 723, 480, 55);
		homeButton.addActionListener(new HomeListener());
        getContentPane().add(homeButton);
        
        lblBackground = new JLabel(new ImageIcon("/Users/black and white/Desktop/App/Backgrounds/background.png"));
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
	
	//Go to DefaultGalleryFrame
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
	
	//Open a big picture
	class PictureButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//Getting pic from the pressed button
			Icon img = ((AbstractButton) e.getSource()).getIcon();
			
			JLabel image = new JLabel(img);
			image.setBounds(10, 55, 452, 650);
			
			JButton back = new JButton("BACK");
			back.setFont(new Font("Avenir Next", Font.BOLD, 13));
			back.setForeground(Color.BLACK);
			back.setBounds(0, 0, 97, 42);
			
			JButton homeButton = new JButton("HOME");
	        homeButton.setFont(new Font("Avenir Next", Font.BOLD, 13));
	        homeButton.setForeground(Color.BLACK);
	        homeButton.setBounds(0, 723, 480, 55);
			
	        getContentPane().add(homeButton);
			
			//Frame that displays the pic in big
			JFrame bigPic = new JFrame("Picture");
			bigPic.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			bigPic.setSize(480, 800);
			bigPic.setResizable(false);	
			bigPic.getContentPane().setBackground(Color.WHITE);
			bigPic.getContentPane().setLayout(null);
			bigPic.getContentPane().add(image);
			bigPic.getContentPane().add(back);
			bigPic.getContentPane().add(homeButton);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bigPic.setVisible(false);
					setVisible(true);
				}
			});
			homeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					HomeScreen hs;
					try {
						hs = new HomeScreen();
						bigPic.setVisible(false);
						hs.setVisible(true);
					} catch (ClassNotFoundException | InterruptedException | IOException e1) {
						e1.printStackTrace();
					}
				}
			});
			bigPic.setVisible(true);
			setVisible(false);
			}
			
		}
	
	private JPanel loadPics() throws IOException{
		JPanel myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
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
