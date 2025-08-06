import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Webui extends Library {
  Webui INSTANCE = (Webui) Native.load("webui-2.dll", Webui.class);

  int webui_new_window();

  void webui_show(int windoP, String html);

  void webui_wait();
}
