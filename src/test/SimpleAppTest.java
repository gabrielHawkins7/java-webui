import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleAppTest {
  @Test
  @DisplayName("A simple assertion to verify the test runner is working.")
  void simpleAssertion() {
    assertTrue(true);
  }

  @Test
  @DisplayName("Test running a simple application")
  void simpleApplication() throws IOException, InterruptedException {

    InputStream rawhtml = Main.class.getResourceAsStream("/index.html");
    String html = new String(rawhtml.readAllBytes(), StandardCharsets.UTF_8);

    Lib lib = Lib.INSTANCE;

    int win = lib.webui_new_window();

    WebuiCallbacks.EventCallback handle_data = (e) -> {
      String value = lib.webui_get_string(e);
      System.out.println("Value From JS! " + value);
      lib.webui_run(e.window, String.format("updateLog('JAVA: Received [ %s ] from JS')", value));
    };
    lib.webui_bind(win, "handleData", handle_data);
    lib.webui_show(win, html);

    lib.webui_set_size(win, 800, 600);
    lib.webui_wait();

  }
}
