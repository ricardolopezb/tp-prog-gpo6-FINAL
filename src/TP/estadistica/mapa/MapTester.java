package TP.estadistica.mapa;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.utils.ScreenPosition;
import processing.core.PApplet;
import processing.core.PGraphics;


public class MapTester extends PApplet {

    UnfoldingMap map;

    public static void main(String[] args) {
        PApplet.main(MapTester.class.getName());
    }

    @Override
    public void settings(){
        size(800, 600);
        map = new UnfoldingMap(this, new OpenStreetMap.OSMGrayProvider());
        MapUtils.createDefaultEventDispatcher(this, map);

    }

    @Override
    public void draw(){

        Location pilar = new Location(-34.4778621,-58.9091671);
        SimplePointMarker pilarMarker = new SimplePointMarker(pilar);
        pilarMarker.setColor(color(255,0,0,100));
        pilarMarker.setRadius(6);


        map.addMarker(pilarMarker);
        map.draw();

        //ScreenPosition pilarPos = pilarMarker.getScreenPosition(map);
        //strokeWeight(16);
        //stroke(67, 211, 227, 100);
        //noFill();
        //ellipse(pilarPos.x, pilarPos.y, 36, 36);

    }







}
