package com.performance.tasks;

import java.util.*;

import static java.lang.Math.pow;

public class task1 {
    /**
     * Transform number as String from Custom base to decimal base
     * @param nb String number in base system
     * @param base dictionary of custom system
     * @return nb as int in decimal system
     */
    public static int fromCustomBaseToDecimal(String nb, String base){
        Map<Character, Integer> baseDict = new HashMap<>();
        char [] arrOfDict = base.toCharArray(), arrOfnb = nb.toCharArray();
        int baseAsInt = base.length(), result = 0;
        for (int i = 0 ; i < baseAsInt; i++){
            if (!baseDict.containsKey(arrOfDict[i]))
                baseDict.put(arrOfDict[i],i);
        }
        baseAsInt = baseDict.size();
        for (int i = nb.length() - 1; i >= 0; i--){
            char c = arrOfnb[nb.length() - (i + 1)];
            result += baseDict.get(c) * pow(baseAsInt, i);
        }
        return result;
    }

    /**
     * Transform number as int from decimal to different base
     * @param nb int in decimal system
     * @param base dictionary of new custom system
     * @return nb as String in base system
     */
    public static String itoBase(int nb, String base){
        Map<Integer, Character> baseDict = new HashMap<>();
        StringBuilder result = new StringBuilder();
        char [] arrOfDict = base.toCharArray();
        int baseAsInt = base.length(), temp = nb;
        for (int i = 0 ; i < base.length(); i++){
            if (!baseDict.containsValue(arrOfDict[i]))
                baseDict.put(i,arrOfDict[i]);
        }

        while (temp > 0){
            result.append(baseDict.get(temp % baseAsInt));
            temp /= baseAsInt;
        }
        return result.reverse().toString();
    }

    /**
     * Transform number as String from baseScr system to baseDst system
     * @param nb String number in baseSrc system
     * @param baseSrc dictionary of source custom system
     * @param baseDst dictionary of new custom system
     * @return nb as String in baseDst system
     */
    public static String itoBase(String nb, String baseSrc, String baseDst){
        return itoBase(fromCustomBaseToDecimal(nb, baseSrc), baseDst);
    }

    public static void main(String[] args) {
        if ((args.length > 0) && (args.length <= 3)) {
            try {
                String nb = args[0];
                String baseSrc = args[1];
                if (args.length == 3) {
                    String baseDst = args[2];
                    System.out.println(itoBase(nb, baseSrc, baseDst));
                } else
                    System.out.println(itoBase(Integer.parseInt(nb), baseSrc));
            } catch (Exception e) {
                System.out.println("usage");
                System.exit(-1);
            }
        }
        else System.out.println("usage");
    }
}
