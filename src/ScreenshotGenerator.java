import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class ScreenshotGenerator {
    public static void main(String[] args) throws Exception {
        System.setProperty("apple.awt.UIElement", "true");
        new File("screenshots").mkdirs();
        boolean quickMode = args.length > 0 && args[0].equalsIgnoreCase("quick");

        SwingUtilities.invokeAndWait(() -> {
            capture(new LoginFrame(false), "screenshots/login-window.png");
            Player samplePlayer = new Player(1, "student1", 3, 1, 2, 36);
            capture(new GameFrame(samplePlayer), "screenshots/game-window.png");
            if (!quickMode) {
                capture(new TopScorersFrame(), "screenshots/top-scorers-window.png");
            }
        });
        System.out.println("Screenshots generated in screenshots/.");
        System.exit(0);
    }

    private static void capture(JFrame frame, String path) {
        try {
            Container content = frame.getContentPane();
            content.setSize(frame.getWidth(), frame.getHeight());
            layoutRecursively(content);
            BufferedImage image = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            content.printAll(graphics);
            graphics.dispose();
            ImageIO.write(image, "png", new File(path));
            frame.dispose();
        } catch (Exception exception) {
            throw new RuntimeException("Could not create " + path, exception);
        }
    }

    private static void layoutRecursively(Container container) {
        container.doLayout();
        for (Component component : container.getComponents()) {
            if (component instanceof Container childContainer) {
                layoutRecursively(childContainer);
            }
        }
    }
}
