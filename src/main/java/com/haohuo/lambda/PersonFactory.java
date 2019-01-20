package com.haohuo.lambda;

import lombok.Data;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangpeike
 * @date 14:01 2019/1/18
 */
public class PersonFactory {


    public static void main(String[] args) {

        Predicate<Integer> bigerThan6 = x -> {
            System.out.println("hello world!");
            return x<6;
        };
        System.out.println(bigerThan6.test(6));
        System.out.println(bigerThan6.negate().test(5));

        BinaryOperator<Integer> biopt = (x,y) -> x-y;
        System.out.println(biopt.apply(5, 2));

        List<Person> persons = getPersons(14);
        int count = 0;
        //求集合中男的个数--传统
        Iterator<Person> iterator = persons.iterator();
        while (iterator.hasNext()) {
            Person next = iterator.next();
            if (next.getGen().equals("男"))
                count++;
        }
        //stream.filter
        long count1 = persons.stream().filter(x -> x.getGen().equals("男")).count();
        System.out.println(count1);
        System.out.println(count);

        //将集合内全部转为大写
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        collected.add("cool");
        collected.add("delta");
        //传统
        List<String> objects = new ArrayList<>();
        for (String s : collected) {
            objects.add(s.toUpperCase());
        }
        System.out.println(objects);
        //stream.map
//        List<String> collect = collected.stream().map(str -> str.toUpperCase()).collect(Collectors.toList());
        collected.stream().map(str -> str.toUpperCase()).count();
        System.out.println(collected + "collected");

        /**
         * 测试stream.flatMap类似于addAll的功能 优势：不止2个可以合并，可以更多
         */
        List<Integer> collected0 = new ArrayList<>();
        collected0.add(1);
        collected0.add(3);
        collected0.add(5);
        List<Integer> collected1 = new ArrayList<>();
        collected1.add(2);
        collected1.add(4);
        collected1 = Stream.of(collected0, collected1)
                .flatMap(num -> num.stream()).collect(Collectors.toList());
        System.out.println(collected1);// 1,3,5,2,4

        //利用stream.reduce求和 xy为BinaryOperator的apply函数
        int sumAll = Stream.of(1,3,-1,90)
                .reduce(0,(x, y) -> x + y);
        /*int sumAll = Stream.of(1,3,-1,90)
                .reduce(0,Integer::sum);//Integer在java8中集成了sum求和方法 ::的简单使用*/
        System.out.println(sumAll);

        //stream.mapTot  做简单统计、类型有int long double
        List<Person> personLists =  new PersonFactory().getPersons(8);//随机获取8个person实例
        IntSummaryStatistics intSummaryStatistics = personLists.stream().mapToInt(person-> person.getAge()).summaryStatistics();
        System.out.println("最大年龄："+intSummaryStatistics.getMax()); //最大值
        System.out.println("最小年龄："+intSummaryStatistics.getMin()); //最小值
        System.out.println("年龄总和："+intSummaryStatistics.getSum()); //总计
        System.out.println("人数："+intSummaryStatistics.getCount());   //个数
        System.out.println("平均年龄："+intSummaryStatistics.getAverage());//平均数返回的是double类型

        //获取，年龄在加上5岁后仍然大于8岁的男性 的firstName
        List<Person> peoples = getPersons(100);
        ArrayList<String> nameList = new ArrayList<>();
        peoples.stream().map(person -> { person.setAge(person.getAge() + 5);//年龄加5岁
            return person; })
                .filter(person -> person.getAge() >= 8)//年龄小于8
                .filter(person -> person.getGen().equals("男"))//男性
                .forEach(person -> nameList.add(person.getFirstName()));//获取姓氏
        System.out.println(nameList);


        //利用Stream来实现，将personList的姓氏和性别组合成新的List
        List<String> names = peoples
                .stream()
                .flatMap(person -> Stream.of(person.getName(),person.getGen())).collect(Collectors.toList());
        System.out.println(names);
    }


    public static List<Person> getPersons(int personNum) {
        String[] firstName = {"孙","上官","刘","李","赵","李","王","张"};
        String[] endName = {"宏强","三","四","壁咚","古来","八戒","恒达"};
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < personNum - 1; i++) {
            int findex = new Random().nextInt(firstName.length);
            int eindex = new Random().nextInt(endName.length);
            int age = (findex + 1) * (eindex +1);
            Person person = new Person();
            person.setAge(age);
            person.setName(endName[eindex] + firstName[findex]);
            person.setFirstName(firstName[findex]);
            person.setGen(age % 3 ==0?"男":"女");
            personList.add(person);
        }
        return personList;
    }
    //练习
    public static void question(){
        //思考：两处打印出来的是否相同，为什么
        List<Person> personList = new PersonFactory().getPersons(5);//随机生成5个person实例
        List<Integer> ages = new ArrayList<>();
        personList.stream().forEach(person -> ages.add(person.getAge()));
        System.out.println(ages);//第一处打印
        ages.clear();
        personList.stream().map(person -> {
            person.setAge(person.getAge() + 10);
            return person;
        }).count();
        personList.stream().forEach(person -> ages.add(person.getAge()));
        System.out.println(ages);

        //思考：此处打印出来的是大写还是小写，为什么？
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
        collected.add("cool");
        collected.add("delta");
        collected.stream().map(string -> string.toUpperCase())
                .count();
        System.out.println(collected);
    }

}
@Data
class Person {
    private int age;
    private String name;
    private String firstName;
    private String gen;
}
