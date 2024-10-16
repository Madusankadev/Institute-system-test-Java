/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import model.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Madusanka
 */
public class MarkAttendance extends javax.swing.JDialog {

    Attendance attendance;

    private String studentId = "0";
    private String subjectId = "0";
    private String sId = "0";

    public MarkAttendance(java.awt.Frame parent, boolean model, String sId) {
        super(parent, model);
        initComponents();
        studentId = sId;
        setStudentData();
        attendance = (Attendance) parent;
    }

    private void setStudentData() {
        jLabel6.setText(studentId);

        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();

        String newD = String.valueOf(dayOfWeek);

        HashMap<String, Integer> daysMap = new HashMap<>();
        daysMap.put("MONDAY", 1);
        daysMap.put("TUESDAY", 2);
        daysMap.put("WEDNESDAY", 3);
        daysMap.put("THURSDAY", 4);
        daysMap.put("FRIDAY", 5);
        daysMap.put("SATURDAY", 6);
        daysMap.put("SUNDAY", 7);

        int dayId = daysMap.getOrDefault(newD, -1);  // -1 if newD is not a valid day

        try {
            ResultSet result = MySQL.execute("SELECT * FROM `student` WHERE `sCardId` = '" + studentId + "'");

            while (result.next()) {
                jLabel5.setText(String.valueOf(result.getString("firstName")) + " " + String.valueOf(result.getString("lastName")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            ResultSet resultSet = MySQL.execute("SELECT * FROM `student_has_subject` INNER JOIN `subject` ON `student_has_subject`.`subject_id` = `subject`.`id`  INNER JOIN `student` ON `student_has_subject`.`student_id` = `student`.`id` WHERE `sCardId` = '" + studentId + "' AND `subject_id` IN (SELECT `subject_id` FROM `subject_has_day` INNER JOIN `day` ON `subject_has_day`.`day_id` = `day`.`id` WHERE `day`.`day` = '" + newD + "')");
//            System.out.println(resultSet);

            if (resultSet.next()) {
                jLabel7.setText(String.valueOf(resultSet.getString("subject.sub")));
                subjectId = String.valueOf(resultSet.getString("subject_id"));
                sId = String.valueOf(resultSet.getString("student.id"));

            } else {
                jLabel7.setText("No Class Today");
                jLabel7.setForeground(new Color(255, 0, 0));
                jButton1.setVisible(false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //set payment label
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM");
            Date m = new Date();

            String month = format.format(m);
            System.out.println(month);
            ResultSet monthResult = MySQL.execute("SELECT * FROM `payment` WHERE `student_id` = '" + sId + "' AND `subject_id` = '" + subjectId + "' AND `month_id` = '" + month + "'");

            System.out.println("SELECT * FROM `payment` WHERE `student_id` = '" + sId + "' AND `subject_id` = '" + subjectId + "' AND `month_id` = '" + month + "'");

            if (monthResult.next()) {
                jLabel8.setText("Payment Done");
            } else {
                jLabel8.setText("You Have to Pay for This Month");
                jLabel8.setForeground(new Color(255, 0, 0));
            }

        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel1.setText("Student Name");

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel2.setText("Student ID");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel3.setText("Subject");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel4.setText("Payment");

        jButton1.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jButton1.setText("Mark Attendance");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel5.setText("jLabel5");

        jLabel6.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel6.setText("jLabel6");

        jLabel7.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel7.setText("jLabel7");

        jLabel8.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addGap(54, 54, 54)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        Mark Attendence

        Date t = new Date();
        SimpleDateFormat tFormat = new SimpleDateFormat("hh:mm:ss");

        Date d = new Date();
        SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            MySQL.executeIUD("INSERT INTO `attendance` (`state`, `checkIn`, `date`, `student_id`) VALUES ('1', '" + tFormat.format(t) + "', '" + dFormat.format(d) + "', '" + sId + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "Attendance Marked", "Sucess", JOptionPane.INFORMATION_MESSAGE);

        this.dispose();


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatLightLaf.setup();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new MarkAttendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
