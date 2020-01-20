package com.example.sdaassign4_2019;

        /*
         * Copyright (C) 2016 The Android Open Source Project
         *
         * Licensed under the Apache License, Version 2.0 (the "License");
         * you may not use this file except in compliance with the License.
         * You may obtain a copy of the License at
         *
         *      http://www.apache.org/licenses/LICENSE-2.0
         *
         * Unless required by applicable law or agreed to in writing, software
         * distributed under the License is distributed on an "AS IS" BASIS,
         * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
         * See the License for the specific language governing permissions and
         * limitations under the License.
         */

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentTransaction;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.annotation.GlideModule;
        import com.google.firebase.storage.StorageReference;

        import static android.content.Context.MODE_PRIVATE;
/*
 * @author Chris Coughlan 2019
 */

public class LibraryViewAdapter extends RecyclerView.Adapter<LibraryViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private Context mNewContext;

    //add array for each item\
    private ArrayList<String> mAuthor;
    //declare methods

    private ArrayList<String> mTitle;
    //private ArrayList<Integer> mImageID;
    private ArrayList<String> mImageUri;



    LibraryViewAdapter(Context mNewContext, ArrayList<String> author, ArrayList<String> title, ArrayList<String> imageUri) {
    //LibraryViewAdapter(Context mNewContext, ArrayList<String> author, ArrayList<String> title, ArrayList<Integer> imageId) {
        this.mNewContext = mNewContext;
        this.mAuthor = author;
        this.mTitle = title;
        //  this.mImageID = imageId;
        this.mImageUri = imageUri;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: was called");

        viewHolder.authorText.setText(mAuthor.get(position));
        viewHolder.titleText.setText(mTitle.get(position));
        //viewHolder.imageItem.setImageResource(mImageID.get(position));
        //viewHolder.imageItem.Glide.with(this).load(storageReference).into()

        //Glide.with(viewHolder.getContext());

        //for (int i = 0; i < mImageUri.size(); i++) {
            try {
                Glide.with(viewHolder.imageItem.getContext())
                        .load(mImageUri.get(position))
                        .into(viewHolder.imageItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        //}

        //Task 3 to check if the user saved settings and to send the check result from Settings

        //should check here to see if the book is available.
        viewHolder.checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack if needed
                    ;

                    //SharedPreferences preferences = this.getPreferences(MODE_PRIVATE);
                    // Commit the transaction
                    //commit();

                //Intent mySettingsReceive = new Intent(getActivity(),Settings.class);
                //Bundle extras = getIntent().getExtras();
                //mNewContext.startActivity(mySettingsReceive);


            //}
                //final String statusCheck = extras.getString("SETTINGS_CHECK");

                //if(statusCheck == "true"){

                    Toast.makeText(mNewContext, mTitle.get(position), Toast.LENGTH_SHORT).show();
                    //...
                    Intent myOrder = new Intent (mNewContext, CheckOut.class);
                    myOrder.putExtra("BOOK_TITLE",mTitle.get(position));
                    mNewContext.startActivity(myOrder);}

                //else {
                 //   Toast.makeText(mNewContext, "You need to update your settings first", Toast.LENGTH_SHORT).show();
                 //   mNewContext.startActivity(mySettings);
                //}

            //}
        });

    }

    @Override
    public int getItemCount() {
        return mAuthor.size();
    }

    //view holder class for recycler_list_item.xml
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageItem;
        TextView authorText;
        TextView titleText;
        Button checkOut;
        RelativeLayout itemParentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            //grab the image, the text and the layout id's
            imageItem = itemView.findViewById(R.id.bookImage);
            authorText = itemView.findViewById(R.id.authorText);
            titleText = itemView.findViewById(R.id.bookTitle);
            checkOut = itemView.findViewById(R.id.out_button);
            itemParentLayout = itemView.findViewById(R.id.listItemLayout);

        }
    }
}
