package design_mode.builder.exercises1;

/**
 * 使用纯文本编写文档，并以String返回结果
 * @author yujh
 * @date 2022/11/15 20:23
 */
public class TextBuilder implements Builder {
    //文档内容保存
    private StringBuffer buffer = new StringBuffer();
    @Override
    public void makeTitle(String title) {
        buffer.append("=========================\n");
        buffer.append("[" + title + "]\n");
        buffer.append("\n");
    }

    @Override
    public void makeString(String str) {
        buffer.append('#' + str + "\n");
        buffer.append("\n");
    }

    @Override
    public void makeItems(String[] items) {
        for (String item : items) {
            buffer.append(" `"+ item + "\n");
        }
        buffer.append("\n");
    }

    @Override
    public void close() {
        buffer.append("=========================\n");
    }

    public String getResult() {
        return buffer.toString();
    }
}
