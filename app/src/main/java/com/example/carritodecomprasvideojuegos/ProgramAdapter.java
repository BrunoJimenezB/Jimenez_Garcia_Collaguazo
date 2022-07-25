package com.example.carritodecomprasvideojuegos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Vector;

public class ProgramAdapter extends ArrayAdapter<String> {
    Context context;
    Vector images;
    //String[] programName;
    Vector programName;
    Vector programDescription;
    Vector codigo;
    Vector precio;
    //String[] urls;

    public ProgramAdapter(Context context, Vector programName, Vector images, Vector programDescription, Vector codigo,  Vector precio ) {
        super(context, R.layout.single_item, R.id.textviewNombreP, programName);
        this.context = context;
        this.images = images;
        this.programName = programName;
        this.programDescription = programDescription;
        this.codigo = codigo;
        this.precio = precio;
    }

    /*
    Additional code
    public ProgramAdapter(Context context, String[] programName, int[] images, String[] programDescription, String[] urls) {
        super(context, R.layout.single_item, R.id.textView1, programName);
        this.context = context;
        this.images = images;
        this.programName = programName;
        this.programDescription = programDescription;
        this.urls = urls;
    }
     */

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // The parameter convertView is null when your app is creating a new item for the first time. It's not null when
        // recycling.
        // Assign the convertView in a View object
        View singleItem = convertView;
        // Find a View from the entire View hierarchy by calling findViewById() is a fairly expensive task.
        // So, you'll create a separate class to reduce the number of calls to it.
        // First, create a reference of ProgramViewHolder and assign it to null.
        ProgramViewHolder holder = null;
        // Since layout inflation is a very expensive task, you'll inflate only when creating a new item in the ListView. The first
        // time you're creating a new item, convertView will be null. So, the idea is when creating an item for the first time,
        // we should perform the inflation and initialize the ViewHolder.
        if(singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.single_item, parent, false);
            // Pass the singleItem to the constructor of ProgramViewHolder. This singleItem object contains a LinearLayout
            // as the root element for single_item.xml file that contains other Views as well for the ListView.
            holder = new ProgramViewHolder(singleItem);
            // When you create an object of ProgramViewHolder, you're actually calling findViewById() method inside the constructor.
            // By creating ProgramViewHolder only when making new items, you call findViewById() only when making new rows.
            // At this point all the three Views have been initialized. Now you need to store the holder so that you don't need to
            // create it again while recycling and you can do this by calling setTag() method on singleItem and passing the holder as a parameter.
            singleItem.setTag(holder);
        }
        // If singleItem is not null then we'll be recycling
        else{
            // Get the stored holder object
            holder = (ProgramViewHolder) singleItem.getTag();
        }
        // Set the values for your views in your item by accessing them through the holder
      //  holder.itemImage.setImageResource(images.get(position).toString());
        Picasso.get() //llamo a la imagen
                .load(images.get(position).toString())
                .resize(100, 100) //doy una tamaño de 100x100
                .into(holder.itemImage);
        holder.programTitle.setText(programName.get(position).toString());
        holder.programDescription.setText(programDescription.get(position).toString());
        holder.precio.setText(precio.get(position).toString());
        holder.codigo.setText(codigo.get(position).toString());
        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(getContext(), "You clicked:"+  MyApplication.valorInicial, Toast.LENGTH_SHORT).show();
              Intent intent = new Intent( context,  SelecccionJuego.class);
               //intent.putExtra("img", images[(Integer)view.getTag()]);
                intent.putExtra("nombreVideojuego", programName.get(position).toString());
               intent.putExtra("Stock", programDescription.get(position).toString());
                intent.putExtra("Precio", precio.get(position).toString());
              intent.putExtra("image", images.get(position).toString());
              intent.putExtra("codig", codigo.get(position).toString());
                intent.putExtra("ValorCedula", MyApplication.valorInicial );
               context.startActivity(intent);
               // ((Activity)context).finish();
                // Intent openLinksIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls[position]));
                //context.startActivity(openLinksIntent);
            }
        });
        return singleItem;
    }
}
