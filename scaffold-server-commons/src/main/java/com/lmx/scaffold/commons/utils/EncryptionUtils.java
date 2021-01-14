
package com.lmx.scaffold.commons.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * encryption utils
 */
public class EncryptionUtils {


    /**
     * @param rawStr raw string
     * @return md5(rawStr)
     */
    public static String getMd5(String rawStr) {
        return DigestUtils.md5Hex(null == rawStr ? StringUtils.EMPTY : rawStr);
    }

}
