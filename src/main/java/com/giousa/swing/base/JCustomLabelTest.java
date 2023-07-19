package com.giousa.swing.base;

import com.giousa.swing.base.checkbox.CustomTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class JCustomLabelTest {

    public static void main(String[] args) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        JPanel jPanel = new JPanel(new BorderLayout());
        Object[] columnNames = {"name", "choose"};
        Object[][] rowData = {{"曹操", true},
                {"孙权", true},
                {"刘备", true},
                {"袁绍", true},
                {"袁术", true}};


        JTable jTable = new JTable();
        jTable.setModel(new CustomTableModel(columnNames,rowData));
        //给表格加入点击监听，因为我在表格的单元格改变那个监听遇到了bug，没有解决掉就改成了鼠标监听
        List<Integer> list = new ArrayList<>();
        jTable.addMouseListener(new MouseAdapter() {
            //不要用click点击监听，如果速度过快就会识别为双击，有bug
            @Override
            public void mousePressed(MouseEvent e) {
                //用到后边判断我到后便会说道为啥要放到重写的函数里定义
                int flag = 0;
                if (e.getClickCount() == 1) {
                    int row = jTable.getSelectedRow();
                    int column = jTable.getSelectedColumn();
                    //复选框在哪列填多少，限制鼠标点击的位置
                    if (column == 1) {
                        for (int i = 0; i < list.size(); i++) {
                            int temp = list.get(i);
                            if (temp == row) {
                                //如果新点击的和之前行一样说明是取消了选中就要移除这个行，并且这一行不能加入到集合中
                                flag++;
                                list.remove((Integer) row);
                            }
                        }
                        if (flag == 0) {
                            list.add(row);
                        }
                    }
                }
            }
        });
        // 添加
        Button btn = new Button("按钮");
        btn.addActionListener(e -> {
            System.out.println("点击了");
            for (int i = 0; i < 5; i++) {
                String tableName = (String) jTable.getModel().getValueAt(i, 0);
                Boolean flag = (Boolean) jTable.getModel().getValueAt(i, 1);
                System.out.println("tableName = "+tableName+"，flag = "+flag);
            }
        });
        jPanel.add(jTable,BorderLayout.CENTER);
        jPanel.add(btn,BorderLayout.SOUTH);
        frame.getContentPane().add(jPanel);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }
}
