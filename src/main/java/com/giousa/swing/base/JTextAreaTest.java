package com.giousa.swing.base;

import javax.swing.*;
import java.awt.*;

public class JTextAreaTest {

    public static void main(String[] args) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        JScrollPane jPanel = new JScrollPane();
        JTextArea jTextArea = new JTextArea();
        jTextArea.setText("111111111111\n1222222222233\n4332122223454");
        jTextArea.setPreferredSize(new Dimension(500, 400));
        jPanel.setViewportView(jTextArea);
        jPanel.setPreferredSize(new Dimension(500,400));

        // 添加
        frame.getContentPane().add(jPanel);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }
}
