package LamiKaeyo;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class FoodStyles{
 public static JPanel createAdoboRecipePanel() {
    JPanel adoboPanel = new JPanel(new BorderLayout());

    JLabel adoboLabel = new JLabel("ADOBO FLAKES");
    adoboLabel.setForeground(Color.decode("#8B0000"));
    adoboLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    adoboLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1)); 
    labelsPanel.add(adoboLabel); 
    JLabel noteLabel = new JLabel("Check the ingredient/s you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
    labelsPanel.add(noteLabel); 

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);
    

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] adoboIngredients = {
        "1 Sachet MAGGI Magic Sarap",
        "0.5 Kg Pork Shoulder",
        "2 tbls Vegetable Oil",
        "1 Head Garlic",
        "0.25 Cup Spiced Vinegar",
        "2 tbls Soy Sauce",
        "1 Cup Water",
        "1 Piece Bay Leaf",
        "0.25 tbls Ground Pepper",
        "0.25 Cup Spiced Vinegar"
    };
    
    Map<String, Double> ingredientPrices = new HashMap<>();
    ingredientPrices.put("1 Sachet MAGGI Magic Sarap", 5.00);
    ingredientPrices.put("0.5 Kg Pork Shoulder", 200.00);
    ingredientPrices.put("2 tbls Vegetable Oil", 15.00);
    ingredientPrices.put("1 Head Garlic", 7.00);
    ingredientPrices.put("0.25 Cup Spiced Vinegar", 12.00);
    ingredientPrices.put("2 tbls Soy Sauce", 8.00);
    ingredientPrices.put("1 Piece Bay Leaf", 5.00);
    ingredientPrices.put("0.25 tbls Ground Pepper", 8.00);
 
    

    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false }; 
    double[] totalCost = { 0.0 }; 

    
    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    
    buyButton.setVisible(false); 
    

    for (String ingredient : adoboIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0; 
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = ingredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0])); 
        });
        gbc.gridx = checkboxCount % numColumns; 
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++; 
        }

        checkboxCount++;
    }
    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null); 
    ingredientsScrollPane.getViewport().setOpaque(false); 
    

    JTextArea procedureArea = new JTextArea("Procedure:\n\n"
            + "1. Season chicken with 1 sachet MAGGI® Magic Sarap®. Set aside.\n"
            + "2. Sauté garlic, ginger, onion, lemongrass (optional), and finger chili in oil.\n"
            + "3. Add chicken and cook for 2 minutes.\n"
            + "4. Pour water, boil, skim the scum, and simmer for 15 minutes.\n"
            + "5. Add papaya and simmer for another 10 minutes.\n"
            + "6. Season with the remaining 1 sachet of MAGGI® Magic Sarap®. Stir in chili tops.\n"
            + "7.Transfer into a serving bowl and serve hot.");
    procedureArea.setForeground(Color.decode("#8B0000"));
    procedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    procedureArea.setOpaque(false);
    procedureArea.setEditable(false);
    procedureArea.setWrapStyleWord(true);
    procedureArea.setLineWrap(true);

    JScrollPane procedureScrollPane = new JScrollPane(procedureArea);
    procedureScrollPane.setBorder(null); 
    procedureScrollPane.getViewport().setOpaque(false); 

    buyButton.setVisible(false); 
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});
        
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose());           
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);

        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    adoboPanel.setLayout(new BorderLayout());
    adoboPanel.add(labelsPanel, BorderLayout.NORTH);
    adoboPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    adoboPanel.add(buttonPanel, BorderLayout.EAST);
    adoboPanel.add(procedureScrollPane, BorderLayout.SOUTH);

    return adoboPanel;
}
 
 public static JPanel createBeefCalderetaRecipePanel() {
    JPanel beefCalderetaPanel = new JPanel(new BorderLayout());

    JLabel beefCalderetaLabel = new JLabel("BEEF CALDERETA");
    beefCalderetaLabel.setForeground(Color.decode("#8B0000"));
    beefCalderetaLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    beefCalderetaLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1)); 
    labelsPanel.add(beefCalderetaLabel); 
    JLabel noteLabel = new JLabel("Check the ingredient/s you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
    labelsPanel.add(noteLabel); 

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 5, 10);
    gbc.gridy = 0;

    String[] beefCalderetaIngredients = {
        "0.25 cup Vegetable Oil",
        "2 pcs Potato",
        "1 pc Carrot",
        "1 kg Beef Short Ribs",
        "3 sachets MAGGI® Magic Sarap® 8g",
        "1 head Garlic",
        "1 pc Onion",
        "1 pc Red Chili",
        "0.75 cup Tomato Paste",
        "0.5 cup Liver Spread",
        "2 pcs Bay Leaf",
        "1 pc Red Bell Pepper",
        "1 pc Green Bell Pepper",
        "0.5 cup grated cheese",
        "0.25 cup Coconut Cream",
        "0.25 cup Green Peas"
    };
     Map<String, Double> ingredientPrices = new HashMap<>();
    ingredientPrices.put("0.25 cup Vegetable Oil", 13.00);
    ingredientPrices.put("2 pcs Potato", 55.00);
    ingredientPrices.put("1 pc Carrot", 13.00);
    ingredientPrices.put("1 kg Beef Short Ribs", 1000.00);
    ingredientPrices.put("3 sachets MAGGI® Magic Sarap® 8g", 15.00);
    ingredientPrices.put("1 head Garlic", 10.00);
    ingredientPrices.put("1 pc Onion", 12.00);
    ingredientPrices.put("1 pc Red Chili", 5.00);
    ingredientPrices.put("0.75 cup Tomato Paste", 25.00);
    ingredientPrices.put("0.5 cup Liver Spread", 55.00);
    ingredientPrices.put("2 pcs Bay Leaf", 5.00);
    ingredientPrices.put("1 pc Red Bell Pepper", 10.00);
    ingredientPrices.put("1 pc Green Bell Pepper", 10.00);
    ingredientPrices.put("0.5 cup grated cheese", 55.00);
    ingredientPrices.put("0.25 cup Coconut Cream", 75.00);
    ingredientPrices.put("0.25 cup Green Peas", 100.00);

    int numColumns = 2;
    int checkboxCount = 0;

    boolean[] isChecked = { false }; 
    double[] totalCost = { 0.0 }; 

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : beefCalderetaIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0; 
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = ingredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0])); 
        });

        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }

    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null);
    ingredientsScrollPane.getViewport().setOpaque(false);

    JTextArea procedureArea = new JTextArea("Procedure:\n\n"
            + "1. Sauté potato and carrot in oil until golden brown and set aside.\n"
            + "2. Season beef with 2 sachets 8g MAGGI® Magic Sarap®. Sauté beef and set aside.\n"
            + "3. Sauté garlic, onion, and chili in oil in a pot. Add tomato paste and liver spread. Add sautéed beef. Barely cover with water and add bay leaves. Simmer until tender. Add water as needed.\n"
            + "4. Once tender, add sautéed potato, carrot, and bell peppers. Season with remaining 1 sachet of MAGGI® Magic Sarap®. Stir in cheese, coconut cream, and green peas. Transfer to a serving plate and serve.");
    procedureArea.setForeground(Color.decode("#8B0000"));
    procedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    procedureArea.setOpaque(false);
    procedureArea.setEditable(false);
    procedureArea.setWrapStyleWord(true);
    procedureArea.setLineWrap(true);

    JScrollPane procedureScrollPane = new JScrollPane(procedureArea);
    procedureScrollPane.setBorder(null);
    procedureScrollPane.getViewport().setOpaque(false);

    buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});

   
        
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);

        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    beefCalderetaPanel.add(labelsPanel, BorderLayout.NORTH);
    beefCalderetaPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    beefCalderetaPanel.add(buttonPanel, BorderLayout.EAST);
    beefCalderetaPanel.add(procedureScrollPane, BorderLayout.SOUTH);

    return beefCalderetaPanel;
}

