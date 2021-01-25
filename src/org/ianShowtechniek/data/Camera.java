package org.ianShowtechniek.data;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.ianShowtechniek.util.Util.integerTo2Hex;
import static org.ianShowtechniek.util.Util.integerTo4Hex;

public class Camera {
    public static int CAMERA_TIMEOUT = 1000;
    public static String CAMERA_POSITION = "810106020F0F";
    public static String CAMERA_ZOOM = "81010447";
    public static String CAMERA_FOCUS = "8010448";
    public static String CAMERA_IRIS = " 8101044B0000";
    public static String CAMERA_SHUTTER = "8101044A0000";
    public static String CAMERA_END = "81";

    private String ip;
    private int port;

    private int address = 1;

    private transient Socket socket;
    private transient BufferedOutputStream outputStream;

    public Camera(String ip, int port, int address) {
        this.ip = ip;
        this.port = port;
        this.address = address;
    }

    public boolean connect() {
        socket = new Socket();
        try {
            socket.setSoTimeout(CAMERA_TIMEOUT);
            socket.setTcpNoDelay(true);
            socket.connect(new InetSocketAddress(ip, port));
            outputStream = new BufferedOutputStream(socket.getOutputStream());
            return socket.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendPanTilt(int pan, int tilt) {
        sendCommand(CAMERA_POSITION + integerTo4Hex(pan) + integerTo4Hex(tilt) + CAMERA_END);
    }

    public void sendZoom(int zoom) {
        sendCommand(CAMERA_ZOOM + integerTo4Hex(zoom) + CAMERA_END);
    }

    public void sendFocus(int focus) {
        sendCommand(CAMERA_FOCUS + integerTo4Hex(focus) + CAMERA_END);
    }

    public void sendShutter(int shutter) {
        sendCommand(CAMERA_SHUTTER + integerTo2Hex(shutter) + CAMERA_END);
    }

    public void sendIris(int iris) {
        sendCommand(CAMERA_SHUTTER + integerTo2Hex(iris) + CAMERA_END);
    }

    private void sendCommand(String command) {
        byte[] binaryCommand = DatatypeConverter.parseHexBinary(command);
        try {
            outputStream.write(binaryCommand);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<editor-fold desc="Getters & Setters">
    public String getIp() {
        return ip;
    }

    public Camera setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public int getPort() {
        return port;
    }

    public Camera setPort(int port) {
        this.port = port;
        return this;
    }

    public int getAddress() {
        return address;
    }

    public Camera setAddress(int address) {
        this.address = address;
        return this;
    }
    //</editor-fold>
}
