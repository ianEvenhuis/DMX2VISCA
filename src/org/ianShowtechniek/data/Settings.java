package org.ianShowtechniek.data;

public class Settings {

    private int visca_Port = 1259;
    private int ticksPerSecond = 40;
    private int artnet_Port = 6454;
    private int artnet_universe = 1;
    private int artnet_subnet = 1;


    //<editor-fold desc="Getters & Setters">
    public int getVisca_Port() {
        return visca_Port;
    }

    public Settings setVisca_Port(int visca_Port) {
        this.visca_Port = visca_Port;
        return this;
    }

    public int getTicksPerSecond() {
        return ticksPerSecond;
    }

    public Settings setTicksPerSecond(int ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
        return this;
    }

    public int getArtnet_Port() {
        return artnet_Port;
    }

    public Settings setArtnet_Port(int artnet_Port) {
        this.artnet_Port = artnet_Port;
        return this;
    }

    public int getArtnet_universe() {
        return artnet_universe;
    }

    public Settings setArtnet_universe(int artnet_universe) {
        this.artnet_universe = artnet_universe;
        return this;
    }

    public int getArtnet_subnet() {
        return artnet_subnet;
    }

    public Settings setArtnet_subnet(int artnet_subnet) {
        this.artnet_subnet = artnet_subnet;
        return this;
    }
    //</editor-fold>
}
