import com.sun.jna.platform.EnumUtils;

public class Webui {

  private Lib lib;
  private int window;

  void bind() {
    throw new Error("Not implemented!");
  }

  boolean show(String html) {
    return lib.webui_show(window, html);
  }

  boolean show_browser(String html, WebuiConst.browser browser) {
    return lib.webui_show_browser(window, html, EnumUtils.toInteger(browser));
  }

  void set_kiosk(boolean status) {
    lib.webui_set_kiosk(window, status);
  }
}
