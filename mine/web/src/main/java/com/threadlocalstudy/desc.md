1.threadlocal:用于解决线程之间并发的问题，当多个线程使用该变量时，threadlocal为每个线程创建一个副本，所以每个线程
    只能改变自己的副本，对其他线程没有影响。
2.