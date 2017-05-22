import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class DefaultGalleryFrame extends JFrame{

	private JFrame frame;
	private JTextField txtSearch;
	private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery";
	private Icon defaultAlbum = new ImageIcon("C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery\\defaultPictures\\album.png");
	private JLabel album1Title;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefaultGalleryFrame window = new DefaultGalleryFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DefaultGalleryFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JButton addAlbum = new JButton("Add album");
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
		
		JButton album1 = new JButton("Album1");
		album1.setBounds(50, 114, 155, 155);
		getContentPane().add(album1);
		
		JButton album2 = new JButton("Album2");
		album2.setBounds(269, 114, 155, 155);
		getContentPane().add(album2);
		
		JButton album3 = new JButton("Album3");
		album3.setBounds(50, 355, 155, 155);
		getContentPane().add(album3);
		
		JButton album4 = new JButton("Album4");
		album4.setBounds(269, 355, 155, 155);
		getContentPane().add(album4);
		
		album1Title = new JLabel("Title1");
		album1Title.setHorizontalAlignment(SwingConstants.CENTER);
		album1Title.setBounds(50, 271, 155, 37);
		getContentPane().add(album1Title);
		
		JLabel album2Title = new JLabel("Title2");
		album2Title.setHorizontalAlignment(SwingConstants.CENTER);
		album2Title.setBounds(269, 271, 155, 37);
		getContentPane().add(album2Title);
		
		JLabel album3Title = new JLabel("Title3");
		album3Title.setHorizontalAlignment(SwingConstants.CENTER);
		album3Title.setBounds(50, 512, 155, 37);
		getContentPane().add(album3Title);
		
		JLabel album4Title = new JLabel("Title4");
		album4Title.setHorizontalAlignment(SwingConstants.CENTER);
		album4Title.setBounds(269, 512, 155, 37);
		getContentPane().add(album4Title);
		
		JButton switchToPictures = new JButton("PICTURES");
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
}
