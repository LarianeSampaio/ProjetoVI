
package br.com.infox.telas;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class TelaEmpresas extends javax.swing.JInternalFrame {

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
    
    public TelaEmpresas() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    
    public void adicionar(){
        String sql = "INSERT INTO empresa(Razao_social, CNPJ, logradouro, bairro, cep, telefone, email, descricao) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, JtextRazaoSocialEmpresa.getText());
            pst.setString(2, JtextCNPJEmpresa.getText());
            pst.setString(3, JtextLogradouroEmpresa.getText());
            pst.setString(4, JtextBairroDistritoEmpresa.getText());
            pst.setString(5, JtextCepEmpresa.getText());
            pst.setString(6, JtextTelefoneEmpresa.getText());
            pst.setString(7, JtextEmailEmpresa.getText());
            pst.setString(8,ADescricaoEmpresa.getText());
     
            
              if ((JtextRazaoSocialEmpresa.getText().isEmpty())||(JtextCNPJEmpresa.getText().isEmpty())) {
                 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                 
             } else {
                  int empresaAdicionada = pst.executeUpdate();
                  if(empresaAdicionada > 0){
                      JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso");
                      JtextRazaoSocialEmpresa.setText(null);
                      JtextCNPJEmpresa.setText(null);
                      JtextLogradouroEmpresa.setText(null);
                      JtextBairroDistritoEmpresa.setText(null);
                      JtextCepEmpresa.setText(null);
                      JtextTelefoneEmpresa.setText(null);
                      JtextEmailEmpresa.setText(null);
                      ADescricaoEmpresa.setText(null);
                     
                      
                  }
              }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
           
        }
    }
    
     public void setar_campos(){
        int setar = TabelaPesquisarEmpresa.getSelectedRow();
        txtIdEmp.setText(TabelaPesquisarEmpresa.getModel().getValueAt(setar, 0).toString());
        JtextRazaoSocialEmpresa.setText(TabelaPesquisarEmpresa.getModel().getValueAt(setar, 1).toString());
        JtextCNPJEmpresa.setText((TabelaPesquisarEmpresa.getModel().getValueAt(setar, 2).toString()));
        JtextLogradouroEmpresa.setText(TabelaPesquisarEmpresa.getModel().getValueAt(setar, 3).toString());
        JtextBairroDistritoEmpresa.setText(TabelaPesquisarEmpresa.getModel().getValueAt(setar, 4).toString());
        JtextCepEmpresa.setText(TabelaPesquisarEmpresa.getModel().getValueAt(setar, 5).toString());
        JtextTelefoneEmpresa.setText(TabelaPesquisarEmpresa.getModel().getValueAt(setar, 6).toString());
        JtextEmailEmpresa.setText(TabelaPesquisarEmpresa.getModel().getValueAt(setar, 7).toString());
        ADescricaoEmpresa.setText(TabelaPesquisarEmpresa.getModel().getValueAt(setar, 8).toString());
        
        
        BtnAdicionarEmpresa.setEnabled(false);
    }
    
    public void pesquisar_empresa(){
        String sql="SELECT * FROM empresa WHERE razao_social LIKE ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1,txtPesquisarEmpresa.getText() + "%");
            rs=pst.executeQuery();
            
            TabelaPesquisarEmpresa.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
      
     private void alterar(){
       String sql ="UPDATE empresa SET razao_social = ?, cnpj = ?, logradouro = ?, bairro = ?, cep = ?, telefone = ?, email = ?, descricao = ? WHERE id_empresa::text = ?";
       try {
           PreparedStatement pst = conexao.prepareStatement(sql);
           
           pst.setString(1, JtextRazaoSocialEmpresa.getText());
           pst.setString(2, JtextCNPJEmpresa.getText());
           pst.setString(3, JtextLogradouroEmpresa.getText());
           pst.setString(4, JtextBairroDistritoEmpresa.getText());
           pst.setString(5, JtextCepEmpresa.getText());
           pst.setString(6, JtextTelefoneEmpresa.getText());
           pst.setString(7, JtextEmailEmpresa.getText());
           pst.setString(8, ADescricaoEmpresa.getText());
           pst.setString(9, txtIdEmp.getText());
          
          
           if(JtextRazaoSocialEmpresa.getText().isEmpty()|| (JtextCNPJEmpresa.getText().isEmpty())|| JtextEmailEmpresa.getText().isEmpty()){
               JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
           } else {
               int adicionado = pst.executeUpdate();
               
               if(adicionado > 0){
                   JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso");
                   JtextRazaoSocialEmpresa.setText(null);
                   JtextCNPJEmpresa.setText(null);
                   JtextLogradouroEmpresa.setText(null);
                   JtextBairroDistritoEmpresa.setText(null);
                   JtextCepEmpresa.setText(null);
                   JtextTelefoneEmpresa.setText(null);
                   JtextEmailEmpresa.setText(null);
                   ADescricaoEmpresa.setText(null);
                   DefaultTableModel tableModel = (DefaultTableModel) TabelaPesquisarEmpresa.getModel();
                   tableModel.setRowCount(0);
                   
                   BtnEditarEmpresa.setSelected(true);
                }
           }

           
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
   }
     public void remover_empresa(){
         int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que dedeja excluir a empresa?", "Atenção", JOptionPane.YES_NO_OPTION);
       if(confirma == JOptionPane.YES_OPTION){
           String sql ="DELETE FROM empresa WHERE id_empresa::text = ?";
           try {
               PreparedStatement pst = conexao.prepareStatement(sql);
               pst.setString(1, txtIdEmp.getText());
               int apagado = pst.executeUpdate();
               if(apagado > 0){
                   JOptionPane.showMessageDialog(null, "empresa excluida com sucesso");
                   JtextRazaoSocialEmpresa.setText(null);
                   JtextCNPJEmpresa.setText(null);
                   JtextLogradouroEmpresa.setText(null);
                   JtextBairroDistritoEmpresa.setText(null);
                   JtextCepEmpresa.setText(null);
                   JtextTelefoneEmpresa.setText(null);
                   JtextTelefoneEmpresa.setText(null);
                   JtextEmailEmpresa.setText(null);
                   ADescricaoEmpresa.setText(null);
                   DefaultTableModel tableModel = (DefaultTableModel) TabelaPesquisarEmpresa.getModel();
                   tableModel.setRowCount(0);
                   BtnAdicionarEmpresa.setSelected(true);
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
        lblRazaoSocialEmpresa = new javax.swing.JLabel();
        JtextRazaoSocialEmpresa = new javax.swing.JTextField();
        lblCnpjEmpresa = new javax.swing.JLabel();
        JtextCNPJEmpresa = new javax.swing.JTextField();
        lblLogradouroEmpresa = new javax.swing.JLabel();
        JtextLogradouroEmpresa = new javax.swing.JTextField();
        lblCepEmpresa = new javax.swing.JLabel();
        JtextCepEmpresa = new javax.swing.JTextField();
        lblBairroDistritoEmpresa = new javax.swing.JLabel();
        JtextBairroDistritoEmpresa = new javax.swing.JTextField();
        lblTelefoneEmpresa = new javax.swing.JLabel();
        JtextTelefoneEmpresa = new javax.swing.JTextField();
        lblEmailEmpresa = new javax.swing.JLabel();
        JtextEmailEmpresa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ADescricaoEmpresa = new javax.swing.JTextArea();
        TextDescricaoEmpresa = new javax.swing.JLabel();
        BtnAdicionarEmpresa = new javax.swing.JButton();
        BtnEditarEmpresa = new javax.swing.JButton();
        BtnExcluirEmpresa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaPesquisarEmpresa = new javax.swing.JTable();
        lbltxtEmp = new javax.swing.JLabel();
        txtIdEmp = new javax.swing.JTextField();
        lblPesquisarEmp = new javax.swing.JLabel();
        txtPesquisarEmpresa = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(800, 660));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Empresas"));
        jPanel1.setAutoscrolls(true);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 630));

        lblRazaoSocialEmpresa.setText("Razão Social: ");

        lblCnpjEmpresa.setText("CNPJ: ");

        lblLogradouroEmpresa.setText("Logradouro:");

        lblCepEmpresa.setText("CEP: ");

        lblBairroDistritoEmpresa.setText("Bairro / Distrito: ");

        lblTelefoneEmpresa.setText("Telefone:");

        lblEmailEmpresa.setText("Email: ");

        ADescricaoEmpresa.setColumns(20);
        ADescricaoEmpresa.setRows(5);
        jScrollPane1.setViewportView(ADescricaoEmpresa);

        TextDescricaoEmpresa.setText("Descrição: ");

        BtnAdicionarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/ADDCli.png"))); // NOI18N
        BtnAdicionarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdicionarEmpresaActionPerformed(evt);
            }
        });

        BtnEditarEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Text_edit_35243.png"))); // NOI18N
        BtnEditarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarEmpresaActionPerformed(evt);
            }
        });

        BtnExcluirEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/trash-can_115312.png"))); // NOI18N
        BtnExcluirEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirEmpresaActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Empresas Cadastradas"));

        TabelaPesquisarEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Razão Social", "CNPJ", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TabelaPesquisarEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaPesquisarEmpresaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelaPesquisarEmpresa);

        lbltxtEmp.setText("ID:");

        txtIdEmp.setEditable(false);
        txtIdEmp.setEnabled(false);

        lblPesquisarEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/search_find_lupa_21889.png"))); // NOI18N

        txtPesquisarEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarEmpresaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblPesquisarEmp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesquisarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbltxtEmp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPesquisarEmp, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbltxtEmp)
                        .addComponent(txtPesquisarEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(BtnAdicionarEmpresa)
                        .addGap(18, 18, 18)
                        .addComponent(BtnEditarEmpresa)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExcluirEmpresa)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblTelefoneEmpresa)
                                        .addGap(115, 115, 115)
                                        .addComponent(lblEmailEmpresa))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(JtextTelefoneEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(JtextEmailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(JtextBairroDistritoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblBairroDistritoEmpresa))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCepEmpresa)
                                            .addComponent(JtextCepEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(260, 260, 260)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLogradouroEmpresa)
                                    .addComponent(JtextLogradouroEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblRazaoSocialEmpresa)
                                    .addComponent(lblCnpjEmpresa)
                                    .addComponent(JtextCNPJEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JtextRazaoSocialEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextDescricaoEmpresa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BtnAdicionarEmpresa, BtnEditarEmpresa, BtnExcluirEmpresa});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblRazaoSocialEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextRazaoSocialEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCnpjEmpresa)
                        .addGap(3, 3, 3)
                        .addComponent(JtextCNPJEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLogradouroEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextLogradouroEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBairroDistritoEmpresa)
                    .addComponent(lblCepEmpresa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JtextBairroDistritoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JtextCepEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefoneEmpresa)
                    .addComponent(lblEmailEmpresa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JtextTelefoneEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JtextEmailEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(TextDescricaoEmpresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAdicionarEmpresa)
                    .addComponent(BtnEditarEmpresa)
                    .addComponent(BtnExcluirEmpresa))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BtnAdicionarEmpresa, BtnEditarEmpresa, BtnExcluirEmpresa});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnAdicionarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdicionarEmpresaActionPerformed
            adicionar();
    }//GEN-LAST:event_BtnAdicionarEmpresaActionPerformed

    private void TabelaPesquisarEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaPesquisarEmpresaMouseClicked
        setar_campos();
    }//GEN-LAST:event_TabelaPesquisarEmpresaMouseClicked

    private void txtPesquisarEmpresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarEmpresaKeyReleased
        pesquisar_empresa();
    }//GEN-LAST:event_txtPesquisarEmpresaKeyReleased

    private void BtnEditarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarEmpresaActionPerformed
        alterar();
    }//GEN-LAST:event_BtnEditarEmpresaActionPerformed

    private void BtnExcluirEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirEmpresaActionPerformed
    remover_empresa();
    }//GEN-LAST:event_BtnExcluirEmpresaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ADescricaoEmpresa;
    private javax.swing.JButton BtnAdicionarEmpresa;
    private javax.swing.JButton BtnEditarEmpresa;
    private javax.swing.JButton BtnExcluirEmpresa;
    private javax.swing.JTextField JtextBairroDistritoEmpresa;
    private javax.swing.JTextField JtextCNPJEmpresa;
    private javax.swing.JTextField JtextCepEmpresa;
    private javax.swing.JTextField JtextEmailEmpresa;
    private javax.swing.JTextField JtextLogradouroEmpresa;
    private javax.swing.JTextField JtextRazaoSocialEmpresa;
    private javax.swing.JTextField JtextTelefoneEmpresa;
    private javax.swing.JTable TabelaPesquisarEmpresa;
    private javax.swing.JLabel TextDescricaoEmpresa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBairroDistritoEmpresa;
    private javax.swing.JLabel lblCepEmpresa;
    private javax.swing.JLabel lblCnpjEmpresa;
    private javax.swing.JLabel lblEmailEmpresa;
    private javax.swing.JLabel lblLogradouroEmpresa;
    private javax.swing.JLabel lblPesquisarEmp;
    private javax.swing.JLabel lblRazaoSocialEmpresa;
    private javax.swing.JLabel lblTelefoneEmpresa;
    private javax.swing.JLabel lbltxtEmp;
    private javax.swing.JTextField txtIdEmp;
    private javax.swing.JTextField txtPesquisarEmpresa;
    // End of variables declaration//GEN-END:variables
}
