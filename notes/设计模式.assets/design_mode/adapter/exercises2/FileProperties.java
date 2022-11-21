package design_mode.adapter.exercises2;

import java.io.*;
import java.util.Properties;

/**
 * @author yujh
 * @date 2022/11/10 16:57
 */
public class FileProperties implements FileIO {
    private Properties properties;

    FileProperties(){
        this.properties = new Properties();
    }
    @Override
    public void readFromFile(String fileName) throws IOException {
        FileReader reader = new FileReader("src/design_mode/adapter/exercises2/file.txt");
        properties.load(reader);
    }

    @Override
    public void writeToFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter("src/design_mode/adapter/exercises2/newfile.txt");
        properties.store(writer,"");

    }

    @Override
    public void setValue(String key, String value) {
        properties.setProperty(key,value);
    }

    @Override
    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
