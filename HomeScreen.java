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
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;

<<<<<<< HEAD
public class Frame1 {
=======
public class HomeScreen {
>>>>>>> e0e64137b82c17b86d6710dae698dc3aea6ebbd9

    private  Timer tm;
    private static LocalDateTime time;
    private JLabel statusBar;
    private JLabel backgroundLbl;
    private JFrame frame;
    private JButton contactButton;
    private JButton galleryButton;
    private JButton calculatorButton;
    private Icon backgroundImage = new ImageIcon("/Users/black and white/Desktop/tiger.jpg");
    private Icon calButtonImage = new ImageIcon("/Users/black and white/Desktop/calc.jpg");
    private Icon contButtonImage = new ImageIcon("/Users/black and white/Desktop/contact.jpg");
    private Icon galButtonImgae = new ImageIcon("/Users/black and white/Desktop/gallery.jpg");
    private JLabel lblContact;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame1 window = new Frame1();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

<<<<<<< HEAD
    public Frame1() throws InterruptedException, IOException {
=======
    public HomeScreen() throws InterruptedException, IOException {
>>>>>>> e0e64137b82c17b86d6710dae698dc3aea6ebbd9
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
        calculatorButton.setBounds(121, 522, 230, 207);
        calculatorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "APP 3");
            }
        });
        
        
        frame.getContentPane().add(contactButton);
        frame.getContentPane().add(galleryButton);
        frame.getContentPane().add(calculatorButton);
        
        JLabel lblCalculator = new JLabel("Calculator");
        lblCalculator.setBounds(208, 731, 76, 16);
        frame.getContentPane().add(lblCalculator);
        
        JLabel lblGallery = new JLabel("Gallery");
        lblGallery.setBounds(208, 494, 76, 16);
        frame.getContentPane().add(lblGallery);
        
        lblContact = new JLabel("Gallery");
        lblContact.setBounds(208, 259, 76, 16);
        frame.getContentPane().add(lblContact);

        backgroundLbl = new JLabel(backgroundImage);
        backgroundLbl.setText("Calculator");
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
