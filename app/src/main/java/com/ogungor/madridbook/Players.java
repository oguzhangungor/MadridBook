package com.ogungor.madridbook;

public class Players {

    private String name;
    private String position;
    private String uniformNumber;
    private byte [] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Players(String name, String position, String uniformNumber, byte[] image) {
        this.name = name;
        this.position = position;
        this.uniformNumber = uniformNumber;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUniformNumber() {
        return uniformNumber;
    }

    public void setUniformNumber(String uniformNumber) {
        this.uniformNumber = uniformNumber;
    }


}
