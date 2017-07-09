package ajudamei.allan_arthur.com.ajuda_mei.adapter.pessoa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.domain.pessoa.Empregado;

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

        TextView nome = (TextView) convertView.findViewById(R.id.empregado_nome);
        nome.setText("Nome: " + temp.getNome());
        TextView salario = (TextView) convertView.findViewById(R.id.empregado_salario);
        salario.setText("Salário: R$" + temp.getSalario());
        TextView per = (TextView) convertView.findViewById(R.id.empregado_per);
        per.setText("Recebe a cada: " + temp.getPeriodicidade() + " dias");
        return convertView;
    }
}
