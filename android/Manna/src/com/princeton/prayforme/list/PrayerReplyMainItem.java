package com.princeton.prayforme.list;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.Security;
import com.princeton.prayforme.helper.SharedPrefsHelper;
import com.princeton.prayforme.model.Prayer;

public class PrayerReplyMainItem extends ListItem<Prayer> {

    private Activity context;

    public PrayerReplyMainItem(Activity context, Prayer model) {
        super(model);
        this.context = context;
    }

    @Override
    public ListItemType getType() {
        return ListItemType.PRAYERREPLYMAIN;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_prayerreplymain;
    }

    @Override
    public void populate(View view) {
        TextView author = (TextView) view.findViewById(R.id.prayer_author);
        author.setText(GlobalConstants.isNullOrEmpty(model.getPerson()) ? "Anonymous" : model.getPerson());
        TextView title = (TextView) view.findViewById(R.id.prayer_title);
        title.setText(model.getSubject());
        TextView text = (TextView) view.findViewById(R.id.prayer_text);
        text.setText(model.getMessage());

        ViewGroup signature = (ViewGroup) view.findViewById(R.id.prayer_image);
        signature.addView(Security.getColorSignature(context.getLayoutInflater(), signature, model.getSignature()));

        TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
        prayButton.setText(String.format("Prays(%d)", model.getTimesPrayedFor()));
        prayButton.setEnabled(!model.isPrayedFor());
        prayButton.setOnClickListener(prayOnClickListener);

        TextView timestamp = (TextView) view.findViewById(R.id.prayer_timestamp);
        timestamp.setText(String.format("Posted on %s", model.getTimestamp()));

//        String mySignature = new SharedPrefsHelper(context.getApplicationContext()).getSignature();
//        if (!GlobalConstants.isNullOrEmpty(mySignature) && (model.getSignature().equals(mySignature)) {
        if (true) {
            view.findViewById(R.id.prayer_author_buttons).setVisibility(View.VISIBLE);
            TextView editButton = (TextView) view.findViewById(R.id.prayer_btn_edit);
            editButton.setOnClickListener(editOnClickListener);
            TextView deleteButton = (TextView) view.findViewById(R.id.prayer_btn_delete);
            deleteButton.setOnClickListener(deleteOnClickListener);
        }
    }

    private final View.OnClickListener prayOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            model.incTimesPrayed();
            TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
            prayButton.setEnabled(false);
            model.setPrayedFor(true);
            prayButton.setText(String.format("Prays(%d)", model.getTimesPrayedFor()));
        }
    };

    private final View.OnClickListener editOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private final View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//        AlertDialog dialog = new AlertDialog.Builder(context)
//                .setCancelable(true)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialog.cancel();
//                    }
//                }).create();
//
//            dialog.show();
        }
    };

}
