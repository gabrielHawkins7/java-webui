import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

interface Lib extends Library {

  Lib INSTANCE = (Lib) Native.load("debug/lin-x64/webui-2.so", Lib.class);

  /**
   * @brief Create a new WebUI window object.
   *
   * @return Returns the window number.
   *
   * @example size_t myWindow = webui_new_window();
   */
  int webui_new_window();

  /**
   * @brief Show a window using embedded HTML, or a file. If the window is
   * already
   * open, it will be refreshed. This will refresh all windows in multi-client
   * mode.
   *
   * @param window The window number
   * @param content The HTML, URL, Or a local file
   *
   * @return Returns True if showing the window is successed.
   *
   * @example webui_show(myWindow, "<html>...</html>"); |
   * webui_show(myWindow, "index.html"); | webui_show(myWindow, "http://...");
   */
  boolean webui_show(int window, String content);

  /**
   * @brief Wait until all opened windows get closed.
   *
   * @example webui_wait();
   */
  void webui_wait();

  void webui_minimize(int window);

  void webui_maximize(int window);

  /**
   * @brief Close a specific window only. The window object will still exist.
   * All clients.
   *
   * @param window The window number
   *
   * @example webui_close(myWindow);
   */
  void webui_close(int window);

  /**
   * @brief Set the window size.
   *
   * @param window The window number
   * @param width The window width
   * @param height The window height
   *
   * @example webui_set_size(myWindow, 800, 600);
   */
  void webui_set_size(int window, int width, int height);

  void webui_set_frameless(int window, boolean status);

  void webui_set_transparent(int window, boolean status);

  void webui_set_resizable(int window, boolean status);

  void webui_set_center(int window);

  /**
   * @brief Show a WebView window using embedded HTML, or a file. If the window
   * is already
   * open, it will be refreshed. Note: Win32 need `WebView2Loader.dll`.
   *
   * @param window The window number
   * @param content The HTML, URL, Or a local file
   *
   * @return Returns True if showing the WebView window is successed.
   *
   * @example webui_show_wv(myWindow, "<html>...</html>"); |
   * webui_show_wv(myWindow,
   * "index.html"); | webui_show_wv(myWindow, "http://...");
   */
  void webui_show_wv(int window, String content);

  /**
   * @brief Bind an HTML element and a JavaScript object with a backend
   * function. Empty
   * element name means all events.
   *
   * @param window The window number
   * @param element The HTML element / JavaScript object
   * @param func The callback function
   *
   * @return Returns a unique bind ID.
   *
   * @example webui_bind(myWindow, "myFunction", myFunction);
   */
  int webui_bind(int window, String element, WebuiCallbacks.EventCallback func);

  /**
   * @brief Use this API after using `webui_bind()` to add any user data to it
   * that can be
   * read later using `webui_get_context()`.
   *
   * @param window The window number
   * @param element The HTML element / JavaScript object
   * @param context Any user data
   *
   * @example
   * webui_bind(myWindow, "myFunction", myFunction);
   * 
   * webui_set_context(myWindow, "myFunction", myData);
   * 
   * void myFunction(webui_event_t* e) {
   * void* myData = webui_get_context(e);
   * }
   */
  void webui_set_context(int window, String element, Pointer context);

  /**
   * @brief Get user data that is set using `webui_set_context()`.
   *
   * @param e The event struct
   * 
   * @return Returns user data pointer.
   *
   * @example
   * webui_bind(myWindow, "myFunction", myFunction);
   * 
   * webui_set_context(myWindow, "myFunction", myData);
   * 
   * void myFunction(webui_event_t* e) {
   * void* myData = webui_get_context(e);
   * }
   */
  Pointer webui_get_context(WebuiCallbacks.WebUIEventT e);

  /**
   * @brief Get the recommended web browser ID to use. If you
   * are already using one, this function will return the same ID.
   * 
   * @param window The window number
   * 
   * @return Returns a web browser ID.
   * 
   * @example size_t browserID = webui_get_best_browser(myWindow);
   */
  int webui_get_best_browser(int window);

  /**
   * @brief Show a window using embedded HTML, or a file. If the window is
   * already
   * open, it will be refreshed. Single client.
   *
   * @param e The event struct
   * @param content The HTML, URL, Or a local file
   *
   * @return Returns True if showing the window is successed.
   *
   * @example webui_show_client(e, "<html>...</html>"); |
   * webui_show_client(e, "index.html"); | webui_show_client(e, "http://...");
   */
  boolean webui_show_client(WebuiCallbacks.WebUIEventT e, String content);

