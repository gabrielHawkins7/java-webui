
public class Main {

  static Webui lib = Webui.INSTANCE;
  private static final String HTML_CONTENT = """
          <!DOCTYPE html>
          <html>
            <head>
              <meta charset="UTF-8">
              <title>WebUI JNA Bind Example</title>
              <style>
                body { font-family: sans-serif; background-color: #222; color: #fff; text-align: center; margin-top: 50px; }
                button { padding: 10px 20px; font-size: 16px; margin: 10px; border-radius: 5px; cursor: pointer; }
                #log { margin-top: 20px; padding: 10px; border: 1px solid #555; background-color: #333; min-height: 50px; }
              </style>
              <script src="/webui.js"></script>
            </head>
            <body>
              <h1>WebUI JNA Bind Example</h1>

              <!-- Case 1: Simple element binding -->
              <button id="simple_click_btn">Simple Click Event</button>

              <!-- Case 2: Function binding with arguments and return value -->
              <button onclick="callBackendWithArgs()">Call Backend with Args & Await</button>

              <p>Log from Backend:</p>
              <div id="log"></div>

              <script>
                async function callBackendWithArgs() {
                  const myText = "Hello from JavaScript!";
                  const myNumber = 42;
                  const myBool = true;

                  try {
                    // Call the backend function "process_data" and wait for a response.
                    const result = await webui.call('process_data', myText, myNumber, myBool);

                    // Display the result returned from the Java callback.
                    const logDiv = document.getElementById('log');
                    logDiv.innerHTML += `<p><strong>[JS]</strong> Received from Java: "${result}"</p>`;
                  } catch (error) {
                    console.error("Error calling backend:", error);
                    const logDiv = document.getElementById('log');
                    logDiv.innerHTML += `<p><strong>[JS]</strong> Error: ${error}</p>`;
                  }
                }
              </script>
            </body>
          </html>
      """;
  public static final Webui.EventCallback fn = new Webui.EventCallback() {
    @Override
    public void invoke(Webui.WebUIEventT e) {
      System.out.println("Hello");
    }
  };

  public static void main(String[] args) {
    int x = lib.webui_new_window();

    Webui.EventCallback func = (e) -> {
      System.out.println("Hello World!");
      Webui.INSTANCE.webui_close(e.window);
    };

    lib.webui_bind(x, "simple_click_btn", func);

    lib.webui_set_size(x, 800, 600);

    lib.webui_show(x, HTML_CONTENT);
    lib.webui_wait();
  }
}
