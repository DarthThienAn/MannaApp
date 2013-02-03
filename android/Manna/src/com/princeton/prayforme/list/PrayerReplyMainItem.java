package com.princeton.prayforme.list;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.princeton.prayforme.GlobalConstants;
import com.princeton.prayforme.R;
import com.princeton.prayforme.Security;
import com.princeton.prayforme.activity.PostActivity;
import com.princeton.prayforme.asynctask.AsyncDelete;
import com.princeton.prayforme.asynctask.AsyncPut;
import com.princeton.prayforme.helper.SharedPrefsHelper;
import com.princeton.prayforme.helper.URLHelper;
import com.princeton.prayforme.model.Prayer;

public class PrayerReplyMainItem extends ListItem<Prayer> {

    private Activity context;
    private SharedPrefsHelper prefsHelper;
    ProgressDialog loadingDialog;

    public PrayerReplyMainItem(Activity context, Prayer model) {
        super(model);
        this.context = context;
        prefsHelper = new SharedPrefsHelper(context.getApplicationContext());
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
        author.setText(model.getPerson());
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

        String mySignature = new SharedPrefsHelper(context.getApplicationContext()).getSignature();
        if (!mySignature.equals(Security.ANONYMOUS_SIGNATURE) && (model.getSignature().equals(mySignature))) {
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

    private final View.OnClickListener editOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent startEditIntent = new Intent(context.getApplicationContext(), PostActivity.class);
            startEditIntent.putExtra(GlobalConstants.KEY_POST_SUBJECT, model.getSubject());
            startEditIntent.putExtra(GlobalConstants.KEY_POST_TEXT, model.getMessage());
            startEditIntent.putExtra(GlobalConstants.KEY_POST_ID, model.getId());
            context.startActivity(startEditIntent);
        }
    };

    private final View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        final AlertDialog deleteDialog = new AlertDialog.Builder(context)
                .setCancelable(true)
                .setTitle("Are you sure?")
                .setMessage("Deleting your prayer will delete it and its replies forever.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AsyncDelete task = new AsyncDelete(URLHelper.deletePrayerURL(model.getId(), prefsHelper.getSignature(), prefsHelper.getName())) {
                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                            }

                            @Override
                            protected void onPostExecute(Void s) {
                                super.onPostExecute(s);
                                Toast.makeText(context, "Prayer deleted", Toast.LENGTH_SHORT).show();
                                context.finish();
                            }
                        };
                        task.execute();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create();

            deleteDialog.show();

        }
    };

}
