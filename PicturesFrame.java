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

public class PicturesFrame extends JFrame{

	private JFrame frame;
	private JPanel picturesPanel = new JPanel();
	private JScrollPane scroll;
	private String path = "C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\gallery";

	public PicturesFrame() throws IOException {
		super("Pictures");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);
		getContentPane().setLayout(null);
		picturesPanel=loadPics();
		
		//Bottom buttons
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
		
		//My pictures
        scroll = new JScrollPane(picturesPanel);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(0, 0, 476, 675);
        add(scroll);
	}
	
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			Icon img = ((AbstractButton) e.getSource()).getIcon();
			
			JLabel image = new JLabel(img);
			image.setBounds(10, 55, 452, 697);
			JButton back = new JButton("BACK");
			back.setBackground(SystemColor.textHighlight);
			back.setBounds(0, 0, 97, 42);
			JFrame bigPic = new JFrame();
			bigPic.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			bigPic.setSize(480, 800);
			bigPic.setResizable(false);	
			bigPic.setLayout(null);
			bigPic.add(image);
			bigPic.add(back);
			bigPic.setVisible(true);
			}
			
		}
	
	private JPanel loadPics() throws IOException{
		JPanel myPanel = new JPanel();
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
			imgPath=path+"\\"+Integer.toString(i)+".jpg";
			fr = new FileInputStream(imgPath);
			bfr = new BufferedInputStream(fr);
			images[i] = new ImageIcon(imgPath);
			buttons[i] = new JButton(images[i]);
			buttons[i].addActionListener(new ButtonListener());
			
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
