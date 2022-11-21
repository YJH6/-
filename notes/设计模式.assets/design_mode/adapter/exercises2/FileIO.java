package design_mode.adapter.exercises2;

import java.io.IOException;

/**
 * @author yujh
 * @date 2022/11/10 16:55
 */
public interface FileIO {
    public void readFromFile(String fileName) throws IOException;
    public void writeToFile(String fileName) throws IOException;
    public void setValue(String key, String value);
    public String getValue(String key);
}
