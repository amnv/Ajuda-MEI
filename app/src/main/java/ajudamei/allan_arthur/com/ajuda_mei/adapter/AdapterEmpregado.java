package ajudamei.allan_arthur.com.ajuda_mei.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.domain.Empregado;

/**
 * Created by Allan on 08/07/2017.
 */

public class AdapterEmpregado extends ArrayAdapter<Empregado> {

    public AdapterEmpregado(Context context, int resource, List<Empregado> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if( convertView == null ){
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.empregado, parent, false);
        }

        Empregado temp = getItem(position);

        ImageView img = (ImageView) convertView.findViewById(R.id.imagem);
        img.setImageBitmap(temp.getFoto());
        TextView nome = (TextView) convertView.findViewById(R.id.empregado_nome);
        nome.setText("Nome: " + temp.getNome());
        TextView cpf = (TextView) convertView.findViewById(R.id.empregado_cpf);
        cpf.setText("CPF: " + temp.getCpf());
        TextView qnt = (TextView) convertView.findViewById(R.id.empregado_telefone);
        qnt.setText("Telefone: " + temp.getTelefone());
        return convertView;
    }
}
