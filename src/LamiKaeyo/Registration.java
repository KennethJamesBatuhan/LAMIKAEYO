package LamiKaeyo;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Registration extends JFrame implements ActionListener {

    // Components of the Form
    private Container frame;
    private JPanel logo;
    private JLabel title;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField userName;
    private JPasswordField password;
    private JTextField address;
    private JTextField email;
    private JTextField contactNumber;
    private JButton register;
    private JButton accountReady;

    // constructor, to initialize the components
    // with default values.
    public Registration()
    {
        setTitle("LAMIKAEYO");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        frame = getContentPane();
        frame.setLayout(null);

        logo = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage logoDisplay = ImageIO.read(new File("C:\\Users\\Kenji\\OneDrive\\Desktop\\ka\\src\\LamiKaeyo\\LogoDesignWithText.png"));
                    g.drawImage(logoDisplay, 25, -30, logoDisplay.getWidth()/5+80, logoDisplay.getHeight()/5+60, null);
                } catch (IOException ignored) {}
            }
        };
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                setVisible(false);
                new LamiKaeyoMain();
            }
        });
        logo.setOpaque(false);
        logo.setPreferredSize(new Dimension(250,200));

        JPanel header = new JPanel();
        header.setBackground(Color.decode("#8B0000"));
        header.setBounds(0, 0, 1250, 50);
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        header.add(logo);
        frame.add(header);

        title = new JLabel("CREATE AN ACCOUNT");
        title.setFont(new Font("Impact", Font.BOLD, 30));
        title.setForeground(Color.decode("#8B0000"));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setSize(400, 30);
        title.setLocation(280, 85);
        frame.add(title);

        firstName = new JTextField("First Name");
        firstName.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        firstName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        firstName.setForeground(Color.GRAY);
        firstName.setBackground(Color.white);
        firstName.setSize(180, 40);
        firstName.setLocation(300, 150);
        frame.add(firstName);

        lastName = new JTextField("Last Name");
        lastName.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        lastName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        lastName.setForeground(Color.GRAY);
        lastName.setBackground(Color.white);
        lastName.setSize(180, 40);
        lastName.setLocation(500, 150);
        frame.add(lastName);

        userName = new JTextField("Username");
        userName.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        userName.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        userName.setSize(380, 40);
        userName.setForeground(Color.GRAY);
        userName.setBackground(Color.white);
        userName.setLocation(300, 210);
        frame.add(userName);

        password = new JPasswordField("Password");
        password.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        password.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        password.setSize(380, 40);
        password.setBackground(Color.white);
        password.setForeground(Color.GRAY);
        password.setLocation(300, 270);
        frame.add(password);

        address = new JTextField("Address");
        address.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        address.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        address.setSize(380, 40);
        address.setBackground(Color.white);
        address.setForeground(Color.GRAY);
        address.setLocation(300, 330);
        frame.add(address);

        email = new JTextField("Email");
        email.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        email.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        email.setSize(380, 40);
        email.setBackground(Color.white);
        email.setForeground(Color.GRAY);
        email.setLocation(300, 390);
        frame.add(email);

        contactNumber = new JTextField("Contact Number");
        contactNumber.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        contactNumber.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        contactNumber.setSize(380, 40);
        contactNumber.setBackground(Color.white);
        contactNumber.setForeground(Color.GRAY);
        contactNumber.setLocation(300, 450);
        frame.add(contactNumber);

        addPlaceholderListener(firstName, "First Name");
        addPlaceholderListener(lastName, "Last Name");
        addPlaceholderListener(userName, "Username");
        addPlaceholderListener(password, "Password");
        addPlaceholderListener(address, "Address");
        addPlaceholderListener(email, "Email");
        addPlaceholderListener(contactNumber, "Contact Number");

        register = new JButton("Register");
        register.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        register.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        register.setBackground(Color.WHITE);
        register.setSize(100, 40);
        register.setLocation(300, 510);
        register.addActionListener(this);
        frame.add(register);

        accountReady = new JButton("Already have an account?");
        accountReady.setOpaque(false);
        accountReady.setContentAreaFilled(false);
        accountReady.setBorderPainted(false);
        accountReady.setFont(new Font("Helvetica Neue", Font.ITALIC, 12));
        accountReady.setForeground(Color.decode("#8B0000"));
        accountReady.setSize(200, 40);
        accountReady.setLocation(450, 510);
        accountReady.addActionListener(this);
        frame.add(accountReady);

        setVisible(true);
    }
    private void addPlaceholderListener(JTextField place, String placeholder) {

        place.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (place.getText().equals(placeholder)) {
                    place.setText("");
                    place.setForeground(Color.BLACK);
                    place.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (place.getText().isEmpty()) {
                    place.setText(placeholder);
                    place.setForeground(Color.GRAY);
                }
            }
        });
    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == register) {
            if (firstName.getText().equals("First Name")){
                firstName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }
            if (lastName.getText().equals("Last Name")) {
                lastName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }
            if (userName.getText().equals("Username")){
                userName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }
            if (toString(password.getPassword()).equals("Password")){
                password.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }
            if (address.getText().equals("Address")){
                address.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }
            if (email.getText().equals("Email")){
                email.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }
            if (contactNumber.getText().equals("Contact Number")){
                contactNumber.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.red),
                        BorderFactory.createEmptyBorder(0,10,0,0)));
            }
            else{
                String data
                    = "First Name : "
                    + firstName.getText() + "\n"
                    + "Last Name : "
                    + lastName.getText() + "\n"
                    + "Username : "
                    + userName.getText() + "\n"
                    + "Password : "
                    + password.getText() + "\n"
                    + "Address : "
                    + address.getText() + "\n"
                    + "Email : "
                    + email.getText() + "\n"
                    + "Contact Number : "
                    + contactNumber.getText() ;
            WriteData(data);
            setVisible(false);
            new LOGIN();
            }
        }
        else if (e.getSource()==accountReady){
            setVisible(false);
            new LOGIN();
        }
    }
    public void WriteData(String write)
    {
        Path path = Paths.get("C:\\Users\\Kenji\\OneDrive\\Desktop\\ka\\src\\LamiKaeyo\\FileData.txt");
        try {
            write = write + "\n" + ReadFile();
            Files.writeString(path, write, StandardCharsets.UTF_8);
        }
        catch (IOException ex) {
        }
    }
    public static String toString(char[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) sb.append(a[i]);
        return sb.toString();
    }
    public String ReadFile()
    {
        String str = "";
        try {
            File myObj = new File("C:\\Users\\Kenji\\OneDrive\\Desktop\\ka\\src\\LamiKaeyo\\FileData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                str = str + "\n" + data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return str;
    }
}