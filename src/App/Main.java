package App;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello World!");
    Webui lib = Webui.INSTANCE;
    int x = lib.webui_new_window();
    lib.webui_show(x, "<html><head><script src=\"webui.js\"></script></head> Hello World ! </html>");
    lib.webui_wait();
  }
}
