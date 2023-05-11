package vn.iotstar.finalproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import vn.iotstar.finalproject.ViewModel.ViewPageImage;
import vn.iotstar.finalproject.R;

public class ImagesViewPageAdapter extends PagerAdapter {
    private List<ViewPageImage> imagesList;
    public ImagesViewPageAdapter(List<ViewPageImage> imagesList){
        this.imagesList=imagesList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpager_item, container, false);
        ImageView imageView = view.findViewById(R.id.viewpager_img);
        ViewPageImage images = imagesList.get(position);
        imageView.setImageResource(images.getImagesId());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount(){
        if(imagesList!=null)
        {
            return imagesList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject (@NonNull View view, @NonNull Object object){
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((View) object);
    }
}
