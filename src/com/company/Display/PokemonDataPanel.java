package com.company.Display;

import com.company.DataStorage.PokeAPIConnection;
import com.company.DataStorage.PokemonData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class PokemonDataPanel extends JPanel {
    //Pokemon data labels
    private JLabel name;
    private JLabel num;
    private JLabel type;
    private JLabel height;
    private JLabel weight;
    private JLabel ability;
    private JLabel weakness;
    private JLabel image;
    private JPanel imagePanel;
    private JPanel dataPanel;

    private Font font;

    private PokemonData pokeData;

    //Default constructor
    public PokemonDataPanel() {
        font = new Font("Times New Roman", Font.PLAIN, 24);

        setDefaultLabels();
        createUI();

        setBackground(new Color(250, 70, 70));
    }

    private void setDefaultLabels() {
        name = new JLabel("Name");
        name.setFont(new Font("Times New Roman", Font.BOLD, 30));

        num = new JLabel("ID");
        num.setFont(font);

        type = new JLabel("Type");
        type.setFont(font);

        height = new JLabel("Height");
        height.setFont(font);

        weight = new JLabel("Weight");
        weight.setFont(font);

        ability = new JLabel("Ability");
        ability.setFont(font);

        weakness = new JLabel("Weakness");
        weakness.setFont(font);

        image = new JLabel();

        try {
            URL url = new URL("https://vignette.wikia.nocookie.net/pokemon/images/d/d3/Missingno.jpg/revision/latest?cb=20070813234243");

            Image img = ImageIO.read(url);
            image.setIcon(new ImageIcon(img));
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(Exception ex) {
            System.err.println("Caught error: " + ex);
        }
    }

    public void setPokemonData(String pokemon) {
        clearDisplay();

        pokeData = new PokemonData(pokemon);

        name.setText(pokeData.getName());
        num.setText(String.valueOf(pokeData.getNum()));
        type.setText(pokeData.getType());
        height.setText(pokeData.getHeight());
        weight.setText(pokeData.getWeight());
        ability.setText(pokeData.getAbility());
        weakness.setText(pokeData.getWeakness());

        try {
            URL url = new URL(pokeData.getImage());

            Image img = ImageIO.read(url);
            image.setIcon(new ImageIcon(img));
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(Exception ex) {
            System.err.println("Caught error: " + ex);
        }

        createUI();
    }

    private void createUI() {
        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(250, 70, 70));
        imagePanel.setPreferredSize(new Dimension(300, 250));
        imagePanel.add(image);
        add(imagePanel, BorderLayout.WEST);

        dataPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        dataPanel.setPreferredSize(new Dimension(500, 400));
        dataPanel.setBackground(new Color(250, 70, 70));
        dataPanel.add(name);
        dataPanel.add(num);
        dataPanel.add(height);
        dataPanel.add(weight);
        dataPanel.add(ability);
        dataPanel.add(weakness);
        add(dataPanel, BorderLayout.EAST);
    }

    private void clearDisplay() {
        name.setText("");
        num.setText("");
        type.setText("");
        height.setText("");
        weight.setText("");
        ability.setText("");
        weakness.setText("");
        image.removeAll();

        validate();
        repaint();
    }
}
