import java.awt.Button;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
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

public class PicturesFrame extends JFrame{

	private JFrame frame;
	private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PicturesFrame window = new PicturesFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public PicturesFrame() throws IOException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);
		loadPics();
		
		JButton albums = new JButton("ALBUMS");
		albums.setBackground(SystemColor.inactiveCaption);
		albums.setBounds(236, 679, 238, 86);
		getContentPane().add(albums);
		
		JLabel pictures = new JLabel("PICTURES");
		pictures.setBackground(SystemColor.textHighlight);
		pictures.setOpaque(true);
		pictures.setHorizontalAlignment(SwingConstants.CENTER);
		pictures.setBounds(0, 679, 238, 86);
		getContentPane().add(pictures);
	}

	
	public void loadPics() throws IOException{
		FileInputStream fr;
		BufferedInputStream bfr;
		File picFolder = new File(path);
		Icon[] images = new ImageIcon[(int) picFolder.length()];
		JButton[] buttons = new JButton[(int) picFolder.length()];
		//Position for the first image
		int x=12;
		int y=51;
		int cptX=0;
		String imgPath;
		
		for(int i=0;i<images.length;i++){
			fr = new FileInputStream(path+"\\"+Integer.toString(i)+".jpg");
			bfr = new BufferedInputStream(fr);
			imgPath=path+"\\"+Integer.toString(i)+".jpg";
			images[i] = new ImageIcon(imgPath);
			buttons[i] = new JButton(images[i]);
			
			switch (cptX){
			case 0:
				buttons[i].setBounds(x, y, 125, 125);
				getContentPane().add(buttons[i]);
				break;
			case 1:
				buttons[i].setBounds(x+162, y, 125, 125);
				getContentPane().add(buttons[i]);
				break;
			case 2:
				buttons[i].setBounds(x+325, y, 125, 125);
				getContentPane().add(buttons[i]);
				break;
			}
			cptX++;
			if(cptX==3){
				cptX=0;
				y += 162;
			}
			bfr.close();
		}
	}

}
