import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

interface Webui extends Library {
  Webui INSTANCE = (Webui) Native.load("webui-2.dll", Webui.class);

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
    Webview// 13. WebView (Non-web-browser)

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

  /**
   * Maps the C struct `webui_event_t`. This structure holds information about an
   * event.
   */
  @Structure.FieldOrder({ "window", "event_type", "element", "event_number", "bind_id", "client_id", "connection_id",
      "cookies" })
  class WebUIEventT extends Structure {
    public int window; // The window object number
    public int event_type; // Event type
    public Pointer element; // HTML element ID
    public int event_number; // Internal WebUI event number
    public int bind_id; // Bind ID
    public int client_id; // Client's unique ID
    public int connection_id; // Client's connection ID
    public Pointer cookies; // Client's full cookies

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

  // -- Definitions ---------------------
  int webui_new_window();

  boolean webui_show(int window, String content);

  void webui_wait();

  void webui_minimize(int window);

  void webui_maximize(int window);

  void webui_close(int window);

  void webui_set_size(int window, int width, int height);

  void webui_set_frameless(int window, boolean status);

  void webui_set_transparent(int window, boolean status);

  void webui_set_resizable(int window, boolean status);

  void webui_set_center(int window);

  void webui_show_wv(int window, String content);

  int webui_bind(int window, String element, EventCallback func);

  void webui_set_context(int window, String element, Pointer context);

  Pointer webui_get_context(WebUIEventT e);

  int webui_get_best_browser(int window);

  boolean webui_show_client(WebUIEventT e, String content);

  boolean webui_show_browser(int window, String content, int broswer);

  String webui_start_server(int window, String content);

  void webui_set_kiosk(int window, boolean status);

  void webui_set_custom_parameters(int window, String[] params);

  void webui_set_high_contrast(int window, boolean status);

  boolean webui_is_high_contrast();

  boolean webui_browser_exist(int browser);

  void webui_close_client(WebUIEventT e);

  void webui_destroy(int window);

  void webui_exit();

  boolean webui_set_root_folder(int window, String path);

  void webui_set_broswer_folder(String path);

  boolean webui_set_default_root_folder(String path);

  void webui_set_file_handler(int window, FileHandler handler);

  void webui_set_file_handler_window(int window, FileWindowHandler handler);

  void webui_interface_set_response_file_handler(int window, Pointer response, int length);

  boolean webui_is_shown(int window);

  void webui_set_timeout(int second);

  void webui_set_icon(int window, String icon, String icon_type);

  Pointer webui_encode(String str);

  Pointer webui_decode(String str);

  void webui_free(Pointer ptr);

  Pointer webui_malloc(int size);

  void webui_memcpy(Pointer dest, Pointer src, int count);

  void webui_send_raw(int window, String function, Pointer raw, int size);

  void webui_send_raw_client(WebUIEventT e, String function, Pointer raw, int size);

  void webui_set_hide(int window, boolean status);

  void webui_set_minimum_size(int window, int width, int height);

  void webui_set_position(int window, int x, int y);

}
