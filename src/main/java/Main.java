/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/2/27
 */

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *@ClassName Main
 *@Description TODO
 *@Author Administrator
 *@Date 2020/2/27 17:53
 *@Version 1.0
 **/
public class Main {

    public static void main(String [] args) {
        Set<String> set = javaFileChineseSet();
        set.addAll(jspFileChineseSet());
        set.forEach(System.out::println);
        //所有中文
        //没有key值的

    }


    public static Set<String> javaFileChineseSet() {
        List<String> files = ListFile.fileList("D:\\workspace\\cnbots\\src\\main\\java\\com\\cnbot\\kindergarten","Controller.java",null);
        Map<String, Set<String>> res = SearchChinese.searchFiles(files, new String[]{SearchChinese.JAVA_CHINESE_STRING_REG}, new String[] {SearchChinese.JAVA_EXCLUDE_ROW_REG});
        Set<String> allChineseSet = new HashSet<>();
        res.entrySet().forEach(o -> o.getValue().forEach(allChineseSet::add));
        return allChineseSet;
    }

    public static Set<String> jspFileChineseSet() {
        List<String> files = ListFile.fileList("D:\\workspace\\cnbots\\src\\main\\webapp\\webpage\\com\\cnbot",".jsp",null);
        Map<String, Set<String>> res = SearchChinese.searchFiles(files, new String[]{SearchChinese.JAVA_CHINESE_STRING_REG, SearchChinese.EL_CHINESE_STRING_REG}, new String[] {SearchChinese.JAVA_EXCLUDE_ROW_REG, SearchChinese.JSP_EXCLUDE_ROW_REG});
        Set<String> allChineseSet = new HashSet<>();
        res.entrySet().forEach(o -> o.getValue().forEach(allChineseSet::add));
        return allChineseSet;
    }

    public static void findJavaFileChinese() {
        List<String> files = ListFile.fileList("D:\\workspace\\cnbots\\src\\main\\java\\com\\cnbot\\kindergarten","Controller.java",null);
        Map<String, Set<String>> res = SearchChinese.searchFiles(files, new String[]{SearchChinese.JAVA_CHINESE_STRING_REG}, new String[] {SearchChinese.JAVA_EXCLUDE_ROW_REG});
        Set<String> allChineseSet = new HashSet<>();
        res.entrySet().forEach(o -> o.getValue().forEach(allChineseSet::add));
        System.out.println(allChineseSet.size());
        allChineseSet.forEach(System.out::println);
    }


    public static void findJspFileChinesee() {
        List<String> files = ListFile.fileList("D:\\workspace\\cnbots\\src\\main\\webapp\\webpage",".jsp",null);
        Map<String, Set<String>> res = SearchChinese.searchFiles(files, new String[]{SearchChinese.JAVA_CHINESE_STRING_REG, SearchChinese.EL_CHINESE_STRING_REG}, new String[] {SearchChinese.JAVA_EXCLUDE_ROW_REG, SearchChinese.JSP_EXCLUDE_ROW_REG});
        Set<String> allChineseSet = new HashSet<>();
        res.entrySet().forEach(o -> o.getValue().forEach(allChineseSet::add));
        System.out.println(allChineseSet.size());
        allChineseSet.forEach(System.out::println);
    }

    //文件 行数 上下文 中文


    //1.扫描规则
    //jsp页面
    //<title>机器人基础数据</title> <label for="cnbotsTypeId" class="col-sm-3 control-label">机器人类型：</label>  title="机器人类型" placeholder="请输入硬件编号SN" '错误'



    //1. 按文件扫描出文件中所有中文
    //输入 文件夹列表 扫描规则 正则规则
    //输出中文字段集合 去除重复

    //替换
    //1. 扫描中文及对应key值
    //输入 中文及对应key值的文件 扫描规则 正则规则
    //扫描及替换
}
