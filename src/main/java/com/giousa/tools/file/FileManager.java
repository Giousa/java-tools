package com.giousa.tools.file;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class FileManager {

    public static void writeContent(String fileName, String content) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                System.out.println("文件不存在，创建文件 >>> "+fileName);
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void writeFile(String fileDir, String fileName, String content) {
        //文件夹为空， 生成文件夹
        createDir(fileDir);

        //在文件夹目录下新建文件
        File file = new File(fileDir + fileName);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            if (!file.exists()) {
                boolean fileCreateFlag = file.createNewFile();
                if (fileCreateFlag) {
                    System.out.println("文件创建成功. fileName = " + (fileDir + fileName));
                }
                fos = new FileOutputStream(file);
            } else {
                //清空文件
                clearFile(file);
                //追加内容
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");

            osw.write(content);
            System.out.printf("文件写入完成! fileDir: %s,fileName: %s \n", fileDir, fileName);
        } catch (Exception e) {
            System.out.printf("文件写入异常!  fileDir: %s,fileName: %s \n", fileDir, fileName);
        } finally {
            // 关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                System.out.println("关闭流异常！");
                e.printStackTrace();
            }
        }

    }

    /**
     * 生成文件夹
     */
    public static void createDir(String fileDir) {
        File folder = new File(fileDir);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.setWritable(true, false);
            folder.mkdirs();
            System.out.printf("文件夹创建成功. fileDir: %s \n", fileDir);
        } else {
            System.out.printf("文件夹已存在. fileDir: %s \n", fileDir);
        }
    }

    private static void clearFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            System.out.println("文件清理成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件清理失败！");

        }
    }


    /**
     * 递归删除
     * 删除某个目录及目录下的所有子目录和文件
     *
     * @param file 文件或目录
     * @return 删除结果
     */
    public static void delFiles(File file) {
        //目录
        if (file.isDirectory()) {
            File[] childrenFiles = file.listFiles();
            for (File childFile : childrenFiles) {
                delFiles(childFile);
            }
        }
        //删除 文件、空目录
        file.delete();
        System.out.printf("删除所有文件或目录. filePath:  %s \n", file.getPath());
    }


    /**
     * 获取FTL临时存储目录
     *
     * @return
     */
    public static String getFtlTempPath(String path) {
        String ftlTempPath = path.endsWith("/") ? path : StringUtils.join(path, "/");
        createDir(ftlTempPath);
        return ftlTempPath;
    }

}
