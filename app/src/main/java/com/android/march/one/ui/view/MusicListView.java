package com.android.march.one.ui.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;

import com.android.march.one.One;
import com.android.march.one.OneUtils;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.adapter.RecyclerViewAdapter;
import com.android.march.one.adapter.TypeFactory;
import com.android.march.one.model.bean.MusicAlbumBean;
import com.android.march.one.model.bean.MusicArtistBean;
import com.android.march.one.model.bean.MusicSongBean;
import com.android.march.one.presenter.contract.MusicListContract;
import com.android.march.one.ui.DividerDecoration;
import com.android.march.one.utils.MusicUtils;
import com.android.march.one.utils.SortOrder;

import java.util.List;

import butterknife.BindView;

public class MusicListView extends RootView<MusicListContract.Presenter> implements MusicListContract.View {

    private final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private final int REQUEST_PERMISSIONS = 1;
    private final int REQUEST_PERMISSION_SETTING = 2;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Fragment mFragment = null;
    private int type;

    private Activity mActivity = null;

    private long artistId;
    private long albumId;
    private String folderPath;
    private String sortOrder;

    public MusicListView(Context context) {
        super(context);
    }

    public MusicListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_music_list;
    }

    // 在 Fragment 中使用
    public void setData(Fragment fragment, int type) {
        this.mFragment = fragment;
        this.type = type;
        checkSelfPermission();
    }

    // 在 Activity 中使用
    public void setData(Activity activity, long artistId, long albumId, String folderPath) {
        this.mActivity = activity;
        this.artistId = artistId;
        this.albumId = albumId;
        this.folderPath = folderPath;
        checkSelfPermission();
    }

    // 验证权限
    private void checkSelfPermission() {
        if (mFragment == null && mActivity == null) return;

        if (One.isMarshmallow()) {
            // 遍历权限数组
            for (String permission : PERMISSIONS) {
                if (!One.checkSelfPermission(permission)) {
                    // 当权限申请被拒绝,并且shouldShowRequestPermissionRationale(permission)返回true,应该显示请求权限的理由
                    if (One.shouldShowRequestPermissionRationale(activity, permission)) {
                        // 显示提示对话框( 授权读取外部存储器来显示设备上的歌曲 )
                        showPromptDialog();
                    } else {
                        // 直接请求权限
                        requestPermissions();
                    }
                    // 只要其中一个没有授权,就结束循环
                    return;
                }
            }
        }
        getData(true);
    }

    // 请求权限
    private void requestPermissions() {
        // 这里目前只有两种情况,不可能 fragment 和 activity 都为空
        if (mFragment != null) {
            One.requestPermissions2(mFragment, PERMISSIONS, REQUEST_PERMISSIONS);
        } else {
            One.requestPermissions(mActivity, PERMISSIONS, REQUEST_PERMISSIONS);
        }
    }

    // 权限请求结果回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS) {
            for (int i = 0; i < grantResults.length; i++) {
                // 判断权限的结果,如果有被拒绝,就return
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (One.shouldShowRequestPermissionRationale(activity, permissions[i])) {
                        Toast.makeText(getContext(), R.string.no_authorization_to_read_external_storage, Toast.LENGTH_SHORT).show();
                    } else {
                        // 当权限申请被拒绝,并且shouldShowRequestPermissionRationale(permission)返回false
                        // 在此就表示用户勾选了不再询问,应该提示用户去设置界面打开相关权限
                        // 显示提示对话框( 去设置界面授权读取外部存储器来显示设备上的歌曲 )
                        showDetailSettingDialog();
                    }
                    return;
                }
            }
            getData(true);// 所有权限都通过
        }
    }

    // 显示授权读取外部存储以在您的设备上显示歌曲对话框
    private void showPromptDialog() {
        new AlertDialog.Builder(activity)
                .setMessage(getString(R.string.read_external_storage))
                .setPositiveButton(R.string.ok, (dialog, i) -> {
                    dialog.dismiss();
                    // 确定,请求权限
                    requestPermissions();
                })
                .setNegativeButton(R.string.cancel, (dialog, i) -> {
                    dialog.dismiss();
                    // 取消,没有权限
                    Toast.makeText(activity, R.string.no_authorization_to_read_external_storage, Toast.LENGTH_SHORT).show();
                })
                .setCancelable(false)
                .create().show();
    }

    // 显示去应用程序详情设置,授权读取外部存储以在设备上显示歌曲对话框
    private void showDetailSettingDialog() {
        new AlertDialog.Builder(activity)
                .setMessage(getString(R.string.to_application_details_settings))
                .setPositiveButton(R.string.ok, (dialog, i) -> {
                    dialog.dismiss();
                    // 确定,打开该应用详情设置界面
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                    intent.setData(uri);
                    activity.startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                })
                .setNegativeButton(R.string.cancel, (dialog, i) -> {
                    dialog.dismiss();
                    // 取消,没有权限
                    Toast.makeText(activity, R.string.no_authorization_to_read_external_storage, Toast.LENGTH_SHORT).show();
                })
                .setCancelable(false)
                .create().show();
    }

    // 去设置界面授权之后,返回当前页面回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            // 还需要对权限进行判断,遍历权限数组
            for (String permission : PERMISSIONS) {
                if (!One.checkSelfPermission(permission)) {
                    Toast.makeText(activity, R.string.no_authorization_to_read_external_storage, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            getData(true);// 所有权限都通过
        }
    }

    // 获取数据
    private void getData(boolean isFirst) {
        switch (type) {
            case MusicUtils.TYPE_SONG:
                if (!isFirst) sortOrder = SortOrder.SongSortOrder.SONG_A_Z;
                presenter.getSongList(activity, artistId, albumId, folderPath, sortOrder);
                break;
            case MusicUtils.TYPE_ARTIST:
                if (!isFirst) sortOrder = SortOrder.ArtistSortOrder.ARTIST_A_Z;
                presenter.getArtistList(activity, sortOrder);
                break;
            case MusicUtils.TYPE_ALBUM:
                if (!isFirst) sortOrder = SortOrder.AlbumSortOrder.ALBUM_A_Z;
                presenter.getAlbumList(activity, sortOrder);
                break;
            case MusicUtils.TYPE_FOLDER:
                presenter.getFolderList(activity);
                break;
        }
    }

    public void sortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
        getData(false);
    }

    // 设置 recyclerView 属性
    private void setRecyclerView() {
        OneUtils.setRecyclerView(getContext(), recyclerView);
        recyclerView.addItemDecoration(new DividerDecoration());
    }

    @Override
    public void setSongList(List<MusicSongBean> musicSongBeanList) {
        setRecyclerView();
        recyclerView.setAdapter(new RecyclerViewAdapter<>(musicSongBeanList, new TypeFactory().setMusicListData(activity, type, null)));
    }

    @Override
    public void setArtistList(List<MusicArtistBean> musicArtistBeanList) {
        setRecyclerView();
        recyclerView.setAdapter(new RecyclerViewAdapter<>(musicArtistBeanList, new TypeFactory().setMusicListData(activity, type, null)));
    }

    @Override
    public void setAlbumList(List<MusicAlbumBean> musicAlbumBeanList) {
        setRecyclerView();
        recyclerView.setAdapter(new RecyclerViewAdapter<>(musicAlbumBeanList, new TypeFactory().setMusicListData(activity, type, null)));
    }

    @Override
    public void setFolderList(List<String> folderList, List<Integer> integers) {
        setRecyclerView();
        recyclerView.setAdapter(new RecyclerViewAdapter<>(folderList, new TypeFactory().setMusicListData(activity, type, integers)));
    }
}