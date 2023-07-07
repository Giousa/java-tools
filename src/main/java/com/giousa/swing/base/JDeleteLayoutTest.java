package com.giousa.swing.base;

import javax.swing.*;
import java.awt.*;

public class JDeleteLayoutTest {

    public static void main(String[] args) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);

        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加
        frame.getContentPane().add(getJPanel());

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }

    private static JPanel getJPanel() {
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setPreferredSize(new Dimension(200, 100));
        JLabel etfDesc = new JLabel(String.format("是否删除卷名: %s ", "2023-07"));
        Button btnCancel = new Button("取消");
        Button btnConfirm = new Button("确定");
        jPanel.add(etfDesc, BorderLayout.NORTH);

        JPanel jPanelButton = new JPanel(new BorderLayout());
        jPanelButton.add(btnCancel, BorderLayout.WEST);
        jPanelButton.add(btnConfirm, BorderLayout.EAST);
        jPanel.add(jPanelButton, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> {
            System.out.println("取消");
        });

        btnConfirm.addActionListener(e -> {
            System.out.println("确定");
        });
        return jPanel;
    }
}
