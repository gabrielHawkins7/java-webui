package Webui;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.sun.jna.NativeLong;

public class Test {
  public static void TestWV() {

    Webui win = new Webui();

    WebuiCallbacks.EventCallback handle_data = (e) -> {
      String value = Webui.get_string(e);
      System.out.println("Value From JS! " + value);
      win.run(String.format("updateLog('JAVA: Received [ %s ] from JS')", value));
    };

    win.bind("handleData", handle_data);

    WebuiCallbacks.EventCallback getRandomNumber = (e) -> {
      Webui.return_int(e, new NativeLong(100));
    };

    WebuiCallbacks.EventCallback closeWin = (e) -> {
      Webui.exit();
    };

    win.bind("getRandomNumber", getRandomNumber);

    InputStream rawhtml = Test.class.getResourceAsStream("/index.html");
    String html = "";
    try {
      html = new String(rawhtml.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    win.show_browser(html, 12);

    win.bind("closeWin", closeWin);

    win.set_size(800, 600);
    win.waitForWindowClose();
  }

}
