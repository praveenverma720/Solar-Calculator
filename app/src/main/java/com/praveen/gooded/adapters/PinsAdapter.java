package com.praveen.gooded.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.praveen.gooded.R;
import com.praveen.gooded.modals.Pins;

import java.util.List;

public class PinsAdapter extends RecyclerView.Adapter<PinsAdapter.PinsViewHolder> {
    class PinsViewHolder extends RecyclerView.ViewHolder {
        private final TextView pinsItemView;

        private PinsViewHolder(View itemView) {
            super(itemView);
            pinsItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Pins> pinsList; // Cached copy of words

    public PinsAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PinsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PinsViewHolder(itemView);
    }

    public double[] getGeoLocation(int position) {
        Pins currentPin = pinsList.get(position);
        return new double[]{currentPin.getLatitude(),currentPin.getLongitude()};
    }

    @Override
    public void onBindViewHolder(PinsViewHolder holder, int position) {
        if (pinsList != null) {
            Pins current = pinsList.get(position);
            holder.pinsItemView.setText(String.format("%s,%s", current.getLatitude(), current.getLongitude()));
        } else {
            // Covers the case of data not being ready yet.
            holder.pinsItemView.setText(R.string.nopins);
        }
    }

    public void setPins(List<Pins> pins) {
        pinsList = pins;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (pinsList != null)
            return pinsList.size();
        else return 0;
    }
}
