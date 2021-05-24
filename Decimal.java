// Represents the Decimal panel

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Decimal extends JPanel {
   
   private JTextField prompt;
   
   public Decimal() {
      addButtons(this);
   }
   
   // sets up the 14 buttons
   public void addButtons(JPanel p) {
      prompt = new JTextField();
      prompt.setFont(new Font("Monospaced", Font.BOLD, 16));
      prompt.setEditable(false);
      prompt.setHorizontalAlignment(JTextField.LEFT); 
      p.add(prompt);
      p.setLayout(new GridLayout(3, 5));
      for(int i = 0; i < 14; i++) {
        if(i>=0 && i<=9) {
           JButton j = new JButton(String.valueOf(i));
           addListener(j);
           p.add(j);
        }
        if(i==10) {
           JButton j = new JButton("To Binary");
           addListener(j);
           p.add(j);
        }
        if(i==11) {
           JButton j = new JButton("To Hex");
           addListener(j);
           p.add(j);
        }
        if(i==12) {
            JButton j = new JButton("Backspace");
            addListener(j);
            p.add(j);
         }
         if(i==13) {
            JButton j = new JButton("Clear");
            addListener(j);
            p.add(j);
         }
      }
   }
   
   // sets up the ActionListener for a given button
   public JButton addListener(JButton j) {
      j.setFont(new Font("Monospaced", Font.BOLD, 16));
      j.setBackground(new Color(255, 255, 175));
      j.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            chooseButton(source);
         }
      });
      return j;
   }
   
   // selects one of the buttons and executes it in the JTextField
   public void chooseButton(JButton j) {  
      String s = new String(j.getText());
      // no input yet
      if(prompt.getText().equals("")) {
         if(s.equals("To Binary") || s.equals("To Hex") || s.equals("Backspace") || s.equals("Clear"))
            return;
         prompt.setText(s);
         return;
      }
      // backspace or clear
      if(s.equals("Backspace")) {
         prompt.setText(prompt.getText().substring(0, prompt.getText().length()-1));
         return;
      }
      if(s.equals("Clear")) {
         prompt.setText("");
         return;
      }
      // some input already
      if(isDecimal(prompt.getText())) {
         int num =  Integer.parseInt(prompt.getText());
         if(s.equals("To Binary"))
            prompt.setText(toBinary(num));
         else if(s.equals("To Hex"))
            prompt.setText(toHex(num));
         else
            prompt.setText(prompt.getText() + s);
      }
      else {
         System.out.println("Input must be a valid integer.");
         return;
      }
   }
   
   // takes in a decimal number and returns it as a binary number String
   public String toBinary(int decimal) {
      String binary = Integer.toBinaryString(decimal);
      return binary;
   }
   
   // takes in a decimal number and returns it as a hex number String
   public String toHex(int decimal) {
      String hex = Integer.toHexString(decimal);
      return hex.toUpperCase();
   }
   
   // takes in a String and returns whether it is a valid decimal number
    public boolean isDecimal(String s) {
      if(s.equals(""))
         return true;
      for(int i=0; i<s.length(); i++)
         if(! Character.isDigit(s.charAt(i)))
            return false;
      int num = Integer.parseInt(s);
      if(num < 0)
         return false;
      return true;
   }

}