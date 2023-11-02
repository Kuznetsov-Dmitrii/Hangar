package com.example.hangar.DTO;

public class JsonDistanceDTO {
    private Points[] points;
    private int[] sources;
    private int[] targets;


    public JsonDistanceDTO(Points[] points, int[] sources, int[] targets) {
        this.points = points;
        this.sources = sources;
        this.targets = targets;
    }

    public Points[] getPoints() {
        return points;
    }

    public void setPoints(Points[] points) {
        this.points = points;
    }

    public int[] getSources() {
        return sources;
    }

    public void setSources(int[] sources) {
        this.sources = sources;
    }

    public int[] getTargets() {
        return targets;
    }

    public void setTargets(int[] targets) {
        this.targets = targets;
    }
}
