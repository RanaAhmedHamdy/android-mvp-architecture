package com.example.enozom.storesapp.stores;

/**
 * Created by enozom on 9/11/2017.
 */

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enozom.storesapp.R;
import com.example.enozom.storesapp.data.Store;
import com.squareup.picasso.Picasso;

public class StoresAdapter extends RecyclerView.Adapter<StoresAdapter.ViewHolder> {
    private List<Store> stores;
    private Context context;
    private final String TAG = "StoresAdapter";

    // Provide a suitable constructor (depends on the kind of dataset)
    public StoresAdapter(List<Store> stores, Context context) {
        this.stores = stores;
        this.context = context;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((ViewHolder) holder).bindViewOnItem(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StoresAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.store_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView storeName;
        private TextView storeDescription;
        private ImageView storeImage;


        public ViewHolder(View v) {
            super(v);
            storeName = (TextView) v.findViewById(R.id.store_name_tv);
            storeDescription = (TextView) v.findViewById(R.id.store_description_tv);
            storeImage = (ImageView) v.findViewById(R.id.store_imageview);
        }

        void bindViewOnItem(final int position) {
            Store store = stores.get(position);
            storeName.setText(store.getStoreName());
            storeDescription.setText(store.getStoreDescription());
            //Log.d(TAG,"bindViewOnItem:imageUrl  "+store.getStoreLogo().replace("http","https"));
            Picasso.with(context).load(store.getStoreLogo().replace("http","https")).into(storeImage);
        }
    }
}
