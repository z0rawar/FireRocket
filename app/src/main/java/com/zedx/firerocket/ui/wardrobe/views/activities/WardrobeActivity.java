package com.zedx.firerocket.ui.wardrobe.views.activities;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.zedx.firerocket.R;
import com.zedx.firerocket.ui.base.views.activities.BaseFireActivity;
import com.zedx.firerocket.ui.services.AlarmReceiver;
import com.zedx.firerocket.ui.utils.ImageUploaderUtil;
import com.zedx.firerocket.ui.wardrobe.constants.WardrobeConstants;
import com.zedx.firerocket.ui.wardrobe.presenters.WardrobePresenter;
import com.zedx.firerocket.ui.wardrobe.presenters.WardrobePresenterImpl;
import com.zedx.firerocket.ui.wardrobe.views.fragments.ImagePickerDialogFragment;
import com.zedx.firerocket.ui.wardrobe.views.interactors.ImagePickerDialogFragmentInteractor;
import com.zedx.firerocket.ui.wardrobe.views.interactors.WardrobeDelegateInteractor;
import com.zedx.firerocket.ui.wardrobe.views.interactors.WardrobeViewListener;
import com.zedx.firerocket.ui.wardrobe.views.viewDelegates.WardrobeViewDelegate;

import java.net.URISyntaxException;
import java.util.Calendar;

import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class WardrobeActivity extends BaseFireActivity implements WardrobeDelegateInteractor, WardrobeViewListener, ImagePickerDialogFragmentInteractor {


    WardrobeViewDelegate wardrobeViewDelegate;

    WardrobePresenter wardrobePresenter;

    private int category;
    private Uri capturedImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wardrobeViewDelegate = new WardrobeViewDelegate(this, this);
        wardrobePresenter = new WardrobePresenterImpl(this);
        wardrobePresenter.initDaggerInjection();
        wardrobeViewDelegate.onViewInit(wardrobePresenter.findAllShirts(), wardrobePresenter.findAllPants());
        setAlarmForNotification();
        checkSource();
    }


    public int getLayoutId() {
        return R.layout.activity_wardrobe;
    }


    @Override
    public void onShuffleClick(int maxShirts, int maxPants) {
        wardrobePresenter.doShufflin(maxShirts, maxPants, false);
    }

    @Override
    public void onAddFavClick(int shirtPosition, int pantPosition) {
        wardrobePresenter.saveFav(shirtPosition, pantPosition);
    }

    @Override
    public void onAddClothes(int category) {
        this.category = category;
        new ImagePickerDialogFragment().show(getSupportFragmentManager(), ImagePickerDialogFragment.class.getSimpleName());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String path = null;
            try {
                switch (requestCode) {
                    case WardrobeConstants.CAMERA_REQ:
                        path = ImageUploaderUtil.getPath(this, capturedImageUri);
                        break;
                    case WardrobeConstants.GALLERY_REQ:
                        if (data != null && data.getData() != null) {
                            path = ImageUploaderUtil.getPath(this, data.getData());
                        }
                        //else no-op
                        break;
                }
            } catch (URISyntaxException e) {
                onSaveClothesFailure(e);
            }
            wardrobePresenter.saveClothes(path, category);

        }
    }

    @Override
    public void onSaveClothesSuccessful(int category, String path) {
        wardrobeViewDelegate.updateViewPager(category, path);
    }

    @Override
    public void onFavFailure() {
        showToast(getString(R.string.fav_failure));
    }

    @Override
    public void onFavSuccesful() {
        //no-op
    }

    @Override
    public void onSaveClothesFailure(Exception e) {
        if (e instanceof RealmPrimaryKeyConstraintException) {
            showToast(getString(R.string.save_failure_duplicate));
        } else if (e instanceof URISyntaxException) {
            showToast(getString(R.string.save_failure_image));
        }
    }

    @Override
    public void onShuffleSuccessful(Integer shirtPosition, Integer pantPosition) {
        wardrobeViewDelegate.setFragmentToViewPager(shirtPosition, pantPosition);
    }


    @Override
    public void startCameraIntent() {
        if (hasRequiredPermission(Manifest.permission.CAMERA)) {
            startCamera();
        } else {
            requestForPermissions(new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, WardrobeConstants.PERMISSION_CAMERA_REQUEST);
        }
    }

    private void startCamera() {
        Intent in = new Intent();
        in.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        capturedImageUri = ImageUploaderUtil.getCameraImageUri();
        in.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
        startActivityForResult(in, WardrobeConstants.CAMERA_REQ);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WardrobeConstants.PERMISSION_GALLERY_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startGallery();
                } else {
                    showToast(getString(R.string.gallery_req));
                }
            }
            break;
            case WardrobeConstants.PERMISSION_CAMERA_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    showToast(getString(R.string.camera_req));
                }
            }
            break;
        }
    }

    @Override
    public void startGalleryIntent() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    WardrobeConstants.PERMISSION_GALLERY_REQUEST);
        } else {
            startGallery();
        }
    }

    private void startGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, WardrobeConstants.GALLERY_REQ);
    }


    private void checkSource() {
        if (getIntent().getExtras() != null && getIntent().getExtras().get(WardrobeConstants.SOURCE) != null && getIntent().getExtras().getString(WardrobeConstants.SOURCE).equals(WardrobeConstants.NOTIFICATION)) {
            wardrobePresenter.doShufflin(wardrobeViewDelegate.getShirtsSize(), wardrobeViewDelegate.getPantsSize(), true);

        }
    }

    private void setAlarmForNotification() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        Intent in = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) this.getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }


}
