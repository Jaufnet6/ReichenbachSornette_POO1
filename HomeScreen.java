import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Icon backgroundImage = new ImageIcon("/Users/black and white/Desktop/Backgrounds/tiger.jpg");
    private Icon calButtonImage = new ImageIcon("/Users/black and white/Desktop/Backgrounds/calc.jpg");
    private Icon contButtonImage = new ImageIcon("/Users/black and white/Desktop/Backgrounds/contact.jpg");
    private Icon galButtonImgae = new ImageIcon("/Users/black and white/Desktop/Backgrounds/gallery.jpg");


    public static void main(String[] args) {
        try {
            Frame1 window = new Frame1();
            window.frame.setVisible(true);
            window.frame.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HomeScreen() throws InterruptedException, IOException {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        
        statusBar = new JLabel("");
        statusBar.setBackground(new Color(0, 0, 0));
        statusBar.setForeground(new Color(255, 255, 255));
        statusBar.setOpaque(true);
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setBounds(179, 0, 128, 20);
        frame.getContentPane().add(statusBar);
        setTime();
        
        contactButton = new JButton(contButtonImage);
        contactButton.setOpaque(false);
        contactButton.setContentAreaFilled(false);
        contactButton.setBorderPainted(false);
        contactButton.setBounds(121, 40, 230, 207);
        contactButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "APP 1");
            }
        });
        
        galleryButton = new JButton(galButtonImgae);
        galleryButton.setOpaque(false);
        galleryButton.setContentAreaFilled(false);
        galleryButton.setBorderPainted(false);
        galleryButton.setBounds(121, 287, 230, 207);
        galleryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "APP 2");
            }
        });
        
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
        
        
        frame.getContentPane().add(contactButton);
        frame.getContentPane().add(galleryButton);
        frame.getContentPane().add(calculatorButton);
        
        lblCalculator = new JLabel("Calculator");
        lblCalculator.setHorizontalAlignment(SwingConstants.CENTER);
        lblCalculator.setBackground(new Color(255, 250, 240));
        lblCalculator.setOpaque(true);
        lblCalculator.setBounds(195, 741, 76, 16);
        frame.getContentPane().add(lblCalculator);
        
        lblGallery = new JLabel("Gallery");
        lblGallery.setHorizontalAlignment(SwingConstants.CENTER);
        lblGallery.setBackground(new Color(255, 250, 240));
        lblGallery.setOpaque(true);
        lblGallery.setBounds(195, 504, 76, 16);
        frame.getContentPane().add(lblGallery);
        
        lblContact = new JLabel("Contact");
        lblContact.setHorizontalAlignment(SwingConstants.CENTER);
        lblContact.setOpaque(true);
        lblContact.setBackground(new Color(255, 250, 240));
        lblContact.setBounds(195, 245, 76, 16);
        frame.getContentPane().add(lblContact);

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
}
