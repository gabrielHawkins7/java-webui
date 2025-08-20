import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.platform.EnumUtils;

public class Webui {

  private static Lib lib = Lib.INSTANCE;
  private int window;

  /**
   * @brief Create a new WebUI window object.
   *
   * @example Webui myWindow = new Webui();
   */
  public Webui() {
    this.window = lib.webui_new_window();
  }

  // -- C API Wrapper
  // -------------------------------------------------------------

  /**
   * @brief Bind an HTML element and a JavaScript object with a backend function.
   *        Empty
   *        element name means all events.
   *
   * @param element The HTML element / JavaScript object
   * @param func    The callback function
   *
   * @example myWindow.bind("myFunction", myFunction);
   */
  void bind(String element, WebuiCallbacks.EventCallback func) {
    lib.webui_bind(window, element, func);
  }

  /**
   * @brief Show a window using embedded HTML, or a file. If the window is
   *        already
   *        open, it will be refreshed. This will refresh all windows in
   *        multi-client
   *        mode.
   *
   * @param html The HTML, URL, Or a local file
   *
   * @return Returns True if showing the window is successed.
   *
   * @example myWindow.show("<html>...</html>"); |
   *          myWindow.show("index.html"); | myWindow.show("http://...");
   */
  boolean show(String html) {
    return lib.webui_show(window, html);
  }

  /**
   * @brief Same as `show()`. But using a specific web browser.
   *
   * @param html    The HTML, Or a local file
   * @param browser The web browser to be used
   *
   * @return Returns True if showing the window is successed.
   *
   * @example myWindow.show_browser("<html>...</html>",
   *          WebuiConst.browser.Chrome);
   *          |
   *          myWindow.show("index.html", WebuiConst.browser.Firefox);
   */
  boolean show_browser(String html, WebuiConst.browser browser) {
    return lib.webui_show_browser(window, html, EnumUtils.toInteger(browser));
  }

  /**
   * @brief Set the window in Kiosk mode (Full screen).
   *
   * @param status True or False
   *
   * @example myWindow.set_kiosk(true);
   */
  void set_kiosk(boolean status) {
    lib.webui_set_kiosk(window, status);
  }

  void minimize() {
    lib.webui_minimize(window);
  }

  void maximize() {
    lib.webui_maximize(window);
  }

  /**
   * @brief Close a specific window only. The window object will still exist.
   *        All clients.
   *
   * @example myWindow.close();
   */
  void close() {
    lib.webui_close(window);
  }

  /**
   * @brief Close a specific window and free all memory resources.
   *
   * @example myWindow.destroy();
   */
  void destroy() {
    lib.webui_destroy(window);
  }

  /**
   * @brief Set the window size.
   *
   * @param width  The window width
   * @param height The window height
   *
   * @example myWindow.set_size(800, 600);
   */
  void set_size(int width, int height) {
    lib.webui_set_size(window, width, height);
  }

  /**
   * @brief Set the window position.
   *
   * @param x The window X
   * @param y The window Y
   *
   * @example myWindow.set_position(100, 100);
   */
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

  /**
   * @brief Show a WebView window using embedded HTML, or a file. If the window
   *        is already
   *        open, it will be refreshed. Note: Win32 need `WebView2Loader.dll`.
   *
   * @param content The HTML, URL, Or a local file
   *
   * @return Returns True if showing the WebView window is successed.
   *
   * @example myWindow.show_wv("<html>...</html>"); | myWindow.show_wv(
   *          "index.html"); | myWindow.show_wv("http://...");
   */
  void show_wv(String content) {
    lib.webui_show_wv(window, content);
  }

  /**
   * @brief Use this API after using `bind()` to add any user data to it
   *        that can be
   *        read later using `get_context()`.
   *
   * @param element The HTML element / JavaScript object
   * @param context Any user data
   *
   * @example
   *          myWindow.bind("myFunction", myFunction);
   * 
   *          myWindow.set_context("myFunction", myData);
   * 
   *          void myFunction(webui_event_t* e) {
   *          void* myData = webui.get_context(e);
   *          }
   */
  void set_context(String element, Pointer context) {
    lib.webui_set_context(window, element, context);
  }