  /**
   * @brief Same as `webui_show()`. But using a specific web browser.
   *
   * @param window The window number
   * @param content The HTML, Or a local file
   * @param browser The web browser to be used
   *
   * @return Returns True if showing the window is successed.
   *
   * @example webui_show_browser(myWindow, "<html>...</html>", Chrome); |
   * webui_show(myWindow, "index.html", Firefox);
   */
  boolean webui_show_browser(int window, String content, int broswer);

  /**
   * @brief Same as `webui_show()`. But start only the web server and return the
   * URL.
   * No window will be shown.
   *
   * @param window The window number
   * @param content The HTML, Or a local file
   *
   * @return Returns the url of this window server.
   *
   * @example const char* url = webui_start_server(myWindow,
   * "/full/root/path");
   */
  String webui_start_server(int window, String content);

  /**
   * @brief Set the window in Kiosk mode (Full screen).
   *
   * @param window The window number
   * @param status True or False
   *
   * @example webui_set_kiosk(myWindow, true);
   */
  void webui_set_kiosk(int window, boolean status);

  /**
   * @brief Add a user-defined web browser's CLI parameters.
   *
   * @param window The window number
   * @param params Command line parameters
   *
   * @example webui_set_custom_parameters(myWindow,
   * "--remote-debugging-port=9222");
   */
  void webui_set_custom_parameters(int window, String[] params);

  /**
   * @brief Set the window with high-contrast support. Useful when you want to
   * build a better high-contrast theme with CSS.
   *
   * @param window The window number
   * @param status True or False
   *
   * @example webui_set_high_contrast(myWindow, true);
   */
  void webui_set_high_contrast(int window, boolean status);

  /**
   * @brief Get OS high contrast preference.
   *
   * @return Returns True if OS is using high contrast theme
   *
   * @example bool hc = webui_is_high_contrast();
   */
  boolean webui_is_high_contrast();

  /**
   * @brief Check if a web browser is installed.
   *
   * @return Returns True if the specified browser is available
   *
   * @example bool status = webui_browser_exist(Chrome);
   */
  boolean webui_browser_exist(int browser);

  /**
   * @brief Close a specific client.
   *
   * @param e The event struct
   *
   * @example webui_close_client(e);
   */
  void webui_close_client(WebuiCallbacks.WebUIEventT e);

  /**
   * @brief Close a specific window and free all memory resources.
   *
   * @param window The window number
   *
   * @example webui_destroy(myWindow);
   */
  void webui_destroy(int window);

  /**
   * @brief Close all open windows. `webui_wait()` will return (Break).
   *
   * @example webui_exit();
   */
  void webui_exit();

  /**
   * @brief Set the web-server root folder path for a specific window.
   *
   * @param window The window number
   * @param path The local folder full path
   *
   * @example webui_set_root_folder(myWindow, "/home/Foo/Bar/");
   */
  boolean webui_set_root_folder(int window, String path);

  void webui_set_broswer_folder(String path);

  /**
   * @brief Set the web-server root folder path for all windows. Should be used
   * before `webui_show()`.
   *
   * @param path The local folder full path
   *
   * @example webui_set_default_root_folder("/home/Foo/Bar/");
   */
  boolean webui_set_default_root_folder(String path);

  /**
   * @brief Set a custom handler to serve files. This custom handler should
   * return full HTTP header and body.
   * This deactivates any previous handler set with
   * `webui_set_file_handler_window`
   *
   * @param window The window number
   * @param handler The handler function: `void myHandler(const char* filename,
   * int* length)`
   *
   * @example webui_set_file_handler(myWindow, myHandlerFunction);
   */
  void webui_set_file_handler(int window, WebuiCallbacks.FileHandler handler);

  /**
   * @brief Set a custom handler to serve files. This custom handler should
   * return full HTTP header and body.
   * This deactivates any previous handler set with `webui_set_file_handler`
   *
   * @param window The window number
   * @param handler The handler function: `void myHandler(size_t window, const
   * char* filename,
   * int* length)`
   *
   * @example webui_set_file_handler_window(myWindow, myHandlerFunction);
   */
  void webui_set_file_handler_window(int window, WebuiCallbacks.FileWindowHandler handler);

