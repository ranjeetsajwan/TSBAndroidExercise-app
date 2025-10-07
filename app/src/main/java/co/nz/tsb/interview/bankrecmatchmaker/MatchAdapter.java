package co.nz.tsb.interview.bankrecmatchmaker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    public interface OnCheckedCountChanged {
        void onCheckedCountChanged(int count);
    }

    private final List<MatchItem> matchItems;
    private OnCheckedCountChanged listener;

    public MatchAdapter(List<MatchItem> matchItems) {
        this.matchItems = matchItems;
    }

    public void setOnCheckedCountChangedListener(OnCheckedCountChanged listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mainText;
        TextView total;
        TextView subtextLeft;
        TextView subtextRight;
        CheckedListItem checkedListItem;

        public ViewHolder(View itemView) {
            super(itemView);
            checkedListItem = (CheckedListItem) itemView; // your root layout
            mainText = itemView.findViewById(R.id.text_main);
            total = itemView.findViewById(R.id.text_total);
            subtextLeft = itemView.findViewById(R.id.text_sub_left);
            subtextRight = itemView.findViewById(R.id.text_sub_right);
        }

        public void bind(MatchItem matchItem) {
            mainText.setText(matchItem.getPaidTo());
            total.setText(Float.toString(matchItem.getTotal()));
            subtextLeft.setText(matchItem.getTransactionDate());
            subtextRight.setText(matchItem.getDocType());
            checkedListItem.setChecked(matchItem.isChecked());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CheckedListItem listItem = (CheckedListItem)
                layoutInflater.inflate(R.layout.list_item_match, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MatchItem matchItem = matchItems.get(position);
        holder.bind(matchItem);

        // handle clicks on the row
        holder.checkedListItem.setOnClickListener(v -> {
            boolean newChecked = !matchItem.isChecked();
            matchItem.setChecked(newChecked);
            holder.checkedListItem.setChecked(newChecked);

            // notify activity if set
            if (listener != null) {
                listener.onCheckedCountChanged(getCheckedItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchItems.size();
    }
 // Get count of checked items
    public int getCheckedItemCount() {
        int count = 0;
        for (MatchItem item : matchItems) {
            if (item.isChecked()) count++;
        }
        return count;
    }
}
