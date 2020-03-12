package com.example.helloword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class winneradapter extends RecyclerView.Adapter<winneradapter.MyviewHolder> {

     LayoutInflater inflater;
     List<winnerclass> winnerList;
     public winneradapter(Context context, List<winnerclass> winnerList){
         inflater = LayoutInflater.from(context);
         this.winnerList = winnerList;
     }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = inflater.inflate(R.layout.leaaderboardlyout,parent,false);
         MyviewHolder myviewHolder = new MyviewHolder(view);
         return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
         winnerclass currentuser = winnerList.get(position);

         holder.position.setText(position+1 +".");
         holder.name.setText(currentuser.getName());
         holder.year.setText(currentuser.getYear());


    }

    @Override
    public int getItemCount() {
        return winnerList.size();
    }

    class  MyviewHolder extends  RecyclerView.ViewHolder{

         TextView position,name,year;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.winnerposition_id);
            name = itemView.findViewById(R.id.winnername);
            year = itemView.findViewById(R.id.winneryear_id);

        }
    }
}
