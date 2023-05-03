package ru.whitefox;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class Options {

    static final Options options = new Options();
    static final URL iconURL = Main.class.getResource("/youtubeicon.png");
    static final ImageIcon logoYouTube = new ImageIcon(Objects.requireNonNull(iconURL));

    static String url;
    static int height;
    static int width;
    static Font font;
    static int frequency;

    private Options(){
        try (InputStream inputStream = this.getClass().getResourceAsStream("/font.ttf")){
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            url = properties.getProperty("url");
            height = Integer.parseInt(properties.getProperty("height"));
            width = Integer.parseInt(properties.getProperty("width"));

            font = Font.createFont(Font.TRUETYPE_FONT,inputStream);
            frequency = Integer.parseInt(properties.getProperty("frequency"));

            if (url == null || url.length() == 0) {
                JOptionPane.showMessageDialog(
                        null, "Некорректный адрес канала Youtube\nПрограмма будет завершена",
                        "Error", JOptionPane.ERROR_MESSAGE, null);
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
            JOptionPane.showMessageDialog(
                    null, "Некорректный адрес канала Youtube\nПрограмма будет завершена",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
            System.exit(1);
        } catch (FontFormatException e) {
            System.err.println("Шрифт не найден или не поддерживается");
            JOptionPane.showMessageDialog(
                    null, "Шрифт не найден или не поддерживается\nПрограмма будет завершена",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
            System.exit(1);
        }
    }
}
