/**
 * 准备天气数据
 */

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


//生成做实验用的数据
public class PrepareTempData {
    @Test
    public void makeData() throws IOException {
        FileWriter fw = new FileWriter("d:/mr/temp.txt");
        for(int i = 0 ; i < 6000 ; i ++){
            int year = 1970 + new Random().nextInt(100) ;
            int temp = -30 + new Random().nextInt(100);
            fw.write("" + year + " " + temp + "\r\n");
        }
        fw.close();
    }
}