public static JPanel createChickenTinolaRecipePanel() {
    JPanel chickenTinolaPanel = new JPanel(new BorderLayout());

    JLabel chickenTinolaLabel = new JLabel("CHICKEN TINOLA");
    chickenTinolaLabel.setForeground(Color.decode("#8B0000"));
    chickenTinolaLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    chickenTinolaLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1)); 
    labelsPanel.add(chickenTinolaLabel); 
    JLabel noteLabel = new JLabel("Check the ingredient/s you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
    labelsPanel.add(noteLabel); 
    
        JPanel ingredientsPanel = new JPanel(new GridBagLayout());
        ingredientsPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 25, 10);
        gbc.gridy = 0;

        String[] chickenTinolaIngredients = {
            "0.5 kg Chicken Leg",
            "2 sachets MAGGI® Magic Sarap® 8g",
            "2 tbsp Vegetable Oil",
            "4 cloves Garlic",
            "1 pc Ginger",
            "1 pc Onion",
            "1 stalk Lemongrass",
            "1 pc Finger Chili",
            "4 cups Water",
            "2 cups Papaya",
            "0.25 cup Red Chili"
        };
        Map<String, Double> ingredientPrices = new HashMap<>();
        ingredientPrices.put("0.5 kg Chicken Leg", 120.00);
        ingredientPrices.put("2 sachets MAGGI® Magic Sarap® 8g", 10.00);
        ingredientPrices.put("2 tbsp Vegetable Oil", 15.00);
        ingredientPrices.put("4 cloves Garlic", 7.00);
        ingredientPrices.put("1 pc Ginger", 5.00);
        ingredientPrices.put("1 pc Onion", 12.00);
        ingredientPrices.put("1 stalk Lemongrass", 2.00);
        ingredientPrices.put("1 pc Finger Chili", 5.00);
        ingredientPrices.put("2 cups Papaya", 40.00);
        ingredientPrices.put("0.25 cup Red Chili", 20.00);


        int numColumns = 2;
        int checkboxCount = 0;

        boolean[] isChecked = { false }; 
    double[] totalCost = { 0.0 }; 

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : chickenTinolaIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0; 
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = ingredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0])); 
        });

        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }
        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
        ingredientsScrollPane.setBorder(null);
        ingredientsScrollPane.getViewport().setOpaque(false);

        JTextArea procedureArea = new JTextArea("Procedure:\n\n"
                + "1. Season chicken with 1 sachet MAGGI® Magic Sarap®. Set aside.\n"
                + "2. Sauté garlic, ginger, onion, lemongrass (optional), and finger chili in oil. Add chicken and cook for 2 minutes.\n"
                + "3. Pour water, boil, skim the scum, and simmer for 15 minutes.\n"
                + "4. Add papaya and simmer for another 10 minutes.\n"
                + "5. Season with the remaining 1 sachet of MAGGI® Magic Sarap®. Stir in chili tops. Transfer into a serving bowl and serve hot.");
        procedureArea.setForeground(Color.decode("#8B0000"));
        procedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
        procedureArea.setOpaque(false);
        procedureArea.setEditable(false);
        procedureArea.setWrapStyleWord(true);
        procedureArea.setLineWrap(true);

        JScrollPane procedureScrollPane = new JScrollPane(procedureArea);
        procedureScrollPane.setBorder(null);
        procedureScrollPane.getViewport().setOpaque(false);

         buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);

        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    chickenTinolaPanel.add(labelsPanel, BorderLayout.NORTH);
    chickenTinolaPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    chickenTinolaPanel.add(buttonPanel, BorderLayout.EAST);
    chickenTinolaPanel.add(procedureScrollPane, BorderLayout.SOUTH);

    return chickenTinolaPanel;
}

      public static JPanel createPorkSinigangRecipePanel() {
    JPanel porkSinigangPanel = new JPanel(new BorderLayout());

    JLabel porkSinigangLabel = new JLabel("PORK SINIGANG WITH GABI");
    porkSinigangLabel.setForeground(Color.decode("#8B0000"));
    porkSinigangLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    porkSinigangLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1)); 
    labelsPanel.add(porkSinigangLabel); 
    JLabel noteLabel = new JLabel("Check the ingredient/s you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
    labelsPanel.add(noteLabel); 
    
        JPanel ingredientsPanel = new JPanel(new GridBagLayout());
        ingredientsPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 25, 10);
        gbc.gridy = 0;

        String[] porkSinigangIngredients = {
            "5 cups Water",
            "0.5 kg Pork Spareribs",
            "1 pc Onion",
            "2 pcs Tomato",
            "1 pc Finger Chili",
            "0.5 cup Radish",
            "0.5 cup Okra",
            "0.5 cup Sitaw",
            "1 sachet MAGGI® Magic Sinigang Sampalok With Gabi Mix",
            "1 cup Kangkong"
        };
        Map<String, Double> ingredientPrices = new HashMap<>();
        ingredientPrices.put("0.5 kg Pork Spareribs", 200.00);
        ingredientPrices.put("1 pc Onion", 20.00);
        ingredientPrices.put("2 pcs Tomato", 40.00);
        ingredientPrices.put("1 pc Finger Chili", 13.00);
        ingredientPrices.put("0.5 cup Radish", 20.00);
        ingredientPrices.put("0.5 cup Okra", 13.00);
        ingredientPrices.put("0.5 cup Sitaw", 55.00);
        ingredientPrices.put("1 sachet MAGGI® Magic Sinigang Sampalok With Gabi Mix", 30.00);
        ingredientPrices.put("1 cup Kangkong", 55.00);

        int numColumns = 2;
        int checkboxCount = 0;

        boolean[] isChecked = { false }; 
    double[] totalCost = { 0.0 }; 

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : porkSinigangIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0; 
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = ingredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0])); 
        });

        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }

        JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
        ingredientsScrollPane.setBorder(null);
        ingredientsScrollPane.getViewport().setOpaque(false);

        JTextArea procedureArea = new JTextArea("Procedure:\n\n"
                + "1. Simmer pork with water with onion and tomatoes for 45 minutes.\n"
                + "2. Add siling panigang, radish, okra, and sitaw with 2-min intervals.\n"
                + "3. Pour MAGGI® Magic Sinigang with Gabi. Stir in kangkong. Transfer into a serving bowl and serve immediately.");
        procedureArea.setForeground(Color.decode("#8B0000"));
        procedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
        procedureArea.setOpaque(false);
        procedureArea.setEditable(false);
        procedureArea.setWrapStyleWord(true);
        procedureArea.setLineWrap(true);

        JScrollPane procedureScrollPane = new JScrollPane(procedureArea);
        procedureScrollPane.setBorder(null);
        procedureScrollPane.getViewport().setOpaque(false);

        buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});

    
        
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    porkSinigangPanel.add(labelsPanel, BorderLayout.NORTH);
    porkSinigangPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    porkSinigangPanel.add(buttonPanel, BorderLayout.EAST);
    porkSinigangPanel.add(procedureScrollPane, BorderLayout.SOUTH);

    return porkSinigangPanel;
}
   public static JPanel createSushiRecipePanel() {
    JPanel sushiPanel = new JPanel(new BorderLayout());

    JLabel sushiLabel = new JLabel("SUSHI");
    sushiLabel.setForeground(Color.decode("#8B0000"));
    sushiLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    sushiLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1)); 
    labelsPanel.add(sushiLabel); 
    JLabel noteLabel = new JLabel("Check the ingredient/s you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
    labelsPanel.add(noteLabel); 

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] sushiIngredients = {
        "1 ⅓ cups water",
        "⅔ cup uncooked short-grain white rice",
        "3 tablespoons rice vinegar",
        "3 tablespoons white sugar",
        "1 ½ teaspoons salt",
        "4 sheets nori seaweed sheets",
        "½ pound imitation crabmeat, flaked",
        "1 avocado - peeled, pitted, and sliced",
        "½ cucumber, peeled, cut into small strips",
        "2 tablespoons pickled ginger"
    };
    
    Map<String, Double> ingredientPrices = new HashMap<>();
    ingredientPrices.put("1 ⅓ cups water", 5.00);
    ingredientPrices.put("⅔ cup uncooked short-grain white rice", 15.00);
    ingredientPrices.put("3 tablespoons rice vinegar", 15.00);
    ingredientPrices.put("3 tablespoons white sugar", 15.00);
    ingredientPrices.put("1 ½ teaspoons salt", 10.00);
    ingredientPrices.put("4 sheets nori seaweed sheets", 60.00);
    ingredientPrices.put("½ pound imitation crabmeat, flaked", 100.00);
    ingredientPrices.put("1 avocado - peeled, pitted, and sliced", 50.00);
    ingredientPrices.put("½ cucumber, peeled, cut into small strips", 1.49);
    ingredientPrices.put("2 tablespoons pickled ginger", 15.00);
    
    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false }; 
    double[] totalCost = { 0.0 }; 

    
    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    
    buyButton.setVisible(false); 

    for (String ingredient : sushiIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0; 
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = ingredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0])); 
        });

        
        gbc.gridx = checkboxCount % numColumns; 
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++; 
        }

        checkboxCount++;
    }

    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null); 
    ingredientsScrollPane.getViewport().setOpaque(false); 

    JTextArea procedureArea = new JTextArea("Procedure:\n\n"
            + "1. Bring water to a boil in a medium pot; stir in rice. Reduce heat to medium-low, cover, and simmer until rice is tender and water has been absorbed, 20 to 25 minutes.\n"
            + "2. Mix rice vinegar, sugar, and salt in a small bowl. Gently stir into cooked rice in the pot and set aside.\n"
            + "3. Lay nori sheets on a baking sheet.\n"
            + "4. Heat nori in the preheated oven until warm, 1 to 2 minutes.\n"
            + "5. Center 1 nori sheet on a bamboo sushi mat. Use wet hands to spread a thin layer of rice on top. Arrange 1/4 of the crabmeat, avocado, cucumber, and pickled ginger over rice in a line down the center. Lift one end of the mat and roll it tightly over filling to make a complete roll. Repeat with remaining ingredients.\n"
            + "6. Use a wet, sharp knife to cut each roll into 4 to 6 slices.\n"
            + "Tips:\n"
            + "If you do not have a bamboo sushi mat, the easiest way to roll sushi is with a clean dish towel.");
    procedureArea.setForeground(Color.decode("#8B0000"));
    procedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    procedureArea.setOpaque(false);
    procedureArea.setEditable(false);
    procedureArea.setWrapStyleWord(true);
    procedureArea.setLineWrap(true);

    JScrollPane procedureScrollPane = new JScrollPane(procedureArea);
    procedureScrollPane.setBorder(null); 
    procedureScrollPane.getViewport().setOpaque(false); 

    buyButton.setVisible(false); 
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});

    
        
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);

        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    sushiPanel.setLayout(new BorderLayout());
    sushiPanel.add(labelsPanel, BorderLayout.NORTH);
    sushiPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    sushiPanel.add(buttonPanel, BorderLayout.EAST);
    sushiPanel.add(procedureScrollPane, BorderLayout.SOUTH);

    return sushiPanel;
}


