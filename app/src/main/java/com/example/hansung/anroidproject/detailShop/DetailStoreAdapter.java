package com.example.hansung.anroidproject.detailShop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hansung.anroidproject.R;
import com.example.hansung.anroidproject.deprecated.model.Product;
import com.example.hansung.anroidproject.model.Stylist;

import java.util.List;

/**
 * Created by kimsungmin on 2017-11-26.
 */

public class DetailStoreAdapter extends RecyclerView.Adapter<DetailStoreAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> productList;
    private String profileImageUrl, ShopName, StylistName, uid, StoreAddress;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ProductName, ProductPrice;
        public Button reservation_button;

        public MyViewHolder(View view) {
            super(view);
            ProductName = (TextView) view.findViewById(R.id.productName);
            ProductPrice = (TextView) view.findViewById(R.id.productPrice);
            reservation_button = (Button) view.findViewById(R.id.reservation_button);
        }
    }


    public DetailStoreAdapter(Context mContext, List<Product> productList, String profileImageUrl, String ShopName, String StoreAddress, String StylistName, String uid) {
        this.mContext = mContext;
        this.productList = productList;
        this.profileImageUrl = profileImageUrl;
        this.ShopName = ShopName;
        this.StoreAddress = StoreAddress;
        this.StylistName = StylistName;
        this.uid = uid;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Product product = productList.get(position);
        holder.ProductName.setText(product.getProductName());
        holder.ProductPrice.setText(product.getProductPrice() + "원");

        // loading album cover using Glide library
        //Glide.with(mContext).load(product.getProductName()).into(holder.ProductName);

        holder.reservation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, productList.get(position).getProductName() + "+" + uid + "예약하기 버튼 누름", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, DetailStorePOP.class);

                intent.putExtra("ProductName", product.getProductName());
                intent.putExtra("ProductPrice", product.getProductPrice() + "원");
                intent.putExtra("StylistImageUrl", profileImageUrl);
                intent.putExtra("StoreName", ShopName);
                intent.putExtra("StoreAddress", StoreAddress);
                intent.putExtra("StylistName", StylistName);
                intent.putExtra("destinationUid", uid);

                mContext.startActivity(intent);
            }
        });

    }


    //팝업창으로부터 data 전달 받는 상황일때
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 1) {
//            if (resultCode == Activity.RESULT_OK) {
//                //데이터 받기
//                String result = data.getStringExtra("result");
//                txtResult.setText(result);
//            }
//        }
//    }


    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_album, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
