package com.example.realestate.view.adapter;import android.content.Context;import android.content.Intent;import android.os.Bundle;import android.view.LayoutInflater;import android.view.MenuItem;import android.view.View;import android.view.ViewGroup;import android.widget.PopupMenu;import androidx.annotation.NonNull;import androidx.recyclerview.widget.RecyclerView;import com.bumptech.glide.Glide;import com.example.realestate.R;import com.example.realestate.model.entity.EstateInfo;import com.example.realestate.view.activity.HomeActivity;import com.example.realestate.view.adapter.viewholder.EstateViewHolder;import com.example.realestate.view.dialog.EstateBottomSheet;import com.example.realestate.view.dialog.EstateDialog;import com.example.realestate.view.fragment.HomeFragment;import java.util.List;public class EstateRcyclerAdapter extends RecyclerView.Adapter<EstateViewHolder> {    private List<EstateInfo> estateInfoList;    private Context context;    public EstateRcyclerAdapter(List<EstateInfo> estateInfoList, Context context) {        this.estateInfoList = estateInfoList;        this.context = context;    }    @NonNull    @Override    public EstateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {        View view = LayoutInflater.from( context ).inflate( R.layout.item_recycler_estate, parent, false );        return new EstateViewHolder( view );    }    @Override    public void onBindViewHolder(@NonNull EstateViewHolder holder, int position) {        holder.getTxtRecyclerEstateArea().setText( estateInfoList.get( position ).getSpace() + "" );        holder.getTxtRecyclerEstatePrice().setText( estateInfoList.get( position ).getPrice() + "  " + "$" );        holder.getTxtRecyclerEstateLocation().setText( estateInfoList.get( position ).getLocation() );        holder.getView().setOnClickListener( new View.OnClickListener() {            @Override            public void onClick(View view) {                //Toast.makeText( context, "mmm", Toast.LENGTH_SHORT ).show();                Bundle args = new Bundle();                args.putString( "Office", estateInfoList.get( position ).getOffice() + "" );                args.putString( "Location", estateInfoList.get( position ).getLocation() + "" );                args.putString( "Price", estateInfoList.get( position ).getPrice() + "" );                args.putString( "Phone", estateInfoList.get( position ).getNumber() + "" );                EstateDialog estateDialog = new EstateDialog();                estateDialog.setArguments( args );                estateDialog.show( ((HomeActivity) context).getSupportFragmentManager(), "EstateDialog" );            }        } );        holder.getView().setOnLongClickListener( new View.OnLongClickListener() {            @Override            public boolean onLongClick(View view) {                PopupMenu popupMenu = new PopupMenu( context, holder.getView() );                popupMenu.inflate( R.menu.option_menu );                popupMenu.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {                    @Override                    public boolean onMenuItemClick(MenuItem menuItem) {                        switch (menuItem.getItemId()) {                            case R.id.deleteItem:                                ((HomeActivity) context).deleteByID( estateInfoList.get( position ).getID() );                                break;                            case R.id.updateItem:                                HomeFragment homeFragment = new HomeFragment();                                ((HomeActivity) context).getEstateByID( estateInfoList.get( position ).getID() );                                EstateBottomSheet estateBottomSheet = new EstateBottomSheet( ((HomeActivity) context).getCurrentEstate(), homeFragment.getLastTypeEstate() );                                estateBottomSheet.show( ((HomeActivity) context).getSupportFragmentManager(), "EstateBottomSheet" );                                break;                            case R.id.shareItem:                                Intent sharingIntent = new Intent( android.content.Intent.ACTION_SEND );                                sharingIntent.setType( "text/plain" );                                String shareBody = "Location :" + estateInfoList.get( position ).getLocation() +                                        "\nPrice :" + estateInfoList.get( position ).getPrice() + "$" +                                        "\nSpace :" + estateInfoList.get( position ).getSpace() + "m" +                                        "\nOffice :" + estateInfoList.get( position ).getOffice();                                sharingIntent.putExtra( android.content.Intent.EXTRA_SUBJECT, "Subject Here" );                                sharingIntent.putExtra( android.content.Intent.EXTRA_TEXT, shareBody );                                ((HomeActivity) context).startActivity( Intent.createChooser( sharingIntent, "Share via" ) );                                break;                        }                        return false;                    }                } );                popupMenu.show();                return true;            }        } );        Glide                .with( context )                .load( estateInfoList.get( position ).getImage() )                .placeholder( R.drawable.estate )                .into( holder.getImgRecyclerEstateImage() );    }    @Override    public int getItemCount() {        return estateInfoList.size();    }    public void setDate(List<EstateInfo> estateInfos) {        this.estateInfoList = estateInfos;        notifyDataSetChanged();    }}