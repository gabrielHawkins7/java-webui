import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import com.sun.jna.NativeLong;

public class Main {

  static Lib lib = Lib.INSTANCE;

  public static void main(String[] args) throws IOException, URISyntaxException {

    int win = lib.webui_new_window();

    WebuiCallbacks.EventCallback handle_data = (e) -> {
      String value = lib.webui_get_string(e);
      System.out.println("Value From JS! " + value);
      lib.webui_run(e.window, String.format("updateLog('JAVA: Received [ %s ] from JS')", value));
    };

    lib.webui_bind(win, "handleData", handle_data);

    WebuiCallbacks.EventCallback getRandomNumber = (e) -> {
      lib.webui_return_int(e, new NativeLong(100));
    };

    WebuiCallbacks.EventCallback closeWin = (e) -> {
      lib.webui_exit();
    };

    lib.webui_bind(win, "getRandomNumber", getRandomNumber);

    InputStream rawhtml = Main.class.getResourceAsStream("/index.html");
    String html = new String(rawhtml.readAllBytes(), StandardCharsets.UTF_8);

    lib.webui_show(win, html);

    lib.webui_bind(win, "closeWin", closeWin);

    lib.webui_set_size(win, 800, 600);
    lib.webui_wait();

  }
}
