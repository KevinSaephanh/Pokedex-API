package com.company.Display;

import com.company.DataStorage.PokeAPIConnection;
import com.company.DataStorage.PokemonData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PokemonSearchPanel extends JPanel {
    private JTextField searchField;
    private JLabel searchLabel;
    private JLabel titleLabel;

    private ImageIcon icon;
    private Box hBox;

    public PokemonSearchPanel() {
        init();

        setBackground(new Color(250, 70, 70));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 150));
    }

    private void init() {
        titleLabel = new JLabel("Pokedex");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 50));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel, BorderLayout.NORTH);

        searchField = new JTextField(25);
        searchField.setMaximumSize(new Dimension(350, 30));
        searchField.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        //Add key listener for enter key
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    new PokemonDataPanel().setPokemonData(new PokeAPIConnection().getPokemonData(searchField.getText()));
                    searchField.setText("");
                }
            }
        });

        //Icon made by Egor Rumyantsev from www.flaticon.com
        //Create and resize the image icon
        icon = new ImageIcon("images\\searchIcon.png");
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(50, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);

        //Add image icon to search label
        searchLabel = new JLabel(icon);
        searchLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PokemonDataPanel().setPokemonData(new PokeAPIConnection().getPokemonData(searchField.getText()));
                searchField.setText("");
            }
        });

        //Create horizontal alignment of search bar and search label
        hBox = Box.createHorizontalBox();
        hBox.add(searchField);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(searchLabel);
        add(hBox, BorderLayout.CENTER);
    }
}
