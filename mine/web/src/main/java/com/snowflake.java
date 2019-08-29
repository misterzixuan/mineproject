//package com;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class snowflake {
//    private static Set<Long> keys = new HashSet<Long>();
//    public static void main(String[] args) throws Exception {
//        for(int i=0;i<100;i++) {
//            SnowFlakeKeyTest2.GetKey runner = new SnowFlakeKeyTest2().new GetKey();
//            new Thread (runner).start();;
//        }
//        System.out.println(keys);
//
//    }
//
//    class GetKey implements Runnable{
//
//        @Override
//        public void run() {
//            SnowFlake key = new SnowFlake(30,20);
//            long k = key.nextId();
//            if(!keys.contains(k)) {
//                keys.add(k);
//            }else {
//                System.out.println("重复:"+k);
//            }
//        }
//    }
//}
//}
