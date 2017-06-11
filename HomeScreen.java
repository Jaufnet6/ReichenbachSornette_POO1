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

public class HomeScreen extends JFrame{

    private  Timer tm;
    private static LocalDateTime time;
    private JLabel statusBar;
    private JLabel backgroundLbl;
    private JFrame frame;
    private JButton contactButton;
    private JButton galleryButton;
    private JButton calculatorButton;
    private JLabel lblCalculator;
    private JLabel lblPhotos;
    private JLabel lblContacts;
    
    // Mac : /Users/black and white/Desktop/App/Backgrounds/
    // Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\Backgrounds
    private File folder = new File("/Users/black and white/Desktop/App/Backgrounds/");
    // Mac : /Users/black and white/Desktop/App/Backgrounds/tiger.jpg
    // Windows : C:\\Users\\Julien\\Desktop\\SEMESTRE 2\\POO\\Projet\\homescreen\\tiger.jpg
    private Icon backgroundImage = new ImageIcon("/Users/black and white/Desktop/App/Backgrounds/background.png");

    private ContactApp contacts;
    private CalculatorFrame calculator;
    private DefaultGalleryFrame dgf;
    
    public HomeScreen() throws InterruptedException, IOException, ClassNotFoundException {
        super("Smartphone");
        getContentPane().setLayout(null);
        setResizable(false);
        
        statusBar = new JLabel("");
        statusBar.setFont(new Font("Avenir Next", Font.BOLD, 50));
        statusBar.setForeground(Color.WHITE);
        statusBar.setBackground(Color.DARK_GRAY);
        statusBar.setOpaque(false);
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setBounds(0, 75, 480, 99);
        getContentPane().add(statusBar);
        setTime();
        
        contactButton = new JButton(new ImageIcon (folder + "/contact.png"));
        contactButton.setOpaque(true);
        contactButton.setContentAreaFilled(false);
        contactButton.setBorderPainted(false);
        contactButton.setBounds(25, 220, 125, 125);
        contactButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contacts.setVisible(true);
                setVisible(false);
            }
        });
        galleryButton = new JButton(new ImageIcon (folder + "/gallery.png"));
        galleryButton.setOpaque(true);
        galleryButton.setContentAreaFilled(false);
        galleryButton.setBorderPainted(false);
        galleryButton.setBounds(175, 220, 125, 125);
        galleryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    dgf = new DefaultGalleryFrame();
                    setVisible(false);
                    dgf.setVisible(true);

                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        calculatorButton = new JButton(new ImageIcon (folder + "/calc.png"));
        calculatorButton.setOpaque(true);
        calculatorButton.setContentAreaFilled(false);
        calculatorButton.setBorderPainted(false);
        calculatorButton.setBounds(325, 220, 125, 125);
        calculatorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculator.setVisible(true);
                setVisible(false);
            }
        });
        getContentPane().add(contactButton);
        getContentPane().add(galleryButton);
        getContentPane().add(calculatorButton);
        
        lblContacts = new JLabel("Contacts");
        lblContacts.setForeground(Color.WHITE);
        lblContacts.setFont(new Font("Avenir Next", Font.BOLD, 14));
        lblContacts.setHorizontalAlignment(SwingConstants.CENTER);
        lblContacts.setBounds(25, 359, 125, 16);
        getContentPane().add(lblContacts);
                
        lblCalculator = new JLabel("Calculator");
        lblCalculator.setForeground(Color.WHITE);
        lblCalculator.setFont(new Font("Avenir Next", Font.BOLD, 14));
        lblCalculator.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalculator.setBounds(325, 359, 125, 16);
        getContentPane().add(lblCalculator);
                
        lblPhotos = new JLabel("Photos");
        lblPhotos.setForeground(Color.WHITE);
        lblPhotos.setFont(new Font("Avenir Next", Font.BOLD, 14));
        lblPhotos.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhotos.setBounds(175, 359, 125, 16);
        getContentPane().add(lblPhotos);
        
        backgroundLbl = new JLabel(backgroundImage);
        backgroundLbl.setBounds(0, 0, 480, 778);
        getContentPane().add(backgroundLbl);
        setBounds(100, 100, 480, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        contacts = new ContactApp();
        calculator = new CalculatorFrame();
        
    }
    
    
    //Time on status bar
    private void setTime(){
        
        tm = new Timer(50, new ActionListener() {          
            public void actionPerformed(ActionEvent e) {
                time = LocalDateTime.now();
                statusBar.setText(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());                
            }
        });
        tm.start();
    }
   
    private BufferedImage resizePhotos(String path) throws IOException{
        BufferedImage img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        img.createGraphics().drawImage(ImageIO.read(new File(folder, path+ ".jpg")).getScaledInstance(125, 125, Image.SCALE_SMOOTH),0,0,null);

        /*for(int i = 1; i <= folder.length()-1; i++){
            
            Code for completely writing the image resized
            ImageIO.write(img, "jpg", new File("/Users/black and white/Desktop/Photos/" + i + "0" + ".jpg"));

        }*/
        return img;
    }
}
