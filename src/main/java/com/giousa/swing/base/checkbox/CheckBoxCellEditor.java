package com.giousa.swing.base.checkbox;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {

    protected JCheckBox checkBox;

    public CheckBoxCellEditor() {
        checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        checkBox.setSelected(((Boolean) value).booleanValue());
        return checkBox;
    }

    @Override
    public Object getCellEditorValue() {
        return Boolean.valueOf(checkBox.isSelected());
    }
}
