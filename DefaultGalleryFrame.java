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
import java.awt.Color;

public class DefaultGalleryFrame extends JFrame{

	private JTextField txtSearch;
	private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery";
	private Icon defaultAlbum = new ImageIcon("C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery\\defaultPictures\\album.png");
	private JLabel album1Title;
	
	public DefaultGalleryFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JButton addAlbum = new JButton("Add album");
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
	}
	
	class PicturesButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			PicturesFrame picFrame;
			try {
				picFrame = new PicturesFrame();
				picFrame.setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
