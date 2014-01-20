package br.com.johnnatan.places;

import java.util.ArrayList;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.bing.BingMapTileSource;
import org.osmdroid.views.MapController;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import br.com.johnnatan.R;
import br.com.johnnatan.places.util.MapView;
import br.com.johnnatan.places.util.MyPoint;
import br.com.johnnatan.places.util.ResourceProxyImpl;

public class PlacesMapOsmDroidActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	private static String TAG = "PlacesMapOsmDroidActivity";
	private MapView mapView;
	private MapController mapController;
	private static double latitude = -20.39711, longitude = -43.50906;
	private MyLocationOverlay myLocationOverlay;
	private BingMapTileSource bms;
	private ItemizedOverlay<OverlayItem> itemizedOverlay;
	private ResourceProxy resourceProxy;
	private boolean isAerial = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_main_map_osmdroid);
		resourceProxy = new ResourceProxyImpl(getApplicationContext());
		bms = new BingMapTileSource(null);
		// bms.setStyle(BingMapTileSource.IMAGERYSET_AERIAL);
		if (BingMapTileSource.getBingKey().length() == 0) {
			BingMapTileSource.retrieveBingKey(getApplicationContext());
		}

		if (!TileSourceFactory.containsTileSource(bms.name())) {
			TileSourceFactory.addTileSource(bms);
		}

		MyPoint point = new MyPoint(latitude, longitude);

		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		mapView.setClickable(true);
		mapView.setMultiTouchControls(true);
		mapView.setDrawingCacheEnabled(true);

		mapView.setTileSource(TileSourceFactory.getTileSource(bms.name()));

		mapController = mapView.getController();
		mapController.setZoom(17);
		mapController.setCenter(point);

		myLocationOverlay = new MyLocationOverlay(this, mapView, resourceProxy);

		myLocationOverlay.enableCompass();
		myLocationOverlay.enableMyLocation();

		ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
		items.add(new OverlayItem("Johnnatan", "Está na UFOP", point));

		itemizedOverlay = new ItemizedIconOverlay<OverlayItem>(items,
				new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
					@Override
					public boolean onItemLongPress(int index, OverlayItem item) {
						// TODO Auto-generated method stub
						Toast.makeText(
								PlacesMapOsmDroidActivity.this,
								"onItemLongPress Title: " + item.mTitle + " "
										+ "Desc: " + item.mDescription
										+ " index: " + index,
								Toast.LENGTH_SHORT).show();
						return true;
					}

					@Override
					public boolean onItemSingleTapUp(int index, OverlayItem item) {
						// TODO Auto-generated method stub
						Toast.makeText(
								PlacesMapOsmDroidActivity.this,
								"ItemSingleTapUp Title: " + item.mTitle + " "
										+ "Desc: " + item.mDescription
										+ " index: " + index,
								Toast.LENGTH_SHORT).show();
						return true;
					}

				}, resourceProxy);

		mapView.getOverlays().add(myLocationOverlay);
		mapView.getOverlays().add(itemizedOverlay);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		myLocationOverlay.disableCompass();
		myLocationOverlay.disableMyLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.ui_place_map, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case R.id.place_menu_map:
			if (isAerial) {
				bms.setStyle(BingMapTileSource.IMAGERYSET_ROAD);
				isAerial = false;
				Toast.makeText(this, "Mapa normal", Toast.LENGTH_SHORT).show();
			} else {
				bms.setStyle(BingMapTileSource.IMAGERYSET_AERIAL);
				isAerial = true;
				Toast.makeText(this, "Mapa Satélite", Toast.LENGTH_SHORT)
						.show();
			}

			break;

		}
		return super.onOptionsItemSelected(item);

	}

}