  /**
   * @brief Use this API to set a file handler response if your backend need
   * async
   * response for `webui_set_file_handler()`.
   *
   * @param window The window number
   * @param response The response buffer
   * @param length The response size
   *
   * @example webui_interface_set_response_file_handler(myWindow, buffer,
   * 1024);
   */
  void webui_interface_set_response_file_handler(int window, Pointer response, int length);

  /**
   * @brief Check if the specified window is still running.
   *
   * @param window The window number
   *
   * @example webui_is_shown(myWindow);
   */
  boolean webui_is_shown(int window);

  /**
   * @brief Set the maximum time in seconds to wait for the window to connect.
   * This effect `show()` and `wait()`. Value of `0` means wait forever.
   *
   * @param second The timeout in seconds
   *
   * @example webui_set_timeout(30);
   */
  void webui_set_timeout(int second);

  /**
   * @brief Set the default embedded HTML favicon.
   *
   * @param window The window number
   * @param icon The icon as string: `<svg>...</svg>`
   * @param icon_type The icon type: `image/svg+xml`
   *
   * @example webui_set_icon(myWindow, "<svg>...</svg>", "image/svg+xml");
   */
  void webui_set_icon(int window, String icon, String icon_type);

  /**
   * @brief Encode text to Base64. The returned buffer need to be freed.
   *
   * @param str The string to encode (Should be null terminated)
   *
   * @return Returns the base64 encoded string
   * 
   * @example char* base64 = webui_encode("Foo Bar");
   */
  Pointer webui_encode(String str);

  /**
   * @brief Decode a Base64 encoded text. The returned buffer need to be freed.
   *
   * @param str The string to decode (Should be null terminated)
   * 
   * @return Returns the base64 decoded string
   *
   * @example char* str = webui_decode("SGVsbG8=");
   */
  Pointer webui_decode(String str);

  /**
   * @brief Safely free a buffer allocated by WebUI using `webui_malloc()`.
   *
   * @param ptr The buffer to be freed
   *
   * @example webui_free(myBuffer);
   */
  void webui_free(Pointer ptr);

  /**
   * @brief Safely allocate memory using the WebUI memory management system. It
   * can be safely freed using `webui_free()` at any time.
   *
   * @param size The size of memory in bytes
   *
   * @example char* myBuffer = (char*)webui_malloc(1024);
   */
  Pointer webui_malloc(int size);

  /**
   * @brief Copy raw data.
   *
   * @param dest Destination memory pointer
   * @param src Source memory pointer
   * @param count Bytes to copy
   *
   * @example webui_memcpy(myBuffer, myData, 64);
   */
  void webui_memcpy(Pointer dest, Pointer src, int count);

  /**
   * @brief Safely send raw data to the UI. All clients.
   *
   * @param window The window number
   * @param function The JavaScript function to receive raw data: `function
   * myFunc(myData){}`
   * @param raw The raw data buffer
   * @param size The raw data size in bytes
   *
   * @example webui_send_raw(myWindow, "myJavaScriptFunc", myBuffer, 64);
   */
  void webui_send_raw(int window, String function, Pointer raw, int size);

  /**
   * @brief Safely send raw data to the UI. Single client.
   *
   * @param e The event struct
   * @param function The JavaScript function to receive raw data: `function
   * myFunc(myData){}`
   * @param raw The raw data buffer
   * @param size The raw data size in bytes
   *
   * @example webui_send_raw_client(e, "myJavaScriptFunc", myBuffer, 64);
   */
  void webui_send_raw_client(WebuiCallbacks.WebUIEventT e, String function, Pointer raw, int size);

  /**
   * @brief Set a window in hidden mode. Should be called before `webui_show()`.
   *
   * @param window The window number
   * @param status The status: True or False
   *
   * @example webui_set_hide(myWindow, True);
   */
  void webui_set_hide(int window, boolean status);

  /**
   * @brief Set the window minimum size.
   *
   * @param window The window number
   * @param width The window width
   * @param height The window height
   *
   * @example webui_set_minimum_size(myWindow, 800, 600);
   */
  void webui_set_minimum_size(int window, int width, int height);

  /**
   * @brief Set the window position.
   *
   * @param window The window number
   * @param x The window X
   * @param y The window Y
   *
   * @example webui_set_position(myWindow, 100, 100);
   */
  void webui_set_position(int window, int x, int y);

