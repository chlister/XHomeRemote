/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.ui.outletlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Outlet;

import java.util.ArrayList;
import java.util.List;

public class OutletViewAdapter extends RecyclerView.Adapter<OutletViewAdapter.ViewHolder> {
    private List<Outlet> mData;
    private LayoutInflater mInflater;
    private static onClickListner mOnclicklistner;
    private Context mContext;

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    public OutletViewAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = new ArrayList<>();
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    public void setData(List<Outlet> data){
        this.mData = data;
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_outlet_card, parent, false);
        return new ViewHolder(view);
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mData.get(position).name.isEmpty()){
            holder.mNameTextView.setVisibility(View.GONE);
            holder.mAgentIdLayout.setVisibility(View.VISIBLE);
            holder.mOutletIdLayout.setVisibility(View.VISIBLE);
            holder.mAgentIdTextView.setText(Integer.toString(mData.get(position).agentId));
            holder.mOutletIdTextView.setText(Integer.toString(mData.get(position).id));
        }
        else{
            holder.mNameTextView.setVisibility(View.VISIBLE);
            holder.mAgentIdLayout.setVisibility(View.GONE);
            holder.mOutletIdLayout.setVisibility(View.GONE);
            holder.mNameTextView.setText(mData.get(position).name);
        }
        if(mData.get(position).type == HomeApplianceType.getNonType()){
            holder.mTypeTextView.setText(mContext.getString(R.string.not_registered));
            holder.mTypeHeadTextView.setVisibility(View.GONE);
        }
        else {
            holder.mTypeTextView.setText(mData.get(position).type.name);
            holder.mTypeHeadTextView.setVisibility(View.VISIBLE);
        }
        onClickListner temp = mOnclicklistner;
        setOnItemClickListener(getDummyonLick());
        holder.mSwitchView.setChecked(mData.get(position).state);
        setOnItemClickListener(temp);
        if(mData.get(position).state)
            holder.mImageView.setImageResource(mData.get(position).type.onRessource);
        else
            holder.mImageView.setImageResource(mData.get(position).type.offRessource);
    }

    public static onClickListner getDummyonLick(){
        return new onClickListner() {
            @Override
            public void onItemClick(Outlet item) {

            }

            @Override
            public void onItemLongClick(Outlet item) {

            }

            @Override
            public void onChangeListener(Outlet item, boolean isChecked) {

            }
        };
    }


    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    public void setOnItemClickListener(onClickListner onclicklistner) {
        this.mOnclicklistner = onclicklistner;
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    public interface onClickListner {
        void onItemClick(Outlet item);
        void onItemLongClick(Outlet item);
        void onChangeListener(Outlet item, boolean isChecked);
    }


    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Under Development
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, CompoundButton.OnCheckedChangeListener {
        ImageView mImageView;
        TextView mNameTextView, mTypeTextView, mAgentIdTextView, mOutletIdTextView, mTypeHeadTextView;
        LinearLayout mAgentIdLayout, mOutletIdLayout;
        Switch mSwitchView;


        /**
         * @author  Martin J. J.
         * @since   11/20/2019
         * @status  Under Development
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.OutletViewAdapter_Outlet_ImageView);
            mNameTextView = itemView.findViewById(R.id.OutletViewAdapter_Name);
            mTypeTextView = itemView.findViewById(R.id.OutletViewAdapter_Type);
            mAgentIdTextView = itemView.findViewById(R.id.OutletViewAdapter_AgentID);
            mOutletIdTextView = itemView.findViewById(R.id.OutletViewAdapter_OutletID);
            mTypeHeadTextView = itemView.findViewById(R.id.OutletViewAdapter_type_head_tv);

            mAgentIdLayout = itemView.findViewById(R.id.OutletViewAdapter_agentID_ll);
            mOutletIdLayout = itemView.findViewById(R.id.OutletViewAdapter_OutletID_ll);
            mSwitchView = itemView.findViewById(R.id.OutletViewAdapter_Switch);
            mSwitchView.setOnCheckedChangeListener(this);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        /**
         * @author  Martin J. J.
         * @since   11/20/2019
         * @status  Under Development
         */
        @Override
        public void onClick(View v) {
            mOnclicklistner.onItemClick(mData.get(getAdapterPosition()));
        }

        /**
         * @author  Martin J. J.
         * @since   11/20/2019
         * @status  Under Development
         */
        @Override
        public boolean onLongClick(View v) {
            mOnclicklistner.onItemLongClick(mData.get(getAdapterPosition()));
            return false;
        }

        /**
         * @author  Martin J. J.
         * @since   11/20/2019
         * @status  Under Development
         */
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mOnclicklistner.onChangeListener(mData.get(getAdapterPosition()), isChecked);
        }
    }
}
