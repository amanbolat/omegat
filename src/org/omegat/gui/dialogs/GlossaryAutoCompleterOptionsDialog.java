/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool 
          with fuzzy matching, translation memory, keyword search, 
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2013 Zoltan Bartko
               2015 Aaron Madlon-Kay
               Home page: http://www.omegat.org/
               Support center: http://groups.yahoo.com/group/OmegaT/

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/

package org.omegat.gui.dialogs;

import java.awt.Frame;

import org.omegat.util.OStrings;
import org.omegat.util.Preferences;
import org.omegat.util.gui.DockingUI;
import org.omegat.util.gui.StaticUIUtils;

/**
 *
 * @author bartkoz
 */
public class GlossaryAutoCompleterOptionsDialog extends javax.swing.JDialog {
    
    /**
     * Creates new form GlossaryAutoCompleterOptionsDialog
     */
    public GlossaryAutoCompleterOptionsDialog(Frame parent) {
        super(parent, true);
        
        StaticUIUtils.setEscapeClosable(this);
        
        initComponents();
        
        getRootPane().setDefaultButton(okButton);
        
        // initializing options
        displaySourceCheckBox.setSelected(Preferences.isPreference(Preferences.AC_GLOSSARY_SHOW_SOURCE));
        activateSourceItems(displaySourceCheckBox.isSelected());
        sourceFirstRadioButton.setSelected(!Preferences.isPreference(Preferences.AC_GLOSSARY_SHOW_TARGET_BEFORE_SOURCE));
        targetFirstRadioButton.setSelected(Preferences.isPreference(Preferences.AC_GLOSSARY_SHOW_TARGET_BEFORE_SOURCE));
        sortBySourceCheckBox.setSelected(Preferences.isPreference(Preferences.AC_GLOSSARY_SORT_BY_SOURCE));
        longerFirstCheckBox.setSelected(Preferences.isPreference(Preferences.AC_GLOSSARY_SORT_BY_LENGTH));
        sortEntriesCheckBox.setSelected(Preferences.isPreference(Preferences.AC_GLOSSARY_SORT_ALPHABETICALLY));
        DockingUI.displayCentered(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sourceButtonGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        displaySourceCheckBox = new javax.swing.JCheckBox();
        sourceFirstRadioButton = new javax.swing.JRadioButton();
        targetFirstRadioButton = new javax.swing.JRadioButton();
        sortBySourceCheckBox = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        longerFirstCheckBox = new javax.swing.JCheckBox();
        sortEntriesCheckBox = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(OStrings.getString("AC_OPTIONS_GLOSSARY_FRAME")); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        displaySourceCheckBox.setText(OStrings.getString("AC_OPTIONS_DISPLAY_SOURCE")); // NOI18N
        displaySourceCheckBox.setToolTipText(OStrings.getString("AC_OPTIONS_DISPLAY_SOURCE_TOOLTIP")); // NOI18N
        displaySourceCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displaySourceCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(displaySourceCheckBox, gridBagConstraints);

        sourceButtonGroup.add(sourceFirstRadioButton);
        sourceFirstRadioButton.setText(OStrings.getString("AC_OPTIONS_SOURCE_FIRST")); // NOI18N
        sourceFirstRadioButton.setToolTipText(OStrings.getString("AC_OPTIONS_SOURCE_FIRST")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(sourceFirstRadioButton, gridBagConstraints);

        sourceButtonGroup.add(targetFirstRadioButton);
        targetFirstRadioButton.setText(OStrings.getString("AC_OPTIONS_TARGET_FIRST")); // NOI18N
        targetFirstRadioButton.setToolTipText(OStrings.getString("AC_OPTIONS_TARGET_FIRST_TOOLTIP")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(targetFirstRadioButton, gridBagConstraints);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/omegat/Bundle"); // NOI18N
        sortBySourceCheckBox.setText(bundle.getString("AC_OPTIONS_SORT_SOURCE_ALPHABETICALLY")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(sortBySourceCheckBox, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(OStrings.getString("AC_GLOSSARY_TARGET_PANEL"))); // NOI18N
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        longerFirstCheckBox.setText(OStrings.getString("AC_OPTIONS_SORT_BY_LENGTH")); // NOI18N
        longerFirstCheckBox.setToolTipText(OStrings.getString("AC_OPTIONS_SORT_BY_LENGTH_TOOLTIP")); // NOI18N
        jPanel1.add(longerFirstCheckBox);

        sortEntriesCheckBox.setText(OStrings.getString("AC_OPTIONS_SORT_TARGET_ALPHABETICALLY")); // NOI18N
        sortEntriesCheckBox.setToolTipText(OStrings.getString("AC_OPTIONS_SORT_ALPHABETICALLY_TOOLTIP")); // NOI18N
        jPanel1.add(sortEntriesCheckBox);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel2.add(jPanel1, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 10, 10));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        org.openide.awt.Mnemonics.setLocalizedText(okButton, OStrings.getString("BUTTON_OK")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel3.add(okButton);

        org.openide.awt.Mnemonics.setLocalizedText(cancelButton, OStrings.getString("BUTTON_CANCEL")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel3.add(cancelButton);

        jPanel4.add(jPanel3, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel4, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doClose() {
        setVisible(false);
        dispose();
    }
     
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void activateSourceItems(boolean activate) {
        sortBySourceCheckBox.setEnabled(activate);
        sourceFirstRadioButton.setEnabled(activate);
        targetFirstRadioButton.setEnabled(activate);
    }
    
    private void displaySourceCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displaySourceCheckBoxActionPerformed
        activateSourceItems(displaySourceCheckBox.isSelected());
    }//GEN-LAST:event_displaySourceCheckBoxActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        Preferences.setPreference(Preferences.AC_GLOSSARY_SHOW_SOURCE, displaySourceCheckBox.isSelected());
        if (displaySourceCheckBox.isSelected()) {
            Preferences.setPreference(Preferences.AC_GLOSSARY_SHOW_TARGET_BEFORE_SOURCE, targetFirstRadioButton.isSelected());
            Preferences.setPreference(Preferences.AC_GLOSSARY_SORT_BY_SOURCE, sortBySourceCheckBox.isSelected());
        }
        Preferences.setPreference(Preferences.AC_GLOSSARY_SORT_BY_LENGTH, longerFirstCheckBox.isSelected());
        Preferences.setPreference(Preferences.AC_GLOSSARY_SORT_ALPHABETICALLY, sortEntriesCheckBox.isSelected());
        doClose();
    }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox displaySourceCheckBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JCheckBox longerFirstCheckBox;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox sortBySourceCheckBox;
    private javax.swing.JCheckBox sortEntriesCheckBox;
    private javax.swing.ButtonGroup sourceButtonGroup;
    private javax.swing.JRadioButton sourceFirstRadioButton;
    private javax.swing.JRadioButton targetFirstRadioButton;
    // End of variables declaration//GEN-END:variables
}
