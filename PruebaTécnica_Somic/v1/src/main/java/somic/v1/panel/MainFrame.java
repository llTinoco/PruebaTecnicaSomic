package somic.v1.panel;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import somic.v1.panel.nitPanel.SearchPanel;

@Component
public class MainFrame extends JFrame {

    // Inyectamos el panel combinado para búsquedas
    public MainFrame(SearchPanel searchPanel) {
        initUI(searchPanel);
    }

    private void initUI(SearchPanel searchPanel) {
        setTitle("Gestión de Procesos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        add(searchPanel, BorderLayout.CENTER);
    }
}