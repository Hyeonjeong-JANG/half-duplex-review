import org.junit.jupiter.api.Test;

public class ParseTest {
    @Test
    public void parse_test() {
        String url = "/save.do";

        String t1 = url.replace("/", "");
        System.out.println(t1);

        String[] t2 = t1.split("\\.");
        System.out.println(t2);

    }
}
