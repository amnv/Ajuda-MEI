package ajudamei.allan_arthur.com.ajuda_mei.empregados;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import ajudamei.allan_arthur.com.ajuda_mei.R;

public class DireitosEmpregadosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direitos_empregados);
        String aux = "At vero eos et accusamus et iusto odio dignissimos" +
                " ducimus qui blanditiis praesentium voluptatum deleniti " +
                "atque corrupti quos dolores et quas molestias excepturi " +
                "sint occaecati cupiditate non provident, similique sunt " +
                "in culpa qui officia deserunt mollitia animi, id est laborum " +
                "et dolorum fuga. Et harum quidem rerum facilis est et expedita " +
                "distinctio. Nam libero tempore, cum soluta nobis est eligendi " +
                "optio cumque nihil impedit quo minus id quod maxime placeat " +
                "facere possimus, omnis voluptas assumenda est, omnis dolor " +
                "repellendus. Temporibus autem quibusdam et aut officiis debitis " +
                "aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae " +
                "sint et molestiae non recusandae. Itaque earum rerum hic tenetur a " +
                "sapiente delectus, ut aut reiciendis voluptatibus maiores alias " +
                "consequatur aut perferendis doloribus asperiores repellat.";
        final TextView texto = (TextView) findViewById(R.id.txt_descricao);
        texto.setText(aux);
    }
}
