/**
 * TODO: Add class description
 *
 * @author Martin J. J.
 * @version 1.0
 * @since 11/20/2019
 */
package com.xpower.xhomeremote.ui.socketlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.HomeApplianceType;
import com.xpower.xhomeremote.data.model.Socket;

import java.util.ArrayList;
import java.util.List;

public class SocketViewAdapter extends RecyclerView.Adapter<SocketViewAdapter.ViewHolder> {
    private List<Socket> mData;
    private LayoutInflater mInflater;
    private static onClickListner onclicklistner;
    private Context mContext;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public SocketViewAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = new ArrayList<>();
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public void setData(List<Socket> data){
        this.mData = data;
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_socket_card, parent, false);
        return new ViewHolder(view);
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mData.get(position).name.isEmpty()){
            holder.nameTextView.setVisibility(View.GONE);
            holder.agentIdTextView.setText(Integer.toString(mData.get(position).agentId));
            holder.socketIdTextView.setText(Integer.toString(mData.get(position).id));
        }
        else{
            holder.agentIdLayout.setVisibility(View.GONE);
            holder.socketIdLayout.setVisibility(View.GONE);
            holder.nameTextView.setText(mData.get(position).name);
        }
        if(mData.get(position).type == HomeApplianceType.NON){
            holder.typeTextView.setText(mContext.getString(R.string.not_registered));
            holder.typeHeadTextView.setVisibility(View.GONE);
        }
        else
            holder.typeTextView.setText(mData.get(position).type.name());
    }


    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public void setOnItemClickListener(onClickListner onclicklistner) {
        this.onclicklistner = onclicklistner;
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public interface onClickListner {
        void onItemClick(Socket item);
        void onItemLongClick(Socket item);
    }


    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView nameTextView, typeTextView, agentIdTextView, socketIdTextView, typeHeadTextView;
        LinearLayout agentIdLayout, socketIdLayout;
        Switch switchView;


        /**
         * @author  Martin J. J.
         * @version 1.0
         * @since   11/20/2019
         * @status  Under Development
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.SocketViewAdapter_Name);
            typeTextView = itemView.findViewById(R.id.SocketViewAdapter_Type);
            agentIdTextView = itemView.findViewById(R.id.SocketViewAdapter_AgentID);
            socketIdTextView = itemView.findViewById(R.id.SocketViewAdapter_SocketID);
            typeHeadTextView = itemView.findViewById(R.id.SocketViewAdapter_type_head_tv);

            agentIdLayout = itemView.findViewById(R.id.SocketViewAdapter_agentID_ll);
            socketIdLayout = itemView.findViewById(R.id.SocketViewAdapter_SocketID_ll);
            switchView = itemView.findViewById(R.id.SocketViewAdapter_Switch);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onclicklistner.onItemClick(mData.get(getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            onclicklistner.onItemLongClick(mData.get(getAdapterPosition()));
            return false;
        }
    }
}
