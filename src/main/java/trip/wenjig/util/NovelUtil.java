package trip.wenjig.util;

public class NovelUtil {

    public static boolean checkNovelChapter(String line) {
        return line.matches("(^\\s*第)([零一二三四五六七八九十百千万1234567890]{1,9})[章](\\s{0})(.*)($\\s*)");
    }

    public static boolean checkNovelChapter(String line, int i) {
        return line.matches("(^\\s*第)([零一二三四五六七八九十百千万1234567890]{1,9})[卷](\\s{0})(.*)($\\s*)");
    }

}
