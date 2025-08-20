import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LibTest {

    @Test
    @DisplayName("Test webui_new_window() native call.")
    void testNewWindow() {
        int window = Lib.INSTANCE.webui_new_window();
        assertTrue(window > 0, "webui_new_window() should return a positive window number.");
        Lib.INSTANCE.webui_destroy(window);
    }

    @Test
    @DisplayName("Test webui_show(), webui_is_shown(), and webui_destroy() native calls.")
    void testShowIsShownAndDestroy() {
        int window = Lib.INSTANCE.webui_new_window();
        assertFalse(Lib.INSTANCE.webui_is_shown(window), "A new window should not be shown initially.");
        
        boolean showResult = Lib.INSTANCE.webui_show(window, "<html><head><script src=\"webui.js\"></script></head><body><h1>Test</h1></body></html>");
        assertTrue(showResult, "webui_show() should return true on success.");
        
        assertTrue(Lib.INSTANCE.webui_is_shown(window), "The window should be shown after webui_show().");
        
        Lib.INSTANCE.webui_destroy(window);
        
        assertFalse(Lib.INSTANCE.webui_is_shown(window), "The window should not be shown after webui_destroy().");
    }
}
