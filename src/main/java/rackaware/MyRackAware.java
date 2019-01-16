package rackaware;

import org.apache.hadoop.net.DNSToSwitchMapping;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *机架感知类
 */
public class MyRackAware implements DNSToSwitchMapping {


    //s202--192.168.231.202        /rack1/202
    //s203--192.168.231.203        /rack1/203
    //        -----------------------------------------------------------模拟一个交换机
    //s204--192.168.231.204        /rack2/204
    //s205--192.168.231.205        /rack2/205
    //        -----------------------------------------------------------模拟一个交换机
    public List<String> resolve(List<String> names) {
        List<String> list =  new ArrayList<String>();
        try {
            //输出信息 放在一个文件里面  相当于一个日志
            FileWriter fw = new FileWriter("/home/centos/rackaware.txt",true);
            for(String str : names){
                //输出原来的信息,解析ip地址或者主机名
                fw.write(str + "\r\n");
                //
                if(str.startsWith("192")){
                    //192.168.231.202
                    String ip = str.substring(str.lastIndexOf(".") + 1);
                    if(Integer.parseInt(ip) <= 203) {
                        list.add("/rack1/" + ip);
                    }
                    else{
                        list.add("/rack2/" + ip);
                    }
                }
                //主机名开头
                else if(str.startsWith("s")){
                    String ip = str.substring(1);
                    if (Integer.parseInt(ip) <= 203) {
                        list.add("/rack1/" + ip);
                    } else {
                        list.add("/rack2/" + ip);
                    }
                }
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void reloadCachedMappings() {

    }

    public void reloadCachedMappings(List<String> names) {
    }
}