package org.ianShowtechniek;

import org.ianShowtechniek.data.Camera;
import org.ianShowtechniek.data.Settings;
import org.ianShowtechniek.io.FileManager;
import org.ianShowtechniek.io.artnet.api.ArtNetBuffer;
import org.ianShowtechniek.io.artnet.api.ArtNetClient;
import org.ianShowtechniek.util.Util;
import org.ianShowtechniek.util.logger.LogLevel;
import org.ianShowtechniek.util.logger.Logger;
import org.ianShowtechniek.util.logger.SimpleLogger;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static final String VERSION = "0.1";
    public static final Logger logger = new SimpleLogger("DMX2VISCA", LogLevel.ALL);

    public static Settings settings;

    List<Camera> cameraList = new ArrayList<>();

    ArtNetClient artNetClient;
    ArtNetBuffer artNetBuffer;


    public App() {
        //will load the settings file
        settings = FileManager.loadFile();
        //load the camera's
        cameraList = FileManager.loadCameras();
        if (cameraList == null) {
            cameraList = new ArrayList<>();
            FileManager.saveCamera(cameraList);
        }
        cameraList = new ArrayList<>();

        cameraList.add(new Camera("192.168.5.162", 1259, 1));

        logger.info("Added " + cameraList.size() + " Camera's");
        artNetBuffer = new ArtNetBuffer();
        artNetClient = new ArtNetClient(artNetBuffer);

        artNetClient.start("192.168.5.3"); //todo add settingss

        init();


        Camera camera = cameraList.get(0);

        int pan = (int) Util.mapd(360, 0, 360, 0, 4884);

        int res = 0;

        if (pan > 2442) {
            res += (pan - 2442);
        }else{
            res =  65536 + (pan - 2442);
        }

        System.out.println("pan " + pan);
        System.out.println("tilt " + 0);
        System.out.println("res " + res);


        camera.sendPanTilt(res, 1);

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //camera.sendPanTilt(65536 - 2443, 1);


        //loop();

    }

    public void init() {
        for (Camera camera : cameraList) {
            boolean result = camera.connect();
            if (result) {
                logger.info(Logger.ANSI_GREEN + "Connected with camera " + camera.getIp());
            } else {
                logger.warning("Couldn't connect with camera " + camera.getIp());
            }
        }
    }

    public void loop() {
        double amountOfTicks = 60.0D; //todo change to settings
        double ns = 1.6666666666666666E7D;
        long time = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        long now = 0L;
        double delta = 0.0D;
        int tickCount = 0;
        int tickTotal = 0;

        while (true) {
            now = System.nanoTime();
            delta += (double) (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0D) {
                tick();
                ++tickTotal;
                ++tickCount;
                --delta;
            }

            if (System.currentTimeMillis() - time >= 1000L) {
                time += 1000L;
                if ((double) tickCount != 60.0D) {
                    if (tickCount < 5) {
                        logger.fatal("current tick count: " + tickCount + ".");
                    }

                    logger.debug(tickCount + " Ticks.");
                }

                tickCount = 0;
            }
        }
    }


    public void tick() {
        byte[] data = artNetClient.readDmxData(0, 0); //todo chnage this to settings
        for (Camera camera : cameraList) {
            int i = camera.getAddress();
            i--;
            camera.sendPanTilt(Util.get16bitValue(data[i], data[i + 1]), Util.get16bitValue(data[i + 2], data[i + 3]));
            //camera.sendZoom(Util.get16bitValue(data[i + 4], data[i + 5])); //todo add the other functions
        }


    }

    public static void main(String[] args) {
        logger.info(Logger.ANSI_GREEN + "Starting DMX2VISCA  V" + VERSION);
        new App();
    }
}
