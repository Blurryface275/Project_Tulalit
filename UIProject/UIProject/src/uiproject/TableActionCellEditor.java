/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uiproject;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author drons
 */
public class TableActionCellEditor extends AbstractCellEditor implements TableCellEditor {

    private final PanelAction actionPanel;
    private final TableActionEvent mainEvent; // simpan event sebagai field

    public TableActionCellEditor(TableActionEvent event) {
        this.mainEvent = event; // simpan ke field
        this.actionPanel = new PanelAction();
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        // Gunakan mainEvent yang disimpan di field
        actionPanel.initEvent(mainEvent, row);
        return actionPanel;
        
    }
}

