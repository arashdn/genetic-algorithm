/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetic_algorithm;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;//table
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.effect.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.util.Callback;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author arashdn
 */
public class MainForm extends javax.swing.JFrame
{


    final int maxTypes = 8;
    String[] names;
    double infinityValue = 1000;//1000 only for initilize , will be changed
    Task task = null;
    
    
    
    
    ArrayList<JTextField> equPowers;
    ArrayList<JTextField> equCoeff;
    ArrayList<JLabel> equVars;
    JPanel equPan;
    final int equPowY = 0;
    final int equCoeffY = 25;
    int equOffset = 100;
    
    final void resetPolynomial()
    {
        equPowers = new ArrayList<>();
        equCoeff = new ArrayList<>();
        equVars = new ArrayList<>();
        equPan = new JPanel(null);
        equPan.setPreferredSize(new Dimension(1000, 80));
        jScrollPane1.setViewportView(equPan);
        addMonoNomial();
        
    }
    void removeMonoNomial(int res)
    {
        equPan.remove(equPowers.get(res));
        equPowers.remove(res);
        equPan.remove(equCoeff.get(res));
        equCoeff.remove(res);
        equPan.remove(equVars.get(res));
        equVars.remove(res);
        
        for (int i = res; i < equPowers.size(); i++)
        {
            equPowers.get(i).setLocation(equPowers.get(i).getLocation().x-equOffset, equPowers.get(i).getLocation().y);
            equCoeff.get(i).setLocation(equCoeff.get(i).getLocation().x-equOffset, equCoeff.get(i).getLocation().y);
            equVars.get(i).setLocation(equVars.get(i).getLocation().x-equOffset, equVars.get(i).getLocation().y);
            equVars.get(i).setText("<html><font size=\"6\">X<sub>"+(i+1)+"</sub>&nbsp;+&nbsp;</font></html>");
        }
        equCoeff.get(res-1).requestFocus();
        equPan.updateUI();
    }
    
    
    final void addMonoNomial()
    {
        addMonoNomial(equPowers.size());
    }
    final void addMonoNomial(int res)
    {
        JTextField temp1 = new JTextField("1");
        temp1.setLocation((res)*equOffset+55, equPowY);
        temp1.setSize(30, 30);
        
        temp1.addKeyListener(new KeyListener()
        {

            @Override
            public void keyTyped(KeyEvent e)
            {
                char ch = e.getKeyChar();

                if (!(ch >= '0' && ch <= '9') && ch != '.' && ch!='-' && ch!='+' && ch != '\b') 
                {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                generatePolyNomial();
                if(e.getKeyCode()==157)
                {
                    if (equPowers.size() == 1)
                        return;
                    int res = equPowers.lastIndexOf(e.getSource());
                    removeMonoNomial(res);
                }
                else if(e.getKeyCode()==10)
                {
                    int res = equPowers.lastIndexOf(e.getSource());
                    addMonoNomial(res+1);
                }
                else if(e.getKeyCode()==16 || e.getKeyCode() == e.VK_TAB)
                {
                    int res = equPowers.lastIndexOf(e.getSource());
                    if(res<equCoeff.size()-1)
                    {
                        equCoeff.get(res+1).requestFocus();
                    }
                }
            }
        });
        
        temp1.addFocusListener(new FocusListener()
        {

            @Override
            public void focusGained(FocusEvent e)
            {
                JTextField t = (JTextField) e.getSource();
                t.selectAll();
                generatePolyNomial();
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                generatePolyNomial();
            }
        });
        
        equPowers.add(res,temp1);
        JTextField temp2 = new JTextField("0");
        temp2.setLocation((res)*equOffset, equCoeffY);
        temp2.setSize(40, 40);
        
        
        temp2.addKeyListener(new KeyListener()
        {

            @Override
            public void keyTyped(KeyEvent e)
            {
                char ch = e.getKeyChar();

                if (!(ch >= '0' && ch <= '9') && ch != '.' && ch!='-' && ch!='+' && ch != '\b') 
                {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                generatePolyNomial();
                if(e.getKeyCode()==157)
                {
                    if (equCoeff.size() == 1)
                        return;
                    int res = equCoeff.lastIndexOf(e.getSource());
                    removeMonoNomial(res);
                }
                else if(e.getKeyCode()==10)
                {
                    int res = equCoeff.lastIndexOf(e.getSource());
                    addMonoNomial(res+1);
                }
                else if(e.getKeyCode()==16)
                {
                    int res = equCoeff.lastIndexOf(e.getSource());
                    equPowers.get(res).requestFocus();
                    
                }
            }
        });
        
        temp2.addFocusListener(new FocusListener()
        {

            @Override
            public void focusGained(FocusEvent e)
            {
                JTextField t = (JTextField) e.getSource();
                t.selectAll();
                generatePolyNomial();
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                generatePolyNomial();
            }
        });
        
        equCoeff.add(res,temp2);
        
        
        JLabel temp3 = new JLabel("<html><font size=\"6\">X<sub>"+(res+1)+"</sub>&nbsp;+&nbsp;</font></html>");
        
        temp3.setSize(80, 40);
        temp3.setLocation((res)*equOffset+44, equCoeffY);
        equVars.add(res,temp3);
        
        
        equPan.add(equPowers.get(res));
        equPan.add(equCoeff.get(res));
        equPan.add(equVars.get(res));
        
        
        for (int i = res+1; i < equPowers.size(); i++)
        {
            equPowers.get(i).setLocation(equPowers.get(i).getLocation().x+equOffset, equPowers.get(i).getLocation().y);
            equCoeff.get(i).setLocation(equCoeff.get(i).getLocation().x+equOffset, equCoeff.get(i).getLocation().y);
            equVars.get(i).setLocation(equVars.get(i).getLocation().x+equOffset, equVars.get(i).getLocation().y);
            equVars.get(i).setText("<html><font size=\"6\">X<sub>"+(i+1)+"</sub>&nbsp;+&nbsp;</font></html>");
        }
        
        equCoeff.get(res).requestFocus();
        
        
        equPan.updateUI();
    }
    
     void generatePolyNomial()
     {
         String pow = "";
         String coeff = "";
         
         for (int i = 0; i < equCoeff.size(); i++)
         {
             coeff += equCoeff.get(i).getText() + ",";
             pow += equPowers.get(i).getText() + ",";
         }
         jTextField1.setText(pow);
         jTextField2.setText(coeff);
         previewPolynomial();
     }
    
    public MainForm()
    {
        initComponents();
        names = new String[maxTypes];
        jCheckBox1.setText(names[0] = "Single CO - Tournament - Allow Repetitive");
        jCheckBox2.setText(names[1] = "Single CO - Tournament - Not Repetitive");
        jCheckBox3.setText(names[2] = "Single CO - Roulete - Allow Repetitive");
        jCheckBox4.setText(names[3] = "Single CO - Roulete - NOT Repetitive");
        jCheckBox5.setText(names[4] = "Double CO - Tournament - Allow Repetitive");
        jCheckBox6.setText(names[5] = "Double CO - Tournament - Not Repetitive");
        jCheckBox7.setText(names[6] = "Double CO - Roulete - Allow Repetitive");
        jCheckBox8.setText(names[7] = "Double CO - Roulete - NOT Repetitive");
        previewPolynomial();
        changeAutoEqu(jCheckBox11.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jSpinner9 = new javax.swing.JSpinner();
        jCheckBox10 = new javax.swing.JCheckBox();
        jSpinner10 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jSpinner8 = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSpinner11 = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        jSpinner6 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSpinner7 = new javax.swing.JSpinner();
        jCheckBox9 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jCheckBox11 = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSpinner1.setValue(2000);

        jLabel1.setText("Population Size");

        jLabel2.setText("Generations");

        jSpinner2.setValue(30);

        jLabel9.setText("Chromosomes in Tournament");

        jSpinner9.setValue(30);

        jCheckBox10.setSelected(true);
        jCheckBox10.setText("Stop if Fitness larger than");

        jSpinner10.setValue(100);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addComponent(jSpinner2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner9))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(126, 126, 126))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jSpinner9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox10)
                    .addComponent(jSpinner10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jButton1.setText("Run");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setText("Mutation Probability(%)");

        jLabel3.setText("Crossover Probability(%)");

        jSpinner3.setValue(80);

        jSpinner4.setValue(20);

        jSpinner8.setValue(1);

        jLabel8.setText("Elitisim Ratio(%)");

        jLabel16.setText("Display Infinity as ");

        jSpinner11.setValue(100);

        jLabel17.setText("in charts");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinner8, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jSpinner3)
                    .addComponent(jSpinner4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jSpinner11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jCheckBox1.setText("jCheckBox1");

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("jCheckBox2");

        jCheckBox3.setText("jCheckBox3");

        jCheckBox4.setText("jCheckBox4");

        jCheckBox5.setText("jCheckBox5");

        jCheckBox6.setSelected(true);
        jCheckBox6.setText("jCheckBox6");

        jCheckBox7.setText("jCheckBox7");

        jCheckBox8.setText("jCheckBox8");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox8)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTextField1.setText("8,3,5,1,7,1");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField2.setText("7,-2,11,3,0,9");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel10.setText("Powers");

        jLabel11.setText("CoEfficients");

        jLabel5.setText("Min Cofficent value:");

        jSpinner5.setValue(-40);

        jSpinner6.setValue(40);

        jLabel6.setText("Max Cofficent value:");

        jLabel7.setText("Decimal Points:");

        jSpinner7.setValue(1);

        jCheckBox9.setSelected(true);
        jCheckBox9.setText("Display Result Instead of Fitness in Table");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(49, 49, 49))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSpinner5, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(jSpinner6)
                            .addComponent(jSpinner7))))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jCheckBox9)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        jLabel12.setText("Equation");

        jProgressBar1.setToolTipText("");

        jLabel13.setText("Stage 0/0");

        jLabel14.setText("Current:");

        jLabel15.setText("Total:");

        jCheckBox11.setLabel("Auto Equation Generator");
        jCheckBox11.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                jCheckBox11StateChanged(evt);
            }
        });
        jCheckBox11.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jCheckBox11ActionPerformed(evt);
            }
        });

        jLabel18.setText("<html>Press Enter to add<br>Ctrl to Remove<br>Shift to move");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(41, 41, 41))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1)
                                        .addGap(35, 35, 35))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox11)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addContainerGap(149, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1)
                                            .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jCheckBox11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    String textPolynomial(Polynomial pn , double [] values)
    {
        if (values.length != pn.getSize())
            return "Not correct values";
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(2);
        df.setMinimumIntegerDigits(1);
        //String res = "<html>";
        String res = "";
        int i = 0;
        for (Mononomial mn : pn.lst)
        {
            
            if (mn.getCoefficient() > 0)
            {
                res += "+";
            }
            if (mn.getCoefficient() != 0)
            {
                res += df.format(mn.getCoefficient()) +"("+ df.format(values[i])+")"+ "<sup>" + mn.getPower() + "</sup>&nbsp;";
            } 
            i++;
        }
        //res += "</html>";
        df.setMaximumFractionDigits(5);
        res += "&nbsp;&nbsp; = &nbsp;&nbsp;"+df.format(pn.getValue(values));
        return res;
    }

    String displayPolynomial(Polynomial pn)
    {
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(2);
        df.setMinimumIntegerDigits(1);
        String res = "<html>";
        int i = 0;
        for (Mononomial mn : pn.lst)
        {
            i++;
            if (mn.getCoefficient() > 0)
            {
                res += "+";
            }
            if (mn.getCoefficient() != 0)
            {
                res += df.format(mn.getCoefficient()) + "X"+ "<sub>" + i + "</sub>" + (mn.getPower() != 1 ?("<sup>" + mn.getPower() + "</sup>"):"")+"&nbsp;";
            }   
        }
        res += "</html>";
        return res;
    }
    
    final void previewPolynomial()
    {
        String[] coeffTemp = jTextField2.getText().split(",");
        String[] powersTemp = jTextField1.getText().split(",");
        Polynomial pn = new Polynomial();

        double coTemp;
        int pwTemp;
        if (coeffTemp.length != powersTemp.length)
        {
            jLabel12.setText("Polynomial is not correct");
            return;
        }
        try
        {
            for (int i = 0; i < coeffTemp.length; i++)
            {
                coTemp = Double.parseDouble(coeffTemp[i]);
                pwTemp = Integer.parseInt(powersTemp[i]);
                pn.add(coTemp, pwTemp);
            }
            jLabel12.setText(displayPolynomial(pn));
        } 
        catch (Exception ex)
        {
            jLabel12.setText("Polynomial is not correct");
            return;
        }
    }
    
    
    
    private class Task extends Thread 
    {    
      public Task()
      {
          
      }

      public void run()
      {
         execute();
      }
   }  
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        if(task == null)
        {
            task = new Task();                
            task.start();
            jButton1.setText("Stop");
        }
        else
        {
            task.stop();
            task=null;
            jProgressBar1.setValue(0);
            jProgressBar2.setValue(0);
            jLabel13.setText("Stage 0/0");
            jButton1.setText("Run");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public void execute()                                         
    {                                             
        String[] coeffTemp = jTextField2.getText().split(",");
        String[] powersTemp = jTextField1.getText().split(",");
        Polynomial pn = new Polynomial();

        double coTemp;
        int pwTemp;
        if (coeffTemp.length != powersTemp.length)
        {
            JOptionPane.showMessageDialog(this, "Polynomial is not correct");
            return;
        }
        try
        {
            for (int i = 0; i < coeffTemp.length; i++)
            {
                coTemp = Double.parseDouble(coeffTemp[i]);
                pwTemp = Integer.parseInt(powersTemp[i]);
                pn.add(coTemp, pwTemp);
            }
        } 
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Polynomial is not correct");
            return;
        }

        
        final boolean resInTable = jCheckBox9.isSelected();
        
        
        Chromosome.setPolynomial(pn);
        Gene.maxValue = Integer.parseInt(jSpinner6.getValue().toString());
        Gene.minValue = Integer.parseInt(jSpinner5.getValue().toString());
        Gene.points = Integer.parseInt(jSpinner7.getValue().toString());

        int generations = Integer.parseInt(jSpinner2.getValue().toString());
        int popSize = Integer.parseInt(jSpinner1.getValue().toString());

        int CORate = Integer.parseInt(jSpinner3.getValue().toString());
        int MURate = Integer.parseInt(jSpinner4.getValue().toString());
        int ElitismRate = Integer.parseInt(jSpinner8.getValue().toString());

        int tournamentChromosomes = Integer.parseInt(jSpinner9.getValue().toString());

        double[][] best = new double[maxTypes][generations];
        double[][] avg = new double[maxTypes][generations];
        
        
        boolean continueOnresult = jCheckBox10.isSelected();
        double resultValue = Double.parseDouble(jSpinner10.getValue().toString());
        infinityValue = Double.parseDouble(jSpinner11.getValue().toString());

        boolean[] checks = new boolean[maxTypes];
        checks[0] = jCheckBox1.isSelected();
        checks[1] = jCheckBox2.isSelected();
        checks[2] = jCheckBox3.isSelected();
        checks[3] = jCheckBox4.isSelected();
        checks[4] = jCheckBox5.isSelected();
        checks[5] = jCheckBox6.isSelected();
        checks[6] = jCheckBox7.isSelected();
        checks[7] = jCheckBox8.isSelected();
        
        
        
        
        int runTypes = 0;
        for (int i = 0; i < maxTypes; i++)
            if(checks[i])
                runTypes++;
        final int totalRuns = runTypes*generations;
        int totalRunCounter = 0;
        int currentRunCounter;
        int currentStage = 0;

        GeneticAlgorithm ga;
        String results = "<html>";

        for (int j = 0; j < maxTypes; j++)
        {
            if (checks[j])
            {
                currentStage++;
                switch (j)
                {
                    case 0:
                        Chromosome.setCrossOverType(Chromosome.CO_TYPE_SINGLE_POINT);
                        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_TOURNAMENT);
                        GeneticAlgorithm.setAddExisting(true);
                        break;
                    case 1:
                        Chromosome.setCrossOverType(Chromosome.CO_TYPE_SINGLE_POINT);
                        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_TOURNAMENT);
                        GeneticAlgorithm.setAddExisting(false);
                        break;
                    case 2:
                        Chromosome.setCrossOverType(Chromosome.CO_TYPE_SINGLE_POINT);
                        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
                        GeneticAlgorithm.setAddExisting(true);
                        break;
                    case 3:
                        Chromosome.setCrossOverType(Chromosome.CO_TYPE_SINGLE_POINT);
                        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
                        GeneticAlgorithm.setAddExisting(false);
                        break;
                    case 4:
                        Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
                        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_TOURNAMENT);
                        GeneticAlgorithm.setAddExisting(true);
                        break;
                    case 5:
                        Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
                        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_TOURNAMENT);
                        GeneticAlgorithm.setAddExisting(false);
                        break;
                    case 6:
                        Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
                        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
                        GeneticAlgorithm.setAddExisting(true);
                        break;
                    case 7:
                        Chromosome.setCrossOverType(Chromosome.CO_TYPE_DOUBLE_POINT);
                        GeneticAlgorithm.setSelectionType(GeneticAlgorithm.SELECTION_TYPE_ROULETTE);
                        GeneticAlgorithm.setAddExisting(false);
                        break;
                }

                ga = new GeneticAlgorithm(popSize, CORate, MURate, ElitismRate, tournamentChromosomes);
                currentRunCounter = 0;
                DecimalFormat df = new DecimalFormat("#");
                df.setMinimumIntegerDigits(1);
                df.setMaximumFractionDigits(12);
                for (int i = 0; i < generations; i++)
                {
                    ga.repeat();
                    currentRunCounter++;
                    totalRunCounter++;
                    jProgressBar1.setValue((int)((double)currentRunCounter*100/(double)generations));
                    jProgressBar2.setValue((int)((double)totalRunCounter*100/(double)totalRuns));
                    best[j][i] = ga.getBestChromosome().getFitness();
                    avg[j][i] = ga.getFitnessAverage();
                    jLabel13.setText("<html>Stage&nbsp;"+currentStage+"/"+runTypes+" : "+names[j]+"<br>"+"Generation&nbsp;"+currentRunCounter+"/"+generations+
                            "<br>Best&nbsp;Fitness:&nbsp;"+df.format(best[j][i])+"<br>Average&nbsp;Fitness:&nbsp;"+df.format(avg[j][i])+"</html>");
                    if( continueOnresult && best[j][i] >= resultValue)
                    {
                        for (int k = i+1; k < generations; k++)
                        {
                            best[j][k] = avg[j][k] = best[j][i];
                            totalRunCounter++;
                        }
                        break;
                    }//if result
                }//for i
                results += names[j]+" -> "+textPolynomial(pn, ga.getBestChromosome().getGenesValue())+"<br>";
            }//if
        }//for j
        results += "</html>";
        /////////////////Report//////////////////
        JFXPanel fxPanel = new JFXPanel();
        
        ResultForm rf = new ResultForm(fxPanel,results);
        rf.setVisible(true);

        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                Scene scene = createScene(avg, best, names , checks , resInTable);
                fxPanel.setScene(scene);
            }
        });


    } 
    
    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField2KeyReleased
    {//GEN-HEADEREND:event_jTextField2KeyReleased
        previewPolynomial();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTextField1KeyReleased
    {//GEN-HEADEREND:event_jTextField1KeyReleased
        previewPolynomial();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jCheckBox11StateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_jCheckBox11StateChanged
    {//GEN-HEADEREND:event_jCheckBox11StateChanged
        
        
    }//GEN-LAST:event_jCheckBox11StateChanged

    final void changeAutoEqu(boolean enable)
    {
        jTextField1.setEnabled(!enable);
        jTextField2.setEnabled(!enable);
        jScrollPane1.setVisible(enable);
        jLabel18.setVisible(enable);
        resetPolynomial();
        
    }
    private void jCheckBox11ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCheckBox11ActionPerformed
    {//GEN-HEADEREND:event_jCheckBox11ActionPerformed
        
        changeAutoEqu(jCheckBox11.isSelected());
    }//GEN-LAST:event_jCheckBox11ActionPerformed

    
//    Scene createTwoCharts2(double[][] avg, double[][] best, String[] names)
//    {
//        final Group rootGroup = new Group();
//        final Scene scene
//                = new Scene(rootGroup, 800, 400, Color.BEIGE);
//
//        final Text text1 = new Text(25, 25, "(2007) JavaFX based on F3");
//        text1.setFill(Color.CHOCOLATE);
//        text1.setFont(Font.font(java.awt.Font.SERIF, 25));
//        rootGroup.getChildren().add(text1);
//
//        final Text text2 = new Text(25, 50, "(2010) JavaFX Script Deprecated");
//        text2.setFill(Color.DARKBLUE);
//        text2.setFont(Font.font(java.awt.Font.SANS_SERIF, 30));
//        rootGroup.getChildren().add(text2);
//
//        final Text text3 = new Text(25, 75, "(2011) JavaFX to be Open Sourced!");
//        text3.setFill(Color.TEAL);
//        text3.setFont(Font.font(java.awt.Font.MONOSPACED, 35));
//        rootGroup.getChildren().add(text3);
//
//        final Text text4 = new Text(25, 125, "(2011) JavaFX to be Standardized");
//        text4.setFill(Color.CRIMSON);
//        text4.setFont(Font.font(java.awt.Font.DIALOG, 40));
//        final Effect glow = new Glow(1.0);
//        text4.setEffect(glow);
//        rootGroup.getChildren().add(text4);
//
//        final Text text5 = new Text(25, 1005, "(Now) Time for JavaFX 2.0!");
//        text5.setFill(Color.DARKVIOLET);
//        text5.setFont(Font.font(java.awt.Font.SERIF, FontWeight.EXTRA_BOLD, 45));
//        final Light.Distant light = new Light.Distant();
//        light.setAzimuth(-135.0);
//        final Lighting lighting = new Lighting();
//        lighting.setLight(light);
//        lighting.setSurfaceScale(9.0);
//        text5.setEffect(lighting);
//        rootGroup.getChildren().add(text5);
//
//        final Text text6 = new Text(25, 225, "JavaFX News at JavaOne!");
//        text6.setFill(Color.DARKGREEN);
//        text6.setBlendMode(BlendMode.COLOR_BURN);
//        text6.setFont(Font.font(java.awt.Font.DIALOG_INPUT, FontWeight.THIN, 45));
//        final Reflection reflection = new Reflection();
//        reflection.setFraction(1.0);
//        text6.setEffect(reflection);
//        rootGroup.getChildren().add(text6);
//        return scene;
//    }

    Scene createScene(double[][] avg, double[][] best, String[] names, boolean [] checked , boolean resInTable )
    {
        Group root = createChart(best, names);
        NumberAxis lineYAxis = new NumberAxis();
        CategoryAxis lineXAxis = new CategoryAxis();
        lineYAxis.setLabel("Fitness");
        lineXAxis.setLabel("Generation");
        LineChart barChart = new LineChart(lineXAxis, lineYAxis);
        barChart.setMinSize(800, 600);
        barChart.setCreateSymbols(false);

        XYChart.Series[] bar = new XYChart.Series[names.length];

        for (int j = 0; j < names.length; j++)
        {
            bar[j] = new XYChart.Series<>();
            bar[j].setName(names[j]);

            for (int i = 0; i < avg[j].length; i++)
            {
                bar[j].getData().add(getChartData(avg[j][i]==Double.POSITIVE_INFINITY?infinityValue:avg[j][i] , "" + (i + 1)));
            }
            barChart.getData().addAll(bar[j]);
        }

        barChart.setLayoutY(700);

        final Text text = new Text(5, 100, "Best Fitness:");
        text.setFill(Color.DARKVIOLET);
        text.setFont(Font.font(java.awt.Font.SERIF, FontWeight.EXTRA_BOLD, 30));
        final Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);
        final Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(9.0);
        text.setEffect(lighting);
        text.setLayoutY(-55);
        root.getChildren().add(text);

        final Text text2 = new Text(5, 100, "Aerage Fitness:");
        text2.setFill(Color.DARKVIOLET);
        text2.setFont(Font.font(java.awt.Font.SERIF, FontWeight.EXTRA_BOLD, 30));
        text2.setEffect(lighting);
        text2.setLayoutY(600);
        root.getChildren().add(text2);

        root.getChildren().add(barChart);

        TableView table = new TableView();
        table.setEditable(false);
        
        int runTypes = 0;
        for (int i = 0; i < maxTypes; i++)
            if(checked[i])
                runTypes++;
        
        
        TableColumn [] mainCols = new TableColumn[runTypes+1];
        TableColumn [] secondRow = new TableColumn[runTypes*2];
        
        String[][] dt = new String[best[0].length][runTypes*2+1];//1 for generation
        
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(10);
        for (int i = 0; i < best[0].length; i++)
        {
            dt[i][0] = ""+(i+1);
            for (int j = 0 , k=1; j < maxTypes; j++)
            {
               if(checked[j])
                {
                    dt[i][k]=df.format(resInTable?1/best[j][i]:best[j][i]);
                    k++;
                    dt[i][k]=df.format(resInTable?1/avg[j][i]:avg[j][i]);
                    k++;
                } 
            }
        }
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(dt));

        mainCols[0] = new TableColumn("#Gen");
        mainCols[0].setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>()
        {
            @Override
            public ObservableValue<String> call(CellDataFeatures<String[], String> p)
            {
                return new SimpleStringProperty((p.getValue()[0]));
            }
        });
        for (int i = 0 , j = 0; i < maxTypes; i++)
        {
            if(checked[i])
            {
                mainCols[j+1] = new TableColumn(names[i]);
                secondRow[2*j] = new TableColumn("Best Fitness");
                secondRow[2*j+1] = new TableColumn("Average Fitness");
                mainCols[j+1].getColumns().addAll(secondRow[2*j] , secondRow[2*j+1]);
                mainCols[j+1].setMinWidth(120);
      
                
                final int colNo = 2*j+1;
                final int colNo2 = 2*j+1+1;
                secondRow[2 * j].setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>()
                {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<String[], String> p)
                    {
                        return new SimpleStringProperty((p.getValue()[colNo]));
                    }
                });
                secondRow[2*j+1].setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                        return new SimpleStringProperty((p.getValue()[colNo2]));
                    }
                });
                
                j++;
            }
        }
        table.getColumns().addAll((Object[]) mainCols);
        table.setItems(data);

        
        table.setLayoutY(1300);
        table.setLayoutX(50);
        table.setPrefSize(125*runTypes*2+55, 800);
        root.getChildren().add(table);
        
        

        Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
        return scene;
    }

    private static XYChart.Data getChartData(double x, String y)
    {
        XYChart.Data data = new XYChart.Data<>();
        data.setYValue(x);
        data.setXValue(y);
        return data;
    }

    private Group createChart(double[][] inp, String[] names)
    {
        Group root = new Group();
        //Scene  scene  =  new  Scene(root, javafx.scene.paint.Color.ALICEBLUE);

        NumberAxis lineYAxis = new NumberAxis();
        CategoryAxis lineXAxis = new CategoryAxis();
        lineYAxis.setLabel("Fitness");
        lineXAxis.setLabel("Generation");
        LineChart barChart = new LineChart(lineXAxis, lineYAxis);
        barChart.setMinSize(800, 600);
        barChart.setCreateSymbols(false);

        XYChart.Series[] bar = new XYChart.Series[names.length];

        for (int j = 0; j < names.length; j++)
        {
            bar[j] = new XYChart.Series<>();
            bar[j].setName(names[j]);

            for (int i = 0; i < inp[j].length; i++)
            {
                bar[j].getData().add(getChartData(inp[j][i]==Double.POSITIVE_INFINITY?infinityValue:inp[j][i], "" + (i + 1)));
            }
            barChart.getData().addAll(bar[j]);
        }

        barChart.setLayoutY(40);
        root.getChildren().add(barChart);

        //return (scene);
        return root;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner10;
    private javax.swing.JSpinner jSpinner11;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    private javax.swing.JSpinner jSpinner9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
