package com.tianhy.springbootmybatis;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/17
 **/
@Slf4j
public class ObjectBinary {


    /**
     * 对象转二进制
     *
     * @param obj
     * @return
     */
    public static byte[] object2Binary(Object obj) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            //写对象转为二进制数据
            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (IOException e) {
            log.error("对象转为二进制失败");
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 二进制转对象
     *
     * @param bytes
     * @return
     */
    public static Object binary2Object(byte[] bytes) {
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            log.error("二进制转对象失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
