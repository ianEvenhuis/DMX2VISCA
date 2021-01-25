package org.ianShowtechniek.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.ianShowtechniek.App;
import org.ianShowtechniek.data.Camera;
import org.ianShowtechniek.data.Settings;
import org.ianShowtechniek.util.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String settings_FileName = "Application_settings.json";
    private static final String camere_FileName = "cameras.json";
    private static final String file_Location = "/Data/Settings/";

    public static List<Camera> loadCameras() {
        try {
            File file = new File(Util.getJarContainingFolder() + file_Location + camere_FileName);
            String str = Files.readString(file.toPath());
            System.out.println("Loaded Cameras file");
            return new Gson().fromJson(str, new TypeToken<List<Camera>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveCamera(new ArrayList<>());
        return new ArrayList<>();
    }

    public static void saveCamera(List<Camera> cameraList) {
        try {
            FileWriter fileOut =
                    new FileWriter(Util.getJarContainingFolder() + file_Location + camere_FileName);
            fileOut.write(new GsonBuilder().setPrettyPrinting().create().toJson(cameraList));
            fileOut.flush();
            fileOut.close();
            System.out.println("Saved Camera file");
        } catch (IOException i) {
            App.logger.warning("Can't save the Camera file");
            i.printStackTrace();
        }
    }


    public static void saveFile(Settings settings) {
        try {
            FileWriter fileOut =
                    new FileWriter(Util.getJarContainingFolder() + file_Location + settings_FileName);
            fileOut.write(new GsonBuilder().setPrettyPrinting().create().toJson(settings));
            fileOut.flush();
            fileOut.close();
            System.out.println("Saved Setting file");
        } catch (IOException i) {
            App.logger.warning("Can't save the settings file");
            i.printStackTrace();
        }
    }

    public static Settings loadFile() {
        try {
            File file = new File(Util.getJarContainingFolder() + file_Location + settings_FileName);
            String str = Files.readString(file.toPath());
            System.out.println("Loaded Setting file");
            return new Gson().fromJson(str, Settings.class);
        } catch (IOException i) {
            i.printStackTrace();
            App.logger.warning("Settings don't exist creating them");
        }
        Settings settings = new Settings();
        saveFile(settings);
        return settings;
    }

}
