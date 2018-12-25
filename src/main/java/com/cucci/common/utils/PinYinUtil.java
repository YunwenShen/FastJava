package com.cucci.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.List;

/**
 * pinyin操作类
 *
 * @author shenyw
 */
public class PinYinUtil {

    private PinYinUtil() {
    }

    /**
     * 得到单个字符的pinYin形式
     *
     * @param c 单个字符
     * @return 拼音列表
     */
    private static List<String> getSpell(char c) {
        List<String> list = new ArrayList<>();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        // 小写
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 没有音调数字
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        String[] result = new String[]{};
        if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
            try {
                result = PinyinHelper.toHanyuPinyinStringArray(c, format);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            if (result == null) {
                return list;
            }
            //去重复项
            for (String str : result) {
                if (list.indexOf(str) == -1) {
                    list.add(str);
                }
            }
        } else {
            list.add(String.valueOf(c));
        }
        return list;
    }

    public static String getSpell(String str) {
        int iLength = str.toCharArray().length;
        String[][] strArray = new String[iLength][];
        char[] clist = str.toCharArray();
        //组建二维数组
        for (int i = 0; i < clist.length; i++) {
            List<String> slist = getSpell(clist[i]);
            if (slist == null || slist.size() == 0) {
                return "";
            }
            int slength = slist.size();
            strArray[i] = new String[slength];
            if (slength == 0) {
                strArray[i][0] = String.valueOf(clist[i]);
            } else if (slength == 1) {
                strArray[i][0] = slist.get(0);
            } else {
                for (int j = 0; j < slist.size(); j++) {
                    strArray[i][j] = slist.get(j);
                }
            }
        }

        //二维数组排列组合
        StringBuilder result = new StringBuilder();
        int max = 1;
        for (String[] aStrArray : strArray) {
            max *= aStrArray.length;
        }
        for (int i = 0; i < max; i++) {
            StringBuilder s = new StringBuilder();
            int temp = 1; // 注意这个temp的用法。
            for (String[] aStrArray : strArray) {
                temp *= aStrArray.length;
                String tempStr = aStrArray[i / (max / temp) % aStrArray.length];
                s.append(tempStr);
            }
            result.append(",").append(s);
        }
        return result.toString();
    }

    public static List<String> getFirstSpell(char c) {
        List<String> list = new ArrayList<String>();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        String[] result = new String[]{};
        if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
            try {
                result = PinyinHelper.toHanyuPinyinStringArray(c, format);

            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            if (result == null) {
                return list;
            }
            //去重复项
            for (String str : result) {
                str = str.substring(0, 1);
                if (list.indexOf(str) == -1) {
                    list.add(str);
                }
            }
        } else {
            list.add(String.valueOf(c));
        }
        return list;
    }

    public static String getFirstSpell(String str) {
        int iLength = str.toCharArray().length;
        String[][] strArray = new String[iLength][];
        char[] clist = str.toCharArray();
        //组建二维数组
        for (int i = 0; i < clist.length; i++) {
            List<String> slist = getFirstSpell(clist[i]);
            if (slist == null || slist.size() == 0) {
                return "";
            }
            int slength = slist.size();
            strArray[i] = new String[slength];
            if (slength == 0) {
                strArray[i][0] = String.valueOf(clist[i]);
            } else if (slength == 1) {
                strArray[i][0] = slist.get(0);
            } else {
                for (int j = 0; j < slist.size(); j++) {
                    strArray[i][j] = slist.get(j);
                }
            }
        }

        //二维数组排列组合
        StringBuilder result = new StringBuilder();
        int max = 1;
        for (int i = 0; i < strArray.length; i++) {
            max *= strArray[i].length;
        }
        for (int i = 0; i < max; i++) {
            String s = "";
            int temp = 1; // 注意这个temp的用法。
            for (int j = 0; j < strArray.length; j++) {
                temp *= strArray[j].length;
                String tempStr = strArray[j][i / (max / temp) % strArray[j].length];
                s += tempStr;
            }
            result.append(",").append(s);
        }
        return result.toString().substring(1);
    }
}
