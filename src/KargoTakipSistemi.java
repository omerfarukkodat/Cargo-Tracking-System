import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class KargoTakipSistemi extends JFrame {
    private JTextField takipNumarasiField, adField, adresField, cikisIlField, varisIlField;
    private DefaultListModel<String> kargoListModel;
    private JList<String> kargoListesiView;
    private LinkedList<Kargo> kargoListesi;
    private TreeSet<Kargo> kargoTreeSet;

    public KargoTakipSistemi() {
        setTitle("Kargo Takip Sistemi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Kargo Takip Numarası
        JLabel takipNumarasiLabel = new JLabel("Takip Numarası:");
        add(takipNumarasiLabel);

        takipNumarasiField = new JTextField(15);
        add(takipNumarasiField);

        // Ad
        JLabel adLabel = new JLabel("Ad:");
        add(adLabel);

        adField = new JTextField(15);
        add(adField);

        // Adres
        JLabel adresLabel = new JLabel("Adres:");
        add(adresLabel);

        adresField = new JTextField(15);
        add(adresField);

        // Çıkış İli
        JLabel cikisIlLabel = new JLabel("Çıkış İli:");
        add(cikisIlLabel);

        cikisIlField = new JTextField(15);
        add(cikisIlField);

        // Varış İli
        JLabel varisIlLabel = new JLabel("Varış İli:");
        add(varisIlLabel);

        varisIlField = new JTextField(15);
        add(varisIlField);

        // Ekle Butonu
        JButton ekleButton = new JButton("Ekle");
        ekleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String takipNumarasi = takipNumarasiField.getText();
                String ad = adField.getText();
                String adres = adresField.getText();
                String cikisIl = cikisIlField.getText();
                String varisIl = varisIlField.getText();

                String[] durumlar = {"Yolda", "Teslim Edildi", "Dağıtımda"};
                Random random = new Random();
                String durum = durumlar[random.nextInt(durumlar.length)];

                Kargo yeniKargo = new Kargo(takipNumarasi, ad, adres, cikisIl, varisIl, durum);
                kargoListesi.add(yeniKargo);
                kargoTreeSet.add(yeniKargo);
                kargoListModel.addElement(takipNumarasi);

                JOptionPane.showMessageDialog(null, "Kargo eklendi.");
                temizle();
            }
        });
        add(ekleButton);

        // Ara Butonu
        JButton araButton = new JButton("Ara");
        araButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String takipNumarasi = takipNumarasiField.getText();
                Kargo bulunanKargo = null;
                for (Kargo kargo : kargoListesi) {
                    if (kargo.getTakipNumarasi().equals(takipNumarasi)) {
                        bulunanKargo = kargo;
                        break;
                    }
                }

                if (bulunanKargo != null) {
                    JOptionPane.showMessageDialog(null, "Kargo Bulundu:\n" +
                            "Ad: " + bulunanKargo.getAd() +
                            "\nAdres: " + bulunanKargo.getAdres());
                } else {
                    JOptionPane.showMessageDialog(null, "Kargo bulunamadı.");
                }
            }
        });
        add(araButton);

        // Kargoları Göster Butonu
        JButton kargolariGosterButton = new JButton("Kargoları Göster");
        kargolariGosterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (kargoListesi.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Kargo bulunmamaktadır.");
                } else {
                    StringBuilder kargolar = new StringBuilder();
                    for (Kargo kargo : kargoListesi) {
                        kargolar.append("Takip Numarası: ").append(kargo.getTakipNumarasi()).append("\n");
                        kargolar.append("Ad: ").append(kargo.getAd()).append("\n");
                        kargolar.append("Adres: ").append(kargo.getAdres()).append("\n");
                        kargolar.append("Çıkış İli: ").append(kargo.getCikisIl()).append("\n");
                        kargolar.append("Varış İli: ").append(kargo.getVarisIl()).append("\n");
                        kargolar.append("Durum: ").append(kargo.getDurum()).append("\n");
                        kargolar.append("------------------------------\n");
                    }
                    JOptionPane.showMessageDialog(null, kargolar.toString());
                }
            }
        });
        add(kargolariGosterButton);

        // Sil Butonu
        JButton silButton = new JButton("Sil");
        silButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = kargoListesiView.getSelectedIndex();
                if (selectedIndex != -1) {
                    String takipNumarasi = kargoListModel.getElementAt(selectedIndex);
                    Iterator<Kargo> iterator = kargoListesi.iterator();
                    while (iterator.hasNext()) {
                        Kargo kargo = iterator.next();
                        if (kargo.getTakipNumarasi().equals(takipNumarasi)) {
                            iterator.remove();
                            kargoListModel.remove(selectedIndex);
                            kargoTreeSet.remove(kargo);
                            JOptionPane.showMessageDialog(null, "Kargo silindi.");
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Lütfen silmek için bir kargo seçin.");
                }
            }
        });
        add(silButton);

        // Kargo Listesi
        kargoListesi = new LinkedList<>();
        kargoTreeSet = new TreeSet<>();
        kargoListModel = new DefaultListModel<>();
        kargoListesiView = new JList<>(kargoListModel);
        JScrollPane scrollPane = new JScrollPane(kargoListesiView);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        add(scrollPane);

        pack();
        setVisible(true);
    }

    private void temizle() {
        takipNumarasiField.setText("");
        adField.setText("");
        adresField.setText("");
        cikisIlField.setText("");
        varisIlField.setText("");
    }

}

