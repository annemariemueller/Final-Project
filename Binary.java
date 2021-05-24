// Represents the Binary panel

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Binary extends JPanel {
   
   private JTextField prompt;
   
   public Binary() {
      this.setBackground(new Color(255, 125, 125));
      addButtons(this);
   }
   
   // sets up the 6 buttons
   public void addButtons(JPanel p) {
      prompt = new JTextField();
      prompt.setFont(new Font("Monospaced", Font.BOLD, 17));
      prompt.setEditable(false);
      prompt.setHorizontalAlignment(JTextField.LEFT);   
      p.add(prompt);
      p.setLayout(new GridLayout(2, 3));
      for(int i = 0; i < 6; i++) {
         if(i==0 || i==1) {
            JButton j = new JButton(String.valueOf(i));
            addListener(j);
            p.add(j);
         }
         if(i==2) {
            JButton j = new JButton("To Hex");
            addListener(j);
            p.add(j);
         }
         if(i==3) {
            JButton j = new JButton("To Decimal");
            addListener(j);
            p.add(j);
         }
         if(i==4) {
            JButton j = new JButton("Backspace");
            addListener(j);
            p.add(j);
         }
         if(i==5) {
            JButton j = new JButton("Clear");
            addListener(j);
            p.add(j);
         }
      }
   }
   
   // sets up the ActionListener for a given button
   public JButton addListener(JButton j) {
      j.setFont(new Font("Monospaced", Font.BOLD, 17));
      j.setBackground(new Color(255, 125, 125));
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
         if(s.equals("To Decimal") || s.equals("To Hex") || s.equals("Backspace") || s.equals("Clear"))
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
      if(isBinary(prompt.getText())) {
         int num =  Integer.parseInt(prompt.getText());
         if(s.equals("To Decimal"))
            prompt.setText(toDecimal(num));
         else if(s.equals("To Hex"))
            prompt.setText(toHex(num));
         else if(s.equals("Backspace"))
            prompt.setText(prompt.getText().substring(0, prompt.getText().length()-1));
         else if(s.equals("Clear"))
            prompt.setText("");
         else
            prompt.setText(prompt.getText() + s);   
      }
      else {
         System.out.println("Input must be a valid binary number.");
         return;
      }
   }
   
   // takes in a binary number and returns it as a decimal number String
     // the sum of the digits times their power of 2
   public String toDecimal(int binary) {
      int decimal = 0;
      int i = 0;
      while(binary > 0) {
         decimal += (binary%10 * Math.pow(2, i++));
         binary /= 10;
      }
      return Integer.toString(decimal);
   }
   
   // takes in a binary number and returns it as a hex number
   public String toHex(int binary) {
      int decimal = Integer.parseInt(toDecimal(binary));
      String hex = Integer.toHexString(decimal);
      return hex.toUpperCase();
   }
   
   // takes in a String and returns whether it is a valid binary number
   public boolean isBinary(String s) {
      if(s.equals(""))
         return true;
      for(int i=0; i<s.length(); i++)
         if(! Character.isDigit(s.charAt(i)))
            return false;
      int num = Integer.parseInt(s);
      if(num < 0)
         return false;
      while(num > 0) {
         if(num%10 == 1 || num%10 == 0)
            num /= 10;
         else
            return false;
      }
      return true;
   }
   
}