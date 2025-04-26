/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package uiproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author drons
 */
public class FormListTickets extends javax.swing.JFrame {

    Socket s;
    ArrayList<Tiket> listTiket = new ArrayList<>();
    private int iterator = 0; //ini buat nandain nomor column yg bakal dikirim
    /**
     * Creates new form FormListTickets
     */
    public FormListTickets() throws IOException {
        try {
            initComponents();
             TableActionEvent event = new TableActionEvent() {
        @Override
        public void onDetail(int row) {
            try{
                System.out.println("onDetail triggered with row: " + row);
                // Ambil data dari tabel
                String closeDate = tabelListTiket.getValueAt(row, 0).toString();
                String eventCreator = tabelListTiket.getValueAt(row, 1).toString();
                String eventName = tabelListTiket.getValueAt(row, 2).toString();
                String location = tabelListTiket.getValueAt(row, 3).toString();
                String regPrice =  tabelListTiket.getValueAt(row, 5).toString();
                String vipPrice = tabelListTiket.getValueAt(row, 6).toString();
                String stock = tabelListTiket.getValueAt(row, 7).toString();
                // Buat dan tampilkan form detail
                FormTicketDetail detailForm = new FormTicketDetail(closeDate, eventCreator, eventName, location, regPrice, vipPrice, stock);
                detailForm.setVisible(true);
                detailForm.setLocationRelativeTo(null); // agar form muncul di tengah
                Tiket tiket = listTiket.get(row); // Kalau mau ambil objek Tiket lengkap
                new FormTicketDetail(closeDate, eventCreator, eventName, location, regPrice, vipPrice, stock).setVisible(true); // Kirim ke form
                System.out.println("Tiket : " + tiket.getEventName());
            }
            catch (Exception e){
                e.printStackTrace();
            }
            
        }
    };
             // Set renderer dan editor SEKALI SAJA sebelum looping
         tabelListTiket.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
            tabelListTiket.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event)); // kirim event di sini
            usernameProfile1.setText(FormLogin.user);
            s = new Socket("localhost", 6000);

            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeBytes("GET_LIST_TICKETS\n");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String receivedData = in.readLine();
            System.out.println(receivedData);
            ArrayList<Tiket> parsedList = parseStringToList(receivedData);
            loadData(parsedList);
            tampilkanKeTabel();

