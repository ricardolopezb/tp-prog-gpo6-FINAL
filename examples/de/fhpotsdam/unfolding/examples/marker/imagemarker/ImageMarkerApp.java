package de.fhpotsdam.unfolding.examples.marker.imagemarker;

import TP.estadistica.mapa.InfoMapa;
import de.fhpotsdam.unfolding.examples.marker.labelmarker.LabeledMarker;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.MapUtils;

import java.util.ArrayList;

/**
 * Demonstrates how to use ImageMarkers with different icons. Note, the used icons contain translucent (the shadows) and
 * transparent (the inner holes) areas.
 */
public class ImageMarkerApp extends PApplet {

	InfoMapa infoMapa = new InfoMapa();
	ArrayList<Location> coordAMarcar = infoMapa.searchLocation();



	UnfoldingMap map;

	public void settings() {
		size(800, 600, P2D);
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { ImageMarkerApp.class.getName() });
	}

	public void setup() {
		map = new UnfoldingMap(this);
		map.zoomAndPanTo(4, new Location(-34f, -58f)); //poner bs as
		MapUtils.createDefaultEventDispatcher(this, map);

		for (Location location : coordAMarcar) {
			ImageMarker imgMarker = new ImageMarker(location, loadImage("ui/marker_red.png"));
			LabeledMarker labeledMarker = new LabeledMarker(location, "droga");
			map.addMarker(imgMarker);
			map.addMarker(labeledMarker);

		}

	}

	public void draw() {
		map.draw();
	}

}
