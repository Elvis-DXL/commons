package com.elvis.commons.utils;

import com.elvis.commons.prop.FtpProp;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Elvis
 * @since : 2020/8/28 17:37
 */
public final class FtpUtils {

    private FtpProp ftpProp;

    private FTPClient ftpClient;

    private static final String SLASH = "/";

    public FtpUtils(FtpProp ftpProp) {
        if (null == ftpProp || StringUtils.isEmpty(ftpProp.getHost()) || null == ftpProp.getPort()
                || StringUtils.isEmpty(ftpProp.getUsername()) || StringUtils.isEmpty(ftpProp.getPassword())) {
            throw new IllegalArgumentException("Configuration information error[" + ftpProp + "]");
        }
        this.ftpProp = ftpProp;
    }

    /**
     * 初始化FTP连接
     *
     * @throws IOException 连接失败异常
     */
    private void initFtpClient() throws IOException {
        ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.connect(ftpProp.getHost(), ftpProp.getPort());
        ftpClient.login(ftpProp.getUsername(), ftpProp.getPassword());
        int replyCode = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            throw new IOException("Failed to connect to FTP [Host=" + ftpProp.getHost() + ";Port=" + ftpProp.getPort() + "]");
        } else {
            ftpClient.enterLocalPassiveMode();
        }
    }

    /**
     * 上传文件至FTP
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param inStream 输入流
     * @return 是否成功
     */
    public boolean uploadFile(String filePath, String fileName, InputStream inStream) {
        boolean flag = false;
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filePath can not be empty");
        }
        if (StringUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("fileName can not be empty");
        }
        try {
            initFtpClient();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            flag = ftpClient.changeWorkingDirectory(filePath);
            if (!flag) {
                flag = createDir(filePath);
                if (!flag) {
                    throw new IOException("Failed to create directory：" + filePath);
                }
            }
            flag = ftpClient.storeFile(fileName, inStream);
            if (!flag) {
                throw new IOException("Failed to upload file：" + fileName);
            }
            inStream.close();
            ftpClient.logout();
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            IOUtils.closeStream(inStream);
        }
        return flag;
    }

    /**
     * 下载FTP文件
     *
     * @param filePath  文件路径
     * @param fileName  文件名
     * @param outStream 输出流
     * @return 是否成功
     */
    public boolean downloadFile(String filePath, String fileName, OutputStream outStream) {
        boolean flag = false;
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filePath can not be empty");
        }
        if (StringUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("fileName can not be empty");
        }
        InputStream inStream = null;
        try {
            initFtpClient();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory(filePath);
            inStream = ftpClient.retrieveFileStream(fileName);
            if (inStream == null) {
                throw new IOException("Failed to read file stream：" + fileName);
            }
            IOUtils.inToOut(inStream, outStream);
            ftpClient.logout();
            flag = true;
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            IOUtils.closeStream(inStream, outStream);
        }
        return flag;
    }

    /**
     * 删除FTP文件
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return 是否成功
     */
    public boolean deleteFile(String filePath, String fileName) {
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filePath can not be empty");
        }
        if (StringUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("fileName can not be empty");
        }
        boolean flag = false;
        try {
            initFtpClient();
            ftpClient.changeWorkingDirectory(filePath);
            ftpClient.deleteFile(fileName);
            ftpClient.logout();
            flag = true;
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 创建目录
     *
     * @param filePath 文件目录
     * @return 成功与否
     * @throws IOException 创建异常
     */
    private boolean createDir(String filePath) throws IOException {
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filePath can not be empty");
        }
        if (filePath.startsWith(SLASH)) {
            filePath = filePath.substring(1);
        }
        List<String> dirList = Arrays.asList(filePath.split(SLASH));
        if (dirList.size() > 0) {
            String path = "";
            boolean flag = false;
            for (String dir : dirList) {
                path += SLASH + dir;
                if (!existDir(path)) {
                    flag = ftpClient.makeDirectory(dir);
                    if (flag) {
                        flag = ftpClient.changeWorkingDirectory(dir);
                        if (!flag) {
                            throw new IOException("Failed to switch directory：" + dir);
                        }
                    } else {
                        throw new IOException("Failed to create directory：" + dir);
                    }
                }
            }
        }
        return true;
    }

    /**
     * 通过切换路径的方式判断服务器文件路径是否存在
     *
     * @param filePath 文件目录
     * @return 是否存在
     */
    private boolean existDir(String filePath) {
        boolean flag = false;
        try {
            flag = ftpClient.changeWorkingDirectory(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
