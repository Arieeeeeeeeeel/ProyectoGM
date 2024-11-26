package io.github.some_example_name;

public final class GameConfigurationSingleton {
    private static GameConfigurationSingleton instance;
    public int width;
    public int height;
    public int fps;
    public boolean fullscreen;
    public boolean vSync;

    private GameConfigurationSingleton() {
        this.width = 800;
        this.height = 600;
        this.fps = 30;
        this.fullscreen = false;
        this.vSync = true;
    }

    public static GameConfigurationSingleton getInstance() {
        if (instance == null) {
            instance = new GameConfigurationSingleton();
        }
        return instance;
    }
}
