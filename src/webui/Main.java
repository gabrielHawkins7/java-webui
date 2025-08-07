import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.jna.Pointer;
import com.sun.jna.platform.EnumUtils;

public class Main {

  static Webui lib = Webui.INSTANCE;
  static ArrayList<String> privateArr = new ArrayList<>();
  static ArrayList<String> publicArr = new ArrayList<>();
  int users_count = 0;
  int tab_count = 0;

  public static void main(String[] args) throws IOException {
    int win = lib.webui_new_window();
    lib.webui_show(win, "index.html");
    lib.webui_wait();
  }
}
