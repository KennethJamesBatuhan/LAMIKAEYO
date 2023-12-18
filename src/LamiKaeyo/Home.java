package LamiKaeyo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.border.Border;
import java.util.concurrent.atomic.AtomicBoolean;

public class Home extends JFrame implements ActionListener {
    private static JPanel contentPanel;
    private static JPanel homePage;
    private static JPanel pinoyPage;
    private static JPanel koreanPage;
    private static JPanel japanesePage;
    private static JPanel frenchPage;
    private static JButton logoutButton;
    private static JButton menuButton;


    public Home() {
        JFrame frame = new JFrame("LAMIKAEYO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());


        JLabel homeLabel = new JLabel("Home | ");
        homeLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        homeLabel.setBounds(20,10,100,30);
        homeLabel.setForeground(Color.WHITE);
        frame.add(homeLabel);


        JLabel pinoyLabel = new JLabel("Pinoy Style");
        pinoyLabel.setForeground(Color.WHITE);
        pinoyLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        pinoyLabel.setBounds(100,10,116,30);
        pinoyLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        frame.add(pinoyLabel);

        JLabel koreanLabel = new JLabel("Korean Style");
        koreanLabel.setForeground(Color.WHITE);
        koreanLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        koreanLabel.setBounds(230,10,132,30);
        koreanLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        frame.add(koreanLabel);

        JLabel japaneseLabel = new JLabel("Japanese Style");
        japaneseLabel.setForeground(Color.WHITE);
        japaneseLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        japaneseLabel.setBounds(375,10,154,30);
        japaneseLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        frame.add(japaneseLabel);

        JLabel frenchLabel = new JLabel("French Style");
        frenchLabel.setForeground(Color.WHITE);
        frenchLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        frenchLabel.setBounds(540,10,129,30);
        frenchLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        frame.add(frenchLabel);

        JPanel redBar = new JPanel();
        redBar.setBackground(Color.decode("#8B0000"));
        redBar.setPreferredSize(new Dimension(frame.getWidth(), 50));

        JPanel spacePanel = new JPanel();
        spacePanel.setOpaque(false);
        spacePanel.setPreferredSize(new Dimension(900, 30));
        redBar.add(spacePanel);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources\\menu.png"));
            int scaledWidth = 30;
            int scaledHeight = 30;
            Image scaledImg = img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            ImageIcon menuIcon = new ImageIcon(scaledImg);

            menuButton = new JButton(menuIcon);
            menuButton.setOpaque(false);
            menuButton.setContentAreaFilled(false);
            menuButton.setBorderPainted(false);
            frame.add(menuButton);


        AtomicBoolean isMenuOpen = new AtomicBoolean(false);
        JPanel[] slidingMenu = { null };


        menuButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           if (!isMenuOpen.get()) {
            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

            JPanel profilePanel = new JPanel(new GridLayout(5, 1));
            JPanel contactPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


            String userName = "";
            String fullName = "Alexes Zerna Cena";
            String address = "Quit Pardo Cebu";
            String email = "alexes@gmail.com";
            String contactNumber = "0912340383742";

            JLabel greetingLabel = new JLabel("Hello! \n" + userName);
            JLabel nameLabel = new JLabel(fullName);
            JLabel addressLabel = new JLabel(address);
            JLabel emailLabel = new JLabel(email);
            JLabel contactLabel = new JLabel(contactNumber);

            profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            contactPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
            addressLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
            emailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
            contactLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));


           ImageIcon userIcon = new ImageIcon("resources\\profile.png");
           int width = 50;
           int height = 50;
           Image img = userIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
           ImageIcon resizedUserIcon = new ImageIcon(img);


           JLabel userImageLabel = new JLabel(resizedUserIcon);
           JPanel userPanel = new JPanel(new BorderLayout());
           userPanel.add(userImageLabel, BorderLayout.WEST);
           userPanel.add(greetingLabel, BorderLayout.CENTER);

           profilePanel.add(userPanel);
           profilePanel.add(nameLabel);
           profilePanel.add(addressLabel);
           profilePanel.add(emailLabel);
           profilePanel.add(contactLabel);

        JLabel contactInfoLabel = new JLabel("<html>Contact Us:<br>lamikaayo@gmail.com<br>Tel#: 32-0942-123<br>Cel#: 09079142208</html>");

            contactPanel.add(contactInfoLabel);
            menuPanel.add(profilePanel);
            menuPanel.add(contactPanel);


            JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
             logoutButton = new JButton("Logout");
            logoutButton.setBackground(Color.decode("#8B0000"));
            logoutButton.setForeground(Color.WHITE);
//          logoutButton.addActionListener(this);
            logoutPanel.add(logoutButton);
            menuPanel.add(Box.createVerticalGlue());
            menuPanel.add(logoutPanel);

               logoutButton.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if(e.getSource()==logoutButton){
                           frame.setVisible(false);
                           new LamiKaeyoMain();
                       }
                   }
               });

            slidingMenu[0] = new JPanel(new BorderLayout());
            slidingMenu[0].setPreferredSize(new Dimension(frame.getWidth() * 1 / 7, frame.getHeight()));

            Border redBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 0, 0, Color.decode("#8B0000")),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            );
            slidingMenu[0].setBorder(redBorder);

            slidingMenu[0].add(menuPanel, BorderLayout.CENTER);

            frame.add(slidingMenu[0], BorderLayout.EAST);
            ((CardLayout) contentPanel.getLayout()).show(contentPanel, "Home");

            isMenuOpen.set(true);
                } else {

            if (slidingMenu[0] != null) {
                frame.remove(slidingMenu[0]);
                isMenuOpen.set(false);
            }
                }

        frame.revalidate();
        frame.repaint();
            }
        });


            redBar.add(menuButton);
        } catch (Exception e) {
            e.printStackTrace();
        }

        contentPanel = new JPanel(new CardLayout());

        homePage = createHomePage();

        pinoyPage = createStylePanel("Pinoy", new String[]{
                "resources\\adobo.png",
                "resources\\caldereta.png",
                "resources\\tinola.png",
                "resources\\sinigang.png"
        }, new String[]{"Adobo Flakes", "Beef Caldereta", "Chicken Tinola", "Pork Sinigang with Gabi"});

        koreanPage = createStylePanel("Korean", new String[]{
                "resources\\sotteok.png",
                "resources\\kimbap.png",
                "resources\\chickensoup.png",
                "resources\\donkatsu.png"
        }, new String[]{"Sotteok", "Mini Kimbap", "Chicken Noodle Soup", "Donkatsu"});

        japanesePage = createStylePanel("Japanese", new String[]{
                "resources\\Sushi.png",
                "resources\\Sukiyaki.png",
                "resources\\Tamagoyaki.png",
                "resources\\Ramen.png"
        }, new String[]{"Sushi", "Traditional Beef Sukiyaki", "Tamagoyaki\n(Japanese Sweet Omelet)", "Ramen"});

        frenchPage = createStylePanel("French", new String[]{
                "resources\\souffle.png",
                "resources\\macarons.png",
                "resources\\dijon.png",
                "resources\\confit.png"

        }, new String[]{"Chocolate Souffle", "French Macaroon", "Chicken Dijon", "Chicken Confit"});

        contentPanel.add(homePage, "Home");
        contentPanel.add(pinoyPage, "Pinoy");
        contentPanel.add(koreanPage, "Korean");
        contentPanel.add(japanesePage, "Japanese");
        contentPanel.add(frenchPage, "French");


        pinoyLabel.addMouseListener(
                new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "Pinoy");
            }
        });

        koreanLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "Korean");
            }
        });

        japaneseLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "Japanese");
            }
        });

        frenchLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "French");
            }
        });

        homeLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                cardLayout.show(contentPanel, "Home");
            }
        });

        frame.add(redBar, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setPreferredSize(new Dimension(frame.getWidth(), 20));


        int inch = (int) (Toolkit.getDefaultToolkit().getScreenResolution() * 0.25);
        Border paddingBorder = BorderFactory.createEmptyBorder(inch, inch, inch, inch);


        contentPanel.setBorder(paddingBorder);

        frame.add(contentPanel, BorderLayout.CENTER);

        frame.add(footerPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==logoutButton){
                setVisible(false);
                new LamiKaeyoMain();
            }
        }

            private static JPanel createHomePage() {
        JPanel homePage = new JPanel(new BorderLayout());

            try {
                BufferedImage backgroundImage = ImageIO.read(new File("resources\\logo.png"));
                ImageIcon logoIcon = new ImageIcon(backgroundImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH));
                JLabel logoLabel = new JLabel(logoIcon);

                JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                logoPanel.add(logoLabel);

                JPanel logoContainer = new JPanel(new BorderLayout());
                logoContainer.setOpaque(false);
                logoContainer.add(logoPanel, BorderLayout.NORTH);

                JLabel promptLabel = new JLabel("What do you want to cook?");
                Font labelFont = new Font("Brush Script MT", Font.PLAIN, 48);
                promptLabel.setFont(labelFont);
                promptLabel.setForeground(Color.decode("#8B0000"));
                promptLabel.setHorizontalAlignment(JLabel.CENTER);

                JPanel textContainer = new JPanel(new BorderLayout());
                textContainer.setOpaque(false);
                textContainer.add(promptLabel, BorderLayout.NORTH);
                textContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

                logoContainer.add(textContainer, BorderLayout.CENTER);

                JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
                imagePanel.setOpaque(false);

                String[] imagePaths = {
                    "resources\\pinoyStyle.png",
                    "resources\\koreanStyle.png",
                    "resources\\japaneseStyle.png",
                    "resources\\frenchStyle.png"
                };

                for (int i = 0; i < imagePaths.length; i++) {
                    BufferedImage image = ImageIO.read(new File(imagePaths[i]));
                    ImageIcon icon = new ImageIcon(image.getScaledInstance(170, 120, Image.SCALE_SMOOTH));
                    JLabel imageLabel = new JLabel(icon);
                    imageLabel.setBorder(BorderFactory.createLineBorder(Color.decode("#8B0000"), 2));

                    int finalI = i;

                    imageLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {

                    CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                    switch (finalI) {
                        case 0:
                            cardLayout.show(contentPanel, "Pinoy");
                            break;
                        case 1:
                            cardLayout.show(contentPanel, "Korean");
                            break;
                        case 2:
                            cardLayout.show(contentPanel, "Japanese");
                            break;
                        case 3:
                            cardLayout.show(contentPanel, "French");
                            break;
                        default:
                            break;
                    }
                }
            });

            imagePanel.add(imageLabel);
        }

        homePage.add(logoContainer, BorderLayout.NORTH);
        homePage.add(imagePanel, BorderLayout.CENTER);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return homePage;
}


    private static JPanel createStylePanel(String styleName, String[] imagePaths, String[] foodNames) {
        JPanel stylePanel = new JPanel(new BorderLayout());

        JLabel styleLabel = new JLabel(styleName.toUpperCase() + " STYLE", SwingConstants.CENTER);
        Font styleFont = new Font("Impact", Font.BOLD, 36);
        styleLabel.setForeground(Color.decode("#8B0000"));
        styleLabel.setFont(styleFont);
        stylePanel.add(styleLabel, BorderLayout.NORTH);

        JPanel imagePanelContainer = new JPanel(new GridLayout(2, 2));

        for (int i = 0; i < imagePaths.length; i++) {
            JPanel imagePanel = new JPanel(new BorderLayout());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePaths[i]).getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT));
            JLabel imageLabel = new JLabel(imageIcon);
            JLabel textLabel = new JLabel(foodNames[i]);
            textLabel.setFont(new Font("century gothic", Font.BOLD, 18));
            textLabel.setForeground(Color.decode("#8B0000"));
            textLabel.setHorizontalAlignment(JLabel.CENTER);

            if (foodNames[i].equals("Adobo Flakes")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel adoboRecipePanel = FoodStyles.createAdoboRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "AdoboRecipe");
                        contentPanel.add(adoboRecipePanel, "AdoboRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Beef Caldereta")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel beefCalderetaRecipePanel = FoodStyles.createBeefCalderetaRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "BeefCalderetaRecipe");
                        contentPanel.add(beefCalderetaRecipePanel, "BeefCalderetaRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Chicken Tinola")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel chickenTinolaRecipePanel = FoodStyles.createChickenTinolaRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "ChickenTinolaRecipe");
                        contentPanel.add(chickenTinolaRecipePanel, "ChickenTinolaRecipe");
                    }
                });
            } if (foodNames[i].equals("Pork Sinigang with Gabi")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel porkSinigangRecipePanel = FoodStyles.createPorkSinigangRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "PorkSinigangRecipe");
                        contentPanel.add(porkSinigangRecipePanel, "PorkSinigangRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Sushi")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel sushiRecipePanel = FoodStyles.createSushiRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "SushiRecipe");
                        contentPanel.add(sushiRecipePanel, "SushiRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Traditional Beef Sukiyaki")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel sukiyakiRecipePanel = FoodStyles.createSukiyakiRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "SukiyakiRecipe");
                        contentPanel.add(sukiyakiRecipePanel, "SukiyakiRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Ramen")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel ramenRecipePanel = FoodStyles.createRamenRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "RamenRecipe");
                        contentPanel.add(ramenRecipePanel, "RamenRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Tamagoyaki\n(Japanese Sweet Omelet)")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel tamagoyakiRecipePanel = FoodStyles.createTamagoyakiRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "TamagoyakiRecipe");
                        contentPanel.add(tamagoyakiRecipePanel, "TamagoyakiRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Sotteok")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel sotteokRecipePanel = FoodStyles.createSotteokRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "SotteokRecipe");
                        contentPanel.add(sotteokRecipePanel, "SotteokRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Mini Kimbap")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel miniKimbapPanel = FoodStyles.createMiniKimbapRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "MiniKimbapRecipe");
                        contentPanel.add(miniKimbapPanel, "MiniKimbapRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Chicken Noodle Soup")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel chickenNoodleSoupPanel = FoodStyles.createChickenNoodleSoupRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "ChickenNoodleSoupRecipe");
                        contentPanel.add(chickenNoodleSoupPanel, "ChickenNoodleSoupRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Donkatsu")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel donkatsuPanel = FoodStyles.createDonkatsuRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        cardLayout.show(contentPanel, "DonkatsuRecipe");
                        contentPanel.add(donkatsuPanel, "DonkatsuRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Chocolate Souffle")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel chocolateSoufflePanel = FoodStyles.createChocolateSouffleRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        contentPanel.add(chocolateSoufflePanel, "ChocolateSouffleRecipe");
                        cardLayout.show(contentPanel, "ChocolateSouffleRecipe");
                    }
                });
            }
            if (foodNames[i].equals("French Macaroon")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel frenchMacaroonPanel = FoodStyles.createFrenchMacaroonRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        contentPanel.add(frenchMacaroonPanel, "FrenchMacaroonRecipe");
                        cardLayout.show(contentPanel, "FrenchMacaroonRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Chicken Dijon")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel chickenDijonPanel = FoodStyles.createChickenDijonRecipePanel();
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        contentPanel.add(chickenDijonPanel, "ChickenDijonRecipe");
                        cardLayout.show(contentPanel, "ChickenDijonRecipe");
                    }
                });
            }
            if (foodNames[i].equals("Chicken Confit")) {
                imagePanel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        JPanel chickenConfitPanel = FoodStyles.createChickenConfitRecipePanel(); // Call the method to create Chicken Confit panel
                        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
                        contentPanel.add(chickenConfitPanel, "ChickenConfitRecipe");
                        cardLayout.show(contentPanel, "ChickenConfitRecipe");
                    }
                });
            }
            imagePanel.add(imageLabel, BorderLayout.CENTER);
            imagePanel.add(textLabel, BorderLayout.SOUTH);
            imagePanelContainer.add(imagePanel);
        }
        stylePanel.add(imagePanelContainer, BorderLayout.CENTER);

        return stylePanel;
    }
}