package com.giousa.swing.base;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class JFolderTest {

    public static void main(String[] args) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);
        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        JButton btn = new JButton("目录打开");
        btn.addActionListener(e -> {
            String path = "/Users/zhangmengmeng/Desktop/ftl_temp_file";
//            JFileChooser fileChooser = new JFileChooser(path);
//            fileChooser.setDialogTitle("选择存储目录");
//            fileChooser.setApproveButtonText("确定");
//            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//            fileChooser.showOpenDialog(btn);

            File file=new File(path);
            try {
                java.awt.Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // 添加
        frame.getContentPane().add(btn);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }
}