  /**
   * @brief Set the web browser profile to use. An empty `name` and `path` means
   * the default user profile. Need to be called before `webui_show()`.
   *
   * @param window The window number
   * @param name The web browser profile name
   * @param path The web browser profile full path
   *
   * @example webui_set_profile(myWindow, "Bar", "/Home/Foo/Bar"); |
   * webui_set_profile(myWindow, "", "");
   */
  void webui_set_profile(int window, String name, String path);

  /**
   * @brief Set the web browser proxy server to use. Need to be called before
   * `webui_show()`.
   *
   * @param window The window number
   * @param proxy_server The web browser proxy_server
   *
   * @example webui_set_proxy(myWindow, "http://127.0.0.1:8888");
   */
  void webui_set_proxy(int window, String proxy_server);

  /**
   * @brief Get current URL of a running window.
   *
   * @param window The window number
   *
   * @return Returns the full URL string
   *
   * @example const char* url = webui_get_url(myWindow);
   */
  String webui_get_url(int window);

  /**
   * @brief Open an URL in the native default web browser.
   *
   * @param url The URL to open
   *
   * @example webui_open_url("https://webui.me");
   */
  void webui_open_url(String url);

  /**
   * @brief Allow a specific window address to be accessible from a public
   * network.
   *
   * @param window The window number
   * @param status True or False
   *
   * @example webui_set_public(myWindow, true);
   */
  void webui_set_public(int window, boolean status);

  /**
   * @brief Navigate to a specific URL. All clients.
   *
   * @param window The window number
   * @param url Full HTTP URL
   *
   * @example webui_navigate(myWindow, "http://domain.com");
   */
  void webui_navigate(int window, String url);

  /**
   * @brief Navigate to a specific URL. Single client.
   *
   * @param e The event struct
   * @param url Full HTTP URL
   *
   * @example webui_navigate_client(e, "http://domain.com");
   */
  void webui_navigate_client(WebuiCallbacks.WebUIEventT e, String url);

  /**
   * @brief Free all memory resources. Should be called only at the end.
   *
   * @example
   * webui_wait();
   * webui_clean();
   */
  void webui_clean();

  /**
   * @brief Delete all local web-browser profiles folder. It should be called at
   * the
   * end.
   *
   * @example
   * webui_wait();
   * webui_delete_all_profiles();
   * webui_clean();
   */
  void webui_delete_all_profiles();

  /**
   * @brief Delete a specific window web-browser local folder profile.
   *
   * @param window The window number
   *
   * @example
   * webui_wait();
   * webui_delete_profile(myWindow);
   * webui_clean();
   *
   * @note This can break functionality of other windows if using the same
   * web-browser.
   */
  void webui_delete_profile(int window);

  /**
   * @brief Get the ID of the parent process (The web browser may re-create
   * another new process).
   *
   * @param window The window number
   *
   * @return Returns the the parent process id as integer
   *
   * @example size_t id = webui_get_parent_process_id(myWindow);
   */
  int webui_get_parent_process_id(int window);

  /**
   * @brief Get the ID of the last child process.
   *
   * @param window The window number
   *
   * @return Returns the the child process id as integer
   *
   * @example size_t id = webui_get_child_process_id(myWindow);
   */
  int webui_get_child_process_id(int window);

  /**
   * @brief Get the network port of a running window.
   * This can be useful to determine the HTTP link of `webui.js`
   *
   * @param window The window number
   * 
   * @return Returns the network port of the window
   *
   * @example size_t port = webui_get_port(myWindow);
   */
  int webui_get_port(int window);

  /**
   * @brief Set a custom web-server/websocket network port to be used by WebUI.
   * This can be useful to determine the HTTP link of `webui.js` in case
   * you are trying to use WebUI with an external web-server like NGNIX.
   *
   * @param window The window number
   * @param port The web-server network port WebUI should use
   *
   * @return Returns True if the port is free and usable by WebUI
   *
   * @example bool ret = webui_set_port(myWindow, 8080);
   */
  boolean webui_set_port(int window, int port);

  /**
   * @brief Get an available usable free network port.
   *
   * @return Returns a free port
   *
   * @example size_t port = webui_get_free_port();
   */
  int webui_get_free_port();

  /**
   * @brief Control the WebUI behaviour. It's recommended to be called at the
   * beginning.
   *
   * @param option The desired option from `webui_config` enum
   * @param status The status of the option, `true` or `false`
   *
   * @example webui_set_config(show_wait_connection, false);
   */
  void webui_set_config(int option, boolean status);

