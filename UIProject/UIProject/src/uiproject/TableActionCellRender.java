/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiproject;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author drons
 */
public class TableActionCellRender extends DefaultTableCellRenderer{
    @Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                               boolean hasFocus, int row, int column) {
    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

    PanelAction action = new PanelAction(); //  ini panel dengan tombol "Detail"
    if(isSelected == false && row%2==0){
        action.setBackground(com.getBackground());
    }
    else{
        action.setBackground(com.getBackground());
    }
    return action;
}
    
    
}
