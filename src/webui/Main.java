import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.jna.Pointer;
import com.sun.jna.platform.EnumUtils;

public class Main {

  static Webui lib = Webui.INSTANCE;

  public static void main(String[] args) throws IOException {
    int win = lib.webui_new_window();
    lib.webui_show(win, "index.html");
    lib.webui_set_size(win, 800, 600);
    lib.webui_wait();
  }
}
