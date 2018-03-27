package org.cloud.manage.model.similarity;

public class Similarity {
	public static void main(String[] args) {
       /* String[] tag1 = { "起亚", "实拍", "汽车", "新闻", "广州车展", "东风", "资讯"};
        String[] tag2 = {"广州", "现场", "汽车", "国际车展", "新闻", "首发", "资讯", "现代", "概念", "北京"};*/
		String [] tag1 = {"洗衣机"};
		String [] tag2 = {"衣洗机"};
        double similar = levenshtein(tag1, tag2);
        System.out.println(similar);

    }

    public static float levenshtein(String[] str1, String[] str2) {
        // 计算两个字符串的长度。
        int len1 = str1.length;
        int len2 = str2.length;
        // 建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        // 赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        // 计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // 取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1, dif[i - 1][j] + 1);

            }
        }
        // 取数组右下角的值，同样不同位置代表不同字符串的比较
        // System.out.println("差异步骤：" + dif[len1][len2]);
        // 计算相似度
        float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length, str2.length);

        return similarity;
    }

    private static int min(int a, int b, int c) {
        int min = a < b ? a : b;
        return min < c ? min : c;
    }
}
