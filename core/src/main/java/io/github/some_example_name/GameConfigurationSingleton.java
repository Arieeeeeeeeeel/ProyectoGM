package io.github.some_example_name;

public final class GameConfigurationSingleton {
    private static GameConfigurationSingleton instance;
    private int width;
    private int height;
    private int fps;
    private boolean fullscreen;
    private boolean vSync;

    private GameConfigurationSingleton() {
        this.width = 800;
        this.height = 600;
        this.fps = 60;
        this.fullscreen = false;
        this.vSync = true;
    }

    public static GameConfigurationSingleton getInstance() {
        if (instance == null) {
            instance = new GameConfigurationSingleton();
        }
        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFps() {
        return fps;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public boolean isvSync() {
        return vSync;
    }
}
