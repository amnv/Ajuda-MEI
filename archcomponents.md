A refatoração substituiu a classe DatabaseRegistroBoleto pertencente ao pacote ajudamei.allan_arthur.com.ajuda_mei.database

Esta classe contém código para criar, inserir e recurar dados da tabela boletos_registrados utilizando a classe SQLiteOpenHelper

Foi criado o pacote ajudamei.allan_arthur.com.ajuda_mei.database.archComponent para colocar as classe que envolviam o "Architecture Components". Foram criados as classes RegistroBoletoRoom, RegistroBoletoDao, RegistroBoletoDatabase.

Além disso, foi necessário modificar as classes AdicionarBoletoActivity, PagamentoBoletoActivity onde efetivamente ocorreram refatoração de código. Visto que havia uma classe básica chamada Boleto na qual decidir não mexer evitando criar erros adicionais no código, decidir criar um método dentro de RegistroBoletoRoom para converter desta classe para a classe Boleto. Desta forma não foi necessário mexer na classe adapter.

O resultado foi um código muito mais conciso e fácil de manter. Utilizar Archature Component me pareceu ser muito viável.
