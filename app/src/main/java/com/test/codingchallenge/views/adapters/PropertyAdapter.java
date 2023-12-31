package com.test.codingchallenge.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.codingchallenge.R;
import com.test.codingchallenge.models.search.PropertyResource;

import java.util.List;

//import butterknife.BindView;
//import butterknife.ButterKnife;

/**
 * Created for Coding Challenge Project of PF.
 */
public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {

    private List<PropertyResource> mPropertyList;

    public PropertyAdapter(List<PropertyResource> propertyList) {
        this.mPropertyList = propertyList;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_property, viewGroup, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder viewHolder, int position) {

        PropertyResource holderItem = mPropertyList.get(position);
        if (holderItem != null) {

            StringBuilder priceString = new StringBuilder();
            priceString.append(holderItem.getPrice())
                    .append(" ")
                    .append(holderItem.getPriceLabel());
            viewHolder.tvPrice.setText(priceString);

            StringBuilder detailString = new StringBuilder();
            detailString.append(holderItem.getBedrooms())
                    .append(" ").append("Bedrooms")
                    .append(" ")
                    .append(holderItem.getBathrooms())
                    .append(" ").append("Bathrooms");
            viewHolder.tvDetails.setText(detailString);

            Picasso.get()
                    .load(holderItem.getThumbnail())
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_report_image)
                    .noFade()
                    .into(viewHolder.ivThumbnail);

            Picasso.get()
                    .load(holderItem.getBroker().getAgentPhoto())
                    .placeholder(android.R.drawable.ic_menu_myplaces)
                    .resize(50, 50)
                    .centerCrop()
                    .noFade()
                    .into(viewHolder.ivAgent);
        }
    }

    @Override
    public int getItemCount() {
        return mPropertyList.size();
    }

    public void appendPropertyList(List<PropertyResource> propertyList) {
        int positionStart = this.mPropertyList.size();
        int itemCount = propertyList.size();

        this.mPropertyList.addAll(propertyList);
        notifyItemRangeInserted(positionStart, itemCount);
    }

    class PropertyViewHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.iv_property_thumbnail)
        ImageView ivThumbnail;
//        @BindView(R.id.tv_property_price)
        TextView tvPrice;
//        @BindView(R.id.tv_property_details)
        TextView tvDetails;
//        @BindView(R.id.iv_property_agent)
        ImageView ivAgent;

        PropertyViewHolder(@NonNull View itemView) {
            super(itemView);

//            // ButterKnife data binding of adapter
//            ButterKnife.bind(this, itemView);
        }
    }
}