  /**
   * @brief Get the recommended web browser ID to use. If you
   *        are already using one, this function will return the same ID.
   * 
   * @return Returns a web browser ID.
   * 
   * @example int browserID = myWindow.get_best_browser();
   */
  int get_best_browser() {
    return lib.webui_get_best_browser(window);
  }

  /**
   * @brief Same as `show()`. But start only the web server and return the
   *        URL.
   *        No window will be shown.
   *
   * @param content The HTML, Or a local file
   *
   * @return Returns the url of this window server.
   *
   * @example String url = myWindow.start_server("/full/root/path");
   */
  String start_server(String content) {
    return lib.webui_start_server(window, content);
  }

  /**
   * @brief Add a user-defined web browser's CLI parameters.
   *
   * @param params Command line parameters
   *
   * @example myWindow.set_custom_parameters(new String[]
   *          {"--remote-debugging-port=9222"});
   */
  void set_custom_parameters(String[] params) {
    lib.webui_set_custom_parameters(window, params);
  }

  /**
   * @brief Set the window with high-contrast support. Useful when you want to
   *        build a better high-contrast theme with CSS.
   *
   * @param status True or False
   *
   * @example myWindow.set_high_contrast(true);
   */
  void set_high_contrast(boolean status) {
    lib.webui_set_high_contrast(window, status);
  }

  /**
   * @brief Set the web-server root folder path for a specific window.
   *
   * @param path The local folder full path
   *
   * @example myWindow.set_root_folder("/home/Foo/Bar/");
   */
  boolean set_root_folder(String path) {
    return lib.webui_set_root_folder(window, path);
  }

  /**
   * @brief Set a custom handler to serve files. This custom handler should
   *        return full HTTP header and body.
   *        This deactivates any previous handler set with
   *        `set_file_handler_window`
   *
   * @param handler The handler function: `void myHandler(const char* filename,
   *                int* length)`
   *
   * @example myWindow.set_file_handler(myHandlerFunction);
   */
  void set_file_handler(WebuiCallbacks.FileHandler handler) {
    lib.webui_set_file_handler(window, handler);
  }

  /**
   * @brief Set a custom handler to serve files. This custom handler should
   *        return full HTTP header and body.
   *        This deactivates any previous handler set with `set_file_handler`
   *
   * @param handler The handler function: `void myHandler(size_t window, const
   *                char* filename,
   *                int* length)`
   *
   * @example myWindow.set_file_handler_window(myHandlerFunction);
   */
  void set_file_handler_window(WebuiCallbacks.FileWindowHandler handler) {
    lib.webui_set_file_handler_window(window, handler);
  }

  /**
   * @brief Check if the specified window is still running.
   *
   * @example myWindow.is_shown();
   */
  boolean is_shown() {
    return lib.webui_is_shown(window);
  }

  /**
   * @brief Set the default embedded HTML favicon.
   *
   * @param icon      The icon as string: `<svg>...</svg>`
   * @param icon_type The icon type: `image/svg+xml`
   *
   * @example myWindow.set_icon("<svg>...</svg>", "image/svg+xml");
   */
  void set_icon(String icon, String icon_type) {
    lib.webui_set_icon(window, icon, icon_type);
  }

  /**
   * @brief Safely send raw data to the UI. All clients.
   *
   * @param function The JavaScript function to receive raw data: `function
   *                 myFunc(myData){}`
   * @param raw      The raw data buffer
   * @param size     The raw data size in bytes
   *
   * @example myWindow.send_raw("myJavaScriptFunc", myBuffer, 64);
   */
  void send_raw(String function, Pointer raw, int size) {
    lib.webui_send_raw(window, function, raw, size);
  }

  /**
   * @brief Set a window in hidden mode. Should be called before `show()`.
   *
   * @param status The status: True or False
   *
   * @example myWindow.set_hide(true);
   */
  void set_hide(boolean status) {
    lib.webui_set_hide(window, status);
  }

