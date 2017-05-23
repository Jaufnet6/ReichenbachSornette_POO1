import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class HomeScreen {

    private  Timer tm;
    private static LocalDateTime time;
    private JLabel statusBar;
    private JLabel backgroundLbl;
    private JLabel lblCalculator;
    private JLabel lblContact;
    private JLabel lblGallery;
    private JFrame frame;
    private JButton contactButton;
    private JButton galleryButton;
    private JButton calculatorButton;
    
    private File folder = new File("/Users/black and white/Desktop/App/Backgrounds/");
    // Mac : /Users/black and white/Desktop/App/Backgrounds/tiger.jpg
    // Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\homescreen\\tiger.jpg
    private Icon backgroundImage = new ImageIcon("/Users/black and white/Desktop/App/Backgrounds/tiger.jpg");
    // Mac : /Users/black and white/Desktop/Backgrounds/calc.jpg
    // Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\homescreen\\calc.jpg
    //private Icon calButtonImage = new ImageIcon("/Users/black and white/Desktop/App/Backgrounds/calc.jpg");
    // Mac : /Users/black and white/Desktop/App/Backgrounds/contact.jpg
    // Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\homescreen\\contact.png
    //private Icon contButtonImage = new ImageIcon("/Users/black and white/Desktop/App/Backgrounds/contact.jpg");
    // Mac : /Users/black and white/Desktop/App/Backgrounds/gallery.jpg
    // Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\homescreen\\gallery.jpg
    //private Icon galButtonImgae = new ImageIcon("/Users/black and white/Desktop/App/Backgrounds/gallery.jpg");


    public static void main(String[] args) {
        try {
            HomeScreen window = new HomeScreen();
            window.frame.setVisible(true);
            window.frame.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HomeScreen() throws InterruptedException, IOException {
        frame = new JFrame("Reich Sauron");
        frame.getContentPane().setLayout(null);
        
        statusBar = new JLabel("");
        statusBar.setBackground(new Color(102, 102, 102));
        statusBar.setForeground(new Color(255, 255, 255));
        statusBar.setOpaque(true);
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setBounds(179, 0, 128, 20);
        frame.getContentPane().add(statusBar);
        setTime();
        
        BufferedImage bcontact = resizePhotos("contact");
        ImageIcon contactButtonImage= new ImageIcon(bcontact);
        
        contactButton = new JButton(contactButtonImage);
        contactButton.setOpaque(false);
        contactButton.setContentAreaFilled(false);
        contactButton.setBorderPainted(false);
        contactButton.setBounds(121, 40, 230, 207);
        contactButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "APP 1");
            }
        });
        
        BufferedImage bgal = resizePhotos("gallery");
        ImageIcon galButtonImage= new ImageIcon(bgal);
        
        galleryButton = new JButton(galButtonImage);
        galleryButton.setOpaque(false);
        galleryButton.setContentAreaFilled(false);
        galleryButton.setBorderPainted(false);
        galleryButton.setBounds(121, 287, 230, 207);
        galleryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "APP 2");
            }
        });
        
        BufferedImage bcalc = resizePhotos("calc");
        ImageIcon calButtonImage= new ImageIcon(bcalc);
        
        calculatorButton = new JButton(calButtonImage);
        calculatorButton.setOpaque(false);
        calculatorButton.setContentAreaFilled(false);
        calculatorButton.setBorderPainted(false);
        calculatorButton.setBounds(115, 532, 230, 207);
        calculatorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "APP 3");
            }
        });
        
        lblContact = new JLabel("Contact");
        lblContact.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
        lblContact.setForeground(new Color(255, 255, 255));
        lblContact.setHorizontalAlignment(SwingConstants.CENTER);
        lblContact.setOpaque(false);
        lblContact.setBackground(new Color(255, 255, 255));
        lblContact.setBounds(195, 136, 76, 16);
        frame.getContentPane().add(lblContact);
        
        
        frame.getContentPane().add(contactButton);
        
        lblGallery = new JLabel("Gallery");
        lblGallery.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
        lblGallery.setForeground(new Color(102, 102, 102));
        lblGallery.setHorizontalAlignment(SwingConstants.CENTER);
        lblGallery.setBackground(new Color(255, 255, 255));
        lblGallery.setOpaque(false);
        lblGallery.setBounds(195, 383, 76, 16);
        frame.getContentPane().add(lblGallery);
        frame.getContentPane().add(galleryButton);
        
        lblCalculator = new JLabel("Calculator");
        lblCalculator.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 15));
        lblCalculator.setForeground(new Color(102, 102, 102));
        lblCalculator.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalculator.setBackground(new Color(255, 255, 255));
        lblCalculator.setOpaque(false);
        lblCalculator.setBounds(195, 562, 76, 16);
        frame.getContentPane().add(lblCalculator);
        frame.getContentPane().add(calculatorButton);

        backgroundLbl = new JLabel(backgroundImage);
        backgroundLbl.setBounds(0, 0, 480, 778);
        frame.getContentPane().add(backgroundLbl);
        frame.setBounds(100, 100, 480, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    //Time on status bar
    private void setTime(){
        
        tm = new Timer(1000, new ActionListener() {          
            public void actionPerformed(ActionEvent e) {
                time = LocalDateTime.now();
                statusBar.setText(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());                
            }
        });
        tm.start();
    }
   
    private BufferedImage resizePhotos(String path) throws IOException{
        BufferedImage img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        img.createGraphics().drawImage(ImageIO.read(new File(folder, path+ ".jpg")).getScaledInstance(200, 200, Image.SCALE_SMOOTH),0,0,null);

        /*for(int i = 1; i <= folder.length()-1; i++){
            
            Code for completely writing the image resized
            ImageIO.write(img, "jpg", new File("/Users/black and white/Desktop/Photos/" + i + "0" + ".jpg"));

        }*/
        return img;
    }
}
