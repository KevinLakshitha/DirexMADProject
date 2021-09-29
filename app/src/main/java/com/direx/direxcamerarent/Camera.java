package com.direx.direxcamerarent;

public class Camera {
    private String cameraID;
    private String cameratitle;
    private String cameraprice;
    private String camerades;

    public Camera() {
    }

    public Camera(String cameraID, String cameratitle, String cameraprice, String camerades) {
        this.cameraID = cameraID;
        this.cameratitle = cameratitle;
        this.cameraprice = cameraprice;
        this.camerades = camerades;
    }

    public String getcameraID() {
        return cameraID;
    }

    public void setCameraID(String cameraID) {
        this.cameraID = cameraID;
    }

    public String getCameratitle() {
        return cameratitle;
    }

    public void setCameratitle(String cameratitle) {
        this.cameratitle = cameratitle;
    }

    public String getCameraprice() {
        return cameraprice;
    }

    public void setCameraprice(String cameraprice) {
        this.cameraprice = cameraprice;
    }

    public String getCamerades() {
        return camerades;
    }

    public void setCamerades(String camerades) {
        this.camerades = camerades;
    }
}