  /**
   * @brief Set the window minimum size.
   *
   * @param width  The window width
   * @param height The window height
   *
   * @example myWindow.set_minimum_size(800, 600);
   */
  void set_minimum_size(int width, int height) {
    lib.webui_set_minimum_size(window, width, height);
  }

  /**
   * @brief Set the web browser profile to use. An empty `name` and `path` means
   *        the default user profile. Need to be called before `show()`.
   *
   * @param name The web browser profile name
   * @param path The web browser profile full path
   *
   * @example myWindow.set_profile("Bar", "/Home/Foo/Bar"); |
   *          myWindow.set_profile("", "");
   */
  void set_profile(String name, String path) {
    lib.webui_set_profile(window, name, path);
  }

  /**
   * @brief Set the web browser proxy server to use. Need to be called before
   *        `show()`.
   *
   * @param proxy_server The web browser proxy_server
   *
   * @example myWindow.set_proxy("http://127.0.0.1:8888");
   */
  void set_proxy(String proxy_server) {
    lib.webui_set_proxy(window, proxy_server);
  }

  /**
   * @brief Get current URL of a running window.
   *
   * @return Returns the full URL string
   *
   * @example String url = myWindow.get_url();
   */
  String get_url() {
    return lib.webui_get_url(window);
  }

  /**
   * @brief Allow a specific window address to be accessible from a public
   *        network.
   *
   * @param status True or False
   *
   * @example myWindow.set_public(true);
   */
  void set_public(boolean status) {
    lib.webui_set_public(window, status);
  }

  /**
   * @brief Navigate to a specific URL. All clients.
   *
   * @param url Full HTTP URL
   *
   * @example myWindow.navigate("http://domain.com");
   */
  void navigate(String url) {
    lib.webui_navigate(window, url);
  }

  /**
   * @brief Delete a specific window web-browser local folder profile.
   *
   * @example
   *          Webui.wait_();
   *          myWindow.delete_profile();
   *          Webui.clean();
   *
   * @note This can break functionality of other windows if using the same
   *       web-browser.
   */
  void delete_profile() {
    lib.webui_delete_profile(window);
  }

  /**
   * @brief Get the ID of the parent process (The web browser may re-create
   *        another new process).
   *
   * @return Returns the the parent process id as integer
   *
   * @example int id = myWindow.get_parent_process_id();
   */
  int get_parent_process_id() {
    return lib.webui_get_parent_process_id(window);
  }

  /**
   * @brief Get the ID of the last child process.
   *
   * @return Returns the the child process id as integer
   *
   * @example int id = myWindow.get_child_process_id();
   */
  int get_child_process_id() {
    return lib.webui_get_child_process_id(window);
  }

  /**
   * @brief Get the network port of a running window.
   *        This can be useful to determine the HTTP link of `webui.js`
   *
   * @return Returns the network port of the window
   *
   * @example int port = myWindow.get_port();
   */
  int get_port() {
    return lib.webui_get_port(window);
  }

  /**
   * @brief Set a custom web-server/websocket network port to be used by WebUI.
   *        This can be useful to determine the HTTP link of `webui.js` in case
   *        you are trying to use WebUI with an external web-server like NGNIX.
   *
   * @param port The web-server network port WebUI should use
   *
   * @return Returns True if the port is free and usable by WebUI
   *
   * @example boolean ret = myWindow.set_port(8080);
   */
  boolean set_port(int port) {
    return lib.webui_set_port(window, port);
  }

  /**
   * @brief Control if UI events comming from this window should be processed
   *        one a time in a single blocking thread `True`, or process every event
   *        in
   *        a new non-blocking thread `False`. This update single window. You can
   *        use
   *        `Webui.set_config(ui_event_blocking, ...)` to update all windows.
   *
   * @param status The blocking status `true` or `false`
   *
   * @example myWindow.set_event_blocking(true);
   */
  void set_event_blocking(boolean status) {
    lib.webui_set_event_blocking(window, status);
  }

