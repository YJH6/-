package design_mode.singleton.exercises2;

/**
 * @author yujh
 * @date 2022/11/12 15:40
 */
public class Triple {
    private static Triple[] triples = new Triple[3];
    private int i = 0;
    private Triple() {
        if(i<3){
            triples[i++] = new Triple();
        }
    }

    public static Triple getInstance(int id) {
        if (id >3) {
            return null;
        }
        return triples[id - 1];
    }
}
