import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class DisplayAlbumFrame extends JFrame{
	
	private JButton back = new JButton("BACK");
	private JLabel albumName = new JLabel("");
	
	public DisplayAlbumFrame() {
		super("Album");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);	
		
		back.setBackground(SystemColor.textHighlight);
		back.setBounds(0, 0, 97, 42);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultGalleryFrame dgf;
				try {
					dgf = new DefaultGalleryFrame();
					dgf.setVisible(true);
					setVisible(false);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		getContentPane().add(back);
		
		albumName.setFont(new Font("Tahoma", Font.BOLD, 20));
		albumName.setHorizontalAlignment(SwingConstants.CENTER);
		albumName.setBounds(97, 0, 280, 42);
		getContentPane().add(albumName);
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.setBackground(Color.RED);
		deleteButton.setBounds(377, 0, 97, 42);
		getContentPane().add(deleteButton);
	}
}
