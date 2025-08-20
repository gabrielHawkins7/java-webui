import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

  static Lib lib = Lib.INSTANCE;

  public static void main(String[] args) throws IOException, URISyntaxException {
    int win = lib.webui_new_window();

    lib.webui_show(win, "<html><head><script src=\"webui.js\"></script></head> Hello World ! </html>");

    lib.webui_wait();
  }
}
