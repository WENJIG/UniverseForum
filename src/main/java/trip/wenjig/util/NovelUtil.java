package trip.wenjig.util;

public class NovelUtil {

    public static boolean checkNovelChapter(String line) {
        if (line.matches("(^\\s*第)([零一二三四五六七八九十百千万1234567890]{1,9})[章](\\s{0})(.*)($\\s*)"))
            return true;
        else
            return false;
    }

    public static boolean checkNovelChapter(String line, int i) {
        if (line.matches("(^\\s*第)([零一二三四五六七八九十百千万1234567890]{1,9})[卷](\\s{0})(.*)($\\s*)"))
            return true;
        else
            return false;
    }

}
