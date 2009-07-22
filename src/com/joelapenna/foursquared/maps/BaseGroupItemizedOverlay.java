/**
 * Copyright 2009 Joe LaPenna
 */

package com.joelapenna.foursquared.maps;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.joelapenna.foursquare.types.Group;
import com.joelapenna.foursquared.Foursquared;

import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * @author Joe LaPenna (joe@joelapenna.com)
 */
abstract class BaseGroupItemizedOverlay extends ItemizedOverlay<OverlayItem> {
    public static final String TAG = "BaseGroupItemizedOverlay";
    public static final boolean DEBUG = Foursquared.DEBUG;

    Group group = null;

    public BaseGroupItemizedOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
    }

    @Override
    public int size() {
        return group.size();
    }

    @Override
    public boolean onTap(GeoPoint p, MapView mapView) {
        if (DEBUG) Log.d(TAG, "onTap: " + group.getType() + " " + p);
        mapView.getController().animateTo(p);
        return super.onTap(p, mapView);
    }

    @Override
    protected boolean onTap(int i) {
        if (DEBUG) Log.d(TAG, "onTap: " + group.getType() + " " + i);
        return super.onTap(i);
    }

    public void setGroup(Group g) {
        assert g.getType() != null;
        group = g;
        super.populate();
    }
}