  /**
   * @brief Run JavaScript without waiting for the response. All clients.
   *
   * @param script The JavaScript to be run
   *
   * @example myWindow.run("alert('Hello');");
   */
  void run(String script) {
    lib.webui_run(window, script);
  }

  /**
   * @brief Run JavaScript and get the response back. Work only in single client
   *        mode.
   *        Make sure your local buffer can hold the response.
   *
   * @param script        The JavaScript to be run
   * @param timeout       The execution timeout in seconds
   * @param buffer        The local buffer to hold the response
   * @param buffer_length The local buffer size
   *
   * @return Returns True if there is no execution error
   *
   * @example boolean err = myWindow.script("return 4 + 6;", 0, myBuffer,
   *          myBufferSize);
   */
  void script(String script, int timeout, String[] buffer, int buffer_length) {
    lib.webui_script(window, script, timeout, buffer, buffer_length);
  }

  /**
   * @brief Chose between Deno and Nodejs as runtime for .js and .ts files.
   *
   * @param runtime Deno | Bun | Nodejs | None
   *
   * @example myWindow.set_runtime(WebuiConst.runtime.Deno);
   */
  void set_runtime(WebuiConst.runtime runtime) {
    lib.webui_set_runtime(window, EnumUtils.toInteger(runtime));
  }

  // -- Static methods
  // ------------------------------------------------------------

  /**
   * @brief Wait until all opened windows get closed.
   *
   * @example Webui.waitForWindowClose();
   */
  public void waitForWindowClose() {
    lib.webui_wait();
  }

  /**
   * @brief Get user data that is set using `set_context()`.
   *
   * @param e The event struct
   * 
   * @return Returns user data pointer.
   *
   * @example
   *          myWindow.bind("myFunction", myFunction);
   * 
   *          myWindow.set_context("myFunction", myData);
   * 
   *          void myFunction(webui_event_t* e) {
   *          void* myData = Webui.get_context(e);
   *          }
   */
  public static Pointer get_context(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_context(e);
  }

  /**
   * @brief Show a window using embedded HTML, or a file. If the window is
   *        already
   *        open, it will be refreshed. Single client.
   *
   * @param e       The event struct
   * @param content The HTML, URL, Or a local file
   *
   * @return Returns True if showing the window is successed.
   *
   * @example Webui.show_client(e, "<html>...</html>"); |
   *          Webui.show_client(e, "index.html"); | Webui.show_client(e,
   *          "http://...");
   */
  public static boolean show_client(WebuiCallbacks.WebUIEventT e, String content) {
    return Lib.INSTANCE.webui_show_client(e, content);
  }

  /**
   * @brief Get OS high contrast preference.
   *
   * @return Returns True if OS is using high contrast theme
   *
   * @example boolean hc = Webui.is_high_contrast();
   */
  public static boolean is_high_contrast() {
    return Lib.INSTANCE.webui_is_high_contrast();
  }

  /**
   * @brief Check if a web browser is installed.
   *
   * @return Returns True if the specified browser is available
   *
   * @example boolean status = Webui.browser_exist(WebuiConst.browser.Chrome);
   */
  public static boolean browser_exist(WebuiConst.browser browser) {
    return Lib.INSTANCE.webui_browser_exist(EnumUtils.toInteger(browser));
  }

  /**
   * @brief Close a specific client.
   *
   * @param e The event struct
   *
   * @example Webui.close_client(e);
   */
  public static void close_client(WebuiCallbacks.WebUIEventT e) {
    Lib.INSTANCE.webui_close_client(e);
  }

  /**
   * @brief Close all open windows. `wait_()` will return (Break).
   *
   * @example Webui.exit();
   */
  public static void exit() {
    Lib.INSTANCE.webui_exit();
  }

  public static void set_broswer_folder(String path) {
    Lib.INSTANCE.webui_set_broswer_folder(path);
  }

