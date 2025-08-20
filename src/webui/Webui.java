import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.platform.EnumUtils;

public class Webui {

  private Lib lib = Lib.INSTANCE;
  private int window;

  public Webui() {
    this.window = lib.webui_new_window();
  }

  // -- C API Wrapper -------------------------------------------------------------

  void bind(String element, WebuiCallbacks.EventCallback func) {
    lib.webui_bind(window, element, func);
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

  void minimize() {
    lib.webui_minimize(window);
  }

  void maximize() {
    lib.webui_maximize(window);
  }

  void close() {
    lib.webui_close(window);
  }

  void destroy() {
    lib.webui_destroy(window);
  }

  void set_size(int width, int height) {
    lib.webui_set_size(window, width, height);
  }

  void set_position(int x, int y) {
    lib.webui_set_position(window, x, y);
  }

  void set_frameless(boolean status) {
    lib.webui_set_frameless(window, status);
  }

  void set_transparent(boolean status) {
    lib.webui_set_transparent(window, status);
  }

  void set_resizable(boolean status) {
    lib.webui_set_resizable(window, status);
  }

  void set_center() {
    lib.webui_set_center(window);
  }

  void show_wv(String content) {
    lib.webui_show_wv(window, content);
  }

  void set_context(String element, Pointer context) {
    lib.webui_set_context(window, element, context);
  }

  int get_best_browser() {
    return lib.webui_get_best_browser(window);
  }

  String start_server(String content) {
    return lib.webui_start_server(window, content);
  }

  void set_custom_parameters(String[] params) {
    lib.webui_set_custom_parameters(window, params);
  }

  void set_high_contrast(boolean status) {
    lib.webui_set_high_contrast(window, status);
  }

  boolean set_root_folder(String path) {
    return lib.webui_set_root_folder(window, path);
  }

  void set_file_handler(WebuiCallbacks.FileHandler handler) {
    lib.webui_set_file_handler(window, handler);
  }

  void set_file_handler_window(WebuiCallbacks.FileWindowHandler handler) {
    lib.webui_set_file_handler_window(window, handler);
  }

  boolean is_shown() {
    return lib.webui_is_shown(window);
  }

  void set_icon(String icon, String icon_type) {
    lib.webui_set_icon(window, icon, icon_type);
  }

  void send_raw(String function, Pointer raw, int size) {
    lib.webui_send_raw(window, function, raw, size);
  }

  void set_hide(boolean status) {
    lib.webui_set_hide(window, status);
  }

  void set_minimum_size(int width, int height) {
    lib.webui_set_minimum_size(window, width, height);
  }

  void set_profile(String name, String path) {
    lib.webui_set_profile(window, name, path);
  }

  void set_proxy(String proxy_server) {
    lib.webui_set_proxy(window, proxy_server);
  }

  String get_url() {
    return lib.webui_get_url(window);
  }

  void set_public(boolean status) {
    lib.webui_set_public(window, status);
  }

  void navigate(String url) {
    lib.webui_navigate(window, url);
  }

  void delete_profile() {
    lib.webui_delete_profile(window);
  }

  int get_parent_process_id() {
    return lib.webui_get_parent_process_id(window);
  }

  int get_child_process_id() {
    return lib.webui_get_child_process_id(window);
  }

  int get_port() {
    return lib.webui_get_port(window);
  }

  boolean set_port(int port) {
    return lib.webui_set_port(window, port);
  }

  void set_event_blocking(boolean status) {
    lib.webui_set_event_blocking(window, status);
  }

  void run(String script) {
    lib.webui_run(window, script);
  }

  void script(String script, int timeout, String[] buffer, int buffer_length) {
    lib.webui_script(window, script, timeout, buffer, buffer_length);
  }

  void set_runtime(WebuiConst.runtime runtime) {
    lib.webui_set_runtime(window, EnumUtils.toInteger(runtime));
  }

  // -- Static methods ------------------------------------------------------------

  public static void wait_() {
    Lib.INSTANCE.webui_wait();
  }

  public static Pointer get_context(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_context(e);
  }

  public static boolean show_client(WebuiCallbacks.WebUIEventT e, String content) {
    return Lib.INSTANCE.webui_show_client(e, content);
  }

  public static boolean is_high_contrast() {
    return Lib.INSTANCE.webui_is_high_contrast();
  }

  public static boolean browser_exist(WebuiConst.browser browser) {
    return Lib.INSTANCE.webui_browser_exist(EnumUtils.toInteger(browser));
  }

  public static void close_client(WebuiCallbacks.WebUIEventT e) {
    Lib.INSTANCE.webui_close_client(e);
  }

  public static void exit() {
    Lib.INSTANCE.webui_exit();
  }

  public static void set_broswer_folder(String path) {
    Lib.INSTANCE.webui_set_broswer_folder(path);
  }

  public static boolean set_default_root_folder(String path) {
    return Lib.INSTANCE.webui_set_default_root_folder(path);
  }

  public static void set_timeout(int second) {
    Lib.INSTANCE.webui_set_timeout(second);
  }

  public static Pointer encode(String str) {
    return Lib.INSTANCE.webui_encode(str);
  }

  public static Pointer decode(String str) {
    return Lib.INSTANCE.webui_decode(str);
  }

  public static void free(Pointer ptr) {
    Lib.INSTANCE.webui_free(ptr);
  }

  public static Pointer malloc(int size) {
    return Lib.INSTANCE.webui_malloc(size);
  }

  public static void memcpy(Pointer dest, Pointer src, int count) {
    Lib.INSTANCE.webui_memcpy(dest, src, count);
  }

  public static void send_raw_client(WebuiCallbacks.WebUIEventT e, String function, Pointer raw, int size) {
    Lib.INSTANCE.webui_send_raw_client(e, function, raw, size);
  }

  public static void open_url(String url) {
    Lib.INSTANCE.webui_open_url(url);
  }

  public static void navigate_client(WebuiCallbacks.WebUIEventT e, String url) {
    Lib.INSTANCE.webui_navigate_client(e, url);
  }

  public static void clean() {
    Lib.INSTANCE.webui_clean();
  }

  public static void delete_all_profiles() {
    Lib.INSTANCE.webui_delete_all_profiles();
  }

  public static int get_free_port() {
    return Lib.INSTANCE.webui_get_free_port();
  }

  public static void set_config(WebuiConst.config option, boolean status) {
    Lib.INSTANCE.webui_set_config(EnumUtils.toInteger(option), status);
  }

  public static String get_mime_type(String file) {
    return Lib.INSTANCE.webui_get_mime_type(file);
  }

  public static boolean set_tls_certificate(String certificate_pem, String private_key_pem) {
    return Lib.INSTANCE.webui_set_tls_certificate(certificate_pem, private_key_pem);
  }

  public static void run_client(WebuiCallbacks.WebUIEventT e, String script) {
    Lib.INSTANCE.webui_run_client(e, script);
  }

  public static void script_client(WebuiCallbacks.WebUIEventT e, String script, int timeout, String[] buffer,
      int buffer_length) {
    Lib.INSTANCE.webui_script_client(e, script, timeout, buffer, buffer_length);
  }

  public static int get_count(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_count(e);
  }

  public static NativeLong get_int_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_int_at(e, index);
  }

