package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.pagamento.boleto;

import org.junit.Before;
import org.junit.Test;

import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseRegistroBoleto;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Boleto;

import static junit.framework.Assert.assertEquals;

/**
 * Created by allyson on 11/12/17.
 */
public class AdicionarBoletoActivityTest {
    DatabaseRegistroBoleto db;
    @Before
    public void setup()
    {
        db = new DatabaseRegistroBoleto(null);
    }

    @Test
    public  void can_add_boleto()
    {
        Boleto b = new Boleto("d", 10.0,"11/11/11", 10, "t");
        db.insert(b);
        assertEquals(b.getDescricao(), "d");
    }
}