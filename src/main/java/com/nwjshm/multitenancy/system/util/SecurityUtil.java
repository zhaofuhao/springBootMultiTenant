package com.nwjshm.multitenancy.system.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * @author ：扫地僧
 * @date ：2023-05-11 10:19
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 密码加密解密
 **/
public class SecurityUtil {
    /**加密key*/
    private static String key = "JEECGBOOT1423670";

    //---AES加密---------begin---------
    /**加密
     * @param content
     * @return
     */
    public static String jiami(String content) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
        String encryptResultStr = aes.encryptHex(content);
        return encryptResultStr;
    }

    /**解密
     * @param encryptResultStr
     * @return
     */
    public static String jiemi(String encryptResultStr){
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
        //解密为字符串
        String decryptResult = aes.decryptStr(encryptResultStr, CharsetUtil.CHARSET_UTF_8);
        return  decryptResult;
    }
}