  /**
   * @brief Control if UI events comming from this window should be processed
   * one a time in a single blocking thread `True`, or process every event in
   * a new non-blocking thread `False`. This update single window. You can use
   * `webui_set_config(ui_event_blocking, ...)` to update all windows.
   *
   * @param window The window number
   * @param status The blocking status `true` or `false`
   *
   * @example webui_set_event_blocking(myWindow, true);
   */
  void webui_set_event_blocking(int window, boolean status);

  /**
   * @brief Get the HTTP mime type of a file.
   *
   * @return Returns the HTTP mime string
   *
   * @example const char* mime = webui_get_mime_type("foo.png");
   */
  String webui_get_mime_type(String file);

  /**
   * @brief Set the SSL/TLS certificate and the private key content, both in PEM
   * format. This works only with `webui-2-secure` library. If set empty WebUI
   * will generate a self-signed certificate.
   *
   * @param certificate_pem The SSL/TLS certificate content in PEM format
   * @param private_key_pem The private key content in PEM format
   *
   * @return Returns True if the certificate and the key are valid.
   *
   * @example bool ret = webui_set_tls_certificate("-----BEGIN
   * CERTIFICATE-----\n...", "-----BEGIN PRIVATE KEY-----\n...");
   */
  boolean webui_set_tls_certificate(String certificate_pem, String private_key_pem);

  /**
   * @brief Run JavaScript without waiting for the response. All clients.
   *
   * @param window The window number
   * @param script The JavaScript to be run
   *
   * @example webui_run(myWindow, "alert('Hello');");
   */
  void webui_run(int window, String script);

  /**
   * @brief Run JavaScript without waiting for the response. Single client.
   *
   * @param e The event struct
   * @param script The JavaScript to be run
   *
   * @example webui_run_client(e, "alert('Hello');");
   */
  void webui_run_client(WebuiCallbacks.WebUIEventT e, String script);

  /**
   * @brief Run JavaScript and get the response back. Work only in single client
   * mode.
   * Make sure your local buffer can hold the response.
   *
   * @param window The window number
   * @param script The JavaScript to be run
   * @param timeout The execution timeout in seconds
   * @param buffer The local buffer to hold the response
   * @param buffer_length The local buffer size
   *
   * @return Returns True if there is no execution error
   *
   * @example bool err = webui_script(myWindow, "return 4 + 6;", 0, myBuffer,
   * myBufferSize);
   */
  void webui_script(int window, String script, int timeout, String[] buffer, int buffer_length);

  /**
   * @brief Run JavaScript and get the response back. Single client.
   * Make sure your local buffer can hold the response.
   *
   * @param e The event struct
   * @param script The JavaScript to be run
   * @param timeout The execution timeout in seconds
   * @param buffer The local buffer to hold the response
   * @param buffer_length The local buffer size
   *
   * @return Returns True if there is no execution error
   *
   * @example bool err = webui_script_client(e, "return 4 + 6;", 0, myBuffer,
   * myBufferSize);
   */
  void webui_script_client(WebuiCallbacks.WebUIEventT e, String script, int timeout, String[] buffer,
      int buffer_length);

  /**
   * @brief Chose between Deno and Nodejs as runtime for .js and .ts files.
   *
   * @param window The window number
   * @param runtime Deno | Bun | Nodejs | None
   *
   * @example webui_set_runtime(myWindow, Deno);
   */
  void webui_set_runtime(int window, int runtime);

  /**
   * @brief Get how many arguments there are in an event.
   *
   * @param e The event struct
   *
   * @return Returns the arguments count.
   *
   * @example size_t count = webui_get_count(e);
   */
  int webui_get_count(WebuiCallbacks.WebUIEventT e);

  /**
   * @brief Get an argument as integer at a specific index.
   *
   * @param e The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns argument as integer
   *
   * @example long long int myNum = webui_get_int_at(e, 0);
   */
  NativeLong webui_get_int_at(WebuiCallbacks.WebUIEventT e, int index);

  /**
   * @brief Get the first argument as integer.
   *
   * @param e The event struct
   *
   * @return Returns argument as integer
   *
   * @example long long int myNum = webui_get_int(e);
   */
  NativeLong webui_get_int(WebuiCallbacks.WebUIEventT e);

  /**
   * @brief Get an argument as float at a specific index.
   *
   * @param e The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns argument as float
   *
   * @example double myNum = webui_get_float_at(e, 0);
   */
  double webui_get_float_at(WebuiCallbacks.WebUIEventT e, int index);

