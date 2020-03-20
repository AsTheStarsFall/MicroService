package com.tianhy.springbootmybatis;

import com.tianhy.springbootmybatis.enumeration.SexEnum;
import com.tianhy.springbootmybatis.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/17
 **/
@Slf4j
public class ObjectBinaryTest {

    public static void main(String[] args) {
        User user = new User(1L, "name", "note", SexEnum.FEMALE);
        //序列化
        //对象转为字节数组的过程，Object->byte[] bos.toByteArray()
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        //反序列化
        //字节数组到对象的过程，byte[]->obj ois.readObject()
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            //写对象转为二进制数据
            oos.writeObject(user);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            //读转为对象
            User user1 = (User) ois.readObject();
            System.out.println(user1.toString());
        } catch (IOException | ClassNotFoundException e) {
            log.error("err");
        }
    }

    @Test
    public void serialiable(){
        String str = "tianhy";
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(str);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            System.out.println(Arrays.toString(bytes));
            oos.close();
            bos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void fileTest() {
        //创建文件
        File file = new File("testFile");
        try {
            if (file.exists()) {
                file.createNewFile();
            }
            //文件输出流
            FileOutputStream fos = new FileOutputStream(file);
            //文件通道
            FileChannel fileChannel = fos.getChannel();
            //缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            //写入内容
            byteBuffer.put("写点东西".getBytes("UTF-8"));
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
            fos.close();
            fileChannel.close();


            //获取文件
            Path path = Paths.get("testFile");
            //文件通道
            FileChannel fileChannel1 = FileChannel.open(path);
            //缓冲区
            ByteBuffer byteBuffer1 = ByteBuffer.allocate((int) (fileChannel1.size()+1));
            //读到通道
            fileChannel1.read(byteBuffer1);
            byteBuffer1.flip();
            Charset utf8 = Charset.forName("UTF-8");
            CharBuffer charBuffer = utf8.decode(byteBuffer1);
            System.out.println(charBuffer.toString());
            charBuffer.clear();

            fileChannel1.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void enumtest(){
        System.out.println(SexEnum.FEMALE);
        System.out.println(SexEnum.getSexEnumById(1));
    }

    @Test
    public void dir(){
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }

}
