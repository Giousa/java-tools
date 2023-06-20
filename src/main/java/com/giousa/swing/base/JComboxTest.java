package com.giousa.swing.base;

import javax.swing.*;
import java.awt.*;

public class JComboxTest {

    public static void main(String[] args) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建下拉框
        JComboBox comboBox = new JComboBox();
        comboBox.setPreferredSize(new Dimension(400,200));

        // 绑定下拉框选项
        String[] strArray = { "学生", "军人", "工人" };
        for (String item : strArray)
        {
            comboBox.addItem(item);
        }
        // 添加
        frame.getContentPane().add(comboBox);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }
}
