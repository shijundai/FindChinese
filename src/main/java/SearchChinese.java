/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/2/28
 */

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@ClassName SearchChinese
 *@Description TODO
 *@Author Administrator
 *@Date 2020/2/28 17:03
 *@Version 1.0
 **/
public class SearchChinese {

    /**
     * java文件中的中文 双引号或者单引号之间的
     */
    public static String JAVA_CHINESE_STRING_REG = "(?<=\"|\')[\\s\\w]*[\\u4e00-\\u9fa5]+[\\s\\w]*(?=\"|\')";

    /**
     * java文件中需要排除的行号 @开头 //开头 或者带@的行中的中文
     */
    public static String JAVA_EXCLUDE_ROW_REG = "(^\\s*@|^\\s*//|^[\\w\\s\\(\\)\\<\\>\\?]*@).+";


    public static String JSP_EXCLUDE_ROW_REG = "(^\\s*\\<\\!\\-\\-|^\\s*//).*";

    public static String EL_CHINESE_STRING_REG = "(?<=\\>)[\\s\\w]*[\\u4e00-\\u9fa5]+[\\s\\w]*(?=\\<|：)";


    public static Set<String> searchFile(String filePath, String[] patterns, String[] excludePatterns) {
        Set<String> searchChineseList = new HashSet<>();
        StringBuffer content = new StringBuffer(readFile(filePath));

        Arrays.asList(excludePatterns).stream().forEach(o -> {
            Pattern pattern = Pattern.compile(o,Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
            Matcher m = pattern.matcher(content);
            content.replace(0,content.length(),m.replaceAll(""));
        });

        Arrays.asList(patterns).stream().forEach(o -> {
            Pattern pattern = Pattern.compile(o,Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
            Matcher m = pattern.matcher(content);
            while (m.find()){
                searchChineseList.add(m.group(0).trim());
            }
        });
        return searchChineseList;
    }

    /**
     * 查找文件中所有符合正则规则的字符
     * @param fileList
     * @param patterns
     * @return
     */
    public static Map<String,Set<String>> searchFiles(List<String> fileList, String[] patterns, String[] excludePatterns) {
        Map<String,Set<String>> resMap = new HashMap<>();
        fileList.stream().forEach(o -> resMap.put(o,searchFile(o, patterns, excludePatterns)));
        return resMap;
    }

    public static String readFile(String filePath) {
        try {
            return FileUtils.readFileToString(new File(filePath), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
