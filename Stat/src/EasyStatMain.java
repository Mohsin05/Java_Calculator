
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mohsin
 */
public class EasyStatMain extends javax.swing.JFrame {

    /**
     * Creates new form EasyStatMain
     */
    private Connection connection = null;
    private PreparedStatement pst = null;
    private ResultSet resultset = null;
    private byte[] personImg=null;
    private String filePath = null;

    public EasyStatMain() {
        initComponents();
        init();
        connection = javaDbConnect.dbConnect();
        upDateStudentInfo();
        updateStudentShortInfo();
        currentDate();
    }

    private void currentDate() {
        //static date and time

        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        menuDate.setText("Current Date: " + day + "/" + (month + 1) + "/" + year);
        menuDate.setForeground(Color.blue);

        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        menuTime.setText("Current Time: " + hour + ":" + minute + ":" + second);
        menuTime.setForeground(Color.red);

    }

    private void upDateStudentInfo() {

        try {
            String sql = "select Student_id, First_name, Last_name, Department, Series, Age, Height, Weight ,Gender,"
                    + "Blood from Student_info";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            tbStudentInfo.setModel(DbUtils.resultSetToTableModel(resultset));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }

    }

    private void updateStudentShortInfo() {

        try {
            String sql = "select Student_id, First_name from Student_info";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            tbStudentShortInfo.setModel(DbUtils.resultSetToTableModel(resultset));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }

    }

    public void init() {

        setLocationRelativeTo(null);
        //By the piece of code below we are actually limiting the close of window 
        // that when the close method is called only the window below will be close
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

    }

    ;
     
    
    private void setValues() {

        try {
            txtStudentId.setText(resultset.getString("Student_id"));
            txtStdentFname.setText(resultset.getString("First_name"));
            txtStdentLname.setText(resultset.getString("Last_name"));
            txtStdentDept.setText(resultset.getString("Department"));
            txtStdentSeries.setText(resultset.getString("Series"));
            txtStdentAge.setText(resultset.getString("Age"));
            txtStdentHeight.setText(resultset.getString("Height"));
            txtStdentWeight.setText(resultset.getString("Weight"));
            txtStdentBlood.setText(resultset.getString("Blood"));
            comboStudentId.setSelectedItem(resultset.getString("Gender"));
            byte[] imageData = resultset.getBytes("Photo");        
            ImageIcon format = new ImageIcon(scaledImage(imageData,labelImage.getWidth(),labelImage.getHeight()));
            labelImage.setIcon(format);        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }

    };
    
    private Image scaledImage(byte[]img,int w, int h){
    
    BufferedImage resizedImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    
    try{
    
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        //convert byte array back to buffered image
        
        ByteArrayInputStream in = new ByteArrayInputStream(img);
        BufferedImage bImageFromConvert = ImageIO.read(in);
        g2.drawImage(bImageFromConvert,0,0,w,h,null);
        g2.dispose();
        
    
    }catch(Exception e){ JOptionPane.showMessageDialog(rootPane,e);}
         
   return resizedImage;
    };
     
