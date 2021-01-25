package org.ianShowtechniek;

import org.ianShowtechniek.data.Camera;
import org.ianShowtechniek.data.Settings;
import org.ianShowtechniek.io.FileManager;
import org.ianShowtechniek.util.logger.LogLevel;
import org.ianShowtechniek.util.logger.Logger;
import org.ianShowtechniek.util.logger.SimpleLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static final String VERSION = "0.1";
    public static final Logger logger = new SimpleLogger("DMX2VISCA", LogLevel.ALL);

    public static Settings settings;

    List<Camera> cameraList = new ArrayList<>();


    public App() {
        //will load the settings file
        settings = FileManager.loadFile();
        //load the camera's
        cameraList = FileManager.loadCameras();
        if(cameraList == null){
            cameraList = new ArrayList<>();
            FileManager.saveCamera(cameraList);
        }

        cameraList.add(new Camera("192.168.5.161", settings.getVisca_Port(), 1));
        cameraList.add(new Camera("192.168.5.161", settings.getVisca_Port(), 1));
        cameraList.add(new Camera("192.168.5.161", settings.getVisca_Port(), 1));
        cameraList.add(new Camera("192.168.5.161", settings.getVisca_Port(), 1));
        cameraList.add(new Camera("192.168.5.161", settings.getVisca_Port(), 1));
        cameraList.add(new Camera("192.168.5.161", settings.getVisca_Port(), 1));

        FileManager.saveCamera(cameraList);



    }


    public void loop() {

    }

    public static void main(String[] args) {
        logger.info(Logger.ANSI_GREEN + "Starting DMX2VISCA  V" + VERSION);
        new App();
    }
}
