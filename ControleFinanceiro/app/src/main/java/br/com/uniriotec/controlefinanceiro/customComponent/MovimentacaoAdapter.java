package br.com.uniriotec.controlefinanceiro.customComponent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.uniriotec.controlefinanceiro.R;
import br.com.uniriotec.controlefinanceiro.model.Movimentacao;

/**
 * Created by PedroLZ on 14/12/2015.
 */
public class MovimentacaoAdapter extends ArrayAdapter<Movimentacao>{

	private int resource;

	public MovimentacaoAdapter(Context context, int resource) {
		super(context, resource);
		this.resource = resource;
	}

	public MovimentacaoAdapter(Context context, int resource, List<Movimentacao> objects) {
		super(context, resource, objects);
		this.resource = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View linha = convertView;

		if (linha == null) {
			LayoutInflater layoutInflater = LayoutInflater.from(getContext());
			linha = layoutInflater.inflate(resource, null);
		}

		Movimentacao movimentacao = getItem(position);

		if (movimentacao != null) {
			TextView txtDia = (TextView) linha.findViewById(R.id.txtDia);
			TextView txtDescricao = (TextView) linha.findViewById(R.id.txtDescricao);
			TextView txtValor = (TextView) linha.findViewById(R.id.txtValor);

			txtDia.setText(movimentacao.getDiaDescr());
			txtDescricao.setText(movimentacao.getDescricao());
			txtValor.setText(movimentacao.getValorDescr());
			int corDoCampoValor = getContext().getResources().getColor(obterIdCorDoCampoValor(movimentacao));
			txtValor.setTextColor(corDoCampoValor);
		}

		return linha;
	}

	private int obterIdCorDoCampoValor(Movimentacao movimentacao) {
		if (movimentacao.isValorPositivo()) {
			if (movimentacao.isEfetuada()) {
				return R.color.cor_valor_positivo_efetuado;
			} else {
				return R.color.cor_valor_positivo_nao_efetuado;
			}
		} else {
			if (movimentacao.isEfetuada()) {
				return R.color.cor_valor_negativo_efetuado;
			} else {
				return R.color.cor_valor_negativo_nao_efetuado;
			}
		}
	}

}
