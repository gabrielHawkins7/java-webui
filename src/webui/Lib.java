import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

interface Lib extends Library {

  Lib INSTANCE = (Lib) Native.load("debug/lin-x64/webui-2.so", Lib.class);

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