    public void close() {

        WindowEvent winCloseingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winCloseingEvent);

    }

    ;
     
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbStudentInfo = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtSub = new javax.swing.JTextField();
        tAMsgBody = new java.awt.TextArea();
        txtAttachFile = new javax.swing.JTextField();
        btnAttach = new javax.swing.JButton();
        txtAttachName = new javax.swing.JTextField();
        txtAttachNme = new javax.swing.JLabel();
        btnsndMail = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboStudentId = new javax.swing.JComboBox<>();
        txtStudentId = new javax.swing.JTextField();
        txtStdentLname = new javax.swing.JTextField();
        txtStdentFname = new javax.swing.JTextField();
        txtStdentDept = new javax.swing.JTextField();
        txtStdentSeries = new javax.swing.JTextField();
        txtStdentAge = new javax.swing.JTextField();
        txtStdentHeight = new javax.swing.JTextField();
        txtStdentBlood = new javax.swing.JTextField();
        txtStdentWeight = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbStudentShortInfo = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        txtImageUpload = new javax.swing.JTextField();
        btnImageUpload = new javax.swing.JButton();
        btnImageSave = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        labelImage = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mItclose = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        mItOffHelp = new javax.swing.JMenuItem();
        mItWebHelp = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuDate = new javax.swing.JMenu();
        menuTime = new javax.swing.JMenu();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1179, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.png"))); // NOI18N
        jButton1.setText("Sign out");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/help.png"))); // NOI18N
        btnHelp.setText("Help");
        btnHelp.setFocusable(false);
        btnHelp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHelp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });
        jToolBar1.add(btnHelp);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 153)), "Action Panel", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 0, 153))); // NOI18N

        tbStudentInfo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbStudentInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbStudentInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbStudentInfoMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbStudentInfo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 482, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 179, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" Data table ", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1163, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(" Chart ", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1163, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(" Statistics ", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1163, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(" Documents ", jPanel5);

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("From");
        jPanel15.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 26, -1, -1));
        jPanel15.add(txtFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 23, 161, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Password");
        jPanel15.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 57, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 0));
        jLabel14.setText("To");
        jPanel15.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 88, -1, -1));

        txtTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtToActionPerformed(evt);
            }
        });
        jPanel15.add(txtTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 88, 161, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 0));
        jLabel15.setText("Subject");
        jPanel15.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 122, 54, -1));

        txtSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubActionPerformed(evt);
            }
        });
        jPanel15.add(txtSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 119, 161, -1));
        jPanel15.add(tAMsgBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 23, 300, 128));

        txtAttachFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAttachFileActionPerformed(evt);
            }
        });
        jPanel15.add(txtAttachFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 163, 182, -1));

        btnAttach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/attach.png"))); // NOI18N
        btnAttach.setText("Attach");
        btnAttach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachActionPerformed(evt);
            }
        });
        jPanel15.add(btnAttach, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 161, 108, -1));

        txtAttachName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAttachNameActionPerformed(evt);
            }
        });
        jPanel15.add(txtAttachName, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 192, 182, -1));

        txtAttachNme.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtAttachNme.setForeground(new java.awt.Color(0, 51, 204));
        txtAttachNme.setText("Attachment name");
        jPanel15.add(txtAttachNme, new org.netbeans.lib.awtextra.AbsoluteConstraints(575, 195, 108, -1));

        btnsndMail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/email.png"))); // NOI18N
        btnsndMail.setText("Send Mail");
        btnsndMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsndMailActionPerformed(evt);
            }
        });
        jPanel15.add(btnsndMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 218, 300, 43));

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        jPanel15.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 54, 161, -1));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" Email ", jPanel13);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setText("Welcome To Easy Stats Software ");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Commands", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Agency FB", 1, 14), new java.awt.Color(0, 0, 102))); // NOI18N

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/clear.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Student Info", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel8KeyReleased(evt);
            }
        });

        jLabel1.setText("Student Id");

        jLabel2.setText("First Name");

        jLabel3.setText("Last Name");

        jLabel4.setText("Department");

        jLabel5.setText("Series");

        jLabel6.setText("Age");

        jLabel7.setText("Height");

        jLabel8.setText("Weight");

        jLabel9.setText("Gender");

        jLabel10.setText("Blood");

        comboStudentId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        txtStudentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStudentIdActionPerformed(evt);
            }
        });

        txtStdentLname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStdentLnameActionPerformed(evt);
            }
        });

        txtStdentFname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStdentFnameActionPerformed(evt);
            }
        });

        txtStdentDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStdentDeptActionPerformed(evt);
            }
        });

        txtStdentAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStdentAgeActionPerformed(evt);
            }
        });

        txtStdentHeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStdentHeightActionPerformed(evt);
            }
        });

        txtStdentBlood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStdentBloodActionPerformed(evt);
            }
        });

        txtStdentWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStdentWeightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtStdentLname)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtStdentFname))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtStdentDept))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(38, 38, 38)
                                .addComponent(txtStdentSeries))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(43, 43, 43)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStdentBlood, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(txtStdentHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStdentAge, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboStudentId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStdentWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtStdentAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtStdentHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtStdentWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(comboStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtStdentBlood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtStudentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStdentFname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtStdentLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtStdentDept, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtStdentSeries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbStudentShortInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbStudentShortInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbStudentShortInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbStudentShortInfoMouseClicked(evt);
            }
        });
        tbStudentShortInfo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbStudentShortInfoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbStudentShortInfo);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtImageUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImageUploadActionPerformed(evt);
            }
        });

        btnImageUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/attach.png"))); // NOI18N
        btnImageUpload.setText("Upload");
        btnImageUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageUploadActionPerformed(evt);
            }
        });

        btnImageSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btnImageSave.setText("Save");
        btnImageSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageSaveActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(labelImage, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelImage, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(txtImageUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImageUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnImageSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImageUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtImageUpload))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnImageSave)
                .addGap(0, 0, 0))
        );

        jMenu1.setText(" File ");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        mItclose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mItclose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.png"))); // NOI18N
        mItclose.setText("Close");
        mItclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItcloseActionPerformed(evt);
            }
        });
        jMenu1.add(mItclose);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(" Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText(" Help");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        mItOffHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        mItOffHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/help.png"))); // NOI18N
        mItOffHelp.setText("Offline Help");
        mItOffHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItOffHelpActionPerformed(evt);
            }
        });
        jMenu3.add(mItOffHelp);

        mItWebHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        mItWebHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Web_Help.png"))); // NOI18N
        mItWebHelp.setText("Web Help");
        mItWebHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItWebHelpActionPerformed(evt);
            }
        });
        jMenu3.add(mItWebHelp);

        jMenuBar1.add(jMenu3);

        jMenu4.setText(" About");
        jMenuBar1.add(jMenu4);

        menuDate.setText(" Date");
        jMenuBar1.add(menuDate);

        menuTime.setText(" Time");
        jMenuBar1.add(menuTime);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mItcloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItcloseActionPerformed
        // TODO add your handling code here:

        try {
            close();
            LogIn Login = new LogIn();
            Login.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        } finally {
            try {
//                resultset.close();
//                pst.close();
                connection.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }

    }//GEN-LAST:event_mItcloseActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {
            close();
            LogIn Login = new LogIn();
            Login.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        } finally {
            try {
                resultset.close();
                pst.close();
                connection.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void txtStudentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStudentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentIdActionPerformed

    private void txtStdentLnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStdentLnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStdentLnameActionPerformed

    private void txtStdentFnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStdentFnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStdentFnameActionPerformed

    private void txtStdentDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStdentDeptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStdentDeptActionPerformed

    private void txtStdentAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStdentAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStdentAgeActionPerformed

    private void txtStdentHeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStdentHeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStdentHeightActionPerformed

    private void txtStdentBloodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStdentBloodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStdentBloodActionPerformed

    private void txtStdentWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStdentWeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStdentWeightActionPerformed

    private void tbStudentShortInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStudentShortInfoMouseClicked
        try {
            // TODO add your handling code here:

            int row = tbStudentShortInfo.getSelectedRow();
            String tableClick = (tbStudentShortInfo.getModel().getValueAt(row, 0).toString());
            String sql = "select * from Student_info where Student_id ='" + tableClick + "'";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            if (resultset.next()) {
                setValues();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }


    }//GEN-LAST:event_tbStudentShortInfoMouseClicked

    private void tbStudentShortInfoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbStudentShortInfoKeyReleased
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            try {
                // TODO add your handling code here:

                int row = tbStudentShortInfo.getSelectedRow();
                String tableClick = (tbStudentShortInfo.getModel().getValueAt(row, 0).toString());
                String sql = "select * from Student_info where Student_id ='" + tableClick + "'";
                pst = connection.prepareStatement(sql);
                resultset = pst.executeQuery();
                if (resultset.next()) {

                    setValues();

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
            }

        }


    }//GEN-LAST:event_tbStudentShortInfoKeyReleased

    private void jPanel8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel8KeyReleased

    private void tbStudentInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbStudentInfoMousePressed
        // TODO add your handling code here:

        try {
            // TODO add your handling code here:

            int row = tbStudentInfo.getSelectedRow();
            String tableClick = (tbStudentInfo.getModel().getValueAt(row, 0).toString());
            String sql = "select * from Student_info where Student_id ='" + tableClick + "'";
            pst = connection.prepareStatement(sql);
            resultset = pst.executeQuery();
            if (resultset.next()) {
                setValues();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }


    }//GEN-LAST:event_tbStudentInfoMousePressed


    private void mItOffHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItOffHelpActionPerformed
        // TODO add your handling code here:

        try {

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler" + "help.pdf");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "Error Opening File!");

        }


    }//GEN-LAST:event_mItOffHelpActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        // TODO add your handling code here:

        try {

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler" + "help.pdf");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(rootPane, "Error Opening File!");

        }
    }//GEN-LAST:event_btnHelpActionPerformed

    private void mItWebHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItWebHelpActionPerformed
        try {
            // TODO add your handling code here:

            String url = "https://www.google.de";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error Opening File!");

        }


    }//GEN-LAST:event_mItWebHelpActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

        String sql = "select * from Student_info where first_name=?";
        try {
            // TODO add your handling code here:

            pst = connection.prepareStatement(sql);
            pst.setString(1, txtSearch.getText());
            resultset = pst.executeQuery();
            if (resultset.next()) {
                setValues();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }

        String sql2 = "select * from Student_info where Student_id=?";
        try {
            // TODO add your handling code here:

            pst = connection.prepareStatement(sql2);
            pst.setString(1, txtSearch.getText());
            resultset = pst.executeQuery();
            if (resultset.next()) {
                setValues();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }

    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
     
            // TODO add your handling code here:
            
            txtStudentId.setText(null);
            txtStdentFname.setText(null);
            txtStdentLname.setText(null);
            txtStdentSeries.setText(null);
            txtStdentWeight.setText(null);
            txtStdentAge.setText(null);
            txtStdentBlood.setText(null);
            txtStdentDept.setText(null);
            txtStdentHeight.setText(null);
            comboStudentId.setSelectedItem(null);
            
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        
        
        String sql= "insert into Student_info(Student_id,First_name,Last_name,Department,"
                + "Series,Age,Height,Weight,Gender,Blood) values(?,?,?,?,?,?,?,?,?,?)";
      try{
          pst = connection.prepareStatement(sql);
          pst.setString(1,txtStudentId.getText());
          pst.setString(2,txtStdentFname.getText());
          pst.setString(3,txtStdentLname.getText());
          pst.setString(4,txtStdentDept.getText());
          pst.setString(5,txtStdentSeries.getText());
          pst.setString(6,txtStdentAge.getText());    
          pst.setString(7,txtStdentBlood.getText());
          pst.setString(8,txtStdentHeight.getText());
          pst.setString(9,(String)comboStudentId.getSelectedItem());
          pst.setString(10,txtStdentWeight.getText());

          pst.execute();
          JOptionPane.showMessageDialog(rootPane, "Saved");
      
      }catch(Exception e){
                JOptionPane.showMessageDialog(rootPane, e);

          
          
      }
        
        
        
        updateStudentShortInfo();
        upDateStudentInfo();
        
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        
        
        
         
        String sql= "update Student_info set First_name=?,Last_name=?,Department=?,"
                + "Series=?, Age=?,Height=?,Weight=?,Gender=?,Blood=? where Student_id=?";
      try{
          pst = connection.prepareStatement(sql);
          
          pst.setString(1,txtStdentFname.getText());
          pst.setString(2,txtStdentLname.getText());
          pst.setString(3,txtStdentDept.getText());
          pst.setString(4,txtStdentSeries.getText());
          pst.setString(5,txtStdentAge.getText());    
          pst.setString(6,txtStdentBlood.getText());
          pst.setString(7,txtStdentHeight.getText());
          pst.setString(8,(String)comboStudentId.getSelectedItem());
          pst.setString(9,txtStdentWeight.getText());
          pst.setString(10, txtStudentId.getText());

          pst.execute();
          JOptionPane.showMessageDialog(rootPane, "Updated");
      
      }catch(Exception e){
                JOptionPane.showMessageDialog(rootPane, e);
 
      }
      
        updateStudentShortInfo();
        upDateStudentInfo();
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        
        
        
       int p=JOptionPane.showConfirmDialog(rootPane,"Do you really want to Delete?","Delete",JOptionPane.YES_NO_OPTION);
       
       if(p==0){
       
           String sql="delete from Student_info where Student_id=?";
           
           try{
           
           pst=connection.prepareStatement(sql);
           pst.setString(1,txtStudentId.getText());
           pst.execute();
           JOptionPane.showMessageDialog(rootPane, "Deleted!");
           }
           catch(SQLException e){
           
           JOptionPane.showMessageDialog(rootPane, e);}
           
       }
        
        updateStudentShortInfo();
        upDateStudentInfo();
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtImageUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImageUploadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImageUploadActionPerformed

    private void btnImageUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageUploadActionPerformed
        // TODO add your handling code here:
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
         String fileName=f.getAbsolutePath();
         txtImageUpload.setText(fileName);
        
         try{
         
         FileInputStream fIS = new FileInputStream(f);
         ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
         byte[] buf = new byte[1024];
         for(int readNum;(readNum=fIS.read(buf))!=-1;){
         
             bAOS.write(buf,0,readNum);
         };
         personImg = bAOS.toByteArray();
         
         }catch(Exception e){
         JOptionPane.showMessageDialog(rootPane, e);
         }                 
    }//GEN-LAST:event_btnImageUploadActionPerformed

    private void btnImageSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageSaveActionPerformed
        // TODO add your handling code here:
        
        String sql = "update Student_info set Photo=? where Student_id=?";
        
        try{
        pst = connection.prepareStatement(sql);
        pst.setBytes(1,personImg);
        pst.setString(2,txtStudentId.getText());
        pst.execute();
        JOptionPane.showMessageDialog(rootPane, "Image Saved");
        
        }catch(Exception e ){
        JOptionPane.showMessageDialog(rootPane, e);
        } 
    }//GEN-LAST:event_btnImageSaveActionPerformed

    private void txtToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtToActionPerformed

    private void txtSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubActionPerformed

    private void btnsndMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsndMailActionPerformed
        // TODO add your handling code here:
        
        final String From = txtFrom.getText();
        final String password = txtPassword.getText();
        
        String To = txtTo.getText();
        String Subject = txtSub.getText();
        String txtMessage = tAMsgBody.getText();
        
      Properties pros = new Properties();
      pros.put("mail.smtp.host","smtp.gmail.com");
      pros.put("mail.smtp.socketFactory.port","465"); //ssl protocol port no is 465
      pros.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      pros.put("mail.smtp.auth","true");
      pros.put("mail.smtp.port","465");
         
      Session session = Session.getDefaultInstance(pros,new javax.mail.Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication(){
             
          return new PasswordAuthentication(From,password);
          }
       });
      
      try{
      
      //message header
      Message message= new MimeMessage(session);
      message.setFrom(new InternetAddress(From));
      message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(To));
      message.setSubject(Subject);
     
      //code for set the text message
      
      MimeBodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setText(txtMessage);
      Multipart multiPart = new MimeMultipart();
      multiPart.addBodyPart(messageBodyPart);
      
      
      //code for attach file
      
      messageBodyPart = new MimeBodyPart();
      DataSource source = new FileDataSource(filePath);
      messageBodyPart.setDataHandler(new DataHandler(source));
      messageBodyPart.setFileName(txtAttachName.getText());
      multiPart.addBodyPart(messageBodyPart);
      
      message.setContent(multiPart);
      Transport.send(message);
      JOptionPane.showMessageDialog(rootPane,"Message sent");
      
      }catch(MessagingException e){
          JOptionPane.showMessageDialog(rootPane,e);}
      
      
        
    }//GEN-LAST:event_btnsndMailActionPerformed

    private void txtAttachFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAttachFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAttachFileActionPerformed

    private void txtAttachNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAttachNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAttachNameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachActionPerformed
        // TODO add your handling code here:
        
        
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(rootPane);
        
        File f=chooser.getSelectedFile();
        filePath = f.getAbsolutePath();
        txtAttachFile.setText(filePath);
        txtAttachName.setText(filePath);
        
        
        
        
        
        
    }//GEN-LAST:event_btnAttachActionPerformed

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
                if ("window".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EasyStatMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EasyStatMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EasyStatMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EasyStatMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EasyStatMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAttach;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnImageSave;
    private javax.swing.JButton btnImageUpload;
    private javax.swing.JButton btnsndMail;
    private javax.swing.JComboBox<String> comboStudentId;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel labelImage;
    private javax.swing.JMenuItem mItOffHelp;
    private javax.swing.JMenuItem mItWebHelp;
    private javax.swing.JMenuItem mItclose;
    private javax.swing.JMenu menuDate;
    private javax.swing.JMenu menuTime;
    private java.awt.TextArea tAMsgBody;
    private javax.swing.JTable tbStudentInfo;
    private javax.swing.JTable tbStudentShortInfo;
    private javax.swing.JTextField txtAttachFile;
    private javax.swing.JTextField txtAttachName;
    private javax.swing.JLabel txtAttachNme;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtImageUpload;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStdentAge;
    private javax.swing.JTextField txtStdentBlood;
    private javax.swing.JTextField txtStdentDept;
    private javax.swing.JTextField txtStdentFname;
    private javax.swing.JTextField txtStdentHeight;
    private javax.swing.JTextField txtStdentLname;
    private javax.swing.JTextField txtStdentSeries;
    private javax.swing.JTextField txtStdentWeight;
    private javax.swing.JTextField txtStudentId;
    private javax.swing.JTextField txtSub;
    private javax.swing.JTextField txtTo;
    // End of variables declaration//GEN-END:variables
}
