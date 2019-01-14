//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.MapFile.Reader;
import org.apache.hadoop.io.MapFile.Writer;
import org.junit.Test;

public class TestMapFile {
    public TestMapFile() {
    }

    @Test
    public void save() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        FileSystem fs = FileSystem.get(conf);
        new Path("d:/seq/1.seq");
        Writer writer = new Writer(conf, fs, "d:/map", IntWritable.class, Text.class);

        //此处会连续写2次，不会覆盖原来的key
        for(int i = 0; i < 100; ++i) {
            writer.append(new IntWritable(i), new Text("tom" + i));
            writer.append(new IntWritable(i), new Text("tom" + i));
        }

        writer.close();
    }

    @Test
    public void read4() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "file:///");
        FileSystem fs = FileSystem.get(conf);
        new Path("d:/seq/1.seq");
        Reader reader = new Reader(fs, "d:/map", conf);
        IntWritable key = new IntWritable();
        Text value = new Text();

        while(reader.next(key, value)) {
            System.out.println(key.get() + ":" + value.toString());
        }

        reader.close();
    }
}