  /**
   * @brief Get the first argument as float.
   *
   * @param e The event struct
   *
   * @return Returns argument as float
   *
   * @example double myNum = webui_get_float(e);
   */
  double webui_get_float(WebuiCallbacks.WebUIEventT e);

  /**
   * @brief Get an argument as string at a specific index.
   *
   * @param e The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns argument as string
   *
   * @example const char* myStr = webui_get_string_at(e, 0);
   */
  String webui_get_string_at(WebuiCallbacks.WebUIEventT e, int index);

  /**
   * @brief Get the first argument as string.
   *
   * @param e The event struct
   *
   * @return Returns argument as string
   *
   * @example const char* myStr = webui_get_string(e);
   */
  String webui_get_string(WebuiCallbacks.WebUIEventT e);

  /**
   * @brief Get an argument as boolean at a specific index.
   *
   * @param e The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns argument as boolean
   *
   * @example bool myBool = webui_get_bool_at(e, 0);
   */
  boolean webui_get_bool_at(WebuiCallbacks.WebUIEventT e, int index);

  /**
   * @brief Get the first argument as boolean.
   *
   * @param e The event struct
   *
   * @return Returns argument as boolean
   *
   * @example bool myBool = webui_get_bool(e);
   */
  boolean webui_get_bool(WebuiCallbacks.WebUIEventT e);

  /**
   * @brief Get the size in bytes of an argument at a specific index.
   *
   * @param e The event struct
   * @param index The argument position starting from 0
   *
   * @return Returns size in bytes
   *
   * @example size_t argLen = webui_get_size_at(e, 0);
   */
  int webui_get_size_at(WebuiCallbacks.WebUIEventT e, int index);

  /**
   * @brief Get size in bytes of the first argument.
   *
   * @param e The event struct
   *
   * @return Returns size in bytes
   *
   * @example size_t argLen = webui_get_size(e);
   */
  int webui_get_size(WebuiCallbacks.WebUIEventT e);

  /**
   * @brief Return the response to JavaScript as integer.
   *
   * @param e The event struct
   * @param n The integer to be send to JavaScript
   *
   * @example webui_return_int(e, 123);
   */
  void webui_return_int(WebuiCallbacks.WebUIEventT e, NativeLong n);

  /**
   * @brief Return the response to JavaScript as float.
   *
   * @param e The event struct
   * @param f The float number to be send to JavaScript
   *
   * @example webui_return_float(e, 123.456);
   */
  void webui_return_float(WebuiCallbacks.WebUIEventT e, double f);

  /**
   * @brief Return the response to JavaScript as string.
   *
   * @param e The event struct
   * @param n The string to be send to JavaScript
   *
   * @example webui_return_string(e, "Response...");
   */
  void webui_return_string(WebuiCallbacks.WebUIEventT e, String s);

  /**
   * @brief Return the response to JavaScript as boolean.
   *
   * @param e The event struct
   * @param n The boolean to be send to JavaScript
   *
   * @example webui_return_bool(e, true);
   */
  void webui_return_bool(WebuiCallbacks.WebUIEventT e, boolean b);

  int webui_get_last_error_number();

  String webui_get_last_error_message();

  /**
   * @brief Bind a specific HTML element click event with a function. Empty
   * element means all events.
   *
   * @param window The window number
   * @param element The element ID
   * @param func The callback as myFunc(Window, EventType, Element, EventNumber,
   * BindID)
   *
   * @return Returns unique bind ID
   *
   * @example size_t id = webui_interface_bind(myWindow, "myID", myCallback);
   */
  int webui_interface_bind(int window, String element, WebuiCallbacks.InterfaceCallback func);

  /**
   * @brief When using `webui_interface_bind()`, you may need this function to
   * easily set a response.
   *
   * @param window The window number
   * @param event_number The event number
   * @param response The response as string to be send to JavaScript
   *
   * @example webui_interface_set_response(myWindow, e->event_number,
   * "Response...");
   */
  void webui_interface_set_response(int window, int event_number, String response);

  /**
   * @brief Check if the app still running.
   *
   * @return Returns True if app is running
   *
   * @example bool status = webui_interface_is_app_running();
   */
  boolean webui_interface_is_app_runnint();

  /**
   * @brief Get a unique window ID.
   *
   * @param window The window number
   *
   * @return Returns the unique window ID as integer
   *
   * @example size_t id = webui_interface_get_window_id(myWindow);
   */
  int webui_interface_get_window_id(int window);

