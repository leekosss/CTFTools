package com.fxz.service.impl;
import com.fxz.pojo.NativeCipher;
import com.fxz.pojo.Result;
import com.fxz.pojo.Tools;
import com.fxz.service.ToolsService;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class ToolsServiceImpl implements ToolsService {
    static{
        String dllPath="E:\\JavaProgram\\CTFTools\\src\\main\\resources\\static\\lib\\Cipher.dll";
        System.load(dllPath);

    }
    @Override
    public Result base(Tools tools) {
        String type = tools.getType();
        String outputs = "";
        if (tools.getOptions() == 1) {   // 加密
            if ("32".equals(type)) {
                return Result.success("base32暂未实现~");
            } else if ("64".equals(type)) {
                try {
                    byte[] bytes = tools.getInputs().getBytes();
                    byte[] encodedBytes = Base64.getEncoder().encode(bytes);
                    return Result.success(new String(encodedBytes));
                } catch (Exception e) {
                    return Result.error("转换异常");
                }
            }
        } else if (tools.getOptions() == 2) {     // 解密
            if ("32".equals(type)) {
                return Result.success("base32暂未实现~");
            } else if ("64".equals(type)) {
                try {
                    byte[] encodedBytes = tools.getInputs().getBytes();
                    byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
                    return Result.success(new String(decodedBytes));
                } catch (Exception e) {
                    return Result.error("转换异常");
                }
            }
        }
        return Result.error("error");
    }

    @Override
    public Result caesar(Tools tools) {
        try {
            StringBuilder encryptedText = new StringBuilder();
            for (char ch : tools.getInputs().toCharArray()) {
                if (Character.isLetter(ch)) {
                    char base = Character.isUpperCase(ch) ? 'A' : 'a';
                    if (tools.getOptions() == 1) {
                        encryptedText.append((char) ((ch - base + Integer.parseInt(tools.getType())) % 26 + base));
                    } else if (tools.getOptions() == 2) {
                        int shiftKey = 26 - Integer.parseInt(tools.getType());
                        encryptedText.append((char) ((ch - base + shiftKey) % 26 + base));
                    } else {
                        return Result.error("error");
                    }
                } else {
                    encryptedText.append(ch);
                }
            }
            return Result.success(encryptedText.toString());
        } catch (Exception e) {
            return Result.error("转换异常");
        }
    }

    @Override
    public Result sha(Tools tools) {
        String type = tools.getType();
        if ("256".equals(type)) {
            try {
                // 创建SHA-256摘要算法的MessageDigest对象
                MessageDigest md = MessageDigest.getInstance("SHA-256");

                // 将字符串转换为字节数组并进行加密
                byte[] inputBytes = tools.getInputs().getBytes();
                byte[] sha256Bytes = md.digest(inputBytes);

                // 将字节数组转换为十六进制字符串
                StringBuilder hexString = new StringBuilder();
                for (byte b : sha256Bytes) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }
                return Result.success(hexString.toString());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return Result.error("NoSuchAlgorithmException");
            }
        }
        return Result.error("转换异常");

    }

    @Override
    public Result md(Tools tools) {
        String type = tools.getType();
        if ("5".equals(type)) {
            try {
                // 创建MD5摘要算法的MessageDigest对象
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 将字符串转换为字节数组并进行加密
                byte[] md5Bytes = md.digest(tools.getInputs().getBytes());
                // 将字节数组转换为十六进制字符串
                StringBuilder hexString = new StringBuilder();
                for (byte b : md5Bytes) {
                    String hex = Integer.toHexString(0xff & b);
                    if (hex.length() == 1) {
                        hexString.append('0');
                    }
                    hexString.append(hex);
                }

                return Result.success(hexString.toString());

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return Result.error("NoSuchAlgorithmException");
            }
        }
        return Result.error("error");
    }
    @Override
    public Result convert(Tools tools) {
        String type = tools.getType();
        if ("To16".equals(type)) {   // 转16进制
            // 获取字符串的字节数组
            byte[] byteArray = tools.getInputs().getBytes(StandardCharsets.UTF_8);
            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : byteArray) {
                // 0xFF用于确保取到的是无符号整数
                hexString.append(String.format("%02X", b & 0xFF));
            }
            return Result.success(hexString.toString());
        } else if ("From16".equals(type)) {     //从16进制转回去
            String hexString = tools.getInputs();
            // 移除"0x"前缀
            hexString = hexString.replaceAll("0x", "");
            // 移除","和" "间隔符
            hexString = hexString.replaceAll("[, ]", "");
            BigInteger bigInt = new BigInteger(hexString, 16);
            byte[] byteArray = bigInt.toByteArray();
            return Result.success(new String(byteArray));
        }
        return Result.error("error");
    }

    @Override
    public Result encryption(Tools tools) {
        String type = tools.getType();
        NativeCipher cipher = new NativeCipher();
        byte[] key = tools.getKey().getBytes(); // 密钥
        byte[] data = tools.getInputs().getBytes(); // 输入的串
        if("rc4".equals(type)) {       // rc4加解密
            if(tools.getOptions()==1) { // 加密
                try{
                    data = cipher.RC4Encrypt(data,key);
                    return Result.success(new String(data));
                }catch (Exception e) {
                    return Result.error("rc4加密失败");
                }
            }else if (tools.getOptions()==2) {  // 解密
                try{
                    data = cipher.RC4Encrypt(data,key);
                    return Result.success(new String(data));
                }catch (Exception e) {
                    return Result.error("rc4解密失败");
                }
            }else {
                return Result.error("error");
            }
        }
        return Result.error("error");
    }
}