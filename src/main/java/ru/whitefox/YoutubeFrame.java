package ru.whitefox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class YoutubeFrame extends JFrame {

    private static final YoutubeFrame youtubeFrame = new YoutubeFrame();

    private JLabel chanelTitleLabel;
    private JLabel subscribersCountLabel;

    public YoutubeFrame(){
        YoutubeParser.update();

        this.setUndecorated(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
        jPanel.setBackground(Color.DARK_GRAY);

        JLabel iconLabel = new JLabel(new ImageIcon(YoutubeParser.image));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        jPanel.add(iconLabel);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        textPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
        textPanel.setBackground(Color.DARK_GRAY);

        chanelTitleLabel = new JLabel(YoutubeParser.chanelTitle);
        chanelTitleLabel.setFont(new Font(Options.font.getName(), Font.PLAIN, 24));
        chanelTitleLabel.setForeground(Color.WHITE);
        textPanel.add(chanelTitleLabel);

        subscribersCountLabel = new JLabel(YoutubeParser.subscribersCount);
        subscribersCountLabel.setFont(new Font(Options.font.getName(), Font.PLAIN, 15));
        subscribersCountLabel.setForeground(Color.WHITE);
        textPanel.add(subscribersCountLabel);

        jPanel.add(textPanel);
        this.add(jPanel);

        FrameDragListener frameDragListener = new FrameDragListener(this);
        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);

        this.pack();
        this.setLocation(Options.width, Options.height);
        this.setIconImage(Options.logoYouTube.getImage());
    }

    public static void init(){
        youtubeFrame.setVisible(true);
    }


    public static void updateUI(){
        YoutubeParser.update();
        SwingUtilities.invokeLater(() -> {
            youtubeFrame.chanelTitleLabel.setText(YoutubeParser.chanelTitle);
            youtubeFrame.subscribersCountLabel.setText(YoutubeParser.subscribersCount);
        });
    }


    private static class FrameDragListener extends MouseAdapter {
        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
            //System.out.println(frame.getLocation());
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }

}
