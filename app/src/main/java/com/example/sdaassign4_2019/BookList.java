package com.example.sdaassign4_2019;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * Images used are sourced from Public Domain Day 2019.
 * by Duke Law School's Center for the Study of the Public Domain
 * is licensed under a Creative Commons Attribution-ShareAlike 3.0 Unported License.
 * A simple {@link Fragment} subclass.
 * @author Chris Coughlan
 */

public class BookList extends Fragment {

    public BookList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_book_list, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.bookView_view);

        //add array for each item
        ArrayList<String> mAuthor = new ArrayList<>();
        ArrayList<String> mTitle = new ArrayList<>();
        ArrayList<String> mImageUri = new ArrayList<>();

        //adding storage URL reference for book images
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku10001.jpg?alt=media&token=24f6c534-7258-4d70-bfbb-503ee8d4cf4c");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku10002.jpg?alt=media&token=60d4734f-96a5-497d-80b1-8e82cedce8d4");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku10003.jpg?alt=media&token=075d6d06-6cc9-4cb0-8e67-ab0021729eba");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku10004.jpg?alt=media&token=c61cd210-c603-4804-b0ee-ad224d7a01a9");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku10005.jpg?alt=media&token=e9f5eb4d-35fc-4d5c-b0eb-ca9aac57559a");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku10006.jpg?alt=media&token=c0db15a1-d1ab-48aa-a180-3314a4409fac");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku10007.jpg?alt=media&token=b92964bc-64bf-43a4-9187-9752580814c4");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku10008.jpg?alt=media&token=9dee7ddc-b054-4867-81fa-9f4644c16a47");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku10009.jpg?alt=media&token=7b9c4737-9cdd-45a6-890f-f15ac633c981");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku100010.jpg?alt=media&token=4e833698-76bd-40f9-949f-0240380f1ea3");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku100011.jpg?alt=media&token=47ecdc79-27b4-4fe8-a999-df218ce6e746");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku100012.jpg?alt=media&token=9b15eb41-5867-4bd8-a23a-6b4b03e6fe49");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku100013.jpg?alt=media&token=4871e2b6-2082-4953-aebf-b2be32d9c35b");
        mImageUri.add("https://firebasestorage.googleapis.com/v0/b/book-club-99c23.appspot.com/o/images%2Fsku100014.jpg?alt=media&token=2168114a-c151-4fda-9b78-a0b45e59d762");

        //adding author and title.
        mAuthor.add("Edgar Rice Burroughs"); mTitle.add("Tarzan and the Golden Lion");
        mAuthor.add("Agatha Christie"); mTitle.add("The Murder on the Links");
        mAuthor.add("Winston S. Churchill"); mTitle.add("The World Crisis");
        mAuthor.add("E.e. cummings"); mTitle.add("Tulips and Chimneys");
        mAuthor.add("Robert Frost"); mTitle.add("New Hampshire");
        mAuthor.add("Kahlil Gibran"); mTitle.add("The Prophet");
        mAuthor.add("Aldous Huxley"); mTitle.add("Antic Hay");
        mAuthor.add("D.H. Lawrence"); mTitle.add("Kangaroo");
        mAuthor.add("Bertrand and Dora Russell"); mTitle.add("The Prospects of Industrial Civilization");
        mAuthor.add("Carl Sandberg"); mTitle.add("Rootabaga Pigeons");
        mAuthor.add("Edith Wharton"); mTitle.add("A Son at the Front");
        mAuthor.add("P.G. Wodehouse"); mTitle.add("The Inimitable Jeeves");
        mAuthor.add("P.G. Wodehouse"); mTitle.add("Leave it to Psmith");
        mAuthor.add("Viginia Woolf"); mTitle.add("Jacob's Room");

        LibraryViewAdapter recyclerViewAdapter = new LibraryViewAdapter(getContext(), mAuthor, mTitle, mImageUri);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

}
