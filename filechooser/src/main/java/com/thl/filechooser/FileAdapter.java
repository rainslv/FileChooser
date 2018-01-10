package com.thl.filechooser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.thl.filechooser.FileChooser.*;


public class FileAdapter extends CommonAdapter<FileInfo> {

    private int sign = -1;
    private int chooseType = 0;

    public FileAdapter(Context context, ArrayList<FileInfo> dataList, int resId, int chooseType) {
        super(context, dataList, resId);
        this.chooseType = chooseType;
    }

    @Override
    public void bindView(RecyclerView.ViewHolder holder, FileInfo data, final int position) {
        TextView textView = (TextView) holder.itemView.findViewById(R.id.fileName);
        TextView textTime = (TextView) holder.itemView.findViewById(R.id.fileTime);
        textView.setText(data.getFileName());
        textTime.setText(data.getCreateTime());

        ImageView imageView = (ImageView) holder.itemView.findViewById(R.id.fileIcon);
        View divider = holder.itemView.findViewById(R.id.divider);
        if (FileInfo.FILE_TYPE_VIDEO.equals(data.getFileType())) {
            imageView.setImageResource(R.drawable.format_video);
        } else if (FileInfo.FILE_TYPE_AUDIO.equals(data.getFileType())) {
            imageView.setImageResource(R.drawable.format_music);
        } else if (FileInfo.FILE_TYPE_APK.equals(data.getFileType())) {
            imageView.setImageResource(R.drawable.format_app);
        } else if (FileInfo.FILE_TYPE_ZIP.equals(data.getFileType()) || FileInfo.FILE_TYPE_RAR.equals(data.getFileType())) {
            imageView.setImageResource(R.drawable.format_compress);
        } else if (FileInfo.FILE_TYPE_JPEG.equals(data.getFileType())
                || FileInfo.FILE_TYPE_JPG.equals(data.getFileType())
                || FileInfo.FILE_TYPE_PNG.equals(data.getFileType())) {
            imageView.setImageResource(R.drawable.format_picture);
        } else if (FileInfo.FILE_TYPE_FOLDER.equals(data.getFileType())) {
            imageView.setImageResource(R.drawable.format_folder);
        } else {
            imageView.setImageResource(R.drawable.format_other);
        }
        if (position != dataList.size() - 1) {
            divider.setVisibility(View.VISIBLE);
        } else {
            divider.setVisibility(View.GONE);
        }

        ImageView fileChoose = (ImageView) holder.itemView.findViewById(R.id.fileChoose);
        if (sign == position) {
            fileChoose.setImageResource(R.drawable.log_choose_checkbox_on);
        } else {
            fileChoose.setImageResource(R.drawable.log_choose_checkbox_off);
        }
        boolean folder = data.isFolder();
        switch (chooseType) {
            case 0:
                fileChoose.setVisibility(View.VISIBLE);
                break;
            case 1:
                if (folder) {
                    fileChoose.setVisibility(View.VISIBLE);
                } else {
                    fileChoose.setVisibility(View.GONE);
                }
                break;
            case 2:
                if (!folder) {
                    fileChoose.setVisibility(View.VISIBLE);
                } else {
                    fileChoose.setVisibility(View.GONE);
                }
                break;
            default:
                fileChoose.setVisibility(View.VISIBLE);
                break;
        }

        fileChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyData(position);
            }
        });
    }

    public int getSign() {
        return sign;
    }

    public String getChooseFilePath() {
        FileInfo fileInfo = dataList.get(sign);
        return fileInfo.getFilePath();
    }

    public void notifyData() {
        FileAdapter.this.sign = -1;
        notifyDataSetChanged();
    }

    public void notifyData(int position) {
        if (FileAdapter.this.sign == position) {
            FileAdapter.this.sign = -1;
        } else {
            FileAdapter.this.sign = position;
        }
        notifyDataSetChanged();
    }

}