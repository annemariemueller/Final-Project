// Annemarie Mueller
// AP CSA Final Project

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

// Hex numbers are Strings, Binary numbers are ints, Decimal numbers are ints
public class Calculator extends JFrame {

   private CardLayout layout;
   private JPanel binary; JPanel decimal; JPanel hex;
   private JPanel cards;
   private JComboBox cb;
   
   public Calculator() {
      super("Number Conversions Calculator");
      setUp();
          
      binary = new Binary();
      cards.add(binary, "BINARY");
      decimal = new Decimal();
      cards.add(decimal, "DECIMAL");
      hex = new Hex();
      cards.add(hex, "HEX"); 
      
      String items[] = {"BINARY", "DECIMAL", "HEX"};
      cb = new JComboBox(items);
      cb.setEditable(false);
      cb.addItemListener(createListener());
            
      add(cb, BorderLayout.PAGE_START);
      add(cards, BorderLayout.CENTER);
      setVisible(true);
   }
   
   // general setup for the JFrame
   public void setUp() {
      layout = new CardLayout();
      cards = new JPanel(layout);
      setBounds(100, 150, 800, 400);
      setResizable(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   // creates an ItemListener for the JComboBox
   private ItemListener createListener() {
     return new ItemListener() {
       @Override 
       public void itemStateChanged(ItemEvent e) {
         JComboBox box = (JComboBox) e.getSource();
         String whichTab = box.getSelectedItem().toString();
         layout.show(cards, whichTab);
       }
     };
   }
   
   // displays the calculator
   public static void main(String[] args) {
      Calculator c = new Calculator();
   }
}