import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;

public class WebuiCallbacks {
  /**
   * Maps the C struct `webui_event_t`. This structure holds information about an
   * event.
   */
  @Structure.FieldOrder({ "window", "event_type", "element", "event_number", "bind_id", "client_id", "connection_id",
      "cookies" })
  public static class WebUIEventT extends Structure {
    public int window; // The window object number
    public int event_type; // Event type
    public Pointer element; // HTML element ID
    public int event_number; // Internal WebUI event number
    public int bind_id; // Bind ID
    public int client_id; // Client's unique ID
    public int connection_id; // Client's connection ID
    public Pointer cookies; // Client's full cookies

    public WebUIEventT() {
      super();
    }

    public WebUIEventT(Pointer p) {
      super(p);
    }

  }

  // -- Callbacks --------------------------------------------------------------

  /**
   * Callback interface for events bound using `webui_bind`.
   */
  public interface EventCallback extends Callback {
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
