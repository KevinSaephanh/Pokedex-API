package com.company.Display;

import javax.swing.*;
import java.awt.*;

public class PokedexFrame extends JFrame {
    private PokemonSearchPanel searchPanel;
    private PokemonDataPanel infoPanel;

    public PokedexFrame() {
        super("Pokedex");
        init();

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void init() {
        searchPanel = new PokemonSearchPanel();
        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(searchPanel, BorderLayout.NORTH);

        infoPanel = new PokemonDataPanel();
        add(infoPanel, BorderLayout.CENTER);
    }
}