package com.wtp.base.common.collection;

import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author wangtaiping
 * 2022/4/9 1:01
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        /**
         * 1、优先队列内部维护一个数组
         * 2、通过构造函数可以传入Comparable接口的实现，该实现由构造方法的泛型限定上界（也就是放入元素的比较），从小到大排列
         */
        PriorityQueue<Customer> people = new PriorityQueue<>(Comparator.comparing(Customer::getPriority));
        people.add(new Customer("a",2));
        people.add(new Customer("b",4));
        people.add(new Customer("c",2));
        people.add(new Customer("d",1));
        people.forEach(a->{
            System.out.println(a.name);
        });
    }

    @Data
    public static class Customer{
        private String name;
        //优先级
        private int priority;

        public Customer() {
        }

        public Customer(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
    }
}
