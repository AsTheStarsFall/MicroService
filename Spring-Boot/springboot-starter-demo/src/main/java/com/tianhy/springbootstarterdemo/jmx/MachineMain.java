package com.tianhy.springbootstarterdemo.jmx;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/3 15:53
 **/
public class MachineMain {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        //创建一个服务
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.tianhy.springbootstarterdemo.jmx.Machine:type=machine");
        MachineMBean machineBean = new Machine();
        platformMBeanServer.registerMBean(machineBean,objectName);
        System.in.read();


    }
}
