package com.haohuo.lambda;

import java.util.Objects;

/**
 * @author zhangpeike
 * @date 13:20 2019/1/18
 */
@FunctionalInterface//函数式接口检查注解 只能存在一个抽象方法
public interface WorkerInterface<T> {

     boolean doSomeWork(T t);
     //取反
     default WorkerInterface<T> workTest() {
          return t -> !doSomeWork(t);
     }
}