public static JPanel createSukiyakiRecipePanel() {
    JPanel sukiyakiPanel = new JPanel(new BorderLayout());

    JLabel sukiyakiLabel = new JLabel("TRADITIONAL BEEF SUKIYAKI");
    sukiyakiLabel.setForeground(Color.decode("#8B0000"));
    sukiyakiLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    sukiyakiLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1)); 
    labelsPanel.add(sukiyakiLabel); 
    JLabel noteLabel = new JLabel("Check the ingredient/s you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
    labelsPanel.add(noteLabel); 

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] sukiyakiIngredients = {
        "1 ½ cups water",
        "⅔ cup soy sauce",
        "⅔ cup white sugar",
        "⅓ cup sake",
        "1 pound thinly sliced beef",
        "1 (12 ounce) package firm tofu, drained and cut into bite-size pieces",
        "½ head Chinese cabbage, cut into bite-size pieces",
        "1 (7 ounce) package yam noodles (shirataki), drained",
        "7 shiitake mushrooms, sliced",
        "1 enoki mushrooms, roots removed",
        "1 green onion (negi), sliced",
        "1 tablespoon vegetable oil",
        "4 eggs"
    };

    
    Map<String, Double> sukiyakiIngredientPrices = new HashMap<>();
    sukiyakiIngredientPrices.put("1 ½ cups water", 0.99);
    sukiyakiIngredientPrices.put("⅔ cup soy sauce", 10.00);
    sukiyakiIngredientPrices.put("⅔ cup white sugar", 10.00);
    sukiyakiIngredientPrices.put("⅓ cup sake", 25.00);
    sukiyakiIngredientPrices.put("1 pound thinly sliced beef", 2.49);
    sukiyakiIngredientPrices.put("1 (12 ounce) package firm tofu, drained and cut into bite-size pieces", 175.00);
    sukiyakiIngredientPrices.put("½ head Chinese cabbage, cut into bite-size pieces", 55.00);
    sukiyakiIngredientPrices.put("1 (7 ounce) package yam noodles (shirataki), drained", 50.00);
    sukiyakiIngredientPrices.put("7 shiitake mushrooms, sliced", 30.00);
    sukiyakiIngredientPrices.put("1 enoki mushrooms, roots removed", 50.00);
    sukiyakiIngredientPrices.put("1 green onion (negi), sliced", 30.00);
    sukiyakiIngredientPrices.put("1 tablespoon vegetable oil", 8.00);
    sukiyakiIngredientPrices.put("4 eggs", 60.00);
    
    
    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false }; 
    double[] totalCost = { 0.0 }; 

    
    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);

    buyButton.setVisible(false); 

    for (String ingredient : sukiyakiIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0; 
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = sukiyakiIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0])); 
        });

        gbc.gridx = checkboxCount % numColumns; 
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++; 
        }

        checkboxCount++;
    }

    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null); 
    ingredientsScrollPane.getViewport().setOpaque(false); 

    JTextArea sukiyakiProcedureArea = new JTextArea("Procedure:\n\n"
            + "1. Combine water, soy sauce, sugar, and sake in a bowl to make broth.\n"
            + "2. Arrange beef, tofu, Chinese cabbage, yam noodles, shiitake mushrooms, enoki mushrooms, and green onion on separate plates on the table.\n"
            + "3. Heat oil in an electric skillet or a large skillet set over a hot plate at the table. Add beef slices; cook and stir until browned, about 1 minute. Pour in some broth; bring to a boil. Stir in tofu, cabbage, noodles, shiitake mushrooms, enoki mushrooms, and green onion; simmer until softened, about 5 minutes.\n"
            + "4. Ladle cooked sukiyaki mixture into serving bowls. Replenish broth in the skillet.\n"
            + "5. Crack each egg into a small bowl and beat lightly. Serve sukiyaki alongside eggs for dipping.\n\n"
            + "Cook’s Note:\n"
            + "If using dried shiitake mushrooms, follow directions on the package to rehydrate.");

    sukiyakiProcedureArea.setForeground(Color.decode("#8B0000"));
    sukiyakiProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    sukiyakiProcedureArea.setOpaque(false);
    sukiyakiProcedureArea.setEditable(false);
    sukiyakiProcedureArea.setWrapStyleWord(true);
    sukiyakiProcedureArea.setLineWrap(true);

    JScrollPane sukiyakiProcedureScrollPane = new JScrollPane(sukiyakiProcedureArea);
    sukiyakiProcedureScrollPane.setBorder(null); 
    sukiyakiProcedureScrollPane.getViewport().setOpaque(false); 

    buyButton.setVisible(false); 
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});

    
        
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);

        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    sukiyakiPanel.setLayout(new BorderLayout());
    sukiyakiPanel.add(labelsPanel, BorderLayout.NORTH);
    sukiyakiPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    sukiyakiPanel.add(buttonPanel, BorderLayout.EAST);
    sukiyakiPanel.add(sukiyakiProcedureScrollPane, BorderLayout.SOUTH);

    return sukiyakiPanel;
}
  public static JPanel createRamenRecipePanel() {
    JPanel ramenPanel = new JPanel(new BorderLayout());

    JLabel ramenLabel = new JLabel("Ramen");
    ramenLabel.setForeground(Color.decode("#8B0000"));
    ramenLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    ramenLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1)); 
    labelsPanel.add(ramenLabel); 
    JLabel noteLabel = new JLabel("Check the ingredient/s you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
    labelsPanel.add(noteLabel); 

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] ramenIngredients = {
    "6 cups chicken broth",
    "3 cloves garlic, minced",
    "1 onion, chopped",
    "1 piece ginger (about 1-inch), sliced",
    "2 tablespoons soy sauce",
    "2 tablespoons mirin (sweet rice wine) or sugar",
    "Salt to taste",
    "Ramen noodles (fresh or dried)",
    "Sliced cooked pork (chashu), chicken, or tofu for protein",
    "Soft-boiled eggs (seasoned with soy sauce and mirin while boiling)",
    "Sliced green onions",
    "Nori seaweed sheets",
    "Bamboo shoots (menma)",
    "Corn kernels (optional)",
    "Sesame seeds (optional)",
    "Red chili flakes or sesame oil (optional for spice)"
};
    
    Map<String, Double> ramenIngredientPrices = new HashMap<>();
    ramenIngredientPrices.put("6 cups chicken broth", 200.00);
    ramenIngredientPrices.put("3 cloves garlic, minced", 35.00);
    ramenIngredientPrices.put("1 onion, chopped", 32.00);
    ramenIngredientPrices.put("1 piece ginger (about 1-inch), sliced", 30.00);
    ramenIngredientPrices.put("2 tablespoons soy sauce", 15.00);
    ramenIngredientPrices.put("2 tablespoons mirin (sweet rice wine) or sugar", 15.00);
    ramenIngredientPrices.put("Salt to taste", 20.00);
    ramenIngredientPrices.put("Ramen noodles (fresh or dried)", 150.00);
    ramenIngredientPrices.put("Sliced cooked pork (chashu), chicken, or tofu for protein", 250.00);
    ramenIngredientPrices.put("Soft-boiled eggs (seasoned with soy sauce and mirin while boiling)", 100.00);
    ramenIngredientPrices.put("Sliced green onions", 35.00);
    ramenIngredientPrices.put("Nori seaweed sheets", 125.00);
    ramenIngredientPrices.put("Bamboo shoots (menma)", 75.00);
    ramenIngredientPrices.put("Corn kernels (optional)", 65.00);
    ramenIngredientPrices.put("Sesame seeds (optional)", 85.00);
    ramenIngredientPrices.put("Red chili flakes or sesame oil (optional for spice)", 125.00);

    
    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false }; 
    double[] totalCost = { 0.0 }; 

    
    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);

    buyButton.setVisible(false); 

    for (String ingredient : ramenIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0; 
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = ramenIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0])); 
        });

        gbc.gridx = checkboxCount % numColumns; 
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++; 
        }

        checkboxCount++;
    }

    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null); 
    ingredientsScrollPane.getViewport().setOpaque(false); 

    JTextArea ramenProcedureArea = new JTextArea("Procedure:\n\n"
        + "1. In a large pot, heat a little oil over medium heat. Add minced garlic, chopped onion, and sliced ginger. Sauté until fragrant.\n"
        + "2. Pour in the chicken or vegetable broth. Bring it to a boil, then reduce the heat to low and let it simmer for at least 30-40 minutes to allow the flavors to meld.\n"
        + "3. Add soy sauce and mirin (or sugar) to the broth. Adjust the seasoning with salt if needed. Simmer for an additional 10-15 minutes.\n"
        + "4. While the broth is simmering, prepare your desired toppings - Cook pork, chicken, or tofu separately. Slice them for later use. Soft-boil eggs and marinate them in a mix of soy sauce and mirin for added flavor. Slice green onions, prepare nori sheets, bamboo shoots, and any other desired toppings.\n"
        + "5. Cook the ramen noodles according to the package instructions. Drain and rinse them under cold water to stop the cooking process.\n"
        + "6. Once the broth is ready and the toppings are prepared, it's time to assemble the ramen bowls - Place the cooked noodles into individual bowls, ladle the hot broth over the noodles, ensuring they're fully submerged, and arrange the toppings on the noodles.");


    ramenProcedureArea.setForeground(Color.decode("#8B0000"));
    ramenProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    ramenProcedureArea.setOpaque(false);
    ramenProcedureArea.setEditable(false);
    ramenProcedureArea.setWrapStyleWord(true);
    ramenProcedureArea.setLineWrap(true);

    JScrollPane ramenProcedureScrollPane = new JScrollPane(ramenProcedureArea);
    ramenProcedureScrollPane.setBorder(null); 
    ramenProcedureScrollPane.getViewport().setOpaque(false); 

    buyButton.setVisible(false); 
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});

    
        
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);

        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    ramenPanel.setLayout(new BorderLayout());
    ramenPanel.add(labelsPanel, BorderLayout.NORTH);
    ramenPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    ramenPanel.add(buttonPanel, BorderLayout.EAST);
    ramenPanel.add(ramenProcedureScrollPane, BorderLayout.SOUTH);

    return ramenPanel;
}

  public static JPanel createTamagoyakiRecipePanel() {
    JPanel tamagoyakiPanel = new JPanel(new BorderLayout());

    JLabel tamagoyakiLabel = new JLabel("TAMAGOYAKI");
    tamagoyakiLabel.setForeground(Color.decode("#8B0000"));
    tamagoyakiLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    tamagoyakiLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1)); 
    labelsPanel.add(tamagoyakiLabel); 
    JLabel noteLabel = new JLabel("Check the ingredient/s you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
    labelsPanel.add(noteLabel); 

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    
    String[] tamagoyakiIngredients = {
        "1 tablespoon water",
        "1 teaspoon soy sauce, or to taste (Optional)",
        "½ teaspoon white sugar",
        "3 egg"
    };
    
    Map<String, Double> tamagoyakiIngredientPrices = new HashMap<>();
    tamagoyakiIngredientPrices.put("1 tablespoon water", 5.00);
    tamagoyakiIngredientPrices.put("1 teaspoon soy sauce, or to taste (Optional)", 5.00);
    tamagoyakiIngredientPrices.put("½ teaspoon white sugar", 15.00);
    tamagoyakiIngredientPrices.put("3 egg", 45.00);
    
    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false }; 
    double[] totalCost = { 0.0 }; 

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false); 

    for (String ingredient : tamagoyakiIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0; 
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = tamagoyakiIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0])); 
        });

        gbc.gridx = checkboxCount % numColumns; 
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++; 
        }

        checkboxCount++;
    }
    
    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null); 
    ingredientsScrollPane.getViewport().setOpaque(false); 

    JTextArea tamagoyakiProcedureArea = new JTextArea("Procedure:\n\n"
        + "1. Mix water, soy sauce, and sugar together in a small bowl until sugar is dissolved. Add egg and beat until the egg mixture is combined.\n"
        + "2. Heat a skillet over medium heat until a drop of water evaporates almost immediately. Pour the egg mixture into the pan. Cook until the bottom of the omelet has solidified, 3 to 5 minutes. Flip and fold the omelet into a square and transfer it to a plate.");

    tamagoyakiProcedureArea.setForeground(Color.decode("#8B0000"));
    tamagoyakiProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    tamagoyakiProcedureArea.setOpaque(false);
    tamagoyakiProcedureArea.setEditable(false);
    tamagoyakiProcedureArea.setWrapStyleWord(true);
    tamagoyakiProcedureArea.setLineWrap(true);

    JScrollPane tamagoyakiProcedureScrollPane = new JScrollPane(tamagoyakiProcedureArea);
    tamagoyakiProcedureScrollPane.setBorder(null);
    tamagoyakiProcedureScrollPane.getViewport().setOpaque(false);

    buyButton.setVisible(false);
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});

    
        
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    tamagoyakiPanel.setLayout(new BorderLayout());
    tamagoyakiPanel.add(labelsPanel, BorderLayout.NORTH);
    tamagoyakiPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    tamagoyakiPanel.add(buttonPanel, BorderLayout.EAST);
    tamagoyakiPanel.add(tamagoyakiProcedureScrollPane, BorderLayout.SOUTH);

    return tamagoyakiPanel;
 }
 
    public static JPanel createSotteokRecipePanel() {
    JPanel sotteokPanel = new JPanel(new BorderLayout());

    JLabel sotteokLabel = new JLabel("SOTTEOK");
    sotteokLabel.setForeground(Color.decode("#8B0000"));
        sotteokLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    sotteokLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
    labelsPanel.add(sotteokLabel);
    JLabel noteLabel = new JLabel("Check the ingredients you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    labelsPanel.add(noteLabel);

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] sotteokIngredients = {
        "1/3 cup raw sugar",
        "1/3 cup water",
        "1/4 cup ketchup",
        "1 1/2 Tbsp soy sauce, regular",
        "1/2 Tbsp gochujang (Korean chilli paste)",
        "toasted sesame seeds or other crushed nuts",
        "American mustard sauce",
        "mayonnaise",

    };

    Map<String, Double> sotteokIngredientPrices = new HashMap<>();
    sotteokIngredientPrices.put("1/3 cup raw sugar", 6.91);
    sotteokIngredientPrices.put("1/4 cup ketchup", 16.59);
    sotteokIngredientPrices.put("1 1/2 Tbsp soy sauce, regular", 16.59);
    sotteokIngredientPrices.put("1/2 Tbsp gochujang (Korean chilli paste)", 4.42);
    sotteokIngredientPrices.put("toasted sesame seeds or other crushed nuts", 16.59);
    sotteokIngredientPrices.put("American mustard sauce", 110.58);
    sotteokIngredientPrices.put("mayonnaise", 165.80);


    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false };
    double[] totalCost = { 0.0 };

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : sotteokIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0;
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = sotteokIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0]));
        });

        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }

    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null);
    ingredientsScrollPane.getViewport().setOpaque(false);

    JTextArea sotteokProcedureArea = new JTextArea("Procedure:\n\n"
        + "1. Blanch the rice cakes in boiling water for 1-2 minutes to soften them. Then, drain the water and rinse the rice cakes under cold tap water. Set aside. (If your rice cakes are fresh, soft, and the skewer can pass through them without any problems, you can skip this step.)\n"
        + "2. Combine all the sauce ingredients in a saucepan, stirring continuously until the sugar dissolves completely and the sauce thickens. This should take approximately 5 minutes. Be sure to keep stirring to prevent the sauce from burning. Set aside\n"
        + "3. Thread the rice cakes and sausages onto the skewers, alternating them in a pattern. For a well-balanced size, aim for three rice cakes and two sausages per skewer, assuming you’re using a 12cm (or 4.7-inch) long skewer.\n"
        + "4. Heat the oil in a shallow pan until it reaches boiling point. Gently place the skewers into the hot oil and fry them for about one minute on each side to ensure even cooking. Fry the skewers in batches to prevent overcrowding in the pan.\n"
        + "5. If you prefer not to deep-fry, you can also pan-grill the skewers using less oil. Using tongs, carefully remove the fried skewers from the pan and set them aside on a paper towel. Continue this process until all the skewers have been cooked.\n"
        + "6. Dip the cooked skewers into the sauce, generously coat both sides evenly by brushing the sauce onto them. \n"
        + "7. Optionally, garnish with your preferred nuts or seeds, drizzle with the sauce, and serve immediately. \n");
 

    sotteokProcedureArea.setForeground(Color.decode("#8B0000"));
    sotteokProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    sotteokProcedureArea.setOpaque(false);
    sotteokProcedureArea.setEditable(false);
    sotteokProcedureArea.setWrapStyleWord(true);
    sotteokProcedureArea.setLineWrap(true);

    JScrollPane sotteokProcedureScrollPane = new JScrollPane(sotteokProcedureArea);
    sotteokProcedureScrollPane.setBorder(null);
    sotteokProcedureScrollPane.getViewport().setOpaque(false);

    buyButton.setVisible(false);
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});

    
        
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    sotteokPanel.setLayout(new BorderLayout());
    sotteokPanel.add(labelsPanel, BorderLayout.NORTH);
    sotteokPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    sotteokPanel.add(buttonPanel, BorderLayout.EAST);
    sotteokPanel.add(sotteokProcedureScrollPane, BorderLayout.SOUTH); 

  
    return sotteokPanel;
 }
 
 
   public static JPanel createMiniKimbapRecipePanel() {
    JPanel miniKimbapPanel = new JPanel(new BorderLayout());

    JLabel miniKimbapLabel = new JLabel("MINI KIMBAP");
    miniKimbapLabel.setForeground(Color.decode("#8B0000"));
       miniKimbapLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    miniKimbapLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
    labelsPanel.add(miniKimbapLabel);
    JLabel noteLabel = new JLabel("Check the ingredients you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    labelsPanel.add(noteLabel);

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] miniKimbapIngredients = {
        "4 cups cooked rice, short or medium grain",
        "1 Tbsp sesame oil",
        "1/2 tsp fine salt",
        "140 g yellow pickled radish (danmuji)",
        "1 carrot (150g / 5.3 ounces)",
        "2 sheets fish cake",
        "1.5 Tbsp soy sauce, regular",
        "1/2 Tbsp brown sugar",
        "2 Tbsp rice wine (mirin)",
        "6 sheets dried seaweed (nori)"
        
    };

    Map<String, Double> miniKimbapIngredientPrices = new HashMap<>();
    miniKimbapIngredientPrices.put("4 cups cooked rice, short or medium grain", 55.00);
    miniKimbapIngredientPrices.put("1 Tbsp sesame oil", 15.00);
    miniKimbapIngredientPrices.put("1/2 tsp fine salt", 5.00);
    miniKimbapIngredientPrices.put("140 g yellow pickled radish (danmuji)", 150.00);
    miniKimbapIngredientPrices.put("1 carrot (150g / 5.3 ounces)", 50.00);
    miniKimbapIngredientPrices.put("2 sheets fish cake", 30.00);
    miniKimbapIngredientPrices.put("1.5 Tbsp soy sauce, regular", 15.00);
    miniKimbapIngredientPrices.put("1/2 Tbsp brown sugar", 15.00);
    miniKimbapIngredientPrices.put("4 cups cooked rice, short or medium grain", 55.00);
    miniKimbapIngredientPrices.put("2 Tbsp rice wine (mirin)", 40.00);
        

    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false };
    double[] totalCost = { 0.0 };

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : miniKimbapIngredients) {
    JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
    ingredientCheckbox.setForeground(Color.decode("#8B0000"));
    ingredientCheckbox.setOpaque(false);

    ingredientCheckbox.addActionListener(e -> {
        boolean atLeastOneChecked = false;
        totalCost[0] = 0.0;
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    atLeastOneChecked = true;
                    String text = cb.getText();
                    double price = miniKimbapIngredientPrices.getOrDefault(text, 0.0);
                    totalCost[0] += price;
                }
            }
        }
        buyButton.setVisible(atLeastOneChecked);
        buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0]));
    });

    gbc.gridx = checkboxCount % numColumns;
    ingredientsPanel.add(ingredientCheckbox, gbc);

    if (checkboxCount % numColumns == numColumns - 1) {
        gbc.gridy++;
    }

    checkboxCount++;
}

JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
ingredientsScrollPane.setBorder(null);
ingredientsScrollPane.getViewport().setOpaque(false);

    
    JTextArea miniKimbapProcedureArea = new JTextArea("Instructions:\n\n"
            + "Combine mustard, sugar, and water in a bowl, stirring until the mustard dissolves. Add soy sauce and vinegar, mixing well. Set aside. Grind sesame seeds into a fine powder using a spice grinder or mortar and pestle. Stir the ground seeds into the sauce mixture, ensuring they’re evenly distributed. Set aside \n"
            + "In a mixing bowl, combine the rice with sesame oil and salt. Using a rice paddle, mix well to ensure even seasoning. Allow the rice to cool while preparing the remaining ingredients. \n"
            + "Heat up a pan and pour in some oil, then add the carrots. Stir-fry them over medium-high heat for 2-3 minutes until they soften slightly. Season with a sprinkle of salt.\n"
            + "(If reusing the pan from Step 3, wipe it clean with a paper towel first.) Heat up the pan, pour in some oil, and add the fish cakes. \n"
            + "Stir-fry them over medium-high heat for a minute. Add the soy sauce, brown sugar, and mirin to the pan and stir well. Continue cooking for an additional 2 minutes, or until the fish cakes are fully cooked \n"
            + "Cut the seaweed sheets in half lengthwise. Then, stack all the seaweed pieces on top of each other and cut them in half crosswise. This will give you evenly-cut, quarter-sized seaweed pieces. \n"
            + "Place a quarter sheet of dried seaweed on a cutting board, shiny side down and the shorter side facing you. Evenly spread the rice over the seaweed, leaving about 1.5 cm of space on the edge farthest from you. (Tip: Wearing food-handling gloves on your left hand and using a rice paddle with your right hand can speed up the rolling process and prevent rice from sticking to your hands.) \n"
            + "Arrange the prepared ingredients on top of the rice near you. (Tip: For each roll, I recommend using seven to eight julienned carrots, two to three pickled radishes, and three fish cakes. Be sure to distribute the ingredients evenly so you don’t run out.) \n"
            + "To make the kimbap roll, first lift the bottom edge of the seaweed with both hands. Then, roll the seaweed over the filling, away from you, tucking in the filling with your fingers as you go. Finally, use your fingertips to brush some water onto the reserved space, or stick some rice onto it to glue down the roll. \n"
            + "Repeat steps 6 and 7 for the remaining ingredients. You can also prepare them in batches by placing four seaweed sheets on a work surface, spreading rice on each one, adding the filling, and then rolling them up. This can improve your efficiency. \n"
            + "Stack the finished rolls on a cutting board or large plate. Brush the outside of the roll with a little bit of sesame oil for extra flavor and shine. When all the rolls are coated, sprinkle some sesame seeds on them (optional). Serve with the prepared dipping sauce. \n" );
            

    miniKimbapProcedureArea.setForeground(Color.decode("#8B0000"));
    miniKimbapProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    miniKimbapProcedureArea.setOpaque(false);
    miniKimbapProcedureArea.setEditable(false);
    miniKimbapProcedureArea.setWrapStyleWord(true);
    miniKimbapProcedureArea.setLineWrap(true);

    JScrollPane miniKimbapProcedureScrollPane = new JScrollPane(miniKimbapProcedureArea);
    miniKimbapProcedureScrollPane.setBorder(null);
    miniKimbapProcedureScrollPane.getViewport().setOpaque(false);

    buyButton.setVisible(false);
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );

    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});

    
        
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});


    
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    
    miniKimbapPanel.setLayout(new BorderLayout());
    miniKimbapPanel.add(labelsPanel, BorderLayout.NORTH);
    miniKimbapPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    miniKimbapPanel.add(buttonPanel, BorderLayout.EAST);
    miniKimbapPanel.add(miniKimbapProcedureScrollPane, BorderLayout.SOUTH);

    return miniKimbapPanel;
}

    public static JPanel createChickenNoodleSoupRecipePanel() {
    JPanel chickenNoodleSoupPanel = new JPanel(new BorderLayout());

    JLabel chickenNoodleSoupLabel = new JLabel("CHICKEN NOODLE SOUP");
    chickenNoodleSoupLabel.setForeground(Color.decode("#8B0000"));
        chickenNoodleSoupLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    chickenNoodleSoupLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
    labelsPanel.add(chickenNoodleSoupLabel);
    JLabel noteLabel = new JLabel("Check the ingredients you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    labelsPanel.add(noteLabel);

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] chickenNoodleSoupIngredients = {
        "1 kg chicken backs (chicken frames), (2.2 pounds",
        "110 g onion (3.9 ounce) ",
        "50 g green onion (1.8 ounce), white part  ",
        "15 g garlic cloves (0.5 ounce) ",
        "50 g dried anchovies (1.8 ounce)  ",
        "1/2 tsp whole black peppers  ",
        "12 cups water ",                                                         
        "1 kg chicken drumsticks (2.2 pounds) ",
        "300 g little neck clams (10.6 ounce), rinsed ",
        "400 g kalguksu noodles (or udon noodles, *see above for more options), (14.1 ounce) ) ",
        "1 tsp Korean soup soy sauce or regular kikkoman soy sauce  ",
        "3/4 tsp fine sea salt , or to your taste   ) ",
        "30 g green onion (1 ounce), thinly sliced ",
        "1/4 cup soy sauce , regular (I use kikkoman brand.) ",
        "1 Tbsp Korean chili flakes (gochugaru)   ",
        "1/2 Tbsp honey  ",
        "1 Tbsp green onion , thinly sliced ",
        "1 tsp minced garlic  ",
        "1 tsp sesame oil "
    };
     

    Map<String, Double> chickenNoodleSoupIngredientPrices = new HashMap<>();
    chickenNoodleSoupIngredientPrices.put("1 kg chicken backs (chicken frames)", 363.99);
    chickenNoodleSoupIngredientPrices.put("110 g onion (3.9 ounce) " ,  18.25);
    chickenNoodleSoupIngredientPrices.put("50 g green onion (1.8 ounce), white part", 8.30);
    chickenNoodleSoupIngredientPrices.put("15 g garlic cloves (0.5 ounce)", 3.32);
    chickenNoodleSoupIngredientPrices.put("50 g dried anchovies (1.8 ounce)", 35.96);
    chickenNoodleSoupIngredientPrices.put("1/2 tsp whole black peppers ", 1.86);
    chickenNoodleSoupIngredientPrices.put("1 kg chicken drumsticks (2.2 pounds)", 363.926);    
    chickenNoodleSoupIngredientPrices.put("300 g little neck clams (10.6 ounce), rinsed", 276.56);
    chickenNoodleSoupIngredientPrices.put("400 g kalguksu noodles (or udon noodles, *see above for more options), (14.1 ounce)", 110.64);   
    chickenNoodleSoupIngredientPrices.put("1 tsp Korean soup soy sauce or regular kikkoman soy sauce", 1.66);    
    chickenNoodleSoupIngredientPrices.put("3/4 tsp fine sea salt , or to your taste", 3.30);    
    chickenNoodleSoupIngredientPrices.put("30 g green onion (1 ounce), thinly sliced ",  9.96);    
    chickenNoodleSoupIngredientPrices.put("1/4 cup soy sauce , regular (I use kikkoman brand.) ", 13.83);    
    chickenNoodleSoupIngredientPrices.put("1 Tbsp Korean chili flakes (gochugaru) ", 8.30);   
    chickenNoodleSoupIngredientPrices.put("1 Tbsp green onion , thinly sliced ", 2.21);    
    chickenNoodleSoupIngredientPrices.put("1 tsp minced garlic ", 2.77);  
    chickenNoodleSoupIngredientPrices.put("1 tsp sesame oil ", 22.14);  
    

    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false };
    double[] totalCost = { 0.0 };

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : chickenNoodleSoupIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0;
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = chickenNoodleSoupIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0]));
        });

        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }
    
    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null);
    ingredientsScrollPane.getViewport().setOpaque(false);

    JTextArea chickenNoodleSoupProcedureArea = new JTextArea("Procedure:\n\n"
         + "1. Place the chicken backs in a large pot along with the other soup base ingredients and water. To make the removal process easier later on, you may want to consider using a stock bag, also known as a soup sock, which is typically made of fine mesh or cheesecloth. This bag can hold all the solid soup base ingredients. If your bag isn’t spacious enough to contain all the ingredients, prioritize enclosing the anchovies, garlic, and black pepper. These elements can be more challenging to remove later, so it’s best to ensure they are securely contained within the bag.  \n"
            + "2. Bring this mixture to a boil over medium-high heat, which should take around 25 minutes. Once the water is boiling, carefully add the chicken drumsticks to the pot. Maintain the heat and let them cook for an additional 25 minutes.  \n"
            + "3. Lower the heat to medium-low and let the broth simmer for an additional 30 minutes. Skim off any fat or scum that floats to the surface of the broth to ensure a clear soup. \n"
            + "4. Remove the soup base ingredients from the broth. Set the chicken drumsticks aside in a separate bowl and allow them to cool down. Once cooled, debone and skin the drumsticks, tearing the meat into bite-sized pieces.  \n"
            + "5. In another large pot, add the broth back and bring it to a boil. Then, add little neck clams, Korean soup soy sauce, and season with salt. \n"
            + "6. Rinse the kalguksu noodles under cold running water briefly to remove excess starch. Then, add these noodles to the boiling broth and cook them over medium heat until they are fully cooked, about 3-5 minutes.  \n"
            + "7. Meanwhile, combine the optional seasoning sauce ingredients in a small bowl and set aside. \n"
            + "8. Serve the soup piping hot, garnished with the prepared chicken pieces and green onions. Enjoy the chicken noodle soup as it is, or enhance its flavor by adding the prepared seasoning sauce \n"
            + " Notes \n"
            + " 1 Tbsp = 15 ml, 1 Cup = 250 ml \n"
            + "If you’re interested in learning more about Korean ingredients, check out my essential list of Korean cooking ingredients \n" );

    chickenNoodleSoupProcedureArea.setForeground(Color.decode("#8B0000"));
    chickenNoodleSoupProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    chickenNoodleSoupProcedureArea.setOpaque(false);
    chickenNoodleSoupProcedureArea.setEditable(false);
    chickenNoodleSoupProcedureArea.setWrapStyleWord(true);
    chickenNoodleSoupProcedureArea.setLineWrap(true);

    JScrollPane chickenNoodleSoupProcedureScrollPane = new JScrollPane(chickenNoodleSoupProcedureArea);
    chickenNoodleSoupProcedureScrollPane.setBorder(null);
    chickenNoodleSoupProcedureScrollPane.getViewport().setOpaque(false);

    buyButton.setVisible(false);
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }

        
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );
    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    chickenNoodleSoupPanel.setLayout(new BorderLayout());
    chickenNoodleSoupPanel.add(labelsPanel, BorderLayout.NORTH);
    chickenNoodleSoupPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    chickenNoodleSoupPanel.add(buttonPanel, BorderLayout.EAST);
    chickenNoodleSoupPanel.add(chickenNoodleSoupProcedureScrollPane, BorderLayout.SOUTH);

    return chickenNoodleSoupPanel;
}
   public static JPanel createDonkatsuRecipePanel() {
    JPanel donkatsuPanel = new JPanel(new BorderLayout());

    JLabel donkatsuLabel = new JLabel("DONKATSU");
    donkatsuLabel.setForeground(Color.decode("#8B0000"));
       donkatsuLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
    donkatsuLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
    labelsPanel.add(donkatsuLabel);
    JLabel noteLabel = new JLabel("Check the ingredients you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    labelsPanel.add(noteLabel);

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] donkatsuIngredients = {
        "¼ cup ketchup ",
        "½ cup tonkatsu sauce, bulldog brand preferred ",
        "1 ½ cup water  ",
        "½ cup milk  ",
        "30g / 1 ounce salted butter  ",
        "1 ½ Tbsp plain flour  ",
        "½ Tbsp cornstarch ",                                                         
        "1 Tbsp water",
        "600g / 1.3 pound pork loin (cut thinly for pork cutlet), about 1 cm / 0.4 inch thickness  ",
        "Milk, enough to soak the meat ",
        "3 eggs ",
        "½ cup plain flour",
        "½ tsp garlic powder ",
        "½ tsp onion powder ",
        "1 ½ cup panko bread crumbs ",
        "250g / 9 ounce cabbage, thinly sliced  ",
        "1/3 cup ketchup ",
        "Ssamjang  ",
        "Macaroni salad   ",
        "Cooked rice    ",
        "Pickled cucumber    ",
        "Danmuji (yellow pickled radish)    ",
        "Kimchi ",
        "*1 Tbsp = 15 ml, 1 Cup = 250 ml ",
    };
    Map<String, Double> donkatsuIngredientPrices = new HashMap<>();
    donkatsuIngredientPrices.put("¼ cup ketchup", 4.43);
    donkatsuIngredientPrices.put("½ cup tonkatsu sauce, bulldog brand preferred " , 47.06);
    donkatsuIngredientPrices.put("2 Tbsp brown sugar " , 13.28);
    donkatsuIngredientPrices.put("½ cup milk " , 2.00);
    donkatsuIngredientPrices.put("30g / 1 ounce salted butter " , 9.96);
    donkatsuIngredientPrices.put("1 ½ Tbsp plain flour " , 7.75);
    donkatsuIngredientPrices.put("½ Tbsp cornstarch " , 1.66);    
    donkatsuIngredientPrices.put("600g / 1.3 pound pork loin (cut thinly for pork cutlet), about 1 cm / 0.4 inch thickness " , 508.88);
    donkatsuIngredientPrices.put("Milk, enough to soak the meat " , 13.84);   
    donkatsuIngredientPrices.put("3 eggs " , 27.00);    
    donkatsuIngredientPrices.put("½ cup plain flour " , 41.52);    
    donkatsuIngredientPrices.put("½ tsp garlic powder " , 1.66);    
    donkatsuIngredientPrices.put("½ tsp onion powder" , 13.84);    
    donkatsuIngredientPrices.put("1 ½ cup panko bread crumbs " , 69.20);   
    donkatsuIngredientPrices.put("250g / 9 ounce cabbage, thinly sliced " , 4.98);    
    donkatsuIngredientPrices.put("1/3 cup ketchup " , 19.93);  
    donkatsuIngredientPrices.put("1/3 cup mayonnaise " , 24.91);
    donkatsuIngredientPrices.put("Non-spicy chili (e.g. cucumber chili) " , 50.38 );
    donkatsuIngredientPrices.put("Ssamjang " , 55.36);
    donkatsuIngredientPrices.put("Macaroni salad  " , 120.13);
    donkatsuIngredientPrices.put("Cooked rice " , 12.95);
    donkatsuIngredientPrices.put("Pickled cucumber " , 19.93);
    donkatsuIngredientPrices.put("Danmuji (yellow pickled radish) " , 50.38 );
    donkatsuIngredientPrices.put("Kimchi " , 39.90 ); 
    

    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false };
    double[] totalCost = { 0.0 };

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : donkatsuIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0;
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = donkatsuIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0]));
        });

        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }
    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null);
    ingredientsScrollPane.getViewport().setOpaque(false);

    JTextArea donkatsuProcedureArea = new JTextArea("Procedure:\n\n"
            +"DONKATSU SAUCE"
            + "1.Combine ketchup, tonkatsu sauce, brown sugar, water and milk in a large bowl or measuring cup. Mix well and set aside \n "
            + "2. Preheat a frying pan over medium heat, then add the butter. Once the butter has melted, add the flour to the pan. Stir well to ensure the flour is thoroughly mixed with the butter. Continue to cook over medium to medium-high heat until the mixture turns dark brown; this should take approximately 5-7 minutes, with occasional stirring. Then, reduce the heat to low \n"
            + "3. Pour the brown sauce from step 1 into the pan, stirring well. Increase the heat to medium-low or medium and cook until the sauce thickens, which should take approximately 8-10 minutes; remember to stir often. In a separate bowl, combine the cornstarch and water, whisking until the starch dissolves completely. Once the sauce in the pan has thickened slightly, add the slurry. Continue to stir frequently for an additional 5 minutes, or until the sauce reaches your desired thickness. Once done, either set it aside or reduce the heat to the lowest setting to keep it warm while the pork cutlet is being cooked \n "
            +"PORK CUTLET"
            + "1. Lightly score the meat in a diamond pattern using a knife, or use a mallet to tenderize it. Next, place the meat in a bowl and pour in the milk. Let the meat soak in the milk for 10 minutes. This will help further tenderize the meat and reduce any gamey smell. Afterward, drain the milk away and set the meat aside. \n "
            + "2.Prepare two separate plates; fill one with a mixture of flour, garlic powder, and onion powder, and the other with panko bread crumbs. In a medium-sized bowl, crack the eggs and whisk them until fully combined. Arrange your workstation in the following order: a plate of pork, a plate of the flour mixture, a bowl of whisked eggs, and finally, a plate of panko bread crumbs. \n"
            + "3.Thoroughly coat all sides of the pork with flour, then dip it into the beaten eggs. Finally, roll it in the panko bread crumbs until it’s fully covered. Repeat this process until all the meat is fully breaded. \n "
            + "4.To prepare for frying, fill a fryer or large deep pan with a generous amount of oil and heat it. Make sure the container is wide enough to fully immerse at least one pork cutlet. Once the oil reaches 175°C (350°F), it is ready—this usually takes about five minutes on medium-high heat. \n "
            + "4.To prepare for frying, fill a fryer or large deep pan with a generous amount of oil and heat it. Make sure the container is wide enough to fully immerse at least one pork cutlet. Once the oil reaches 175°C (350°F), it is ready—this usually takes about five minutes on medium-high heat.\n"
            + "5.Carefully place the pork cutlet in the sizzling oil and fry for about four minutes, flipping it every one to two minutes for even cooking. Once the pork cutlet is fully cooked and golden-brown, remove it from the oil and place on paper towels to absorb excess oil. Using a fine-mesh strainer, quickly skim off any remaining fried crumbs from the surface of the oil between batches. Repeat this process until all the cutlets are fried. \n "
            + "6.Serve your chosen sides on a large plate, arranged around the edge. If serving cabbage, combine the ketchup and mayonnaise in a small bowl, then dollop the sauce over the cabbage. Place the cutlet in the center of the plate. Drizzle the sauce over it. Enjoy! \n " 
            );
    donkatsuProcedureArea.setForeground(Color.decode("#8B0000"));
    donkatsuProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    donkatsuProcedureArea.setOpaque(false);
    donkatsuProcedureArea.setEditable(false);
    donkatsuProcedureArea.setWrapStyleWord(true);
    donkatsuProcedureArea.setLineWrap(true);

    JScrollPane donkatsuProcedureScrollPane = new JScrollPane(donkatsuProcedureArea);
    donkatsuProcedureScrollPane.setBorder(null);
    donkatsuProcedureScrollPane.getViewport().setOpaque(false);

    buyButton.setVisible(false);
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );
    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);

        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    donkatsuPanel.setLayout(new BorderLayout());
    donkatsuPanel.add(labelsPanel, BorderLayout.NORTH);
    donkatsuPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    donkatsuPanel.add(buttonPanel, BorderLayout.EAST);
    donkatsuPanel.add(donkatsuProcedureScrollPane, BorderLayout.SOUTH);

    return donkatsuPanel;
}
    public static JPanel createChocolateSouffleRecipePanel() {
    JPanel chocolateSoufflePanel = new JPanel(new BorderLayout());

    JLabel chocolateSouffleLabel = new JLabel("CHOCOLATE SOUFFLE");
    chocolateSouffleLabel.setForeground(Color.decode("#8B0000"));
    chocolateSouffleLabel.setFont(new Font("Calibri", Font.BOLD, 30));
    chocolateSouffleLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
    labelsPanel.add(chocolateSouffleLabel);
    JLabel noteLabel = new JLabel("Check the ingredients you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    labelsPanel.add(noteLabel);

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] chocolateSouffleIngredients = {
        "1/2 c. (1 stick) butter, cut into tablespoons",
        "6 tbsp. granulated sugar - Price",
        "8 oz. semisweet chocolate, roughly chopped",
        "1 tsp. pure vanilla extract",
        "1 tsp. kosher salt - Price",
        "4 large eggs, separated",
        "Powdered sugar, for serving"
    };
    Map<String, Double> chocolateSouffleIngredientPrices = new HashMap<>();
    chocolateSouffleIngredientPrices.put("1/2 c. (1 stick) butter, cut into tablespoons", 15.00);
    chocolateSouffleIngredientPrices.put("6 tbsp. granulated sugar", 30.00);
    chocolateSouffleIngredientPrices.put("8 oz. semisweet chocolate", 75.00);
    chocolateSouffleIngredientPrices.put("1 tsp. pure vanilla extract", 40.00);
    chocolateSouffleIngredientPrices.put("1 tsp. kosher salt", 10.00);
    chocolateSouffleIngredientPrices.put("4 large eggs", 60.00);
    chocolateSouffleIngredientPrices.put("Powdered sugar (for serving)", 50.00);

    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false };
    double[] totalCost = { 0.0 };

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : chocolateSouffleIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0;
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = chocolateSouffleIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0]));
        });

        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }
    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null);
    ingredientsScrollPane.getViewport().setOpaque(false);

    JTextArea chocolateSouffleProcedureArea = new JTextArea("Procedure:\n\n"
            + "1. Preheat oven to 375°. Grease ramekins with butter and coat with sugar.\n"
        + "2. Create a double boiler, melt chocolate and butter. Stir in vanilla and salt. Let cool.\n"
        + "3. Beat egg whites until stiff peaks form. Mix egg yolks into chocolate mixture.\n"
        + "4. Fold in egg whites. Pour batter into ramekins.\n"
        + "5. Bake until risen and slightly wobbly, about 20 minutes. Dust with powdered sugar before serving."
            
    );
    chocolateSouffleProcedureArea.setForeground(Color.decode("#8B0000"));
    chocolateSouffleProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    chocolateSouffleProcedureArea.setOpaque(false);
    chocolateSouffleProcedureArea.setEditable(false);
    chocolateSouffleProcedureArea.setWrapStyleWord(true);
    chocolateSouffleProcedureArea.setLineWrap(true);

    JScrollPane chocolateSouffleProcedureScrollPane = new JScrollPane(chocolateSouffleProcedureArea);
    chocolateSouffleProcedureScrollPane.setBorder(null);
    chocolateSouffleProcedureScrollPane.getViewport().setOpaque(false);

   buyButton.setVisible(false);
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );
    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    chocolateSoufflePanel.setLayout(new BorderLayout());
    chocolateSoufflePanel.add(labelsPanel, BorderLayout.NORTH);
    chocolateSoufflePanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    chocolateSoufflePanel.add(buttonPanel, BorderLayout.EAST);
    chocolateSoufflePanel.add(chocolateSouffleProcedureScrollPane, BorderLayout.SOUTH);

    return chocolateSoufflePanel;
}  
    public static JPanel createFrenchMacaroonRecipePanel() {
    JPanel frenchMacaroonPanel = new JPanel(new BorderLayout());

    JLabel frenchMacaroonLabel = new JLabel("FRENCH MACAROON");
    frenchMacaroonLabel.setForeground(Color.decode("#8B0000"));
    frenchMacaroonLabel.setFont(new Font("Calibri", Font.BOLD, 30));
    frenchMacaroonLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
    labelsPanel.add(frenchMacaroonLabel);
    JLabel noteLabel = new JLabel("Check the ingredients you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    labelsPanel.add(noteLabel);

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] frenchMacaroonIngredients = {
            "1 1/4 c. (120 g.) superfine almond flour",
            "1 c. (115 g.) confectioners' sugar",
            "3 (100 g.) large egg whites",
            "1/4 tsp. cream of tartar",
            "1/2 c. (100 g.) granulated sugar",
            "8 drops pink food coloring",
            "9 tbsp. raspberry preserves"
        };
    Map<String, Double> frenchMacaroonIngredientPrices = new HashMap<>();
    frenchMacaroonIngredientPrices.put("1 1/4 c. (120 g.) superfine almond flour", 55.0);
    frenchMacaroonIngredientPrices.put("1 c. (115 g.) confectioners' sugar", 50.0);
    frenchMacaroonIngredientPrices.put("3 (100 g.) large egg whites", 45.00);
    frenchMacaroonIngredientPrices.put("1/4 tsp. cream of tartar", 15.00);
    frenchMacaroonIngredientPrices.put("1/2 c. (100 g.) granulated sugar", 25.0);
    frenchMacaroonIngredientPrices.put("8 drops pink food coloring", 20.0);
    frenchMacaroonIngredientPrices.put("9 tbsp. raspberry preserves", 35.0);

    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false };
    double[] totalCost = { 0.0 };

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : frenchMacaroonIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0;
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = frenchMacaroonIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0]));
        });

        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }

    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null);
    ingredientsScrollPane.getViewport().setOpaque(false);

    JTextArea frenchMacaroonProcedureArea = new JTextArea("Procedure:\n\n"
            + "1. Line baking sheets with parchment. Sift almond flour and confectioners' sugar.\n"
            + "2. Whip egg whites with cream of tartar and granulated sugar until stiff peaks form.\n"
            + "3. Fold in dry ingredients and food coloring. Pipe circles onto sheets and let rest.\n"
            + "4. Bake until firm, then cool. Fill with raspberry preserves between shells."           
    );

    frenchMacaroonProcedureArea.setForeground(Color.decode("#8B0000"));
    frenchMacaroonProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    frenchMacaroonProcedureArea.setOpaque(false);
    frenchMacaroonProcedureArea.setEditable(false);
    frenchMacaroonProcedureArea.setWrapStyleWord(true);
    frenchMacaroonProcedureArea.setLineWrap(true);

    JScrollPane frenchMacaroonProcedureScrollPane = new JScrollPane(frenchMacaroonProcedureArea);
    frenchMacaroonProcedureScrollPane.setBorder(null);
    frenchMacaroonProcedureScrollPane.getViewport().setOpaque(false);

    buyButton.setVisible(false);
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );
    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        
        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    frenchMacaroonPanel.setLayout(new BorderLayout());
    frenchMacaroonPanel.add(labelsPanel, BorderLayout.NORTH);
    frenchMacaroonPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    frenchMacaroonPanel.add(buttonPanel, BorderLayout.EAST);
    frenchMacaroonPanel.add(frenchMacaroonProcedureScrollPane, BorderLayout.SOUTH);

    return frenchMacaroonPanel;
}
   public static JPanel createChickenDijonRecipePanel() {
    JPanel chickenDijonPanel = new JPanel(new BorderLayout());

    JLabel chickenDijonLabel = new JLabel("CHICKEN DIJON");
    chickenDijonLabel.setForeground(Color.decode("#8B0000"));
    chickenDijonLabel.setFont(new Font("Calibri", Font.BOLD, 30));
    chickenDijonLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1));
    labelsPanel.add(chickenDijonLabel);
    JLabel noteLabel = new JLabel("Check the ingredients you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    labelsPanel.add(noteLabel);

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] chickenDijonIngredients = {
        "1 teaspoon coriander seeds",
        "2 tablespoons extra-virgin olive oil",
        "8 medium chicken drumsticks",
        "Salt and freshly ground pepper",
        "1/4 cup finely chopped onion",
        "4 garlic cloves, minced",
        "1 1/2 cups low-sodium chicken broth",
        "2 tablespoons whole-grain mustard",
        "3 tablespoons crème fraîche or sour cream",
        "2 teaspoons chopped tarragon",
        "Crusty bread (for serving)"
};
    Map<String, Double> chickenDijonIngredientPrices = new HashMap<>();
    chickenDijonIngredientPrices.put("1 teaspoon coriander seeds", 15.00);
    chickenDijonIngredientPrices.put("2 tablespoons extra-virgin olive oil", 10.00);
    chickenDijonIngredientPrices.put("8 medium chicken drumsticks", 150.00);
    chickenDijonIngredientPrices.put("Salt and freshly ground pepper", 25.00);
    chickenDijonIngredientPrices.put("1/4 cup finely chopped onion", 25.00);
    chickenDijonIngredientPrices.put("4 garlic cloves, minced", 8.00);
    chickenDijonIngredientPrices.put("1 1/2 cups low-sodium chicken broth", 20.00);
    chickenDijonIngredientPrices.put("2 tablespoons whole-grain mustard", 15.00);
    chickenDijonIngredientPrices.put("3 tablespoons crème fraîche or sour cream", 25.00);
    chickenDijonIngredientPrices.put("2 teaspoons chopped tarragon", 15.00);
    chickenDijonIngredientPrices.put("Crusty bread (for serving)", 35.00);

    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false };
    double[] totalCost = { 0.0 };

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false);

    for (String ingredient : chickenDijonIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0;
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = chickenDijonIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0]));
        });
        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }
    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null);
    ingredientsScrollPane.getViewport().setOpaque(false);

    JTextArea chickenDijonProcedureArea = new JTextArea("Procedure:\n\n"
            + "1. Toast coriander seeds, then crush in a mortar. Brown chicken in oil.\n"
            + "2. Add onion and garlic. Pour in chicken broth and coriander. Simmer until cooked.\n"
            + "3. Whisk mustard with crème fraîche and tarragon. Simmer in skillet. Serve with bread."
    );
    chickenDijonProcedureArea.setForeground(Color.decode("#8B0000"));
    chickenDijonProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    chickenDijonProcedureArea.setOpaque(false);
    chickenDijonProcedureArea.setEditable(false);
    chickenDijonProcedureArea.setWrapStyleWord(true);
    chickenDijonProcedureArea.setLineWrap(true);

    JScrollPane chickenDijonProcedureScrollPane = new JScrollPane(chickenDijonProcedureArea);
    chickenDijonProcedureScrollPane.setBorder(null);
    chickenDijonProcedureScrollPane.getViewport().setOpaque(false);

    buyButton.setVisible(false);
    buyButton.addActionListener(e -> {
        if (totalCost[0] > 0.0) {

            StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        checkedIngredients.append("- ").append(cb.getText()).append("\n");
                    }
                }
            }
            JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
            JPanel ingredientsListPanel = new JPanel(new BorderLayout());

            JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
            checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
            checkedIngredientsArea.setEditable(false);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton confirmButton = new JButton("Confirm Purchase");
            JButton cancelButton = new JButton("Cancel");

            confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );
    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});
            cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose());

            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
            ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);

            buyIngredientsFrame.add(ingredientsListPanel);
            buyIngredientsFrame.pack();
            buyIngredientsFrame.setLocationRelativeTo(null);
            buyIngredientsFrame.setVisible(true);
        }
    });
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    chickenDijonPanel.setLayout(new BorderLayout());
    chickenDijonPanel.add(labelsPanel, BorderLayout.NORTH);
    chickenDijonPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    chickenDijonPanel.add(buttonPanel, BorderLayout.EAST);
    chickenDijonPanel.add(chickenDijonProcedureScrollPane, BorderLayout.SOUTH);

    return chickenDijonPanel;
}
      public static JPanel createChickenConfitRecipePanel() {
    JPanel chickenConfitPanel = new JPanel(new BorderLayout());

    JLabel chickenConfitLabel = new JLabel("CHICKEN CONFIT");
    chickenConfitLabel.setForeground(Color.decode("#8B0000"));
    chickenConfitLabel.setFont(new Font("Calibri", Font.BOLD, 30));
    chickenConfitLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel labelsPanel = new JPanel(new GridLayout(2, 1)); 
    labelsPanel.add(chickenConfitLabel); 
    JLabel noteLabel = new JLabel("Check the ingredients you don't have");
    noteLabel.setForeground(Color.decode("#8B0000"));
    noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
    noteLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
    labelsPanel.add(noteLabel); 

    JPanel ingredientsPanel = new JPanel(new GridBagLayout());
    ingredientsPanel.setOpaque(false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 10, 25, 10);
    gbc.gridy = 0;

    String[] chickenConfitIngredients = {
        "4 chicken leg portions with thighs attached",
        "1 tablespoon plus 1/8 teaspoon kosher salt",
        "½ teaspoon freshly ground black pepper",
        "10 garlic cloves",
        "4 bay leaves",
        "4 sprigs fresh thyme",
        "1 ½ teaspoons black peppercorns",
        "½ teaspoon table salt",
        "4 cups olive oil"
};
    Map<String, Double> chickenConfitIngredientPrices = new HashMap<>();
    chickenConfitIngredientPrices.put("4 chicken leg portions with thighs attached", 150.00);
    chickenConfitIngredientPrices.put("1 tablespoon plus 1/8 teaspoon kosher salt", 15.00);
    chickenConfitIngredientPrices.put("½ teaspoon freshly ground black pepper", 10.00);
    chickenConfitIngredientPrices.put("10 garlic cloves", 40.00);
    chickenConfitIngredientPrices.put("4 bay leaves", 10.00);
    chickenConfitIngredientPrices.put("4 sprigs fresh thyme", 15.00);
    chickenConfitIngredientPrices.put("1 ½ teaspoons black peppercorns", 5.00);
    chickenConfitIngredientPrices.put("½ teaspoon table salt", 5.00);
    chickenConfitIngredientPrices.put("4 cups olive oil", 20.00);

    int numColumns = 2;
    int checkboxCount = 0;
    boolean[] isChecked = { false }; 
    double[] totalCost = { 0.0 }; 

    JButton buyButton = new JButton("Buy");
    buyButton.setBackground(Color.decode("#8B0000"));
    buyButton.setForeground(Color.WHITE);
    buyButton.setVisible(false); 

    for (String ingredient : chickenConfitIngredients) {
        JCheckBox ingredientCheckbox = new JCheckBox(ingredient);
        ingredientCheckbox.setForeground(Color.decode("#8B0000"));
        ingredientCheckbox.setOpaque(false);

        ingredientCheckbox.addActionListener(e -> {
            boolean atLeastOneChecked = false;
            totalCost[0] = 0.0;
            for (Component component : ingredientsPanel.getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox cb = (JCheckBox) component;
                    if (cb.isSelected()) {
                        atLeastOneChecked = true;
                        String text = cb.getText();
                        double price = chickenConfitIngredientPrices.getOrDefault(text, 0.0);
                        totalCost[0] += price;
                    }
                }
            }
            buyButton.setVisible(atLeastOneChecked);
            buyButton.setText("Buy - Total: ₱" + String.format("%.2f", totalCost[0]));
        });

        gbc.gridx = checkboxCount % numColumns;
        ingredientsPanel.add(ingredientCheckbox, gbc);

        if (checkboxCount % numColumns == numColumns - 1) {
            gbc.gridy++;
        }

        checkboxCount++;
    }
    JScrollPane ingredientsScrollPane = new JScrollPane(ingredientsPanel);
    ingredientsScrollPane.setBorder(null);
    ingredientsScrollPane.getViewport().setOpaque(false);
    
    JTextArea chickenConfitProcedureArea = new JTextArea("Procedure:\n\n"
        + "1. Season chicken legs with kosher salt and black pepper, layer with garlic, bay leaves, and thyme. Refrigerate for 12 hours.\n"
        + "2. Rinse chicken and pat dry. Place garlic, bay leaves, thyme, and reserved chicken fat in a pot.\n"
        + "3. Place chicken on top, cover with olive oil. Bake at 200°F for 12 to 14 hours until tender.\n"
        + "4. Remove chicken from fat, strain and reserve fat. Pick meat from bones, store in a stoneware container, cover with reserved fat.\n"
        + "5. Refrigerate chicken confit for up to 1 month. Use excess oil for cooking potatoes, green beans, or veal.");
        
    chickenConfitProcedureArea.setForeground(Color.decode("#8B0000"));
    chickenConfitProcedureArea.setFont(new Font("Arial", Font.PLAIN, 14));
    chickenConfitProcedureArea.setOpaque(false);
    chickenConfitProcedureArea.setEditable(false);
    chickenConfitProcedureArea.setWrapStyleWord(true);
    chickenConfitProcedureArea.setLineWrap(true);

    JScrollPane chickenConfitProcedureScrollPane = new JScrollPane(chickenConfitProcedureArea);
    chickenConfitProcedureScrollPane.setBorder(null);
    chickenConfitProcedureScrollPane.getViewport().setOpaque(false);

    buyButton.setVisible(false);
     buyButton.addActionListener(e -> {
    if (totalCost[0] > 0.0) {
        
        StringBuilder checkedIngredients = new StringBuilder("Selected Ingredients:\n");
        for (Component component : ingredientsPanel.getComponents()) {
            if (component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if (cb.isSelected()) {
                    checkedIngredients.append("- ").append(cb.getText()).append("\n");
                }
            }
        }
        JFrame buyIngredientsFrame = new JFrame("Checked Ingredients");
        JPanel ingredientsListPanel = new JPanel(new BorderLayout());

        JTextArea checkedIngredientsArea = new JTextArea(checkedIngredients.toString());
        checkedIngredientsArea.setFont(new Font("Arial", Font.PLAIN, 14));
        checkedIngredientsArea.setEditable(false);

        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm Purchase");
        JButton cancelButton = new JButton("Cancel");
        
        
        confirmButton.addActionListener(actionEvent -> {

    JPanel confirmationPanel = new JPanel(new BorderLayout());

    JLabel totalCostLabel = new JLabel("Total: ₱" + String.format("%.2f", totalCost[0]));
    totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
    totalCostLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JLabel paymentNoteLabel = new JLabel("You can pay upon delivery");
    paymentNoteLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    paymentNoteLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JTextArea purchasedItemsArea = new JTextArea(checkedIngredients.toString());
    purchasedItemsArea.setFont(new Font("Arial", Font.PLAIN, 14));
    purchasedItemsArea.setEditable(false);

    JScrollPane scrollPane = new JScrollPane(purchasedItemsArea);

    confirmationPanel.add(totalCostLabel, BorderLayout.NORTH);
    confirmationPanel.add(scrollPane, BorderLayout.CENTER);
    confirmationPanel.add(paymentNoteLabel, BorderLayout.SOUTH);

    int optionChosen = JOptionPane.showConfirmDialog(
            buyIngredientsFrame,
            confirmationPanel,
            "Payment Confirmation",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE
    );
    if (optionChosen == JOptionPane.OK_OPTION) {
        JFrame thankYouFrame = new JFrame("Thank You");
        JLabel thankYouLabel = new JLabel("Thank you for your purchase!");
        thankYouLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thankYouFrame.add(thankYouLabel);
        thankYouFrame.setSize(300, 100);
        thankYouFrame.setLocationRelativeTo(buyIngredientsFrame);
        thankYouFrame.setVisible(true);
        buyIngredientsFrame.dispose();
    }
});
        cancelButton.addActionListener(actionEvent -> buyIngredientsFrame.dispose()); 
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        ingredientsListPanel.add(checkedIngredientsArea, BorderLayout.CENTER);
        ingredientsListPanel.add(buttonPanel, BorderLayout.SOUTH);

        buyIngredientsFrame.add(ingredientsListPanel);
        buyIngredientsFrame.pack();
        buyIngredientsFrame.setLocationRelativeTo(null); 
        buyIngredientsFrame.setVisible(true);
    }
});
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(buyButton);
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 0));

    chickenConfitPanel.setLayout(new BorderLayout());
    chickenConfitPanel.add(labelsPanel, BorderLayout.NORTH);
    chickenConfitPanel.add(ingredientsScrollPane, BorderLayout.CENTER);
    chickenConfitPanel.add(buttonPanel, BorderLayout.EAST);
    chickenConfitPanel.add(chickenConfitProcedureScrollPane, BorderLayout.SOUTH);

    return chickenConfitPanel;
}
}