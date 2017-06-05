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
        super("Reich Sauron");
        getContentPane().setLayout(null);
        setResizable(false);
        
        statusBar = new JLabel("");
        statusBar.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        statusBar.setBackground(Color.DARK_GRAY);
        statusBar.setForeground(new Color(255, 255, 255));
        statusBar.setOpaque(true);
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setBounds(179, 0, 128, 20);
        getContentPane().add(statusBar);
        setTime();
        
        BufferedImage bcontact = resizePhotos("contact");
        ImageIcon contactButtonImage= new ImageIcon(bcontact);
        
        contactButton = new JButton();
        contactButton.setForeground(Color.WHITE);
        contactButton.setFont(new Font("Avenir Next", Font.BOLD | Font.ITALIC, 16));
        contactButton.setText("Contact");
        contactButton.setBackground(Color.DARK_GRAY);
        contactButton.setOpaque(true);
        contactButton.setContentAreaFilled(true);
        contactButton.setBorderPainted(false);
        contactButton.setBounds(121, 40, 230, 207);
        contactButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contacts.setVisible(true);
                setVisible(false);
            }
        });
        
        BufferedImage bgal = resizePhotos("gallery");
        ImageIcon galButtonImage= new ImageIcon(bgal);
        
        galleryButton = new JButton();
        galleryButton.setForeground(Color.WHITE);
        galleryButton.setFont(new Font("Avenir Next", Font.BOLD | Font.ITALIC, 16));
        galleryButton.setText("Gallery");
        galleryButton.setBackground(Color.DARK_GRAY);
        galleryButton.setOpaque(true);
        galleryButton.setContentAreaFilled(true);
        galleryButton.setBorderPainted(false);
        galleryButton.setBounds(121, 287, 230, 207);
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
        
        BufferedImage bcalc = resizePhotos("calc");
        ImageIcon calButtonImage= new ImageIcon(bcalc);
        
        calculatorButton = new JButton();
        calculatorButton.setForeground(Color.WHITE);
        calculatorButton.setFont(new Font("Avenir Next", Font.BOLD | Font.ITALIC, 16));
        calculatorButton.setText("Calculator");
        calculatorButton.setBackground(Color.DARK_GRAY);
        calculatorButton.setOpaque(true);
        calculatorButton.setContentAreaFilled(true);
        calculatorButton.setBorderPainted(false);
        calculatorButton.setBounds(121, 531, 230, 207);
        calculatorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculator.setVisible(true);
                setVisible(false);
            }
        });
        getContentPane().add(contactButton);
        getContentPane().add(galleryButton);
        getContentPane().add(calculatorButton);

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
