package com.lpf.tools.views;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lpf.tools.R;

/**
 * Created by liupengfei on 2018/8/6 15:27.
 */
public class LoadingDialog extends DialogFragment{

    private String msg = "Loading";

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_loading,null);
        TextView title = view.findViewById(R.id.id_dialog_loading_msg);
        title.setText(msg);
        Dialog dialog = new Dialog(getActivity(),R.style.loadingDialog);
        dialog.setContentView(view);
        return dialog;
    }
}
