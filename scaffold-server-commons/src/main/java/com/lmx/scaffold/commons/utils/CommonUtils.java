
package com.lmx.scaffold.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * common utils
 */
public class CommonUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static String getStringForKey(String key) {
        return PropertyUtils.getString(key);
    }


}
