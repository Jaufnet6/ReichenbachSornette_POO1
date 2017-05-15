import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CalculatorFrame extends JFrame{
    
    private static final long serialVersionUID = 1L;
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    //Area number written
    private JLabel firstNumber = new JLabel();
    private JLabel addition = new JLabel();
    private JLabel secondNumber = new JLabel();
    private JLabel resultValue = new JLabel();
    private JButton go = new JButton("Go");
    private JButton clear = new JButton("Clear");
    private JButton clearAll = new JButton("Clear All");
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
//  private JButton modulo = new JButton("Mod");
    //Panels
    private JPanel fields = new JPanel();
    private JPanel buttons = new JPanel();
    private JPanel results = new JPanel(new BorderLayout());
    private JPanel whole = new JPanel();
    private JPanel clears = new JPanel();
    private JPanel clearsAndGo = new JPanel();
    private JPanel operationsAndGo = new JPanel();
    private JPanel allButtons = new JPanel();
    //Not Visible variables
    private String choice; //Useful for the choice of symbol (sign)
    
    private GridBagLayout midPartLayout = new GridBagLayout();
    private GridBagLayout wholeLayout = new GridBagLayout();
    private GridBagLayout clearsLayout = new GridBagLayout();
    private GridBagLayout clearsNGoLayout = new GridBagLayout();

    private GridBagConstraints mpLc = new GridBagConstraints();
    private GridBagConstraints wLc = new GridBagConstraints();
    private GridBagConstraints cLc = new GridBagConstraints();
    private GridBagConstraints cNGc = new GridBagConstraints();


    
    public CalculatorFrame(){
        super("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
		setResizable(false);

        //Add the text fields
        fields.setLayout(new GridLayout(1, 3));
        fields.add(firstNumber);
        fields.add(addition);
        fields.add(secondNumber);
        
        //Regrouping the mid part
        operationsAndGo.setLayout(midPartLayout);
        operationsAndGo.setBackground(Color.BLUE);
        mpLc.gridx = 0;
        mpLc.gridy = 0;
        mpLc.weightx = 1.0;
        mpLc.weighty = 1.0;
        operationsAndGo.add(fields,mpLc);
        mpLc.gridx = 1;
        mpLc.gridy = 0;
        operationsAndGo.add(clearsAndGo,mpLc);
             
        //Add the results place
  //    resultValue.setFont(new Font("Serif", Font.BOLD, 120));
        results.setBackground(Color.RED);
        results.add(resultValue, BorderLayout.LINE_END);

        //Add both clear buttons to clear Panel
        clears.setLayout(clearsLayout);
        cLc.gridx = 0;
        cLc.gridy = 0;
        cLc.weightx = 1.0;
        cLc.weighty = 1.0;
        clears.add(clear,cLc);
        cLc.gridx = 1;
        clears.add(clearAll,cLc);
        //Add Clears and Go to ClearsAndGo Panel
        clearsAndGo.setLayout(clearsNGoLayout);
        cNGc.gridx = 0;
        cNGc.gridy = 0;
        cNGc.weightx = 1.0;
        cNGc.weighty = 1.0;
        clearsAndGo.add(clears);
        cNGc.gridy = 1;
        cNGc.gridwidth = 2;
        cNGc.fill = GridBagConstraints.HORIZONTAL;
        clearsAndGo.add(go);

        //Add the center with all the numbers and signs
        buttons.setLayout(new GridLayout(4,4));
        buttons.add(one);
        buttons.add(two);
        buttons.add(three);
        buttons.add(add);
        buttons.add(four);
        buttons.add(five);
        buttons.add(six);
        buttons.add(substract);
        buttons.add(seven);
        buttons.add(eight);
        buttons.add(nine);
        buttons.add(multiply);
        buttons.add(negation);
        buttons.add(zero);
        buttons.add(dot);
        buttons.add(divide);

        //Puts everything in a panel
        whole.setLayout(wholeLayout);
        wLc.gridx = 0;
        wLc.gridy = 0;
        wLc.weightx = 1.0;
        wLc.weighty = 1.0;
        wLc.fill = GridBagConstraints.BOTH;
        whole.add(results,wLc);
        wLc.gridy = 1;
        whole.add(operationsAndGo,wLc);
        wLc.gridy = 2;
        whole.add(buttons,wLc);
        
        //Add to the main frame
        add(whole);        
        
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
//        modulo.addActionListener(new Button_Signs());
        

      
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
            }
            
        }
        
    }
    
    //The Go button activate the calculus
    class Button_Go implements ActionListener{
        ErrorMessage test = new ErrorMessage();

        public void actionPerformed(ActionEvent e) {
            if(firstNumber.getText().equals("") == false && secondNumber.getText().equals("") ){
                resultValue.setText((double) (Double.parseDouble(firstNumber.getText())) + "");
            } else if(firstNumber.getText().equals("") && addition.getText().equals("")) {
                resultValue.setText("0.0");
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

