
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
public class TelaCadHardware extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadHardware
     */
    
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
    
    public TelaCadHardware() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void adicionar_hardware(){
        //Método cadastrar Hardware.
        String sql="INSERT INTO produtos(nome_prod, marca_prod, serial_prod, valor_prod, tipo_prod, modelo, situacao_produto, defeito_descricao, placa, socket, processador, memoria, frequencia, fonte, tipo_fonte, disco, tipo_disco, descricao_hardware, SO, versao, descricao_software)VALUES (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, JtextNomeProd.getText());
            pst.setString(2, JtextMarcaProduto.getText());
            pst.setString(3, JtextSerialProduto.getText());
            pst.setString(4, JtextValorProduto.getText());
            pst.setString(5, jComboBoxTipoProduto.getSelectedItem().toString());
            pst.setString(6, JtextModeloProduto.getText());
            pst.setString(7, jComboBoxSituacaoProduto.getSelectedItem().toString());
            pst.setString(8, TextAreaDefeitoProdudo.getText());
            pst.setString(9, JtextPlacaMaeProduto.getText());
            pst.setString(10, JtextSocketProduto.getText());
            pst.setString(11, JtextProcessadorProduto.getText());
            pst.setString(12, JtextMemoriaProduto.getText());
            pst.setString(13, JtextFreqMemoriaProd.getText());
            pst.setString(14, JtextFonteProduto.getText());
            pst.setString(15, jComboBoxTipoFonteProduto.getSelectedItem().toString());
            pst.setString(16, JTextDiscoProduto.getText());
            pst.setString(17, jComboBoxTipoHDProduto.getSelectedItem().toString());
            pst.setString(18, AtextDescricaoHard.getText());
            pst.setString(19, jComboBox1TipoSistema.getSelectedItem().toString());
            pst.setString(20, JtextVersaoSistemaProduto.getText());
            pst.setString(21, TextAreaDescricaoSoftwareProduto.getText());
            
          if ((JtextNomeProd.getText().isEmpty())||(JtextMarcaProduto.getText().isEmpty())) {
                 JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
                 
             } else {
                 int adicionado = pst.executeUpdate();
                 if (adicionado > 0){
                      JOptionPane.showMessageDialog(null, "Hardware cadastrado com sucesso.");
                      JtextNomeProd.setText(null);
                      JtextMarcaProduto.setText(null);
                      JtextSerialProduto.setText(null);
                      JtextValorProduto.setText(null);
                      JtextModeloProduto.setText(null);
                      TextAreaDefeitoProdudo.setText(null);
                      JtextPlacaMaeProduto.setText(null);
                      JtextPlacaMaeProduto.setText(null);
                      JtextProcessadorProduto.setText(null);
                      JtextMemoriaProduto.setText(null);
                      JtextFonteProduto.setText(null);
                      JTextDiscoProduto.setText(null);
                      JtextSocketProduto.setText(null);
                      JtextFreqMemoriaProd.setText(null);
                      AtextDescricaoHard.setText(null);
                      JtextVersaoSistemaProduto.setText(null);
                      TextAreaDescricaoSoftwareProduto.setText(null);
                 }  
             }
            
           } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void alterar_produto(){
        String sql = "UPDATE produtos SET nome_prod = ?, marca_prod = ?, serial_prod = ?, valor_prod = ?, tipo_prod = ?, modelo = ?, situacao_produto = ?, defeito_descricao = ?, placa = ?, socket = ?, processador = ?, memoria = ?, frequencia = ?, fonte = ?, tipo_fonte = ?, disco = ?, tipo_disco = ?, descricao_hardware = ?, SO = ?, versao = ?, descricao_software = ? WHERE id_prod::text = ?";
        
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, JtextNomeProd.getText());
            pst.setString(2, JtextMarcaProduto.getText());
            pst.setString(3, JtextSerialProduto.getText());
            pst.setString(4, JtextValorProduto.getText());
            pst.setString(5, jComboBoxTipoProduto.getSelectedItem().toString()); 
            pst.setString(6, JtextModeloProduto.getText()); 
            pst.setString(7, jComboBoxSituacaoProduto.getSelectedItem().toString());
            pst.setString(8, TextAreaDefeitoProdudo.getText()); 
            pst.setString(9, JtextPlacaMaeProduto.getText()); 
            pst.setString(10, JtextSocketProduto.getText()); 
            pst.setString(11, JtextProcessadorProduto.getText()); 
            pst.setString(12, JtextMemoriaProduto.getText()); 
            pst.setString(13, JtextFreqMemoriaProd.getText()); 
            pst.setString(14, JtextFonteProduto.getText()); 
            pst.setString(15, jComboBoxTipoFonteProduto.getSelectedItem().toString());
            pst.setString(16, JTextDiscoProduto.getText()); 
            pst.setString(17, jComboBoxTipoHDProduto.getSelectedItem().toString());
            pst.setString(18, AtextDescricaoHard.getText()); 
            pst.setString(19, jComboBox1TipoSistema.getSelectedItem().toString());
            pst.setString(20, JtextVersaoSistemaProduto.getText()); 
            pst.setString(21, TextAreaDescricaoSoftwareProduto.getText());
            pst.setString(22, JtextCodigoCadProdutos.getText());
            
            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Equipamento alterado com sucesso");
                
            JtextNomeProd.setText(null);
            JtextMarcaProduto.setText(null);
            JtextSerialProduto.setText(null);
            JtextValorProduto.setText(null);
            JtextModeloProduto.setText(null);
            TextAreaDefeitoProdudo.setText(null);
            JtextPlacaMaeProduto.setText(null);
            JtextSocketProduto.setText(null);
            JtextProcessadorProduto.setText(null);
            JtextMemoriaProduto.setText(null);
            JtextFreqMemoriaProd.setText(null);
            JtextFonteProduto.setText(null);
            JTextDiscoProduto.setText(null);
            AtextDescricaoHard.setText(null);
            JtextVersaoSistemaProduto.setText(null);
            TextAreaDescricaoSoftwareProduto.setText(null);
            JtextCodigoCadProdutos.setText(null);
            DefaultTableModel tableModel = (DefaultTableModel) TabelaListaHardware.getModel();
            tableModel.setRowCount(0);
            btnAdicionarProduto.setEnabled(true);
            } 
            } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void excluir_produto(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que dedeja excluir o Produto?", "Atenção", JOptionPane.YES_NO_OPTION);
          if(confirma == JOptionPane.YES_OPTION){
           String sql = "DELETE FROM produtos WHERE id_prod::text = ?";
           try {
               PreparedStatement pst = conexao.prepareStatement(sql);
               pst.setString(1, JtextCodigoCadProdutos.getText());
               int apagado = pst.executeUpdate();
               if(apagado > 0){
                   JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
                   JtextNomeProd.setText(null);
                   JtextMarcaProduto.setText(null);
                   JtextSerialProduto.setText(null);
                   JtextValorProduto.setText(null);
                   JtextModeloProduto.setText(null);
                   TextAreaDefeitoProdudo.setText(null);
                   JtextPlacaMaeProduto.setText(null);
                   JtextSocketProduto.setText(null);
                   JtextProcessadorProduto.setText(null);
                   JtextMemoriaProduto.setText(null);
                   JtextFreqMemoriaProd.setText(null);
                   JtextFonteProduto.setText(null);
                   JTextDiscoProduto.setText(null);
                   AtextDescricaoHard.setText(null);
                   JtextVersaoSistemaProduto.setText(null);
                   TextAreaDescricaoSoftwareProduto.setText(null);
                   JtextCodigoCadProdutos.setText(null);
                   btnAdicionarProduto.setEnabled(true);
                   DefaultTableModel tableModel = (DefaultTableModel) TabelaListaHardware.getModel();
                   tableModel.setRowCount(0);
               }
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
           }
       }
        
    
        try {
            
        } catch (Exception e) {
        }
    }
    
    private void setar_campos(){
        int setar = TabelaListaHardware.getSelectedRow();
        JtextCodigoCadProdutos.setText(TabelaListaHardware.getModel().getValueAt(setar, 0).toString());
        JtextNomeProd.setText(TabelaListaHardware.getModel().getValueAt(setar, 1).toString());
        JtextMarcaProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 2).toString());
        JtextSerialProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 3).toString());
        JtextValorProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 4).toString());
        JtextModeloProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 6).toString());
        TextAreaDefeitoProdudo.setText(TabelaListaHardware.getModel().getValueAt(setar, 7).toString());
        JtextPlacaMaeProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 9).toString());
        JtextSocketProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 10).toString());
        JtextProcessadorProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 11).toString());
        JtextMemoriaProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 12).toString());
        JtextFreqMemoriaProd.setText(TabelaListaHardware.getModel().getValueAt(setar, 13).toString());
        JtextFonteProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 14).toString());
        JTextDiscoProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 16).toString());
        AtextDescricaoHard.setText(TabelaListaHardware.getModel().getValueAt(setar, 18).toString());
        JtextVersaoSistemaProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 20).toString());
        TextAreaDescricaoSoftwareProduto.setText(TabelaListaHardware.getModel().getValueAt(setar, 21).toString());
        btnAdicionarProduto.setEnabled(false);
    
    }
    
    private void pequisar_hardware(){
        String sql="SELECT * FROM produtos WHERE nome_prod LIKE ?";
        
          try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1,TxtPesquisaHardware.getText() + "%");
            rs=pst.executeQuery();
            //Usando a biblioteca rs2xml.jar para incluir dados em tabela(select);;
            TabelaListaHardware.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        GuiaInformacoesDeSoftwareProduto = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        lblTipoProduto = new javax.swing.JLabel();
        lblNomeProd = new javax.swing.JLabel();
        JtextNomeProd = new javax.swing.JTextField();
        lblMarcaProduto = new javax.swing.JLabel();
        JtextMarcaProduto = new javax.swing.JTextField();
        lblModeloProduto = new javax.swing.JLabel();
        JtextModeloProduto = new javax.swing.JTextField();
        lblSerialProduto = new javax.swing.JLabel();
        JtextSerialProduto = new javax.swing.JTextField();
        lblSituacaoProduto = new javax.swing.JLabel();
        lblValorProduto = new javax.swing.JLabel();
        JtextValorProduto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaDefeitoProdudo = new javax.swing.JTextArea();
        lblDefeitoProduto = new javax.swing.JLabel();
        jComboBoxSituacaoProduto = new javax.swing.JComboBox<>();
        jComboBoxTipoProduto = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TabelaListaHardware = new javax.swing.JTable();
        lblPesquisarProdutos = new javax.swing.JLabel();
        TxtPesquisaHardware = new javax.swing.JTextField();
        lblCodigoProduto = new javax.swing.JLabel();
        JtextCodigoCadProdutos = new javax.swing.JTextField();
        Jpanel3 = new javax.swing.JPanel();
        lblPlacaMãeProduto = new javax.swing.JLabel();
        JtextPlacaMaeProduto = new javax.swing.JTextField();
        lblSocketProduto = new javax.swing.JLabel();
        JtextSocketProduto = new javax.swing.JTextField();
        lblProcessadorProduto = new javax.swing.JLabel();
        JtextProcessadorProduto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        AtextDescricaoHard = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        lblMemoriaProduto = new javax.swing.JLabel();
        JtextMemoriaProduto = new javax.swing.JTextField();
        lblFreqMemoryProd = new javax.swing.JLabel();
        JtextFreqMemoriaProd = new javax.swing.JTextField();
        lblFonteProduto = new javax.swing.JLabel();
        JtextFonteProduto = new javax.swing.JTextField();
        lblTipoFonteProduto = new javax.swing.JLabel();
        lblDiscoRigidoProduto = new javax.swing.JLabel();
        JTextDiscoProduto = new javax.swing.JTextField();
        lblTipDisco = new javax.swing.JLabel();
        jComboBoxTipoFonteProduto = new javax.swing.JComboBox<>();
        jComboBoxTipoHDProduto = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        lblSistemaProduto = new javax.swing.JLabel();
        lblVersaoSistemaProduto = new javax.swing.JLabel();
        JtextVersaoSistemaProduto = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TextAreaDescricaoSoftwareProduto = new javax.swing.JTextArea();
        lblDescricaoSoftwareProduto = new javax.swing.JLabel();
        jComboBox1TipoSistema = new javax.swing.JComboBox<>();
        btnAdicionarProduto = new javax.swing.JButton();
        BtnEditarProduto = new javax.swing.JButton();
        BtnExcluirProduto = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(800, 660));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Hardwares / / Softwares"));

        GuiaInformacoesDeSoftwareProduto.setBackground(new java.awt.Color(204, 204, 204));

        lblTipoProduto.setText("Tipo: ");

        lblNomeProd.setText("Nome: ");

        lblMarcaProduto.setText("Marca: ");

        lblModeloProduto.setText("Modelo:");

        lblSerialProduto.setText("Serial Number: ");

        lblSituacaoProduto.setText("Situação: ");

        lblValorProduto.setText("Valor Produto: ");

        TextAreaDefeitoProdudo.setColumns(20);
        TextAreaDefeitoProdudo.setRows(5);
        jScrollPane1.setViewportView(TextAreaDefeitoProdudo);

        lblDefeitoProduto.setText("Defeito: ");

        jComboBoxSituacaoProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Manutenção", "Em Garantia", "Em finalização", "Entregue", "Abandonado pelo Cliente" }));

        jComboBoxTipoProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desktop", "Notebook", "TimClient", "Tablet", "SmartPhone ", "Iphone", "Servidores" }));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamentos Cadastrados"));

        TabelaListaHardware.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Marca", "Modelo"
            }
        ));
        TabelaListaHardware.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaListaHardwareMouseClicked(evt);
            }
        });
        TabelaListaHardware.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TabelaListaHardwareKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(TabelaListaHardware);

        lblPesquisarProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/search_find_lupa_21889.png"))); // NOI18N

        TxtPesquisaHardware.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtPesquisaHardwareKeyReleased(evt);
            }
        });

        lblCodigoProduto.setText("Código: ");

        JtextCodigoCadProdutos.setEditable(false);
        JtextCodigoCadProdutos.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblPesquisarProdutos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtPesquisaHardware, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblCodigoProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JtextCodigoCadProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPesquisarProdutos)
                    .addComponent(TxtPesquisaHardware, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JtextCodigoCadProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDefeitoProduto)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jComboBoxTipoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JtextModeloProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblModeloProduto))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxSituacaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSituacaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(267, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMarcaProduto)
                            .addComponent(JtextMarcaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JtextNomeProd, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblValorProduto)
                            .addComponent(JtextValorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JtextSerialProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSerialProduto)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblTipoProduto)
                                .addComponent(lblNomeProd)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoProduto)
                    .addComponent(lblModeloProduto)
                    .addComponent(lblSituacaoProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JtextModeloProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSituacaoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeProd)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextNomeProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMarcaProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextMarcaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSerialProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextSerialProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblValorProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextValorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(305, 305, 305)
                .addComponent(lblDefeitoProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GuiaInformacoesDeSoftwareProduto.addTab("Informações Gerais do Produto", jPanel2);

        lblPlacaMãeProduto.setText("Placa Mãe: ");

        lblSocketProduto.setText("Socket:");

        lblProcessadorProduto.setText("Processador: ");

        AtextDescricaoHard.setColumns(20);
        AtextDescricaoHard.setRows(5);
        jScrollPane2.setViewportView(AtextDescricaoHard);

        jLabel1.setText("Descrição do Hardware: ");

        lblMemoriaProduto.setText("Memória: ");

        lblFreqMemoryProd.setText("Frequência:");

        lblFonteProduto.setText("Fonte: ");

        lblTipoFonteProduto.setText("Tipo Fonte: ");

        lblDiscoRigidoProduto.setText("Disco Rigído: ");

        lblTipDisco.setText("Tipo Disco: ");

        jComboBoxTipoFonteProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Real", "Genérica", " " }));

        jComboBoxTipoHDProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HD", "SSD", " " }));

        javax.swing.GroupLayout Jpanel3Layout = new javax.swing.GroupLayout(Jpanel3);
        Jpanel3.setLayout(Jpanel3Layout);
        Jpanel3Layout.setHorizontalGroup(
            Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpanel3Layout.createSequentialGroup()
                        .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Jpanel3Layout.createSequentialGroup()
                                .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFonteProduto)
                                    .addComponent(JtextFonteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTipoFonteProduto)
                                    .addComponent(jComboBoxTipoFonteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Jpanel3Layout.createSequentialGroup()
                                .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDiscoRigidoProduto)
                                    .addComponent(JTextDiscoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxTipoHDProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTipDisco))))
                        .addGap(0, 420, Short.MAX_VALUE))
                    .addGroup(Jpanel3Layout.createSequentialGroup()
                        .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblProcessadorProduto)
                                .addGroup(Jpanel3Layout.createSequentialGroup()
                                    .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(Jpanel3Layout.createSequentialGroup()
                                            .addComponent(lblMemoriaProduto)
                                            .addGap(145, 145, 145))
                                        .addGroup(Jpanel3Layout.createSequentialGroup()
                                            .addComponent(JtextMemoriaProduto)
                                            .addGap(44, 44, 44)))
                                    .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblFreqMemoryProd)
                                        .addComponent(JtextFreqMemoriaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpanel3Layout.createSequentialGroup()
                                    .addComponent(JtextPlacaMaeProduto)
                                    .addGap(40, 40, 40)
                                    .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblSocketProduto)
                                        .addComponent(JtextSocketProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(JtextProcessadorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPlacaMãeProduto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(31, 31, 31))))
        );
        Jpanel3Layout.setVerticalGroup(
            Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlacaMãeProduto)
                    .addComponent(lblSocketProduto)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpanel3Layout.createSequentialGroup()
                        .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JtextPlacaMaeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JtextSocketProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblProcessadorProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextProcessadorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMemoriaProduto)
                            .addComponent(lblFreqMemoryProd)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JtextMemoriaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JtextFreqMemoriaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Jpanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblFonteProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JtextFonteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDiscoRigidoProduto)
                            .addComponent(lblTipDisco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Jpanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTextDiscoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTipoHDProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Jpanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTipoFonteProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipoFonteProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        GuiaInformacoesDeSoftwareProduto.addTab("Informações de Hardware", Jpanel3);

        lblSistemaProduto.setText("Sistema Operacional: ");

        lblVersaoSistemaProduto.setText("Versão: ");

        TextAreaDescricaoSoftwareProduto.setColumns(20);
        TextAreaDescricaoSoftwareProduto.setRows(5);
        jScrollPane3.setViewportView(TextAreaDescricaoSoftwareProduto);

        lblDescricaoSoftwareProduto.setText("Descrição: ");

        jComboBox1TipoSistema.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Windows", "Linux ", "Android ", " " }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVersaoSistemaProduto)
                            .addComponent(JtextVersaoSistemaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescricaoSoftwareProduto)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSistemaProduto))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBox1TipoSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSistemaProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1TipoSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(lblVersaoSistemaProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JtextVersaoSistemaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblDescricaoSoftwareProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        GuiaInformacoesDeSoftwareProduto.addTab("Informações de Software", jPanel3);

        btnAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/ADDCli.png"))); // NOI18N
        btnAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarProdutoActionPerformed(evt);
            }
        });

        BtnEditarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/editCli1.png"))); // NOI18N
        BtnEditarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarProdutoActionPerformed(evt);
            }
        });

        BtnExcluirProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/document-delete_114476.png"))); // NOI18N
        BtnExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GuiaInformacoesDeSoftwareProduto)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(btnAdicionarProduto)
                .addGap(18, 18, 18)
                .addComponent(BtnEditarProduto)
                .addGap(18, 18, 18)
                .addComponent(BtnExcluirProduto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GuiaInformacoesDeSoftwareProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAdicionarProduto)
                    .addComponent(BtnEditarProduto)
                    .addComponent(BtnExcluirProduto))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoActionPerformed
        adicionar_hardware();
    }//GEN-LAST:event_btnAdicionarProdutoActionPerformed

    private void TabelaListaHardwareKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TabelaListaHardwareKeyReleased
        //
    }//GEN-LAST:event_TabelaListaHardwareKeyReleased

    private void TabelaListaHardwareMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaListaHardwareMouseClicked
        setar_campos();
    }//GEN-LAST:event_TabelaListaHardwareMouseClicked

    private void TxtPesquisaHardwareKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtPesquisaHardwareKeyReleased
        pequisar_hardware();
    }//GEN-LAST:event_TxtPesquisaHardwareKeyReleased

    private void BtnEditarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarProdutoActionPerformed
        // TODO add your handling code here:
        alterar_produto();
    }//GEN-LAST:event_BtnEditarProdutoActionPerformed

    private void BtnExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirProdutoActionPerformed
        // TODO add your handling code here:
        excluir_produto();
    }//GEN-LAST:event_BtnExcluirProdutoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AtextDescricaoHard;
    private javax.swing.JButton BtnEditarProduto;
    private javax.swing.JButton BtnExcluirProduto;
    private javax.swing.JTabbedPane GuiaInformacoesDeSoftwareProduto;
    private javax.swing.JTextField JTextDiscoProduto;
    private javax.swing.JPanel Jpanel3;
    private javax.swing.JTextField JtextCodigoCadProdutos;
    private javax.swing.JTextField JtextFonteProduto;
    private javax.swing.JTextField JtextFreqMemoriaProd;
    private javax.swing.JTextField JtextMarcaProduto;
    private javax.swing.JTextField JtextMemoriaProduto;
    private javax.swing.JTextField JtextModeloProduto;
    private javax.swing.JTextField JtextNomeProd;
    private javax.swing.JTextField JtextPlacaMaeProduto;
    private javax.swing.JTextField JtextProcessadorProduto;
    private javax.swing.JTextField JtextSerialProduto;
    private javax.swing.JTextField JtextSocketProduto;
    private javax.swing.JTextField JtextValorProduto;
    private javax.swing.JTextField JtextVersaoSistemaProduto;
    private javax.swing.JTable TabelaListaHardware;
    private javax.swing.JTextArea TextAreaDefeitoProdudo;
    private javax.swing.JTextArea TextAreaDescricaoSoftwareProduto;
    private javax.swing.JTextField TxtPesquisaHardware;
    private javax.swing.JButton btnAdicionarProduto;
    private javax.swing.JComboBox<String> jComboBox1TipoSistema;
    private javax.swing.JComboBox<String> jComboBoxSituacaoProduto;
    private javax.swing.JComboBox<String> jComboBoxTipoFonteProduto;
    private javax.swing.JComboBox<String> jComboBoxTipoHDProduto;
    private javax.swing.JComboBox<String> jComboBoxTipoProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblCodigoProduto;
    private javax.swing.JLabel lblDefeitoProduto;
    private javax.swing.JLabel lblDescricaoSoftwareProduto;
    private javax.swing.JLabel lblDiscoRigidoProduto;
    private javax.swing.JLabel lblFonteProduto;
    private javax.swing.JLabel lblFreqMemoryProd;
    private javax.swing.JLabel lblMarcaProduto;
    private javax.swing.JLabel lblMemoriaProduto;
    private javax.swing.JLabel lblModeloProduto;
    private javax.swing.JLabel lblNomeProd;
    private javax.swing.JLabel lblPesquisarProdutos;
    private javax.swing.JLabel lblPlacaMãeProduto;
    private javax.swing.JLabel lblProcessadorProduto;
    private javax.swing.JLabel lblSerialProduto;
    private javax.swing.JLabel lblSistemaProduto;
    private javax.swing.JLabel lblSituacaoProduto;
    private javax.swing.JLabel lblSocketProduto;
    private javax.swing.JLabel lblTipDisco;
    private javax.swing.JLabel lblTipoFonteProduto;
    private javax.swing.JLabel lblTipoProduto;
    private javax.swing.JLabel lblValorProduto;
    private javax.swing.JLabel lblVersaoSistemaProduto;
    // End of variables declaration//GEN-END:variables
}
