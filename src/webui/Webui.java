import java.util.ArrayList;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.platform.EnumUtils;

public class Webui {
  // -- Enums ---------------------------
  enum BROSWER {
    NoBrowser, // 0. No web browser
    AnyBrowser, // 1. Default recommended web browser
    Chrome, // 2. Google Chrome
    Firefox, // 3. Mozilla Firefox
    Edge, // 4. Microsoft Edge
    Safari, // 5. Apple Safari
    Chromium, // 6. The Chromium Project
    Opera, // 7. Opera Browser
    Brave, // 8. The Brave Browser
    Vivaldi, // 9. The Vivaldi Browser
    Epic, // 10. The Epic Browser
    Yandex, // 11. The Yandex Browser
    ChromiumBased, // 12. Any Chromium based browser
    Webview// 13. WebView (Non-web-browser)
  };

  private Lib lib;
  private int window;

  void bind() {
    throw new Error("Not implemented!");
  }

  boolean show(String html) {
    return lib.webui_show(window, html);
  }

  boolean show_browser(String html, BROSWER browser) {
    return lib.webui_show_browser(window, html, EnumUtils.toInteger(browser));
  }

  void set_kiosk(boolean status) {
    lib.webui_set_kiosk(window, status);
  }
}
