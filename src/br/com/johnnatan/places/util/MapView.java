package br.com.johnnatan.places.util;

import android.content.Context;
import android.util.AttributeSet;

public class MapView extends org.osmdroid.views.MapView {

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public int getMinZoomLevel() {
		return 3;
	}

	@Override
	public int getMaxZoomLevel() {
		return 19;
	}

}
