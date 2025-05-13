package org.example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {

    private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

    public static String getUnicode(String text){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<text.length();i++){
            char charAt = text.charAt(i);
            String hex = String.format("\\u%04x", (int) charAt);
            sb.append(hex);
        }
        logger.info("input:[{}] output:[{}]",text, sb);
        return sb.toString();
    }
}
