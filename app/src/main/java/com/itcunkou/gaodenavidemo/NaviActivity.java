package com.itcunkou.gaodenavidemo;

import android.app.Activity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.UiSettings;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.tbt.TrafficFacilityInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JLin
 * @date 2019/4/26
 */
public class NaviActivity extends Activity implements AMapNaviListener, AMapNaviViewListener {
    AMapNaviView mAMapNaviView = null;
    AMapNavi amapNavi = null;

    private double startLng;
    private double startLat;
    private double endLng;
    private double endLat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            startLng = bundle.getDouble("start_lng");
            startLat = bundle.getDouble("start_lat");
            endLng = bundle.getDouble("end_lng");
            endLat = bundle.getDouble("end_lat");
        }

        mAMapNaviView = (AMapNaviView) findViewById(R.id.map);
        mAMapNaviView.setAMapNaviViewListener(this);
        mAMapNaviView.onCreate(savedInstanceState);

        AMap aMap = mAMapNaviView.getMap();
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setLogoBottomMargin(-50);

        amapNavi = AMapNavi.getInstance(this);
        amapNavi.addAMapNaviListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        amapNavi.resumeNavi();
        mAMapNaviView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        amapNavi.stopNavi();
    }

    @Override
    protected void onPause() {
        super.onPause();
        amapNavi.pauseNavi();
        mAMapNaviView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("----");
        amapNavi.destroy();
        mAMapNaviView.onDestroy();
    }

    @Override
    public void onNaviSetting() {

    }

    @Override
    public void onNaviCancel() {
        System.out.println("TestActivity:" + "onNaviCancel" + "----");
        finish();
    }

    @Override
    public boolean onNaviBackClick() {
        System.out.println("TestActivity:" + "onNaviBackClick" + "----");
        return false;
    }

    @Override
    public void onNaviMapMode(int i) {

    }

    @Override
    public void onNaviTurnClick() {

    }

    @Override
    public void onNextRoadClick() {

    }

    @Override
    public void onScanViewButtonClick() {

    }

    @Override
    public void onLockMap(boolean b) {

    }

    @Override
    public void onNaviViewLoaded() {

    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onInitNaviSuccess() {
        System.out.println("TestActivity:" + "onInitNaviSuccess" + "----");
        int strategy = 0;
        try {
            strategy = amapNavi.strategyConvert(true, false, false, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<NaviLatLng> start = new ArrayList<>();
        NaviLatLng naviLatLng1 = new NaviLatLng(startLat, startLng);
        start.add(naviLatLng1);

        List<NaviLatLng> end = new ArrayList<>();
        NaviLatLng naviLatLng2 = new NaviLatLng(endLat, endLng);
        end.add(naviLatLng2);

        amapNavi.calculateDriveRoute(start, end, null, strategy);
        amapNavi.setUseInnerVoice(true);
    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onGetNavigationText(int i, String s) {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }

    @Override
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

    }

    @Override
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showModeCross(AMapModelCross aMapModelCross) {

    }

    @Override
    public void hideModeCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {
        //模拟导航
        System.out.println("TestActivity:" + "onCalculateRouteSuccess" + "----");
        amapNavi.startNavi(NaviType.EMULATOR);
    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }

    @Override
    public void onPlayRing(int i) {

    }
}
