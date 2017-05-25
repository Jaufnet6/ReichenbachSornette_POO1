import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.SystemColor;

public class CalculatorFrame extends JFrame{

	 //Area number written
    private JLabel statusBar;
    private JLabel firstNumber = new JLabel();
    private JLabel addition = new JLabel();
    private JLabel secondNumber = new JLabel();
    private JLabel resultValue = new JLabel();
    private JButton go = new JButton("=");
    private JButton clear = new JButton("C");
    private JButton clearAll = new JButton("CE");
    //Numbers
    private JButton one = new JButton("1");
    private JButton two = new JButton("2");
    private JButton three = new JButton("3");
    private JButton four = new JButton("4");
    private JButton five = new JButton("5");
    private JButton six = new JButton("6");
    private JButton seven = new JButton("7");
    private JButton eight = new JButton("8");
    private JButton nine = new JButton("9");
    private JButton zero = new JButton("0");
    private JButton negation = new JButton("+/-");
    private JButton dot = new JButton(".");    
    //Signs
    private JButton add = new JButton("+");
    private JButton substract = new JButton("-");
    private JButton multiply = new JButton("x");
    private JButton divide = new JButton("/");
    
    private  Timer tm;
    private static LocalDateTime time;
    private String choice; //Useful for the choice of symbol (sign)
    