  /**
   * @brief Set the web-server root folder path for all windows. Should be used
   *        before `show()`.
   *
   * @param path The local folder full path
   *
   * @example Webui.set_default_root_folder("/home/Foo/Bar/");
   */
  public static boolean set_default_root_folder(String path) {
    return Lib.INSTANCE.webui_set_default_root_folder(path);
  }

  /**
   * @brief Set the maximum time in seconds to wait for the window to connect.
   *        This effect `show()` and `wait()`. Value of `0` means wait forever.
   *
   * @param second The timeout in seconds
   *
   * @example Webui.set_timeout(30);
   */
  public static void set_timeout(int second) {
    Lib.INSTANCE.webui_set_timeout(second);
  }

  /**
   * @brief Encode text to Base64. The returned buffer need to be freed.
   *
   * @param str The string to encode (Should be null terminated)
   *
   * @return Returns the base64 encoded string
   * 
   * @example Pointer base64 = Webui.encode("Foo Bar");
   */
  public static Pointer encode(String str) {
    return Lib.INSTANCE.webui_encode(str);
  }

  /**
   * @brief Decode a Base64 encoded text. The returned buffer need to be freed.
   *
   * @param str The string to decode (Should be null terminated)
   * 
   * @return Returns the base64 decoded string
   *
   * @example Pointer str = Webui.decode("SGVsbG8=");
   */
  public static Pointer decode(String str) {
    return Lib.INSTANCE.webui_decode(str);
  }

  /**
   * @brief Safely free a buffer allocated by WebUI using `malloc()`.
   *
   * @param ptr The buffer to be freed
   *
   * @example Webui.free(myBuffer);
   */
  public static void free(Pointer ptr) {
    Lib.INSTANCE.webui_free(ptr);
  }

  /**
   * @brief Safely allocate memory using the WebUI memory management system. It
   *        can be safely freed using `free()` at any time.
   *
   * @param size The size of memory in bytes
   *
   * @example Pointer myBuffer = Webui.malloc(1024);
   */
  public static Pointer malloc(int size) {
    return Lib.INSTANCE.webui_malloc(size);
  }

  /**
   * @brief Copy raw data.
   *
   * @param dest  Destination memory pointer
   * @param src   Source memory pointer
   * @param count Bytes to copy
   *
   * @example Webui.memcpy(myBuffer, myData, 64);
   */
  public static void memcpy(Pointer dest, Pointer src, int count) {
    Lib.INSTANCE.webui_memcpy(dest, src, count);
  }

  /**
   * @brief Safely send raw data to the UI. Single client.
   *
   * @param e        The event struct
   * @param function The JavaScript function to receive raw data: `function
   *                 myFunc(myData){}`
   * @param raw      The raw data buffer
   * @param size     The raw data size in bytes
   *
   * @example Webui.send_raw_client(e, "myJavaScriptFunc", myBuffer, 64);
   */
  public static void send_raw_client(WebuiCallbacks.WebUIEventT e, String function, Pointer raw, int size) {
    Lib.INSTANCE.webui_send_raw_client(e, function, raw, size);
  }

  /**
   * @brief Open an URL in the native default web browser.
   *
   * @param url The URL to open
   *
   * @example Webui.open_url("https://webui.me");
   */
  public static void open_url(String url) {
    Lib.INSTANCE.webui_open_url(url);
  }

  /**
   * @brief Navigate to a specific URL. Single client.
   *
   * @param e   The event struct
   * @param url Full HTTP URL
   *
   * @example Webui.navigate_client(e, "http://domain.com");
   */
  public static void navigate_client(WebuiCallbacks.WebUIEventT e, String url) {
    Lib.INSTANCE.webui_navigate_client(e, url);
  }

  /**
   * @brief Free all memory resources. Should be called only at the end.
   *
   * @example
   *          Webui.wait_();
   *          Webui.clean();
   */
  public static void clean() {
    Lib.INSTANCE.webui_clean();
  }

