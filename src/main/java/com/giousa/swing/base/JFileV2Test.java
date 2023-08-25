package com.giousa.swing.base;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.File;

public class JFileV2Test {

    public static void main(String[] args) {
        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            LookAndFeel lookAndFeel = new NimbusLookAndFeel();
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
//                JFrame.setDefaultLookAndFeelDecorated(true);
        // 创建及设置窗口
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加 "Hello World" 标签
        JButton btn = new JButton("文件打开");
        btn.addActionListener(e -> {
            FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
            System.out.println("桌面路径: " + fsv.getHomeDirectory()); //得到桌面路径
            String path = fsv.getHomeDirectory().getPath();

            path = "/Users/zhangmengmeng/Downloads/other/开药门诊标签账号.xlsx";

            JFileChooser fileChooser = new JFileChooser(path);

            //后缀名过滤器
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter(
                    "*.xls", "xls");
            FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
                    "*.xlsx", "xlsx");
            fileChooser.setFileFilter(filter1);
            fileChooser.setFileFilter(filter2);


            fileChooser.setDialogTitle("选择Excel文件");
            fileChooser.setApproveButtonText("确定1111111");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(null);
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
