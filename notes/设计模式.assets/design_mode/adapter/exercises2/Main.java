package design_mode.adapter.exercises2;

import java.io.IOException;

/**
 * @author yujh
 * @date 2022/11/10 16:57
 */
public class Main {
    public static void main(String[] args) {
        FileIO f = new FileProperties();
        try {
            f.readFromFile("file.txt");
//            f.setValue("year","2001");
            f.setValue("month","4");
            f.setValue("day","21");
            f.writeToFile("newfile.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
