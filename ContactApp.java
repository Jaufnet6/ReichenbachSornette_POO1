import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;

public class ContactApp {

    private JFrame frame;
    private  Timer tm;
    private static LocalDateTime time;
    private JLabel statusBar;
    private JScrollBar scrollBar;


    public static void main(String[] args) {
        try {
            ContactApp window = new ContactApp();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ContactApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Contacts");
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 480, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);        
        
        statusBar = new JLabel();
        statusBar.setBackground(new Color(102, 102, 102));
        statusBar.setForeground(new Color(255, 255, 255));
        statusBar.setOpaque(true);
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
        statusBar.setBounds(179, 0, 128, 20);
        frame.getContentPane().add(statusBar);
        setTime();
        
        scrollBar = new JScrollBar();
        scrollBar.setBounds(0, 0, 15, 778);
        frame.getContentPane().add(scrollBar);
        
        
    }
    
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
