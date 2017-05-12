package eskool.com.eskoolapp.Gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import eskool.com.eskoolapp.R;

/**
 * Created by user on 5/11/2017.
 */

public class GalleryParent extends Fragment {
    ImageView album1;

    public GalleryParent() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gallery_parent, container, false);
        album1 = (ImageView) view.findViewById(R.id.album1);
        album1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
               GalleryParent2 frag1 = new GalleryParent2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;

    }
}
