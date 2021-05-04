package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controller.FabricanteController;
import model.Fabricante;

@SuppressWarnings("rawtypes")
@FacesConverter("conversor")
public class Conversor implements Converter{

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			FabricanteController fabricanteControle = new FabricanteController();
			Fabricante fabricante = new Fabricante();
			Long codigo = Long.parseLong(value);
			fabricante = fabricanteControle.fabricantePorCodigo(codigo);
			System.out.println("getAsObject: "+value);
			return fabricante;
		} catch (RuntimeException e) {
			return null;
		}
		
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Long codigo = 0L;
		try {
			if(value != null || value != "") {
				Fabricante fabricante = (Fabricante) value;
				codigo = fabricante.getCodigo();
			}
			
			System.out.println("getAsString: "+value);
			return codigo.toString();
		} catch (RuntimeException e) {
			return null;
		}
		
	}

}