  /**
   * @brief Get an argument as string at a specific index.
   *
   * @param window The window number
   * @param event_number The event number
   * @param index The argument position
   *
   * @return Returns argument as string
   *
   * @example const char* myStr = webui_interface_get_string_at(myWindow,
   * e->event_number, 0);
   */
  String webui_interface_get_string_at(int window, int event_number, int index);

  /**
   * @brief Get an argument as integer at a specific index.
   *
   * @param window The window number
   * @param event_number The event number
   * @param index The argument position
   *
   * @return Returns argument as integer
   *
   * @example long long int myNum = webui_interface_get_int_at(myWindow,
   * e->event_number, 0);
   */
  NativeLong webui_interface_get_int_at(int window, int event_number, int index);

  /**
   * @brief Get an argument as float at a specific index.
   *
   * @param window The window number
   * @param event_number The event number
   * @param index The argument position
   *
   * @return Returns argument as float
   *
   * @example double myFloat = webui_interface_get_int_at(myWindow,
   * e->event_number, 0);
   */
  double webui_interface_get_float_at(int window, int event_number, int index);

  /**
   * @brief Get an argument as boolean at a specific index.
   *
   * @param window The window number
   * @param event_number The event number
   * @param index The argument position
   *
   * @return Returns argument as boolean
   *
   * @example bool myBool = webui_interface_get_bool_at(myWindow,
   * e->event_number, 0);
   */
  boolean webui_interface_get_bool_at(int window, int event_number, int index);

  /**
   * @brief Get the size in bytes of an argument at a specific index.
   *
   * @param window The window number
   * @param event_number The event number
   * @param index The argument position
   *
   * @return Returns size in bytes
   *
   * @example size_t argLen = webui_interface_get_size_at(myWindow,
   * e->event_number, 0);
   */
  int webui_interface_get_size_at(int window, int event_number, int index);

  /**
   * @brief Show a window using embedded HTML, or a file. If the window is
   * already
   * open, it will be refreshed. Single client.
   *
   * @param window The window number
   * @param event_number The event number
   * @param content The HTML, URL, Or a local file
   *
   * @return Returns True if showing the window is successed.
   *
   * @example webui_show_client(e, "<html>...</html>"); |
   * webui_show_client(e, "index.html"); | webui_show_client(e, "http://...");
   */
  boolean webui_interface_show_client(int window, int event_number, String content);

  /**
   * @brief Close a specific client.
   *
   * @param window The window number
   * @param event_number The event number
   *
   * @example webui_close_client(e);
   */
  void webui_interface_close_client(int window, int event_number);

  /**
   * @brief Safely send raw data to the UI. Single client.
   *
   * @param window The window number
   * @param event_number The event number
   * @param function The JavaScript function to receive raw data: `function
   * myFunc(myData){}`
   * @param raw The raw data buffer
   * @param size The raw data size in bytes
   *
   * @example webui_send_raw_client(e, "myJavaScriptFunc", myBuffer, 64);
   */
  void webui_interface_send_raw_client(int window, int event_number, String function, Pointer raw, int size);

  /**
   * @brief Navigate to a specific URL. Single client.
   *
   * @param window The window number
   * @param event_number The event number
   * @param url Full HTTP URL
   *
   * @example webui_navigate_client(e, "http://domain.com");
   */
  void webui_interface_navigate_client(int window, int event_number, String url);

  /**
   * @brief Run JavaScript without waiting for the response. Single client.
   *
   * @param window The window number
   * @param event_number The event number
   * @param script The JavaScript to be run
   *
   * @example webui_run_client(e, "alert('Hello');");
   */
  void webui_interface_run_client(int window, int event_number, String script);

  /**
   * @brief Run JavaScript and get the response back. Single client.
   * Make sure your local buffer can hold the response.
   *
   * @param window The window number
   * @param event_number The event number
   * @param script The JavaScript to be run
   * @param timeout The execution timeout in seconds
   * @param buffer The local buffer to hold the response
   * @param buffer_length The local buffer size
   *
   * @return Returns True if there is no execution error
   *
   * @example bool err = webui_script_client(e, "return 4 + 6;", 0, myBuffer,
   * myBufferSize);
   */
  boolean webui_interface_script_client(int window, int event_number, String script, int timeour, String[] buffer,
      int buffer_length);

}
