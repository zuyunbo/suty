package com.example.commoncenter.util.page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CH041 {


    /**
     * 读取文件内容
     * @param args
     */
    public static void main(String[] args) {
        //InputStream:是一个抽象类
        // \:是一个 转移符
        //表示磁盘路径的两种表示方式：1、\\   2、/
        try {
            //从文件地址中读取内容到程序中
            //1、建立连接
            InputStream is = new FileInputStream("/Users/zuyunbo/test.txt");
            //2、开始读取信息

			/*
			//方法1：一次只读一个
			System.out.println(is.read());//读取的是字节型的：49
			System.out.println((byte)is.read());//50
            */

            //方法2：定义数组，循环读取
            //先定义一个字节数组存放数据
            byte[] b = new byte[is.available()];//把所有的数据读取到这个字节当中
            //声明一个int存储每次读取到的数据
            int i = 0;
            //定义一个记录索引的变量
            int index = 0;
            //循环读取每个数据
            while((i=is.read())!=-1){//把读取的数据放到i中
                b[index]=(byte) i;
                index++;
            }
            //把字节数组转成字符串
            System.out.println(new String(b));
            //关闭流
            is.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //系统强制解决的问题：文件没有找到
            e.printStackTrace();
        } catch (IOException e) {
            //文件读写异常
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
