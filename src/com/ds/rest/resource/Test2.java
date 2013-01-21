package com.ds.rest.resource;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 19, 2013
 * Time: 7:06:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test2 {


    private static class rect {
        int topx, topy, botx, boty;

        rect(int x1, int y1, int x2, int y2) {
            topx = x1;
            topy = y1;
            botx = x2;
            boty = y2;
        }

    }

    ;


    /*private static int doesRectOverlap(rect ra, rect rb) {
        if (ra.topx < rb.botx && ra.botx > rb.topx &&
                ra.topy < rb.boty && ra.Y2 > rb.topy) {
            return 0;
        }

        return -1;
    }*/

    public static void main(String[] args) {


    }
}