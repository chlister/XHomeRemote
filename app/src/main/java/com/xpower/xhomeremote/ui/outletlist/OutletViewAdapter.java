/**
 * This i a Recyclerview.Adapter that creates the cardviews without the recycleview and binds the
 * Outlets to those cardviews.
 *
 * @author Martin J. J.
 * @version 2.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.ui.outletlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
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

public class OutletViewAdapter extends RecyclerView.Adapter<OutletViewAdapter.ViewHolder>
        implements Filterable {
    private List<Outlet> mData;
    private List<Outlet> mFilteredData;
    private LayoutInflater mInflater;
    private static ICardClickListner mOnclicklistner;
    private Context mContext;

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    public OutletViewAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = new ArrayList<>();
        this.mFilteredData = new ArrayList<>();
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    public void setData(List<Outlet> data){
        this.mData = data;
        this.mFilteredData = data;
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_outlet_card, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This method binds the data to the view.
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mFilteredData.get(position).name.isEmpty() ||mFilteredData.get(position).name == ("outlet_" + mFilteredData.get(position).id)){
            holder.mNameTextView.setVisibility(View.GONE);
            holder.mAgentIdLayout.setVisibility(View.VISIBLE);
            holder.mOutletIdLayout.setVisibility(View.VISIBLE);
            holder.mAgentIdTextView.setText(Integer.toString(mFilteredData.get(position).agentId));
            holder.mOutletIdTextView.setText(Integer.toString(mFilteredData.get(position).id));
        }
        else{
            holder.mNameTextView.setVisibility(View.VISIBLE);
            holder.mAgentIdLayout.setVisibility(View.GONE);
            holder.mOutletIdLayout.setVisibility(View.GONE);
            holder.mNameTextView.setText(mFilteredData.get(position).name);
        }
        if(mFilteredData.get(position).type.name == HomeApplianceType.getNonType().name){
            holder.mTypeTextView.setText(mContext.getString(R.string.not_registered));
            holder.mTypeHeadTextView.setVisibility(View.GONE);
        }
        else {
            holder.mTypeTextView.setText(mFilteredData.get(position).type.name);
            holder.mTypeHeadTextView.setVisibility(View.VISIBLE);
        }
        ICardClickListner temp = mOnclicklistner;
        setOnItemClickListener(getEmptyICardClickListner());
        holder.mSwitchView.setChecked(mFilteredData.get(position).state);
        setOnItemClickListener(temp);
        if(mFilteredData.get(position).state)
            holder.mImageView.setImageResource(mFilteredData.get(position).type.onRessource);
        else
            holder.mImageView.setImageResource(mFilteredData.get(position).type.offRessource);
    }

    /**
     * This method is use for when the data is being binded to the view, if this is not use during
     * binding to state update will trigger the onChangeLister and keep request new data
     * @author  Martin J. J.
     * @since   11/28/2019
     * @status  Done
     */
    public static ICardClickListner getEmptyICardClickListner(){
        return new ICardClickListner() {
            @Override
            public void onItemClick(Outlet item) {

            }

            @Override
            public void onItemLongClick(Outlet item) {
                int i = 0;
            }

            @Override
            public void onChangeListener(Outlet item, boolean isChecked) {
                int i = 0;
            }
        };
    }


    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    @Override
    public int getItemCount() {
        return mFilteredData.size();
    }

    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    public void setOnItemClickListener(ICardClickListner onclicklistner) {
        this.mOnclicklistner = onclicklistner;
    }

    /**
     * Returns a filter that constrains data with the filter pattern.
     * @author  Martin J. J.
     * @since   12/1/2019
     * @status  Done
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredData = mData;
                } else {
                    List<Outlet> filteredList = new ArrayList<>();
                    for (Outlet outlet : mData) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (outlet.name.toLowerCase().contains(charString.toLowerCase()) ||
                                outlet.type.name.toLowerCase().contains(charString.toLowerCase()) ||
                                Integer.toString(outlet.agentId).contains(charSequence) ||
                                Integer.toString(outlet.id).contains(charSequence) )
                        {
                            filteredList.add(outlet);
                        }
                    }

                    mFilteredData = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredData = (ArrayList<Outlet>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    /**
     * Listener interface for the ViewCards
     * @author  Martin J. J.
     * @since   11/28/2019
     * @status  Done
     */
    public interface ICardClickListner {
        void onItemClick(Outlet item);
        void onItemLongClick(Outlet item);
        void onChangeListener(Outlet item, boolean isChecked);
    }


    /**
     * @author  Martin J. J.
     * @since   11/20/2019
     * @status  Done
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener, CompoundButton.OnCheckedChangeListener {
        ImageView mImageView;
        TextView mNameTextView, mTypeTextView, mAgentIdTextView, mOutletIdTextView, mTypeHeadTextView;
        LinearLayout mAgentIdLayout, mOutletIdLayout;
        Switch mSwitchView;


        /**
         * Contructor for the ViewHolder, that binds the layout to the ViewHolder
         * @author  Martin J. J.
         * @since   11/20/2019
         * @status  Done
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
         * @since   11/28/2019
         * @status  Done
         */
        @Override
        public void onClick(View v) {
            mOnclicklistner.onItemClick(mFilteredData.get(getAdapterPosition()));
        }

        /**
         * @author  Martin J. J.
         * @since   11/28/2019
         * @status  Done
         */
        @Override
        public boolean onLongClick(View v) {
            mOnclicklistner.onItemLongClick(mFilteredData.get(getAdapterPosition()));
            return false;
        }

        /**
         * @author  Martin J. J.
         * @since   11/28/2019
         * @status  Done
         */
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(((Outlet)mFilteredData.get(getAdapterPosition())).state != isChecked)
                mOnclicklistner.onChangeListener(mFilteredData.get(getAdapterPosition()), isChecked);
        }
    }
}
