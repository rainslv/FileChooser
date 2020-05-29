package com.thl.filechooser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


public class FileChooser {

    private Context mContext;
    private FileChoosenListener fileChoosenListener;

    private int themeColorRes = -1;
    private String mChoosenFilePath = "";
    private String title = "选择文件";
    private String doneText = "完成";
    private int backIconRes = -1;
    private boolean showFile = true;
    private boolean showHideFile = false;
    private boolean chooseCurrentPath = false;//在没有勾选任何文件夹或文件的情况下，是否直接抓取当前目录

    public boolean isShowHideFile() {
        return showHideFile;
    }

    public FileChooser setShowHideFile(boolean showHideFile) {
        this.showHideFile = showHideFile;
        return this;
    }

    private String chooseType = FileInfo.FILE_TYPE_ALL; //显示选择文件类型

    public String getChooseType() {
        return chooseType;
    }

    public void setChooseType(String chooseType) {
        this.chooseType = chooseType;
    }

    public boolean isFileShow() {
        return showFile;
    }

    public FileChooser showFile(boolean showFile) {
        this.showFile = showFile;
        return this;
    }

    public FileChooser setCurrentPath(String currentPath) {
        this.mChoosenFilePath = currentPath;
        return this;
    }

    public FileChooser setTitle(String title) {
        this.title = title;
        return this;
    }

    public FileChooser setDoneText(String doneText) {
        this.doneText = doneText;
        return this;
    }

    public FileChooser setBackIconRes(int backIconRes) {
        this.backIconRes = backIconRes;
        return this;
    }

    public FileChooser(Fragment fragment, FileChoosenListener fileChoosenListener) {
        this.mContext = fragment.getContext();
        this.fileChoosenListener = fileChoosenListener;
    }

    public FileChooser(Activity activity, FileChoosenListener fileChoosenListener) {
        this.mContext = activity;
        this.fileChoosenListener = fileChoosenListener;
    }

    public void open() {
        FileChooserActivity.mFileChooser = this;
        Intent intent = new Intent(mContext, FileChooserActivity.class);
        intent.putExtra("themeColorRes", this.themeColorRes);
        intent.putExtra("showHideFile", this.showHideFile);
        intent.putExtra("currentPath", this.mChoosenFilePath);
        intent.putExtra("title", this.title);
        intent.putExtra("doneText", this.doneText);
        intent.putExtra("backIconRes", this.backIconRes);
        intent.putExtra("chooseType", this.chooseType);
        intent.putExtra("showFile", this.showFile);
        intent.putExtra("chooseCurrentPath", this.chooseCurrentPath);
        this.mContext.startActivity(intent);
    }

    protected void finish(String filePath) {
        if (fileChoosenListener != null)
            fileChoosenListener.onFileChoosen(filePath);
    }

    public FileChooser setThemeColor(int themeColorRes) {
        this.themeColorRes = themeColorRes;
        return this;
    }

    public FileChooser setFileChoosenListener(FileChoosenListener fileChoosenListener) {
        this.fileChoosenListener = fileChoosenListener;
        return this;
    }

    public boolean isChooseCurrentPath() {
        return chooseCurrentPath;
    }

    public FileChooser setChooseCurrentPath(boolean chooseCurrentPath) {
        this.chooseCurrentPath = chooseCurrentPath;
        return this;
    }

    public interface FileChoosenListener {
        void onFileChoosen(String filePath);
    }
}
