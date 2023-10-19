package gui;

import basics.Artist;
import basics.BaseEntity;
import basics.Release;
import db.Database;
import files.APIWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class MainWindow extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton importButton;
    private JComboBox importTypeCBox;
    private JTextField importTextField;
    private JTextField searchKeywordField;
    private JButton searchButton;
    private JList searchResultList;
    private JComboBox typeCombo;
    private Database db;

    public MainWindow(Database db) {
        this.db = db;
        getContentPane().add(tabbedPane1);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 700);
        importButton.addActionListener(e -> importPressed());
        importTextField.addActionListener(e -> importPressed());

        searchButton.addActionListener(e -> searchPressed());
        searchKeywordField.addActionListener(e -> searchPressed());

        JFrame window = this;
        searchResultList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 2) {
                    BaseEntity entity = getEntity((BaseEntity) list.getSelectedValue());
                    if (entity instanceof Artist) {
                        new ArtistView((Artist) entity).setVisible(true);
                    } else if (entity instanceof Release) {
                        new ReleaseView((Release) entity).setVisible(true);
                    }
                }
            }
        });
        typeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchResultList.setListData(new String[0]);
            }
        });
    }

    private BaseEntity getEntity(BaseEntity entity) {
        if (!entity.isFullyRetrieved()) {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Object is incomplete, do a full retrieval from Musicbrainz?", "Incomplete",
                    JOptionPane.YES_NO_OPTION);

            if (choice != 0) {
                // rejected
                return entity;
            }

            try {
                db.detach(entity);
                if (entity instanceof Artist) {
                    entity = APIWrapper.getArtistFromId(entity.getId());
                } else if (entity instanceof Release) {
                    entity = APIWrapper.getReleaseFromId(entity.getId());
                }
                db.save(entity);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        return entity;
    }

    private void searchPressed() {
        String keyword = searchKeywordField.getText();
        String type = typeCombo.getSelectedItem().toString();

        List results = null;
        switch (type) {
            case "Artist":
                results = db.searchArtistsByName(keyword);
                break;
            case "Release":
                results = db.searchReleasesByTitle(keyword);
                break;
        }

        if ((results != null && !results.isEmpty()) || keyword.equals("")) {
            searchResultList.setListData(results.toArray());
        } else {
            int choice = JOptionPane.showConfirmDialog(this, "No results found, attempt to import from Musicbrainz",
                    "No results",
                    JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                importItems(keyword, type);
                searchPressed();
            }
        }
    }

    private void importItems(String keyword, String type) {
        List results = null;
        try {
            if (type.equals("Artist")) {
                results = APIWrapper.getArtists(keyword);
            } else if (type.equals("Release")) {
                results = APIWrapper.getReleases(keyword);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            e.printStackTrace();
        }

        if (results == null || results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No results found");
            return;
        }

        ResultDialog resultDialog = new ResultDialog(results, db);
        resultDialog.setLocationRelativeTo(this);
        resultDialog.setVisible(true);
    }

    private void importPressed() {
        importItems(importTextField.getText(), importTypeCBox.getSelectedItem().toString());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new CardLayout(0, 0));
        tabbedPane1 = new JTabbedPane();
        panel1.add(tabbedPane1, "Card1");
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Import", panel2);
        importButton = new JButton();
        importButton.setText("Import");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel2.add(importButton, gbc);
        importTypeCBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Artist");
        defaultComboBoxModel1.addElement("Release");
        importTypeCBox.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(importTypeCBox, gbc);
        importTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(importTextField, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Keyword");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        panel2.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 10;
        panel2.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 10;
        panel2.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        panel2.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        panel2.add(spacer5, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Search", panel3);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer6, gbc);
        searchKeywordField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(searchKeywordField, gbc);
        searchButton = new JButton();
        searchButton.setText("Search");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(searchButton, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(spacer7, gbc);
        typeCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Artist");
        defaultComboBoxModel2.addElement("Release");
        typeCombo.setModel(defaultComboBoxModel2);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel3.add(typeCombo, gbc);
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(scrollPane1, gbc);
        searchResultList = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        searchResultList.setModel(defaultListModel1);
        scrollPane1.setViewportView(searchResultList);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
