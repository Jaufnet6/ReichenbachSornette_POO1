import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GalleryFrame extends JFrame{
	//Title (top of frame)
	private JLabel gallery = new JLabel("GALLERY");
	//Album name
	private JLabel albumName;
	//To all pictures OR to all albums (bottom of frame)
	private JButton allPics = new JButton("All pictures");
	private JButton allAlbums = new JButton("Albums");
	//When user click on an album
	private JButton selectedAlbum;
	private JButton addAlbum = new JButton("+");
	//Research button+field
	private JButton search = new JButton("SEARCH");
	private JTextField searchText = new JTextField(25);
	
	private JPanel topButtons = new JPanel();
	private JPanel bottomButtons = new JPanel();
	
	public GalleryFrame(){
		super("Gallery");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);

		
		add(gallery, BorderLayout.NORTH);
		//Setting sizes for top field and buttons
		topButtons.setSize(480, 100);
		addAlbum.setSize(100, 100);
		searchText.setSize(280, 100);
		search.setSize(100, 100);
		//Adding top buttons to panel
		topButtons.add(addAlbum);
		topButtons.add(searchText);
		topButtons.add(search);
		//Setting sizes for bottom buttons
		bottomButtons.setSize(480, 150);
		allPics.setSize(240,150);
		allAlbums.setSize(240,150);
		//Adding bottom buttons to panel
		bottomButtons.add(allPics);
		bottomButtons.add(allAlbums);

		add(topButtons);
		add(bottomButtons, BorderLayout.SOUTH);

	}
}
