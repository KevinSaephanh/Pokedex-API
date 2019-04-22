package com.company.Display;

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

    //Constructor
    public PokemonDataPanel() {
        initLabels();
        displayUI();

        setBackground(new Color(250, 70, 70));
    }

    //Initialize jlabels and set font
    private void initLabels() {
        font = new Font("Times New Roman", Font.PLAIN, 24);

        name = new JLabel();
        name.setFont(new Font("Times New Roman", Font.BOLD, 30));

        num = new JLabel();
        num.setFont(font);

        type = new JLabel();
        type.setFont(font);

        height = new JLabel();
        height.setFont(font);

        weight = new JLabel();
        weight.setFont(font);

        ability = new JLabel();
        ability.setFont(font);

        weakness = new JLabel();
        weakness.setFont(font);

        image = new JLabel();
    }

    //Set inital default jlabel texts
    public void setDefaultLabels() {
        name.setText("MissingNo.");
        num.setText("#???");
        type.setText("Type: ???");
        height.setText("Height: ???");
        weight.setText("Weight: ???");
        ability.setText("Ability: ???");
        weakness.setText("Weakness: ???");

        try {
            URL url = new URL("https://vignette.wikia.nocookie.net/pokemon/images/d/d3/Missingno.jpg/revision/latest?cb=20070813234243");

            Image img = ImageIO.read(url);
            img = img.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            image.setIcon(new ImageIcon(img));
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(Exception ex) {
            System.err.println("Caught error: " + ex);
        }
    }

    public void displayPokemonData(String pokemon) {
        pokeData = new PokemonData(pokemon);

        //Set new labels to pokemon data
        name.setText(pokeData.getName());
        num.setText("#" + pokeData.getNum());
        type.setText("Type: " + pokeData.getType());
        height.setText("Height: " + pokeData.getHeight());
        weight.setText("Weight: " + pokeData.getWeight());
        ability.setText("Ability: " + pokeData.getAbility());
        weakness.setText("Weakness: " + pokeData.getWeakness());

        //Add pokemon image
        try {
            URL url = new URL(pokeData.getImage());

            Image img = ImageIO.read(url);
            img = img.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            image.setIcon(new ImageIcon(img));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Caught error: " + ex);
        }
    }

    //Initialize and set panels
    private void displayUI() {
        //Image panel attributes
        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(250, 70, 70));
        imagePanel.setPreferredSize(new Dimension(300, 250));
        imagePanel.add(image);
        add(imagePanel, BorderLayout.WEST);

        //Data panel attributes
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
}
