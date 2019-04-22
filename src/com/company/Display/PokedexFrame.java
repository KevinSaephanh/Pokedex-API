package com.company.Display;

import com.company.DataStorage.PokeAPIConnection;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PokedexFrame extends JFrame {
    private JPanel searchPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JLabel titleLabel;

    private ImageIcon titleIcon;
    private ImageIcon searchIcon;
    private Box hBox;

    private PokemonDataPanel infoPanel;

    public PokedexFrame() {
        super("Pokedex");
        init();
        setSearchLabels();

        add(searchPanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //Initialize panels
    private void init() {
        searchPanel = new JPanel();
        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.setBackground(new Color(250, 70, 70));
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setPreferredSize(new Dimension(500, 150));

        infoPanel = new PokemonDataPanel();
        infoPanel.setDefaultLabels();
    }

    private void setSearchLabels() {
        //Create and resize title image
        titleIcon = new ImageIcon("images\\Pokedex.png");
        Image image1 = titleIcon.getImage();
        Image newImg1 = image1.getScaledInstance(300, 100, Image.SCALE_SMOOTH);
        titleIcon = new ImageIcon(newImg1);

        titleLabel = new JLabel(titleIcon);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(titleLabel, BorderLayout.NORTH);

        searchField = new JTextField(30);
        searchField.setMaximumSize(new Dimension(350, 30));
        searchField.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        //Add key listener for ENTER key
        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        infoPanel.displayPokemonData(new PokeAPIConnection().getPokemonData(searchField.getText()));
                        searchField.setText("");
                    } catch(JSONException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Pokemon input");
                    }
                }
            }
        });

        //Icon made by Egor Rumyantsev from www.flaticon.com
        //Create and resize the image searchIcon
        searchIcon = new ImageIcon("images\\searchIcon.png");
        Image image2 = searchIcon.getImage();
        Image newImg2 = image2.getScaledInstance(30, 25, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(newImg2);

        //Add image searchIcon to search button
        searchButton = new JButton(searchIcon);
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    infoPanel.displayPokemonData(new PokeAPIConnection().getPokemonData(searchField.getText()));
                    searchField.setText("");
                } catch(JSONException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Pokemon input");
                }
            }
        });

        //Create horizontal alignment of search bar and search label
        hBox = Box.createHorizontalBox();
        hBox.add(searchField);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(searchButton);
        searchPanel.add(hBox, BorderLayout.CENTER);
    }
}