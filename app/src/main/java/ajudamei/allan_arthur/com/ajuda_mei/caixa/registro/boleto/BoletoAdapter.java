package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.boleto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;

/**
 * Created by Allan on 22/06/2017.
 */

public class BoletoAdapter extends ArrayAdapter<Boleto> {

    public BoletoAdapter(Context context, int resource, List<Boleto> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.boletolista, parent, false);
        }

        Boleto temp = getItem(position);

        TextView descricao = (TextView) convertView.findViewById(R.id.registro_boleto_info);
        descricao.setText(temp.getNome());
        TextView preco = (TextView) convertView.findViewById(R.id.registro_boleto_preco);
        preco.setText(String.valueOf(temp.getPreco()));
        TextView data = (TextView) convertView.findViewById(R.id.registro_boleto_data);
        data.setText(temp.getData());

        return convertView;
    }
}
