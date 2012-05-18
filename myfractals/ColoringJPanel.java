/*
 * ColoringJPanel.java
 *
 * Created on March 21, 2008, 2:25 AM
 */

package myfractals;

import myfractals.kolorowania.*;
/**
 *
 * @author  victories
 */
public class ColoringJPanel extends javax.swing.JPanel {
    /** Creates new form ColoringJPanel */
    public ColoringJPanel() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        coloringsJScrollPane = new javax.swing.JScrollPane();
        coloringsJList = new javax.swing.JList();
        buttonsPanel = new myfractals.ButtonsPanel();

        coloringsJList.setModel(colListModel);
        coloringsJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        coloringsJList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                coloringListSelectionHandler(evt);
            }
        });

        coloringsJScrollPane.setViewportView(coloringsJList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(coloringsJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coloringsJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void coloringListSelectionHandler(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_coloringListSelectionHandler
            if(evt.getValueIsAdjusting()) {
                switch(colListModel.getColoring(coloringsJList.getSelectedIndex(), null).getParametersQuantity()) {
                    case 0: buttonsPanel.setButtonsQuantity(0); break;
                    case 1: buttonsPanel.setButtonsQuantity(1); break;
                    case 2: buttonsPanel.setButtonsQuantity(functionsCount); break;
                    default: buttonsPanel.setButtonsQuantity(0); break;
                }
                
            }
    }//GEN-LAST:event_coloringListSelectionHandler
    
    public IKolor getColoring() {
        return colListModel.getColoring(coloringsJList.getSelectedIndex(), buttonsPanel.getColors());
    }
    
    public void setFunctionsCount(int _functionsCount) { 
        functionsCount = _functionsCount;
        if(colListModel.getColoring(coloringsJList.getSelectedIndex(), null).getParametersQuantity() == 2) {
            buttonsPanel.setButtonsQuantity(functionsCount);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private myfractals.ButtonsPanel buttonsPanel;
    private javax.swing.JList coloringsJList;
    private javax.swing.JScrollPane coloringsJScrollPane;
    // End of variables declaration//GEN-END:variables
    private ColoringListModel colListModel = new ColoringListModel();
    
    public int functionsCount = 0;
}
