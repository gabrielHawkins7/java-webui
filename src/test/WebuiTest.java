import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WebuiTest {
    @Test
    @DisplayName("A simple assertion to verify the test runner is working.")
    void simpleAssertion() {
        assertTrue(true);
    }

    @Test
    @DisplayName("Test creating a new window.")
    void testNewWindow() {
        Webui w = new Webui();
        assertFalse(w.is_shown());
        w.destroy();
    }

    @Test
    @DisplayName("Test show(), is_shown(), and destroy()")
    void testShowAndDestroy() {
        Webui w = new Webui();
        assertTrue(w.show("<html><head><script src=\"webui.js\"></script></head><body><h1>Test</h1></body></html>"));
        assertTrue(w.is_shown());
        w.destroy();
        assertFalse(w.is_shown());
    }

    @Test
    @DisplayName("Test set_size() and set_position()")
    void testSizeAndPosition() {
        Webui w = new Webui();
        assertDoesNotThrow(() -> {
            w.set_size(800, 600);
            w.set_position(100, 100);
        });
        w.destroy();
    }

    @Test
    @DisplayName("Test set_kiosk()")
    void testKiosk() {
        Webui w = new Webui();
        assertDoesNotThrow(() -> {
            w.set_kiosk(true);
        });
        w.destroy();
    }
}
