
package br.com.infox.telas;

import br.com.infox.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Bruno
 */
public class TelaServices extends javax.swing.JInternalFrame {

     public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    Connection conexao=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    private String pesquisa;

    
    
    public TelaServices() {
        initComponents();
        conexao = ModuloConexao.conector();
        
    }

    public void adicionar_servico(){
        String sql = "INSERT INTO servicos(tipo_servico, produto_servico, nome, descricao) VALUES (?,?,?,?)";
       
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            
            pst.setString(1, JcomboTipoServico.getSelectedItem().toString());
            pst.setString(2, JcomboTipoProduto.getSelectedItem().toString());
            pst.setString(3, JtextNomeServico.getText());
            pst.setString(4, JtextDescricaoServico.getText());
            
            
            
            if ((JtextDescricaoServico.getText().isEmpty())||(JtextNomeServico.getText().isEmpty())) {
                 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                 
             } else {
                 int adicionado = pst.executeUpdate();
                 if (adicionado > 0){
                      JOptionPane.showMessageDialog(null, "Serviço adicionado com sucesso");
                      JtextNomeServico.setText(null);
                      JtextDescricaoServico.setText(null);
                      JtextIdServico.setText(null);
                      
                  }
            }
            
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void pesquisar_servico(){
         String sql="SELECT * FROM servicos WHERE nome LIKE ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, JtextPesquisaServ.getText() + "%");
            rs=pst.executeQuery();
            //Usando a biblioteca rs2xml.jar para incluir dados em tabela(select);;
            tblListaServico.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
       public void setar_campos(){
        int setar = tblListaServico.getSelectedRow();
        JtextIdServico.setText(tblListaServico.getModel().getValueAt(setar, 0).toString());
        JtextTipoServico.setText(tblListaServico.getModel().getValueAt(setar,1).toString());
        JtextProdutoServico.setText(tblListaServico.getModel().getValueAt(setar, 2).toString());
        JtextNomeServico.setText(tblListaServico.getModel().getValueAt(setar, 3).toString());
        JtextDataServico.setText(tblListaServico.getModel().getValueAt(setar, 4).toString());
        JtextDescricaoServico.setText(tblListaServico.getModel().getValueAt(setar, 5).toString());
        
        
        
        BtnAdicionarServico.setEnabled(false);
    }
       
       
       private void alterar(){
       String sql ="UPDATE servicos SET tipo_servico = ?, produto_servico = ?, nome = ?, descricao = ? WHERE id_servico::text = ?";
       try {
           PreparedStatement pst = conexao.prepareStatement(sql);
           
           pst.setString(1, JcomboTipoServico.getSelectedItem().toString());
           pst.setString(2, JcomboTipoProduto.getSelectedItem().toString());  
           pst.setString(3, JtextNomeServico.getText());
           pst.setString(4, JtextDescricaoServico.getText());
           pst.setString(5, JtextIdServico.getText());
           if(JtextNomeServico.getText().isEmpty()|| (JtextDescricaoServico.getText().isEmpty())|| JtextProdutoServico.getText().isEmpty()){
               JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
           } else {
               int adicionado = pst.executeUpdate();
               
               if(adicionado > 0){
                   JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso");
                   JtextNomeServico.setText(null);
                   JtextDescricaoServico.setText(null);
                   JtextIdServico.setText(null);
                   JtextDataServico.setText(null);
                   DefaultTableModel tableModel = (DefaultTableModel) tblListaServico.getModel();
                   tableModel.setRowCount(0);
                   BtnAdicionarServico.setEnabled(true);
                   
                  }
           }

           
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
   }
       
       private void excluir_serv(){
            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que dedeja excluir o serviço?", "Atenção", JOptionPane.YES_NO_OPTION);
       if(confirma == JOptionPane.YES_OPTION){
           String sql ="DELETE FROM servicos WHERE id_servico::text = ?";
           try {
               PreparedStatement pst = conexao.prepareStatement(sql);
               pst.setString(1, JtextIdServico.getText());
               int apagado = pst.executeUpdate();
               if(apagado > 0){
                   JOptionPane.showMessageDialog(null, "Serviço excluido com sucesso");
                   JtextNomeServico.setText(null);
                   JtextDescricaoServico.setText(null);
                   JtextDataServico.setText(null);
                   JtextIdServico.setText(null);
                   JtextProdutoServico.setText(null);
                   JtextDescricaoServico.setText(null);
                   DefaultTableModel tableModel = (DefaultTableModel) tblListaServico.getModel();
                   tableModel.setRowCount(0);
                   BtnAdicionarServico.setEnabled(true);
               }
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
           }
       }
       }
       
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTipoManutenção = new javax.swing.JLabel();
        JtextNomeServico = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblDataServico = new javax.swing.JLabel();
        JtextDataServico = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtextDescricaoServico = new javax.swing.JTextArea();
        lblDescricaoServico = new javax.swing.JLabel();
        BtnAdicionarServico = new javax.swing.JButton();
        BtnEditarServico = new javax.swing.JButton();
        btnExcluirServico = new javax.swing.JButton();
        JcomboTipoServico = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        JcomboTipoProduto = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListaServico = new javax.swing.JTable();
        JtextIdServico = new javax.swing.JTextField();
        lblServicoId = new javax.swing.JLabel();
        lblPesquisaServ = new javax.swing.JLabel();
        JtextPesquisaServ = new javax.swing.JTextField();
        lblProdutoServico = new javax.swing.JLabel();
        JtextProdutoServico = new javax.swing.JTextField();
        lblServico = new javax.swing.JLabel();
        JtextTipoServico = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(800, 660));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Serviços"));

        lblTipoManutenção.setText("Descrição Manutenção: ");

        jLabel1.setText("Produto: ");

        lblDataServico.setText("Data:");

        JtextDataServico.setEnabled(false);

        JtextDescricaoServico.setColumns(20);
        JtextDescricaoServico.setRows(5);
        jScrollPane1.setViewportView(JtextDescricaoServico);

        lblDescricaoServico.setText("Descrição Serviço / Motivos:");

        BtnAdicionarServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/ADDCli.png"))); // NOI18N
        BtnAdicionarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdicionarServicoActionPerformed(evt);
            }
        });

        BtnEditarServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Text_edit_35243.png"))); // NOI18N
        BtnEditarServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarServicoActionPerformed(evt);
            }
        });

        btnExcluirServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/document_delete_256_icon-icons.com_75995.png"))); // NOI18N
        btnExcluirServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirServicoActionPerformed(evt);
            }
        });

        JcomboTipoServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Preventivo", "Corretivo", "Preditivo" }));

        jLabel2.setText("* Tipo Serviço: ");

        JcomboTipoProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desktop", "Notebook", "SmartPhone", "Tablet", "Impressora", "Ipad", "Iphone", " " }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Serviços "));

        tblListaServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Produto", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListaServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaServicoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblListaServico);

        JtextIdServico.setEditable(false);
        JtextIdServico.setEnabled(false);

        lblServicoId.setText("ID: ");

        lblPesquisaServ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/search_find_lupa_21889.png"))); // NOI18N

        JtextPesquisaServ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtextPesquisaServKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPesquisaServ)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextPesquisaServ, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(lblServicoId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JtextIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblServicoId)
                        .addComponent(JtextPesquisaServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPesquisaServ, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblProdutoServico.setText("Produto: ");

        JtextProdutoServico.setEditable(false);
        JtextProdutoServico.setEnabled(false);

        lblServico.setText("Serviço: ");

        JtextTipoServico.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipoManutenção)
                            .addComponent(JtextNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDataServico)
                            .addComponent(lblProdutoServico)
                            .addComponent(JtextProdutoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblServico)
                            .addComponent(JtextDataServico, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JtextTipoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JcomboTipoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(JcomboTipoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(BtnAdicionarServico)
                                .addGap(18, 18, 18)
                                .addComponent(BtnEditarServico)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirServico))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescricaoServico)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BtnAdicionarServico, BtnEditarServico, btnExcluirServico});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JcomboTipoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JcomboTipoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblTipoManutenção)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDataServico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextDataServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblServico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JtextTipoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProdutoServico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextProdutoServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)))
                .addComponent(lblDescricaoServico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnEditarServico)
                    .addComponent(BtnAdicionarServico)
                    .addComponent(btnExcluirServico))
                .addGap(46, 46, 46))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BtnAdicionarServico, BtnEditarServico, btnExcluirServico});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAdicionarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdicionarServicoActionPerformed
        adicionar_servico();
    }//GEN-LAST:event_BtnAdicionarServicoActionPerformed

    private void tblListaServicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaServicoMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblListaServicoMouseClicked

    private void JtextPesquisaServKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtextPesquisaServKeyReleased
        pesquisar_servico();
    }//GEN-LAST:event_JtextPesquisaServKeyReleased

    private void BtnEditarServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarServicoActionPerformed
           alterar();
    }//GEN-LAST:event_BtnEditarServicoActionPerformed

    private void btnExcluirServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirServicoActionPerformed
        excluir_serv();
    }//GEN-LAST:event_btnExcluirServicoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdicionarServico;
    private javax.swing.JButton BtnEditarServico;
    private javax.swing.JComboBox<String> JcomboTipoProduto;
    private javax.swing.JComboBox<String> JcomboTipoServico;
    private javax.swing.JTextField JtextDataServico;
    private javax.swing.JTextArea JtextDescricaoServico;
    private javax.swing.JTextField JtextIdServico;
    private javax.swing.JTextField JtextNomeServico;
    private javax.swing.JTextField JtextPesquisaServ;
    private javax.swing.JTextField JtextProdutoServico;
    private javax.swing.JTextField JtextTipoServico;
    private javax.swing.JButton btnExcluirServico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDataServico;
    private javax.swing.JLabel lblDescricaoServico;
    private javax.swing.JLabel lblPesquisaServ;
    private javax.swing.JLabel lblProdutoServico;
    private javax.swing.JLabel lblServico;
    private javax.swing.JLabel lblServicoId;
    private javax.swing.JLabel lblTipoManutenção;
    private javax.swing.JTable tblListaServico;
    // End of variables declaration//GEN-END:variables
}
