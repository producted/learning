package com.haohuo.center;

import java.util.ArrayList;
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
        List<String> x = new ArrayList<>();
       /* list.stream().forEach(str -> {
            if (str.equals("2"))
                list.remove(str);
            x.add("4");
        });*/
       int count = 0;
        for (int i = list.size()-1; i >= 0; i--) {
            if (list.get(i).equals("3"))
                list.remove(list.get(i));
            count++;
        }
        System.out.println(list);
        System.out.println(count);
    }
}
