package com.niostudy;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BufferTest {

    private static String a;
    public static void main(String[] args) {
//        test1();
//        copyImg();
//          test4();
        channelData();
    }


    public static void test1() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024); /*创建非直接缓冲区，直接new byte[] 创建  mark=-1标记（可用reset复原指针位置）  position=0指针位置  limit=capacity可操作数限制  capacity容量*/

        byteBuffer.flip();  /*切换成读模式，其实就是修改了limit=position  position=0  mark=-1*/

        byteBuffer.rewind();  /*修改position位置   position=0*/

        byteBuffer.reset();  /*修改position位置，将mark标记赋值给它*/

        byteBuffer.hasRemaining(); /*比较position 和limit   如果postion小于limit说明还有可操作位  返回true*/

        byteBuffer.remaining();  /*limit-position 返回可操作的个数*/

        byteBuffer.clear();  /*重置mark=-1  limit=capacity  position=0  数据并没有清除*/
    }


    /*直接缓冲区，非直接缓冲区
    *
    *
    * 直接缓冲区，物理磁盘与程序的交互 通过内存（jvm内存）作为中间件完成： 有点提高交互效率，但是对资源失去控制比较浪费内存   磁盘  <----> 内存 <----->程序
    * 非直接缓冲区         节约内存，但是效率低下需要copy         磁盘  <----> <缓存---copy--->缓存 <----->程序
    *
    *
    * */
    public void test2(){
            /*创建直接缓冲区*/
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        System.out.println(byteBuffer.isDirect());  /*isDirect判断是否是直接缓冲区 调用系统函数创建，其他mark position limit capacity 四个歌参数和非直接缓冲初始值一样*/
    }



    /*
    * 通道相对于cpu是独立的，原始的流没次操作io都需要以来cpu ，当大量操作io是  导致cpu性能下降，因此通道在处理能力上有很大提升
    * 通道本身是不存储数据的，因此需要配合缓冲区一起使用来完成交互数据
    * 通道 api 都位于  java.nio.channels.channel 接口：
    * filechannel
    * socketchannel
    * servercocketchannel
    * datagramchannel
    *
    *
    * 获取方式1.7以后有三种方式获取通道
    *1.java内置方式
    * 本地IO
    * fileInputstream/fileoutputstream
    * reandomAccessfile
    *
    *
    * 网络io
    * socket
    * serversocket
    * datagramsocket
    *
    *
    * 2.nio.2 针对各个通道提供了静态方法open()来获取通道
    * 3.files工具类 newByteChannel（）获取通道
    *
    * */


    /*间接缓冲区  复制文件*/
    public static  void copyImg(){
        try {
            FileChannel readChannel = (FileChannel) Files.newByteChannel(Paths.get("E:\\testfile\\1.jpg"), new StandardOpenOption[]{StandardOpenOption.READ});
            File file=new File("E:\\testfile\\2.jpg");
            if(!file.exists()){
                file.createNewFile();
            }
            FileChannel writeChannel = (FileChannel) Files.newByteChannel(Paths.get("E:\\testfile\\2.jpg"), new StandardOpenOption[]{StandardOpenOption.WRITE});
            ByteBuffer byteBuffer=ByteBuffer.allocate((int)readChannel.size());
           while(readChannel.read(byteBuffer)!=-1){
               byteBuffer.flip();
               writeChannel.write(byteBuffer);
               byteBuffer.clear();
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*直接缓存区  复制文件*/
    public static  void test4(){
        try {
            FileChannel read = FileChannel.open(Paths.get("E:\\testfile\\1.jpg"), StandardOpenOption.READ);
            FileChannel write=FileChannel.open(Paths.get("E:\\testfile\\2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

            MappedByteBuffer readby = read.map(FileChannel.MapMode.READ_ONLY, 0, read.size());
            MappedByteBuffer writeby = write.map(FileChannel.MapMode.READ_WRITE, 0, read.size());
            byte[] b=new byte[readby.limit()];
            readby.get(b);

            writeby.put(b);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*通道直接交互数据*/
    public static void channelData(){
        try {
            FileChannel read = FileChannel.open(Paths.get("E:\\testfile\\1.jpg"), StandardOpenOption.READ);
            FileChannel write=FileChannel.open(Paths.get("E:\\testfile\\2.jpg"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
            read.transferTo(0,read.size(),write);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
