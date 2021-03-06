package TriangleRasterisation;

import TriangleRasterisation.DrawingPanel.COLOR_MODE;
import TriangleRasterisation.DrawingPanel.TRIANGLE_ALGORITHM;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TriangleRasterisationApplet.java
 *
 * Created on 06.12.2011, 19:16:38
 */
/**
 *
 * @author SUN2K
 */
public class TriangleRasterizationApplet extends javax.swing.JApplet {

    private DrawingPanel drawPanel;
    
    /** Initializes the applet TriangleRasterisationApplet */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void init() {
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
            java.util.logging.Logger.getLogger(TriangleRasterizationApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TriangleRasterizationApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TriangleRasterizationApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TriangleRasterizationApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    initComponents();
                    
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        jButton1.setVisible(false);
        setSize(730, 330);
        drawPanel = new DrawingPanel(500, 330);
        getContentPane().add(drawPanel);
       
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        Graphics gPanel = jPanel1.getGraphics();
        
        gPanel.setColor(drawPanel.getColorV1());
        gPanel.fillRect(chooseColorV1Button.getX() + chooseColorV1Button.getWidth() + 10, 
                   chooseColorV1Button.getY() + 5, 15, 10);
        
        gPanel.setColor(drawPanel.getColorV2());
        gPanel.fillRect(chooseColorV2Button.getX() + chooseColorV2Button.getWidth() + 10, 
                   chooseColorV2Button.getY() + 5, 15, 10);
        
        gPanel.setColor(drawPanel.getColorV3());
        gPanel.fillRect(chooseColorV3Button.getX() + chooseColorV3Button.getWidth() + 10, 
                   chooseColorV3Button.getY() + 5, 15, 10);
        
        gPanel.setColor(drawPanel.getSolidColor());
        gPanel.fillRect(chooseColorButton.getX() + chooseColorButton.getWidth() + 10, 
                   chooseColorButton.getY() + 5, 15, 10);
        
    }
    

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rasterizationAlgorithmGroup = new javax.swing.ButtonGroup();
        colorModeGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        standardAlgoRadioButton = new javax.swing.JRadioButton();
        barycentricAlgoRadioButton = new javax.swing.JRadioButton();
        solidColorModeRadioButton = new javax.swing.JRadioButton();
        hideVerticesCheckBox = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        chooseColorButton = new javax.swing.JButton();
        edgeColorModeRadioButton = new javax.swing.JRadioButton();
        chooseColorV3Button = new javax.swing.JButton();
        chooseColorV1Button = new javax.swing.JButton();
        chooseColorV2Button = new javax.swing.JButton();
        bresenhamIntAlgoRadioButton1 = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        rasterizationAlgorithmGroup.add(standardAlgoRadioButton);
        standardAlgoRadioButton.setFont(new java.awt.Font("Tahoma", 0, 10));
        standardAlgoRadioButton.setSelected(true);
        standardAlgoRadioButton.setText("Standard Algorithm");
        standardAlgoRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standardAlgoRadioButtonActionPerformed(evt);
            }
        });
        jPanel1.add(standardAlgoRadioButton);
        standardAlgoRadioButton.setBounds(10, 10, 170, 21);

        rasterizationAlgorithmGroup.add(barycentricAlgoRadioButton);
        barycentricAlgoRadioButton.setFont(new java.awt.Font("Tahoma", 0, 10));
        barycentricAlgoRadioButton.setText("Barycentric Algorithm");
        barycentricAlgoRadioButton.setActionCommand("Integer Algorithm");
        barycentricAlgoRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barycentricAlgoRadioButtonActionPerformed(evt);
            }
        });
        jPanel1.add(barycentricAlgoRadioButton);
        barycentricAlgoRadioButton.setBounds(10, 70, 200, 21);

        colorModeGroup.add(solidColorModeRadioButton);
        solidColorModeRadioButton.setFont(new java.awt.Font("Tahoma", 0, 10));
        solidColorModeRadioButton.setSelected(true);
        solidColorModeRadioButton.setText("Solid Color");
        solidColorModeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solidColorModeRadioButtonActionPerformed(evt);
            }
        });
        jPanel1.add(solidColorModeRadioButton);
        solidColorModeRadioButton.setBounds(10, 120, 75, 21);

        hideVerticesCheckBox.setText("Hide vertices");
        hideVerticesCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideVerticesCheckBoxActionPerformed(evt);
            }
        });
        jPanel1.add(hideVerticesCheckBox);
        hideVerticesCheckBox.setBounds(10, 290, 120, 23);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(130, 300, 90, 23);

        chooseColorButton.setFont(new java.awt.Font("Tahoma", 0, 10));
        chooseColorButton.setText("Solid Color");
        chooseColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseColorButtonActionPerformed(evt);
            }
        });
        jPanel1.add(chooseColorButton);
        chooseColorButton.setBounds(100, 120, 83, 21);

        colorModeGroup.add(edgeColorModeRadioButton);
        edgeColorModeRadioButton.setFont(new java.awt.Font("Tahoma", 0, 10));
        edgeColorModeRadioButton.setText("Edge Colors");
        edgeColorModeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edgeColorModeRadioButtonActionPerformed(evt);
            }
        });
        jPanel1.add(edgeColorModeRadioButton);
        edgeColorModeRadioButton.setBounds(10, 160, 81, 21);

        chooseColorV3Button.setFont(new java.awt.Font("Tahoma", 0, 10));
        chooseColorV3Button.setText("Color V3");
        chooseColorV3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseColorV3ButtonActionPerformed(evt);
            }
        });
        jPanel1.add(chooseColorV3Button);
        chooseColorV3Button.setBounds(100, 220, 83, 21);

        chooseColorV1Button.setFont(new java.awt.Font("Tahoma", 0, 10));
        chooseColorV1Button.setText("Color V1");
        chooseColorV1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseColorV1ButtonActionPerformed(evt);
            }
        });
        jPanel1.add(chooseColorV1Button);
        chooseColorV1Button.setBounds(100, 160, 83, 21);

        chooseColorV2Button.setFont(new java.awt.Font("Tahoma", 0, 10));
        chooseColorV2Button.setText("Color V2");
        chooseColorV2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseColorV2ButtonActionPerformed(evt);
            }
        });
        jPanel1.add(chooseColorV2Button);
        chooseColorV2Button.setBounds(100, 190, 83, 21);

        rasterizationAlgorithmGroup.add(bresenhamIntAlgoRadioButton1);
        bresenhamIntAlgoRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 10));
        bresenhamIntAlgoRadioButton1.setText("Integer Algorithm (slow)");
        bresenhamIntAlgoRadioButton1.setActionCommand("Integer Algorithm");
        bresenhamIntAlgoRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bresenhamIntAlgoRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(bresenhamIntAlgoRadioButton1);
        bresenhamIntAlgoRadioButton1.setBounds(10, 40, 200, 21);
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(0, 330, 230, 2);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(0, 100, 230, 10);
        jPanel1.add(jSeparator3);
        jSeparator3.setBounds(0, 270, 230, 10);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(500, 0, 230, 330);
    }// </editor-fold>//GEN-END:initComponents

    private void barycentricAlgoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barycentricAlgoRadioButtonActionPerformed
        drawPanel.setRasterizationAlgorithm(DrawingPanel.TRIANGLE_ALGORITHM.BARYCENTRIC);
    }//GEN-LAST:event_barycentricAlgoRadioButtonActionPerformed

    private void standardAlgoRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standardAlgoRadioButtonActionPerformed
        drawPanel.setRasterizationAlgorithm(DrawingPanel.TRIANGLE_ALGORITHM.STANDARD);
    }//GEN-LAST:event_standardAlgoRadioButtonActionPerformed
	
	private void bresenhamIntAlgoRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bresenhamIntAlgoRadioButton1ActionPerformed
        drawPanel.setRasterizationAlgorithm(DrawingPanel.TRIANGLE_ALGORITHM.INTEGER);
    }//GEN-LAST:event_bresenhamIntAlgoRadioButton1ActionPerformed

    private void hideVerticesCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideVerticesCheckBoxActionPerformed
        drawPanel.setVerticeVisibility(!hideVerticesCheckBox.isSelected());
    }//GEN-LAST:event_hideVerticesCheckBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        final long numOfIterations = 1000;
        long startTime, endTime;
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, 
                "Perform a simple benchmark: Run each rasterization algorithm 10000 times. Continue?",
                "Benchmark", JOptionPane.YES_NO_OPTION);
        
        
        if (showConfirmDialog == JOptionPane.NO_OPTION)
        {
            return;
        }
        
        for (TRIANGLE_ALGORITHM triangleAlgo : TRIANGLE_ALGORITHM.values())
        {
            for (COLOR_MODE colorMode : COLOR_MODE.values())
            {
                drawPanel.setRasterizationAlgorithm(triangleAlgo);
                drawPanel.setColorMode(colorMode);
                
                this.setVisible(false);
                startTime = System.currentTimeMillis();
                for (long i = 0; i < numOfIterations; i++)
                {
                    drawPanel.paintComponent(this.getGraphics());
                }
                endTime = System.currentTimeMillis();
                this.setVisible(true);
                
                JOptionPane.showMessageDialog(null, 
                        triangleAlgo.toString() + " / " + colorMode.toString() + " required : " + (endTime - startTime) + " ms");
            }           
        }        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void chooseColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseColorButtonActionPerformed
        Color newColor = JColorChooser.showDialog(this,
                                 "Choose Background Color",
                                 drawPanel.getSolidColor());
        if (newColor != null)
        {
            drawPanel.setSolidColor(newColor);
            jPanel1.invalidate();
        }
    }//GEN-LAST:event_chooseColorButtonActionPerformed

    private void solidColorModeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solidColorModeRadioButtonActionPerformed
        drawPanel.setColorMode(DrawingPanel.COLOR_MODE.SOLID);
        jPanel1.invalidate();
    }//GEN-LAST:event_solidColorModeRadioButtonActionPerformed

    private void edgeColorModeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edgeColorModeRadioButtonActionPerformed
       drawPanel.setColorMode(DrawingPanel.COLOR_MODE.FLUXIONARY);
       jPanel1.invalidate();
    }//GEN-LAST:event_edgeColorModeRadioButtonActionPerformed

    private void chooseColorV3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseColorV3ButtonActionPerformed
        Color newColor = JColorChooser.showDialog(this,
                                 "Choose Background Color",
                                 drawPanel.getColorV3());
        if (newColor != null)
        {
            drawPanel.setColorV3(newColor);
            jPanel1.invalidate();
        }
    }//GEN-LAST:event_chooseColorV3ButtonActionPerformed

    private void chooseColorV1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseColorV1ButtonActionPerformed
        Color newColor = JColorChooser.showDialog(this,
                                 "Choose Background Color",
                                 drawPanel.getColorV1());
        if (newColor != null)
        {
            drawPanel.setColorV1(newColor);
            jPanel1.invalidate();
        }
    }//GEN-LAST:event_chooseColorV1ButtonActionPerformed

    private void chooseColorV2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseColorV2ButtonActionPerformed
        Color newColor = JColorChooser.showDialog(this,
                                 "Choose Background Color",
                                 drawPanel.getColorV2());
        if (newColor != null)
        {
            drawPanel.setColorV2(newColor);
            jPanel1.invalidate();
        }
    }//GEN-LAST:event_chooseColorV2ButtonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton barycentricAlgoRadioButton;
    private javax.swing.JRadioButton bresenhamIntAlgoRadioButton1;
    private javax.swing.JButton chooseColorButton;
    private javax.swing.JButton chooseColorV1Button;
    private javax.swing.JButton chooseColorV2Button;
    private javax.swing.JButton chooseColorV3Button;
    private javax.swing.ButtonGroup colorModeGroup;
    private javax.swing.JRadioButton edgeColorModeRadioButton;
    private javax.swing.JCheckBox hideVerticesCheckBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.ButtonGroup rasterizationAlgorithmGroup;
    private javax.swing.JRadioButton solidColorModeRadioButton;
    private javax.swing.JRadioButton standardAlgoRadioButton;
    // End of variables declaration//GEN-END:variables
}
