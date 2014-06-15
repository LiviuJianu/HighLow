package highlow;

/**
 *
 * @author Liviu Jianu <liviu.jianu@gmail.com>
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This program is a simple card game.  The user sees a card and
 * tries to predict whether the next card will be higher or 
 * lower.  Aces are the lowest-valued cards.  If the user makes
 * three correct predictions, the user wins.  If not, the
 * user loses.
 * 
 * This class defines a panel, but it also contains a main()
 * routine that makes it possible to run the program as a
 * stand-alone application.  In also contains a public nested
 * class, HighLowGUI.Applet that can be used as an applet version
 * of the program.  When used as an applet, the size of
 * the applet should be 376 pixels wide and about 220 pixels high.
 * The height is larger than strictly necessary to allow for 
 * variations in the size of buttons from one platform to another.
 * 
 * This program depends on several additional source code files:
 * Card.java, Hand.java, and Deck.java.
 */
public class HighLowGUI extends JPanel {
   
   /**
    * The main routine simply opens a window that shows a HighLowGUIl.
     * @param args
    */
    
    private Image cardImages;
    
   public static void main(String[] args) {
       initGUI();
   }
   
    /**
     * Graphical init method
     * @return 
     */
    public static HighLowGUI initGUI() {
      JFrame window = new JFrame("Mare Mica");
      HighLowGUI content = new HighLowGUI();
      window.setContentPane(content);
      window.pack();  // Set size of window to preferred size of its contents.
      window.setResizable(false);  // User can't change the window's size.
      window.setLocation(100,100);
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setVisible(true);
        return null;
      }
   
   /**
    * The public static class HighLowGUI$Applet represents this program
    * as an applet.  The applet's init() method simply sets the content 
    * pane of the applet to be a HighLowGUI.  To use the applet on
    * a web page, use code="HighLowGUI$Applet.class" as the name of
    * the class.
    */
   public static class Applet extends JApplet {
      @Override
      public void init() {
         setContentPane( initGUI() );
      }
   }
   
   
   
