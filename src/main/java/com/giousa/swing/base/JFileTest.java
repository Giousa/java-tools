package com.giousa.swing.base;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class JFileTest {

    public static void main(String[] args) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);
        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        JButton btn = new JButton("文件打开");
        btn.addActionListener(e -> {
            FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
            System.out.println("桌面路径: " + fsv.getHomeDirectory()); //得到桌面路径
            String path = fsv.getHomeDirectory().getPath();

            path = "/Users/zhangmengmeng/Desktop/ftl_temp_file";
            JFileChooser fileChooser = new JFileChooser(path);

//            fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
            fileChooser.setDialogTitle("选择存储目录");
            fileChooser.setApproveButtonText("确定");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(btn);
            if (JFileChooser.APPROVE_OPTION == result) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("选择路径： " + selectedFile.getPath());
            }

        });

        // 添加
        frame.getContentPane().add(btn);

        // 显示窗口
        frame.pack();
        frame.setVisible(true);
    }
}
