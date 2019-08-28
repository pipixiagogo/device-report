package com.zh.device.prepareFuture.jieYasuoUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    public static final String SUFFIX_NAME = "zip";
    /**
     *  压缩文件
     *  @param zipFile 压缩文件名路径
     *  @param fileList 待压缩的文件集合
    // *  @param isDeleteRes 如果出现同名压缩包, 是否删除原本的压缩包, 如果false,且出现同名则抛异常
     *  @return 压缩后的压缩包File
     */
    public static File compress(String zipFile, List<File> fileList, boolean isDelete){
        File f = isCheckFileNameByZip(zipFile);
        return compress( f.getName(), f.getParent(), getFilePathArray(fileList), false, isDelete );
    }
    /**
     *  压缩文件
     *  @param zipFile 压缩文件名路径
//     *  @param fileList 待压缩的文件集合
     *  @return 压缩后的压缩包File
     */
    public static File compress(String zipFile, String res){
        List<File> fileList = Arrays.asList(new File(res).listFiles());
        return compress( zipFile, fileList, true );
    }
    /**
     *  校验目标文件名称是否是zip格式
     *  @param zipFile
     *  @return
     */
    private static File isCheckFileNameByZip(String zipFile){
        return new File(zipFile.substring(0, isCheckFileNameInZip(zipFile)));
    }
    /**
     *  校验目标文件名称是否是zip格式
     *  @param zipFile
     *  @return
     */
    private static int isCheckFileNameInZip(String zipFile){
        int las = zipFile.lastIndexOf(".");
        if(las == -1){
            throw new RuntimeException( zipFile + " is not zip format! this format = ??? ");
        }
        String format = zipFile.substring(las + 1);
        if(!SUFFIX_NAME.equalsIgnoreCase(format)){
            throw new RuntimeException( zipFile + " is not zip format! this format = " + format);
        }
        return las;
    }
    private static String[] getFilePathArray (List<File> list){
        String[] strs = new String[list.size()];
        for(int index = 0; index < strs.length; index++){
            strs[index] = list.get(index).getPath();
        }
        return strs;
    }
    /**
     *  压缩文件
     *  @param zipName 压缩文件名, 无需加后缀
     *  @param zipFilePath 压缩路径
     *  @param filePaths 待压缩的文件路径
     *  @param isNewFolder 是否在压缩包新建同名文件夹
     *  @param isDeleteRes 如果出现同名压缩包, 是否删除原本的压缩包, 如果false,且出现同名则抛异常
     *  @return 压缩后的压缩包File
     */
    private static File compress(String zipName,String zipFilePath, String[] filePaths, boolean isNewFolder, boolean isDeleteRes) {
        File target = null;
        File source = new File(zipFilePath);
        if (source.exists()) {
            String base = isNewFolder? zipName + File.separator: "";
            zipName = zipName + "." + SUFFIX_NAME;// 压缩文件名=源文件名.zip
            target = new File(source.getPath(), zipName);
            if (target.exists()) {
                if(!isDeleteRes){
                    throw new RuntimeException("Compression package name repetition !");
                }
                target.delete(); // 删除旧的文件
            }
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            try {
                fos = new FileOutputStream(target);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                // 添加对应的文件Entry
                for(String fip:filePaths){
                    addEntry(base, new File(fip), zos);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                closeQuietly(zos, fos);
            }
        }
        return target;
    }

    /**
     *  解压文件
     *  filePath所代表文件系统不能与targetStr一致
     *  @param filePath 压缩文件路径
     *  @param targetStr 解压至所在文件目录
     */
    public static void decompression(String filePath, String targetStr) {
        isCheckFileNameInZip(filePath);
        File source = new File(filePath);
        if (source.exists()) {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {
                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry = null;
                while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                    File target = new File(targetStr, entry.getName());
                    if (!target.getParentFile().exists()) {
                        target.getParentFile().mkdirs();// 创建文件父目录
                    }
                    // 写入文件
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                closeQuietly(zis, bos);
            }
        }
    }

    /**
     *  扫描添加文件Entry
     *  @param base 基路径
     *  @param source 源文件
     *  @param zos Zip文件输出流
     *  @throws IOException
     */
    private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException {
        // 按目录分级，形如：/aaa/bbb.txt
        String entry = base.concat(source.getName());
        if (source.isDirectory()) {
            for (File file : source.listFiles()) {
                // 递归列出目录下的所有文件，添加文件Entry
                addEntry(entry + File.separator, file, zos);
            }
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read = 0;
                zos.putNextEntry(new ZipEntry(entry));
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            } finally {
                closeQuietly(bis, fis);
            }
        }
    }

    /**
     * 关闭一个或多个流对象
     *
     * @param closeables
     *            可关闭的流对象列表
     */
    public static void closeQuietly(Closeable... closeables) {
        try {
            if (closeables != null && closeables.length > 0) {
                for (Closeable closeable : closeables) {
                    if (closeable != null) {
                        closeable.close();
                    }
                }
            }
        } catch (IOException e) {

        }
    }
    public static void main(String[] args) {
        String res = "D://test";
        compress("D://testOne.zip", res);
        decompression("D://电影.zip", "D://testOne1");
    }

}
