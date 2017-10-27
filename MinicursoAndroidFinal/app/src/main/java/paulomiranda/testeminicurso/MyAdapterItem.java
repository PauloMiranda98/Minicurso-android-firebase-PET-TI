package paulomiranda.testeminicurso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapterItem extends BaseAdapter{

    private Context ctx;
    private List<Tarefa> lista;

    public MyAdapterItem(Context ctx, List<Tarefa> lista) {
        this.ctx = ctx;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View layout;

        if(view == null){
            LayoutInflater mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = mInflater.inflate(R.layout.adapter_layout, null);
        }else{
            layout = view;
        }

        Tarefa mTarefa = lista.get(i);

        TextView txt_titulo = (TextView) layout.findViewById(R.id.txt_titulo);
        TextView txt_descricao = (TextView) layout.findViewById(R.id.txt_descricao);
        TextView txt_materia = (TextView) layout.findViewById(R.id.txt_materia);
        TextView txt_data = (TextView) layout.findViewById(R.id.txt_data);

        txt_titulo.setText(mTarefa.getTitulo());
        txt_descricao.setText(mTarefa.getDescricao());
        txt_materia.setText(mTarefa.getMateria());
        txt_data.setText(mTarefa.getData());

        return layout;
    }
}
