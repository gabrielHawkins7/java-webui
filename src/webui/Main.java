import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.jna.NativeLong;

public class Main {

  static Webui lib = Webui.INSTANCE;

  public static void main(String[] args) throws IOException, URISyntaxException {
    int win = lib.webui_new_window();

    Webui.EventCallback handle_data = (e) -> {
      String value = lib.webui_get_string(e);
      System.out.println("Value From JS! " + value);
      lib.webui_run(e.window, String.format("updateLog('JAVA: Received [ %s ] from JS')", value));
    };

    lib.webui_bind(win, "handleData", handle_data);

    Webui.EventCallback getRandomNumber = (e) -> {
      lib.webui_return_int(e, new NativeLong(100));
    };

    Webui.EventCallback closeWin = (e) -> {
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
