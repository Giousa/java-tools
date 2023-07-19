package com.giousa.swing.base.checkbox;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CWCheckBoxRenderer extends JCheckBox implements TableCellRenderer {

    Border border = new EmptyBorder(1, 2, 1, 2);

    public CWCheckBoxRenderer() {
        super();
        setOpaque(true);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Boolean) {
            setSelected(((Boolean) value).booleanValue());
// setEnabled(table.isCellEditable(row, column));
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        return this;
    }
}
