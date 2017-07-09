package ajudamei.allan_arthur.com.ajuda_mei.adapter.registro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Aquisicao;

/**
 * Created by Allan on 22/06/2017.
 */

public class AquisicaoAdapter extends ArrayAdapter<Aquisicao> {

    public AquisicaoAdapter(Context context, int resource, List<Aquisicao> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.registrolista, parent, false);
        }

        Aquisicao temp = getItem(position);

        TextView descricao = (TextView) convertView.findViewById(R.id.registro_info);
        descricao.setText(temp.getDescricao());
        TextView preco = (TextView) convertView.findViewById(R.id.registro_preco);
        preco.setText(String.valueOf(temp.getPreco()));
        TextView data = (TextView) convertView.findViewById(R.id.registro_data);
        data.setText(temp.getData());

        return convertView;
    }
}
