import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

public interface Webui extends Library {
  Webui INSTANCE = (Webui) Native.load("webui-2.dll", Webui.class);

  int webui_new_window();

  void webui_show(int windoP, String html);

  void webui_wait();

  // -- Enums ---------------------------
  enum webui_browser {
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
    Webview, // 13. WebView (Non-web-browser)
  };

  enum webui_runtime {
    None, // 0. Prevent WebUI from using any runtime for .js and .ts files
    Deno, // 1. Use Deno runtime for .js and .ts files
    NodeJS, // 2. Use Nodejs runtime for .js files
    Bun, // 3. Use Bun runtime for .js and .ts files
  };

  enum webui_event {
    WEBUI_EVENT_DISCONNECTED, // 0. Window disconnection event
    WEBUI_EVENT_CONNECTED, // 1. Window connection event
    WEBUI_EVENT_MOUSE_CLICK, // 2. Mouse click event
    WEBUI_EVENT_NAVIGATION, // 3. Window navigation event
    WEBUI_EVENT_CALLBACK, // 4. Function call event
  };

  enum webui_config {
    // Control if `webui_show()`, `webui_show_browser()` and
    // `webui_show_wv()` should wait for the window to connect
    // before returns or not.
    //
    // Default: True
    show_wait_connection,
    // Control if WebUI should block and process the UI events
    // one a time in a single thread `True`, or process every
    // event in a new non-blocking thread `False`. This updates
    // all windows. You can use `webui_set_event_blocking()` for
    // a specific single window update.
    //
    // Default: False
    ui_event_blocking,
    // Automatically refresh the window UI when any file in the
    // root folder gets changed.
    //
    // Default: False
    folder_monitor,
    // Allow multiple clients to connect to the same window,
    // This is helpful for web apps (non-desktop software),
    // Please see the documentation for more details.
    //
    // Default: False
    multi_client,
    // Allow or prevent WebUI from adding `webui_auth` cookies.
    // WebUI uses these cookies to identify clients and block
    // unauthorized access to the window content using a URL.
    // Please keep this option to `True` if you want only a single
    // client to access the window content.
    //
    // Default: True
    use_cookies,
    // If the backend uses asynchronous operations, set this
    // option to `True`. This will make webui wait until the
    // backend sets a response using `webui_return_x()`.
    asynchronous_response
  };

  class WebUIEventT extends Structure {
    public int window; // The window object number
    public int event_type; // Event type
    public String element; // HTML element ID
    public int event_number; // Internal WebUI event number
    public int bind_id; // Bind ID
    public int client_id; // Client's unique ID
    public int connection_id; // Client's connection ID
    public String cookies; // Client's full cookies

    public WebUIEventT() {
    }

    public WebUIEventT(Pointer p) {
      super(p);
      read();
    }
  }

  // -- Callbacks --------------------------------------------------------------

  /**
   * Callback interface for events bound using `webui_bind`.
   */
  interface EventCallback extends Callback {
    void invoke(WebUIEventT e);
  }

  /**
   * Callback interface for the file handler.
   */
  interface FileHandler extends Callback {
    Pointer invoke(String filename, IntByReference length);
  }

  /**
   * Callback interface for the file handler with window context.
   */
  interface FileWindowHandler extends Callback {
    Pointer invoke(int window, String filename, IntByReference length);
  }

  /**
   * Callback interface for wrapper/interface bindings.
   */
  interface InterfaceCallback extends Callback {
    void invoke(int window, int event_type, String element, int event_number, int bind_id);
  }

}