  /**
   * @brief Delete all local web-browser profiles folder. It should be called at
   *        the
   *        end.
   *
   * @example
   *          Webui.wait_();
   *          Webui.delete_all_profiles();
   *          Webui.clean();
   */
  public static void delete_all_profiles() {
    Lib.INSTANCE.webui_delete_all_profiles();
  }

  /**
   * @brief Get an available usable free network port.
   *
   * @return Returns a free port
   *
   * @example int port = Webui.get_free_port();
   */
  public static int get_free_port() {
    return Lib.INSTANCE.webui_get_free_port();
  }

  /**
   * @brief Control the WebUI behaviour. It's recommended to be called at the
   *        beginning.
   *
   * @param option The desired option from `webui_config` enum
   * @param status The status of the option, `true` or `false`
   *
   * @example Webui.set_config(WebuiConst.config.show_wait_connection, false);
   */
  public static void set_config(WebuiConst.config option, boolean status) {
    Lib.INSTANCE.webui_set_config(EnumUtils.toInteger(option), status);
  }

  /**
   * @brief Get the HTTP mime type of a file.
   *
   * @return Returns the HTTP mime string
   *
   * @example String mime = Webui.get_mime_type("foo.png");
   */
  public static String get_mime_type(String file) {
    return Lib.INSTANCE.webui_get_mime_type(file);
  }

  /**
   * @brief Set the SSL/TLS certificate and the private key content, both in PEM
   *        format. This works only with `webui-2-secure` library. If set empty
   *        WebUI
   *        will generate a self-signed certificate.
   *
   * @param certificate_pem The SSL/TLS certificate content in PEM format
   * @param private_key_pem The private key content in PEM format
   *
   * @return Returns True if the certificate and the key are valid.
   *
   * @example boolean ret = Webui.set_tls_certificate("-----BEGIN
   *          CERTIFICATE-----\n...", "-----BEGIN PRIVATE KEY-----\n...");
   */
  public static boolean set_tls_certificate(String certificate_pem, String private_key_pem) {
    return Lib.INSTANCE.webui_set_tls_certificate(certificate_pem, private_key_pem);
  }

  /**
   * @brief Run JavaScript without waiting for the response. Single client.
   *
   * @param e      The event struct
   * @param script The JavaScript to be run
   *
   * @example Webui.run_client(e, "alert('Hello');");
   */
  public static void run_client(WebuiCallbacks.WebUIEventT e, String script) {
    Lib.INSTANCE.webui_run_client(e, script);
  }

  /**
   * @brief Run JavaScript and get the response back. Single client.
   *        Make sure your local buffer can hold the response.
   *
   * @param e             The event struct
   * @param script        The JavaScript to be run
   * @param timeout       The execution timeout in seconds
   * @param buffer        The local buffer to hold the response
   * @param buffer_length The local buffer size
   *
   * @return Returns True if there is no execution error
   *
   * @example boolean err = Webui.script_client(e, "return 4 + 6;", 0,
   *          myBuffer, myBufferSize);
   */
  public static void script_client(WebuiCallbacks.WebUIEventT e, String script, int timeout, String[] buffer,
      int buffer_length) {
    Lib.INSTANCE.webui_script_client(e, script, timeout, buffer, buffer_length);
  }

  /**
   * @brief Get how many arguments there are in an event.
   *
   * @param e The event struct
   *
   * @return Returns the arguments count.
   *
   * @example int count = Webui.get_count(e);
   */
  public static int get_count(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_count(e);
  }

