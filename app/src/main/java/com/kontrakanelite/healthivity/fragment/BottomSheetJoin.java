package com.kontrakanelite.healthivity.fragment;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.kontrakanelite.healthivity.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetJoin extends BottomSheetDialogFragment {

    ImageView ivDown;
    Button btnSendRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_join, container, false);

        btnSendRequest = (Button) view.findViewById(R.id.btnSendRequest);

        ivDown = (ImageView) view.findViewById(R.id.ivDown);
        ivDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

}
