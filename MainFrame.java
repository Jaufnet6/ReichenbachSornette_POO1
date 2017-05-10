import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends JFrame{
    
    private static final long serialVersionUID = 1L;
    
    //Area number written
    private JLabel firstNumber = new JLabel();
    private JLabel addition = new JLabel();
    private JLabel secondNumber = new JLabel();
    private JLabel result = new JLabel("Result");
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
    private JButton modulo = new JButton("Mod");
    //Panels
    private JPanel signs = new JPanel();
    private JPanel fields = new JPanel();
    private JPanel buttons = new JPanel();
    private JPanel results = new JPanel();
    private JPanel whole = new JPanel();
    private JPanel clears = new JPanel();
    private JPanel clearsAndGo = new JPanel();
    //Not Visible variables
    private String choice; //Useful for the choice of symbol (sign)
    
    public MainFrame(){
        super("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 800);
        
        //Add the text fields
        fields.setLayout(new GridLayout(1, 3));
        fields.add(firstNumber);
        fields.add(addition);
        fields.add(secondNumber);
        
        //Add both clear buttons to clear Panel
        clears.setLayout(new GridLayout(1, 2));
        clears.add(clear);
        clears.add(clearAll);
        //Add Clears and Go to ClearsAndGo Panel
        clearsAndGo.setLayout(new GridLayout(2, 1));
        clearsAndGo.add(clears);
        clearsAndGo.add(go);
        //Add the results place and go and clears buttons
        results.setLayout(new GridLayout(1, 3));
        results.add(result);
        results.add(resultValue);
        results.add(clearsAndGo);
        //Add all the signs
        signs.setLayout(new GridLayout(1, 5));
        signs.add(add);
        signs.add(substract);
        signs.add(multiply);
        signs.add(divide);
        signs.add(modulo);
        //Add the center with all the numbers
        buttons.setLayout(new GridLayout(4, 3));
        buttons.add(one);
        buttons.add(two);
        buttons.add(three);
        buttons.add(four);
        buttons.add(five);
        buttons.add(six);
        buttons.add(seven);
        buttons.add(eight);
        buttons.add(nine);
        buttons.add(negation);
        buttons.add(zero);
        buttons.add(dot);
        //Puts everything in a panel
        whole.setLayout(new GridLayout(4,1));
        whole.add(fields);
        whole.add(results);
        whole.add(buttons);
        whole.add(signs);
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
        modulo.addActionListener(new Button_Signs());
      
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
                else if(e.getSource().equals(modulo)){
                    choice = "%";
                    addition.setText("mod");
                }
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

