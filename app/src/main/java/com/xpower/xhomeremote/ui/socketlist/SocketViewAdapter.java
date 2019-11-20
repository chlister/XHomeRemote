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
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xpower.xhomeremote.R;
import com.xpower.xhomeremote.data.model.SocketDTO;

import java.util.ArrayList;
import java.util.List;

public class SocketViewAdapter extends RecyclerView.Adapter<SocketViewAdapter.ViewHolder> {
    private List<SocketDTO> mData;
    private LayoutInflater mInflater;

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public SocketViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = new ArrayList<>();
    }

    /**
     * @author  Martin J. J.
     * @version 1.0
     * @since   11/20/2019
     * @status  Under Development
     */
    public void setData(List<SocketDTO> data){
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
        holder.nameTextView.setText(mData.get(position).name);
        //holder.typeTextView.setText(mData.get(position).type);
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
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameTextView, typeTextView;
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
            switchView = itemView.findViewById(R.id.SocketViewAdapter_Switch);
        }

        /**
         * @author  Martin J. J.
         * @version 1.0
         * @since   11/20/2019
         * @status  Defined
         */
        @Override
        public void onClick(View v) {

        }
    }
}
