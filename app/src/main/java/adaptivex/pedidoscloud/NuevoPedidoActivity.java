package adaptivex.pedidoscloud;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import adaptivex.pedidoscloud.Config.Constants;
import adaptivex.pedidoscloud.Config.GlobalValues;
import adaptivex.pedidoscloud.Core.FactoryRepositories;
import adaptivex.pedidoscloud.Repositories.PedidoRepository;
import adaptivex.pedidoscloud.Core.IniciarApp;
import adaptivex.pedidoscloud.Entity.PedidoEntity;
import adaptivex.pedidoscloud.Servicios.Helpers.HelperPedidos;
import adaptivex.pedidoscloud.View.Pedidos.CargarHeladosFragment;

public class NuevoPedidoActivity extends AppCompatActivity implements  CargarHeladosFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_nuevo_pedido, fragment).addToBackStack(null)
                .commit();
*/


    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    public long crearNuevoPedido(){
        try{
            IniciarApp ia = new IniciarApp(this);
            ia.isLoginRemember();
            PedidoRepository gestdb = new PedidoRepository(this);
            Date fecha = new Date();
            Calendar cal = Calendar.getInstance();
            fecha = cal.getTime();
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            String fechaDMY = df1.format(fecha);
            //Nuevo Pedido
            PedidoEntity pedido = new PedidoEntity();
            pedido.setEstadoId(Constants.ESTADO_NUEVO);
            pedido.setCliente_id(GlobalValues.getINSTANCIA().getUsuariologueado().getId());
            pedido.setCreated(fechaDMY);
            long id = gestdb.abrir().agregar(pedido);
            pedido.setAndroid_id(id);
            gestdb.cerrar();
            FactoryRepositories.getInstancia().PEDIDO_TEMPORAL = new PedidoEntity();
            FactoryRepositories.getInstancia().PEDIDO_TEMPORAL = pedido;

            gestdb.cerrar();
            Toast.makeText(this, "Generando Nuevo Pedido  "+ String.valueOf(id) , Toast.LENGTH_SHORT).show();
            return id;
        }catch (Exception e ){
            Toast.makeText(this, "Error: " +e.getMessage(),Toast.LENGTH_LONG).show();
            return 0;
        }
    }





    public boolean enviarPedido(){
        try{
            //SE graba el pedido en la base de datos android
            PedidoEntity p;
            p = FactoryRepositories.getInstancia().PEDIDO_TEMPORAL;
            PedidoRepository pc = new PedidoRepository(this.getBaseContext());
            Long idTmp = pc.abrir().agregar(p);
            pc.cerrar();

            //Se envia a la web Base de datos MYSQL
            HelperPedidos hp = new HelperPedidos(this.getBaseContext(), idTmp, GlobalValues.getINSTANCIA().OPTION_HELPER_ENVIO_PEDIDO);
            hp.execute();
            return true;
        }catch(Exception e){
            Toast.makeText(getBaseContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