	public CalculatorFrame() {
		 super("Calculator");
		 getContentPane().setBackground(new Color(51, 153, 204));
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(480, 800);
			setResizable(false);
			getContentPane().setLayout(null);
			
			statusBar = new JLabel("");
	        statusBar.setBackground(new Color(255, 255, 255));
	        statusBar.setForeground(new Color(102, 102, 102));
	        statusBar.setOpaque(true);
	        statusBar.setHorizontalAlignment(SwingConstants.CENTER);
	        statusBar.setBounds(179, 0, 128, 20);
	        getContentPane().add(statusBar);
	        setTime();
			
			//Top part : result
			resultValue.setOpaque(true);
			resultValue.setHorizontalAlignment(SwingConstants.RIGHT);
			resultValue.setBackground(new Color(255, 255, 255));
			resultValue.setFont(new Font("Arial Black", Font.BOLD, 40));
			resultValue.setBounds(12, 48, 450, 156);
			getContentPane().add(resultValue);
			
			//Mid part : operation
			firstNumber.setOpaque(true);
			firstNumber.setBackground(new Color(255, 255, 255));
			firstNumber.setHorizontalAlignment(SwingConstants.CENTER);
			firstNumber.setFont(new Font("Arial", Font.PLAIN, 25));
			firstNumber.setBounds(12, 217, 140, 123);
			getContentPane().add(firstNumber);
			
			addition.setOpaque(true);
			addition.setBackground(new Color(255, 255, 255));
			addition.setHorizontalAlignment(SwingConstants.CENTER);
			addition.setFont(new Font("Arial", Font.PLAIN, 25));
			addition.setBounds(154, 217, 25, 123);
			getContentPane().add(addition);
			
			secondNumber.setOpaque(true);
			secondNumber.setBackground(new Color(255, 255, 255));
			secondNumber.setHorizontalAlignment(SwingConstants.CENTER);
			secondNumber.setFont(new Font("Arial", Font.PLAIN, 25));
			secondNumber.setBounds(181, 217, 140, 123);
			getContentPane().add(secondNumber);
			
			//Buttons clears&go
			clearAll.setBackground(SystemColor.activeCaption);
			clearAll.setFont(new Font("Arial", Font.BOLD, 22));
			clearAll.setBounds(397, 217, 65, 55);
			getContentPane().add(clearAll);
			
			clear.setBackground(SystemColor.activeCaption);
			clear.setFont(new Font("Arial", Font.BOLD, 22));
			clear.setBounds(333, 217, 65, 55);
			getContentPane().add(clear);
			
			go.setBackground(SystemColor.activeCaption);
			go.setFont(new Font("Arial", Font.BOLD, 30));
			go.setBounds(333, 271, 129, 69);
			getContentPane().add(go);
			
			//Bottom part : numbers
			one.setBackground(SystemColor.activeCaption);
			one.setFont(new Font("Arial", Font.BOLD, 30));
			one.setBounds(12, 355, 110, 100);
			getContentPane().add(one);
			
			two.setBackground(SystemColor.activeCaption);
			two.setFont(new Font("Arial", Font.BOLD, 30));
			two.setBounds(121, 355, 110, 100);
			getContentPane().add(two);
			
			three.setBackground(SystemColor.activeCaption);
			three.setFont(new Font("Arial", Font.BOLD, 30));
			three.setBounds(230, 355, 110, 100);
			getContentPane().add(three);
			
			four.setBackground(SystemColor.activeCaption);
			four.setFont(new Font("Arial", Font.BOLD, 30));
			four.setBounds(12, 454, 110, 100);
			getContentPane().add(four);
			
			five.setBackground(SystemColor.activeCaption);
			five.setFont(new Font("Arial", Font.BOLD, 30));
			five.setBounds(121, 454, 110, 100);
			getContentPane().add(five);
			
			six.setBackground(SystemColor.activeCaption);
			six.setFont(new Font("Arial", Font.BOLD, 30));
			six.setBounds(230, 454, 110, 100);
			getContentPane().add(six);
			
			seven.setBackground(SystemColor.activeCaption);
			seven.setFont(new Font("Arial", Font.BOLD, 30));
			seven.setBounds(12, 553, 110, 100);
			getContentPane().add(seven);
			
			eight.setBackground(SystemColor.activeCaption);
			eight.setFont(new Font("Arial", Font.BOLD, 30));
			eight.setBounds(121, 553, 110, 100);
			getContentPane().add(eight);
			
			nine.setBackground(SystemColor.activeCaption);
			nine.setFont(new Font("Arial", Font.BOLD, 30));
			nine.setBounds(230, 553, 110, 100);
			getContentPane().add(nine);
			
			zero.setBackground(SystemColor.activeCaption);
			zero.setFont(new Font("Arial", Font.BOLD, 30));
			zero.setBounds(121, 652, 110, 100);
			getContentPane().add(zero);
			
			dot.setBackground(SystemColor.activeCaption);
			dot.setFont(new Font("Arial", Font.BOLD, 30));
			dot.setBounds(230, 652, 110, 100);
			getContentPane().add(dot);
						
			negation.setBackground(SystemColor.activeCaption);
			negation.setFont(new Font("Arial", Font.BOLD, 30));
			negation.setBounds(12, 652, 110, 100);
			getContentPane().add(negation);
			
			//Operation buttons
			add.setBackground(SystemColor.activeCaption);
			add.setFont(new Font("Arial", Font.BOLD, 30));
			add.setBounds(352, 355, 110, 100);
			getContentPane().add(add);
			
			substract.setBackground(SystemColor.activeCaption);
			substract.setFont(new Font("Arial", Font.BOLD, 30));
			substract.setBounds(352, 454, 110, 100);
			getContentPane().add(substract);
			
			multiply.setBackground(SystemColor.activeCaption);
			multiply.setFont(new Font("Arial", Font.BOLD, 30));
			multiply.setBounds(352, 553, 110, 100);
			getContentPane().add(multiply);
			
			divide.setBackground(SystemColor.activeCaption);
			divide.setFont(new Font("Arial", Font.BOLD, 30));
			divide.setBounds(352, 652, 110, 100);
			getContentPane().add(divide);
			
			JButton btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        setVisible(false);
			    }
			});
			btnBack.setBounds(10, 5, 75, 41);
			getContentPane().add(btnBack);
		
			 //Actions
	        //Action for each number button
	        one.addActionListener(new Button_Numbers());
	        two.addActionListener(new Button_Numbers());
	        three.addActionListener(new Button_Numbers());
	        four.addActionListener(new Button_Numbers());
	        five.addActionListener(new Button_Numbers());
	        six.addActionListener(new Button_Numbers());
	        seven.addActionListener(new Button_Numbers());
	        eight.addActionListener(new Button_Numbers());
	        nine.addActionListener(new Button_Numbers());
	        zero.addActionListener(new Button_Numbers());
	        negation.addActionListener(new Button_Negation());
	        dot.addActionListener(new Button_Dot());
	        //Action for the go button
	        go.addActionListener(new Button_Go());
	        //Action for both clear buttons
	        clear.addActionListener(new Button_Clear());
	        clearAll.addActionListener(new Button_ClearAll());
	        //Actions for the symbol buttons
	        add.addActionListener(new Button_Signs());
	        substract.addActionListener(new Button_Signs());
	        multiply.addActionListener(new Button_Signs());
	        divide.addActionListener(new Button_Signs());
	        
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
	
    //Listener for all the button for the numbers
    class Button_Numbers implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(addition.getText().isEmpty()){
                if(e.getSource().equals(one))
                    firstNumber.setText(firstNumber.getText() + one.getText());
                else if(e.getSource().equals(two))
                    firstNumber.setText(firstNumber.getText() + two.getText());
                else if(e.getSource().equals(three))
                    firstNumber.setText(firstNumber.getText() + three.getText());
                else if(e.getSource().equals(four))
                    firstNumber.setText(firstNumber.getText() + four.getText());
                else if(e.getSource().equals(five))
                    firstNumber.setText(firstNumber.getText() + five.getText());
                else if(e.getSource().equals(six))
                    firstNumber.setText(firstNumber.getText() + six.getText());
                else if(e.getSource().equals(seven))
                    firstNumber.setText(firstNumber.getText() + seven.getText());
                else if(e.getSource().equals(eight))
                    firstNumber.setText(firstNumber.getText() + eight.getText());
                else if(e.getSource().equals(nine))
                    firstNumber.setText(firstNumber.getText() + nine.getText());
                else if(e.getSource().equals(zero))
                    firstNumber.setText(firstNumber.getText() + zero.getText());
            }
            else{
                if(e.getSource().equals(one))
                    secondNumber.setText(secondNumber.getText() + one.getText());
                else if(e.getSource().equals(two))
                    secondNumber.setText(secondNumber.getText() + two.getText());
                else if(e.getSource().equals(three))
                    secondNumber.setText(secondNumber.getText() + three.getText());
                else if(e.getSource().equals(four))
                    secondNumber.setText(secondNumber.getText() + four.getText());
                else if(e.getSource().equals(five))
                    secondNumber.setText(secondNumber.getText() + five.getText());
                else if(e.getSource().equals(six))
                    secondNumber.setText(secondNumber.getText() + six.getText());
                else if(e.getSource().equals(seven))
                    secondNumber.setText(secondNumber.getText() + seven.getText());
                else if(e.getSource().equals(eight))
                    secondNumber.setText(secondNumber.getText() + eight.getText());
                else if(e.getSource().equals(nine))
                    secondNumber.setText(secondNumber.getText() + nine.getText());
                else if(e.getSource().equals(zero))
                    secondNumber.setText(secondNumber.getText() + zero.getText());
            }
        }
        
    }
    
    //Assigns the symbol for the equation
    class Button_Signs implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            
            if(resultValue.getText().equals("") == false || firstNumber.getText().equals("") == false){
                if(firstNumber.getText().equals("-")){
                    return;
                }
                if(resultValue.getText().equals("") == false && firstNumber.getText().equals("")){
                    firstNumber.setText(resultValue.getText());
                } 
                
                if(e.getSource().equals(add)){
                    choice = "+";
                    addition.setText("+");
                }
                else if(e.getSource().equals(substract)){
                    choice = "-";
                    addition.setText("-");
                }
                else if(e.getSource().equals(multiply)){
                    choice = "*";
                    addition.setText("x");
                }
                else if(e.getSource().equals(divide)){
                    choice = "/";
                    addition.setText("/");
                }
//                else if(e.getSource().equals(modulo)){
//                    choice = "%";
//                    addition.setText("mod");
//                }
            } else if(resultValue.getText().equals("") && firstNumber.getText().equals("")){
                //If user tries to enter a equation sign 
                //Nothing happens 
                return;
            }
            
        }
        
    }
    
    //The Go button activate the calculus
    class Button_Go implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if((firstNumber.getText().equals("") == false && secondNumber.getText().equals("") && firstNumber.getText().equals("-") == false) || (firstNumber.getText().equals("") == false && secondNumber.getText().equals("-"))){
                resultValue.setText((double) (Double.parseDouble(firstNumber.getText())) + "");
            } else if(firstNumber.getText().equals("") && addition.getText().equals("")) {
                resultValue.setText("0.0");
            } else if (firstNumber.getText().equals("-")){
                firstNumber.setText("");
            } else {
                switch(choice){
                case "+":
                    resultValue.setText(Double.parseDouble(firstNumber.getText()) + Double.parseDouble(secondNumber.getText()) + "");
                    break;
                case "-":
                    resultValue.setText(Double.parseDouble(firstNumber.getText()) - Double.parseDouble(secondNumber.getText()) + "");
                    break;
                case "*":
                    resultValue.setText(Double.parseDouble(firstNumber.getText()) * Double.parseDouble(secondNumber.getText()) + "");
                    break;
                case "/":
                    if(Double.parseDouble(secondNumber.getText()) == 0)
                        resultValue.setText("Cannot divide by 0");
                    else
                        resultValue.setText(Double.parseDouble(firstNumber.getText()) / Double.parseDouble(secondNumber.getText()) + "");
                    break;
                case "%":
                    resultValue.setText(Double.parseDouble(firstNumber.getText()) % Double.parseDouble(secondNumber.getText()) + "");
                    break;
                }
            }       
            //Resets everything
            choice = "";
            secondNumber.setText("");
            firstNumber.setText("");
            addition.setText("");
        }
    
    }
    //The negation button either negate or positive the number
    class Button_Negation implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(addition.getText().isEmpty()){
                if(firstNumber.getText().contains("-") == false)
                    firstNumber.setText("-" + firstNumber.getText());
                else
                    firstNumber.setText(firstNumber.getText().substring(1));
            } else {
                if(secondNumber.getText().contains("-") == false)
                    secondNumber.setText("-" + secondNumber.getText());
                else
                    secondNumber.setText(secondNumber.getText().substring(1));
            }
        }
        
    }
    
    //The Dot button allows the user to have number element of Z
    class Button_Dot implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(addition.getText().isEmpty()){
                if(firstNumber.getText().equals(""))
                    firstNumber.setText("0.");
                else if(firstNumber.getText().contains(".") == false)
                    firstNumber.setText(firstNumber.getText() + ".");
            } else {
                if(secondNumber.getText().equals(""))
                    secondNumber.setText("0.");
                else if(secondNumber.getText().contains(".") == false)
                    secondNumber.setText(secondNumber.getText() + ".");
            }
        }        
    }
    //The Clear Button clears only the first or second number
    class Button_Clear implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(addition.getText().equals("")){
                firstNumber.setText("");
            } else 
                secondNumber.setText("");
        }
        
    }
    //The ClearAll Button clears everything
    class Button_ClearAll implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            firstNumber.setText("");
            secondNumber.setText("");
            resultValue.setText("");
            addition.setText("");
        }
        
    }
}
	
	
        
    

