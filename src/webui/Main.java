import java.io.IOException;
import com.sun.jna.NativeLong;

public class Main {

  static Webui lib = Webui.INSTANCE;

  public static void main(String[] args) throws IOException {
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

    lib.webui_show(win, "index.html");

    lib.webui_bind(win, "closeWin", closeWin);

    lib.webui_set_size(win, 800, 600);
    lib.webui_wait();
  }
}
