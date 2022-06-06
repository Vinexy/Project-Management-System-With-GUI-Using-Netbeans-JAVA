
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author patir
 */
public class CompanyInfos extends javax.swing.JFrame {

    /**
     * Creates new form CompanyInfos
     */
    public CompanyInfos() {
        initComponents();
    }
    String strComp="";
    static int x=0;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        cbox1 = new javax.swing.JCheckBox();
        cbox2 = new javax.swing.JCheckBox();
        cbox3 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153), 2));

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        cbox1.setText("Project Info");

        cbox2.setText("Administration Infos");

        cbox3.setText("Employee Infos");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELECT THE DATA ");

        jButton1.setText("DISPLAY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(cbox2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbox3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbox1)
                    .addComponent(cbox2)
                    .addComponent(cbox3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    Project obj1 = new Project();
    Administration obj2 = new Administration();
    ProjectMember obj3 = new ProjectMember();
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        textArea.setText("");
        String[] infos = strComp.split("\t");
        //seçili checkBox lara göre şirketteki yönetici proje ve çalışan bilgilerini ekrana yazdırır
        if(cbox1.isSelected()){
            try {
                obj1.ReadFile();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

           textArea.append("\nNumber of projects in the company: " + Integer.toString(obj1.getProjectNum())+ "\n");

        }
        
        if(cbox2.isSelected()){
            textArea.append("\n/*****************************************************/\n");
            try {
                obj2.ReadFile();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            textArea.append("\nNumber of admins in the company: " + Integer.toString(obj2.getAdminNum())+ "\n");

        }
        
        if(cbox3.isSelected()){
            textArea.append("\n/*****************************************************/\n");
            try {
                obj3.ReadFile();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
               textArea.append("\nNumber of employees in the company: " + Integer.toString(obj3.getMemberNum())+ "\n");
            
        }
        
        /*proje müdürleri ve yöneticiler daha detaylı bilgiler görebilir eğer 
        idleri 11 ve 12 ile başlıyorsa bu bilgiler textAreaya aktarılır*/
        if(x==11 || x==12){
            if(cbox1.isSelected()){
                textArea.append("\n/*****************************************************/\n");
            try {
                obj1.ReadFile();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            for(int j=0;j<obj1.getProjectList().size();j++){
               textArea.append(obj1.getProjectList().get(j).toString());
            }
            
        }
        
        if(cbox2.isSelected()){
            textArea.append("\n/*****************************************************/\n" );
            try {
                obj2.ReadFile();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            for(int j=0;j<obj2.getAdminList().size();j++){
               textArea.append(obj2.getAdminList().get(j).toString());
            }

        }
        
        if(cbox3.isSelected()){
            textArea.append("\n/*****************************************************/\n");
            try {
                obj3.ReadFile();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            for(int j=0;j<obj3.getMemberList().size();j++){
               textArea.append(obj3.getMemberList().get(j).toString());
            }
        }
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
            java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompanyInfos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CompanyInfos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbox1;
    private javax.swing.JCheckBox cbox2;
    private javax.swing.JCheckBox cbox3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}