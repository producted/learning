package com.haohuo.thread;

/**
 * @ClassName SeqCount
 * @Description threadLocal的简单使用-序列化自增ID
 * @Author Zhang Peike
 * @Date 2019/2/26 9:04
 **/
public class SeqCount {

    private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>(){
        protected Integer initialValue() {
            return 0;
        }
    };
    SeqCount(){
        super();
    }

    public int nexSeq(){
        seqCount.set(seqCount.get() + 1);
        return seqCount.get();
    }

    public static void main(String[] args) {
        SeqCount sequence = new SeqCount();
        SeqThread thread1 = new SeqThread(sequence);
        SeqThread thread2 = new SeqThread(sequence);
        SeqThread thread3 = new SeqThread(sequence);
        SeqThread thread4 = new SeqThread(sequence);
        SeqThread thread5 = new SeqThread(sequence);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        System.out.println(seqCount.get());
    }

    private static class SeqThread extends Thread{
        private SeqCount seqCount;
        SeqThread(SeqCount seqCount){
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() +
                        ".seqCount:" + seqCount.nexSeq());
            }
        }
    }


}
