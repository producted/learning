package com.haohuo.lambda;

/**
 * @author zhangpeike
 * @date 13:30 2019/1/18
 */
public class WorkerInterfaceTest {

    public static void execute(WorkerInterface workerInterface){
        System.out.println(workerInterface.doSomeWork(-1));
    }

    public static void main(String[] args) {
        WorkerInterface<Integer> a = x -> x < 6;
        execute(a);
        System.out.println(a.doSomeWork(5));
        System.out.println(a.workTest().doSomeWork(5));
    }


}
