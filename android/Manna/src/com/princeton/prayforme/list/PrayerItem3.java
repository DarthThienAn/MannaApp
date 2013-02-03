package com.princeton.prayforme.list;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.Security;
import com.princeton.prayforme.activity.PrayerRepliesActivity;
import com.princeton.prayforme.activity.PrayerReplyActivity;
import com.princeton.prayforme.activity.RepliesActivity;
import com.princeton.prayforme.asynctask.AsyncPut;
import com.princeton.prayforme.helper.SharedPrefsHelper;
import com.princeton.prayforme.helper.URLHelper;
import com.princeton.prayforme.model.Prayer;

public class PrayerItem3 extends ListItem<Prayer> {
    private Context context;
    ProgressDialog loadingDialog;

    public PrayerItem3(Context context, Prayer model) {
        super(model);
        this.context = context;
    }

    @Override
    public ListItemType getType() {
        return ListItemType.PRAYER3;
    }

    @Override
    public int getResourceId() {
        return R.layout.item_prayer3;
    }

    @Override
    public void populate(View view) {
//        GlobalConstants.log("PrayerItem", model);
        TextView title = (TextView) view.findViewById(R.id.prayer_title);
        title.setText(model.getSubject());
        TextView text = (TextView) view.findViewById(R.id.prayer_text);
        text.setText(model.getMessage());

        TextView commentButton = (TextView) view.findViewById(R.id.prayer_btn_reply);
        commentButton.setText(String.format("Replies(%d)", model.getTimesReplied()));
        commentButton.setOnClickListener(repliesOnClickListener);

        final ViewGroup signature = (ViewGroup) view.findViewById(R.id.signature);
        signature.addView(Security.getColorSignatureMini(((Activity) context).getLayoutInflater(), signature, model.getSignature()));

        TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
        prayButton.setText(String.format("Prays(%d)", model.getTimesPrayedFor()));
        prayButton.setEnabled(!model.isPrayedFor());

        prayButton.setOnClickListener(prayOnClickListener);

        TextView timestamp = (TextView) view.findViewById(R.id.prayer_timestamp);
        timestamp.setText(String.format("Posted on %s", model.getTimestamp()));

        TextView author = (TextView) view.findViewById(R.id.prayer_author);
        author.setText(model.getPerson());
    }

    private final View.OnClickListener repliesOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context.getApplicationContext(), PrayerReplyActivity.class);
            intent.putExtra(GlobalConstants.KEY_PRAYER, model);
            context.startActivity(intent);
//                TextView commentButton = (TextView) view.findViewById(R.id.prayer_btn_comment);
//                commentButton.setText("Comments(X)");
        }
    };

    private final View.OnClickListener prayOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            model.incTimesPrayed();
            TextView prayButton = (TextView) view.findViewById(R.id.prayer_btn_pray);
            prayButton.setEnabled(false);
            model.setPrayedFor(true);
            prayButton.setText(String.format("Prays(%d)", model.getTimesPrayedFor()));

            AsyncPut task = new AsyncPut(URLHelper.prayerURL(), URLHelper.postPrayer(model.getId(), model.getSignature(), model.getPerson(), model.getSubject(), model.getMessage(), model.getTimesPrayedFor())) {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loadingDialog = ProgressDialog.show(context, "",
                            "Loading. Please wait...", true);
                }

                @Override
                protected void onPostExecute(Void s) {
                    super.onPostExecute(s);
                    GlobalConstants.log("Prayer", "successful update");
                    loadingDialog.dismiss();
                }
            };

            task.execute();

        }
    };
}
