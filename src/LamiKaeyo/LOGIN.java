package LamiKaeyo;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class LOGIN extends JFrame {
    JPanel backgroundPanel, background, loginPanel;
    JButton loginButtons;

    private static JLabel errorMessageLabel;
    public LOGIN(){
        // Create a JFrame
        this.setTitle("LAMIKAEYO");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        LOGIN();
        this.add(backgroundPanel);
        this.add(loginPanel);
    }

    public void backgroundPanel(){
        background = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage backgroundPicture = ImageIO.read(new File("C:\\Users\\Kenji\\OneDrive\\Desktop\\ka\\src\\LamiKaeyo\\LOGINPIC.png"));
                    g.drawImage(backgroundPicture, 0, 0, backgroundPicture.getWidth() , backgroundPicture.getHeight(), null);
                } catch (IOException ignored) {}
            }
            };
        background.setBounds(0, 0, 500, 700);
        }

    public void LOGIN() {

        // Create a JPanel with a background
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0,0,500,700);
        backgroundPanel();
        backgroundPanel.add(background);

        // Create a JPanel with a maroon background
        loginPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage logoDisplay = ImageIO.read(new File("C:\\Users\\Kenji\\OneDrive\\Desktop\\ka\\src\\LamiKaeyo\\kaeyo.png"));
                    g.drawImage(logoDisplay, 160, 80, logoDisplay.getWidth() , logoDisplay.getHeight(), null);
                } catch (IOException ignored) {}
            }
        };
        loginPanel.setBackground(Color.decode("#8B0000"));
        loginPanel.setLayout(null);
        loginPanel.setBounds(500, 0, 500, 700);

        // Create a TitleLabel -LAMI
        JLabel titleLabel = new JLabel();
        titleLabel.setText("LAMI");
        titleLabel.setForeground(new Color(255, 140, 0)); // RGB values for a darker orange
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setBounds((loginPanel.getWidth() - 210) / 2, 150, 100, 80);
        loginPanel.add(titleLabel);

        // Create a TitleLabel2 - KAEYO
        JLabel titleLabel2 = new JLabel();
        titleLabel2.setText("KAEYO");
        titleLabel2.setForeground(Color.white);
        titleLabel2.setFont(new Font("Arial", Font.PLAIN, 35));
        titleLabel2.setBounds((loginPanel.getWidth() - 35) / 2, 150, 150, 80);
        loginPanel.add(titleLabel2);

        // Login components
        JTextField userIDField = new JTextField("Username");
        userIDField.setForeground(Color.GRAY);
        userIDField.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));
        userIDField.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        userIDField.setBounds((loginPanel.getWidth() - 285) / 2, 250, 275, 40);

        JPasswordField userPasswordField = new JPasswordField("Password");
        userPasswordField.setForeground(Color.GRAY);
        userPasswordField.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));
        userPasswordField.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        userPasswordField.setBounds((loginPanel.getWidth() - 285) / 2, 300, 275, 40);

        // Add focus listeners for placeholder effect
        addPlaceholderListener(userIDField, "Username");
        addPlaceholderListener(userPasswordField, "Password");

        JButton loginButton = new JButton("LOG IN");
        loginButton.setFont(new Font("Helvetica Neue", Font.BOLD, 14));
        loginButton.setBounds((loginPanel.getWidth() - 285) / 2, 360, 115, 35);
        loginButton.setBackground(Color.white);
        loginButton.setForeground(Color.decode("#8B0000"));
        loginButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        JButton createAccount = new JButton("Create an account");
        createAccount.setOpaque(false);
        createAccount.setContentAreaFilled(false);
        createAccount.setBorderPainted(false);
        createAccount.setFont(new Font("Helvetica Neue", Font.ITALIC, 13));
        createAccount.setForeground(Color.white);
        createAccount.setBounds((loginPanel.getWidth() - 285) / 2 + 120, 360, 150, 35);

        // Add components to loginPanel
        loginPanel.add(userIDField);
        loginPanel.add(userPasswordField);
        loginPanel.add(loginButton);
        loginPanel.add(createAccount);

        // Create an error message label
        errorMessageLabel = new JLabel("Invalid Username or Password");
        errorMessageLabel.setForeground(Color.red);
        errorMessageLabel.setBounds((loginPanel.getWidth() - 285) / 2, 230, 275, 25);
        errorMessageLabel.setVisible(false);
        // Add error message label to mPanel
        loginPanel.add(errorMessageLabel);

        // Add action listener to the login button

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if( checkAccount(ReadFile(), userIDField.getText(), Registration.toString(userPasswordField.getPassword()))){
                        setVisible(false);
                        new Home();
                    }
                    else {
                        errorMessageLabel.setVisible(true);
                    }
                }
            });

        // Add action listener to the create button
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic for the back button (e.g., go back to the previous screen)
                if(e.getSource()==createAccount){
                    setVisible(false);
                    new Registration();
                }
            }
        });
    }
    private static void addPlaceholderListener(JTextField textField, String placeholder) {
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
    public String ReadFile()
    {
        String str = "";
        try {
            File myObj = new File("C:\\Users\\Kenji\\OneDrive\\Desktop\\ka\\src\\LamiKaeyo\\FileData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                str += data + "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return str;
    }
//    public boolean checkAccount(String data, String userNameEntered, String passwordEntered){
//        String userName, password;
//        String[] datas = data.split("\n");
//        for(int i = 0; i < datas.length; i++){
//            if(!datas[i].isEmpty()) {
//                if(datas[i].length() > 10) {
//                    userName = datas[i].substring(11);
//                    password = datas[i + 1].substring(11);
//                    if (userName.equals(userNameEntered) && password.equals(passwordEntered)) {
//                        //                    String filename = "C:\\Users\\Kenji\\OneDrive\\Desktop\\ka\\src\\LamiKaeyo\\" + userName + "_" + password + ".txt";
//                        //                    try {
//                        //                        Writer write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
//                        //                    }catch (IOException ex){
//                        //
//                        //                    }
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
public boolean checkAccount(String data, String userNameEntered, String passwordEntered){
    String userName, password;
    String[] datas = data.split("\n");
    for(int i = 0; i < datas.length; i++){
        if(i%8==2){
            userName = datas[i].substring(11);
            if(userNameEntered.equals(userName)){
                password = datas[i+1].substring(11);
                if(passwordEntered.equals(password)) return true;
            }
        }
    }
    return false;
}

}

