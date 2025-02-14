package de.geeksfactory.opacclient.frontend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.format.DateTimeFormat;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import de.geeksfactory.opacclient.R;
import de.geeksfactory.opacclient.objects.Copy;

public class CopiesAdapter extends RecyclerView.Adapter<CopiesAdapter.ViewHolder> {
    private final List<Copy> copies;
    private final Context context;

    public CopiesAdapter(List<Copy> copies, Context context) {
        this.copies = copies;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_copy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Copy copy = copies.get(position);
        setTextOrHide(copy.getBranch(), holder.tvBranch);
        setTextOrHide(copy.getDepartment(), holder.tvDepartment);
        setTextOrHide(copy.getLocation(), holder.tvLocation);
        setTextOrHide(copy.getShelfmark(), holder.tvShelfmark);
        setTextOrHide(copy.getStatus(), holder.tvStatus);
        setTextOrHide(copy.getReservations(), holder.tvReservations);
        setTextOrHide(copy.getUrl(), holder.tvUrl);
        if (copy.getReturnDate() != null) {
            holder.tvReturndate.setText(DateTimeFormat.shortDate().print(copy.getReturnDate()));
            holder.tvReturndate.setVisibility(View.VISIBLE);
        } else {
            holder.tvReturndate.setVisibility(View.GONE);
        }
    }

    private void setTextOrHide(String text, TextView tv) {
        if (notEmpty(text)) {
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return copies.size();
    }

    private boolean notEmpty(String text) {
        return text != null && !text.isEmpty();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBranch;
        public TextView tvDepartment;
        public TextView tvLocation;
        public TextView tvShelfmark;
        public TextView tvStatus;
        public TextView tvReservations;
        public TextView tvReturndate;
        public TextView tvUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvBranch = (TextView) itemView.findViewById(R.id.tvBranch);
            this.tvDepartment = (TextView) itemView.findViewById(R.id.tvDepartment);
            this.tvLocation = (TextView) itemView.findViewById(R.id.tvLocation);
            this.tvShelfmark = (TextView) itemView.findViewById(R.id.tvShelfmark);
            this.tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);
            this.tvReservations = (TextView) itemView.findViewById(R.id.tvReservations);
            this.tvReturndate = (TextView) itemView.findViewById(R.id.tvReturndate);
            this.tvUrl = (TextView) itemView.findViewById(R.id.tvUrl);
        }
    }
}
