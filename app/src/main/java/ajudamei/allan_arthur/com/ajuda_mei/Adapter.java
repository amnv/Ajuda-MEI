package ajudamei.allan_arthur.com.ajuda_mei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Allan on 31/05/2017.
 */

public class Adapter extends ArrayAdapter<ItemMateriaPrima> {

    public Adapter(Context context, int resource, List<ItemMateriaPrima> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.itemlista, parent, false);
        }

        Item temp = getItem(position);

        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        nome.setText(temp.getNome());
        TextView tamanho = (TextView) convertView.findViewById(R.id.tamanho);
        tamanho.setText(temp.getTamanho());
        TextView preco = (TextView) convertView.findViewById(R.id.preco_und);
        preco.setText(String.valueOf(temp.getPreco()));
        TextView qnt = (TextView) convertView.findViewById(R.id.quantidade);
        qnt.setText(String.valueOf(temp.getQuantidade()));

        return convertView;
    }
}
