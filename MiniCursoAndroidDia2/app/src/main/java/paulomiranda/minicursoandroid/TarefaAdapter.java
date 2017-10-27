package paulomiranda.minicursoandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Paulo Miranda on 04/10/2017.
 */

public class TarefaAdapter extends BaseAdapter{

    private Context ctx;
    private List<Tarefa> lista;

    public TarefaAdapter(Context ctx, List<Tarefa> lista) {
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
    public View getView(int i, View v, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater)
                ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.layout_tarefa, null);
        TextView txt_titulo = view.findViewById(R.id.txt_titulo);
        TextView txt_descricao = view.findViewById(R.id.txt_descricao);
        TextView txt_disciplina = view.findViewById(R.id.txt_disciplina);
        TextView txt_data = view.findViewById(R.id.txt_data);

        Tarefa minhaTarefa = lista.get(i);

        txt_titulo.setText(minhaTarefa.getTitulo());
        txt_descricao.setText(minhaTarefa.getDescricao());
        txt_disciplina.setText(minhaTarefa.getDisciplina());
        txt_data.setText(minhaTarefa.getData());

        return view;
    }
}











