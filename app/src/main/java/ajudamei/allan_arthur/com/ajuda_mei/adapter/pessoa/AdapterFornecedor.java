package ajudamei.allan_arthur.com.ajuda_mei.adapter.pessoa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.domain.pessoa.Fornecedor;

/**
 * Created by Allan on 08/07/2017.
 */

public class AdapterFornecedor extends ArrayAdapter<Fornecedor> {

    public AdapterFornecedor(Context context, int resource, List<Fornecedor> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dataregistro, parent, false);
        }

        Fornecedor temp = getItem(position);

        TextView data = (TextView) convertView.findViewById(R.id.txt_data);
        data.setText("Nome: " + temp.getNome());
        TextView qnt = (TextView) convertView.findViewById(R.id.txt_per);
        qnt.setText("Telefone: " + temp.getTelefone());

        return convertView;
    }
}
