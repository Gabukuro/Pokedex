package com.example.pokedex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pokedex.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FeedImageAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> applications;



    public FeedImageAdapter(@NonNull Context context, int resource, @NonNull List<FeedEntry> applications) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        FeedEntry currentApp = applications.get(position);

        viewHolder.name.setText(currentApp.getName());
        viewHolder.num.setText(currentApp.getNum());
        viewHolder.height.setText(currentApp.getHeight());
        viewHolder.weight.setText(currentApp.getWeight());
        try{
            viewHolder.type1.setText(currentApp.getType().get(0));
            viewHolder.type2.setText(currentApp.getType().get(1));
        }catch (IndexOutOfBoundsException e){
        }

        new DownloadImageTask(viewHolder.img).execute(currentApp.getImg());

        return convertView;
    }

    private class ViewHolder {
        final TextView name;
        final TextView num;
        final TextView height;
        final TextView weight;
        final TextView type1;
        final TextView type2;
        final ImageView img;

        ViewHolder(View v) {
            this.name = v.findViewById(R.id.name);
            this.num = v.findViewById(R.id.num);
            this.height = v.findViewById(R.id.height);
            this.weight = v.findViewById(R.id.weight);
            this.type1 = v.findViewById(R.id.type1);
            this.type2 = v.findViewById(R.id.type2);
            this.img = v.findViewById(R.id.img);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            url = url.replaceFirst("http://", "https://");

            Bitmap bmp = null;
            try {
                InputStream inputStream = new URL(url).openStream();
                bmp = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            bmImage.setImageBitmap(bitmap);
        }
    }
}