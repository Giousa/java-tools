package com.giousa.swing.base.checkbox;

import javax.swing.*;
import java.awt.*;

public class TableCellEditorButton extends DefaultCellEditor {

    private JButton btn;
    private int row;

    private ClickListener clickListener;

    public void onClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public TableCellEditorButton() {
        super(new JTextField());
        //设置点击一次就激活，否则默认好像是点击2次激活。
        this.setClickCountToStart(1);
        btn = new JButton("按钮");
        btn.addActionListener(e -> {
//            System.out.println("按钮事件触发----");
            clickListener.onClick(row);
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        return btn;
    }

    public interface ClickListener{
        void onClick(int row);
    }
}