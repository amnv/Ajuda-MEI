package ajudamei.allan_arthur.com.ajuda_mei.adapter.registro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Registro;

/**
 * Created by arthur on 28/06/17.
 */

public class AdapterRegistro extends ArrayAdapter<Registro> {

    public AdapterRegistro(Context context, int resource, List<Registro> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dataregistro, parent, false);
        }

        Registro temp = getItem(position);

        TextView data = (TextView) convertView.findViewById(R.id.txt_data);
        data.setText("Data: " + temp.getData());
        TextView qnt = (TextView) convertView.findViewById(R.id.txt_per);
        qnt.setText("Qtd: " + String.valueOf(temp.getQuantidade()));

        return convertView;
    }
}