  /**
   * @brief Get an argument as integer at a specific index.
   *
   * @param e     The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns argument as integer
   *
   * @example NativeLong myNum = Webui.get_int_at(e, 0);
   */
  public static NativeLong get_int_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_int_at(e, index);
  }

  /**
   * @brief Get the first argument as integer.
   *
   * @param e The event struct
   *
   * @return Returns argument as integer
   *
   * @example NativeLong myNum = Webui.get_int(e);
   */
  public static NativeLong get_int(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_int(e);
  }

  /**
   * @brief Get an argument as float at a specific index.
   *
   * @param e     The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns argument as float
   *
   * @example double myNum = Webui.get_float_at(e, 0);
   */
  public static double get_float_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_float_at(e, index);
  }

  /**
   * @brief Get the first argument as float.
   *
   * @param e The event struct
   *
   * @return Returns argument as float
   *
   * @example double myNum = Webui.get_float(e);
   */
  public static double get_float(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_float(e);
  }

  /**
   * @brief Get an argument as string at a specific index.
   *
   * @param e     The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns argument as string
   *
   * @example String myStr = Webui.get_string_at(e, 0);
   */
  public static String get_string_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_string_at(e, index);
  }

  /**
   * @brief Get the first argument as string.
   *
   * @param e The event struct
   *
   * @return Returns argument as string
   *
   * @example String myStr = Webui.get_string(e);
   */
  public static String get_string(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_string(e);
  }

  /**
   * @brief Get an argument as boolean at a specific index.
   *
   * @param e     The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns argument as boolean
   *
   * @example boolean myBool = Webui.get_bool_at(e, 0);
   */
  public static boolean get_bool_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_bool_at(e, index);
  }

  /**
   * @brief Get the first argument as boolean.
   *
   * @param e The event struct
   *
   * @return Returns argument as boolean
   *
   * @example boolean myBool = Webui.get_bool(e);
   */
  public static boolean get_bool(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_bool(e);
  }

  /**
   * @brief Get the size in bytes of an argument at a specific index.
   *
   * @param e     The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns size in bytes
   *
   * @example int argLen = Webui.get_size_at(e, 0);
   */
  public static int get_size_at(WebuiCallbacks.WebUIEventT e, int index) {
    return Lib.INSTANCE.webui_get_size_at(e, index);
  }

  /**
   * @brief Get size in bytes of the first argument.
   *
   * @param e The event struct
   *
   * @return Returns size in bytes
   *
   * @example int argLen = Webui.get_size(e);
   */
  public static int get_size(WebuiCallbacks.WebUIEventT e) {
    return Lib.INSTANCE.webui_get_size(e);
  }

  /**
   * @brief Return the response to JavaScript as integer.
   *
   * @param e The event struct
   * @param n The integer to be send to JavaScript
   *
   * @example Webui.return_int(e, 123);
   */
  public static void return_int(WebuiCallbacks.WebUIEventT e, NativeLong n) {
    Lib.INSTANCE.webui_return_int(e, n);
  }

  /**
   * @brief Return the response to JavaScript as float.
   *
   * @param e The event struct
   * @param f The float number to be send to JavaScript
   *
   * @example Webui.return_float(e, 123.456);
   */
  public static void return_float(WebuiCallbacks.WebUIEventT e, double f) {
    Lib.INSTANCE.webui_return_float(e, f);
  }

  /**
   * @brief Return the response to JavaScript as string.
   *
   * @param e The event struct
   * @param s The string to be send to JavaScript
   *
   * @example Webui.return_string(e, "Response...");
   */
  public static void return_string(WebuiCallbacks.WebUIEventT e, String s) {
    Lib.INSTANCE.webui_return_string(e, s);
  }

  /**
   * @brief Return the response to JavaScript as boolean.
   *
   * @param e The event struct
   * @param b The boolean to be send to JavaScript
   *
   * @example Webui.return_bool(e, true);
   */
  public static void return_bool(WebuiCallbacks.WebUIEventT e, boolean b) {
    Lib.INSTANCE.webui_return_bool(e, b);
  }

  public static int get_last_error_number() {
    return Lib.INSTANCE.webui_get_last_error_number();
  }

  public static String get_last_error_message() {
    return Lib.INSTANCE.webui_get_last_error_message();
  }

  /**
   * @brief Check if the app still running.
   *
   * @return Returns True if app is running
   *
   * @example boolean status = Webui.interface_is_app_running();
   */
  public static boolean interface_is_app_running() {
    return Lib.INSTANCE.webui_interface_is_app_runnint();
  }
}