  public static NativeLong get_int(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_int(e);
  }

  public static double get_float_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_float_at(e, index);
  }

  public static double get_float(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_float(e);
  }

  public static String get_string_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_string_at(e, index);
  }

  public static String get_string(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_string(e);
  }

  public static boolean get_bool_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_bool_at(e, index);
  }

  public static boolean get_bool(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_bool(e);
  }

  public static int get_size_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_size_at(e, index);
  }

  public static int get_size(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_size(e);
  }

  public static void return_int(WebuiCallbacks.WebUIEventT e, NativeLong n) {
    Lib.INSTANCE.webui_return_int(e, n);
  }

  public static void return_float(WebuiCallbacks.WebUIEventT e, double f) {
    Lib.INSTANCE.webui_return_float(e, f);
  }

  public static void return_string(WebuiCallbacks.WebUIEventT e, String s) {
    Lib.INSTANCE.webui_return_string(e, s);
  }

  public static void return_bool(WebuiCallbacks.WebUIEventT e, boolean b) {
    Lib.INSTANCE.webui_return_bool(e, b);
  }

  public static int get_last_error_number() {
    return Lib.INSTANCE.webui_get_last_error_number();
  }

  public static String get_last_error_message() {
    return Lib.INSTANCE.webui_get_last_error_message();
  }

  public static boolean interface_is_app_running() {
    return Lib.INSTANCE.webui_interface_is_app_runnint();
  }
}
