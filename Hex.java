// Represents the Hex panel

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hex extends JPanel {
   
   private JTextField prompt;
   
   public Hex() {
      addButtons(this);
   }
   
   // sets up the 20 buttons
   public void addButtons(JPanel p) {
      prompt = new JTextField();
      prompt.setFont(new Font("Monospaced", Font.BOLD, 12));
      prompt.setEditable(false);
      prompt.setHorizontalAlignment(JTextField.LEFT); 
      p.add(prompt);
      p.setLayout(new GridLayout(3, 7));
      for(int i = 0; i < 20; i++) {
        if(i>=0 && i<=9) {
           JButton j = new JButton(String.valueOf(i));
           addListener(j);
           p.add(j);
        }
        if(i>=10 && i<=15) {
           JButton j = new JButton(Integer.toHexString(i).toUpperCase());
           addListener(j);
           p.add(j);
        }
        if(i==16) {
           JButton j = new JButton("To Binary");
           addListener(j);
           p.add(j);
        }
        if(i==17) {
           JButton j = new JButton("To Decimal");
           addListener(j);
           p.add(j);  
        }
        if(i==18) {
            JButton j = new JButton("Backspace");
            addListener(j);
            p.add(j);
         }
         if(i==19) {
            JButton j = new JButton("Clear");
            addListener(j);
            p.add(j);
         }
      }
   }
   
   // sets up the ActionListener for a given button
   public JButton addListener(JButton j) {
      j.setFont(new Font("Monospaced", Font.BOLD, 12));
      j.setBackground(new Color(50, 200, 200));
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
         if(s.equals("To Binary") || s.equals("To Decimal") || s.equals("Backspace") || s.equals("Clear"))
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
      String num = prompt.getText();
      if(isHex(num)) {
         if(s.equals("To Binary"))
            prompt.setText(toBinary(num));
         else if(s.equals("To Decimal"))
            prompt.setText(toDecimal(num));
         else if(s.equals("Backspace"))
            prompt.setText(prompt.getText().substring(0, prompt.getText().length()-1));
         else if(s.equals("Clear"))
            prompt.setText("");
         else
            prompt.setText(prompt.getText() + s);
      }
      else {
         System.out.println("Input must be a valid hex number.");
         return;
      }
   }
   
   // takes in a hex number an returns it as a decimal number String
   public String toDecimal(String hex) {
      int decimal = Integer.parseInt(hex, 16);
      return Integer.toString(decimal);
   }
   
   // takes in a hex number and returns it as a binary number String
   public String toBinary(String hex) {
      int decimal = Integer.parseInt(toDecimal(hex));
      String binary = Integer.toBinaryString(decimal);
      return binary;
   }
   
   // takes in a String and returns whether it is a valid hex number
   public boolean isHex(String s) {
      if(s.equals(""))
         return true;
      try {
         int decimal = Integer.parseInt(s, 16);
         Integer.toString(decimal);
      }
      catch(NumberFormatException e) {
         return false;
      }
      return true;
   }
   
}