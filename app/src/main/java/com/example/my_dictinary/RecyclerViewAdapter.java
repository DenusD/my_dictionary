package com.example.my_dictinary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    String[] engWords, ukrWords;
    Context context;
    public RecyclerViewAdapter(Context context, String[] englishWordsArray, String[] ukrainianWordsArray){
        this.context = context;
        engWords = englishWordsArray;
        ukrWords = ukrainianWordsArray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textNumber.setText(Integer.toString(position + 1));
        holder.textEnglish.setText(engWords[position]);
        holder.textUkrainian.setText(ukrWords[position]);

        holder.textNum.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("englishWord", engWords[position]);
                intent.putExtra("ukrainianWord", ukrWords[position]);
                context.startActivity(intent);
                return false;
            }
        });
        holder.textEng.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("englishWord", engWords[position]);
                intent.putExtra("ukrainianWord", ukrWords[position]);
                context.startActivity(intent);
                return false;
            }
        });
        holder.textUkr.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("englishWord", engWords[position]);
                intent.putExtra("ukrainianWord", ukrWords[position]);
                context.startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return engWords.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textNumber, textEnglish, textUkrainian;
        //LinearLayout lineLayout;
        TextView textNum, textEng, textUkr;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textNumber = itemView.findViewById(R.id.textNumber);
            textEnglish = itemView.findViewById(R.id.textEnglish);
            textUkrainian = itemView.findViewById(R.id.textUkrainian);
            textNum = itemView.findViewById(R.id.textNumber);
            textEng = itemView.findViewById(R.id.textEnglish);
            textUkr = itemView.findViewById(R.id.textUkrainian);
            //lineLayout = itemView.findViewById(R.id.lineLayout);
        }

    }
}
