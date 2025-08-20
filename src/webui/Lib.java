import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

interface Lib extends Library {

  Lib INSTANCE = (Lib) Native.load("debug/lin-x64/webui-2.so", Lib.class);

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

  void webui_set_profile(int window, String name, String path);

  void webui_set_proxy(int window, String proxy_server);

  String webui_get_url(int window);

  void webui_open_url(String url);

  void webui_set_public(int window, boolean status);

  void webui_navigate(int window, String url);

  void webui_navigate_client(WebUIEventT e, String url);

  void webui_clean();

  void webui_delete_all_profiles();

  void webui_delete_profile(int window);

  int webui_get_parent_process_id(int window);

  int webui_get_child_process_id(int window);

  int webui_get_port(int window);

  boolean webui_set_port(int window, int port);

  int webui_get_free_port();

  void webui_set_config(webui_config option, boolean status);

  void webui_set_event_blocking(int window, boolean status);

  String webui_get_mime_type(String file);

  boolean webui_set_tls_certificate(String certificate_pem, String private_key_pem);

  void webui_run(int window, String script);

  void webui_run_client(WebUIEventT e, String script);

  // TODO: Not working? buffer issue
  void webui_script(int window, String script, int timeout, String[] buffer, int buffer_length);

  void webui_script_client(WebUIEventT e, String script, int timeout, String[] buffer, int buffer_length);

  void webui_set_runtime(int window, webui_runtime runtime);

  int webui_get_count(WebUIEventT e);

  NativeLong webui_get_int_at(WebUIEventT e, int index);

  NativeLong webui_get_int(WebUIEventT e);

  double webui_get_float_at(WebUIEventT e, int index);

  double webui_get_float(WebUIEventT e);

  String webui_get_string_at(WebUIEventT e, int index);

  String webui_get_string(WebUIEventT e);

  boolean webui_get_bool_at(WebUIEventT e, int index);

  boolean webui_get_bool(WebUIEventT e);

  int webui_get_size_at(WebUIEventT e, int index);

  int webui_get_size(WebUIEventT e);

  void webui_return_int(WebUIEventT e, NativeLong n);

  void webui_return_float(WebUIEventT e, double f);

  void webui_return_string(WebUIEventT e, String s);

  void webui_return_bool(WebUIEventT e, boolean b);

  int webui_get_last_error_number();

  String webui_get_last_error_message();

  // Wrappers Interface
  int webui_interface_bind(int window, String element, InterfaceCallback func);

  void webui_interface_set_response(int window, int event_number, String response);

  boolean webui_interface_is_app_runnint();

  int webui_interface_get_window_id(int window);

  String webui_interface_get_string_at(int window, int event_number, int index);

  NativeLong webui_interface_get_int_at(int window, int event_number, int index);

  double webui_interface_get_float_at(int window, int event_number, int index);

  boolean webui_interface_get_bool_at(int window, int event_number, int index);

  int webui_interface_get_size_at(int window, int event_number, int index);

  boolean webui_interface_show_client(int window, int event_number, String content);

  void webui_interface_close_client(int window, int event_number);

  void webui_interface_send_raw_client(int window, int event_number, String function, Pointer raw, int size);

  void webui_interface_navigate_client(int window, int event_number, String url);

  void webui_interface_run_client(int window, int event_number, String script);

  boolean webui_interface_script_client(int window, int event_number, String script, int timeour, String[] buffer,
      int buffer_length);

}
