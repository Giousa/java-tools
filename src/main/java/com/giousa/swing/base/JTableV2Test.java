package com.giousa.swing.base;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JTableV2Test {

    public static void main(String[] args) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        String[] volumeHeader = {"表名","复选框","按钮"};
        Object[][] cellData = {{"consult_workbench",null,""}, {"consult_emr_case",null,""}, {"consult_order",null,""}};
        DefaultTableModel defaultTableModel = new DefaultTableModel(cellData, volumeHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //禁止单元格编辑
                return false;
            }
        };
        JTable tableCenter = new JTable();
        tableCenter.setBackground(Color.GRAY);
        tableCenter.setPreferredSize(new Dimension(800,500));
        tableCenter.setModel(defaultTableModel);
        //隐藏TAB头部描述
        tableCenter.setTableHeader(null);
        //设置高度
        tableCenter.setRowHeight(30);
        //限制某列的宽度
        TableColumn cbColumn = tableCenter.getColumnModel().getColumn(1);
        cbColumn.setPreferredWidth(35);
        cbColumn.setMaxWidth(35);
        cbColumn.setMinWidth(35);

        TableColumn btnColumn = tableCenter.getColumnModel().getColumn(2);
        btnColumn.setPreferredWidth(100);
        btnColumn.setMaxWidth(100);
        btnColumn.setMinWidth(100);

        //定义复选框
        JCheckBox box = new JCheckBox();
        Button button = new Button("转JSON");

//        tableCenter.getColumnModel().getColumn(1).setCellEditor(new CheckBoxCellEditor());
//        tableCenter.getColumnModel().getColumn(1).setCellRenderer(new CWCheckBoxRenderer());
        //getColumn()中数字填对应的第几行添加复选框
        tableCenter.getColumnModel().getColumn(1).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {

            /*设置当复选框被选中整行被渲染*/
            box.setSelected(isSelected);

            /*设置复选框在单元格中居中*/
            box.setHorizontalAlignment((int) 0.5f);

            return box;
        });
        tableCenter.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    int row = tableCenter.getSelectedRow();
                    int column = tableCenter.getSelectedColumn();
//                    JCheckBox jCheckBox = (JCheckBox) tableCenter.getModel().getValueAt(row, 1);
//                    System.out.println("选择了： "+jCheckBox.isSelected());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // 添加
        frame.getContentPane().add(tableCenter);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }
}
