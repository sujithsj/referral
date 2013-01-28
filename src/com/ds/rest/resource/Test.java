package com.ds.rest.resource;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 19, 2013
 * Time: 7:06:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {


    public static int exists(String a, String b) {
        int loc = -1;

        if (a == null) {
            return loc;
        }

        if (b == null) {
            return loc;
        }

        char one[] = a.toCharArray();
        char two[] = b.toCharArray();


        if (one.length == 0) {
            return loc;
        }

        if (two.length == 0) {
            return loc;
        }

        if (two.length > one.length) {
            return loc;
        }

        boolean exists = false;
        for (int i = 0; (i < one.length && !exists); i++) {

            for (int j = 0; j < two.length; j++) {
                if (one[i] == two[j]) {
                    int prevI = i;
                    j++;

                    boolean found = true;
                    for (int k = i + 1; (k < one.length && j < two.length); k++, j++) {

                        if (one[k] != two[j]) {
                            found = false;
                            break;
                        }
                    }

                    if (!found) {
                        i = prevI;
                    }

                    if (j == two.length) {
                        loc = prevI + 1;
                        exists = true;
                        break;
                    }
                }
            }
        }

        return loc;
    }


    public static Object[] minimumCoinTrial(int s, int try1, int coin[], int nc) {
        Object[] result = new Object[2];
        for (int i = 0; i < nc; i++)
            if (s == coin[i]) {
                result[0] = true;
                result[1] = 1;

                return result;
            }

        if (s < 0) {
            System.exit(0);
        }


        int minimunCoin = s;
        boolean found = false;
        for (int i = 0; i < nc; i++) {
            int trialCoin = 0;
            if (s - coin[i] > 0) {
                Object resultArr[] = minimumCoinTrial(s - coin[i], trialCoin, coin, nc);

                if ((Boolean) resultArr[0]) {
                    if (trialCoin + 1 < minimunCoin) {
                        minimunCoin = trialCoin + 1;
                    }
                    found = true;
                }
            }

        }
        if (found) {

            result[0] = true;
            result[1] = minimunCoin;
            /*return true;*/
            return result;
        }

        result[0] = false;
        result[1] = -1;
        /*return false;*/
        return result;
    }


    static int minCoins(Vector<Integer> a, int S) {

        int coins[] = new int[a.size()];

        int k = 0;
        for (Integer x : a) {
            coins[k] = a.elementAt(k);
            k++;
        }

        

        int numberOfCoins = coins.length;
        int minimumNoCoins = S;

        boolean found = false;
        for (int i = 0; i < numberOfCoins; i++) {
            int try1 = 0;
            if (S - coins[i] > 0) {
                Object[] result = minimumCoinTrial(S - coins[i], try1, coins, numberOfCoins);

                if ((Boolean) result[0]) {
                    try1 = (Integer) result[1];
                    if (try1 + 1 < minimumNoCoins) minimumNoCoins = try1 + 1;
                    found = true;
                }
            }
        }


        if (found) {
            return (minimumNoCoins + 1);
        } else {
            return -1;
        }

    }

    public static void main(String[] args) {


        int s = 11;

        int coins[] = {1, 3, 5};



    }
}
