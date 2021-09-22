package cl.tofcompany.drivindesafio.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import cl.tofcompany.drivindesafio.R;
import cl.tofcompany.drivindesafio.activities.MainActivity;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    //Initialize variable
    private final Activity activity;
    private final ArrayList<String> dataArrayList;

    //create constructor
    public MainAdapter(Activity activity, ArrayList<String> dataArrayList) {
        this.activity = activity;
        this.dataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initialize view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Initialize main data
        String imagen = dataArrayList.get(position);
        //set image on imageview
        Picasso.get().load(imagen).into(holder.mImageView);
        holder.itemView.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Perdon no he logrado capturar los" +
                            "nombres de esta raza no estaba bien claro la " +
                            "documentacion de la api la position de" +
                            "este imagen es " + position)
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialogInterface, i) -> activity.getApplicationContext()).create().show();
        });

    }


    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        ImageView mImageView;
        TextView mTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }
}
