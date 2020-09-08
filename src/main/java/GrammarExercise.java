import java.util.*;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner in = new Scanner(System.in);
        String firstWordList = in.nextLine();
        String secondWordList = in.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        System.out.println(result);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        // 连续逗号和非字母检测报RuntimeException
        String word = firstWordList.concat(secondWordList);
        if (word.matches(".*?,,+.*") || word.matches("[^a-zA-Z,]")) {
            throw new RuntimeException("input not valid");
        }
        // 全部转为大写字母
        firstWordList = firstWordList.toUpperCase();
        secondWordList = secondWordList.toUpperCase();
        // 根据逗号分隔字符串
        String [] first = firstWordList.split(",");
        String [] second = secondWordList.split(",");
        // 找出重复的字符串，并去重排序，字母间插入空格
        List<String> result = Arrays.asList(first).stream().filter(item -> Arrays.asList(second).contains(item)).collect(Collectors.toList());
        result = new ArrayList<>(new HashSet<>(result));
        Collections.sort(result);
        String regex = "(.{2})";
        result.forEach(item -> item.replaceAll(regex,"$1 "));
        return result;
    }
}
