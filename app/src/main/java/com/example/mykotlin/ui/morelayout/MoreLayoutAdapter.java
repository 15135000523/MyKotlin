package com.example.mykotlin.ui.morelayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mykotlin.R;

import java.util.ArrayList;
import java.util.List;


public class MoreLayoutAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;

    private List<MoreLayoutBean> mList;

    private static final int BANNER = 0;
    private static final int COLUMN = 1;
    private static final int MARQUEE = 2;
    private static final int NUM_TWO = 3;
    private static final int TITLE = 4;
    private static final int NUM_THREE = 5;
    private static final int NORMAL = 6;

    public MoreLayoutAdapter(Context mContext, List<MoreLayoutBean> list) {
        inflater = LayoutInflater.from(mContext);
        mList = new ArrayList();
        mList.addAll(list);
    }

    public void setmList(List<MoreLayoutBean> mList) {
        this.mList.clear();
        this.mList.addAll(mList);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        } else if (position >= 1 && position <= 8) {
            return COLUMN;
        } else if (position == 9) {
            return MARQUEE;
        } else if (position >= 10 && position <= 13) {
            return NUM_TWO;
        } else if (position == 14 || position == 18) {
            return TITLE;
        } else if (position >= 15 && position <= 17) {
            return NUM_THREE;
        } else if (position >= 19 && position <= 24) {
            return NORMAL;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
//            case BANNER:
//                View itemBanner = inflater.inflate(R.layout.item_banner, parent, false);
//                return new BannerHolder(itemBanner);
//            case COLUMN:
//                View itemColumn = inflater.inflate(R.layout.item_column, parent, false);
//                return new ColumnHolder(itemColumn);
//            case MARQUEE:
//                View itemMarquee = inflater.inflate(R.layout.item_marquee, parent, false);
//                return new MarqueeHolder(itemMarquee);
//            case NUM_TWO:
//                View itemNumTwo = inflater.inflate(R.layout.item_num_two, parent, false);
//                return new NumTwoHolder(itemNumTwo);
//            case TITLE:
//                View itemTitle = inflater.inflate(R.layout.item_title, parent, false);
//                return new TitleHolder(itemTitle);
//            case NUM_THREE:
//                View itemNumThree = inflater.inflate(R.layout.item_num_three, parent, false);
//                return new NumThreeHolder(itemNumThree);
//            case NORMAL:
//                View itemNormal = inflater.inflate(R.layout.item_normal, parent, false);
//                return new NormalHolder(itemNormal);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof BannerHolder) {
//            setBanner((BannerHolder) holder);
//        } else if (holder instanceof ColumnHolder) {
//
//        } else if (holder instanceof MarqueeHolder && marqueeList != null) {
//            setMarquee((MarqueeHolder) holder);
//        } else if (holder instanceof NumTwoHolder) {
//
//        } else if (holder instanceof TitleHolder) {
//            setTitle((TitleHolder) holder, position);
//        } else if (holder instanceof NumThreeHolder) {
//
//        } else if (holder instanceof NormalHolder) {
//
//        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case BANNER:
                            return 12;
                        case COLUMN:
                            return 3;
                        case MARQUEE:
                            return 12;
                        case NUM_TWO:
                            return 6;
                        case TITLE:
                            return 12;
                        case NUM_THREE:
                            return 4;
                        case NORMAL:
                            return 6;
                        default:
                            return 12;
                    }
                }
            });
        }
    }

    public class BannerHolder extends RecyclerView.ViewHolder {

        BannerHolder(View itemView) {
            super(itemView);
        }
    }

    public class ColumnHolder extends RecyclerView.ViewHolder {

        public ColumnHolder(View itemView) {
            super(itemView);
        }
    }

    public class MarqueeHolder extends RecyclerView.ViewHolder {

        public MarqueeHolder(View itemView) {
            super(itemView);
        }
    }

    public class NumTwoHolder extends RecyclerView.ViewHolder {

        public NumTwoHolder(View itemView) {
            super(itemView);
        }
    }

    public class TitleHolder extends RecyclerView.ViewHolder {

        public TitleHolder(View itemView) {
            super(itemView);
        }
    }

    public class NumThreeHolder extends RecyclerView.ViewHolder {

        public NumThreeHolder(View itemView) {
            super(itemView);
        }
    }


    public class NormalHolder extends RecyclerView.ViewHolder {

        public NormalHolder(View itemView) {
            super(itemView);
        }
    }

}
