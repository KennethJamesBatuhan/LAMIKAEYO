package LamiKaeyo;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LamiKaeyoMain implements ActionListener{
        JButton signIn, signUp;
        JFrame frame;
    public static void main (String[] a){
            // Frame
            LamiKaeyoMain lamiKaeyoMain= new LamiKaeyoMain();
            lamiKaeyoMain.frame = new JFrame();
            lamiKaeyoMain.frame.setSize(1000,700);
            lamiKaeyoMain.frame.setLocationRelativeTo(null);
            lamiKaeyoMain.frame.setTitle("LAMIKAEYO");
            lamiKaeyoMain.frame.setResizable(false);
            lamiKaeyoMain.frame.setLayout(null);
            lamiKaeyoMain.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Button for Sign in
            lamiKaeyoMain.signIn = new JButton();
            lamiKaeyoMain.signIn.setText("Sign In");
            lamiKaeyoMain.signIn.setForeground(Color.WHITE);
            lamiKaeyoMain.signIn.setOpaque(false);
            lamiKaeyoMain.signIn.setContentAreaFilled(false);
            lamiKaeyoMain.signIn.setBorderPainted(false);
            lamiKaeyoMain.signIn.addActionListener(lamiKaeyoMain);
            lamiKaeyoMain.signIn.setBounds(700, 15, 80, 20);
            lamiKaeyoMain.frame.add(lamiKaeyoMain.signIn);

            //Line between the Sign In and Sign Up
            JTextField Line = new JTextField();
            Line.setBackground(Color.WHITE);
            Line.setEditable(false);
            Line.setBounds(800, 15, 2, 20);
            lamiKaeyoMain.frame.add(Line);

            // Button for Sign up
            lamiKaeyoMain.signUp = new JButton();
            lamiKaeyoMain.signUp.setText("Sign Up");
            lamiKaeyoMain.signUp.setForeground(Color.WHITE);
            lamiKaeyoMain.signUp.setOpaque(false);
            lamiKaeyoMain.signUp.setContentAreaFilled(false);
            lamiKaeyoMain.signUp.setBorderPainted(false);
            lamiKaeyoMain.signUp.addActionListener(lamiKaeyoMain);
            lamiKaeyoMain.signUp.setBounds(820, 15, 80, 20);
            lamiKaeyoMain.frame.add(lamiKaeyoMain.signUp);

            //header
            JPanel header = new JPanel();
            header.setBackground(Color.decode("#8B0000"));
            header.setBounds(0, 0, 1250, 50);
            lamiKaeyoMain.frame.add(header);

            //Logo Design
            JLabel logoDesign = new JLabel(){
                    @Override
                    public void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            try {
                                    BufferedImage logoDisplay = ImageIO.read(new File("resources\\LogoDesignWithText.png"));
                                    g.drawImage(logoDisplay, 0, 0, logoDisplay.getWidth(), logoDisplay.getHeight(), null);
                            } catch (IOException ignored) {}
                    }
            };
            logoDesign.setBounds(330, 30, 300, 195);
            lamiKaeyoMain.frame.add(logoDesign);

            //PINOY STYLE PICTURE
            JLabel pinoyStyle = new JLabel(){
                    @Override
                    public void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            try {
                                    BufferedImage logoDisplay = ImageIO.read(new File("resources\\pinoyStyle.png"));
                                    g.drawImage(logoDisplay, 0, 0, logoDisplay.getWidth()-100 , logoDisplay.getHeight()-50, null);
                            } catch (IOException ignored) {}
                    }
            };
            pinoyStyle.setBorder(BorderFactory.createLineBorder(Color.decode("#8B0000"), 5));
            pinoyStyle.setBounds(150, 200, 300, 150);
            lamiKaeyoMain.frame.add(pinoyStyle);

            //KOREAN STYLE PICTURE
            JLabel koreanStyle = new JLabel(){
                    @Override
                    public void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            try {
                                    BufferedImage logoDisplay = ImageIO.read(new File("resources\\koreanStyle.png"));
                                    g.drawImage(logoDisplay, 0, -20, logoDisplay.getWidth()/5+100 , logoDisplay.getHeight()/5+55, null);
                            } catch (IOException ignored) {}
                    }
            };
            koreanStyle.setBorder(BorderFactory.createLineBorder(Color.decode("#8B0000"), 5));
            koreanStyle.setBounds(500, 200, 300, 150);
            lamiKaeyoMain.frame.add(koreanStyle);

            //JAPANESE STYLE PICTURE
            JLabel japaneseSTyle = new JLabel(){
                    @Override
                    public void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            try {
                                    BufferedImage logoDisplay = ImageIO.read(new File("resources\\japaneseStyle.png"));
                                    g.drawImage(logoDisplay, 0, -20, logoDisplay.getWidth()/5+100 , logoDisplay.getHeight()/5+55, null);
                            } catch (IOException ignored) {}
                    }
            };
            japaneseSTyle.setBorder(BorderFactory.createLineBorder(Color.decode("#8B0000"), 5));
            japaneseSTyle.setBounds(150, 375, 300, 150);
            lamiKaeyoMain.frame.add(japaneseSTyle);

            //FRENCH STYLE PICTURE
            JLabel frenchStyle = new JLabel(){
                    @Override
                    public void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            try {
                                    BufferedImage logoDisplay = ImageIO.read(new File("resources\\frenchStyle.png"));
                                    g.drawImage(logoDisplay, 0, -20, logoDisplay.getWidth()/5+100 , logoDisplay.getHeight()/5+55, null);
                            } catch (IOException ignored) {}
                    }
            };
            frenchStyle.setBorder(BorderFactory.createLineBorder(Color.decode("#8B0000"), 5));
            frenchStyle.setBounds(500, 375, 300, 150);
            lamiKaeyoMain.frame.add(frenchStyle);

            //TEXT FIELD FOR NOTE
            JTextField text = new JTextField();
            text.setText("Note: Sign in to see recipe.");
            text.setFont(new Font("Arial", Font.ITALIC, 12));
            text.setBorder(null);
            text.setBackground(null);
            text.setEditable(false);
            text.setBounds(400, 580, 150, 20);
            lamiKaeyoMain.frame.add(text);

            lamiKaeyoMain.frame.setVisible(true);
    }
    //ACTION LISTENER FOR SIGN IN AND SIGN UP BUTTON
    @Override
        public void actionPerformed(ActionEvent e){
        if(e.getSource()==signIn){
                frame.setVisible(false);
                new LOGIN();
        }
        else if (e.getSource()==signUp){
                frame.setVisible(false);
                new Registration();
        }
        else{
                frame.setVisible(true);
        }
    }
}