package com.example.realestate.view.dialog;import android.content.Intent;import android.net.Uri;import android.os.Bundle;import android.os.Handler;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.RelativeLayout;import android.widget.TextView;import androidx.annotation.NonNull;import androidx.annotation.Nullable;import androidx.fragment.app.DialogFragment;import androidx.viewpager2.widget.CompositePageTransformer;import androidx.viewpager2.widget.MarginPageTransformer;import androidx.viewpager2.widget.ViewPager2;import com.example.realestate.R;import com.example.realestate.model.entity.SlideImage;import com.example.realestate.view.adapter.SliderAdapter;import java.util.ArrayList;import java.util.List;public class EstateDialog extends DialogFragment {    private TextView txtOfficeNameDialogEstate;    private TextView txtLocationDialogEstate;    private TextView txtPriceDialogEstate;    private RelativeLayout relCallPhone;    private RelativeLayout relWhatsapp;    private ViewPager2 viewPagerDialogSlideImage;    private Handler slideHandler;    private List<SlideImage>slideImages;    private SliderAdapter sliderAdapter;    private String office;    private String location;    private String price;    private String phone;    @Nullable    @Override    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        View view=inflater.inflate( R.layout.dialog_estate,container );        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.popup_background);        txtOfficeNameDialogEstate=view.findViewById( R.id.txtOfficeNameDialogEstate );        relCallPhone=view.findViewById( R.id.relCallPhone);        relWhatsapp=view.findViewById( R.id.relWhatsapp );        txtLocationDialogEstate=view.findViewById( R.id.txtLocationDialogEstate );        txtPriceDialogEstate=view.findViewById( R.id.txtPriceDialogEstate );        txtOfficeNameDialogEstate=view.findViewById( R.id.txtOfficeNameDialogEstate );        slideHandler=new Handler();        viewPagerDialogSlideImage=view.findViewById( R.id.viewPagerDialogSlideImage );        slideImages=new ArrayList<>();        slideImages.add( new SlideImage( R.drawable.estate1 ) );        slideImages.add( new SlideImage( R.drawable.estate1 ) );        slideImages.add( new SlideImage( R.drawable.estate1 ) );        slideImages.add( new SlideImage( R.drawable.estate1 ) );        slideImages.add( new SlideImage( R.drawable.estate1 ) );        sliderAdapter=new SliderAdapter( viewPagerDialogSlideImage, slideImages);        viewPagerDialogSlideImage.setAdapter( sliderAdapter );        viewPagerDialogSlideImage.setClipToPadding( false );        viewPagerDialogSlideImage.setClipChildren( false );        viewPagerDialogSlideImage.setOffscreenPageLimit( 3 );        viewPagerDialogSlideImage.getChildAt( 0 ).setOverScrollMode( View.OVER_SCROLL_NEVER );        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();        compositePageTransformer.addTransformer( new MarginPageTransformer( 40 ) );        compositePageTransformer.addTransformer( new ViewPager2.PageTransformer() {            @Override            public void transformPage(@NonNull View page, float position) {                float r=1-Math.abs( position );                page.setScaleY( 0.85f+r*0.15f );            }        } );        viewPagerDialogSlideImage.setPageTransformer( compositePageTransformer );        viewPagerDialogSlideImage.registerOnPageChangeCallback( new ViewPager2.OnPageChangeCallback() {            @Override            public void onPageScrollStateChanged(int state) {                super.onPageScrollStateChanged( state );                slideHandler.removeCallbacks( slideRunnable );                slideHandler.postDelayed( slideRunnable,3000 );            }        } );        Bundle mArgs = getArguments();        office=mArgs.getString( "Office" );        location=mArgs.getString( "Location" );        price=mArgs.getString( "Price" );        phone=mArgs.getString( "Phone" );        txtOfficeNameDialogEstate.setText( office+"");        txtLocationDialogEstate.setText( location+"" );        txtPriceDialogEstate.setText( price+"$" );        relCallPhone.setOnClickListener( new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent intent = new Intent(Intent.ACTION_DIAL);                intent.setData( Uri.parse("tel:"+phone));                startActivity(intent);            }        } );        relWhatsapp.setOnClickListener( new View.OnClickListener() {            @Override            public void onClick(View view) {                String url = "https://api.whatsapp.com/send?phone="+"+963"+phone;                Intent i = new Intent(Intent.ACTION_VIEW);                i.setData( Uri.parse(url));                startActivity(i);            }        } );        return view;    }    private Runnable slideRunnable=new Runnable() {        @Override        public void run() {            viewPagerDialogSlideImage.setCurrentItem( viewPagerDialogSlideImage.getCurrentItem()+1 );        }    };}