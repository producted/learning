package com.haohuo.center;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhangpeike
 * @date 21:08 2019/1/19
 */
public class Hello {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("3");
        list.add("3");
        list.add("4");
        list.add("5");
        List<String> list1 = new ArrayList<>();
        for (String str : list) {
            if (str.equals("3"))
                list1.add(str);
        }
        list.removeAll(list1);
        System.out.println(list);

    }
}
