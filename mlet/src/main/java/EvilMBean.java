import java.io.IOException;

public interface EvilMBean {
    void runCommand(String cmd) throws IOException;
}