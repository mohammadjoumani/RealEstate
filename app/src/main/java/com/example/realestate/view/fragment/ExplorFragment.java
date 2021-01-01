package com.example.realestate.view.fragment;import android.os.Bundle;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.view.animation.AnimationUtils;import android.view.animation.LayoutAnimationController;import android.widget.ImageButton;import android.widget.ImageView;import androidx.annotation.NonNull;import androidx.fragment.app.Fragment;import androidx.lifecycle.Observer;import androidx.lifecycle.ViewModelProviders;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;import com.example.realestate.R;import com.example.realestate.model.entity.Estate;import com.example.realestate.model.entity.EstateInfo;import com.example.realestate.model.entity.Office;import com.example.realestate.view.activity.HomeActivity;import com.example.realestate.view.adapter.EstateRcyclerAdapter;import com.example.realestate.viewmodel.EstateViewModel;import com.facebook.shimmer.ShimmerFrameLayout;import java.util.ArrayList;import java.util.List;import io.paperdb.Paper;public class ExplorFragment extends Fragment {    private ImageButton btnFragExplorBackToOpartion;    private LayoutAnimationController layoutAnimationController;    private ImageView imgFragExplorEmptyData;    private SwipeRefreshLayout swipeRefreshFragExplorEstateRecycler;    private RecyclerView recyclerFragExplorEstate;    private ShimmerFrameLayout shimmerAnimationFragExplorEstate;    private RecyclerView.LayoutManager layoutManagerMyEstate;    private EstateRcyclerAdapter myEstateRcyclerAdapter;    private EstateViewModel estateViewModel;    private List<EstateInfo> myEstates;    private List<Estate> estateAll;    private Office office;    public View onCreateView(@NonNull LayoutInflater inflater,                             ViewGroup container, Bundle savedInstanceState) {        View view = inflater.inflate( R.layout.fragment_explor, container, false );        office = new Office();        office = Paper.book().read( "office" );        estateViewModel = ViewModelProviders.of( this ).get( EstateViewModel.class );        btnFragExplorBackToOpartion=view.findViewById( R.id.btnFragExplorBackToOpartion );        imgFragExplorEmptyData = view.findViewById( R.id.imgFragExplorEmptyData );        layoutAnimationController = AnimationUtils.loadLayoutAnimation( getActivity(), R.anim.layout_animation_recycler );        swipeRefreshFragExplorEstateRecycler = view.findViewById( R.id.swipeRefreshFragExplorEstateRecycler );        recyclerFragExplorEstate = view.findViewById( R.id.recyclerFragExplorEstate );        shimmerAnimationFragExplorEstate = view.findViewById( R.id.shimmerAnimationFragExplorEstate );        recyclerFragExplorEstate.setHasFixedSize( true );        layoutManagerMyEstate = new LinearLayoutManager( getActivity(), RecyclerView.VERTICAL, false );        recyclerFragExplorEstate.setLayoutManager( layoutManagerMyEstate );        myEstates = new ArrayList<>();        estateAll = new ArrayList<>();        myEstateRcyclerAdapter = new EstateRcyclerAdapter( myEstates ,getActivity() );        recyclerFragExplorEstate.setAdapter( myEstateRcyclerAdapter );        btnFragExplorBackToOpartion.setOnClickListener( new View.OnClickListener() {            @Override            public void onClick(View view) {                ((HomeActivity)getActivity()).finshActivity();            }        } );        swipeRefreshFragExplorEstateRecycler.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {            @Override            public void onRefresh() {                getMyEstate();                swipeRefreshFragExplorEstateRecycler.setRefreshing( false );            }        } );        shimmerAnimationFragExplorEstate.setVisibility( View.VISIBLE );        shimmerAnimationFragExplorEstate.showShimmer( true );        getMyEstate();        return view;    }    ///region My Estate    public void getMyEstate() {        estateViewModel.getMyEstate( office.getId() ).observe( getActivity(), new Observer<List<EstateInfo>>() {            @Override            public void onChanged(List<EstateInfo> myEstateInfo) {                myEstates = myEstateInfo;                myEstateRcyclerAdapter.setDate( myEstateInfo );                recyclerFragExplorEstate.setLayoutAnimation( layoutAnimationController );                recyclerFragExplorEstate.getAdapter().notifyDataSetChanged();                recyclerFragExplorEstate.scheduleLayoutAnimation();                if (myEstateInfo.size() == 0) {                    imgFragExplorEmptyData.setVisibility( View.VISIBLE );                    shimmerAnimationFragExplorEstate.hideShimmer();                    shimmerAnimationFragExplorEstate.setVisibility( View.GONE );                } else {                    imgFragExplorEmptyData.setVisibility( View.GONE );                }                shimmerAnimationFragExplorEstate.hideShimmer();                shimmerAnimationFragExplorEstate.setVisibility( View.GONE );            }        } );    }    ///endregion}