   /**
    * The constructor lays out the panel.  A CardPanel occupies the CENTER 
    * position of the panel (where CardPanel is a subclass of JPanel that is 
    * defined below).  On the bottom is a panel that holds three buttons.  
    * The CardPanel listens for ActionEvents from the buttons and does all 
    * the real work of the program.
    */
   public HighLowGUI() {
      
        try {
            cardImages = ImageIO.read(new File("build\\classes\\images\\cards.png"));
        } catch (IOException ex) {
            Logger.getLogger(HighLowGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
      setBackground( new Color(130,50,40) );
      
      setLayout( new BorderLayout(3,3) );
      
      CardPanel board = new CardPanel();
      add(board, BorderLayout.CENTER);
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.setBackground( new Color(220,200,180) );
      add(buttonPanel, BorderLayout.SOUTH);
      
      JButton higher = new JButton( "Mare" );
      higher.addActionListener(board);
      buttonPanel.add(higher);
      
      JButton lower = new JButton( "Mica" );
      lower.addActionListener(board);
      buttonPanel.add(lower);
      
      JButton newGame = new JButton( "Joc nou" );
      newGame.addActionListener(board);
      buttonPanel.add(newGame);
      
      setBorder(BorderFactory.createLineBorder( new Color(130,50,40), 3) );
      
   }   
   
   
   /**
    * A nested class that displays the cards and does all the work
    * of keeping track of the state and responding to user events.
    */
   private class CardPanel extends JPanel implements ActionListener {
      
      Deck deck;       // A deck of cards to be used in the game.
      Hand hand;       // The cards that have been dealt.
      String message;  // A message drawn on the canvas, which changes
                       //    to reflect the state of the game.
      
      boolean gameInProgress;  // Set to true when a game begins and to false
                               //   when the game ends.
      
      Font bigFont;      // Font that will be used to display the message.
      Font smallFont;    // Font that will be used to draw the cards.
      
      /**
       * Constructor creates fonts, sets the foreground and background
       * colors and starts the first game.  It also sets a "preferred
       * size" for the panel.  This size is respected when the program
       * is run as an application, since the pack() method is used to
       * set the size of the window.
       */
      CardPanel() {
         setBackground( new Color(0,120,0) );
         setForeground( Color.GREEN );
         smallFont = new Font("SansSerif", Font.PLAIN, 12);
         bigFont = new Font("Serif", Font.BOLD, 14);
         setPreferredSize( new Dimension(370, 170));
         doNewGame();
      } // end constructor
      
      /**
       * Respond when the user clicks on a button by calling the appropriate 
       * method.  Note that the buttons are created and listening is set
       * up in the constructor of the HighLowPanel class.
       */
      @Override
      public void actionPerformed(ActionEvent evt) {
         String command = evt.getActionCommand();
          switch (command) {
              case "Mare":
                  doHigher();
                  break;
              case "Mica":
                  doLower();
                  break;
              case "Joc nou":
                  doNewGame();
                  break;
          }
      } // end actionPerformed()
      
      /**
       * Called by actionPerformmed() when user clicks "Higher" button.
       * Check the user's prediction.  Game ends if user guessed
       * wrong or if the user has made three correct predictions.
       */
      void doHigher() {
         if (gameInProgress == false) {
            // If the game has ended, it was an error to click "Higher",
            // So set up an error message and abort processing.
            message = "Apasa \"Joc nou\" pentru a incepe!";
            repaint();
            return;
         }
         hand.addCard( deck.dealCard() );     // Deal a card to the hand.
         int cardCt = hand.getCardCount();
         Card thisCard = hand.getCard( cardCt - 1 );  // Card just dealt.
         Card prevCard = hand.getCard( cardCt - 2 );  // The previous card.
         if ( thisCard.getValue() < prevCard.getValue() ) {
            gameInProgress = false;
            message = "Ce pacat! Ai pierdut.";
         }
         else if ( thisCard.getValue() == prevCard.getValue() ) {
            gameInProgress = false;
            message = "Ce pacat! Cartile sunt egale.";
         }
         else if ( cardCt == 4) {
            gameInProgress = false;
            message = "Ai castigat!  Ai ghicit de trei ori consecutiv.";
         }
         else {
            message = "Ai ghicit!  Incearca pentru " + cardCt + ".";
         }
         repaint();
      } // end doHigher()
      
      /**
       * Called by actionPerformmed() when user clicks "Lower" button.
       * Check the user's prediction.  Game ends if user guessed
       * wrong or if the user has made three correct predictions.
       */
      void doLower() {
         if (gameInProgress == false) {
               // If the game has ended, it was an error to click "Lower",
               // So set up an error message and abort processing.
            message = "Apasa \"Joc nou\" pentru a incepe!";
            repaint();
            return;
         }
         hand.addCard( deck.dealCard() );     // Deal a card to the hand.
         int cardCt = hand.getCardCount();
         Card thisCard = hand.getCard( cardCt - 1 );  // Card just dealt.
         Card prevCard = hand.getCard( cardCt - 2 );  // The previous card.
         if ( thisCard.getValue() > prevCard.getValue() ) {
            gameInProgress = false;
            message = "Ce pacat! Ai pierdut.";
         }
         else if ( thisCard.getValue() == prevCard.getValue() ) {
            gameInProgress = false;
            message = "Ce pacat! Cartile sunt egale.";
         }
         else if ( cardCt == 4) {
            gameInProgress = false;
            message = "Ai castigat!  Ai ghicit de trei ori consecutiv.";
         }
         else {
            message = "Ai ghicit!  Incearca pentru " + cardCt + ".";
         }
         repaint();
      } // end doLower()
      
      /**
       * Called by the constructor, and called by actionPerformed() if
       * the use clicks the "New Game" button.  Start a new game.
       */
      void doNewGame() {
         if (gameInProgress) {
               // If the current game is not over, it is an error to try
               // to start a new game.
            message = "Inca nu ai terminat jocul!";
            repaint();
            return;
         }
         deck = new Deck();   // Create the deck and hand to use for this game.
         hand = new Hand();
         deck.shuffle();
         hand.addCard( deck.dealCard() );  // Deal the first card into the hand.
         message = "Urmeaza o carte mai mare sau mai mica?";
         gameInProgress = true;
         repaint();
      } // end doNewGame()
      
      /**
       * This method draws the message at the bottom of the
       * panel, and it draws all of the dealt cards spread out
       * across the canvas.  If the game is in progress, an extra
       * card is drawn face down representing the card to be dealt next.
       */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setFont(bigFont);
         g.drawString(message,10,165);
         g.setFont(smallFont);
         int cardCt = hand.getCardCount();
         for (int i = 0; i < cardCt; i++)
            drawCard(g, hand.getCard(i), 10 + i * 90, 10);
         if (gameInProgress)
            drawCard(g, null, 10 + cardCt * 90, 10);
      } // end paintComponent()
      
      /**
       * Draws a card as a 80 by 100 rectangle with upper left corner at (x,y).
       * The card is drawn in the graphics context g.  If card is null, then
       * a face-down card is drawn.  (The cards are rather primitive!)
       */
      void drawCard(Graphics g, Card card, int x, int y) {
          int cx;
          int cy = 0;
         if (card == null) {  
             cx = 4 * 123;
             cy = 2 * 79;
         }
         else {
             cx = (card.getValue()-1)*79;
             switch(card.getSuit()) {
                 case Card.CLUBS:
                     cy = 0;
                     break;
                 case Card.DIAMONDS:
                     cy = 123;
                     break;
                 case Card.HEARTS:
                     cy = 2 * 123;
                     break;
                 case Card.SPADES:
                     cy = 3 * 123;
             }
             g.drawImage(cardImages, x, y, x+79, y+123, cx, cy, cx+79, cy+123, this);
         }
      } // end drawCard()
      
      
   } // end nested class CardPanel
   
   
} // end class HighLowGUI
