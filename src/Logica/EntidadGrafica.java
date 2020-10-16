package Logica;
import javax.swing.*;

public class EntidadGrafica {
	private ImageIcon gr�fico;
	private String[] numeros;
	private int indexActualEnS;
	
	public EntidadGrafica() {
		this.gr�fico=new ImageIcon();
		this.numeros = new String[]{"/img/1.png","/img/2.png","/img/3.png","/img/4.png","/img/5.png","/img/6.png","/img/7.png","/img/8.png","/img/9.png"} ;
		this.indexActualEnS = 9; //para poder comparar que celdas se actualizaron y cuales no
	}
	
	public void actualizar(int index) {
		if( index < numeros.length ) {
			ImageIcon ImageIcon = new ImageIcon(this.getClass().getResource(this.numeros[index]));
			this.gr�fico.setImage(ImageIcon.getImage());
			this.indexActualEnS=index;
		}
	}
	
	public ImageIcon getGrafico() {
		return this.gr�fico;
	}
	
	public int getIndexActual() {
		return this.indexActualEnS;
	}
	
	public void setGrafico(ImageIcon grafico) {
		this.gr�fico=grafico;
	}
	
	public String[] getNumeros() {
		return this.numeros;
	}
	
	public void setNumeros(String[] n) {
		this.numeros=n;
	}
}