            this.setLocationRelativeTo(null);
            this.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal inisialisasi form: " + e.getMessage());
        }
    }

    private void loadData(ArrayList<Tiket> data) {
        listTiket.clear();
        listTiket.addAll(data);
    }

    private ArrayList<Tiket> parseStringToList(String data) {
        ArrayList<Tiket> result = new ArrayList<>();

        String[] tiketStrings = data.split("#"); // pisah antar tiket

        for (String tiketStr : tiketStrings) {
            if (!tiketStr.trim().isEmpty()) {
                String[] fields = tiketStr.split("\\|");

                LocalDateTime closeDate = Instant.ofEpochMilli(Long.parseLong(fields[0]))
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();

                String creatorName = fields[1];
                String eventName = fields[2];
                String location = fields[3];

                LocalDate eventDate = Instant.ofEpochMilli(Long.parseLong(fields[4]))
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                double regPrice = Double.parseDouble(fields[5]);
                double vipPrice = Double.parseDouble(fields[6]);
                int stock = Integer.parseInt(fields[7]);

                Tiket t = new Tiket(creatorName, eventName, location, eventDate, regPrice, vipPrice, stock, closeDate);
                result.add(t);
            }
        }

        return result;
    }

    private void tampilkanKeTabel() {
        DefaultTableModel model = (DefaultTableModel) tabelListTiket.getModel();
        model.setRowCount(0); // clear existing rows
        
         // Siapkan event listener untuk tombol detail
       
        
        
        
        for (Tiket t : listTiket) {
            String vipDisplay = t.getVipPrice() == 0 ? "-" : String.valueOf(t.getVipPrice()); // biar kalo vipnya ga diisi atau gada, di isi - cuy
            
            
            Object[] row = {
                t.getTiketCloseDate(),
                t.getCreatorName(),
                t.getEventName(),
                t.getLocation(),
                t.getEventDate().toString(),
                t.getRegPrice(),
                vipDisplay,
                t.getStock(),
                "detail" //ini dia manggil panelaction
            // setelah stock ini ada kolom untuk detail, yang nantinya terisi dengan button untuk ke form detail
            };
            model.addRow(row);
            
           
           
        }
        
    

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEvents2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnEvents = new javax.swing.JButton();
        btnEvents1 = new javax.swing.JButton();
        usernameProfile = new javax.swing.JLabel();
        gambarProfile = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ComboBoxFilter = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelListTiket = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSearch1 = new javax.swing.JTextField();
        btnSearch1 = new javax.swing.JButton();
        btnEvents3 = new javax.swing.JButton();
        btnEvents4 = new javax.swing.JButton();
        usernameProfile1 = new javax.swing.JLabel();
        gambarProfile1 = new javax.swing.JLabel();
        btnEvents5 = new javax.swing.JButton();

        btnEvents2.setBackground(new java.awt.Color(160, 89, 104));
        btnEvents2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEvents2.setForeground(new java.awt.Color(255, 255, 255));
        btnEvents2.setText("HISTORY");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("WATCHIFY");

        btnSearch.setText("search");
        btnSearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnEvents.setBackground(new java.awt.Color(153, 0, 51));
        btnEvents.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEvents.setForeground(new java.awt.Color(255, 255, 255));
        btnEvents.setText("EVENTS");

        btnEvents1.setBackground(new java.awt.Color(153, 0, 51));
        btnEvents1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEvents1.setForeground(new java.awt.Color(255, 255, 255));
        btnEvents1.setText("KERANJANG");

        usernameProfile.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        usernameProfile.setForeground(new java.awt.Color(255, 255, 255));
        usernameProfile.setText("nama Pengguna");

        gambarProfile.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 12)); // NOI18N
        gambarProfile.setForeground(new java.awt.Color(255, 255, 255));
        gambarProfile.setText("gambar_barang");
        gambarProfile.setToolTipText("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        jLabel1.setBackground(new java.awt.Color(102, 0, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("List of Tickets");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(102, 0, 51)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Search Event");

        jButton1.setText("Find");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        ComboBoxFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nama Event", "Jenis", "Event Date" }));
        ComboBoxFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxFilterActionPerformed(evt);
            }
        });

        tabelListTiket.setBackground(new java.awt.Color(102, 0, 51));
        tabelListTiket.setForeground(new java.awt.Color(255, 255, 255));
        tabelListTiket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Buy Ticket Close", "Event Creator", "Event Name", "Location", "Event Date", "Reguler Price", "Vip Price", "Stock", "Detail "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelListTiket.setEnabled(false);
        tabelListTiket.setRowHeight(40);
        tabelListTiket.setShowGrid(true);
        jScrollPane1.setViewportView(tabelListTiket);

        jPanel2.setBackground(new java.awt.Color(153, 0, 51));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("WATCHIFY");

        btnSearch1.setText("search");
        btnSearch1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSearch1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnEvents3.setBackground(new java.awt.Color(160, 89, 104));
        btnEvents3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEvents3.setForeground(new java.awt.Color(255, 255, 255));
        btnEvents3.setText("EVENTS");

        btnEvents4.setBackground(new java.awt.Color(153, 0, 51));
        btnEvents4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEvents4.setForeground(new java.awt.Color(255, 255, 255));
        btnEvents4.setText("KERANJANG");
        btnEvents4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvents4ActionPerformed(evt);
            }
        });

        usernameProfile1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        usernameProfile1.setForeground(new java.awt.Color(255, 255, 255));
        usernameProfile1.setText("nama Pengguna");

        gambarProfile1.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 12)); // NOI18N
        gambarProfile1.setForeground(new java.awt.Color(255, 255, 255));
        gambarProfile1.setText("gambar_barang");
        gambarProfile1.setToolTipText("");

        btnEvents5.setBackground(new java.awt.Color(153, 0, 51));
        btnEvents5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnEvents5.setForeground(new java.awt.Color(255, 255, 255));
        btnEvents5.setText("HISTORY");
        btnEvents5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvents5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel5)
                .addGap(40, 40, 40)
                .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnEvents3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEvents4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEvents5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 489, Short.MAX_VALUE)
                .addComponent(usernameProfile1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gambarProfile1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(usernameProfile1)
                        .addComponent(gambarProfile1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch1)
                        .addComponent(btnEvents3)
                        .addComponent(btnEvents4)
                        .addComponent(btnEvents5)))
                .addGap(47, 47, 47))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboBoxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(73, 73, 73))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(689, 689, 689)
                .addComponent(jLabel1)
                .addContainerGap(1085, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(ComboBoxFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 2017, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxFilterActionPerformed

    private void btnEvents4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvents4ActionPerformed
        // TODO add your handling code here:
        this.dispose();

        FormCheckout cekot = new FormCheckout();
        cekot.setVisible(true);
    }//GEN-LAST:event_btnEvents4ActionPerformed

    private void btnEvents5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvents5ActionPerformed
        // TODO add your handling code here:
        this.dispose();

        FormHistory histori = new FormHistory();
        histori.setVisible(true);
    }//GEN-LAST:event_btnEvents5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String kategori = ComboBoxFilter.getSelectedItem().toString().toUpperCase();
        String input = searchBar.getText().trim();

        String keyword;
        if (kategori.equals("NAMA EVENT")) {
            keyword = "NAMA";
        } else if (kategori.equals("JENIS")) {
            keyword = "JENIS";
        } else if (kategori.equals("EVENT DATE")) {
            keyword = "TANGGAL";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Format tanggal salah!\nGunakan format yyyy-MM-dd");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Kategori tidak dikenali.");
            return;
        }

        try {
            s = new Socket("localhost", 6000);

            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String message = "QUERY " + keyword + " " + input;
            out.writeBytes(message + "\n");

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String receivedData = in.readLine();
            System.out.println(receivedData);
            ArrayList<Tiket> parsedList = parseStringToList(receivedData);
            loadData(parsedList);
            tampilkanKeTabel();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal terhubung ke server!");
            ex.printStackTrace();
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormListTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormListTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormListTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormListTickets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FormListTickets().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(FormListTickets.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxFilter;
    private javax.swing.JButton btnEvents;
    private javax.swing.JButton btnEvents1;
    private javax.swing.JButton btnEvents2;
    private javax.swing.JButton btnEvents3;
    private javax.swing.JButton btnEvents4;
    private javax.swing.JButton btnEvents5;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearch1;
    private javax.swing.JLabel gambarProfile;
    private javax.swing.JLabel gambarProfile1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTable tabelListTiket;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JLabel usernameProfile;
    private javax.swing.JLabel usernameProfile1;
    // End of variables declaration//GEN-END:variables
}
